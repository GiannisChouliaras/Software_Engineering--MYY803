package FakeToSpeechAPITest;

import static org.junit.jupiter.api.Assertions.*;

import encodingstrategies.EncodingStrategy;
import encodingstrategies.StrategiesFactory;
import jdk.jfr.Description;
import model.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import text2speechapis.FakeTextToSpeechAPI;

class FakeToSpeechAPI {

	@BeforeEach
	void setUp() throws Exception {
		fake = new FakeTextToSpeechAPI();
	}

	@Test
	@DisplayName("Checking if i can set the pitch correctly.")
	void setPitchTest() {
		int i = 50;
		fake.setPitch(i);
		assertEquals(i, fake.getPitch(), "set and get the pitch");
	}

	@Test
	@DisplayName("Checking if i can set the volume correctly.")
	void setVolumeTest(){
		int i = 39;
		fake.setVolume(i);
		assertEquals(i, fake.getVolume(), "set and get the volume");
	}

	@Test
	@DisplayName("Checking if i can set the Rate correctly.")
	@Description("getRate: returns how many words can say per minute.")
	void setRateTest() {
		int i = 150;
		fake.setRate(i);
		assertEquals(i, fake.getRate(), "set and get the Rate. Getting the" +
				"words per minute");
	}

	@Test
	void playTest() {
		String textTest = "testing!";
		Document doc = new Document("Ioannis", "nothing");
		doc.replaceContents(textTest);
		doc.playContentsForTest();
		assertEquals(textTest, doc.playContentsForTest());

		doc.replaceContents(textTest +" John!");
		doc.playContentsForTest();
		assertEquals(textTest + " John!", doc.playContentsForTest());
	}

	@Test
	void encodedTest() {
		Document doc = new Document("Petros", "Zoggi is fun!");
		EncodingStrategy encodingStrategy;
		StrategiesFactory strategiesFactory = new StrategiesFactory();

		String encodedRot13 = "Uryyb zl sevraq";
		String encodedAtBash = "Svool nb uirvmw";
		encodingStrategy = strategiesFactory.createStrategy("Rot13Encoding");
		String text = "Hello my friend";
		doc.replaceContents(text);
		doc.tuneEncodingStrategy(encodingStrategy);
		String encodedText = doc.playEncodedContentsTest();
		assertEquals(encodedRot13, encodedText, "checking Rot13 encoding!");

		encodingStrategy = strategiesFactory.createStrategy("AtBashEncoding");
		doc.tuneEncodingStrategy(encodingStrategy);
		encodedText = doc.playEncodedContentsTest();
		assertEquals(encodedAtBash, encodedText, "checking AtBash encoding!");
	}

	private FakeTextToSpeechAPI fake;

}
