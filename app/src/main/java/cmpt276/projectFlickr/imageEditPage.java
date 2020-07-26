package cmpt276.projectFlickr;

import androidx.fragment.app.Fragment;

public class imageEditPage extends SingleFragmentActivity {

    protected Fragment createFragment() {
        return imageEditFragment.newInstance();
    }
//    public static void refresh(){
//        finish();
//        startActivity(getIntent());
//    }
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_image_edit_page);
//        for (String s : myImages){
//            System.out.println(s);
//        }
//    }


}