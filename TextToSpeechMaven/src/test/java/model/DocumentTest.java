package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DocumentTest {

	@BeforeEach
	void setUp() throws Exception {
		document = new Document("authorTest", "titleTest"); 
	}

	@Test
	@DisplayName("Checking the new Document list")
	void newDocumentTest() {
		/**
		 * Document class was created. We have to check if arrayList is empty.
		 */
		ArrayList<Line> empty = document.getArrayList();
		assertEquals(0, empty.size(), "An empty arrayList means that size is 0.");
	}
	
	
	Document document;

}
