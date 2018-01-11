package com.mygdx3.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class ZombiePlayer extends AnimatedActor {

    public ZombiePlayer(){
        super();
        moveRegion = new TextureRegion[16]; // Number of animation frames
        idleRegion = new TextureRegion[16];

        for(int i = 0; i < moveRegion.length; i++){
            String filename = "skeleton-move_" + i + ".png";
            Texture texture = new Texture(Gdx.files.internal(filename));
            moveRegion[i] = new TextureRegion(texture);
        }

        for(int i = 0; i < idleRegion.length; i++){
            String filename = "skeleton-idle_" + i + ".png";
            Texture texture = new Texture(Gdx.files.internal(filename));
            idleRegion[i] = new TextureRegion(texture);
        }

        moveArray = new Array<TextureRegion>(moveRegion);
        moveAnim = new Animation<TextureRegion>(0.1f, moveArray, Animation.PlayMode.LOOP);

        idleArray = new Array<TextureRegion>(idleRegion);
        idleAnim = new Animation<TextureRegion>(0.1f, idleArray, Animation.PlayMode.LOOP);

    }
}
