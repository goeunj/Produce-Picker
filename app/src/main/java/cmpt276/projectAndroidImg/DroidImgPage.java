package cmpt276.projectAndroidImg;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import cmpt276.project.R;

import static cmpt276.projectAndroidImg.GalleryArray.myGalleryImgs;

public class DroidImgPage extends AppCompatActivity {

    private static final int GALLERY_REQUEST_CODE = 123;

    ImageView imgLoad;
    Button btnPick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_droid_img_page);

        imgLoad = findViewById(R.id.imgLoader);
        btnPick = findViewById(R.id.btnPicker);

        btnPick.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        "Pick an image"), GALLERY_REQUEST_CODE);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        System.out.println("++data" + data.getClipData().getItemCount());// Get count of image here.
        int size = data.getClipData().getItemCount();
        int i;
        Bitmap bitmap = null;
        for (i = 0; i < size; ++i) {
            Uri selectedImage = data.getClipData().getItemAt(i).getUri();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
            myGalleryImgs.add(bitmap);
        }
    }
}