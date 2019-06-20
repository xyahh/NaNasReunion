package yjj.nanasreunion.Command;

import yjj.nanasreunion.Objects.Actor;

public class KillCommand implements Command
{
    public KillCommand()
    {

    }

    @Override
    public void Execute(Actor instigator, Actor target) {
            target.DestroyActor();
    }
}
