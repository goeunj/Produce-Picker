package cmpt276.projectUI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import cmpt276.music.song;
import cmpt276.project.R;
import cmpt276.projectDeviceImgs.deviceEditPage;
import cmpt276.projectFlickr.PhotoGalleryActivity;
import cmpt276.projectFlickr.imageEditPage;

import static cmpt276.projectDeviceImgs.GalleryArray.deviceImgs;

/**
 * start start_game goes to game page
 * score start_game goes to score page
 * option start_game goes to option page
 * help start_game goes to help page
 */

public class menuPage extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    private static final String DEVICE_IMAGES = "DEVICE_IMAGES";
    private static final String KEY = "DEVICE_KEY";
    private static final int GALLERY_REQUEST_CODE = 123;
    MediaPlayer buttonSound;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);

        setButtons();
        if (deviceImgs.size() != 0){
            setList(deviceImgs);
        }
        List<Bitmap> importArray = getList();
        if (importArray != null) {
            deviceImgs = importArray;
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        startService(new Intent(menuPage.this, song.class).setAction("STOP"));
    }

    private void setButtons() {
        Animation bounce = AnimationUtils.loadAnimation(menuPage.this, R.anim.bounce);

        Button startButton = findViewById(R.id.startButton);
        Button scoreButton = findViewById(R.id.scoreButton);
        Button optionButton = findViewById(R.id.optionButton);
        Button helpButton = findViewById(R.id.helpButton);
        Button imagesButton = findViewById(R.id.imagesButton);
        Button infoButton = findViewById(R.id.infoButton);
        Button androidButton = findViewById(R.id.androidButton);
        Button editButton = findViewById(R.id.editButton);

        startButton.startAnimation(bounce);
        scoreButton.startAnimation(bounce);
        optionButton.startAnimation(bounce);
        helpButton.startAnimation(bounce);
        imagesButton.startAnimation(bounce);
        infoButton.startAnimation(bounce);
        androidButton.startAnimation(bounce);
        editButton.startAnimation(bounce);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSound = MediaPlayer.create(menuPage.this, R.raw.start_game);
                buttonSound.start();
                startActivity(new Intent(menuPage.this, gamePage.class));
            }
        });

        scoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSound = MediaPlayer.create(menuPage.this, R.raw.start_game);
                buttonSound.start();
                startActivity(new Intent(menuPage.this, scorePage.class));
            }
        });

        optionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSound = MediaPlayer.create(menuPage.this, R.raw.start_game);
                buttonSound.start();
                startActivity(new Intent(menuPage.this, optionPage.class));
            }
        });

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSound = MediaPlayer.create(menuPage.this, R.raw.start_game);
                buttonSound.start();
                startActivity(new Intent(menuPage.this, helpPage.class));
            }
        });

        imagesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSound = MediaPlayer.create(menuPage.this, R.raw.start_game);
                buttonSound.start();
                startActivity(new Intent(menuPage.this, PhotoGalleryActivity.class));
            }
        });

        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSound = MediaPlayer.create(menuPage.this, R.raw.start_game);
                buttonSound.start();
                startActivity(new Intent(menuPage.this, imageEditPage.class));
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSound = MediaPlayer.create(menuPage.this, R.raw.start_game);
                buttonSound.start();
                startActivity(new Intent(menuPage.this, deviceEditPage.class));
            }
        });

        androidButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
            @Override
            public void onClick(View v) {
                buttonSound = MediaPlayer.create(menuPage.this, R.raw.start_game);
                buttonSound.start();
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        "Pick an image"), GALLERY_REQUEST_CODE);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED){
            Toast.makeText(getApplicationContext(), "Click DONE in top right corner to save images", Toast.LENGTH_LONG).show();
            return;
        }
        int size = data.getClipData().getItemCount();
        int i;
        Bitmap bitmap = null;
        for (i = 0; i < size; ++i) {
            Uri selectedImage = data.getClipData().getItemAt(i).getUri();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
            deviceImgs.add(bitmap);
        }
        setList(deviceImgs);
    }
    //Bitmap/String conversions modified from https://stackoverflow.com/questions/13562429/how-many-ways-to-convert-bitmap-to-string-and-vice-versa/18052269
    public List<String> BitMapToString(List<Bitmap> list){
        List<String> temp = null;
        for (Bitmap bitmap : list) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] b = baos.toByteArray();
            String strBitmap = Base64.encodeToString(b, Base64.DEFAULT);
            temp.add(strBitmap);
        }
        return temp;
    }
    public List<Bitmap> StringToBitMap(List<String> encodedList){
        List<Bitmap> list = null;
        for (int i = 0; i < encodedList.size(); i++) {
            try {
                byte[] encodeByte = Base64.decode(encodedList.get(i), Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
                list.add(bitmap);
            } catch (Exception e) {
                e.getMessage();
                return null;
            }
        }
        return list;
    }


    public <T> void setList(List<Bitmap> list) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> set = null;
        if (set != null) {
            set.addAll(BitMapToString(list));
        }
        editor.putStringSet(KEY, set);
        editor.apply();
    }

    public List<Bitmap> getList(){
        List<Bitmap> myList = null;
        List<String> tempList = null;
        sharedPreferences = getSharedPreferences(DEVICE_IMAGES, this.MODE_PRIVATE);
        Set<String> encodedSet = sharedPreferences.getStringSet(KEY, null);
        if (encodedSet == null){
            return null;
        }
        for (String i : encodedSet){
            assert tempList != null;
            tempList.add(i);
        }
        assert tempList != null;
        myList = StringToBitMap(tempList);
        return myList;
    }
}
