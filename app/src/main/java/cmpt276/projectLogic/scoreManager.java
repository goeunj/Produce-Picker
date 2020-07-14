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
        if (Integer.parseInt(myScore.get(0).getScore()) >= Integer.parseInt(score)){
            score temp1 = myScore.get(0);
            score temp2 = myScore.get(1);
            score temp3 = myScore.get(2);
            score temp4 = myScore.get(3);
            myScore.set(1, temp1);
            myScore.set(2, temp2);
            myScore.set(3, temp3);
            myScore.set(4, temp4);
            myScore.set(0, new score(nickname, score, date));

        }else{
            int i=4;
            while (i > 0){
                if (Integer.parseInt(myScore.get(i).getScore()) >= Integer.parseInt(score) && Integer.parseInt(myScore.get(i-1).getScore()) < Integer.parseInt(score)){
                    score temp1 = myScore.get(1);
                    score temp2 = myScore.get(2);
                    score temp3 = myScore.get(3);

                    if(i==1){
                        myScore.set(i, new score(nickname, score, date));
                        myScore.set(2, temp1);
                        myScore.set(3, temp2);
                        myScore.set(4, temp3);
                    }
                    if(i==2){
                        myScore.set(i, new score(nickname, score, date));
                        myScore.set(3, temp2);
                        myScore.set(4, temp3);
                    }
                    if(i==3){
                        myScore.set(i, new score(nickname, score, date));
                        myScore.set(4, temp3);
                    }
                    if(i==4) {
                        myScore.set(i, new score(nickname, score, date));
                    }
                    break;
                }
                i--;
            }
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
