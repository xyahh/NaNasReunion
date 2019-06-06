package yjj.nanasreunion.Command;

import yjj.nanasreunion.Objects.Actor;
import yjj.nanasreunion.Services.Timer;

public class TogglePauseCommand implements Command
{
    public TogglePauseCommand()
    {
    }

    @Override
    public void Execute(Actor actor)
    {
        Timer.TogglePause();
    }

}
