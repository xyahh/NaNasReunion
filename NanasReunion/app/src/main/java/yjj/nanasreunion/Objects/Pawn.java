package yjj.nanasreunion.Objects;

import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

import yjj.nanasreunion.Components.Camera;
import yjj.nanasreunion.Components.State.*;
import yjj.nanasreunion.MyStack;
import yjj.nanasreunion.Vector2f;

public class Pawn extends Actor
{
    private    MyStack<State> states;
    private     Camera         m_Camera;
    public Pawn()
    {
        super();
        states = new MyStack<>();
        PushState(new NullState());
    }

    public void SetCamera(Camera camera)
    {
        m_Camera = camera;
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

    @Override
    public void Draw(Canvas canvas, Camera camera, float interp) {
        super.Draw(canvas, camera, interp);
        physics.ApplyForce(new Vector2f(5.f, 0.f));
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
        physics.ApplyForce(new Vector2f(2.f, 0.f));
    }
boolean  f = false;
    public boolean OnTouchEvent(MotionEvent event)
    {
        if(event.getAction() == MotionEvent.ACTION_DOWN && position. y < 0.01f)
            physics.ApplyForce(new Vector2f(0.f, 250.f));
        else if(m_Camera != null)
        {
            if(f)
                m_Camera.SetMovingFactor(1.f, 0.f);
            else
                m_Camera.SetMovingFactor(0.f, 0.f);

            f = !f;
        }


        return states.top().OnTouchEvent(this, event);
    }

    public boolean OnKeyDown(int keyCode, KeyEvent event)
    {
        return states.top().OnKeyDown(this, keyCode, event);
    }
}
