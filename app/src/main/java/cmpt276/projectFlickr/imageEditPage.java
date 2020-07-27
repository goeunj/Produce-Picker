package cmpt276.projectFlickr;

import androidx.fragment.app.Fragment;

/*
    Launches the fragment for editing the image list
 */

public class imageEditPage extends SingleFragmentActivity {

    protected Fragment createFragment() {
        return imageEditFragment.newInstance();
    }


}