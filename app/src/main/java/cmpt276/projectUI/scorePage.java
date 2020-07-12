package cmpt276.projectUI;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import cmpt276.project.R;
import cmpt276.projectLogic.customAdapter;
import cmpt276.projectLogic.score;
import cmpt276.projectLogic.scoreManager;

/**
 * setList = shows list of top 5 scores
 * setBackButton = click goes to menuPage
 * setNewScore = sets new score in correct place
 * removeDuplicates = removes duplicate list
 * setResetButton = resets list with default values
 */

public class scorePage extends AppCompatActivity {
    private scoreManager manager = scoreManager.getInstance();
    customAdapter adapter;
    ListView listView;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_page);

        setBackButton();
        setList();
        setResetButton();
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

    private void setList(){
        Button resetButton = findViewById(R.id.resetButton);
        listView = findViewById(R.id.listScore);

        adapter = new customAdapter(manager.getMyScore(), getApplicationContext(), manager);
        listView.setAdapter(adapter);

        manager.getMyScore().add(new score("player1", "2000", "07.11.2020"));
        manager.getMyScore().add(new score("player2", "30000", "07.11.2020"));
        manager.getMyScore().add(new score("player3", "400000", "07.11.2020"));
        manager.getMyScore().add(new score("player4", "5000000", "07.11.2020"));
        manager.getMyScore().add(new score("player5", "60000000", "07.11.2020"));

        Intent intent = getIntent();
        if (intent.getExtras() != null && !resetButton.isPressed()){
            setNewScore(intent);
        }

        if (manager.getMyScore().size() > 5){
            manager.removeDuplicates();
            adapter.notifyDataSetChanged();
        }
    }

    private void setNewScore(Intent intent){
        String nickname = intent.getStringExtra("nickname");
        String score = intent.getStringExtra("score");
        String date = intent.getStringExtra("date");

        manager.setNewScore(nickname, score, date);
        adapter.notifyDataSetChanged();
    }

    private void setResetButton(){
        final Button resetButton = findViewById(R.id.resetButton);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetButton.setPressed(true);
                listView.removeAllViewsInLayout();
                adapter.clear();
                setList();
            }
        });
    }
}
