package yjj.nanasreunion.Components.Physics;

import yjj.nanasreunion.Objects.Actor;
import yjj.nanasreunion.Vector2f;

public class NullPhysics extends Physics
{
    @Override
    public void Update(Actor actor, float DeltaTime)
    {
        //Do nothing
    }

    @Override
    public void ApplyForce(Vector2f Force)
    {
    }
}
