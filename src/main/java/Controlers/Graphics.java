package Controlers;

import javafx.scene.canvas.Canvas;

public enum Graphics {
    INSTANCE;

    private PlayController playController;

    public void setPlayController(final PlayController playController) {
        this.playController = playController;
    }

    public PlayController getPlayController() {
        return playController;
    }

    public void refresh() {
        clearCanvas();
        printTetrion();
        printCurrentShape();
        printNextShape();
    }

    private void clearCanvas() {
        Canvas canvas = this.playController.getCanvasForBigPane();
        this.playController.getGraphicsContextForBigPane().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void printTetrion() {
        this.playController.printTetrion();
    }

    private void printCurrentShape() {
        this.playController.printCurrentShape();
    }

    private void printNextShape() {
        this.playController.printNextShapeOnGrid();
    }
}
