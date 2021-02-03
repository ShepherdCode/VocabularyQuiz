import java.util.*;
public class ChapterQuiz {
    String filename;
    FileParser parser;
    String [] words ;
    String [] definitions ;
    final int PAUSE = 1000;   // milliseconds between word and definition

    public static void main (String [] args) {
        int ch = Integer.parseInt(args[0]);
        popQuiz(ch);
    }
    
    public static void popQuiz (int chapter) {
        String filename = "ch"+chapter+".tsv";
        ChapterQuiz quiz = new ChapterQuiz (filename);
        quiz.wordQuiz();
    }

    public ChapterQuiz (String filename) {
        this.filename=filename;
        this.parser = new FileParser (filename);
        words = this.parser.getWords();
        definitions = this.parser.getDefinitions();
        if (parser.error()) {
            System.exit(1);
        }
    }

    public void wordQuiz () {
        int numWords = words.length;
        Random generator = new Random ();
        long time = System.currentTimeMillis();
        generator.setSeed(time);
        String oneWord, oneDef, guess, response;
        boolean more = true;
        Scanner scanner = new Scanner (System.in);
        System.out.println("Pop Quiz based on "+this.filename);
        System.out.println("Type the word or X to exit.");
        while (more) {
            int index = generator.nextInt(numWords);
            oneWord = words[index];
            oneDef = definitions[index];
            System.out.println("Definition: "+oneDef);
            System.out.print("Word? ");
            guess = scanner.next();
            if (guess.toUpperCase().equals("X")) {
                response="BYE";
                more = false;
            } else if (guess.equalsIgnoreCase(oneWord)) {
                response="YES";
            } else {
                response="NOPE";
            }               
            System.out.println(response+"! Answer was "+oneWord+"\n");
        }
    }
}
