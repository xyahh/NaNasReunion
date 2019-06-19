package yjj.nanasreunion.Components.Physics;

import yjj.nanasreunion.Vector2f;
import yjj.nanasreunion.Objects.Actor;

public class Physics
{
    protected float     m_Mass;
    protected Vector2f m_Velocity;
    protected Vector2f m_Acceleration;
    protected float     m_Gravity; //Acceleration
    protected float     m_FrictionCoeff;
    protected Vector2f m_MaxSpeed;

    public Physics()
    {
        m_Mass          = 1.f;
        m_Velocity      = new Vector2f();
        m_MaxSpeed = new Vector2f(99999.f, 99999.f);
        m_Acceleration  = new Vector2f();
        m_Gravity       = -9.8f;
        m_FrictionCoeff = 1.f;
    }

    public float    GetMass()           { return m_Mass; }
    public void     SetMass(float Mass) { m_Mass = Mass; }

    public Vector2f GetVelocity()                   { return m_Velocity; }
    public void     SetVelocity(Vector2f Velocity) { m_Velocity = Velocity;}
    public float    GetGravity()                    { return m_Gravity;}
    public void     SetGravity(float Gravity)       { m_Gravity = Gravity;}

    public void     SetMaxVelocity(Vector2f maxVelocity) { m_MaxSpeed = maxVelocity;}
    public Vector2f GetMaxVelocity() { return m_MaxSpeed;}

    public float    GetFriction()                   { return m_FrictionCoeff; }
    public void     SetFriction(float Friction)     { m_FrictionCoeff = Friction;}


    public void SetForce(Vector2f Force)
    {
        m_Acceleration = Vector2f.Scale(Force, 1.f/ m_Mass);
    }

    public void ApplyForce(Vector2f Force)
    {
        m_Acceleration = Vector2f.Add(m_Acceleration, Vector2f.Scale(Force, 1.f/m_Mass));
    }

    public void Update(Actor actor, float DeltaTime)
    {
        //Friction
        {
            float FrictionVelocityX =  m_Velocity.x * Math.abs(DeltaTime * m_FrictionCoeff * m_Gravity);

            Vector2f PreviousVelocity = new Vector2f(m_Velocity);
            m_Velocity.x -= FrictionVelocityX;
            if(m_Velocity.x * PreviousVelocity.x < 0.f) m_Velocity.x = 0.f;
        }

        m_Acceleration = Vector2f.Add(m_Acceleration, new Vector2f(0.f, m_Gravity));
        m_Velocity = Vector2f.Add(m_Velocity, Vector2f.Scale(m_Acceleration, DeltaTime));

        if(m_Velocity.x > m_MaxSpeed.x)
            m_Velocity.x = m_MaxSpeed.x;
        else if(m_Velocity.x < -m_MaxSpeed.x)
            m_Velocity.x = -m_MaxSpeed.x;

        if(m_Velocity.y > m_MaxSpeed.y)
            m_Velocity.y = m_MaxSpeed.y;
        else if(m_Velocity.y < -m_MaxSpeed.y)
            m_Velocity.y = -m_MaxSpeed.y;

        Vector2f newPos = Vector2f.Add(actor.position, Vector2f.Scale(m_Velocity, DeltaTime));

        actor.position.x = newPos.x;
        actor.position.y = newPos.y;

        //LOWEST LEVEL
        if(actor.position.y < 0.f)
        {
            m_Velocity.y = 0.f;
            actor.position.y = 0.f;
        }

        m_Acceleration = new Vector2f();
    }
}
