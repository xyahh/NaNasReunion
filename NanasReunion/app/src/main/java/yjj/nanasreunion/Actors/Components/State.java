package yjj.nanasreunion.Actors.Components;

import yjj.nanasreunion.Actors.Actor;

public interface State
{
    void Enter(Actor actor);
    void Exit(Actor actor);
    void Update(Actor actor, float DeltaTime);
}

