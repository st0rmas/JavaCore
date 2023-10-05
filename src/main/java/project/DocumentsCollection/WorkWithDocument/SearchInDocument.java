package project.DocumentsCollection.WorkWithDocument;

import project.Constants.FilesConstants;
import project.DocumentsCollection.DocumentCollection;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SearchInDocument {

    private static ArrayList<String> filesText = new ArrayList<>();

    public static void start(){
        getTexts();
        DocumentCollection.start();
    }
    private static String getWord(){
        System.out.print("Enter a word to find: ");
        String word = new Scanner(System.in).nextLine();
        if (!word.isEmpty()){
            return word;
        }
        else{
            getWord();
        }
        return null;
    }

    private static boolean search(String wordToFind, String text){
            if (text.contains(wordToFind)) {
                return true;
            }
            return false;
    }
    private static void showTextWithWord(String word){
        int counter = 0;
        for (String text : filesText){

            if (search(word, text)){
                counter++;
                System.out.println("Coincidence " + counter + "\n");
                System.out.println(text);
            }
        }
    }
    private static boolean searchInText(String text, String word){
        return text.contains(word);
    }
    private static void getTexts(){
        String word = getWord();
        File folder = new File(FilesConstants.FILES_DOCUMENT_COLLECTION_PATH);
        int counter = 0;
        for (File file : folder.listFiles()){
            try {
                FileReader fileReader = new FileReader(file);
                BufferedReader br = new BufferedReader(fileReader);
                String line;
                StringBuilder text = new StringBuilder();
                try {
                    line = br.readLine();
                    while (line!=null){
                        text.append(line);
                        line = br.readLine();
                    }
                    if (searchInText(String.valueOf(text), word)){
                        counter++;
                        System.out.println("Coincidence " + counter);
                        System.out.println(file.getName());
                        System.out.println("==================");
                        System.out.println(text);
                        System.out.println();
                    }
                    filesText.add(text.toString());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }



        }
    }
}
