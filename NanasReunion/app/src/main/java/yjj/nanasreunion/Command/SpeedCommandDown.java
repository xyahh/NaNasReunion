package yjj.nanasreunion.Command;

import yjj.nanasreunion.Objects.Actor;
import yjj.nanasreunion.Vector2f;

public class SpeedCommandDown implements Command
{
    Vector2f OriginalMaxVelocity;
    public SpeedCommandDown()
    {

    }

    @Override
    public void Execute(Actor instigator, Actor target)
    {
       target.physics.SetMaxVelocity(new Vector2f(OriginalMaxVelocity.x * 0.5f, OriginalMaxVelocity.y));
    }
}
