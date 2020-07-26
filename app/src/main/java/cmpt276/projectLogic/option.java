package cmpt276.projectLogic;

/**
 * stores options = theme, order, size
 */

public class option {
    private String userTheme;
    private int userOrder;
    private int userPileSize;

    option(String theme, int order, int size){
        this.userTheme = theme;
        this.userOrder = order;
        this.userPileSize = size;
    }

    void setUserTheme(String theme){
        this.userTheme = theme;
    }
    public String getUserTheme(){
        return this.userTheme;
    }

    void setUserOrder(int order){
        this.userOrder = order;
    }
    public int getUserOrder(){
        return this.userOrder;
    }

    void setUSerPileSize(int size){
        this.userPileSize = size;
    }
    public int getUserPileSize(){
        return this.userPileSize;
    }
}
