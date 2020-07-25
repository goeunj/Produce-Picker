package cmpt276.projectUI;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import cmpt276.project.R;
import cmpt276.projectFlickr.PhotoGalleryActivity;
import cmpt276.projectFlickr.imageEditPage;

/**
 * start button goes to game page
 * score button goes to score page
 * option button goes to option page
 * help button goes to help page
 */

public class menuPage extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);

        setButtons();
    }

    private void setButtons() {
        Animation bounce = AnimationUtils.loadAnimation(menuPage.this, R.anim.bounce);

        Button startButton = findViewById(R.id.startButton);
        Button scoreButton = findViewById(R.id.scoreButton);
        Button optionButton = findViewById(R.id.optionButton);
        Button helpButton = findViewById(R.id.helpButton);
        Button imagesButton = findViewById(R.id.imagesButton);
        Button infoButton = findViewById(R.id.infoButton);

        startButton.startAnimation(bounce);
        scoreButton.startAnimation(bounce);
        optionButton.startAnimation(bounce);
        helpButton.startAnimation(bounce);
        imagesButton.startAnimation(bounce);
        infoButton.startAnimation(bounce);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(menuPage.this, gamePage.class));
            }
        });

        scoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(menuPage.this, scorePage.class));
            }
        });

        optionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(menuPage.this, optionPage.class));
            }
        });

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(menuPage.this, helpPage.class));
            }
        });

        imagesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(menuPage.this, PhotoGalleryActivity.class));
            }
        });

        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(menuPage.this, imageEditPage.class));
            }
        });
    }
}
