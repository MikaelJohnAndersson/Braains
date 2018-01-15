package com.mygdx3.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class MenuText extends Actor{

    private TextureRegion region;
    private Texture texture;

    public MenuText(){
        super();
        texture = new Texture(Gdx.files.internal("pressEnter.png"));
        region = new TextureRegion(texture);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(region, 0, 0, Gdx.graphics.getWidth()/2, region.getRegionHeight()/2,
                Gdx.graphics.getWidth(), region.getRegionHeight(), getScaleX(), getScaleY(), getRotation());
    }
}
