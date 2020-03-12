package controller;

import java.util.ArrayList;

import model.Document;

public class Database {

	/**
	 * Constructor.
	 */
	public Database() {
		database = new ArrayList<Document>();
	}
	
	/**
	 * @param author
	 * @param title
	 * @return
	 */
	public Document getDocument(String author, String title)
	{
		for (Document document : database) {
			if (document.getTitle().equals(title) && document.getAuthor().equals(author)) {
				return document;
			}
		}
		System.out.println("No document with this Author and title");
		return null;
	}
	
	/**
	 * 
	 * @param document
	 */
	public void addToDatabase(Document document)
	{
		for (Document document2 : database) {
			if (document.equals(document2)) {
				System.out.println("Database contains this Document");
				return;
			}
		}
		database.add(document);
		
	}

	/**
	 * @param author
	 * @param title
	 * @return
	 */
	public boolean containsDocument(String author, String title)
	{
		for (Document document : database) {
			if (document.getAuthor().equals(author) && document.getTitle().equals(title)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * private fields.
	 */
	private ArrayList<Document> database;
}
