package com.mygdx3.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx3.game.BrainsLevel;
import com.mygdx3.game.MyGdxGame3;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 640;
		config.height = 640;
		config.resizable = false;
		new LwjglApplication(new MyGdxGame3(), config);
	}
}
