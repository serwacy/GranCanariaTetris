package Controlers;

import Services.Engine;
import Services.GameComponent;
import Services.KeyControls;
import Services.ScoreCounter;
import Tetris.Game;
import Tetris.ShapeFactory;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;

public class MainMenuController extends Controller {
    @FXML
    private Button playButton;
    @FXML
    private Button creditsButton;
    @FXML
    private Button highScoresButton;
    @FXML
    private Button exitButton;

    @FXML
    public void onButtonClick(ActionEvent event) {
        try {
            if (event.getSource().equals(playButton)) {
                showGame();
            }
            if (event.getSource().equals(creditsButton)) {
                showCredits();
            }
            if (event.getSource().equals(highScoresButton)) {
                showScores();
            }
            if (event.getSource().equals(exitButton)) {
                exitProgram();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // BELOW ARE THE METHODS RESPONSIBLE FOR SCENE CHANGING - I KNOW IT IS CODE REPETITION - TO BE FIXED LATER
    private void showGame() throws IOException {
        prepareScene(playButton, "Play.fxml");
        initGame();//is this a good place to create game?
    }

    private void showCredits() throws IOException {
        prepareScene(creditsButton, "Credits.fxml");
    }

    private void showScores() throws IOException {
        prepareScene(highScoresButton, "HighScores.fxml");
    }

    private void exitProgram() {
        Platform.exit();
    }
    private void initGame(){
        ShapeFactory shapeFactory = new ShapeFactory();
        ScoreCounter counter = new ScoreCounter();
        Engine engine = new Engine(shapeFactory.createShape());
        KeyControls controls = new KeyControls(ControllerManager.getPlayController());

        Game game = Game.builder().controls(controls).counter(counter).engine(engine).shapeFactory(shapeFactory).build();
        game.startGame();
    }
}
