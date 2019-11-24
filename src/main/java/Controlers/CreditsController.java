package Controlers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;

public class CreditsController extends Controller {
    @FXML
    private Button backButton;

    @FXML
    public void onButtonClick(ActionEvent event){
        try {
            if (event.getSource().equals(backButton)){
                showMenu();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private void showMenu() throws IOException {
        prepareScene(backButton, "Menu.fxml");
    }
}
