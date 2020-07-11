package cmpt276.projectLogic;

import java.util.Random;

/**
 * Class for the backend logic of the game. Functions to get random cards each game and to determine a match
 */
public class GameLogic {
    private static Random r = new Random();

    public GameLogic() {
    }

    public static boolean isThereAMatch(int selection, int[] discardArray){        //param1 = button selected; param2 = buttons on discard card

        for (int value : discardArray) {
            if (selection == value) {
                return true;                                          //there is a match
            }
        }
        return false;               //no match
    }

    private static int getRandom(int[] array){              //helper method for nextCard()

        int index = r.nextInt(array.length);
        return array[index];
    }

    public static int nextCard(int[] cardStillInDeck){             //pick a random index of a card //param = the 7 index array representing each card

        int drawCard = getRandom(cardStillInDeck);
        while(drawCard == 73){                            //if index has already been picked
            drawCard = getRandom((cardStillInDeck));
        }
        cardStillInDeck[drawCard] = 73;

        return drawCard;
    }

    public static int[] getCard(int cardNum){            //get card based off a random selection from nextCard

        int[] card;
        switch(cardNum){
            case 0:
                card = new int[]{0, 1, 4};
                break;
            case 1:
                card = new int[]{2, 3, 4};
                break;
            case 2:
                card = new int[]{0, 2, 5};
                break;
            case 3:
                card = new int[]{1, 3, 5};
                break;
            case 4:
                card = new int[]{0, 3, 6};
                break;
            case 5:
                card = new int[]{1, 2, 6};
                break;
            case 6:
                card = new int[]{4, 5, 6};
                break;
            default:
                throw new IllegalStateException("Not a valid card " + cardNum);
        }
        return card;
    }
}
