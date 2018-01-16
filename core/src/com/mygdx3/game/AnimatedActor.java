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

    //Arrays and Textureregions for animations
    protected TextureRegion[] moveRegion;
    protected TextureRegion[] idleRegion;
    protected Array<TextureRegion> idleArray;
    protected Array<TextureRegion> moveArray;
    protected Animation<TextureRegion> moveAnim;
    protected Animation<TextureRegion> idleAnim;
    protected TextureRegion keyframe;
    protected int textureScale;

    //Rectangle for bounds
    protected Rectangle boundary;

    private float velocityX, velocityY;

    //Variable to fetch correct animation keyframe using elapsed gametime
    protected float elapsedTime;


    public AnimatedActor(){

        super();
        keyframe = new TextureRegion();
        boundary = new Rectangle();
        velocityX = 0;
        velocityY = 0;
        elapsedTime = 0;

    }

    public void act(float dt){
        super.act(dt);

        //Moves actor in both axises according to velocity times DT
        moveBy(velocityX * dt, velocityY * dt);

        //Incrementing elapsed time with DT
        elapsedTime += dt;

        //Rotates actor according to x/y velocity
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

        batch.draw(this.keyframe, getX(), getY(), getWidth()/2, getHeight()/2,
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }

    public Rectangle getBoundary() {
    boundary.set(this.getX(), this.getY(), getWidth(), getHeight());
        return boundary;
    }

    //Method to check if player is moving(Pressing arrow keys)
    public boolean isMoving(){
        if  ((Gdx.input.isKeyPressed(Input.Keys.LEFT)) ||
            (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) ||
            (Gdx.input.isKeyPressed(Input.Keys.DOWN)) ||
            (Gdx.input.isKeyPressed(Input.Keys.UP))){
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

    @Override
    public float getWidth() {
        return this.keyframe.getRegionWidth() / this.textureScale;
    }

    @Override
    public float getHeight() {
        return this.keyframe.getRegionHeight() / this.textureScale;
    }
}


