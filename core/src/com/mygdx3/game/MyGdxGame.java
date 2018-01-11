package com.mygdx3.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture groundTexture;
	ZombiePlayer zombie;
	Stage stage;
	float dt;
	Brain brains;
	int brainsEaten;
	Sound bite;
	Sound steps;
	Sound forest;
	Sound moan1;
	Sound moan2;
	long id;
	String str;
	BitmapFont font;
	
	@Override
	public void create () {
		Gdx.graphics.setTitle("Braaains ver.2");

		batch = new SpriteBatch();

		stage = new Stage();
		zombie = new ZombiePlayer();
		zombie.setPosition(0,0);

		bite = Gdx.audio.newSound(Gdx.files.internal("bite.mp3"));
		steps = Gdx.audio.newSound(Gdx.files.internal("steps.wav"));
		forest = Gdx.audio.newSound(Gdx.files.internal("forest.mp3"));
		moan1 = Gdx.audio.newSound(Gdx.files.internal("moan1.wav"));
		moan2 = Gdx.audio.newSound(Gdx.files.internal("moan2.wav"));

		font = new BitmapFont();

		groundTexture = new Texture(Gdx.files.internal("grass.png"));
		brains = new Brain();
		brains.setPosition(MathUtils.random(0, Gdx.graphics.getWidth()-brains.getWidth()), MathUtils.random(0,Gdx.graphics.getHeight()-brains.getHeight()));

		stage.addActor(brains);
		stage.addActor(zombie);
		brainsEaten = 0;

		//Looping stepping sound w. zero volume
		id = steps.loop(0.0f);
		//Forest sound
		forest.loop(0.2f);

		str = "Number of brains eaten: ";

		RepeatAction action = Actions.forever(Actions.sequence(Actions.scaleTo(2,2,1), Actions.scaleTo(1,1,1)));

		brains.addAction(action);
	}

	@Override
	public void render () {

		zombie.velocityX = 0;
		zombie.velocityY = 0;

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
			zombie.velocityX -= 100;
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
			zombie.velocityX += 100;
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
			zombie.velocityY -= 100;
		if (Gdx.input.isKeyPressed(Input.Keys.UP))
			zombie.velocityY += 100;
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
			moan2.play(0.3f);

		dt = Gdx.graphics.getDeltaTime();
		stage.act(dt);

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(groundTexture, 0, 0);
		font.draw(batch, str + Integer.toString(brainsEaten) , 10, Gdx.graphics.getHeight() -10f);
		batch.end();

		stage.draw();

		//Checks if zombie ate brains
		if (zombie.getBoundary().overlaps(brains.getBoundary())){
			brainsEaten++;
			bite.play(0.2f);
			moan1.play(0.1f);
			brains.setPosition(MathUtils.random(0, Gdx.graphics.getWidth()-60), MathUtils.random(0,Gdx.graphics.getHeight()-60));
		}

		if(zombie.isMoving()){
			steps.setVolume(id, 0.6f);
		}
		else{
			steps.setVolume(id, 0.0f);
		}

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		groundTexture.dispose();
		steps.dispose();
		bite.dispose();
		forest.dispose();
		moan2.dispose();
		moan1.dispose();
	}
}
