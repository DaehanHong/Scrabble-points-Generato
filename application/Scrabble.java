package application;
import java.util.ArrayList;
import java.util.List;

// This class contains logic for Scrabble
public class Scrabble {

	// array of scrabble chars
	private ScrabbleChar[] scrabbleChars;
	private List<String> words; // list of words
 	private int totalPoints; // total points
	
 	
 	// constructor
	public Scrabble() {
		scrabbleChars = ScrabbleChar.initialize();
		words = new ArrayList<>();
		totalPoints = 0;
	}
	
	
	// reset to initial state
	public void reset() {
		for (int i = 0; i < scrabbleChars.length; i++) {
			scrabbleChars[i].reset();
		}
		
		words.clear();
		totalPoints = 0;
	}
	
	
	
	// submit a word
	// returns error if there is any problem, else return null for success
	public String submitWord(String word) {
		word = word.toUpperCase();
		
		// if empty word
		if(word.isEmpty())
			return "Word is blank";
		
		
		// if already used
		if(words.contains(word))
			return "Duplicate word entered";
		
		
		// if short
		if(word.length() < 2)
			return "Word too short (Minimum 2 characters required)";
		
		// if long
		if(word.length() > 8)
			return "Word too long. (Maximum 8 characters allowed)";
		
		// if no vowel
		if(!containsVowel(word))
			return "Word doesn't contain vowel";
		
		
		// if word cannot be formed
		if(!canBeFormed(word))
			return "Word contains letter that is not in enough quantity in bag";
		
		
		// accept word
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			int index = c - 'A';
			scrabbleChars[index].use();
			totalPoints += scrabbleChars[index].getValue();
		}
		
		
		// add to prev words
		words.add(word);
		
		return null;
		
		
	}
	
	
	// GETTERS
	
	
	public ScrabbleChar[] getScrabbleChars() {
		return scrabbleChars;
	}
	
	public List<String> getWords() {
		return words;
	}
	
	
	public int getTotalPoints() {
		return totalPoints;
	}
	
	
	// check if the word contain vowel
	private boolean containsVowel(String word) {
		for (int i = 0; i < word.length(); i++) {
			if("AEIOUY".contains(word.substring(i,i+1)))
				return true;
		}
		
		return false;
	}
	
	
	// check if word can be formed
	private boolean canBeFormed(String word) {
		
		
		// count how many times each character is present in word
		int[] counts = new int[26];
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			
			if(c < 'A' || c > 'Z')
				return false;
			
			int index = c - 'A';
			counts[index]++;
		}
		
		
		
		// compare each character count with the remaining bag
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			int index = c - 'A';
			if(scrabbleChars[index].getRemaining() < counts[index])
				return false;
		}
		
		return true;
	}
	
	// check if game is over
	public boolean isGameOver() {
		// count total remaining letters and vowels remaining
		int remaining = 0;
		int vowelsRem = 0;
		for (int i = 0; i < scrabbleChars.length; i++) {
			if(scrabbleChars[i].getRemaining() > 0) {
				remaining++;
				if(scrabbleChars[i].isVowel())
					vowelsRem++;
			}
		}
		
		// game is over is one or no letter remaining or no vowel is remaining
		return remaining <= 1 || vowelsRem == 0;
	}
	
	
	// get previous words as a comma sepearated string
	public String getPreviousWords() {
		String wordsStr = "";
		for (int i = 0; i < words.size(); i++) {
			wordsStr += words.get(i);
			if(i  != words.size() - 1)
				wordsStr += ", ";
		}
		
		return wordsStr;
	}
	
}
