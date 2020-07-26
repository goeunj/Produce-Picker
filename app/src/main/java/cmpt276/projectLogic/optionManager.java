package cmpt276.projectLogic;

import android.widget.RadioButton;

import java.util.ArrayList;

import cmpt276.project.R;

/**
 * default option is fruits
 * sets user option to radio button clicked
 * gets user choice when needed
 */

public class optionManager {
    private ArrayList<option> userTheme = new ArrayList<>();
    private static optionManager chosenOption;

    private optionManager(){
        userTheme.add(0, new option("FRUITS", 2, 7));
    }

    public static optionManager getInstance(){
        if (chosenOption == null){
            chosenOption = new optionManager();
        }
        return chosenOption;
    }

    public void setMyOption(int themeSelected, RadioButton fruitImgTxt, RadioButton vegImgTxt, RadioButton fruitButton, RadioButton vegeButton){
        switch (themeSelected) {
            case R.id.fruitButton:
                userTheme.get(0).setUserTheme(fruitButton.getText().toString());
                break;
            case R.id.vegeButton:
                userTheme.get(0).setUserTheme(vegeButton.getText().toString());
                break;
            case R.id.fruitImgTxt:
                userTheme.get(0).setUserTheme(fruitImgTxt.getText().toString());
                break;
            case R.id.vegImgTxt:
                userTheme.get(0).setUserTheme(vegImgTxt.getText().toString());
                break;
        }
    }

    public void setMyOrder(int orderSelected){
        switch (orderSelected){
            case R.id.order2:
                userTheme.get(0).setUserOrder(2);
                break;
            case R.id.order3:
                userTheme.get(0).setUserOrder(3);
                break;
            case R.id.order5:
                userTheme.get(0).setUserOrder(5);
                break;
        }
    }

    public void setMySize(int sizeSelected){
        switch (sizeSelected){
            case R.id.size5:
                userTheme.get(0).setUSerPileSize(5);
                break;
            case R.id.size10:
                userTheme.get(0).setUSerPileSize(10);
                break;
            case R.id.size15:
                userTheme.get(0).setUSerPileSize(15);
                break;
            case R.id.size20:
                userTheme.get(0).setUSerPileSize(20);
                break;
            case R.id.sizeAll:
                if (userTheme.get(0).getUserOrder()==2){
                    userTheme.get(0).setUSerPileSize(7);
                }else if (userTheme.get(0).getUserOrder()==3){
                    userTheme.get(0).setUSerPileSize(13);
                }else if (userTheme.get(0).getUserOrder()==5){
                    userTheme.get(0).setUSerPileSize(31);
                }
                break;
        }
    }

    public ArrayList<option> getUserOption(){
        return this.userTheme;
    }
}
