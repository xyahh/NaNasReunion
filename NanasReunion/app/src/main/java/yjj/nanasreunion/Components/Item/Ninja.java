package yjj.nanasreunion.Components.Item;

import android.graphics.Canvas;

import yjj.nanasreunion.Components.Camera;
import yjj.nanasreunion.Components.Graphics.SpriteGraphics;
import yjj.nanasreunion.Objects.Pawn;
import yjj.nanasreunion.Vector2f;

public class Ninja extends Item {
    private SpriteGraphics graphics;
    private Vector2f position;
    private Vector2f pivot;
    private float scaleX = 0.75f;
    private float scaleY = 0.75f;
    private float collisionX = 0.15f;
    private float collisionY = 0.4f;

    protected Ninja()
    {
        super("Ninja", 5.f);
    }


    @Override
    public Item Create() {
        return null;
    }

    @Override
    public void Use(Pawn pawn, Camera camera) {

    }

    @Override
    public boolean UpdateAndValidate(Pawn pawn, Camera camera, float deltaTime) {
        return super.UpdateAndValidate(pawn, camera, deltaTime);
    }

    @Override
    public void Stop(Pawn pawn, Camera camera) {

    }

    @Override
    public void Draw(Canvas canvas, Camera camera, Pawn pawn) {

    }
}
