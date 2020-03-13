package model;

import java.util.ArrayList;

public class Main {

    private static ArrayList<String> document = new ArrayList<>();

    public static void main(String[] args) {
        String doc = "Hello, this is John.\nMy Love.";
        String Jtextarea = "Hello, this is John.\nWhat's up?\nEverything good?";

        String[] split = doc.split("\n");
        for (String string : split) {
            document.add(string);
        }

        algorithm(Jtextarea);
        for (String string : document) {
            System.out.println(string + " ");
        }

    }

    private static void algorithm(String text) {
        String[] content = text.split("\n");
        if (content.length == document.size()) {
            //means its equal
            for (int i = 0; i < content.length; i++) {
                if (content[i].equals(document.get(i))) {
                    System.out.println("one time only\n\n");
                } else {
                    document.set(i,content[i]);
                }
            }
        } else if (content.length > document.size()) {
            for (int i = 0; i < document.size(); i++) {
                if (content[i].equals(document.get(i))) {
                    System.out.println("one time only\n\n");
                } else {
                    document.set(i, content[i]);
                }
            }
            for (int i = document.size(); i < content.length; i++) {
                document.add(content[i]);
            }
        } else {
            for (int i = 0; i < content.length; i++) {
                if (content[i].equals(document.get(i))) {
                    continue;
                } else {
                    document.set(i, content[i]);
                }
            }
            for (int i = document.size() -1; i >= content.length; i--) {
                document.remove(i);
            }
        }
    }

}
