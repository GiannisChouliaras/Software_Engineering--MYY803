package text2speechapis;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class FreeTTSAdapter implements TextToSpeechAPI {

    public FreeTTSAdapter()
    {
        System.setProperty("freetts.voices",
                "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        this.voice = voiceManager.getInstance().getVoice("kevin16");
        this.voice.allocate();
    }

    /* Override all the methods of TextToSpeechAPI */

    @Override
    public void play(String string)
    {
        if (voice != null) {
            try {
                voice.speak(string);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        else {
            throw new IllegalStateException("Cannot field voice");
        }
    }

    @Override
    public void setVolume(int volume)
    {
        if (voice != null) {
            //voice.allocate();
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
    public void setPitch(int pitch)
    {
        if (voice != null) {
            //voice.allocate();
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
    public void setRate(int rate)
    {
        if (voice != null) {
            //voice.allocate();
            try {
                voice.setRate(rate);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        else {
            throw new IllegalStateException("Cannot field voice");
        }
    }

    public int getVolume() { return (int) (voice.getVolume() * 100);}
    public int getPitch() { return (int) voice.getPitch(); }
    public int getRate() { return (int) voice.getRate();}

    // no words..
    public String getString() {return "nothing";}

    /**
     * Fields
     */
    private VoiceManager voiceManager;
    private Voice voice;
}
