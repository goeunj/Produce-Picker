package cmpt276.music;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.Objects;

import cmpt276.project.R;

/**
 * Music service to play background cmpt276.music
 */

public class song extends Service {
    MediaPlayer music;

    @Nullable
    @Override
    public android.os.IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        music = MediaPlayer.create(getApplicationContext(), R.raw.song);
        music.seekTo(3000);
        music.setLooping(true);
    }

    @SuppressLint("WrongConstant")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public int onStartCommand(Intent intent, int flag, int startId){
        if (Objects.equals(intent.getAction(), "PLAY")){
            music.start();
        }

        if (Objects.equals(intent.getAction(), "PAUSE")){
            music.pause();
        }

        if (Objects.equals(intent.getAction(), "STOP")){
            music.pause();
            music.seekTo(0);
        }
        return 1;
    }

    @Override
    public void onDestroy() {
        music.stop();
        music.release();
        music = null;
    }
}
