package cmpt276.projectDeviceImgs;

import android.content.res.Resources;
import android.graphics.Bitmap;
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

import java.io.IOException;

import cmpt276.project.R;

import static cmpt276.projectDeviceImgs.GalleryArray.deviceImgs;
/*
    This will load the table with the images picked from the gallery.
    if there are no images the edit doesn't open and tells the user to pick images.
    click on an image to clear it from your list.
*/
@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
public class deviceEditPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_edit_page);
        if (deviceImgs.size() == 0){
            Toast.makeText(getApplicationContext(), "No images in Device set", Toast.LENGTH_LONG).show();
            deviceEditPage.this.finish();
        }
        try {
            popDeviceImgs();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setTableRow(TableRow tableRow){
        tableRow.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.MATCH_PARENT,
                1.0f));
    }

    private void popDeviceImgs() throws IOException {
        TableLayout table = findViewById(R.id.tblDevice1);;
        Bitmap bitmap;

        TableRow tableRow = new TableRow(this);
        setTableRow(tableRow);
        TableRow tableRow2 = new TableRow(this);
        setTableRow(tableRow2);
        TableRow tableRow3 = new TableRow(this);
        setTableRow(tableRow3);
        TableRow tableRow4 = new TableRow(this);
        setTableRow(tableRow4);
        TableRow tableRow5 = new TableRow(this);
        setTableRow(tableRow5);
        TableRow tableRow6 = new TableRow(this);
        setTableRow(tableRow6);
        TableRow tableRow7 = new TableRow(this);
        setTableRow(tableRow7);

        assert table != null;
        table.addView(tableRow);
        table.addView(tableRow2);
        table.addView(tableRow3);
        table.addView(tableRow4);
        table.addView(tableRow5);
        table.addView(tableRow6);
        table.addView(tableRow7);

        for (int i = 0; i < deviceImgs.size(); i++) {

            final Button button = new Button(this);
            button.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.MATCH_PARENT,
                    1.0f));
            button.setPadding(0, 0, 0, 0);


            Bitmap originalBitmap = deviceImgs.get(i);
            bitmap = Bitmap.createScaledBitmap(originalBitmap, 400, 400, true);
            Resources resource = getResources();
            button.setBackground(new BitmapDrawable(resource, bitmap));
            button.setTag(i);
            button.setOnClickListener(new View.OnClickListener(){
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(View v) {
                    int position  = (int) button.getTag();
                    Bitmap bitmap = deviceImgs.get(position);
                    deviceImgs.remove(bitmap);
                    deviceEditPage.this.finish();
                    startActivity(deviceEditPage.this.getIntent());

                }
            });
            if (i<5){
                tableRow.addView(button);
            }
            else if(i<10){
                tableRow2.addView(button);
            }
            else if(i<15){
                tableRow3.addView(button);
            }
            else if(i<20){
                tableRow4.addView(button);
            }
            else if(i<25){
                tableRow5.addView(button);
            }
            else if(i<30){
                tableRow6.addView(button);
            }
            else if(i<35){
                tableRow7.addView(button);
            }
            else {
                tableRow.addView(button);
            }
        }
    }
}