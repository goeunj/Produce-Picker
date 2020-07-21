package cmpt276.projectLogic;

import android.widget.RadioButton;
import cmpt276.project.R;

/**
 * default option is fruits
 * sets user option to radio button clicked
 * gets user choice when needed
 */

public class optionManager {
    private String userTheme;
    private static optionManager chosenOption;

    private optionManager(){
        userTheme = "FRUITS";
    }

    public static optionManager getInstance(){
        if (chosenOption == null){
            chosenOption = new optionManager();
        }
        return chosenOption;
    }

    public void setMyOption(RadioButton chosenTheme, RadioButton fruitImgTxt, RadioButton vegImgTxt, RadioButton fruitButton, RadioButton vegeButton){
        switch (chosenTheme.getId()) {
            case R.id.fruitButton:
                userTheme = String.valueOf(fruitButton.getText());
                break;
            case R.id.vegeButton:
                userTheme = String.valueOf(vegeButton.getText());
                break;
            case R.id.fruitImgTxt:
                userTheme = String.valueOf(fruitImgTxt.getText());
                break;
            case R.id.vegImgTxt:
                userTheme = String.valueOf(vegImgTxt.getText());
                break;
        }
    }

    public String getMyOption(){
        return this.userTheme;
    }
}
