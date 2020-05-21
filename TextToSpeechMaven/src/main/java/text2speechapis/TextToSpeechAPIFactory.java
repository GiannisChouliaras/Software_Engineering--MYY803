package text2speechapis;

public class TextToSpeechAPIFactory {

    public TextToSpeechAPIFactory() {}

    /**
     * @param string
     * method for creating a TTSAPI
     * @returns TextToSpeechAPI
     */
    public TextToSpeechAPI createTTSAPI(String string)
    {
        if (string.equals("FreeTTSAdapter")) {
            return new FreeTTSAdapter();
        } else if (string.equals("FakeTextToSpeechAPI")) {
            return new FakeTextToSpeechAPI();
        } else {
            System.out.println("You should pick the right class.");
        }
        return null;
    }
}
