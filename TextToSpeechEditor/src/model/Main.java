package model;

public class Main {

    public static void main(String[] args) {
        Document document = new Document("Giannis", "Astronomy");
        String myTextString = "Hello this is John.\nToday I will talk about\nAstronomy.";

        document.setListFromText(myTextString);
        document.playContents();
        //document.playReversedLine(1);
    }

}
