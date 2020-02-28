package encodingstrategies;

public class Rot13Encoding extends TemplateEncoding {

    /**
     * do we have any Field?
     */

    /**
     * Constructor of the Class Rot13Encoding
     */
    public Rot13Encoding() {
        //TODO Fill your code HERE
    }

    /**
     * @param string
     * overrides the method encode
     */
    @Override
    public String encode(String string) {
        //TODO Fill your code HERE
        return null;
    }

    /**
     * @param ch
     * extends the method mapCharacter of the father class TemplateEncoding
     */
    public char mapCharacter(char ch) {
        //TODO Fill your code HERE
        return 'o';
    }
}
