import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;


public class LexicalAnalyzer {
    private LinkedList<Token> tokenQueue = new LinkedList<>();
    private HashMap<String,Symbol> symbolList = new HashMap<>();

    public LexicalAnalyzer(String fileName){
        File inFile = new File(fileName);
        loadSymbolList();
        findTokens(inFile);
    }
    public Token getToken(){
        if(hasToken()){
            return tokenQueue.poll();
        }else{
            tokenQueue.add(new Token(Symbol.EOI,"End of Input",0));
        }
        return tokenQueue.poll();
    }

    private void loadSymbolList(){
        symbolList.put("(",Symbol.LPAREN);
        symbolList.put(")",Symbol.RPAREN);
        symbolList.put("++",Symbol.POSTINC);
        symbolList.put("--",Symbol.POSTDEC);
        symbolList.put("*",Symbol.MULT);
        symbolList.put("/",Symbol.DIV);
        symbolList.put("%", Symbol.MOD);
        symbolList.put("+",Symbol.PLUS);
        symbolList.put("-",Symbol.MINUS);
        symbolList.put("<<",Symbol.SLL);
        symbolList.put(">>",Symbol.SRA);
        symbolList.put(">>>",Symbol.SRL);
        symbolList.put("&",Symbol.BITAND);
        symbolList.put("^",Symbol.BITXOR);
        symbolList.put("!",Symbol.BITOR);
        symbolList.put(":=",Symbol.BECOMES);
        symbolList.put(";",Symbol.SEMICOLON);


    }

    private void findTokens(File inFile){
        try {
            Scanner scan = new Scanner(inFile);
            String identifier ="";
            while(scan.hasNextLine()){
                String textLine = scan.nextLine() + " ";

                for(int i = 0; i < textLine.length();i++){
                    String lexeme = String.valueOf(textLine.charAt(i));
                    if(i != textLine.length()-1 && isNumber(lexeme) ){
                        while(i!= textLine.length()-1 &&  isNumber(String.valueOf(textLine.charAt(i)))){
                            identifier+= textLine.charAt(i);
                            ++i;
                        }
                        if(needsTrimming(identifier)){
                            identifier= trim(identifier,10);
                        }
                        tokenQueue.add(new Token(Symbol.NUMLIT,identifier,Long.parseLong(identifier)));
                        identifier = "";
                    }
                    lexeme = String.valueOf(textLine.charAt(i));
                    if(i != textLine.length()-1 && isAlphabet(lexeme) && (isAlphabet(String.valueOf(textLine.charAt(i+1))) || isNumber(String.valueOf(textLine.charAt(i+1))) ) ){
                        while(i!= textLine.length()-1 && isAlphabet(String.valueOf(textLine.charAt(i))) || isNumber(String.valueOf(textLine.charAt(i)))){
                            identifier+= textLine.charAt(i);
                            ++i;
                        }
                        if(needsTrimming(identifier)){
                            identifier = trim(identifier,20);
                        }
                        tokenQueue.add(new Token(Symbol.IDENT,identifier,0));
                        identifier = "";
                    }
                    lexeme = String.valueOf(textLine.charAt(i));
                    if(i != textLine.length()-1 && isAlphabet(lexeme) && !(isAlphabet(""+textLine.charAt(i+1)) || isNumber(""+textLine.charAt(i+1)) )){
                        tokenQueue.add(new Token(Symbol.IDENT,lexeme,0));
                    }else if(symbolList.containsKey(lexeme)&& i != textLine.length()-1 && lexeme.equals("+")  && textLine.charAt(i+1) == '+'){
                        tokenQueue.add(new Token(symbolList.get("++"),"++",0));
                        ++i;
                    }else if(symbolList.containsKey(lexeme)&& i != textLine.length()-1 && lexeme.equals("-")  && textLine.charAt(i+1) == '-'){
                        tokenQueue.add(new Token(symbolList.get("--"),"--",0));
                        ++i;
                    }else if(i != textLine.length()-1 && lexeme.equals("<")  && textLine.charAt(i+1) == '<'){
                        tokenQueue.add(new Token(symbolList.get("<<"),"<<",0));
                        ++i;
                    }else if(i != textLine.length()-2 && lexeme.equals(">")  && textLine.charAt(i+1) == '>' && textLine.charAt(i+2) == '>'){
                        tokenQueue.add(new Token(symbolList.get(">>>"),">>>",0));
                        i+=2;
                    }else if(i != textLine.length()-1 && lexeme.equals(">")  && textLine.charAt(i+1) == '>' ) {
                        tokenQueue.add(new Token(symbolList.get(">>"),">>",0));
                        ++i;
                    }else if(i != textLine.length()-1 && lexeme.equals(":")  && textLine.charAt(i+1) == '=' ){
                        tokenQueue.add(new Token(symbolList.get(":="),":=",0));
                        ++i;
                    }
                    else if(symbolList.containsKey(lexeme)){
                        tokenQueue.add(new Token(symbolList.get(lexeme),lexeme,0));
                    }else if(!lexeme.equals(" ")){
                        tokenQueue.add(new Token(Symbol.NAL,lexeme,0));
                    }
                }
            }
        }catch (FileNotFoundException e) {
            System.out.println("Input file does not exist, please make sure the file is located in this folder");
        }catch (Exception e){
            System.out.println("an Exception occurred in the findToken method");
        }
    }

    private boolean hasToken(){
        if(tokenQueue.isEmpty()){
            return false;
        }
        return true;
    }

    private boolean isAlphabet(String input){
        char temp = input.charAt(0);
        if((temp >= 'a' && temp <= 'z') || (temp >= 'A' && temp <= 'Z')){
            return true;
        }
        return false;
    }
    private boolean isNumber(String input){
        try{
            long isNum = Long.parseLong(input);
        }catch(NumberFormatException e){
            return false;
        }catch (Exception e){
            return  false;
        }
        return true;
    }

    private boolean needsTrimming(String input){
        if(isNumber(input) && input.length() > 10){
            return true;
        }else if(!isNumber(input) && input.length() > 20){
            return true;
        }
        return false;
    }

    private String trim(String input,int trimSize){
        String output = "";
        for(int i=0;i<trimSize;i++){
            output+= input.charAt(i);
        }
        return output;
    }

}
