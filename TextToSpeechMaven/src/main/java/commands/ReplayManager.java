package commands;

import controller.Controller;
import encodingstrategies.EncodingStrategy;
import encodingstrategies.StrategiesFactory;
import model.Document;

import java.util.ArrayList;


public class ReplayManager {

    /**
     * Constructor of the class ReplayManager
     */
    public ReplayManager(Controller controller)
    {
        this.controller = controller;
        datas = new ArrayList<ArrayList<String>>();
        strategiesFactory = new StrategiesFactory();
    }

    /**
     * method replay
     */
    public void replay()
    {
        for(ArrayList<String> data : datas) {
            if (data.get(0).equals("DocumentToSpeech")) {
                Document myDoc;
                myDoc = controller.getDocument(data.get(1), data.get(2));
                myDoc.replaceContents(data.get(3));
                myDoc.playContents();
            }
            else if (data.get(0).equals("ReverseToSpeech")) {
                Document myDoc;
                myDoc = controller.getDocument(data.get(1), data.get(2));
                myDoc.replaceContents(data.get(3));
                myDoc.playReverseContents();
            }
            else if (data.get(0).equals("LineToSpeech")) {
                Document myDoc;
                myDoc = controller.getDocument(data.get(1), data.get(2));
                myDoc.replaceContents(data.get(3));
                int line = Integer.parseInt(data.get(4));
                myDoc.playLine(line);
            }
            else if (data.get(0).equals("RevLineToSpeech")) {
                Document myDoc;
                myDoc = controller.getDocument(data.get(1), data.get(2));
                myDoc.replaceContents(data.get(3));
                int line = Integer.parseInt(data.get(4));
                myDoc.playReversedLine(line);
            }
            else if (data.get(0).equals("AtBash")) {
                EncodingStrategy encodingStrategy = strategiesFactory.createStrategy("AtBashEncoding");
                Document myDoc;
                myDoc = controller.getDocument(data.get(1), data.get(2));
                myDoc.replaceContents(data.get(3));
                myDoc.tuneEncodingStrategy(encodingStrategy);
                myDoc.playEncodedContents();
            }
            else if (data.get(0).equals("AtBashLine")) {
                EncodingStrategy encodingStrategy = strategiesFactory.createStrategy("AtBashEncoding");
                Document myDoc;
                myDoc = controller.getDocument(data.get(1), data.get(2));
                myDoc.replaceContents(data.get(3));
                myDoc.tuneEncodingStrategy(encodingStrategy);
                int line = Integer.parseInt(data.get(4));
                myDoc.playEncodedLine(line);
            }
            else if (data.get(0).equals("Rot13")) {
                EncodingStrategy encodingStrategy = strategiesFactory.createStrategy("Rot13Encoding");
                Document myDoc;
                myDoc = controller.getDocument(data.get(1), data.get(2));
                myDoc.replaceContents(data.get(3));
                myDoc.tuneEncodingStrategy(encodingStrategy);
                myDoc.playEncodedContents();
            }
            else if (data.get(0).equals("Rot13Line")) {
                EncodingStrategy encodingStrategy = strategiesFactory.createStrategy("Rot13Encoding");
                Document myDoc;
                myDoc = controller.getDocument(data.get(1), data.get(2));
                myDoc.replaceContents(data.get(3));
                myDoc.tuneEncodingStrategy(encodingStrategy);
                int line = Integer.parseInt(data.get(4));
                myDoc.playEncodedLine(line);
            }
        }
    }

    public void addToList(String command, String author, String title, String text)
    {
        ArrayList<String> currentData = new ArrayList<String>();
        currentData.add(command);
        currentData.add(author);
        currentData.add(title);
        currentData.add(text);

        if (datas.contains(currentData)) {
            System.out.println("Already in");
            return;
        }
        datas.add(currentData);
        //System.out.println(datas.size());
    }

    public void addToList(String command, String author, String title, String text, int line)
    {
        ArrayList<String> currentData = new ArrayList<String>();
        currentData.add(command);
        currentData.add(author);
        currentData.add(title);
        currentData.add(text);
        currentData.add(Integer.toString(line));

        if (datas.contains(currentData)) {
            System.out.println("Already in");
            return;
        }
        datas.add(currentData);
    }

    public void clearData()
    {
        datas.clear();
    }
    /** Fields */
    private Controller controller;
    private ArrayList<ArrayList<String>> datas;
    private StrategiesFactory strategiesFactory;
}
