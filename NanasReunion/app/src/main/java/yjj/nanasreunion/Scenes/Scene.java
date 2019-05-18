package yjj.nanasreunion.Scenes;

import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;


public interface Scene {

    public void Init();

    public void Destroy();

    public void Update(float deltaTime);

    public void Render(Canvas canvas, float interp);

    public boolean OnKeyDown(int keyCode, KeyEvent event);

    public boolean OnTouchEvent(MotionEvent event);

    public void SurfaceChange(int width, int height);
}
