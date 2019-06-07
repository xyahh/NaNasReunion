package yjj.nanasreunion.Services;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import yjj.nanasreunion.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void List(View v)
    {
        setContentView(R.layout.test_layout2);
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

    public void BackToGame(View v)
    {
        setContentView(R.layout.activity_main);
        Timer.SetTimeDilation(1.f);
    }

}
