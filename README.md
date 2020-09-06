# Text To Speech 

This project was created for the course MYY803 -- Software Engineering (undergraduate course at the University of Ioannina department of Computer Science and Engineering) 

## Getting Started

This is a simple GUI where you can create some documents and transform them to speech. There are several other choices of interaction such as transform the text or a single line in reverse, encode the text and replaying all the previous commands etc.

### Installing

In this Project we used the library FreeTTS.
FreeTTS is an open source speech synthesis system written entirely in the Java programming language.
Everything is set up and ready to play.

### How to use 

You need to open the project file with IntelliJ or Eclipse and run it.
The GUI of the application will pop up. Then you can create a new Document by providing a name and a title. Now you can add a text and click the Edit Text button (Edit / Edit Text) or use the combination on your keyboard CTRL + S. In this point you can explore the functionality of this app (transform text to speech/in reverse etc). To save/open your document to/from disk you need to click File->Save/open Document.

## Running the tests

Open the project file with IntelliJ or Eclipse. Under the folder src you will find two folders the "main" and the "test". Open the folder test and press right click on the folder "java" and select run all tests (JUnit 5).

### Tests

These tests can ensure that our application is functioning correctly. 

I want to test if the encoding of a text is correct. I have one test method called encodedTest:
```
I create a Document, strategy and encoding objects.
then i define a valid Rot13 and atBash encoded texts of the string "hello".
I call the method with the parameter of "hello" and the method returns the encoded text.
Finally I can use AssertEquals with the valid encoded text and the returned value, 
if the texts are same, my method works fine. Test is green and nice.
```

## Built With

* [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
* [FreeTTS](https://freetts.sourceforge.io)
* [JUnit 5](https://junit.org/junit5/docs/current/user-guide/)
