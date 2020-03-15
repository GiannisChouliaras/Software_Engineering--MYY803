package tests;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import controller.Database;
import model.Document;

class Tests {
	
	private Document document;

	@BeforeEach
	void initialize()
	{
		document = new Document("author", "title");
		
	}
	
	
	@Test
	void createDocumentTest()
	{				
		assertNotNull(document.getDate(), "The dateSting should not be empty");
	
	}
	
	@Test
	void editDocumentTest()
	{
		String oldAuthor = document.getAuthor();
		document.setAuthor("newAuthor");
		String newAuthor = document.getAuthor();
		
		String oldTitle = document.getTitle();
		document.setTitle("newTitle");
		String newTitle = document.getTitle();
		
		String oldModifiedDate = document.getMDate();
		document.giveModifiedDate();
		String newModifiedDate = document.getMDate();
		
		String oldContent = document.getText();
		document.replaceContents("this is \n a test");
		String newContent = document.getText();
		
		assertAll(
				() -> assertNotEquals(oldAuthor, newAuthor, "The author should be differrent"),
				() -> assertNotEquals(oldTitle, newTitle, "The title should be differrent"),
				() -> assertNotEquals(oldModifiedDate, newModifiedDate, "The modified date should be differrent"),
				() -> assertNotEquals(oldContent, newContent, "The content should be differrent")
				);
	
	}
	
	@Test
	void saveDocumentTest()
	{				
		Database database = new Database();
		
		database.addToDatabase(document);
		
		assertTrue(database.containsDocument("author", "title"), "The documnet should be saved in the database");
	
	}
	
	@Test
	void openDocumentTest()
	{				
		document.replaceContents("this is \n a test");
		
		Document openedDoc = new Document(document.getAuthor(), document.getTitle(), document.getDate(), document.getMDate());
		
		assertEquals(document.getText(), openedDoc.getText(), "The content should be the same");
	
	}
	
	@Test
	@Disabled
	void readHoleTextTest()
	{				
		fail("Not yet implemented");
		// TODO
		// create a variable at the FakeTextToSpeechAPI class that holds the hole content (that I want the program to read)
		// and compare it with the the content that is stored to the Document class
	}
	
	@Test
	@Disabled
	void readLineTest()
	{				
		fail("Not yet implemented");
		// TODO
		// create a variable at the FakeTextToSpeechAPI class that holds the content of the line (that I want the program to read)
		// and compare it with the the same line from the A/L that is stored to the Document class
	}

}
