package text2speechapis;

public interface TextToSpeechAPI {

    /**
     * Here is an Interface for the following classes
     * FreeTTSAdapter
     * FakeTextToSpeechAPI
     */

    /* Methods to Implement: */

    /**
     * @param string
     * method play for playing the audio ?
     */
    public void play(String string);

    /**
     * @param volume
     * method setVolume
     */
    public void setVolume(int volume);

    /**
     * @param pitch
     * method setPitch
     */
    public void setPitch(int pitch);

    /**
     * @param rate
     * method setRate
     */
    public void setRate(int rate);

    public int getRate();
    public int getPitch();
    public int getVolume();

    public String getString();
}
