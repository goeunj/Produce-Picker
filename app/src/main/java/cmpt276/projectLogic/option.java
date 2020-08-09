package cmpt276.projectLogic;

/**
 * stores options = theme, order, size
 */

public class option {
    private String userTheme;
    private int userOrder;
    private String userLevel;
    private int userPileSize;
    private boolean userDownload;

    option(String theme, int order, String level, int size, boolean download){
        this.userTheme = theme;
        this.userOrder = order;
        this.userLevel = level;
        this.userPileSize = size;
        this.userDownload = download;
    }

    void setUserTheme(String theme){
        this.userTheme = theme;
    }
    String getUserTheme(){
        return this.userTheme;
    }

    void setUserOrder(int order){
        this.userOrder = order;
    }
    int getUserOrder(){
        return this.userOrder;
    }

    void setUserLevel(String level){
        this.userLevel = level;
    }
    String getUserLevel(){
        return this.userLevel;
    }

    void setUSerPileSize(int size){
        this.userPileSize = size;
    }
    int getUserPileSize(){
        return this.userPileSize;
    }

    void setUserDownload(boolean download){
        this.userDownload = download;
    }
    boolean getUserDownload(){
        return this.userDownload;
    }
}
