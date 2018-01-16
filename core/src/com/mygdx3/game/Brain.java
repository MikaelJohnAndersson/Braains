package com.mygdx3.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Brain extends Actor {

    private TextureRegion brain;
    private Rectangle boundary;

    public Brain(){
        super();
        brain = new TextureRegion(new Texture(Gdx.files.internal("brains.png")));
        setWidth(brain.getRegionWidth()/5);
        setHeight(brain.getRegionHeight()/5);
        setOrigin(getWidth()/2, getHeight()/2);
        boundary = new Rectangle();
    }

    public void draw(Batch batch, float parentAlpha){
        batch.draw(brain, getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }

    public Rectangle getBoundary() {
        boundary.set(this.getX(), this.getY(), getWidth(), getHeight());
        return boundary;
    }

    public void act(float dt){
        super.act(dt);
    }
}
