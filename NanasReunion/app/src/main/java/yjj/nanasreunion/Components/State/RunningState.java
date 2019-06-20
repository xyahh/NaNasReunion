package yjj.nanasreunion.Components.State;

import android.view.KeyEvent;
import android.view.MotionEvent;

import yjj.nanasreunion.Objects.Pawn;
import yjj.nanasreunion.Services.Timer;
import yjj.nanasreunion.Vector2f;

public class RunningState extends State
{
    public RunningState()
    {
        super(STATE_ID.RUNNING);
    }

    public State  CreateState() { return new RunningState(); }

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
        pawn.physics.ApplyForce(new Vector2f(pawn.RunningForce, 0.f));
        if(pawn.position.y > 0.1f)
        {
            pawn.PushState(pawn.JumpingState.CreateState());
        }

    }

    @Override
    public boolean OnTouchEvent(Pawn pawn, MotionEvent event)
    {
        if(event.getAction() == MotionEvent.ACTION_DOWN && pawn.position.y <= 0.1f)
        {
            pawn.physics.ApplyImpulse(new Vector2f(0.f, pawn.JumpImpulse));
            return true;
        }
        return false;
    }

    @Override
    public boolean OnKeyDown(Pawn pawn, int keyCode, KeyEvent event) {
        return false;
    }
}

