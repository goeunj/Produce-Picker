package cmpt276.projectUI;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Objects;

import cmpt276.project.R;
import cmpt276.projectLogic.optionManager;

/**
 * user can choose to have fruit or vegetable theme
 */

public class optionPage extends AppCompatActivity {
    private optionManager manager = optionManager.getInstance();
    private RadioGroup options;
    private RadioButton chosenButton, fruitButton, vegeButton;
    int themeSelected;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_page);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(false);

        setOptionValue();
    }

    private void setOptionValue() {
        Button setOption = findViewById(R.id.setButton);

       setOption.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               options = findViewById(R.id.options);
               fruitButton = findViewById(R.id.fruitButton);
               vegeButton = findViewById(R.id.vegeButton);
               themeSelected = options.getCheckedRadioButtonId();

               chosenButton = findViewById(themeSelected);
               manager.setMyOption(chosenButton, fruitButton, vegeButton);

               startActivity(new Intent(optionPage.this, menuPage.class));
           }
       });
    }
}
