package yjj.nanasreunion.Components.Item;

import yjj.nanasreunion.Components.Camera;
import yjj.nanasreunion.Objects.Pawn;
import yjj.nanasreunion.Services.ServiceHub;

public class MirrorWorld extends Item
{
    public MirrorWorld() {
        super("Mirror World!", 7.f);
    }

    @Override
    public Item Create() {
        return new MirrorWorld();
    }

    @Override
    public void Use(Pawn pawn, Camera camera)
    {
        ServiceHub.RightwardGameplay = false;
    }

    @Override
    public void Stop(Pawn pawn, Camera camera) {
        ServiceHub.RightwardGameplay = true;
    }

}
