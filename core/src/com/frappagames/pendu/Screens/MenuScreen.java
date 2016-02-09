package com.frappagames.pendu.Screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.frappagames.pendu.LePendu;

/**
 * Created by jmoreau on 11/01/16.
 */
public class MenuScreen extends GameScreen {
    private ImageButton playBtn;
    private Image titleImg;
    private Image decosImg;

    public MenuScreen(final LePendu game) {
        super(game);

        titleImg = new Image(new TextureRegionDrawable(game.atlas.findRegion("title")));
        playBtn  = new ImageButton(new TextureRegionDrawable(game.atlas.findRegion("playBtn")));
        decosImg = new Image(new TextureRegionDrawable(game.atlas.findRegion("decos")));

        table.add(titleImg).top().padTop(169);
        table.row();
        table.add(playBtn).expandY();
        table.row();
        table.add(decosImg).bottom().padBottom(50);

        playBtn.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                game.setScreen(new DifficultyScreen(game));
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
