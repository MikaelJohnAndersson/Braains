package com.mygdx3.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class BrainsLevel implements Screen {

	private Game game;

	private Stage stage;
	private Stage uiStage;
	private SpriteBatch batch;
	private Texture groundTexture;
	private ZombiePlayer zombie;
	private Brain brains;

	private Sound bite;
	private Sound steps;
	private Sound forest;
	private Sound moan1;
	private Sound moan2;
	private long id;

	private Label scoreLabel;
	private LabelStyle style;
	private FreeTypeFontGenerator generator;
	private FreeTypeFontParameter parameter;
	private int brainsEaten;


	public BrainsLevel(Game g){
	game = g;
	create();
	}

	public void create () {
		Gdx.graphics.setTitle("Braaains!");

		//Initializing stages
		stage = new Stage();
		uiStage = new Stage();

		//Setting up Spritebatch, actors and ground texture
		batch = new SpriteBatch();
		groundTexture = new Texture(Gdx.files.internal("grass.png"));
		zombie = new ZombiePlayer();
		zombie.setPosition(0,0);
		brains = new Brain();
		brains.setPosition(MathUtils.random(0, Gdx.graphics.getWidth()-brains.getWidth()), MathUtils.random(0,Gdx.graphics.getHeight()-brains.getHeight()));
		//Adding animation action to brain
		RepeatAction action = Actions.forever(Actions.sequence(Actions.scaleTo((float)1.5,(float)1.5,1), Actions.scaleTo(1,1,1)));
		brains.addAction(action);

		//Loading sounds
		bite = Gdx.audio.newSound(Gdx.files.internal("bite.mp3"));
		steps = Gdx.audio.newSound(Gdx.files.internal("steps.wav"));
		forest = Gdx.audio.newSound(Gdx.files.internal("forest.mp3"));
		moan1 = Gdx.audio.newSound(Gdx.files.internal("moan1.wav"));
		moan2 = Gdx.audio.newSound(Gdx.files.internal("moan2.wav"));

		//Setting fonts and scorelabel
		generator = new FreeTypeFontGenerator(Gdx.files.internal("Pixeled.ttf"));
		parameter = new FreeTypeFontParameter();
		parameter.size = 16;
		BitmapFont font = generator.generateFont(parameter);
		style = new LabelStyle(font, Color.BLACK);
		scoreLabel = new Label("Number of brains eaten: " +brainsEaten , style);
		scoreLabel.setPosition(10,Gdx.graphics.getHeight()- 40);
		brainsEaten = 0;

		//Adding actors to stages
		stage.addActor(brains);
		stage.addActor(zombie);
		uiStage.addActor(scoreLabel);

		//Looping stepping sound w. zero volume
		id = steps.loop(0.0f);
		//Forest sound
		forest.loop(0.3f);

	}

	@Override
	public void render (float dt) {

		//Method to update zombie coordinates relative to player input
		zombie.setVelocityX(0);
		zombie.setVelocityY(0);

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
			zombie.setVelocityX(zombie.getVelocityX() - 100);
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
			zombie.setVelocityX(zombie.getVelocityX() + 100);
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
			zombie.setVelocityY(zombie.getVelocityY() - 100);
		if (Gdx.input.isKeyPressed(Input.Keys.UP))
			zombie.setVelocityY(zombie.getVelocityY() + 100);
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
			moan2.play(0.4f);
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
			forest.stop();
			game.setScreen(new BrainsMenu(game));}

		stage.act(dt);

		//Resetting zombie position if out of bounds
		zombie.setX(MathUtils.clamp(zombie.getX(), 0, Gdx.graphics.getWidth()  - zombie.getWidth()));
		zombie.setY(MathUtils.clamp(zombie.getY(), 0, Gdx.graphics.getHeight()  - zombie.getHeight()));

		uiStage.act(dt);

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(groundTexture, 0, 0);
		batch.end();

		stage.draw();
		uiStage.draw();

		//Checks if zombie ate brains
		if (zombie.getBoundary().overlaps(brains.getBoundary())){
			brainsEaten++;
			bite.play(0.3f);
			moan1.play(0.2f);
			scoreLabel.setText("Number of brains eaten: " +brainsEaten);
			brains.setPosition(MathUtils.random(0, Gdx.graphics.getWidth()-60), MathUtils.random(0,Gdx.graphics.getHeight()-60));
		}

		//Playing walking sound if zombie is moving
		if(zombie.isMoving()){
			steps.setVolume(id, 0.5f);
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
		generator.dispose();
		stage.dispose();
		uiStage.dispose();
	}

	@Override
	public void show() {

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}
}
