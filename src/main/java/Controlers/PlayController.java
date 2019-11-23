package Controlers;

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
import javafx.scene.layout.GridPane;
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
    private Canvas canvasForBigPane;
    private GraphicsContext graphicsContextForBigPane;
    @FXML
    private Canvas canvasForSmallPane;
    private GraphicsContext graphicsContextForSmallPane;

    public Game getGame() {
        return game;
    }

    public Canvas getCanvasForBigPane() {
        return canvasForBigPane;
    }

    public GraphicsContext getGraphicsContextForBigPane() {
        return graphicsContextForBigPane;
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        generateBigPane();
        generateSmallPane();
        graphicsContextForBigPane = canvasForBigPane.getGraphicsContext2D();
        graphicsContextForSmallPane = canvasForSmallPane.getGraphicsContext2D();
        ControllerManager.setPlayController(this);
       // printCurrentShapeOnGrid();
        game.startGame();
    }

//    private void addKeyControls() {
//        Stage stage = (Stage) stopButton.getScene().getWindow();
//        stage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
//            if (event.getCode() == KeyCode.LEFT) {
//                game.getCurrentShape().moveLeft();
//            } else if (event.getCode() == KeyCode.RIGHT) {
//                game.getCurrentShape().moveRight();
//            } else if (event.getCode() == KeyCode.SPACE) {
//                game.getCurrentShape().rotate();
//            }
//        });
//    }

    @FXML
    public void onButtonClick(ActionEvent event){
        try {
            if (event.getSource().equals(stopButton)){
                game.endGame();
                goBackToMainMenu();
            }
            if (event.getSource().equals(pauseButton)){
                game.pauseGame();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void goBackToMainMenu() throws Exception {
        Stage stage = (Stage) stopButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Menu.fxml"));
        stage.setScene(new Scene(root,550,850));
        stage.setResizable(false);
        stage.show();
    }

    private void generateSmallPane(){
        for (int i = 0; i <= 3; i++) {
            for (int j = 0; j <= 3; j++) {
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

    public void printCurrentShapeOnGrid(){
        graphicsContextForBigPane.setFill(game.getCurrentShape().getBlocks().get(0).getColor());
        graphicsContextForBigPane.fillRect(game.getCurrentShape().getBlocks().get(0).getX()*30,game.getCurrentShape().getBlocks().get(0).getY()*30,30,30);
        graphicsContextForBigPane.fillRect(game.getCurrentShape().getBlocks().get(1).getX()*30,game.getCurrentShape().getBlocks().get(1).getY()*30,30,30);
        graphicsContextForBigPane.fillRect(game.getCurrentShape().getBlocks().get(2).getX()*30,game.getCurrentShape().getBlocks().get(2).getY()*30,30,30);
        graphicsContextForBigPane.fillRect(game.getCurrentShape().getBlocks().get(3).getX()*30,game.getCurrentShape().getBlocks().get(3).getY()*30,30,30);
    }
    public void printNextShapeOnGrid(){
        graphicsContextForSmallPane.setFill(game.getNextShape().getBlocks().get(0).getColor());
        graphicsContextForSmallPane.fillRect(game.getNextShape().getBlocks().get(0).getX()*30,game.getNextShape().getBlocks().get(0).getY()*30,30,30);
        graphicsContextForSmallPane.fillRect(game.getNextShape().getBlocks().get(1).getX()*30,game.getNextShape().getBlocks().get(1).getY()*30,30,30);
        graphicsContextForSmallPane.fillRect(game.getNextShape().getBlocks().get(2).getX()*30,game.getNextShape().getBlocks().get(2).getY()*30,30,30);
        graphicsContextForSmallPane.fillRect(game.getNextShape().getBlocks().get(3).getX()*30,game.getNextShape().getBlocks().get(3).getY()*30,30,30);
    }



}
