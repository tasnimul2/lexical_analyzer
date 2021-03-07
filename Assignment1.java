import java.io.*;


public class Assignment1 {
    public static void main(String[] args){
        try{
            LexicalAnalyzer analyzer = new LexicalAnalyzer(args[0]);
            FileWriter writer = new FileWriter("output1.txt");
            while(true){
                Token tkn = analyzer.getToken();
                if(tkn.getSym() == Symbol.EOI){
                    writer.write( tkn.getSym() + ", " + tkn.getId() + "\n");
                    break;
                }else if(tkn.getSym() == Symbol.NUMLIT){
                    writer.write( tkn.getSym() + ", " + tkn.getNum() + "\n");
                } else{
                    writer.write( tkn.getSym() + ", " + tkn.getId() +"\n");
                }

            }
            writer.close();
        }catch (IOException e){
            System.out.println("IOException has occurred");
        }catch (Exception e){
            System.out.println("Exception in the main method");
        }
    }
}
