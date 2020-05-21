package encodingstrategies;

public class StrategiesFactory {

    public StrategiesFactory() {}

    /**
     * @param string
     * a method were we create a strategy by giving the name and
     * @return an EncodingStrategy
     */
    public EncodingStrategy createStrategy(String string)
    {
        if (string.equals("AtBashEncoding")) {
            return new AtBashEncoding();
        }
        else if (string.equals("Rot13Encoding")) {
            return new Rot13Encoding();
        }
        else throw new IllegalArgumentException("Encoding Factory Problem");
    }
}
