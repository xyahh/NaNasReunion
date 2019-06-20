package yjj.nanasreunion.Components.State;

import android.view.KeyEvent;
import android.view.MotionEvent;

import yjj.nanasreunion.Objects.Pawn;
import yjj.nanasreunion.Vector2f;

public class MultiJumpState extends State
{
    /*
    the total amount of jumps before exiting state.
    zero means infinite jump counts (external program must pop state!)
     */
    private int m_TotalJumps;
    private int m_CurrentJumpCount;

    public MultiJumpState(int TotalJumpCount)
    {
        super(STATE_ID.MULTI_JUMP);
        m_TotalJumps = TotalJumpCount;

    }

    public State  CreateState() { return new MultiJumpState(m_TotalJumps); }


    @Override
    public void Enter(Pawn pawn)
    {
        m_CurrentJumpCount = 0;
    }

    @Override
    public void Exit(Pawn pawn) {

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
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            if(m_CurrentJumpCount++ < m_TotalJumps && m_TotalJumps > 0)
            {
                Vector2f v = pawn.physics.GetVelocity();
                v.y = 0.f;
                pawn.physics.SetVelocity(v);
                pawn.physics.ApplyImpulse(new Vector2f(0.f, pawn.JumpImpulse));
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean OnKeyDown(Pawn pawn, int keyCode, KeyEvent event) {
        return false;
    }
}
