package encodingstrategies;

public class Rot13Encoding extends TemplateEncoding {

    /**
     * do we have any Field?
     */

    /**
     * Constructor of the Class Rot13Encoding
     */
    public Rot13Encoding() {
        super();
    }

    /**
     * @param string
     * overrides the method encode
     */
    @Override
    public String encode(String string)
    {
        String encode = super.encode(string);
        return encode;
    }


    /**
     * Taking a char and encode it with Rot13.
     * @param ch
     * an abstract method for mapping Character.
     * @return an encoded char.
     */
    public char mapCharacter(char ch)
    {
        if (ch >= 'a' && ch <= 'm') ch += 13;
        else if (ch >= 'A' && ch <= 'M') ch += 13;
        else if (ch >= 'n' && ch <= 'z') ch -= 13;
        else if (ch >= 'N' && ch <= 'Z') ch -= 13;
        return ch;
    }
}
