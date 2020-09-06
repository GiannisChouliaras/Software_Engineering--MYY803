package model.text2speechapis;

public interface ITextToSpeechAPI {
    void play(String line);
    void setVolume(int volume);
    void setPitch(int pitch);
    void setRate(int rate);
}
