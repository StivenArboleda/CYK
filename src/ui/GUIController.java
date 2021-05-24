package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import model.Grammar;

public class GUIController {
	
	private Grammar grammar;
	
	public GUIController() {
		grammar = new Grammar();
		
	}
	
	@FXML
    private TextField chainTxt;

    @FXML
    private TextArea grammarTxt;

    @FXML
    private Label messageLabel;

    @FXML
    private ImageView img;

    @FXML
    void runCYK(ActionEvent event) {
    	
    }
    
    
    
    
    
}
