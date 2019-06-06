package yjj.nanasreunion.Services;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ServiceHub.Inst().InitServices(this);
        setContentView(ServiceHub.Inst().GetGameView());
    }
}
