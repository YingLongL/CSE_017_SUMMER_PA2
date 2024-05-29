/**
 * This class represents the InvalidCallNumber, extending the Exception class.
 * @author  Yinglong Lin    
 * @version Java 11 / VSCode
 * @since   2023-5-28 (date of last revision) 
 */
public class InvalidCallNumber extends Exception{
    // default constructor
    public InvalidCallNumber(){
        super("Invalid Call Number");
    }
    /**
     * Constructor with one parameters
     * @param  Number value for the Number
     */
    public InvalidCallNumber(String Number){
        super(Number);
    }
}