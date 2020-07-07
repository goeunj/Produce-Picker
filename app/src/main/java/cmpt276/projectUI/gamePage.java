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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
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
            button.setBackgroundResource(R.drawable.banana);
//            int newWidth = button.getWidth();
//            int newHeight = button.getHeight();
//            Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.banana);
//            Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
//            Resources resource = getResources();
//            button.setBackground(new BitmapDrawable(resource, scaledBitmap));

            tableRow.addView(button);
        }
    }
}
