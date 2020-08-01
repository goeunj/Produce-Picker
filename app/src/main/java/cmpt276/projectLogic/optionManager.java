package cmpt276.projectLogic;

import android.widget.RadioButton;

import java.util.ArrayList;

import cmpt276.project.R;

/**
 * default option is fruits
 * sets user option to radio start_game clicked
 * gets user choice when needed
 */

public class optionManager {
    private ArrayList<option> userOption = new ArrayList<>();
    private static optionManager chosenOption;

    private optionManager(){
        userOption.add(0, new option("FRUITS", 2,"EASY",  7));
    }

    public static optionManager getInstance(){
        if (chosenOption == null){
            chosenOption = new optionManager();
        }
        return chosenOption;
    }

    public void setMyOption(int themeSelected, RadioButton fruitImgTxt, RadioButton vegImgTxt, RadioButton fruitButton, RadioButton vegeButton, RadioButton flickrButton){
        switch (themeSelected) {
            case R.id.fruitButton:
                userOption.get(0).setUserTheme(fruitButton.getText().toString());
                break;
            case R.id.vegeButton:
                userOption.get(0).setUserTheme(vegeButton.getText().toString());
                break;
            case R.id.fruitImgTxt:
                userOption.get(0).setUserTheme(fruitImgTxt.getText().toString());
                break;
            case R.id.vegImgTxt:
                userOption.get(0).setUserTheme(vegImgTxt.getText().toString());
                break;
            case R.id.flickrButton:
                userOption.get(0).setUserTheme(flickrButton.getText().toString());
                break;
        }
    }

    public void setMyOrder(int orderSelected){
        switch (orderSelected){
            case R.id.order2:
                userOption.get(0).setUserOrder(2);
                break;
            case R.id.order3:
                userOption.get(0).setUserOrder(3);
                break;
            case R.id.order5:
                userOption.get(0).setUserOrder(5);
                break;
        }
    }

    public void setMyLevel(int levelSelected){
        switch (levelSelected){
            case R.id.easy:
                userOption.get(0).setUserLevel("EASY");
                break;
            case R.id.medium:
                userOption.get(0).setUserLevel("MEDIUM");
                break;
            case R.id.hard:
                userOption.get(0).setUserLevel("HARD");
                break;
        }
    }

    public void setMySize(int sizeSelected){
        switch (sizeSelected){
            case R.id.size5:
                userOption.get(0).setUSerPileSize(5);
                break;
            case R.id.size10:
                userOption.get(0).setUSerPileSize(10);
                break;
            case R.id.size15:
                userOption.get(0).setUSerPileSize(15);
                break;
            case R.id.size20:
                userOption.get(0).setUSerPileSize(20);
                break;
            case R.id.sizeAll:
                if (userOption.get(0).getUserOrder()==2){
                    userOption.get(0).setUSerPileSize(7);
                }else if (userOption.get(0).getUserOrder()==3){
                    userOption.get(0).setUSerPileSize(13);
                }else if (userOption.get(0).getUserOrder()==5){
                    userOption.get(0).setUSerPileSize(31);
                }
                break;
        }
    }

    public String getUserTheme(int i){
        return this.userOption.get(i).getUserTheme();
    }

    public int getUserOrder(int i){
        return this.userOption.get(i).getUserOrder();
    }

    public String getUserLevel(int i){
        return this.userOption.get(i).getUserLevel();
    }

    public int getUserSize(int i){
        return this.userOption.get(i).getUserPileSize();
    }
}
