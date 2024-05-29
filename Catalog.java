import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
/**
 * Class Catalog to model the entity Catalog  
 * @author  Yinglong Lin    
 * @version Java 11 / VSCode
 * @since   2023-5-28 (date of last revision)   
 */
public class Catalog{
    // Data members
    private Title[] titles;
    private int count;
    /**
     * Default constructor
     */
    public Catalog(){
        titles = new Title[50];
        count = 0;
    }
    
    /**
     * read the title file and save in the titles
     * @param filename value for the filename
     */
    public void readTitles(String filename){
        try{
            //STEP 1: Open the file for reading
            File file = new File(filename);
            Scanner readFile = new Scanner(file);

            //Step 2: Read from the file
            for(int i=0; i<50 && readFile.hasNextLine(); i++){
                    String s = readFile.nextLine();//read one line from the scanner
                    String[] t = s.split("\\|");
                    if(t[0].charAt(0) == 'P'){//determine which type title for the new input
                        try{//ensure the month type is correct(if month is a integer type)
                            Periodical p = new Periodical(t[0],t[1],t[2],Integer.valueOf(t[3]),Integer.valueOf(t[4]),Integer.valueOf(t[5]),Integer.valueOf(t[6]));
                            if(addTitle(p)){
                                continue;
                            }else{
                                System.out.println("There is a invalid format Periodical on the line" + (count+1));
                            }
                        }
                        catch(Exception e){//if month is a string type
                            Periodical p = new Periodical(t[0],t[1],t[2],Integer.valueOf(t[3]),Integer.valueOf(t[4]),changeMonth(t[5]),Integer.valueOf(t[6]));
                            if(addTitle(p)){
                                continue;
                            }else{
                                System.out.println("There is a invalid format Periodical on the line" + (count+1));
                            }
                        }
                    }else{
                        Book b = new Book(t[0],t[1],t[2],Integer.valueOf(t[3]),Integer.valueOf(t[4]),t[5],t[6]);
                        if(addTitle(b)){
                            continue;
                        }else{
                            System.out.println("There is a invalid format Book on the line" + (count+1));
                        }
                    }
            }
            //STEP 3: Closing the file
            readFile.close();
        }
        catch(FileNotFoundException e){
            return;
        }
    }

    /**
     * Change the month into a integer type
     * @param m value for the month
     */
    public int changeMonth(String m){//change the String month to the integer month
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        for(int i = 0;i<12;i++){
            if(m.equals(months[i])){
                return i;
            }
        }
        return 0;
    }
    /**
     * saving Titles into the map file
     * @param filename value for the filename
     */
    public void saveTitles(String filename){
        try{
            File file = new File(filename);
            PrintWriter writeFile = new PrintWriter(file);
            for(int i =0; i<count; i++){
                writeFile.print(titles[i].simpleToString() + "\n");
            }
            writeFile.close();    
        }
        catch(FileNotFoundException e){
            System.out.println("Cannot write to the " + filename);
        }
    }
    /**
     * Remove a Title object to the array.
     * @param c The call number of a Title object to be removed.
     * @return true if the Title was successfully removed, false if failed.
     */
    public boolean removeTitle(String c){
        for(int i = 0;i<count;i++){
            if(titles[i].getCallN().equals(c)){
                for(int j = i;j<count-1;j++){
                    titles[j] = titles[j+1];
                }
                titles[count-1] = null;
                count--;
                return true;
            }
        }
        return false;
    }

    /**
     * Accessor for the count
     * @return value of the count
     */
    public int getCount(){
        return count;
    }
    /**
     * Adds a Title object to the collection.
     * @param t The Title object to be added.
     * @return true if the Title was successfully added, false if the collection is full.
     */
    public boolean addTitle(Title t){
        if(count<titles.length){
            titles[count] = t;
            count++;
            return true;
        }else{
            return false;
        }
    }
    /**
     * Finds and returns a Title object with a call number.
     * @param c The call number to search for.
     * @return The Title object with the specified call number or null.
     */
    public Title findCallN(String c){
        for(int i = 0;i<count;i++){
            if(titles[i].getCallN().equals(c)){
                return titles[i];
            }
        }
        return null;
    }
    /**
     * Finds and returns an array of Title objects with a specified title.
     * @param title The title to search for.
     * @return An array of Title objects with the specified title, or null.
     */
    public Title[] findTitle(String title){
        Title[] t = new Title[count];
        int n = 0;
        // Check if the title matches the specified title and recorded in a temporary array 
        for(int i = 0;i<count;i++){
            if(titles[i].getTitle().equals(title)){
                t[n] = titles[i];
                n++;
            }
        }
        // return null if no matches are found
        if(n == 0){
            return null;
        }

        //Copy titles from the temporary array to the result array
        Title[] T = new Title[n];
        for(int i = 0; i<n ; i++){
            T[i] = t[i];
        }
        return T;
    }
    /**
     * Finds and returns an array of Title objects with a specified year.
     * @param year The year to search for.
     * @return An array of Title objects with the specified year, or null.
     */
    public Title[] findYear(int year){
        Title[] t = new Title[count];
        int n = 0;
        // Check if the title matches the specified title and recorded in a temporary array 
        for(int i = 0;i<count;i++){
            if(titles[i].getYear() == year){
                t[n] = titles[i];
                n++;
            }
        }
        // return null if no matches are found
        if(n == 0){
            return null;
        }
        //Copy titles from the temporary array to the result array
        Title[] T = new Title[n];
        for(int i = 0; i<n ; i++){
            T[i] = t[i];
        }
        return T;
    }
    /**
     * Sorts the array of Title objects in ascending order based on the year of publication using insertion sort.
     */
    public void sort(){
        for(int i=1; i<count; i++){
        Title currentVal = titles[i];
        int j = i;
            while(j > 0 && currentVal.getYear() < titles[j-1].getYear()){     
                titles[j] = titles[j-1];
                j--;
		    }
      	titles[j] = currentVal;
    	}
    }
    /**
     * Accessor for the Catalog attributes
     * @return formatted string with the object attributes
     */
    public String toString(){
        String str = String.format("%-12s\t%-35s\t%-25s\t%-5s\t%-7s\t%-15s\t%-10s", "Call Number", "Title", "Publisher", "Year", "#Copies", "Author/Month", "ISBN/Issue");
        for(int i = 0; i<count ; i++){
            str += "\n" + titles[i].toString();
        }
        return str;
    }

}
