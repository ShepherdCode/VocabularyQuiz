import java.util.*;
import java.io.*;
/**
 * Parse a text file into arrays of words and definitions.
 */
public class FileParser {
    String filename;
    String [] words;
    String [] definitions;
    boolean error;

    /**
     * Constructor. 
     * @param A valid filename or path+filename.
     */
    public FileParser (String filename) {
        this.filename=filename;
        this.error=false;
    }

    /**
     * Get array of words and phrases.
     * @return Array of words or phrases in same order as definitions.
     * @see #getDefinitions
     */
    public String [] getWords () {
        if (words==null) {
            loadArrays();
        }
        return words;
    }

    /**
     * Get array of definitions.
     * @return Array of sentences in same order as words.
     * @see #getWords
     */
    public String [] getDefinitions () {
        if (definitions==null) {
            loadArrays();
        }
        return definitions;
    }

    /**
     * Was there an I/O error?
     * @return true if there was a problem reading the file.
     */
    public boolean error () {
        return this.error;
    }
    
    int countLines () {
        int lines = 0;
        try {
            File handle = new File(filename);
            Scanner scanner = new Scanner(handle);
            while (scanner.hasNextLine()) {
                scanner.nextLine();
                lines++;
            }
        } catch (Exception e) {
            System.err.println("Cannot open file "+filename);
            System.err.println(e);
            this.error=true;
        }
        return lines;
    }

    void loadArrays () {
        int lines = countLines();
        words = new String [lines];
        definitions = new String [lines];
        try {
            File handle = new File(filename);
            Scanner scanner = new Scanner(handle);
            int line = 0;
            String oneLine;
            while (scanner.hasNextLine()) {
                oneLine = scanner.nextLine();
                String[] split = oneLine.split("\t");
                if (split.length==2) {
                    words[line]=split[0];
                    definitions[line]=split[1];
                    line++;
                } else {
                    System.err.println("Cannot parse this text: "+oneLine);
                    this.error=true;
                }
            }
        } catch (Exception e) {
            System.err.println("Cannot open file "+filename);
            System.err.println(e);
            this.error=true;
        }        
    }
}
