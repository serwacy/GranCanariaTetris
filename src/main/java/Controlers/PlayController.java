package Controlers;

import Services.ScoreCounter;
import Shapes.Block;
import Shapes.Shape;
import Tetris.Game;
import Tetris.ShapeFactory;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlayController extends Controller implements Initializable {


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


    public Canvas getCanvasForBigPane() {
        return canvasForBigPane;
    }

    public GraphicsContext getGraphicsContextForBigPane() {
        return graphicsContextForBigPane;
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        generateGrid(10, 20, bigPane);
        generateGrid(4, 3, smallPane);
        setGraphics();
        ControllerManager.setPlayController(this);
        TEMP_addBlockToTetrion();
    }

    private void setGraphics() {
        graphicsContextForBigPane = canvasForBigPane.getGraphicsContext2D();
        graphicsContextForSmallPane = canvasForSmallPane.getGraphicsContext2D();
    }

    @FXML
    public void setScoreLabel() {
        scoreLabel.setText(String.format("%04d", 236));
    }

    @FXML
    public void onButtonClick(ActionEvent event) {
        try {
            if (event.getSource().equals(stopButton)) {
                //game.endGame();
                showMenu();
            }
            if (event.getSource().equals(pauseButton)) {
                //game.pauseGame();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showMenu() throws IOException {
        prepareScene(stopButton, "Menu.fxml");
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

    public void printTetrion() {
        for (int i = 0; i < Game.getTetrion().length; i++) {
            for (int j = 0; j < Game.getTetrion()[i].length; j++) {
                if (Game.getTetrion()[i][j] != null) {
                    this.graphicsContextForBigPane.setFill(Game.getTetrion()[i][j].getColor());
                    this.graphicsContextForBigPane.fillRect(Game.getTetrion()[i][j].getX() * 30, Game.getTetrion()[i][j].getY() * 30, 30, 30);
                }
            }
        }
    }

    private void printShape(Shape shape, GraphicsContext context){
        context.setFill(shape.getBlocks().get(0).getColor());
        shape.getBlocks().forEach(block -> context.fillRect(block.getX()*30,block.getY()*30,30,30));
    }


    public void printCurrentShape() {
        printShape(game.getCurrentShape(),graphicsContextForBigPane);
    }

    public void printNextShapeOnGrid() {
       printShape(game.getNextShape(),graphicsContextForSmallPane);
    }


    //temp method that adds some block to tetrion - to be deleted
    private void TEMP_addBlockToTetrion() {
        Block b1 = new Block(4, 17, Color.RED);
        Block b2 = new Block(5, 17, Color.RED);
        Block b3 = new Block(5, 18, Color.RED);
        Block b4 = new Block(5, 19, Color.RED);

        game.addBlockToTetrion(b1);
        game.addBlockToTetrion(b2);
        game.addBlockToTetrion(b3);
        game.addBlockToTetrion(b4);

        Block c1 = new Block(7, 17, Color.YELLOW);
        Block c2 = new Block(7, 18, Color.YELLOW);
        Block c3 = new Block(6, 18, Color.YELLOW);
        Block c4 = new Block(6, 19, Color.YELLOW);

        game.addBlockToTetrion(c1);
        game.addBlockToTetrion(c2);
        game.addBlockToTetrion(c3);
        game.addBlockToTetrion(c4);

        Block d1 = new Block(3, 18, Color.MAGENTA);
        Block d2 = new Block(4, 18, Color.MAGENTA);
        Block d3 = new Block(3, 19, Color.MAGENTA);
        Block d4 = new Block(4, 19, Color.MAGENTA);

        game.addBlockToTetrion(d1);
        game.addBlockToTetrion(d2);
        game.addBlockToTetrion(d3);
        game.addBlockToTetrion(d4);
    }
}
