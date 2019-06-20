package yjj.nanasreunion.Components.State;

import android.view.KeyEvent;
import android.view.MotionEvent;

import yjj.nanasreunion.Objects.Pawn;

public class NullState extends State
{

    public NullState()
    {
        super(STATE_ID.NULL);
    }

    @Override
    public void Enter(Pawn pawn)
    {

    }

    @Override
    public void Exit(Pawn pawn)
    {

    }

    @Override
    public void Update(Pawn pawn, float DeltaTime)
    {

    }

    @Override
    public boolean OnTouchEvent(Pawn pawn, MotionEvent event)
    {
        return false;
    }

    @Override
    public boolean OnKeyDown(Pawn pawn, int keyCode, KeyEvent event)
    {
        return false;
    }

}
