package yjj.nanasreunion.Services;

import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.Random;

import yjj.nanasreunion.R;
import yjj.nanasreunion.Vector2i;

public class MainActivity extends AppCompatActivity {

    View gameplay_layout;
    View gameover_layout;
    TextView score_textview;
    TextView item_textview;
    boolean IsGameplay;

    public static MediaPlayer GameplayBGM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.main_menu);

        Point size = new Point();
        getWindowManager().getDefaultDisplay().getRealSize(size);
        ServiceHub.Inst().SetScreenSize(new Vector2i(size.x, size.y));

        GameplayBGM = MediaPlayer.create(this, R.raw.bgm);
        GameplayBGM.setLooping(true);

        IsGameplay = false;

        Random rand = new Random();
        Handler handler = new Handler();

        Runnable r=new Runnable() {
            public void run() {
                if(ServiceHub.LosingCondition && !ServiceHub.AlreadyLost)
                {
                    GameOver();
                    ServiceHub.AlreadyLost = true;
                }
                handler.postDelayed(this, 500);
            }
        };

        handler.postDelayed(r, 500);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_IMMERSIVE
                            // Set the content to appear under the system bars so that the
                            // content doesn't resize when the system bars hide and show.
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            // Hide the nav bar and status bar
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN);
        }
    }

    public View AddToView(int res)
    {
        // Fake empty container layout
        FrameLayout lContainerLayout = new FrameLayout(this);
        lContainerLayout.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
        View v = getLayoutInflater().inflate(res, null);
        lContainerLayout.addView(v);

        addContentView(lContainerLayout, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT) );
        return v;
    }

    public void GameOver()
    {
        Timer.SetTimeDilation(0.f);
        gameover_layout = AddToView(R.layout.game_over);
        GameplayBGM.pause();
        Button pause = (Button)findViewById(R.id.pause_button);
        pause.setText("MAIN MENU");
    }

    public void RemoveFromView(View v)
    {
        ((ViewGroup)v.getParent()).removeView(v);
    }


    public void Pause(View v)
    {
        Button pause = (Button)findViewById(v.getId());
        if(ServiceHub.AlreadyLost)
        {
            setContentView(R.layout.main_menu);
            ServiceHub.AlreadyLost = false;
            ServiceHub.LosingCondition = false;
            pause.setText("PAUSE");
            return;
        }
        if(Timer.TogglePause())
            pause.setText("RESUME");
        else
            pause.setText("PAUSE");
    }

    public void StartGame(View v)
    {
        IsGameplay = true;
        setContentView(R.layout.activity_main);
        gameplay_layout = AddToView(R.layout.gameplay_layout);

        item_textview = (TextView)findViewById(R.id.item_id);
        score_textview = (TextView)findViewById(R.id.ScoreTextView);
        ScoreAsyncUpdater runner = new ScoreAsyncUpdater();
        runner.execute(0);
    }


    private class ScoreAsyncUpdater extends AsyncTask<Integer, String, Integer>
    {
        @Override
        protected Integer doInBackground(Integer... integers)
        {
            int Score = 0;
            while(IsGameplay)
            {
                Score = ServiceHub.Inst().GetScore();
                publishProgress("Score: "+ Integer.toString(Score),
                        ServiceHub.ItemName);

            }
            return Score;
        }

        @Override
        protected void onProgressUpdate(String... values)
        {
            super.onProgressUpdate(values);
            score_textview.setText(values[0]);
            item_textview.setText(values[1]);

        }
    }
}
