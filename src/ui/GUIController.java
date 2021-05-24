package ui;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import customExceptions.EmptyFieldsException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.CYK;
import model.Grammar;

public class GUIController {

	@FXML
	private TextField chainTxt;

	@FXML
	private TextArea grammarTxt;

	@FXML
	private Label messageLabel;

	@FXML
	private ImageView img;

	private Grammar grammar;

	public GUIController() {
		grammar = new Grammar();

	}

	@FXML
	void runCYK(ActionEvent event) {
		try {

			if (grammarTxt.getText() == null || grammarTxt.getText().equals("")) {
				throw new EmptyFieldsException();
				
			}
			
			if (chainTxt.getText() == null || chainTxt.getText().equals("")) {
				throw new EmptyFieldsException();
				
			}
			
			grammar.fillGrammar(setFormat(grammarTxt.getText()));
			CYK cyk = new CYK(grammar);

			if (cyk.isCYK(chainTxt.getText())) {
				messageLabel.setText("THE GIVEN CHAIN CAN BE GENERATED FROM THE GIVEN GRAMMAR");
				setImages(1);
				
			}else {
				messageLabel.setText("THE GIVEN CHAIN CANNOT BE GENERATED FROM THE GIVEN GRAMMAR");
				setImages(3);
				
			}
			
			

		} catch (EmptyFieldsException e) {
			JOptionPane.showMessageDialog(null, "EMPTY FIELDS",null, JOptionPane.WARNING_MESSAGE);
			messageLabel.setText("EMPTY FIELDS, PLEASE FILL ALL THE TEXT AREAS.");
			setImages(4);

		} catch (Exception e) {
			messageLabel.setText("THE GIVEN FORMAT OF THE CHAIN AND/OR THE GRAMMAR  IS INCORRECT.");
			setImages(2);

		}

		
	}

	private void setImages(int i) {
		switch (i) {
		case 1:
			Image image1 = new Image(getClass().getResourceAsStream("img/accepted.jpeg"));
			this.img.setImage(image1);

			break;

		case 2:
			Image image2 = new Image(getClass().getResourceAsStream("img/incorrect.jpeg"));
			this.img.setImage(image2);
			break;

		case 3:
			Image image3 = new Image(getClass().getResourceAsStream("img/rejected.jpeg"));
			this.img.setImage(image3);
			break;

		default:
			Image image4 = new Image(getClass().getResourceAsStream("img/waiting.jpeg"));
			this.img.setImage(image4);
			break;
		}

	}
	
	private String setFormat(String rawGrammar) {
		String finalGrammar = "";
		String[] lines = rawGrammar.split("\n");
		
		for (int i = 0; i < lines.length; i++) {
			StringTokenizer grammarSeparator = new StringTokenizer(lines[i], "->");
			String individualVars = grammarSeparator.nextToken().trim();
			
			finalGrammar += individualVars;
			
			String productions = grammarSeparator.nextToken();
			
			grammarSeparator = new StringTokenizer(productions.trim(), "|");
			
			while (grammarSeparator.hasMoreTokens()) {
				finalGrammar += " " + grammarSeparator.nextToken();
			}
			
			if (i != lines.length - 1) {
				finalGrammar += "\n";
			}
			
		}

		return finalGrammar;
	}

}
