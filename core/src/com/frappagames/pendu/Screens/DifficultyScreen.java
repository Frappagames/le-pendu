package com.frappagames.pendu.Screens;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.frappagames.pendu.LePendu;

/**
 * Created by jmoreau on 11/01/16.
 */
public class DifficultyScreen extends GameScreen {
    private TextureRegion title;
    private TextureRegion decos;
    private TextureRegion easyTexture;
    private TextureRegion normalTexture;
    private TextureRegion hardTexture;
    private ImageButton easyBtn;
    private ImageButton normalBtn;
    private ImageButton hardBtn;

    public DifficultyScreen(final LePendu game) {
        super(game);

        title         = game.atlas.findRegion("title");
        decos         = game.atlas.findRegion("decos");
        easyTexture   = game.atlas.findRegion("easyBtn");
        normalTexture = game.atlas.findRegion("normalBtn");
        hardTexture   = game.atlas.findRegion("hardBtn");

        easyBtn       = new ImageButton(new TextureRegionDrawable(easyTexture));
        easyBtn.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                game.setScreen(new PlayScreen(game, 1));
            }
        });

        normalBtn     = new ImageButton(new TextureRegionDrawable(normalTexture));
        normalBtn.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                game.setScreen(new PlayScreen(game, 2));
            }
        });

        hardBtn       = new ImageButton(new TextureRegionDrawable(hardTexture));
        hardBtn.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                game.setScreen(new PlayScreen(game, 3));
            }
        });

        table.add(easyBtn).row();
        table.add(normalBtn).row();
        table.add(hardBtn).row();
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
