import java.io.*;
import java.util.Scanner;

public class Assignment1 {
    public static void main(String[] args){
        LexicalAnalyzer analyzer = new LexicalAnalyzer(args[0]);

        /*
        try {
            File inFile = new File(args[0]);
            FileWriter writer = new FileWriter(args[1]);
            Scanner scan = new Scanner(inFile);

            while(scan.hasNextLine()){
                String textLine = scan.nextLine();
                System.out.println(textLine);
            }


            for(int i =0; i <10; i++){
                writer.write(i + "\n");
            }
            writer.close();


        }catch (FileNotFoundException e){
            System.out.println("Input file does not exist, please make sure the file is located in this folder");
        }catch (IOException e){
            System.out.println("Issue with I/O operation has occurred");
        }
        */



    }
}
