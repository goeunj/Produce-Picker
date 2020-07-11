package cmpt276.projectUI;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

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

        setOptionValue();
        setBackButton();
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
                themeSelected = options.getCheckedRadioButtonId();

                if(themeSelected == -1){
                    Toast.makeText(getApplicationContext(), getString(R.string.optionMessage), Toast.LENGTH_SHORT).show();
                }else{
                    chosenButton = findViewById(themeSelected);
                    manager.setMyOption(chosenButton, fruitButton, vegeButton);

                    startActivity(new Intent(optionPage.this, menuPage.class));
                }
            }
        });
    }
}
