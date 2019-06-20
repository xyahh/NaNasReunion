package yjj.nanasreunion.Command;

import yjj.nanasreunion.Objects.Actor;
import yjj.nanasreunion.Vector2f;

public class MaxSpeedCommand implements Command
{
    Vector2f NewMaxSpeed;

    public MaxSpeedCommand(Vector2f MaxSpeed)
    {
        NewMaxSpeed = MaxSpeed;
    }

    @Override
    public void Execute(Actor instigator, Actor target)
    {
       target.physics.SetMaxVelocity(NewMaxSpeed);
    }
}
