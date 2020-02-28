package model;
import encodingstrategies.EncodingStrategy;
import text2speechapis.TextToSpeechAPI;

public class Document {
	
	/* private Fields */
	 private EncodingStrategy encodingStrategy;
	 private TextToSpeechAPI audioManager;

	/**
	 * constructor
	 */
	public Document() {
		//TODO fill your code HERE.
	}

	/**
	 * playing the Contents of the document.
	 */
	public void playContents() {
		//TODO fill your code HERE.
	}

	/**
	 * playing the contents of the document in Reverse
	 */
	public void playReverseContents() {
		//TODO fill your code HERE.
	}

	/**
	 * playing the Encoded contents of a document.
	 */
	public void playEncodedContents() {
		//TODO fill your code HERE.
	}

	/**
	 * @param line
	 * play a single line of the contents.
	 */
	public void playLine(int line) {
		//TODO fill your code HERE.
	}

	/**
	 * @param line
	 * play a single line but in Reverse.
	 */
	public void playReversedLine(int line) {
		//TODO fill your code HERE.
	}

	/**
	 * @param line
	 * play an encoded single line of the contents
	 */
	public void playEncodedLine(int line) {
		//TODO fill your code HERE.
	}

	/**
	 * @param encodedStrategy
	 * tune encoding Strategy (?)
	 */
	public void tuneEncodingStrategy(EncodingStrategy encodedStrategy) {
		//TODO fill your code HERE.
	}
}

