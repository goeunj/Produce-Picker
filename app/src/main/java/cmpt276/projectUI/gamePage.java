package cmpt276.projectUI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import cmpt276.project.R;
import cmpt276.projectLogic.GameLogic;
import cmpt276.projectLogic.optionManager;

import static android.widget.Toast.LENGTH_SHORT;
import static cmpt276.projectFlickr.ImagesArray.myImages;

/**
 * populate correct cards according to user option
 * on click, if match = move card to discard pile
 * game ends when all user cards are on discard pile
 *
 * when all matches found, shows win message + ask for nickname
 *      time/score < 5th score shows on score list
 *      time/score > 5th score on score list, shows lose message/toast
 */

public class gamePage extends AppCompatActivity {
    private optionManager manager = optionManager.getInstance();
    Chronometer time;
    ArrayList<String[]> drawCardType;
    int score, count =0;
    int[] myCard, discard, cards;
    int cardCount = 7;           //UPDATE TO OPTIONS SINGLETON
    int gameOrder;
    int images;
    int numCards;
    String[] Image, ImgTxt, cardType;

    String[] Fruit = {"apple", "apricot", "banana", "blackberry", "blueberry", "cherry", "cranberry", "dragonfruit", "durian",
            "elderberry", "fig", "grape", "grapefruit", "guava", "kiwi", "kumquat", "lemon", "lime", "lychee", "mango", "papaya",
            "peach", "pear", "pineapple", "plum", "raspberry", "starfruit", "strawberry", "watermelon", "honeydew", "cantaloupe"};
    String[] FruitText = {"apple2", "apricot2", "banana2", "blackberry2", "blueberry2", "cherry2", "cranberry2", "dragonfruit2", "durian2",
            "elderberry2", "fig2", "grape2", "grapefruit2", "guava2", "kiwi2", "kumquat2", "lemon2", "lime2", "lychee2", "mango2", "papaya2",
            "peach2", "pear2", "pineapple2", "plum2", "raspberry2", "starfruit2", "strawberry2", "watermelon2", "cantaloupe2", "honeydew2"};

    String[] Veg = {"arugula", "asparagus", "bamboo", "bean", "blackbean",  "broccoli", "brusselsprout", "carrot", "cauliflower",
            "celery", "chickpea", "corn", "eggplant", "garlic", "ginger", "greenonion", "kale", "kidneybean", "lettuce", "mushroom",
            "okra", "onion", "parsnip", "pepper", "potato", "raddish", "tomato", "turnip", "yam", "zuchini", "leek"};
    String[] VegText = {"arugula2", "asparagus2", "bamboo2", "bean2", "blackbean2",  "broccoli2", "brusselsprout2", "carrot2", "cauliflower2",
            "celery2", "chickpea2", "corn2", "eggplant2", "garlic2", "ginger2", "greenonion2", "kale2", "kidneybean2", "lettuce2", "mushroom2",
            "okra2", "onion2", "parsnip2", "pepper2", "potato2", "raddish2", "tomato2", "turnip2", "yam2", "zuchini2", "leek2"};

    String[] flickr = new String[31];

    //MAKE two arrays, depending on the numbers of the card and the game mode, it accesses the array of images
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        drawCardType = new ArrayList<>();
        getUserTheme();
        gameOrder = manager.getUserOption().get(0).getUserOrder();
        numCards = manager.getUserOption().get(0).getUserPileSize();
        count = 0;

        if(gameOrder == 2) {
            cards = new int[]{0, 1, 2, 3, 4, 5, 6};
            images = 3;
        }
        if (gameOrder == 3) {
            cards = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
            images = 4;
        }
        if(gameOrder == 5){
            cards = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
            images = 6;
        }
        count = 0;
        if (manager.getUserOption().get(0).getUserTheme().equals("FLICKR")){
            if (myImages.size() < cards.length){
                Toast.makeText(getApplicationContext(), "Not enough images in Flickr set", Toast.LENGTH_LONG).show();
                finish();
                return;
            }
        }

        time = findViewById(R.id.time);
        time.setBase(SystemClock.elapsedRealtime());
        time.start();


        drawCard(cards);
        try {
            populateCard("Discard");
        } catch (IOException e) {
            e.printStackTrace();
        }
        discard = myCard;

        drawCard(cards);
        try {
            populateCard("Draw");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int element: cards){
            System.out.println(element);
        }
    }

    private void getUserTheme(){
        switch (manager.getUserOption().get(0).getUserTheme()) {
            case "FRUITS":
                Image = Fruit;
                cardType = Image;
                break;
            case "VEGGIES":
                Image = Veg;
                cardType = Image;
                break;
            case "FRUITS+TEXT":
                Image = Fruit;
                ImgTxt = FruitText;
                break;
            case "VEGGIES+TEXT":
                Image = Veg;
                ImgTxt = VegText;
                break;
            case "FLICKR":
                Image = flickr;
                cardType = Image;

        }
    }

    private void drawCard(int[] cards){
        int cardNum = GameLogic.nextCard(cards);
        myCard  = GameLogic.getCard(cardNum);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void populateCard(String position) throws IOException {
        int ImageCount = 0, TextCount = 0;
        TableLayout table = null;

        if (position.equals("Discard")){
            table = findViewById(R.id.tblDiscard);
        }
        else if (position.equals("Draw")) {
            table = findViewById(R.id.tblDraw);
        }

        if (table != null) {
            table.removeAllViews();
        }

        TableRow tableRow = new TableRow(this);
        tableRow.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.MATCH_PARENT,
                1.0f));
        assert table != null;
        table.addView(tableRow);

        for (int i = 0; i < images; i++) {

            if (manager.getUserOption().get(0).getUserTheme().equals("FRUITS+TEXT") || manager.getUserOption().get(0).getUserTheme().equals("VEGGIES+TEXT")){
                if (position.equals("Draw") || count == 0){
                    cardType = GameLogic.getImageOrText(Image, ImgTxt);
                    if (cardType == Image) {
                        ImageCount++;
                    } else if (cardType == ImgTxt) {
                        TextCount++;
                    }
                    if (ImageCount == images-1) {
                        cardType = ImgTxt;
                    } else if (TextCount == images-1) {
                        cardType = Image;
                    }
                    //keeps track of the cardType on draw card pile to make cardType on discard pile the same when user card is 'moved'
                    drawCardType.add(i, cardType);
                }else if (count > 0){
                    cardType = drawCardType.get(i);
                }
            }

            final Button button = new Button(this);

            button.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.MATCH_PARENT,
                    1.0f));
            button.setPadding(0, 0, 0, 0);
            Bitmap bitmap = null;

            if (manager.getUserOption().get(0).getUserTheme().equals("FLICKR")){
                InputStream input = new java.net.URL(myImages.get(myCard[i]).getUrl()).openStream();
                bitmap = BitmapFactory.decodeStream(input);
            }
            else {
                int imgID = getResources().getIdentifier(cardType[myCard[i]], "drawable", getPackageName());
                Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), imgID);
                bitmap = Bitmap.createScaledBitmap(originalBitmap, 100, 100, true);
            }
            Resources resource = getResources();
            button.setBackground(new BitmapDrawable(resource, bitmap));
            button.setTag(myCard[i]);

            button.setOnClickListener(new View.OnClickListener(){
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(View v) {

                    int num = (int) button.getTag();
                    try {
                        buttonClicked(num);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            tableRow.addView(button);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void buttonClicked(int num) throws IOException {
        if (GameLogic.isThereAMatch(num, discard)){
            count++;

            if (count == numCards-1){
                time.stop();
                setMessage();
                return;
            }
            Toast.makeText(getApplicationContext(), "MATCH!", LENGTH_SHORT).show();
            populateCard("Discard");
            discard = myCard;
            drawCard(cards);
            drawCardType.clear();
            populateCard("Draw");
        }
        else{
            Toast.makeText(getApplicationContext(), "No Match", LENGTH_SHORT).show();
        }
    }

    private void setMessage(){
        score = (int)(SystemClock.elapsedRealtime() - time.getBase());
        String date = new SimpleDateFormat("MM.dd.yyyy", Locale.getDefault()).format(new Date());

        final EditText nickname = findViewById(R.id.nickname);
        final Button addButton = findViewById(R.id.addButton);
        final Button cancelButton = findViewById(R.id.cancelButton);
        final TextView winMessage = findViewById(R.id.winMessage);
        final TextView txtDiscard = findViewById(R.id.txtDiscard);
        final TextView txtDraw = findViewById(R.id.txtDraw);

        TableLayout discardPile = findViewById(R.id.tblDiscard);
        TableLayout userPile = findViewById(R.id.tblDraw);

        discardPile.setVisibility(View.INVISIBLE);
        userPile.setVisibility(View.INVISIBLE);
        txtDiscard.setVisibility(View.INVISIBLE);
        txtDraw.setVisibility(View.INVISIBLE);

        nickname.setVisibility(View.VISIBLE);
        addButton.setVisibility(View.VISIBLE);
        cancelButton.setVisibility(View.VISIBLE);
        winMessage.setVisibility(View.VISIBLE);

        setAddButton(addButton, cancelButton, nickname, winMessage, date);
        setCancelButton(cancelButton, addButton, nickname, winMessage);
    }

    public void setAddButton(final Button addButton, final Button cancelButton, final EditText nickname, final TextView winMessage, final String date){
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nickname.getText().toString();

                if (name.isEmpty()) {
                    Toast.makeText(getApplicationContext(), R.string.nicknameMessage, Toast.LENGTH_LONG).show();
                }else{
                    nickname.setVisibility(View.INVISIBLE);
                    addButton.setVisibility(View.INVISIBLE);
                    cancelButton.setVisibility(View.INVISIBLE);
                    winMessage.setVisibility(View.INVISIBLE);

                    Intent intent = new Intent(gamePage.this, scorePage.class);

                    SharedPreferences preferences = getSharedPreferences("prefs", 0);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("Name", name);
                    editor.putInt("Score", score);
                    editor.putString("Date", date);
                    editor.apply();
                    scorePage.flag = true;
                    startActivity(intent);
                }
            }
        });
    }

    public void setCancelButton(final Button cancelButton, final Button addButton, final EditText nickname, final TextView winMessage){
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nickname.setVisibility(View.INVISIBLE);
                addButton.setVisibility(View.INVISIBLE);
                cancelButton.setVisibility(View.INVISIBLE);
                winMessage.setVisibility(View.INVISIBLE);
                startActivity(new Intent(gamePage.this, menuPage.class));
            }
        });
    }
}
