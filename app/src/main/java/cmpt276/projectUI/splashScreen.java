package cmpt276.projectUI;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import cmpt276.project.R;

/**
 * shows title of game
 * shows the names of team members
 */

public class splashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Button skip = findViewById(R.id.btnSkip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), menuPage.class));
            }
        });

        TextView authorView = findViewById(R.id.txtAuthors);
        Animation authorAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.author_anim);
        TextView titleView = findViewById(R.id.txtTitle);
        Animation titleAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.title_anim);

        titleView.setAnimation(titleAnim);
        authorView.setAnimation(authorAnim);

        authorAnim.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        finish();
                        startActivity(new Intent(getApplicationContext(), menuPage.class));
                    }
                }, 2000);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }
}
