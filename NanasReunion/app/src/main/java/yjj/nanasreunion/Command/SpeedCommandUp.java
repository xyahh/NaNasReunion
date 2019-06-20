package yjj.nanasreunion.Command;

import yjj.nanasreunion.Objects.Actor;
import yjj.nanasreunion.Vector2f;

public class SpeedCommandUp implements Command
{
    Vector2f OriginalMaxVelocity;
    public SpeedCommandUp()
    {

    }

    @Override
    public void Execute(Actor instigator, Actor target)
    {
        target.physics.SetMaxVelocity(new Vector2f(OriginalMaxVelocity.x * 4.5f, OriginalMaxVelocity.y));
    }
}
