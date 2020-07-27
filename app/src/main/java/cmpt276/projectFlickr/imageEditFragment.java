package cmpt276.projectFlickr;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cmpt276.project.R;

import static cmpt276.projectFlickr.ImagesArray.myImages;

/*
    This fragment allows users to removes images by clicking on them,
    it then refreshes the page to show the updates list.
 */

public class imageEditFragment extends Fragment implements OnNoteListener {
        private static final String TAG = "imageEditFragment";

        private RecyclerView mPhotoRecyclerView;
        private List<GalleryItem> mItems = myImages;
        private ThumbnailDownloader<PhotoHolder> mThumbnailDownloader;

        public static imageEditFragment newInstance() {
            return new imageEditFragment();
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setRetainInstance(true);
            setHasOptionsMenu(true);

            Handler responseHandler = new Handler();
            mThumbnailDownloader = new ThumbnailDownloader<>(responseHandler);
            mThumbnailDownloader.setThumbnailDownloadListener(
                    new ThumbnailDownloader.ThumbnailDownloadListener<PhotoHolder>() {
                        @Override
                        public void onThumbnailDownloaded(PhotoHolder photoHolder, Bitmap bitmap) {
                            Drawable drawable = new BitmapDrawable(getResources(), bitmap);
                            photoHolder.bindDrawable(drawable);
                        }
                    }
            );
            mThumbnailDownloader.start();
            mThumbnailDownloader.getLooper();
            Log.i(TAG, "Background thread started");
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_image_edit_page, container, false);

            mPhotoRecyclerView = (RecyclerView) v.findViewById(R.id.edit_recycler_view);
            mPhotoRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));

            setupAdapter();

            return v;
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            mThumbnailDownloader.clearQueue();
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            mThumbnailDownloader.quit();
            Log.i(TAG, "Background thread destroyed");
        }

        private void setupAdapter() {
            if (isAdded()) {
                mPhotoRecyclerView.setAdapter(new PhotoAdapter(mItems, this));
            }
        }

        @Override
        public void onNoteClick(int position) {
            GalleryItem myItem = mItems.get(position);
            myImages.remove(myItem);
            getActivity().finish();
            startActivity(getActivity().getIntent());
        }

        private class PhotoHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            private ImageView mItemImageView;
            OnNoteListener onNoteListener;

            public PhotoHolder(View itemView, OnNoteListener onNoteListener) {
                super(itemView);
                mItemImageView = (ImageView) itemView.findViewById(R.id.item_image_view);
                this.onNoteListener = onNoteListener;
                itemView.setOnClickListener(this);
            }

            public void bindDrawable(Drawable drawable) {
                mItemImageView.setImageDrawable(drawable);
            }

            @Override
            public void onClick(View v) {
                onNoteListener.onNoteClick(getAdapterPosition());
            }
        }


        private class PhotoAdapter extends RecyclerView.Adapter<PhotoHolder> {

            private List<GalleryItem> mGalleryItems;
            public OnNoteListener mOnNoteListener;

            public PhotoAdapter(List<GalleryItem> galleryItems, OnNoteListener onNoteListener) {
                mGalleryItems = galleryItems;
                this.mOnNoteListener = onNoteListener;
            }

            @Override
            public PhotoHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(getActivity());
                View view = inflater.inflate(R.layout.list_item_gallery, viewGroup, false);
                return new PhotoHolder(view, mOnNoteListener);
            }

            @Override
            public void onBindViewHolder(PhotoHolder photoHolder, int position) {
                GalleryItem galleryItem = mGalleryItems.get(position);
                Drawable placeholder = getResources().getDrawable(R.drawable.loading);
                photoHolder.bindDrawable(placeholder);
                mThumbnailDownloader.queueThumbnail(photoHolder, galleryItem.getUrl());
            }

            @Override
            public int getItemCount() {
                return mGalleryItems.size();
            }


        }

}
