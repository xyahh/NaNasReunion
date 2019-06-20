package yjj.nanasreunion.Components.Item;

import android.graphics.Canvas;

import yjj.nanasreunion.Components.Camera;
import yjj.nanasreunion.Components.Graphics.SpriteGraphics;
import yjj.nanasreunion.Objects.Pawn;
import yjj.nanasreunion.Vector2f;

public class BabyBanana extends Item
{
    Vector2f OriginalDimensions;
    Vector2f OriginalScale;
    float   OriginalMass;
    Vector2f   OriginalMaxVelocity;
    private SpriteGraphics graphics;

    protected BabyBanana()
    {
        super("Baby Banana", 5.f);
    }

    @Override
    public Item Create()
    {
        return new BabyBanana();
    }

    @Override
    public void Use(Pawn pawn, Camera camera)
    {
        OriginalDimensions = pawn.collision.GetDimensions();
        OriginalScale = pawn.graphics.GetScale();

        float Scale = 0.5f;
        pawn.collision.SetDimensions(OriginalDimensions.x *Scale,
                OriginalDimensions.y * Scale);
        pawn.graphics.SetScale(Scale, Scale);

        OriginalMass = pawn.physics.GetMass();
        pawn.physics.SetMass(OriginalMass * 0.75f);

        OriginalMaxVelocity = pawn.physics.GetMaxVelocity();
        pawn.physics.SetMaxVelocity(new Vector2f(OriginalMaxVelocity.x * 2.f, OriginalMaxVelocity.y));
    }

    @Override
    public void Stop(Pawn pawn, Camera camera)
    {
        pawn.collision.SetDimensions(OriginalDimensions.x, OriginalDimensions.y);
        pawn.graphics.SetScale(OriginalScale.x, OriginalScale.y);
        pawn.physics.SetMass(OriginalMass);
        pawn.physics.SetMaxVelocity(OriginalMaxVelocity);
    }

}
