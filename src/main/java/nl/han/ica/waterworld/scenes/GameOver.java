package nl.han.ica.waterworld.scenes;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import nl.han.ica.waterworld.Waterworld;
import nl.han.ica.yaeger.engine.entities.entity.text.TextEntity;
import nl.han.ica.yaeger.engine.scene.SceneType;
import nl.han.ica.yaeger.engine.scene.impl.StaticScene;

import java.util.Set;

public class GameOver extends StaticScene {

    private Waterworld waterworld;

    public GameOver(Waterworld waterworld) {
        this.waterworld = waterworld;
    }

    @Override
    public void onInputChanged(Set<KeyCode> input) {
        if (input.contains(KeyCode.ENTER)) {
            waterworld.nextScene(SceneType.INTRO);
        }
    }

    @Override
    public void initializeScene() {
        setBackgroundImage("underwater2.jpg");
        setBackgroundAudio("audio/ocean.mp3");
    }

    @Override
    public void setupScene() {
        super.setupScene();

        var gameOverText = new TextEntity(440, 350, "Game over");
        gameOverText.setFill(Color.VIOLET);
        gameOverText.setFont(Font.font("palatino", FontWeight.BOLD, 60));
        addEntity(gameOverText);
    }
}
