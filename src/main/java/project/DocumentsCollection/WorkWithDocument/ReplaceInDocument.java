package project.DocumentsCollection.WorkWithDocument;

import project.Constants.FilesConstants;
import project.DocumentsCollection.DocumentCollection;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ReplaceInDocument {
    public void start(){
        File file = getDocument();
        try {
            System.out.println(getText(file));
            ArrayList<String> list = getPhrases();
            System.out.println();
            replaceInFile(list.get(0), list.get(1), file);
            System.out.println();
            System.out.println("Enter any symbol to continue");
            new Scanner(System.in).next();
            DocumentCollection.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    //----------------------------FILEs
    private File getDocument(){
        System.out.print("Enter a file name, where you want to replace: ");
        String fileName = new Scanner(System.in).nextLine();
        if (!fileExists(fileName)){
            getDocument();
        }
        File file = new File(FilesConstants.FILES_DOCUMENT_COLLECTION_PATH + fileName);
        return file;
    }
    private boolean fileExists(String fileName){
        for (File file : DocumentCollection.files){
            if (file.getName().equals(fileName)) return true;
        }
        return false;
    }

    //----------------------------
    private ArrayList<String> getPhrases(){
        ArrayList<String> words = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the phrase you want to replace: ");
        String phrase = scanner.nextLine();
        System.out.print("Enter a new phrase: ");
        String newPhrase = scanner.nextLine();
        words.add(phrase);
        words.add(newPhrase);
        return words;
    }

    private void replaceInFile(String phraseToReplace, String newPhrase, File file) throws IOException {
        String text = getText(file);

        if (textContainsWord(text, phraseToReplace)){
            System.out.println(text.replaceAll(phraseToReplace, newPhrase));
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write(text.replaceAll(phraseToReplace, newPhrase));
            bw.flush();
            bw.close();
        }
        else{
            System.out.println("Can't find phrase in this text");
            start();
        }
    }
    //TEXT
    private boolean textContainsWord(String text, String phraseToReplace){
        return (text.contains(phraseToReplace));
    }
    private String getText(File file) throws IOException {
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        StringBuilder text = new StringBuilder();
        String line = br.readLine();
        while(line!=null){
            text.append(line);
            line = br.readLine();
        }
        return text.toString();
    }

}
