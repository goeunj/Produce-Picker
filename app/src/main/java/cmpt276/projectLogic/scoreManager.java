package cmpt276.projectLogic;

import java.util.ArrayList;

/**
 * store a collection of scores
 * support setting existing scores with new score values
 * removes duplicates
 *
 * singleton score manager
 */

public class scoreManager {
    //myScore stores a collection of scores
    private ArrayList<score> myScore;
    private static scoreManager scoreList;

    scoreManager(){
        myScore = new ArrayList<>();
    }

    public static scoreManager getInstance(){
        if (scoreList == null){
            scoreList = new scoreManager();
        }
        return scoreList;
    }

    public ArrayList<score> getMyScore() {
        return this.myScore;
    }

    public String getScore(int i) {
        return this.myScore.get(i).getScore();
    }

    //sets new score
    public void setNewScore(String nickname, String score, String date){
        int i=5;
        while (i > 0){
            if (Integer.parseInt(myScore.get(i).getScore()) >Integer.parseInt(score) && Integer.parseInt(myScore.get(i-1).getScore()) < Integer.parseInt(score)){
                myScore.set(i, new score(nickname, score, date));
                break;
            }
            i--;
        }
    }

    public void removeDuplicates(){
        myScore.remove(9);
        myScore.remove(8);
        myScore.remove(7);
        myScore.remove(6);
        myScore.remove(5);
    }
}
