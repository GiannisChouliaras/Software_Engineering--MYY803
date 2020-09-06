package model.text2speechapis;

public class Facade {

    private ITextToSpeechAPI freeTTSAdapter;


    public Facade(ITextToSpeechAPI freeTTSAdapter) {
        this.freeTTSAdapter = freeTTSAdapter;
    }

    public void combineVRP(int volume, int rate, int pitch) {
        freeTTSAdapter.setVolume(volume);
        freeTTSAdapter.setRate(rate);
        freeTTSAdapter.setPitch(pitch);
    }
}
