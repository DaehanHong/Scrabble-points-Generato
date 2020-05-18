package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.scene.control.Label;

import javafx.scene.control.TextArea;

import javafx.scene.text.Font;

public class ScrabbleGUIController {
	@FXML
	private TextArea txtPreviousWords;
	@FXML
	private TextField txtWord;
	@FXML
	private Button btnSubmit;
	@FXML
	private Label lblLetterA;
	@FXML
	private Label lblLetterB;
	@FXML
	private Label lblLetterC;
	@FXML
	private Label lblLetterD;
	@FXML
	private Label lblLetterE;
	@FXML
	private Label lblLetterF;
	@FXML
	private Label lblLetterG;
	@FXML
	private Label lblLetterH;
	@FXML
	private Label lblLetterI;
	@FXML
	private Label lblLetterJ;
	@FXML
	private Label lblLetterK;
	@FXML
	private Label lblLetterL;
	@FXML
	private Label lblLetterM;
	@FXML
	private Label lblLetterN;
	@FXML
	private Label lblLetterO;
	@FXML
	private Label lblLetterP;
	@FXML
	private Label lblLetterQ;
	@FXML
	private Label lblLetterR;
	@FXML
	private Label lblLetterS;
	@FXML
	private Label lblLetterT;
	@FXML
	private Label lblLetterU;
	@FXML
	private Label lblLetterV;
	@FXML
	private Label lblLetterW;
	@FXML
	private Label lblLetterX;
	@FXML
	private Label lblLetterY;
	@FXML
	private Label lblLetterZ;
	@FXML
	private Font x1;
	@FXML
	private Label lblTotalPoints;
	
	@FXML
	private Label lblError;

	private Scrabble scrabble = new Scrabble();
	private Label[] lblCharacters;

	@FXML
	private void initialize() {
		lblCharacters = new Label[] { lblLetterA, lblLetterB, lblLetterC, lblLetterD, lblLetterE, lblLetterF,
				lblLetterG, lblLetterH, lblLetterI, lblLetterJ, lblLetterK, lblLetterL, lblLetterM, lblLetterN,
				lblLetterO, lblLetterP, lblLetterQ, lblLetterR, lblLetterS, lblLetterT, lblLetterU, lblLetterV,
				lblLetterW, lblLetterX, lblLetterY, lblLetterZ };
		
		ScrabbleChar[] chars = scrabble.getScrabbleChars();
		
		for (int i = 0; i < lblCharacters.length; i++) {
			lblCharacters[i].setText(String.format("%s [%d|%d]", chars[i].getLetter(), chars[i].getValue(), chars[i].getRemaining()));
		}
	}

	@FXML
	private void handleSubmit(ActionEvent event) {
		// submit word
		String message = scrabble.submitWord(txtWord.getText());
		lblError.setText("");
		
	
		if (message != null) // if error
			lblError.setText(message);
		else
			txtWord.setText("");

		// refresh each character pane
		ScrabbleChar[] chars = scrabble.getScrabbleChars();
		
		for (int i = 0; i < lblCharacters.length; i++) {
			if(chars[i].getRemaining() <= 0)
				lblCharacters[i].setText("");
			else
				lblCharacters[i].setText(String.format("%s [%d|%d]", chars[i].getLetter(), chars[i].getValue(), chars[i].getRemaining()));
		}

		txtPreviousWords.setText(scrabble.getPreviousWords());
		
		// set total points label
		lblTotalPoints.setText(scrabble.getTotalPoints() + "");

		// update previous words
		txtPreviousWords.setText(scrabble.getPreviousWords());

		
		// check if game over
		if (scrabble.isGameOver()) {
			lblError.setText("Game Over");
			btnSubmit.setDisable(true);
		}
	}

}
