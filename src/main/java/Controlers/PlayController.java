package Controlers;

import Shapes.Shape;
import Tetris.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PlayController implements Initializable {

    private Game game = new Game();

    @FXML
    private Button stopButton;
    @FXML
    private Button pauseButton;
    @FXML
    private GridPane smallPane;
    @FXML
    private GridPane bigPane;
    @FXML
    private Canvas canvas;
    private GraphicsContext gc;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        generateBigPane();
        generateSmallPane();
        gc = canvas.getGraphicsContext2D();
        printCurrentShapeOnGrid(gc);
    }
    public void start(Stage primaryStage) throws Exception {
        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.LEFT) {
                //game.currentShape.moveLeft();
                System.out.println("before "+game.getCurrentShape().getBlocks().get(0).getY());
                game.getCurrentShape().getBlocks().get(0).setY(7);
                System.out.println("im here");
                System.out.println("after "+game.getCurrentShape().getBlocks().get(0).getY());
            } else if (event.getCode() == KeyCode.RIGHT) {
                game.getCurrentShape().moveRight();
            } else if (event.getCode() == KeyCode.SPACE) {
                game.getCurrentShape().rotate();
            }
        });
    }

    @FXML
    public void onButtonClick(ActionEvent event){
        try {
            if (event.getSource().equals(stopButton)){
                finishGame();
            }
            if (event.getSource().equals(pauseButton)){
                pauseGame();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void finishGame() throws Exception {
        Stage stage = (Stage) stopButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Menu.fxml"));
        stage.setScene(new Scene(root,550,850));
        stage.setResizable(false);
        stage.show();
    }
    public void pauseGame(){
        System.out.println("paused game");
    }

    private void generateSmallPane(){
        for (int i = 0; i <= 3; i++) {
            for (int j = 0; j <= 2; j++) {
                Rectangle rec = new Rectangle();
                rec.setWidth(29);
                rec.setHeight(29);
                rec.setStyle("-fx-fill: rgba(0,0,0,0); -fx-stroke: grey; -fx-stroke-width: 0.5");
                smallPane.add(rec,i,j);
            }
        }
    }
    private void generateBigPane(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j <= 20; j++) {
                Rectangle rec = new Rectangle();
                rec.setWidth(29);
                rec.setHeight(29);
                rec.setStyle("-fx-fill: rgba(0,0,0,0); -fx-stroke: grey; -fx-stroke-width: 0.5");
                bigPane.add(rec,i,j);
            }
        }
    }

    private void printCurrentShapeOnGrid(GraphicsContext graphicsContext){
        graphicsContext.setFill(game.getCurrentShape().getBlocks().get(0).getColor());
        //System.out.println("in print "+shape.getBlocks().get(0).getY());
        graphicsContext.fillRect(game.getCurrentShape().getBlocks().get(0).getX()*30,game.getCurrentShape().getBlocks().get(0).getY()*30,30,30);
        graphicsContext.fillRect(game.getCurrentShape().getBlocks().get(1).getX()*30,game.getCurrentShape().getBlocks().get(1).getY()*30,30,30);
        graphicsContext.fillRect(game.getCurrentShape().getBlocks().get(2).getX()*30,game.getCurrentShape().getBlocks().get(2).getY()*30,30,30);
        graphicsContext.fillRect(game.getCurrentShape().getBlocks().get(3).getX()*30,game.getCurrentShape().getBlocks().get(3).getY()*30,30,30);
            //graphicsContext.fillRect(0,0, 30,30);

    }
}
