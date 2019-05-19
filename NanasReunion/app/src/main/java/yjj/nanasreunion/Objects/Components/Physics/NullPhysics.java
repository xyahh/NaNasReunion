package yjj.nanasreunion.Objects.Components.Physics;

import yjj.nanasreunion.Objects.Actor;
import yjj.nanasreunion.Vector2d;

public class NullPhysics extends Physics
{
    @Override
    public void Update(Actor actor, float DeltaTime)
    {
        //Do nothing
    }

    @Override
    public void ApplyForce(Vector2d Force)
    {
    }
}
