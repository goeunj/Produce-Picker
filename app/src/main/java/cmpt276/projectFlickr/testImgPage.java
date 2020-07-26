package cmpt276.projectFlickr;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import cmpt276.project.R;

import static android.widget.Toast.LENGTH_SHORT;
import static cmpt276.projectFlickr.ImagesArray.myImages;

public class testImgPage extends AppCompatActivity {

    GalleryItem testImage = myImages.get(0);
    int imgID = Integer.parseInt(testImage.getId());

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_img_page);
        populateCard();
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void populateCard() {
        TableLayout table = findViewById(R.id.tblDiscard);

        TableRow tableRow = new TableRow(this);
        tableRow.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.MATCH_PARENT,
                1.0f));
        assert table != null;
        table.addView(tableRow);

        for (int i = 0; i < 3; i++) {

            final Button button = new Button(this);

            button.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.MATCH_PARENT,
                    1.0f));
            button.setPadding(0, 0, 0, 0);

            //int imgID = getResources().getIdentifier("apple", "drawable", getPackageName());
            Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), imgID);
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, 100, 100, true);
            Resources resource = getResources();
            button.setBackground(new BitmapDrawable(resource, scaledBitmap));


            button.setOnClickListener(new View.OnClickListener(){
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "Click!", LENGTH_SHORT).show();
                }
            });
            tableRow.addView(button);
        }
    }
}