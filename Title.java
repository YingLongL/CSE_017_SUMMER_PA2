/**
 * Abstract class Title to model the entity Title
 * @author  Yinglong Lin    
 * @version Java 11 / VSCode
 * @since   2023-5-28 (date of last revision) 
 */
public abstract class Title{
    // Data members
    private String callN;
    private String title;
    private String publisher;
    private int year;
    private int copies;
    /**
     * Default constructor
     */
    protected Title(){
        this("none", "none", "none", 0, 0);
    }
    /**
     * Constructor with five parameters
     * @param ca initial value for the call number
     * @param ti initial value for the title
     * @param pu initial value for the publisher
     * @param co initial value for the copies
     * @param ye initial value for the year
     */
    protected Title(String ca, String ti, String pu, int ye, int co){
        callN = ca;
        title = ti;
        publisher = pu;
        year = ye;
        copies = co;
    }
    /**
     * Accessor for the call number
     * @return value of the call number
     */
    public String getCallN(){ return callN;}
    /**
     * Accessor for the title
     * @return value of the title
     */
    public String getTitle(){ return title;}
    /**
     * Accessor for the publisher
     * @return value of the publisher
     */
    public String getPublisher(){ return publisher;}
    /**
     * Accessor for the year
     * @return value of the year
     */
    public int getYear(){ return year;}
    /**
     * Accessor for the copies
     * @return value of the copies
     */
    public int getCopies(){ return copies;}
     /**
     * Accessor for the Title attributes
     * @return formatted string with the object attributes
     */
    public String toString(){
        return String.format("%-12s\t%-35s\t%-25s\t%-5d\t%-7d", callN, title, publisher, year, copies);
    }
    /**
     * Accessor for the Title attributes
     * @return simply formatted string with the object attributes
     */
    public String simpleToString(){
        return callN + "|" + title + "|" + publisher + "|" + year + "|" + copies;
    }
    /**
     * Mutator for the call number
     * @param callN value of the call number
     */
    public void setCallN(String callN){
        this.callN = callN;
    }
    /**
     * Mutator for the title
     * @param title value of the title
     */
    public void setTitle(String title){
        this.title = title;
    }
    /**
     * Mutator for the publisher
     * @param publisher value of the publisher
     */
    public void setPublisher(String publisher){
        this.publisher = publisher;
    }
    /**
     * Mutator for the year
     * @param year value of the year
     */
    public void setYear(int year){
        this.year = year;
    }
    /**
     * Mutator for the copies
     * @param copies value of the copies
     */
    public void setCopies(int copies){
        this.copies = copies;
    }

}