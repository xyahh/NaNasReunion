package yjj.nanasreunion.Actors;

import java.util.Deque;

import yjj.nanasreunion.Actors.Components.*;

import yjj.nanasreunion.Actors.Components.Physics.NullPhysics;
import yjj.nanasreunion.Actors.Components.Physics.Physics;
import yjj.nanasreunion.Vector2d;

public class Actor
{

    public Vector2d         position;
    public Graphics         graphics;
    public Physics          physics;
    public Deque<State>     states;

    Actor()
    {
        physics = new NullPhysics();
    }
}
