package com.frappagames.pendu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.frappagames.pendu.Screens.MenuScreen;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LePendu extends Game {
    public static int width = 720;
    public static int height = 1280;
    public static final String TITLE = "Le Pendu";
    public SpriteBatch batch;
    public TextureAtlas atlas;

    public List<String> easyWords   = new ArrayList<String>();
    public List<String> normalWords = new ArrayList<String>();
    public List<String> hardWords   = new ArrayList<String>();

    private String line;
    private FileHandle fileHandle;
    private BufferedReader reader;

    @Override
    public void create () {
        atlas = new TextureAtlas(Gdx.files.internal("LePendu.pack"));
        batch = new SpriteBatch();

        try {
            fileHandle  = Gdx.files.internal("dictionnaires/facile.txt");
            reader      = new BufferedReader(fileHandle.reader());
            while ((line = reader.readLine()) != null) easyWords.add(line);

            fileHandle  = Gdx.files.internal("dictionnaires/normal.txt");
            reader      = new BufferedReader(fileHandle.reader());
            while ((line = reader.readLine()) != null) normalWords.add(line);

            fileHandle  = Gdx.files.internal("dictionnaires/difficile.txt");
            reader      = new BufferedReader(fileHandle.reader());
            while ((line = reader.readLine()) != null) hardWords.add(line);
        } catch (IOException e) {
            System.out.println("Chargement des dictionnaires impossible !");
            System.out.println(e.getMessage());
            Gdx.app.exit();
        }

        setScreen(new MenuScreen(this));
    }

    @Override
    public void render () {
        super.render();
    }

    @Override
    public void dispose () {
        super.dispose();
        atlas.dispose();
    }
}
