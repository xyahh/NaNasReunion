package yjj.nanasreunion.Actors;

import android.view.KeyEvent;
import android.view.MotionEvent;

import yjj.nanasreunion.Actors.Components.State.*;
import yjj.nanasreunion.MyStack;

public class Pawn extends Actor
{
    public    MyStack<State> states;

    public Pawn()
    {
        super();
        states      = new MyStack<>();
        states.push(new NullState());
    }

    @Override
    public void Update(float DeltaTime)
    {
        super.Update(DeltaTime);
        states.top().Update(this, DeltaTime);
    }

    public boolean OnTouchEvent(MotionEvent event)
    {
        return false;
    }

    public boolean OnKeyDown(int keyCode, KeyEvent event)
    {
        return false;
    }
}
