package yjj.nanasreunion.Components.State;

import android.view.KeyEvent;
import android.view.MotionEvent;

import yjj.nanasreunion.Components.Graphics.SpriteGraphics;
import yjj.nanasreunion.Objects.Pawn;

public class FrozenState extends State
{
    public FrozenState() {
        super(STATE_ID.FROZEN);
    }

    float m_FPS;
    @Override
    public void Enter(Pawn pawn)
    {
    }

    public State  CreateState() { return new FrozenState(); }

    @Override
    public void Exit(Pawn pawn)
    {
    }

    @Override
    public void Update(Pawn pawn, float DeltaTime)
    {

    }

    @Override
    public boolean OnTouchEvent(Pawn pawn, MotionEvent event) {
        return false;
    }

    @Override
    public boolean OnKeyDown(Pawn pawn, int keyCode, KeyEvent event) {
        return false;
    }
}
