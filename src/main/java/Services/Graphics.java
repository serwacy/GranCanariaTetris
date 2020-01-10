package Services;

import Shapes.Shape;
import Tetris.Game;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class Graphics {
    private Canvas canvasForBigPane;
    private Canvas canvasForSmallPane;

    private GraphicsContext graphicsContextForBigPane;
    private GraphicsContext graphicsContextForSmallPane;

    public Graphics(final Canvas canvasForBigPane, final Canvas canvasForSmallPane) {
        this.canvasForBigPane = canvasForBigPane;
        this.canvasForSmallPane = canvasForSmallPane;
    }

    public void setUpGraphics(final GridPane bigPane, final GridPane smallPane) {
        generateGrid(10, 20, bigPane);
        generateGrid(4, 3, smallPane);
        setGraphicsContext();
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

    private void setGraphicsContext() {
        graphicsContextForBigPane = canvasForBigPane.getGraphicsContext2D();
        graphicsContextForSmallPane = canvasForSmallPane.getGraphicsContext2D();
    }

    public void showGameOverLabel() {
        this.graphicsContextForBigPane.setFont(new Font("AR CHRISTY", 55));
        this.graphicsContextForBigPane.setFill(Color.DARKSLATEGRAY);
        this.graphicsContextForBigPane.fillText("GAME OVER!", 10, 300);
    }

    public void clearCanvas() {
        graphicsContextForBigPane.clearRect(0, 0, canvasForBigPane.getWidth(), canvasForBigPane.getHeight());
        graphicsContextForSmallPane.clearRect(0, 0, canvasForSmallPane.getWidth(), canvasForSmallPane.getHeight());
    }

    public void printTetrion(final Game game) {
        for (int i = 0; i < game.getTetrion().length; i++) {
            for (int j = 0; j < game.getTetrion()[i].length; j++) {
                if (game.getTetrion()[i][j] != null) {
                    this.graphicsContextForBigPane.setFill(game.getTetrion()[i][j].getColor());
                    this.graphicsContextForBigPane.fillRect(game.getTetrion()[i][j].getX() * 30, game.getTetrion()[i][j].getY() * 30, 30, 30);
                }
            }
        }
    }

    public void printCurrentShape(final Game game) {
        printShape(game.getCurrentShape(), graphicsContextForBigPane);
    }

    public void printNextShape(final Game game) {
        printShape(game.getNextShape(), graphicsContextForSmallPane);
    }

    private void printShape(Shape shape, GraphicsContext context) {
        context.setFill(shape.getBlocks().get(0).getColor());
        shape.getBlocks().forEach(block -> context.fillRect(block.getX() * 30, block.getY() * 30, 30, 30));
    }
}
