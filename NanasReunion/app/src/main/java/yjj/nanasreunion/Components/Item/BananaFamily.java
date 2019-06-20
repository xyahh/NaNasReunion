package yjj.nanasreunion.Components.Item;

import android.graphics.Canvas;

import yjj.nanasreunion.Components.Camera;
import yjj.nanasreunion.Components.Graphics.Graphics;
import yjj.nanasreunion.Components.Graphics.SpriteGraphics;
import yjj.nanasreunion.Components.Physics.Physics;
import yjj.nanasreunion.Objects.Pawn;
import yjj.nanasreunion.R;
import yjj.nanasreunion.Services.ServiceHub;
import yjj.nanasreunion.Vector2f;

public class BananaFamily extends Item {
    private Graphics graphics;
    private Physics physics;
    private Vector2f pivot;
    private Vector2f position;
    protected BananaFamily() {
            super("BananaFamily", 5.f);
    }

    @Override
    public Item Create() {
        return new BananaFamily();
    }

    @Override
    public void Use(Pawn pawn, Camera camera) {
        graphics     = new SpriteGraphics(ServiceHub.Inst().GetBitmap(R.drawable.banana_family),
                10,1,1);
        graphics.SetScale(0.7f, 0.7f);
        pivot = new Vector2f(0.5f, 0.5f);
    }

    public void Update(float DeltaTime) {
        super.Update(DeltaTime);
        physics.ApplyForce(new Vector2f(0.f, 0.f));
    }



    @Override
    public void Stop(Pawn pawn, Camera camera) {

    }

    @Override
    public void Draw(Canvas canvas, Camera camera, Pawn pawn) {
        Vector2f position     = new Vector2f(pawn.position.x+1.0f, pawn.position.y+0.25f);

        graphics.Draw(canvas, camera, position, pivot, 0.f);
    }
}
