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
        if(pawn.graphics instanceof SpriteGraphics)
        {
            SpriteGraphics g = (SpriteGraphics) pawn.graphics;
            m_FPS = g.GetFPS();
            g.SetFPS(0);
        }
    }

    @Override
    public void Exit(Pawn pawn)
    {
        if(pawn.graphics instanceof SpriteGraphics)
        {
            SpriteGraphics g = (SpriteGraphics) pawn.graphics;
            g.SetFPS(m_FPS);
        }
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
