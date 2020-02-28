package text2speechapis;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class FreeTTSAdapter implements TextToSpeechAPI {

    /**
     * here is the implementation of TextToSpeechAPI
     * must add extended library freeTTS.
     * Fields are private :
     * VoiceManager voiceManager
     * Voice voice.
     */

    private VoiceManager voiceManager;
    private Voice voice;


    /**
     * Constructor for the Class FreeTTSAdapter
     */
    public FreeTTSAdapter() {
        //TODO Fill your code HERE
    }

    /* Override all the methods of TextToSpeechAPI */

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
}
