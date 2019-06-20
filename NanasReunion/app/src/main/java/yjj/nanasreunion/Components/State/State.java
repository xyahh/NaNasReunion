package yjj.nanasreunion.Components.State;

import android.view.KeyEvent;
import android.view.MotionEvent;

import yjj.nanasreunion.Objects.Pawn;

public abstract class State
{
    private STATE_ID m_ID = STATE_ID.NULL;

    protected State(STATE_ID id)
    {
        m_ID = id;
    }

    public STATE_ID GetID() {
        return m_ID;
    }

    abstract public void Enter(Pawn pawn);

    abstract public void Exit(Pawn pawn);

    abstract public void Update(Pawn pawn, float DeltaTime);

    abstract public boolean OnTouchEvent(Pawn pawn, MotionEvent event);

    abstract public boolean OnKeyDown(Pawn pawn, int keyCode, KeyEvent event);

}

