package cmpt276.projectUI;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import cmpt276.project.R;
import cmpt276.projectLogic.optionManager;

/**
 * user can choose to have fruit or vegetable theme, order, pile size
 */

public class optionPage extends AppCompatActivity {
    private optionManager manager = optionManager.getInstance();
    RadioGroup options, order, pileSize, difficulty, downloads;
    RadioButton fruitButton, vegeButton, fruitImgTxt, vegImgTxt, flickrbutton, deviceButton;
    RadioButton easy, medium, hard;
    RadioButton order2, order3, order5, size5, size10, size15, size20, sizeAll;
    RadioButton download;
    int themeSelected, orderSelected, levelSelected, sizeSelected, sizeCompare, orderCompare, dlSelected;
    private optionManager compareManager = optionManager.getInstance();
    boolean notValid = true;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_page);

        setOptionValue();
        setBackButton();
        verifyPermission();
    }

    private void setBackButton() {
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(optionPage.this, menuPage.class));
            }
        });
    }

    private void setOptionValue() {
        Button setOption = findViewById(R.id.setButton);

        setOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                options = findViewById(R.id.options);
                fruitButton = findViewById(R.id.fruitButton);
                vegeButton = findViewById(R.id.vegeButton);
                fruitImgTxt = findViewById(R.id.fruitImgTxt);
                vegImgTxt = findViewById(R.id.vegImgTxt);
                flickrbutton = findViewById(R.id.flickrButton);
                deviceButton = findViewById(R.id.deviceButton);
                themeSelected = options.getCheckedRadioButtonId();

                order = findViewById(R.id.order);
                order2 = findViewById(R.id.order2);
                order3 = findViewById(R.id.order3);
                order5= findViewById(R.id.order5);
                orderSelected = order.getCheckedRadioButtonId();

                difficulty = findViewById(R.id.difficulty);
                easy = findViewById(R.id.easy);
                medium = findViewById(R.id.medium);
                hard = findViewById(R.id.hard);
                levelSelected = difficulty.getCheckedRadioButtonId();

                pileSize = findViewById(R.id.pileSize);
                size5 = findViewById(R.id.size5);
                size10 = findViewById(R.id.size10);
                size15 = findViewById(R.id.size15);
                size20 = findViewById(R.id.size20);
                sizeAll = findViewById(R.id.sizeAll);
                sizeSelected = pileSize.getCheckedRadioButtonId();

                downloads = findViewById(R.id.downloads);
                download = findViewById(R.id.dlButton);
                dlSelected = downloads.getCheckedRadioButtonId();


                if(themeSelected != -1 && orderSelected != -1 && levelSelected != -1 && sizeSelected != -1){
                    manager.setMyOption(themeSelected, fruitImgTxt, vegImgTxt, fruitButton, vegeButton, flickrbutton, deviceButton);
                    manager.setMyOrder(orderSelected);
                    manager.setMyLevel(levelSelected);
                    manager.setMySize(sizeSelected);

                    if(dlSelected != -1){
                        manager.setMyDownload(true);
                    }

                    orderCompare = compareManager.getUserOrder(0);
                    sizeCompare = compareManager.getUserSize(0);

                    if(orderCompare == 2 && (sizeCompare == 5 || sizeCompare == 7)){
                        notValid = false;
                        startActivity(new Intent(optionPage.this, menuPage.class));
                    }
                    if(orderCompare == 3 && (sizeCompare == 5 || sizeCompare == 10 || sizeCompare == 13)){
                        notValid = false;
                        startActivity(new Intent(optionPage.this, menuPage.class));
                    }
                    if(orderCompare == 5 && (sizeCompare == 5 || sizeCompare == 10 || sizeCompare == 15 || sizeCompare == 20 || sizeCompare == 31)){
                        notValid = false;
                        startActivity(new Intent(optionPage.this, menuPage.class));
                    }
                    if(notValid) {
                        Toast.makeText(getApplicationContext(), getString(R.string.sizeWarning), Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), getString(R.string.optionMessage), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void verifyPermission(){
        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(), permissions[0]) == PackageManager.PERMISSION_GRANTED){
        }
        else{
            ActivityCompat.requestPermissions(optionPage.this, permissions, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        verifyPermission();
    }
}
