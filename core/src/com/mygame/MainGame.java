package com.mygame;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.assets.AssetManager;


public class MainGame extends ApplicationAdapter {


    private WorldController worldController;
    private WorldRenderer worldRenderer;

    private boolean paused;

    @Override
    public void create() {

        // Set Libgdx log level to DEBUG
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        Assets.instance.init(new AssetManager());

        // Initialize controller and renderer
        worldController = new WorldController();
        worldRenderer = new WorldRenderer(worldController);

        paused = false;


    }

    @Override
    public void render() {


        if (!paused) {

            worldController.update(Gdx.graphics.getDeltaTime());

        }
        // Sets the clear screen color to: Cornflower Blue
        Gdx.gl.glClearColor(100 / 255.0f, 149 / 255.0f, 237 / 255.0f, 255 / 255.0f);
        // Clears the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // Render game world to screen
        worldRenderer.render();

    }

    @Override
    public void resize(int width, int height) {
        worldRenderer.resize(width, height);
    }

    @Override
    public void dispose() {
        worldRenderer.dispose();
        Assets.instance.dispose();
    }

    @Override
    public void pause() {
        paused = true;
    }

    @Override
    public void resume() {
        Assets.instance.init(new AssetManager());
        paused = false;
    }

}
