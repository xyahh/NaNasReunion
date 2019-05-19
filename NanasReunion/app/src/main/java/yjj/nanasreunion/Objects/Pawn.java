package yjj.nanasreunion.Objects;

import android.view.KeyEvent;
import android.view.MotionEvent;

import yjj.nanasreunion.Objects.Components.State.*;
import yjj.nanasreunion.MyStack;

public class Pawn extends Actor
{
    public    MyStack<State> states;

    public Pawn()
    {
        super();
        states      = new MyStack<>();
        PushState(new NullState());
    }


    public void PushState(State state)
    {
        states.push(state);
        states.top().Enter(this);
    }

    public void PopState()
    {
        states.pop().Exit(this);
    }

    public void ChangeState(State state)
    {
        PopState();
        PushState(state);
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
