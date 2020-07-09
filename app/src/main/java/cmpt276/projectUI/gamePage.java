package cmpt276.projectUI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import cmpt276.projectLogic.GameLogic;

import cmpt276.project.R;

import static android.widget.Toast.LENGTH_SHORT;
import static java.lang.Integer.parseInt;

public class gamePage extends AppCompatActivity {
    int[] myCard;
    int[] discard;
    int[] cards = {0,1,2,3,4,5,6};
    String[] Fruit = {"apple", "banana", "cherry", "orange", "peach", "strawberry", "watermelon"};
    String[] Veg = {"broccoli", "carrot", "lettuce", "mushroom", "onion", "pepper", "potato"};
    String[] Chosen;
    int count = 0;
    //MAKE two arrays, depending on the numbers of the card and the game mode, it accesses the array of images
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);
        cards = new int[]{0, 1, 2, 3, 4, 5, 6};
        Chosen = Veg;
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

    private void drawCard(int[] cards){
        int cardNum = GameLogic.nextCard(cards);
        myCard  = GameLogic.getCard(cardNum);
    }

    private void populateCard(String position) {
        TableLayout table = null;
        if (position == "Discard"){
            table = findViewById(R.id.tblDiscard);
        }
        else if (position == "Draw") {
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

    private void buttonClicked(int num) {
        if (GameLogic.isThereAMatch(num, discard)){
            count++;
            if (count == 6){
                Toast.makeText(getApplicationContext(), "YOU WIN!", Toast.LENGTH_LONG).show();
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


}
