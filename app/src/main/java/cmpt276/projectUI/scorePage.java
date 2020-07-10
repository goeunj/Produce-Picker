package cmpt276.projectUI;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import cmpt276.project.R;

/**
 * setList = shows list of top 5 scores
 * setBackButton = click goes to menuPage
 * setNewScore = sets new score in correct place
 * removeDuplicates = removes duplicate list
 * setResetButton = resets list with default values
 */

public class scorePage extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_page);

        setBackButton();
    }

    private void setBackButton() {
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(scorePage.this, menuPage.class));
            }
        });
    }

//    private void setList(){
//
//        Intent intent = getIntent();
//        if (intent.getExtras() != null && !resetButton.isPressed()){
//            setNewScore(intent);
//        }
//    }
//
//    private void setNewScore(Intent intent){
//        String nickname = intent.getStringExtra("nickname");
//        String score = intent.getStringExtra("score");
//        String date = intent.getStringExtra("date");
//
//        manager.setNewScore(nickname, score, date);
//        adapter.notifyDataSetChanged();
//    }

}
