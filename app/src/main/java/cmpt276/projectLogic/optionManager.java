package cmpt276.projectLogic;

import android.widget.RadioButton;
import cmpt276.project.R;

/**
 * sets user option to radio button clicked
 * gets user choice when needed
 */

public class optionManager {
    private String myOption;
    private static optionManager chosenOption;

    private optionManager(){
        myOption = "";
    }

    public static optionManager getInstance(){
        if (chosenOption == null){
            chosenOption = new optionManager();
        }
        return chosenOption;
    }

    public void setMyOption(RadioButton chosenButton, RadioButton fruitButton, RadioButton vegeButton){
        switch (chosenButton.getId()) {
            case R.id.fruitButton:
                myOption = String.valueOf(fruitButton.getText());
                break;
            case R.id.vegeButton:
                myOption = String.valueOf(vegeButton.getText());
                break;
        }
    }

    public String getMyOption(){
        return this.myOption;
    }
}
