package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import commands.EditDocument;
import jdk.jfr.Description;
import model.Document;

class ControllerTest {

	@BeforeEach
	void setUp() throws Exception {
		controller = new Controller();
	}

	@Test
	@DisplayName("Create a new Document")
	void NewDocumentTest() {
		String author = "Ioannis";
		String title = "Astronomy";
		int volume = 50, pitch = 100, rate = 100;		
		controller.newDocument(author, title, volume, pitch, rate);		
		Document documentTest = controller.getDocument(author, title);
		assertEquals(0, documentTest.getArrayList().size(),
				"ArrayList size should be 0 because we just created that.");
	}

	@Test
	@DisplayName("Edit Document Testing")
	void editDocumentTest()
	{
		Document document   	= null;
		String authorString 	= "Ioannis";
		String titleString 		= "Programming";
		String newAuthorString  = "Dimitrios";
		String newTitleString 	= "Petran o Barbaros";
		String textToChange 	= "Gelaw mexri t anathema";
		
		controller.newDocument("Ioannis", "Programming", 50, 100, 100);
		
		/* testing all editDocument options. 0 -> edit Author 1 -> edit title
		 * 2 -> edit text
		 * edit new Author and check
		 */
		controller.editDocument(authorString, titleString, 0, newAuthorString);
		document = controller.getDocument(newAuthorString, titleString);
		assertEquals(newAuthorString, document.getAuthor(),
				"I am checking if author name is valid");
		
		//edit title and check
		controller.editDocument(newAuthorString, titleString, 1, newTitleString);
		document = controller.getDocument(newAuthorString, newTitleString);
		assertEquals(newTitleString, document.getTitle(),
				"I am checking if title field is valid");
		
		//edit text and check
		controller.editDocument(newAuthorString, newTitleString, 2, textToChange);
		document = controller.getDocument(newAuthorString, newTitleString);
		assertEquals(textToChange, document.getText(),
				"I am checking if text is valid");
		
	}
	
	@Test
	@DisplayName("Testing the second NewDocument used in open Command")
	@Description("If there is a file somewhere in disk but not in db, " +
			"it will create a new Document to put in db.")
	void secondNewDocumentTest()
	{
		String test = "This test is stored in disk. there is no document"
				+ "in db like this. Added first time.";

		controller.secondNewDocument("Ioannis", "Astronomy",
				"14/03/2020 17:03:22", "14/03/2020 18:03:23", test);
		
		//database must contain this document.
		assertTrue(controller.containsDocument("Ioannis", "Astronomy"));
		assertEquals(test, controller.getDocument("Ioannis", "Astronomy").getText());
	}

	@Test
	@DisplayName("Testing the Save Content that we will save in disk.")
	void saveToFileTest() {
		String author = "Ioannis";
		String title  = "Astronomy";
		String test   = "This is a test and I am ready\n" +
				"to save it to a file in disk.\nBye!";

		//create new Document and edit the text, choice = 2.
		controller.newDocument(author, title, 50, 100, 100);
		controller.editDocument(author, title, 2,"");

		String info = controller.saveToFile(author,title, test);
		Document document = controller.getDocument(author,title);
		assertNotNull(info);
		String info2 = info+test;
		assertEquals(info2, info+document.getText());
	}
	
	/** field */
	Controller controller;
}
