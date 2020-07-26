package cmpt276.projectLogic;

import java.util.Random;

/**
 * Class for the backend logic of the game
 * Functions to get random cards each game and to determine a match
 */
public class GameLogic {
    private static Random r = new Random();
    private static int numCardsInDraw;
    private static int gameOrder = optionManager.getInstance().getUserOption().get(0).getUserOrder();

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

        if(gameOrder != 2 && gameOrder != 3 && gameOrder != 5){
            throw new IllegalStateException("Not a valid order " + cardNum);
        }

        int[] card = new int[]{0, 1, 4};


        if(gameOrder == 2){
            switch(cardNum){
                case 0: card = new int[]{0, 1, 4}; break;
                case 1: card = new int[]{2, 3, 4}; break;
                case 2: card = new int[]{0, 2, 5}; break;
                case 3: card = new int[]{1, 3, 5}; break;
                case 4: card = new int[]{0, 3, 6}; break;
                case 5: card = new int[]{1, 2, 6}; break;
                case 6: card = new int[]{4, 5, 6}; break;
                default:
                    throw new IllegalStateException("Not a valid card " + cardNum);
            }
        }
        if(gameOrder == 3){
            switch(cardNum){
                case 0: card = new int[]{0, 1, 2, 9}; break;
                case 1: card = new int[]{9, 3, 4, 5}; break;
                case 2: card = new int[]{8, 9, 6, 7}; break;
                case 3: card = new int[]{0, 10, 3, 6}; break;
                case 4: card = new int[]{1, 10, 4, 7}; break;
                case 5: card = new int[]{8, 2, 10, 5}; break;
                case 6: card = new int[]{0, 8, 11, 4}; break;
                case 7: card = new int[]{1, 11, 5, 6}; break;
                case 8: card = new int[]{11, 2, 3, 7}; break;
                case 9: card = new int[]{0, 12, 5, 7}; break;
                case 10: card = new int[]{8, 1, 3, 12}; break;
                case 11: card = new int[]{12, 2, 4, 6}; break;
                case 12: card = new int[]{9, 10, 11, 12}; break;
            }
        }

        if(gameOrder == 5){
            switch(cardNum){
                case 0: card = new int[]{0, 1, 2, 3, 4, 25}; break;
                case 1: card = new int[]{5, 6, 7, 8, 9, 25}; break;
                case 2: card = new int[]{10, 11, 12, 13, 14, 25}; break;
                case 3: card = new int[]{15, 16, 17, 18, 19, 25}; break;
                case 4: card = new int[]{20, 21, 22, 23, 24, 25}; break;
                case 5: card = new int[]{0, 5, 10, 15, 20, 26}; break;
                case 6: card = new int[]{1, 6, 11, 16, 21, 26}; break;
                case 7: card = new int[]{2, 7, 12, 17, 22, 26}; break;
                case 8: card = new int[]{3, 8, 13, 18, 23, 26}; break;
                case 9: card = new int[]{4, 9, 14, 19, 24, 26}; break;
                case 10: card = new int[]{0, 6, 12, 18, 24, 27}; break;
                case 11: card = new int[]{1, 7, 13, 19, 20, 27}; break;
                case 12: card = new int[]{2, 8, 14, 15, 21, 27}; break;
                case 13: card = new int[]{3, 9, 10, 16, 22, 27}; break;
                case 14: card = new int[]{4, 5, 11, 17, 23, 27}; break;
                case 15: card = new int[]{0, 7, 14, 16, 23, 28}; break;
                case 16: card = new int[]{1, 8, 10, 17, 24, 28}; break;
                case 17: card = new int[]{2, 9, 11, 18, 20, 28}; break;
                case 18: card = new int[]{3, 5, 12, 19, 21, 28}; break;
                case 19: card = new int[]{4, 6, 13, 15, 22, 28}; break;
                case 20: card = new int[]{0, 8, 11, 19, 22, 29}; break;
                case 21: card = new int[]{1, 9, 12, 15, 23, 29}; break;
                case 22: card = new int[]{2, 5, 13, 16, 24, 29}; break;
                case 23: card = new int[]{3, 6, 14, 17, 20, 29}; break;
                case 24: card = new int[]{4, 7, 10, 18, 21, 29}; break;
                case 25: card = new int[]{0, 9, 13, 17, 21, 30}; break;
                case 26: card = new int[]{1, 5, 14, 18, 22, 30}; break;
                case 27: card = new int[]{2, 6, 10, 19, 23, 30}; break;
                case 28: card = new int[]{3, 7, 11, 15, 24, 30}; break;
                case 29: card = new int[]{4, 8, 12, 16, 20, 30}; break;
                case 30: card = new int[]{25, 26, 27, 28, 29, 30}; break;
            }
        }

        return card;
    }

    public static int isDrawSizePossible(int order, int drawSize){    //-1 == not possible; 1 == possible
        if(order == 2){
            if(drawSize != 5){
                return -1;
            }
            return 1;
        }
        if(order == 3){
            if(drawSize == 5 || drawSize == 10){
                return 1;
            }
            return -1;
        }
        if(order == 5){
            return -1;
        }
        return 0;
    }

    public static String[] getImageOrText(String[] Image, String[] ImgTxt){         //randomly chooses Image or ImgTxt
        Random r = new Random();
        int index = r.nextInt(2);

        if (index == 0) {
            return Image;
        } else{
            return ImgTxt;
        }
    }
}
