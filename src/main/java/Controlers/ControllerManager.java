package Controlers;

public class ControllerManager {
    private static PlayController playController;

    public static void setPlayController(final PlayController playController) {
        ControllerManager.playController = playController;
    }

    public static PlayController getPlayController() {
        return playController;
    }
}
