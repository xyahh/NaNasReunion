package yjj.nanasreunion.Objects;

import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

import java.util.ArrayDeque;

import yjj.nanasreunion.Components.Camera;
import yjj.nanasreunion.Components.Item.Item;
import yjj.nanasreunion.Components.Physics.NullPhysics;
import yjj.nanasreunion.Components.Physics.Physics;
import yjj.nanasreunion.Components.State.*;
import yjj.nanasreunion.MyStack;

public class Pawn extends Actor
{
    public     MyStack<State> states;
    private     Camera         m_Camera;
    public      Physics physics;
    private     Item m_Item;

    public      float           JumpForce;
    public      float           RunningForce;

    public Pawn()
    {
        super();
        JumpForce = 300.f;
        RunningForce = 15.f;
        physics     = new NullPhysics();
        states = new MyStack<>();
        PushState(new NullState());
        m_Item = null;
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
        if (m_Item != null)
            m_Item.Draw(canvas, camera, this);
    }

    public void ChangeState(State state)
    {
        PopState();
        PushState(state);
    }

    @Override
    public void Update(float DeltaTime) {
        super.Update(DeltaTime);
        physics.Update(this, DeltaTime);
        states.top().Update(this, DeltaTime);

        if (m_Item != null)
        {
            if (m_Item.UpdateAndValidate(this, m_Camera, DeltaTime))
            {
                m_Item.Stop(this, m_Camera);
                m_Item = null;
            }
        }

    }

    public boolean OnTouchEvent(MotionEvent event)
    {
        return states.top().OnTouchEvent(this, event);
    }

    public boolean OnKeyDown(int keyCode, KeyEvent event)
    {
        return states.top().OnKeyDown(this,keyCode, event);
    }

    public void AddItem(Item item)
    {
        if(m_Item != null)
        {
            m_Item.Stop(this, m_Camera);
            m_Item = null;
        }
        m_Item = item;
        m_Item.Use(this, m_Camera);
    }
}
