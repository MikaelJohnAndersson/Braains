package com.mygdx3.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;


public class AnimatedActor extends Actor {

    protected TextureRegion[] moveRegion;
    protected TextureRegion[] idleRegion;
    protected Array<TextureRegion> idleArray;
    protected Array<TextureRegion> moveArray;
    protected Animation<TextureRegion> moveAnim;
    protected Animation<TextureRegion> idleAnim;


    protected TextureRegion keyframe;
    protected Rectangle boundary;

    private float velocityX, velocityY;
    protected float elapsedTime;

    protected float lastX, lastY;

    public AnimatedActor(){

        super();
        keyframe = new TextureRegion();
        boundary = new Rectangle();
        velocityX = 0;
        velocityY = 0;
        lastX = 0;
        lastY = 0;
        elapsedTime = 0;

    }

    public void act(float dt){
        super.act(dt);

        moveBy(velocityX * dt, velocityY * dt);

        elapsedTime += dt;
        if (velocityX != 0 || velocityY != 0){
            setRotation(MathUtils.atan2(velocityY,velocityX) * MathUtils.radiansToDegrees);
        }


    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(isMoving())
            this.keyframe.setRegion(this.moveAnim.getKeyFrame(elapsedTime));
        else
            this.keyframe.setRegion(this.idleAnim.getKeyFrame(elapsedTime));

        batch.draw(this.keyframe, getX(), getY(), keyframe.getRegionWidth()/6, keyframe.getRegionHeight()/6,
                keyframe.getRegionWidth()/3, keyframe.getRegionHeight()/3, getScaleX(), getScaleY(), getRotation());
    }

    public Rectangle getBoundary() {
    boundary.set(this.getX(), this.getY(), this.keyframe.getRegionWidth()/5, this.keyframe.getRegionHeight()/5);
        return boundary;
    }

    public boolean isMoving(){
        if  ((Gdx.input.isKeyPressed(Input.Keys.LEFT)) ||
            (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) ||
            (Gdx.input.isKeyPressed(Input.Keys.DOWN)) ||
            (Gdx.input.isKeyPressed(Input.Keys.UP))){
                return true;
        }
        return false;
    }

    private boolean collision(){
        if (this.getX() + this.keyframe.getRegionWidth() > Gdx.graphics.getWidth()
            ||
            (this.getX()< 0)
            ||
            (this.getY() + this.keyframe.getRegionHeight() > Gdx.graphics.getHeight())
            ||
            (this.getY() < 0)){
            return true;
        }
    return false;
    }


    public float getVelocityX() {
        return velocityX;
    }

    public float getVelocityY() {
        return velocityY;
    }

    public void setVelocityX(float velocityX) {
        this.velocityX = velocityX;
    }

    public void setVelocityY(float velocityY) {
        this.velocityY = velocityY;
    }



}


