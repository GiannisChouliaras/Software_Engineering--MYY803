package encodingstrategies;

public abstract class TemplateEncoding implements EncodingStrategy {

    /**
     * no fields (?)
     */

    /**
     * Constructor for the class TemplateEncoding
     */
    public TemplateEncoding(){
        //TODO Fill your code HERE if needed
    }

    /**
     * @param string
     * implement the encode method of the interface, kids can implement it.
     */
    @Override
    public String encode(String string) {
        //TODO Fill your code HERE
        return null;
    }

    /**
     * @param ch
     * an abstract method for mapping Character.
     * @return char
     */
    public abstract char mapCharacter(char ch);
}
