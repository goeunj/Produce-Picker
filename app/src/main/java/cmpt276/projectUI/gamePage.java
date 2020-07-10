package cmpt276.projectUI;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import cmpt276.projectLogic.GameLogic;
import cmpt276.project.R;
import cmpt276.projectLogic.optionManager;

import static android.widget.Toast.LENGTH_SHORT;

/**
 *
 */

public class gamePage extends AppCompatActivity {
    private optionManager option = optionManager.getInstance();
    int[] myCard;
    int[] discard;
    int[] cards = {0,1,2,3,4,5,6};
    String[] Fruit = {"apple", "banana", "cherry", "orange", "peach", "strawberry", "watermelon"};
    String[] Veg = {"broccoli", "carrot", "lettuce", "mushroom", "onion", "pepper", "potato"};
    String[] Chosen;
    int count = 0;
    //MAKE two arrays, depending on the numbers of the card and the game mode, it accesses the array of images
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);
        getUserOption();
        cards = new int[]{0, 1, 2, 3, 4, 5, 6};
        count = 0;
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
        if (option.getMyOption().equals("FRUITS")){
            Chosen = Fruit;
        }else{
            Chosen = Veg;
        }
    }

    private void drawCard(int[] cards){
        int cardNum = GameLogic.nextCard(cards);
        myCard  = GameLogic.getCard(cardNum);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void populateCard(String position) {
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
            final Button button = new Button(this);
            button.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.MATCH_PARENT,
                    1.0f));
            button.setPadding(0, 0, 0, 0);
            int imgID = getResources().getIdentifier(Chosen[myCard[i]], "drawable", getPackageName());
            Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), imgID);
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, 100, 100, true);
            Resources resource = getResources();
            button.setBackground(new BitmapDrawable(resource, scaledBitmap));
            button.setTag(myCard[i]);

            button.setOnClickListener(new View.OnClickListener(){
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
                Toast.makeText(getApplicationContext(), "YOU WIN!", Toast.LENGTH_LONG).show();
                setWinMessage();
                return;
            }
            Toast.makeText(getApplicationContext(), "MATCH!", LENGTH_SHORT).show();
            populateCard("Discard");
            discard = myCard;
            drawCard(cards);
            populateCard("Draw");

        }
        else{
            Toast.makeText(getApplicationContext(), "No Match", LENGTH_SHORT).show();
        }
    }

    private void setLoseMessage(){
        final TextView loseMessage = findViewById(R.id.winMessage);
        Button backButton = findViewById(R.id.backToMenu);

        loseMessage.setText(R.string.loseMessage);
        loseMessage.setVisibility(View.VISIBLE);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loseMessage.setVisibility(View.INVISIBLE);
                startActivity(new Intent(gamePage.this, menuPage.class));
            }
        });
    }

    private void setWinMessage(){
//        adapter = new CustomAdapter(manager.getMyScore(), getApplicationContext(), manager);
        String date = new SimpleDateFormat("MM.dd.yyyy", Locale.getDefault()).format(new Date());

        final EditText nickname = findViewById(R.id.nickname);
        final Button addButton = findViewById(R.id.addButton);
        final Button cancelButton = findViewById(R.id.cancelButton);
        final TextView winMessage = findViewById(R.id.winMessage);

        TableLayout discardPile = findViewById(R.id.tblDiscard);
        TableLayout userPile = findViewById(R.id.tblDraw);

        discardPile.setVisibility(View.INVISIBLE);
        userPile.setVisibility(View.INVISIBLE);
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
                double time = 3.0;
                String name = nickname.getText().toString();
                String score = String.valueOf(time);

                if (name.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Nickname must be inputted", Toast.LENGTH_LONG).show();
                }else{
                    nickname.setVisibility(View.INVISIBLE);
                    addButton.setVisibility(View.INVISIBLE);
                    cancelButton.setVisibility(View.INVISIBLE);
                    winMessage.setVisibility(View.INVISIBLE);

                    Intent intent = new Intent(gamePage.this, scorePage.class);
                    intent.putExtra("nickname", name);
                    intent.putExtra("score", score);
                    intent.putExtra("date", date);
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
