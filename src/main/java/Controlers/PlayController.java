package Controlers;

import Services.Engine;
import Services.KeyControls;
import Services.ScoreCounter;
import Shapes.Block;
import Shapes.Shape;
import Tetris.Game;
import Tetris.ShapeFactory;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class PlayController extends Controller implements Initializable, Observer {

    @FXML
    private Label scoreLabel;
    @FXML
    private Button stopButton;
    @FXML
    private Button pauseButton;
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

    public Canvas getCanvasForBigPane() {
        return canvasForBigPane;
    }

    public GraphicsContext getGraphicsContextForBigPane() {
        return graphicsContextForBigPane;
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        ControllerManager.setPlayController(this);
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
                .lastNumberOfLinesCleared(0)
                .build();

        controls.addKeyControls();
        counter.addObserver(this);

        engine.addToOnTick(() -> {
            refresh();
            game.fall(); //only one method called
            counter.addScore(1);
        });
        game.startGame();
    }

    @FXML
    public void setScoreLabel(int score) {
        scoreLabel.setText(String.format("%04d", score)); // make score at least 6 digit number
    }

    @Override
    public void update(final Observable o, final Object arg) {
        setScoreLabel((int) arg);
    }

    @FXML
    public void onButtonClick(ActionEvent event) {
        if (event.getSource().equals(stopButton)) {
            endGameAndExitToMenu();
        }
        if (event.getSource().equals(pauseButton)) {
            //game.pauseGame();
        }
    }

    public void endGameAndExitToMenu(){
        refresh();
        game.endGame();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        showScoreSaver();
    }


    private void showScoreSaver() {
        Platform.runLater(() -> {
            try {
                prepareScene(pauseButton, "SaveScore.fxml");
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
    public int getScoreValue(){
        return Integer.parseInt(scoreLabel.getText());
    }
}
