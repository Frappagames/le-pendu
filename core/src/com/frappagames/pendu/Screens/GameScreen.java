package com.frappagames.pendu.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.frappagames.pendu.LePendu;

/**
 * Created by jmoreau on 12/01/16.
 */
public class GameScreen implements Screen {
    protected LePendu game;
    private final OrthographicCamera camera;
    private final Viewport viewport;
    protected Stage stage;
    protected Table table;

    private Texture background;

    public GameScreen(LePendu game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.position.set(400, 640, 0);
        viewport = new FitViewport(800, 1280, camera);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true );
        stage.addActor(table);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(background, 0, 0);
        game.batch.end();
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        if (((width * 16) / height) == 9) {
            viewport.setWorldWidth(720);
            camera.position.x = 360;
            background = new Texture("background-16-9.jpg");
        } else {
            viewport.setWorldWidth(800);
            camera.position.x = 400;
            background = new Texture("background-8-5.jpg");
        }
        viewport.update(width, height);
        stage.getViewport().update(width, height, true);
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
        background.dispose();
        stage.dispose();
    }
}
