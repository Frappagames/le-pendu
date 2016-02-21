package com.frappagames.pendu.Actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.frappagames.pendu.Screens.PlayScreen;

/**
 * Created by Miridan on 28/01/16.
 */
public class KeyboardActor extends VerticalGroup implements Disposable {
    private HorizontalGroup hg;
    private ImageButton button;
    private final Texture alphabet;

    public KeyboardActor(final PlayScreen playScreen) {
        alphabet = new Texture("alphabet.png");

        for (int i = 0 ; i < 26 ; i++) {
            if (i%7 == 0) {
                hg  = new HorizontalGroup();
                this.addActor(hg);
            }
            final char character = (char) (i + 65);

            button = new ImageButton(new TextureRegionDrawable(new TextureRegion(alphabet, i * 90, 0, 90, 90)));
            button.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                    playScreen.proposeLetter(character);
                }
            });

            hg.addActor(button);
        }
    }

    public void dispose() {
        alphabet.dispose();
    }
}
