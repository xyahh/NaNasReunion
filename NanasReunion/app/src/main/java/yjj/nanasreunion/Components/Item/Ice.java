package yjj.nanasreunion.Components.Item;

import android.graphics.Canvas;

import yjj.nanasreunion.Components.Camera;
import yjj.nanasreunion.Components.Graphics.SpriteGraphics;
import yjj.nanasreunion.Objects.Pawn;
import yjj.nanasreunion.R;
import yjj.nanasreunion.Services.ServiceHub;
import yjj.nanasreunion.Vector2f;

public class Ice extends Item {

    float   OriginalMass;
    Vector2f   OriginalMaxVelocity;
    private SpriteGraphics graphics;
    private Vector2f pivot;
    protected Ice()
    {
        super("Ice", 5.f);
    }

    @Override
    public Item Create() {
        return new Ice();
    }

    @Override
    public void Use(Pawn pawn, Camera camera) {

        graphics     = new SpriteGraphics(ServiceHub.Inst().GetBitmap(R.drawable.frosted_ground),
                10,1,1);
        graphics.SetScale(0.7f, 0.7f);

        OriginalMass = pawn.physics.GetMass();
        pawn.physics.SetMass(OriginalMass * 0.75f);

        OriginalMaxVelocity = pawn.physics.GetMaxVelocity();
        pawn.physics.SetMaxVelocity(new Vector2f(OriginalMaxVelocity.x * 2.f, OriginalMaxVelocity.y));
    }

    @Override
    public boolean UpdateAndValidate(Pawn pawn, Camera camera, float deltaTime) {
        return super.UpdateAndValidate(pawn, camera, deltaTime);
    }

    @Override
    public void Stop(Pawn pawn, Camera camera) {
        pawn.physics.SetMass(OriginalMass);
        pawn.physics.SetMaxVelocity(OriginalMaxVelocity);
    }

    @Override
    public void Draw(Canvas canvas, Camera camera, Pawn pawn) {
        Vector2f position     = new Vector2f(pawn.position.x+1.0f, pawn.position.y+0.25f);

        graphics.Draw(canvas, camera, position, pivot, 0.f);
    }
}
