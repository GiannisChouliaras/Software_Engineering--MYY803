package model.text2speechapis;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class FreeTTSAdapter implements ITextToSpeechAPI {

    private final Voice voice;

    public FreeTTSAdapter() {
        System.setProperty("freetts.voices",
                "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        this.voice = VoiceManager.getInstance().getVoice("kevin16");
        this.voice.allocate();
    }

    @Override
    public void play(String line) {
        if (voice == null) throw new IllegalStateException("Cannot find Voice");
        try {voice.speak(line);}
        catch (Exception e) {e.getCause();}
    }

    @Override
    public void setVolume(int volume) {
        this.voice.setVolume((float) volume);
    }

    @Override
    public void setPitch(int pitch) {
        this.voice.setPitch((float) pitch);
    }

    @Override
    public void setRate(int rate) {
        this.voice.setRate((float) rate);
    }
}
