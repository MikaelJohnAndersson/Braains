package com.mygdx3.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class BrainsMenu implements Screen{

    private Stage uiStage;
    private Game game;
    private Texture background;
    private TextureRegion backgroundRegion;
    private SpriteBatch batch;

    public BrainsMenu(Game g){
        game = g;
        create();
    }

    public void create(){
        uiStage = new Stage();
        background = new Texture(Gdx.files.internal("brainsStartScreen.jpg"));
        backgroundRegion = new TextureRegion(background);
        batch = new SpriteBatch();

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER))
            game.setScreen(new BrainsLevel(game));

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(backgroundRegion,0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

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

    @Override
    public void dispose() {
        batch.dispose();
        uiStage.dispose();
        background.dispose();

    }
}
