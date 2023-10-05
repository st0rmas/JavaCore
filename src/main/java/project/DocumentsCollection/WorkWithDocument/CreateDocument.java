package project.DocumentsCollection.WorkWithDocument;

import project.DocumentsCollection.DocumentCollection;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateDocument {

    public void start(){
        try {
            enterFilePathAndName();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private  void enterFilePathAndName() throws IOException {
        System.out.print("Enter a file path(start from src directory( src/somedir1/somedir2/)): ");
        String path = new Scanner(System.in).next();
        if (path.charAt(path.length()-1)!='/'){
            path+='/';
        }
        Pattern patternPath = Pattern.compile("src/\\w.+/");
        Matcher matherPath = patternPath.matcher(path);
        if (!matherPath.matches()){
            System.out.println("Invalid path");
            enterFilePathAndName();
        }

        File folder = new File(path);

        if (!folder.exists()){
            folder.mkdir();
        }
        String fileName = getFileName();
        File file = new File(path+fileName);

        if (!file.exists()){
            file.createNewFile();
        }


        String answer = addToFileAnswer();

        writeDocument(file, answer);

    }
    private  String getFileName(){
        System.out.print("Enter a file name: ");
        String fileName = new Scanner(System.in).next();
        Pattern pattern = Pattern.compile("[(a-z)(A-Z)(0-9)+]+\\.txt");
        Matcher matcher = pattern.matcher(fileName);
        if (matcher.matches()) return fileName;

        else getFileName();
        return null;
    }
    private String addToFileAnswer(){
        System.out.println("Do you want to write something in file?(y/n) ");
        String answer = new Scanner(System.in).next();

        if(!(answer.equals("y") || answer.equals("Y") || answer.equals("n") || answer.equals("N"))){
            System.out.println("Invalid answer");
            addToFileAnswer();
        }
        return answer;
    }
    private void writeDocument(File file, String create) throws IOException {
        if (create.equals("y") || create.equals("Y")){

            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(getTextToFile());
            bw.flush();
            bw.close();
            DocumentCollection.start();
        }
        else {
            DocumentCollection.start();
        }
    }
    private String getTextToFile(){
        StringBuilder text = new StringBuilder();
        System.out.println("!!!Enter /exit to stop write to file!!!");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        while(!line.equals("/exit")){
            text.append(line);
            text.append("\n");
            line = scanner.nextLine();
        }

        return text.toString();
    }
}
