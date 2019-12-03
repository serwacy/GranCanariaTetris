package Services;

import Controlers.ControllerManager;
import Controlers.PlayController;
import javafx.scene.canvas.Canvas;

public enum Graphics {
    INSTANCE;

//    private PlayController playController = ;

    public void refresh() {
        clearCanvas();
        printTetrion();
        printCurrentShape();
        printNextShape();
    }

    private void clearCanvas() {
        Canvas canvas = ControllerManager.getPlayController().getCanvasForBigPane();
        ControllerManager.getPlayController().getGraphicsContextForBigPane().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void printTetrion() {
        ControllerManager.getPlayController().printTetrion();
    }

    private void printCurrentShape() {
        ControllerManager.getPlayController().printCurrentShape();
    }

    private void printNextShape() {
        ControllerManager.getPlayController().printNextShapeOnGrid();
    }
}
