/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frappagames.pendu.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Disposable;
import com.frappagames.pendu.LePendu;
import java.util.Random;

/**
 *
 * @author jmoreau
 */
public class WordActor extends HorizontalGroup implements Disposable {
    private final Texture alphabet;
    private Random random = new Random();
    private String text;
    private int index;
    protected LePendu game;
    private final char guessChars[];

    public WordActor(final LePendu game, final int difficulty) {
        this.game  = game;

        try {
            switch (difficulty) {
                case 1 :
                    index = random.nextInt(game.easyWords.size());
                    text = game.easyWords.get(index);
                    game.easyWords.remove(index);
                    break;
                case 3 :
                    index = random.nextInt(game.hardWords.size());
                    text = game.hardWords.get(index);
                    game.hardWords.remove(index);
                    break;
                default :
                    index = random.nextInt(game.normalWords.size());
                    text = game.normalWords.get(index);
                    game.normalWords.remove(index);
                    break;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Le dictionnaire ne dispose plus de mots !");
            Gdx.app.exit();
        }

        guessChars = new char[text.length()];

        alphabet = new Texture("alphabet.png");

        for (int i = 0; i < text.length() ; i++) {
            addActor(new Image(new TextureRegion(alphabet, 1300, 90, 50, 50)));
            guessChars[i] = '_';
        }
    }

    public boolean findLetter(char character) {
        this.clear();
        char wordChars[] = text.toCharArray();
	boolean isPresent = false;

        for (int i = 0; i < text.length() ; i++) {
            if (wordChars[i] == character) {
                isPresent = true;
                guessChars[i] = character;
            }
        }
        printWord();

        return isPresent;
    }

    public void printWord() {
        for (int i = 0; i < text.length() ; i++) {
            if ((int) guessChars[i] >= 65 && (int) guessChars[i] <= 90){
                this.addActorAt(i, new Image(new TextureRegion(alphabet, 50 * ((int) guessChars[i] - 65), 90, 50, 50)));
            } else {
                this.addActorAt(i, new Image(new TextureRegion(alphabet, 1300, 90, 50, 50)));
            }
        }
    }

    public void printOver() {
        this.clear();
        char wordChars[] = text.toCharArray();
        for (int i = 0; i < text.length() ; i++) {
            this.addActorAt(i, new Image(new TextureRegion(alphabet, 50 * ((int) wordChars[i] - 65), 90, 50, 50)));
        }
    }

    public String getText() {
        return text;
    }

    public boolean isFound() {
        return (text.equals(new String(guessChars)));
    }

    public void dispose() {
        alphabet.dispose();
    }
}
