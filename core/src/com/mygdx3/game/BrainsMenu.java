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
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;

public class BrainsMenu implements Screen{

    private Stage uiStage;
    private Game game;
    private Texture background;
    private TextureRegion backgroundRegion;
    private SpriteBatch batch;
    private MenuText menuText;


    public BrainsMenu(Game g){
        game = g;
        create();
    }

    public void create(){
        uiStage = new Stage();
        background = new Texture(Gdx.files.internal("brainsStartScreen.jpg"));
        backgroundRegion = new TextureRegion(background);
        batch = new SpriteBatch();
        menuText = new MenuText();

        RepeatAction action = Actions.forever(Actions.sequence(Actions.scaleTo(1,1,1), Actions.scaleTo(0,0,1)));
        menuText.addAction(action);

        uiStage.addActor(menuText);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float dt) {

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER))
            game.setScreen(new BrainsLevel(game));

        uiStage.act(dt);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(backgroundRegion,0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        uiStage.draw();

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
