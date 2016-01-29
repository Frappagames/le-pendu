package com.frappagames.pendu.Screens;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.frappagames.pendu.LePendu;

/**
 * Created by jmoreau on 11/01/16.
 */
public class MenuScreen extends GameScreen {
    private TextureRegion title;
    private TextureRegion decos;
    private TextureRegion playTexture;
    private ImageButton playBtn;

    public MenuScreen(final LePendu game) {
        super(game);

        title       = game.atlas.findRegion("title");
        playTexture = game.atlas.findRegion("playBtn");
        decos       = game.atlas.findRegion("decos");

        playBtn     = new ImageButton(new TextureRegionDrawable(playTexture));
        playBtn.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                game.setScreen(new DifficultyScreen(game));
            }
        });

        table.add(playBtn);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        game.batch.begin();
        game.batch.draw(title, 95, 1030);
        game.batch.draw(decos, 45, 80);
        game.batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
