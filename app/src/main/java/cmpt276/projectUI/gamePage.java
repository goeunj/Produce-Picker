package cmpt276.projectUI;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import cmpt276.project.R;

public class gamePage extends AppCompatActivity {
    int[] foods = {1,4,6};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);
        populateCard();

        //Create card to be first on discard pile
        //create card to be on top of draw pile
        //flip top card
        //user finds match
        //move card to discard pile
        //create new card
        //ect
        //win screen

    }
    private void populateCard() {
        TableLayout table = findViewById(R.id.tblCard);
        TableRow tableRow = new TableRow(this);
        tableRow.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.MATCH_PARENT,
                1.0f));
        table.addView(tableRow);
        for (int i = 0; i < 3; i++) {
            Button button = new Button(this);
            button.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.MATCH_PARENT,
                    1.0f));
            button.setPadding(0, 0, 0, 0);
            int newWidth = 100;
            int newHeight = 100;

            if (foods[i] == 0){
                Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.apple);
                Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
                Resources resource = getResources();
                button.setBackground(new BitmapDrawable(resource, scaledBitmap));
            }
            if (foods[i] == 1){
                Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.banana);
                Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
                Resources resource = getResources();
                button.setBackground(new BitmapDrawable(resource, scaledBitmap));
            }
            if (foods[i] == 2){
                Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cherry);
                Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
                Resources resource = getResources();
                button.setBackground(new BitmapDrawable(resource, scaledBitmap));
            }
            if (foods[i] == 3){
                Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.orange);
                Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
                Resources resource = getResources();
                button.setBackground(new BitmapDrawable(resource, scaledBitmap));
            }
            if (foods[i] == 4){
                Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.peach);
                Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
                Resources resource = getResources();
                button.setBackground(new BitmapDrawable(resource, scaledBitmap));
            }
            if (foods[i] == 5){
                Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.strawberry);
                Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
                Resources resource = getResources();
                button.setBackground(new BitmapDrawable(resource, scaledBitmap));
            }
            if (foods[i] == 6){
                Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.watermelon);
                Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
                Resources resource = getResources();
                button.setBackground(new BitmapDrawable(resource, scaledBitmap));
            }
            tableRow.addView(button);
        }
    }

}
