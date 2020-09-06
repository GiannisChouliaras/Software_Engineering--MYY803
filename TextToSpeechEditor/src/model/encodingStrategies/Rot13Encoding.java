package model.encodingStrategies;

public class Rot13Encoding extends TemplateEncoding {

    public Rot13Encoding(){
        super();
    }

    @Override
    public char mapCharacter(char character) {
        if (character >= 'a' && character <= 'm') character += 13;
        else if (character >= 'A' && character <= 'M') character += 13;
        else if (character >= 'n' && character <= 'z') character -= 13;
        else if (character >= 'N' && character <= 'Z') character -= 13;
        return character;
    }
}
