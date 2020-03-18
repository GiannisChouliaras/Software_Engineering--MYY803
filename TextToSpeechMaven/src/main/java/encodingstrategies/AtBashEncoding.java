package encodingstrategies;

public class AtBashEncoding extends TemplateEncoding {

    /**
     * do we have any Field?
     */

    /**
     * Constructor of the Class AtBashEncoding
     */
    public AtBashEncoding() {
        super();
    }

    /**
     * @param string
     * overrides the method encode
     */
    @Override
    public String encode(String string) {
        String encode = super.encode(string);
        return encode;
    }

    /**
     * taking a char, change it with AtBash encoding
     * @param ch
     * an abstract method for mapping Character.
     * @return the encoded char.
     */
    public char mapCharacter(char ch) {
        if (ch >= 'A' && ch <= 'Z') {
            int i = ch;
            i = ch - 'A';
            i = 'Z' - i;
            ch = (char) i;
        }
        else if (ch >= 'a' && ch <= 'z') {
            int i = ch;
            i = ch - 'a';
            i = 'z' - i;
            ch = (char) i;
        }
        return ch;
    }
}