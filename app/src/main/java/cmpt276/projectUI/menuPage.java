package cmpt276.projectUI;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import cmpt276.project.R;

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

        startButton.startAnimation(bounce);
        scoreButton.startAnimation(bounce);
        optionButton.startAnimation(bounce);
        helpButton.startAnimation(bounce);

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
    }
}
