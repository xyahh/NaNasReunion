package yjj.nanasreunion.Objects;

import android.view.KeyEvent;
import android.view.MotionEvent;

import yjj.nanasreunion.Objects.Components.State.*;
import yjj.nanasreunion.MyStack;

public class Pawn extends Actor
{
    private    MyStack<State> states;

    public Pawn()
    {
        super();
        states = new MyStack<>();
        PushState(new NullState());
    }


    public void PushState(State state)
    {
        states.push(state);
        states.top().Enter(this);
    }

    public void PopState()
    {
        if(states.size() > 1) //always have NullState. Never empty.
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
        return states.top().OnTouchEvent(this, event);
    }

    public boolean OnKeyDown(int keyCode, KeyEvent event)
    {
        return states.top().OnKeyDown(this, keyCode, event);
    }
}
