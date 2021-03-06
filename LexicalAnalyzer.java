import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class LexicalAnalyzer {
    private Stack<Token> tokenStack = new Stack<>();

    public LexicalAnalyzer(String fileName){
        File inFile = new File(fileName);
        findToken(inFile);
    }
    public Token getToken(){

        return new Token(Symbol.NAL,"identifier",1234);
    }

    private void findToken(File inFile){
        try {
            Scanner scan = new Scanner(inFile);
            while(scan.hasNextLine()){
                String textLine = scan.nextLine();
                System.out.println(textLine);
            }
        }catch (FileNotFoundException e) {
            System.out.println("Input file does not exist, please make sure the file is located in this folder");
        }
    }
}
