package Tetris;

import Services.Score;
import Services.ScoreCounter;
import Shapes.Shape;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Controller{

    private Game game = new Game();

    @FXML
    private Button playButton;
    @FXML
    private Button creditsButton;
    @FXML
    private Button highScoresButton;
    @FXML
    private Button exitButton;
    @FXML
    private Button backButton;
    @FXML
    private Button stopButton;
    @FXML
    private Button pauseButton;
    @FXML
    private Label highScores = new Label();
    @FXML
    private GridPane smallPane = new GridPane();
    @FXML
    private GridPane bigPane = new GridPane();
    @FXML
    private Canvas canvas = new Canvas();



    // ON BUTTON CLICK METHOD TO BE USED IN ANY CASE OF BUTTON EVENT IN OUR PROGRAM
    public void onButtonClick(ActionEvent event){
        try {
            if (event.getSource().equals(playButton)){
                showGame();
            }
            if (event.getSource().equals(creditsButton)){
                showCredits();
            }
            if (event.getSource().equals(highScoresButton)){
                showScores();
            }
            if (event.getSource().equals(exitButton)){
                exitGame();
            }
            if (event.getSource().equals(backButton)){
                showMenu();
            }
            if (event.getSource().equals(stopButton)){
                fishGame();
            }
            if (event.getSource().equals(pauseButton)){
                pauseGame();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    // BELOW ARE THE METHODS RESPONSIBLE FOR SCENE CHANGING - I KNOW IT IS CODE REPETITION - TO BE FIXED LATER
    public void showGame() throws Exception {
        Stage stage = (Stage) playButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Play.fxml"));
        stage.setScene(new Scene(root,550,850));
        stage.setResizable(false);


        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawShapes(gc);
        bigPane.getChildren().add(canvas);

        this.start(stage);
        stage.show();
    }
    private void drawShapes(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);
        gc.strokeLine(40, 10, 10, 40);
        gc.fillOval(10, 60, 30, 30);
        gc.strokeOval(60, 60, 30, 30);
        gc.fillRoundRect(110, 60, 30, 30, 10, 10);
        gc.strokeRoundRect(160, 60, 30, 30, 10, 10);
    }
    public void showCredits() throws Exception {
        Stage stage = (Stage) creditsButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Credits.fxml"));
        stage.setScene(new Scene(root,550,850));
        stage.setResizable(false);
        stage.show();
    }
    public void showScores() throws Exception {
        Stage stage = (Stage) highScoresButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("HighScores.fxml"));
        stage.setScene(new Scene(root,550,850));
        stage.setResizable(false);
        stage.show();
    }
    public void showMenu() throws Exception {
        Stage stage = (Stage) backButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Menu.fxml"));
        stage.setScene(new Scene(root,550,850));
        stage.setResizable(false);
        stage.show();
    }
    public void fishGame() throws Exception {
        Stage stage = (Stage) stopButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Menu.fxml"));
        stage.setScene(new Scene(root,550,850));
        stage.setResizable(false);
        stage.show();
    }
    public void pauseGame(){
        System.out.println("paused game");
    }
    public void exitGame(){
        Platform.exit();
    }
    //END OF SCENE SETTING

    //INITIALIZER - RELOADED EVERY TIME WE TOUCH THIS CLASS
    @FXML
    private void initialize(){
        highScores.setText(ScoreCounter.getScores());
        this.generateBigPane();
        this.generateSmallPane();
        //this.printShapeOnGrid(smallPane,game.nextShape); HERE BUG BEACUSE SHAPE HAS X AND Y ADJUSTED TO THE MIDDLE OF THE BIG PANE
    }
    //HELP METHODS FOR THE INITIALIZER
    private void generateSmallPane(){
        for (int i = 0; i <= 3; i++) {
            for (int j = 0; j <= 2; j++) {
                Rectangle rec = new Rectangle();
                rec.setWidth(30);
                rec.setHeight(30);
                rec.setStyle("-fx-fill: rgba(0,0,0,0); -fx-stroke: grey; -fx-stroke-width: 0.5");
                smallPane.add(rec,i,j);
            }
        }
    }
    private void generateBigPane(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j <= 20; j++) {
                Rectangle rec = new Rectangle();
                rec.setWidth(30);
                rec.setHeight(30);
                rec.setStyle("-fx-fill: rgba(0,0,0,0); -fx-stroke: grey; -fx-stroke-width: 0.5");
                bigPane.add(rec,i,j);
            }
        }
    }
    private void printShapeOnGrid(GraphicsContext graphicsContext, Shape shape){
        Rectangle r1 = new Rectangle(30,30,shape.getBlocks().get(0).getColor());
        Rectangle r2 = new Rectangle(30,30,shape.getBlocks().get(0).getColor());
        Rectangle r3 = new Rectangle(30,30,shape.getBlocks().get(0).getColor());
        Rectangle r4 = new Rectangle(30,30,shape.getBlocks().get(0).getColor());
        //System.out.println("in print "+shape.getBlocks().get(0).getY());
        //pane.add(r1,game.getCurrentShape().getBlocks().get(0).getX(),game.getCurrentShape().getBlocks().get(0).getY());

    }
    private void printTetrionOnGrid(){

    }
    //END OF HELP METHODS OF THE INITIALIZER
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
}
