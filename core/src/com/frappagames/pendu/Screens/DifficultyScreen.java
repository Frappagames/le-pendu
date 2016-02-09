package com.frappagames.pendu.Screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.frappagames.pendu.LePendu;

/**
 * Created by jmoreau on 11/01/16.
 */
public class DifficultyScreen extends GameScreen {
    private ImageButton easyBtn;
    private ImageButton normalBtn;
    private ImageButton hardBtn;
    private Image titleImg;
    private Image decosImg;

    public DifficultyScreen(final LePendu game) {
        super(game);

        titleImg  = new Image(new TextureRegionDrawable(game.atlas.findRegion("title")));
        decosImg  = new Image(new TextureRegionDrawable(game.atlas.findRegion("decos")));
        easyBtn   = new ImageButton(new TextureRegionDrawable(game.atlas.findRegion("easyBtn")));
        normalBtn = new ImageButton(new TextureRegionDrawable(game.atlas.findRegion("normalBtn")));
        hardBtn   = new ImageButton(new TextureRegionDrawable(game.atlas.findRegion("hardBtn")));

        VerticalGroup vg = new VerticalGroup();
        vg.addActor(easyBtn);
        vg.addActor(normalBtn);
        vg.addActor(hardBtn);

        table.add(titleImg).top().padTop(169);
        table.row();
        table.add(vg).expandY();
        table.row();
        table.add(decosImg).bottom().padBottom(50);

        easyBtn.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                game.setScreen(new PlayScreen(game, 1));
            }
        });
        normalBtn.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                game.setScreen(new PlayScreen(game, 2));
            }
        });
        hardBtn.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                game.setScreen(new PlayScreen(game, 3));
            }
        });
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
