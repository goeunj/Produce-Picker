package cmpt276.projectUI;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import cmpt276.project.R;
import cmpt276.projectLogic.optionManager;

/**
 * user can choose to have fruit or vegetable theme, order, pile size
 */

public class optionPage extends AppCompatActivity {
    private optionManager manager = optionManager.getInstance();
    RadioGroup options, order, pileSize;
    RadioButton fruitButton, vegeButton, fruitImgTxt, vegImgTxt;
    RadioButton order2, order3, order5, size5, size10, size15, size20, sizeAll;
    int themeSelected, orderSelected, sizeSelected;

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
                fruitImgTxt = findViewById(R.id.fruitImgTxt);
                vegImgTxt = findViewById(R.id.vegImgTxt);
                flickrButton = findViewById(R.id.flickrButton);
                themeSelected = options.getCheckedRadioButtonId();

                order = findViewById(R.id.order);
                order2 = findViewById(R.id.order2);
                order3 = findViewById(R.id.order3);
                order5= findViewById(R.id.order5);
                orderSelected = order.getCheckedRadioButtonId();

                pileSize = findViewById(R.id.pileSize);
                size5 = findViewById(R.id.size5);
                size10 = findViewById(R.id.size10);
                size15 = findViewById(R.id.size15);
                size20 = findViewById(R.id.size20);
                sizeAll = findViewById(R.id.sizeAll);
                sizeSelected = pileSize.getCheckedRadioButtonId();

                if(themeSelected != -1 && orderSelected != -1 && sizeSelected != -1){
                    manager.setMyOption(themeSelected, fruitImgTxt, vegImgTxt, fruitButton, vegeButton);
                    manager.setMyOrder(orderSelected);
                    manager.setMySize(sizeSelected);

                    startActivity(new Intent(optionPage.this, menuPage.class));
                }else {
                    Toast.makeText(getApplicationContext(), getString(R.string.optionMessage), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
