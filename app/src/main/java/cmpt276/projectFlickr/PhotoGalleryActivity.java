package cmpt276.projectFlickr;

import android.app.AlertDialog;
import android.content.DialogInterface;

import androidx.fragment.app.Fragment;

/*
    Launches the fragment for the Photo Gallery.
    Also has a condition if the user clicks the back button, just to see if they want to.
    This check was implemented because I (Jacob) kept accidentally clicking back, so I thought others would too.
 */
public class PhotoGalleryActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return PhotoGalleryFragment.newInstance();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("EXIT?")
                .setMessage("Do you want to exit? The images will be saved")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No", null).show();
    }

}
