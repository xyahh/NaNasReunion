package yjj.nanasreunion.Command;

import yjj.nanasreunion.Objects.Actor;

public class SelfDestructCommand implements Command
{
    @Override
    public void Execute(Actor instigator, Actor target)
    {
        instigator.DestroyActor();
    }
}
