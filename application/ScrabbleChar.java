package application;
// This class represents a character in scrabble
public class ScrabbleChar {
	
	 // values and start quantity for A-Z
	private static final int[] VALUES = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
	private static final int[] START_BAGS = {9,2,2,4,12,2,3,2,8,1,1,4,2,6,8,2,1,6,4,6,4,2,2,1,2,1};
	

	// character details to store
	private final char letter;
	private final int value;
	private final int startBag;
	private int remaining;

	
	// constructor to create a scrabble char
	private ScrabbleChar(char letter, int value, int startBag) {
		this.letter = letter;
		this.value = value;
		this.startBag = startBag;
		reset();
	}
	
	
	// Getters
	
	public char getLetter() {
		return letter;
	}
	
	public int getValue() {
		return value;
	}
	
	
	public int getRemaining() {
		return remaining;
	}
	
	
	// use a character
	public boolean use() {
		// if not enough
		if(remaining <= 0)
			return false;
		
		remaining--;
		return true;
	}
	
	// reset to start bag
	public void reset() {
		remaining = startBag;
	}
	
	
	// check if letter is vowel
	public boolean isVowel() {
		return "AEIOUY".contains(letter+"");
	}
	
	
	// initialize set of A-Z characters
	public static ScrabbleChar[] initialize() {
		ScrabbleChar[] chars = new ScrabbleChar[26];
		for (int i = 0; i < chars.length; i++) {
			chars[i] = new ScrabbleChar((char) ('A' + i), VALUES[i], START_BAGS[i]);
		}
		
		return chars;
		
	}
	
	
	
}
