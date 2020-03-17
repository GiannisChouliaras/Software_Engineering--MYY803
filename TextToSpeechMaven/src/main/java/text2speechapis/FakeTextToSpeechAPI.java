package text2speechapis;

public class FakeTextToSpeechAPI implements TextToSpeechAPI {

    /**
     * what exactly is this class ? Fake Text to Speech ?
     * No fields
     */

    /**
     * Constructor of the class FakeTextToSpeechAPI
     */
    public FakeTextToSpeechAPI() {
        //TODO Fill your code HERE
    }

    /* Overriding the methods of TextToSpeechAPI */

    @Override
    public void play(String string) {
        //TODO Fill your code HERE
    }

    @Override
    public void setVolume(int volume) {
        //TODO Fill your code HERE
    }

    @Override
    public void setPitch(int pitch) {
        //TODO Fill your code HERE
    }

    @Override
    public void setRate(int rate) {
        //TODO Fill your code HERE
    }

    @Override
    public int getRate() {
        return 0;
    }

    @Override
    public int getPitch() {
        return 0;
    }

    @Override
    public int getVolume() {
        return 0;
    }
}
