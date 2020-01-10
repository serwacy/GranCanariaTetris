package Controlers;

import Services.Engine;
import Services.KeyControls;
import Services.MusicPlayer;
import Services.ScoreCounter;
import Shapes.Block;
import Shapes.Shape;
import Tetris.Game;
import Tetris.ShapeFactory;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
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
    private GraphicsContext graphicsContextForBigPane;
    @FXML
    private Canvas canvasForSmallPane;
    private GraphicsContext graphicsContextForSmallPane;

    private Game game;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        ControllerManager.setPlayController(this);
        MusicPlayer.INSTANCE.bindAudioButtonImage(soundButton);
        generateGrid(10, 20, bigPane);
        generateGrid(4, 3, smallPane);
        setGraphics();
        Platform.runLater(this::initGame);
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
            game.fall(); //only one method called
            counter.addScore(1);
        });
        game.startGame();
    }

    @FXML
    private void setScoreLabel(int score) {
        scoreLabel.setText(String.format("%04d", score));
    }

    @FXML
    public void setLevelLabel(int level) {
        levelLabel.setText("LEVEL " + level); // make score at least 6 digit number
    }

    @Override
    public void update(final Observable o, final Object arg) {
        if (o instanceof ScoreCounter) {
            setScoreLabel((int) arg);
        }
        if (o instanceof Engine) {
            setLevelLabel((int) arg);
        }
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
        Platform.runLater(this::showGameOverLabel);
        Timeline timer = new Timeline(
                new KeyFrame(Duration.seconds(2), event -> {
                    goToScoreSaver();
                }
                ));
        timer.play();
    }

    private void showGameOverLabel() {
        this.graphicsContextForBigPane.setFont(new Font("AR CHRISTY", 55));
        this.graphicsContextForBigPane.setFill(Color.DARKSLATEGRAY);
        this.graphicsContextForBigPane.fillText("GAME OVER!", 10, 300);
    }

    private void goToScoreSaver() {
        Platform.runLater(() -> {
            try {
               showScoreSaver(soundButton);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void refresh() {
        clearCanvas();
        printTetrion();
        printCurrentShape();
        printNextShape();
    }

    private void clearCanvas() {
        graphicsContextForBigPane.clearRect(0, 0, canvasForBigPane.getWidth(), canvasForBigPane.getHeight());
        graphicsContextForSmallPane.clearRect(0, 0, canvasForSmallPane.getWidth(), canvasForSmallPane.getHeight());
    }

    private void printTetrion() {
        for (int i = 0; i < game.getTetrion().length; i++) {
            for (int j = 0; j < game.getTetrion()[i].length; j++) {
                if (game.getTetrion()[i][j] != null) {
                    this.graphicsContextForBigPane.setFill(game.getTetrion()[i][j].getColor());
                    this.graphicsContextForBigPane.fillRect(game.getTetrion()[i][j].getX() * 30, game.getTetrion()[i][j].getY() * 30, 30, 30);
                }
            }
        }
    }

    private void printCurrentShape() {
        printShape(game.getCurrentShape(), graphicsContextForBigPane);
    }

    private void printNextShape() {
        printShape(game.getNextShape(), graphicsContextForSmallPane);
    }

    private void printShape(Shape shape, GraphicsContext context) {
        context.setFill(shape.getBlocks().get(0).getColor());
        shape.getBlocks().forEach(block -> context.fillRect(block.getX() * 30, block.getY() * 30, 30, 30));
    }

    private void setGraphics() {
        graphicsContextForBigPane = canvasForBigPane.getGraphicsContext2D();
        graphicsContextForSmallPane = canvasForSmallPane.getGraphicsContext2D();
    }

    private void generateGrid(int width, int height, GridPane pane) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Rectangle rec = new Rectangle();
                rec.setWidth(29);
                rec.setHeight(29);
                rec.setStyle("-fx-fill: rgba(0,0,0,0); -fx-stroke: grey; -fx-stroke-width: 0.5");
                pane.add(rec, i, j);
            }
        }
    }

    public int getScoreValue() {
        return Integer.parseInt(scoreLabel.getText());
    }

    public Canvas getCanvasForBigPane() {
        return canvasForBigPane;
    }
}
