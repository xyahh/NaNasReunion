package yjj.nanasreunion.Command;

import yjj.nanasreunion.Objects.Actor;
import yjj.nanasreunion.Services.ServiceHub;

public class GameOverCommand implements Command {

    @Override
    public void Execute(Actor instigator, Actor target) {
        ServiceHub.LosingCondition = true;
    }
}
