package model.encodingStrategies;

public class StrategiesFactory {

    public IEncodingStrategy createStrategy(String strategy) {
        if (strategy.equals("AtBashEncoding")) return new AtBashEncoding();
        if (strategy.equals("Rot13Encoding")) return new Rot13Encoding();
        throw new IllegalArgumentException("Encoding Factory Problem");
    }
}
