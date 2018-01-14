package com.mygdx3.game;

import com.badlogic.gdx.Game;

public class MyGdxGame3 extends Game{

    @Override
    public void create() {
        BrainsMenu bl = new BrainsMenu(this);
        setScreen(bl);
    }
}
