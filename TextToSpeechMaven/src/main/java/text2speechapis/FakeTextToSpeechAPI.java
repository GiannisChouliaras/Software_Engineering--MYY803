package text2speechapis;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class FakeTextToSpeechAPI implements TextToSpeechAPI {

    /**
     * Constructor of the class FakeTextToSpeechAPI
     */
    public FakeTextToSpeechAPI() {
        System.setProperty("freetts.voices",
                "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        this.voice = voiceManager.getInstance().getVoice("kevin16");
        this.voice.allocate();
    }

    /* Overriding the methods of TextToSpeechAPI */

    @Override
    public void play(String string) {
        if (voice != null) {
            try {
                myString = string;
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        else {
            throw new IllegalStateException("Cannot field voice");
        }
    }

    public String getString() {
        return myString;
    }


    @Override
    public void setVolume(int volume) {
        if (voice != null) {
            try {
                float number = (float) volume / (float) 100;
                voice.setVolume(number);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        else {
            throw new IllegalStateException("Cannot field voice");
        }
    }

    @Override
    public void setPitch(int pitch) {
        if (voice != null) {
            try {
                voice.setPitch((float)pitch);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        else {
            throw new IllegalStateException("Cannot field voice");
        }
    }

    @Override
    public void setRate(int rate) {
        if (voice != null) {
            try {
                voice.setRate((float)rate);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        else {
            throw new IllegalStateException("Cannot field voice");
        }
    }

    @Override
    public int getRate() {
        return (int) voice.getRate();
    }

    @Override
    public int getPitch() {
        return (int) voice.getPitch();
    }

    @Override
    public int getVolume() {
        return (int) (voice.getVolume() * 100);
    }

    private VoiceManager voiceManager;
    private Voice voice;
    private String myString;
}
