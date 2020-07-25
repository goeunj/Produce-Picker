package cmpt276.projectUI;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import cmpt276.projectLogic.GameLogic;
import cmpt276.project.R;
import cmpt276.projectLogic.optionManager;

import static android.widget.Toast.LENGTH_SHORT;

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
    private optionManager option = optionManager.getInstance();
    Chronometer time;
    ArrayList<String[]> drawCardType;
    int score, count =0;
    int[] myCard, discard, cards;
    String[] Image, ImgTxt, cardType;

    String[] Fruit = {"apple", "apricot", "banana", "blackberry", "blueberry", "cherry", "cranberry", "dragonfruit", "durian",
            "elderberry", "fig", "grape", "grapefruit", "guava", "kiwi", "kumquat", "lemon", "lime", "lychee", "mango", "papaya",
            "peach", "pear", "pineapple", "plum", "raspberry", "starfruit", "strawberry", "watermelon"};
    String[] FruitText = {"apple2", "apricot2", "banana2", "blackberry2", "blueberry2", "cherry2", "cranberry2", "dragonfruit2", "durian2",
            "elderberry2", "fig2", "grape2", "grapefruit2", "guava2", "kiwi2", "kumquat2", "lemon2", "lime2", "lychee2", "mango2", "papaya2",
            "peach2", "pear2", "pineapple2", "plum2", "raspberry2", "starfruit2", "strawberry2", "watermelon2"};

    String[] Veg = {"arugula", "asparagus", "bamboo", "bean", "blackbean",  "broccoli", "brusselsprout", "carrot", "cauliflower",
            "celery", "chickpea", "corn", "eggplant", "garlic", "ginger", "greenonion", "kale", "kidneybean", "lettuce", "mushroom",
            "okra", "onion", "parsnip", "pepper", "potato", "raddish", "tomato", "turnip", "yam", "zuchini"};
    String[] VegText = {"arugula2", "asparagus2", "bamboo2", "bean2", "blackbean2",  "broccoli2", "brusselsprout2", "carrot2", "cauliflower2",
            "celery2", "chickpea2", "corn2", "eggplant2", "garlic2", "ginger2", "greenonion2", "kale2", "kidneybean2", "lettuce2", "mushroom2",
            "okra2", "onion2", "parsnip2", "pepper2", "potato2", "raddish2", "tomato2", "turnip2", "yam2", "zuchini2"};

    //MAKE two arrays, depending on the numbers of the card and the game mode, it accesses the array of images
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);

        drawCardType = new ArrayList<>();
        cards = new int[]{0, 1, 2, 3, 4, 5, 6};
        count = 0;

        time = findViewById(R.id.time);
        time.setBase(SystemClock.elapsedRealtime());
        time.start();

        getUserOption();

        drawCard(cards);
        populateCard("Discard");
        discard = myCard;

        drawCard(cards);
        populateCard("Draw");

        for (int element: cards){
            System.out.println(element);
        }
    }

    private void getUserOption(){
        switch (option.getUserTheme()) {
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
        }
    }

    private void drawCard(int[] cards){
        int cardNum = GameLogic.nextCard(cards);
        myCard  = GameLogic.getCard(cardNum);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void populateCard(String position) {
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

        for (int i = 0; i < 3; i++) {

            if (option.getUserTheme().equals("FRUITS+TEXT") || option.getUserTheme().equals("VEGGIES+TEXT")){
                if (position.equals("Draw") || count == 0){
                    cardType = GameLogic.getImageOrText(Image, ImgTxt);
                    if (cardType == Image) {
                        ImageCount++;
                    } else if (cardType == ImgTxt) {
                        TextCount++;
                    }
                    if (ImageCount == 2) {
                        cardType = ImgTxt;
                    } else if (TextCount == 2) {
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

            int imgID = getResources().getIdentifier(cardType[myCard[i]], "drawable", getPackageName());
            Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), imgID);
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, 100, 100, true);
            Resources resource = getResources();
            button.setBackground(new BitmapDrawable(resource, scaledBitmap));
            button.setTag(myCard[i]);

            button.setOnClickListener(new View.OnClickListener(){
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(View v) {

                    int num = (int) button.getTag();
                    buttonClicked(num);
                }
            });

            tableRow.addView(button);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void buttonClicked(int num) {
        if (GameLogic.isThereAMatch(num, discard)){
            count++;

            if (count == 6){
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
