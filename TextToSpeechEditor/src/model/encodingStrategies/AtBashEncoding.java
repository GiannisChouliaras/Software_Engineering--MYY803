package model.encodingStrategies;

public class AtBashEncoding extends TemplateEncoding {

    public AtBashEncoding() {
        super();
    }

    @Override
    public char mapCharacter(char characher) {
        if (characher >= 'A' && characher <= 'Z') {
            int i = characher;
            i = characher - 'A';
            i = 'Z' - i;
            characher = (char) i;
        }
        if (characher >= 'a' && characher <= 'z') {
            int i = characher;
            i = characher - 'a';
            i = 'z' - i;
            characher = (char) i;
        }
        return characher;
    }
}
