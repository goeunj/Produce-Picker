package cmpt276.projectLogic;

/**
 * store info about single score including:
 * a string of nicknames
 * score
 * date
 */
public class score {
    private String nickname;
    private String score;
    private String date;

    public score(String nickname, String score, String date){
        this.nickname = nickname;
        this.score = score;
        this.date = date;
    }

    public String getNickname(){
        return this.nickname;
    }
    public String getScore() {
        return this.score;
    }
    public String getDate(){
        return this.date;
    }
}