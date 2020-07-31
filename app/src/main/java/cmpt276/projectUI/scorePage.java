package cmpt276.projectUI;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

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
 * uses flags to dodge duplicates
 */

public class scorePage extends AppCompatActivity {
    SharedPreferences sharedPref;
    private scoreManager manager = scoreManager.getInstance();
    customAdapter adapter;
    ListView listView;
    String nick, date;
    int score;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_page);

        setBackButton();
        setList();
        setResetButton();
    }

    private void saveScoreList(){
        sharedPref = getSharedPreferences("sharePref", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = gson.toJson(manager.getMyScore());
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("set", json);
        editor.apply();
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

    private void addNewScores(){
        manager.getMyScore().add(new score("Jonny", "5000", "07.11.2020"));
        manager.getMyScore().add(new score("David", "7000", "07.11.2020"));
        manager.getMyScore().add(new score("James", "10000", "07.11.2020"));
        manager.getMyScore().add(new score("George", "15000", "07.11.2020"));
        manager.getMyScore().add(new score("Brian", "20000", "07.11.2020"));
    }

    private void setList(){
        sharedPref = getSharedPreferences("sharePref", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPref.getString("set", "");
        listView = findViewById(R.id.listScore);

        adapter = new customAdapter(manager.getMyScore(), getApplicationContext(), manager);
        listView.setAdapter(adapter);

        addNewScores();

        if (!json.isEmpty()) {
            Type type = new TypeToken<List<score>>() {
            }.getType();
            List<score> scoreList = gson.fromJson(json, type);
            int i = 0;
            for (score data : scoreList) {
                manager.getMyScore().set(i, new score(data.getNickname(), data.getScore(), data.getDate()));
                i++;
            }
        }

        Intent intent = getIntent();
        nick = intent.getStringExtra("nickname");
        score = intent.getIntExtra("score", 0);
        date = intent.getStringExtra("date");

        if (nick != null && Integer.parseInt(manager.getMyScore().get(4).getScore()) < score){
            Toast.makeText(this, R.string.loseMessage, Toast.LENGTH_LONG).show();
        }else if (nick != null){
            manager.setNewScore(nick, String.valueOf(score), date);
            adapter.notifyDataSetChanged();
        }

        if (manager.getMyScore().size() > 5){
            manager.removeDuplicates();
            adapter.notifyDataSetChanged();
        }

        saveScoreList();
    }

    private void setResetButton(){
        final Button resetButton = findViewById(R.id.resetButton);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.removeAllViewsInLayout();
                adapter.clear();
                addNewScores();
                adapter.notifyDataSetChanged();

                saveScoreList();
            }
        });
    }
}
