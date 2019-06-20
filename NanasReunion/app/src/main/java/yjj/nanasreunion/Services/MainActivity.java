package yjj.nanasreunion.Services;

import android.content.pm.ActivityInfo;
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

public class MainActivity extends AppCompatActivity {

    View gameplay_layout;
    View gameover_layout;
    TextView score_textview;
    TextView item_textview;
    boolean IsGameplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.main_menu);
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
        Button pause = (Button)findViewById(R.id.pause_button);
        pause.setText("RETRY");
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
            Timer.SetTimeDilation(0.f);
            ServiceHub.Inst().GetCurrentScene().Init();

            RemoveFromView(gameover_layout);

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
