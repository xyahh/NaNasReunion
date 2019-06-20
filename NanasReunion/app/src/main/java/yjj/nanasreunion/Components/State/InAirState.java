package yjj.nanasreunion.Components.State;

import android.view.KeyEvent;
import android.view.MotionEvent;

import yjj.nanasreunion.Objects.Pawn;
import yjj.nanasreunion.Vector2f;

public class InAirState extends State
{
    float m_Gravity;

    public InAirState() {
        super(STATE_ID.IN_AIR);
    }

    public State  CreateState() { return new InAirState(); }

    @Override
    public void Enter(Pawn pawn)
    {
        m_Gravity = pawn.physics.GetGravity();
    }

    @Override
    public void Exit(Pawn pawn)
    {
        pawn.physics.SetGravity(m_Gravity);
    }

    @Override
    public void Update(Pawn pawn, float DeltaTime)
    {
        pawn.physics.ApplyForce(new Vector2f(pawn.RunningForce, 0.f));
        if(pawn.position.y < 0.01f)
            pawn.PopState();
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
