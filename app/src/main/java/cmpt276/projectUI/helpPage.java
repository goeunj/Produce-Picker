package cmpt276.projectUI;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import cmpt276.music.song;
import cmpt276.project.R;

/**
 * Displays the rules of the game and describes how to play
 */

public class helpPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_page);

        setBackButton();
    }

    @Override
    protected void onResume(){
        super.onResume();
        startService(new Intent(helpPage.this, song.class).setAction("PLAY"));
    }

    @Override
    protected void onPause(){
        super.onPause();
        startService(new Intent(helpPage.this, song.class).setAction("PAUSE"));
    }

    private void setBackButton() {
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(helpPage.this, menuPage.class));
            }
        });
    }
}
