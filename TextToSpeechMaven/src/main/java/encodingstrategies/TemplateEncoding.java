package encodingstrategies;

public abstract class TemplateEncoding implements EncodingStrategy {

    /**
     * Constructor for the class TemplateEncoding
     */
    public TemplateEncoding(){}

    /**
     * @param string
     * implement the encode method of the interface, kids can implement it.
     */
    @Override
    public String encode(String string)
    {
        String encoded ="";
        char ch;
        for (int i = 0; i < string.length(); i++) {
            ch = mapCharacter(string.charAt(i));
            encoded += ch;
        }
        return encoded;
    }

    /**
     * @param ch
     * an abstract method for mapping Character.
     * @return char
     */
    public abstract char mapCharacter(char ch);
}
