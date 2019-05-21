package yjj.nanasreunion.Objects.Components.State;

import android.view.KeyEvent;
import android.view.MotionEvent;

import yjj.nanasreunion.Objects.Pawn;

public interface State
{
    void Enter(Pawn pawn);

    void Exit(Pawn pawn);

    void Update(Pawn actor, float DeltaTime);

    boolean OnTouchEvent(Pawn pawn, MotionEvent event);

    boolean OnKeyDown(Pawn pawn, int keyCode, KeyEvent event);

}

