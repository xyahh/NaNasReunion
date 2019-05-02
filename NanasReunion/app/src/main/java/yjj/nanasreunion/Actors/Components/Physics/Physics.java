package yjj.nanasreunion.Actors.Components.Physics;

import yjj.nanasreunion.Vector2d;
import yjj.nanasreunion.Actors.Actor;

public class Physics
{
    protected float     m_Mass;
    protected Vector2d  m_Velocity;
    protected Vector2d  m_Acceleration;
    protected float     m_Gravity; //Acceleration
    protected float     m_FrictionCoeff;

    public Physics()
    {
        m_Mass          = 1.f;
        m_Velocity      = new Vector2d();
        m_Acceleration  = new Vector2d();
        m_Gravity       = -9.8f;
        m_FrictionCoeff = 1.f;
    }

    public float    GetMass()           { return m_Mass; }
    public void     SetMass(float Mass) { m_Mass = Mass; }

    public Vector2d GetVelocity()                   { return m_Velocity; }

    public float    GetGravity()                    { return m_Gravity;}
    public void     SetGravity(float Gravity)       { m_Gravity = Gravity;}

    public float    GetFriction()                   { return m_FrictionCoeff; }
    public void     SetFriction(float Friction)     { m_FrictionCoeff = Friction;}


    public void SetForce(Vector2d Force)
    {
        m_Acceleration = Vector2d.Scale(Force, 1.f/ m_Mass);
    }

    public void ApplyForce(Vector2d Force)
    {
        m_Acceleration = Vector2d.Add(m_Acceleration, Vector2d.Scale(Force, 1.f/m_Mass));
    }

    public void Update(Actor actor, float DeltaTime)
    {
        //Friction
        {
            Vector2d FrictionVelocity =  Vector2d.Scale(m_Velocity,
                    -1.f * Math.abs(DeltaTime * m_FrictionCoeff * m_Gravity));

            Vector2d PreviousVelocity = new Vector2d(m_Velocity);
            m_Velocity = Vector2d.Add(m_Velocity, FrictionVelocity);

            if(m_Velocity.x * PreviousVelocity.x < 0.f) m_Velocity.x = 0.f;
            if(m_Velocity.y * PreviousVelocity.y < 0.f) m_Velocity.y = 0.f;
        }

        m_Acceleration = Vector2d.Add(m_Acceleration, new Vector2d(0.f, m_Gravity));
        m_Velocity = Vector2d.Add(m_Velocity, Vector2d.Scale(m_Acceleration, DeltaTime));
        actor.position = Vector2d.Add(actor.position, Vector2d.Scale(m_Velocity, DeltaTime));

        //LOWEST LEVEL
        if(actor.position.y < 0.f)
        {
            m_Velocity.y = 0.f;
            actor.position.y = 0.f;
        }
    }
}
