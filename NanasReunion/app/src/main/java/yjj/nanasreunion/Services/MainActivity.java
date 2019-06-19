package yjj.nanasreunion.Services;

import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import yjj.nanasreunion.R;

public class MainActivity extends AppCompatActivity {

    View item_layout;
    View gameplay_layout;
    TextView score_textview;
    boolean IsGameplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.main_menu);
        IsGameplay = false;


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

    public void RemoveFromView(View v)
    {
        ((ViewGroup)v.getParent()).removeView(v);
    }

    public void List(View v)
    {
        RemoveFromView(gameplay_layout);
        item_layout = AddToView(R.layout.item_layout);
        Timer.SetTimeDilation(0.f);
    }

    public void Pause(View v)
    {
        Button pause = (Button)findViewById(v.getId());
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

        score_textview = (TextView)findViewById(R.id.ScoreTextView);
        ScoreAsyncUpdater runner = new ScoreAsyncUpdater();
        runner.execute(0);
    }

    public void BackToGame(View v)
    {
        RemoveFromView(item_layout);
        gameplay_layout = AddToView(R.layout.gameplay_layout);
        Timer.SetTimeDilation(1.f);
    }

    private class ScoreAsyncUpdater extends AsyncTask<Integer, String, Integer>
    {
        int Score = 0;
        @Override
        protected Integer doInBackground(Integer... integers)
        {
            while(IsGameplay)
            {
                Score = ServiceHub.Inst().GetScore();
                publishProgress("Score: "+ Integer.toString(Score));
            }
            return Score;
        }

        @Override
        protected void onProgressUpdate(String... values)
        {
            super.onProgressUpdate(values);
            score_textview.setText(values[0]);

        }
    }
}
