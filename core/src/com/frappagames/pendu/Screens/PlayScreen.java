package com.frappagames.pendu.Screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.frappagames.pendu.Actors.KeyboardActor;
import com.frappagames.pendu.LePendu;
import com.frappagames.pendu.Actors.WordActor;
import java.util.ArrayList;

import java.util.List;

/**
 * Created by jmoreau on 11/01/16.
 */
public class PlayScreen extends GameScreen {
    private List<Character> proposedChars;
    private WordActor word;
    private int attenpt;
    private int maxAttenpt;
    private Image image;
    private List<TextureRegionDrawable> images = new ArrayList<TextureRegionDrawable>();
    private InputListener inputListener;
    private VerticalGroup keyboardArea;

    private final int difficulty;

    public PlayScreen(final LePendu game, final int difficulty) {
        super(game);

        this.difficulty = difficulty;
        initValues();

        ImageButton menuBtn = new ImageButton(new TextureRegionDrawable(game.atlas.findRegion("menuBtn")));
        menuBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                game.setScreen(new MenuScreen(game));
            }
        });

        ImageButton newBtn  = new ImageButton(new TextureRegionDrawable(game.atlas.findRegion("newBtn")));
        newBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                game.setScreen(new PlayScreen(game, difficulty));
            }
        });

        table.row().height(154).top();
        table.add(menuBtn).pad(45, 65, 0, 0).left();
        table.add().expandX().fillX();
        table.add(newBtn).pad(45, 0, 0, 65).right();

        table.row().height(512);
        table.add(image).colspan(3).padTop(25).padBottom(25);

        table.row().height(80);
        table.add(word).colspan(3).expandX();

        table.row().height(360);
        table.add(keyboardArea).colspan(3).expand();

        inputListener = new InputListener() {
            @Override
            public boolean keyTyped (InputEvent event, char character) {
                if ( ((int) character >= 97) && ((int) character <= 122)
                        || ((int) character >= 65) && ((int) character <= 90)) {
                    proposeLetter(String.valueOf(character).toUpperCase().charAt(0));
                    return true;
                } else {
                    return false;
                }
            }
        };
        stage.addListener(inputListener);
    }

    private void initValues() {
        attenpt         = 0;
        word            = new WordActor(game, difficulty);
        proposedChars   = new ArrayList<Character>();
        keyboardArea    = new KeyboardActor(this);

        switch(difficulty) {
        case 1:
            maxAttenpt = 10;
            images.add(0, new TextureRegionDrawable(game.atlas.findRegion("pendu0")));
            images.add(1, new TextureRegionDrawable(game.atlas.findRegion("pendu1")));
            images.add(2, new TextureRegionDrawable(game.atlas.findRegion("pendu2")));
            images.add(3, new TextureRegionDrawable(game.atlas.findRegion("pendu3")));
            images.add(4, new TextureRegionDrawable(game.atlas.findRegion("pendu4")));
            images.add(5, new TextureRegionDrawable(game.atlas.findRegion("pendu5")));
            images.add(6, new TextureRegionDrawable(game.atlas.findRegion("pendu6")));
            images.add(7, new TextureRegionDrawable(game.atlas.findRegion("pendu7")));
            images.add(8, new TextureRegionDrawable(game.atlas.findRegion("pendu8")));
            images.add(9, new TextureRegionDrawable(game.atlas.findRegion("pendu9")));
            images.add(10, new TextureRegionDrawable(game.atlas.findRegion("pendu10")));
            break;
        case 3:
            maxAttenpt = 6;
            images.add(0, new TextureRegionDrawable(game.atlas.findRegion("pendu2")));
            images.add(1, new TextureRegionDrawable(game.atlas.findRegion("pendu3")));
            images.add(2, new TextureRegionDrawable(game.atlas.findRegion("pendu4")));
            images.add(3, new TextureRegionDrawable(game.atlas.findRegion("pendu5")));
            images.add(4, new TextureRegionDrawable(game.atlas.findRegion("pendu7")));
            images.add(5, new TextureRegionDrawable(game.atlas.findRegion("pendu9")));
            images.add(6, new TextureRegionDrawable(game.atlas.findRegion("pendu10")));
            break;
        default:
            maxAttenpt = 8;
            images.add(0, new TextureRegionDrawable(game.atlas.findRegion("pendu2")));
            images.add(1, new TextureRegionDrawable(game.atlas.findRegion("pendu3")));
            images.add(2, new TextureRegionDrawable(game.atlas.findRegion("pendu4")));
            images.add(3, new TextureRegionDrawable(game.atlas.findRegion("pendu5")));
            images.add(4, new TextureRegionDrawable(game.atlas.findRegion("pendu6")));
            images.add(5, new TextureRegionDrawable(game.atlas.findRegion("pendu7")));
            images.add(6, new TextureRegionDrawable(game.atlas.findRegion("pendu8")));
            images.add(7, new TextureRegionDrawable(game.atlas.findRegion("pendu9")));
            images.add(8, new TextureRegionDrawable(game.atlas.findRegion("pendu10")));
            break;
        }

        image = new Image(images.get(attenpt));
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }

    public void proposeLetter(char character) {
        if (!proposedChars.contains(character)) {
            proposedChars.add(character);

            if (!word.findLetter(character)) {
                attenpt++;
                image.setDrawable(images.get(attenpt));
            } else {
                if (word.isFound()) {
                    stage.removeListener(inputListener);
                    image.setDrawable(new TextureRegionDrawable(game.atlas.findRegion("penduWin")));
                    keyboardArea.clear();
                    keyboardArea.addActor(new Image(new TextureRegionDrawable(game.atlas.findRegion("gagne"))));
                }
            }
        } else if ((difficulty == 3) || ((difficulty == 2) && (!word.findLetter(character)))) {
            attenpt++;
            image.setDrawable(images.get(attenpt));
        }

        if (attenpt >= maxAttenpt) {
            stage.removeListener(inputListener);
            word.printOver();
            keyboardArea.clear();
            keyboardArea.addActor(new Image(new TextureRegionDrawable(game.atlas.findRegion("perdu"))));
        }
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
