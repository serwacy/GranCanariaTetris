package Controlers;

import Services.*;
import Services.Music.MusicManager;
import Shapes.Block;
import Tetris.Game;
import Tetris.ShapeFactory;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class PlayController extends Controller implements Initializable, Observer {

    @FXML
    private Label scoreLabel;
    @FXML
    private Label levelLabel;
    @FXML
    private Button stopButton;
    @FXML
    private Button soundButton;
    @FXML
    private GridPane smallPane;
    @FXML
    private GridPane bigPane;
    @FXML
    private Canvas canvasForBigPane;
    @FXML
    private Canvas canvasForSmallPane;

    private Game game;
    private Graphics graphics;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        ControllerManager.setPlayController(this);
        MusicManager.INSTANCE.bindAudioButtonImage(soundButton);
        Platform.runLater(this::initGame);
        graphics = new Graphics(canvasForBigPane, canvasForSmallPane);
        graphics.setUpGraphics(bigPane, smallPane);
    }

    private void initGame() {
        ShapeFactory shapeFactory = new ShapeFactory();
        ScoreCounter counter = new ScoreCounter();
        KeyControls controls = new KeyControls();
        Engine engine = new Engine();

        game = Game.builder()
                .tetrion(new Block[10][20])
                .currentShape(shapeFactory.createShape())
                .nextShape(shapeFactory.createShape())
                .controls(controls)
                .counter(counter)
                .engine(engine)
                .shapeFactory(shapeFactory)
                .refresh(this::refresh)
                .build();

        controls.addKeyControls();
        counter.addObserver(this);
        engine.addObserver(this);

        engine.addToOnTick(() -> {
            refresh();
            game.invokeShapeFall(); //only one method called
            counter.addScore(1);
        });
        game.startGame();
    }

    private void refresh() {
        graphics.clearCanvas();
        graphics.printTetrion(game);
        graphics.printCurrentShape(game);
        graphics.printNextShape(game);
    }

    @FXML
    public void onButtonClick(ActionEvent event) {
        if (event.getSource().equals(stopButton)) {
            endGameAndExitToMenu();
        }
    }

    public void endGameAndExitToMenu() {
        refresh();
        game.endGame();
        Platform.runLater(graphics::showGameOverLabel);
        Timeline timer = new Timeline(new KeyFrame(Duration.seconds(2), this::goToScoreSaver));
        timer.play();
    }

    private void goToScoreSaver(ActionEvent event) {
        Platform.runLater(() -> {
            try {
                prepareScene(soundButton, "SaveScore.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void update(final Observable observable, final Object arg) {
        if (observable instanceof ScoreCounter) {
            setScoreLabel((int) arg);
        }
        if (observable instanceof Engine) {
            setLevelLabel((int) arg);
        }
    }

    private void setScoreLabel(int score) {
        scoreLabel.setText(String.format("%04d", score));
    }

    private void setLevelLabel(int level) {
        levelLabel.setText("LEVEL " + level);
    }

    public int getScoreValue() {
        return Integer.parseInt(scoreLabel.getText());
    }

    public Canvas getCanvasForBigPane() {
        return canvasForBigPane;
    }
}
