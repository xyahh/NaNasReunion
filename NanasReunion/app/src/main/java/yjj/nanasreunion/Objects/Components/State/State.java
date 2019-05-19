package yjj.nanasreunion.Objects.Components.State;

import yjj.nanasreunion.Objects.Actor;

public interface State
{
    void Enter(Actor actor);
    void Exit(Actor actor);

    void Update(Actor actor, float DeltaTime);
}

