package model.encodingStrategies;

public abstract class TemplateEncoding implements IEncodingStrategy {

    @Override
    public String encode(String line) {
        String encoded = "";
        char ch;
        for (int position = 0; position < line.length(); position ++) {
            ch = mapCharacter(line.charAt(position));
            encoded += ch;
        }
        return encoded;
    }

    public abstract char mapCharacter(char characher);
}
