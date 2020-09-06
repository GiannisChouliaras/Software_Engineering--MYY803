package model.text2speechapis;

public class TextToSpeechAPIFactory {

    public ITextToSpeechAPI createTTSAPI(String api) {
        if (api.equals("FreeTTSAdapter")) return new FreeTTSAdapter();
        if (api.equals("FakeTextToSpeechAPI")) return new FakeTextToSpeechAPI();
        throw new IllegalArgumentException("TextToSpeech Factory Problem");
    }
}
