package project.DocumentsCollection.WorkWithDocument;

import project.Constants.FilesConstants;
import project.DocumentsCollection.DocumentCollection;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OpenDocument {
    private static List<Page> pages;
    private static final int symbolsOnPage = 500;

    public static void start(){
        String fileName = getDocumentName();
        if (documentExists(fileName)){
            File file = new File(FilesConstants.FILES_DOCUMENT_COLLECTION_PATH + fileName);
            try {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                showDocumentText(br);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            start();
        }


    }
    private static String getDocumentName(){
        System.out.print("Enter a file path: ");
        return new Scanner(System.in).nextLine();
    }
    private static boolean documentExists(String fileName){
        for (File file : DocumentCollection.files){
            if (file.getName().equals(fileName)){
                return true;
            }
        }
        return false;
    }
    private static void showDocumentText(BufferedReader br){
        System.out.println("Document text:");
        StringBuilder text = new StringBuilder();
        int counter = 0;
        try {
            String line = br.readLine();
            while(line!=null){
                text.append(line);
                text.append("\n");
                line = br.readLine();
            }
            counter = text.length()/symbolsOnPage+1;
            pages = getPages(text, counter);
            workWithPages(pages.get(0));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
    private static ArrayList<Page> getPages(StringBuilder text, int counter){
        ArrayList<Page> pages = new ArrayList<>();
        for (int i = 1; i<=counter; i++){
            if (i==counter) pages.add(new Page(i, text.substring(symbolsOnPage*(i-1), text.length()-1)));
            else pages.add(new Page(i, text.substring(symbolsOnPage*(i-1), (symbolsOnPage*i))));
        }

        return pages;
    }
    private static void workWithPages(Page page){
        System.out.println(page);
        System.out.println();
        System.out.println("Page " + page.getId()+"/"+pages.size());

        menuWorkWithPage();
        int choice = new Scanner(System.in).nextInt();
        switch (choice){
            case 1 -> nextPage(page, 1);
            case 2 -> nextPage(page, -1);
            case 0 -> DocumentCollection.start();
            default -> workWithPages(pages.get(0));
        }
    }

    private static void nextPage(Page page, int direction){
        if (direction==1){
            if (page.getId()!=pages.size()){
                page = pages.get(page.getId());
                workWithPages(page);
            }
            else{
                System.out.println("It's the last page");
                workWithPages(pages.get(pages.size()-1));
            }
        }
        if (direction==-1){
            if (page.getId()!=1){
                page = pages.get(page.getId()-2);
                workWithPages(page);
            }
            else{
                System.out.println("It's the first page");
                workWithPages(pages.get(0));
            }
        }

    }
    private static void menuWorkWithPage(){
        System.out.println("[1]. Next page\n[2]. Previous page\n[0]. Go back to main menu");
    }
    private static class Page{
        private int id;
        private String text;

        public Page(){}
        public Page(int id, String text) {
            this.id = id;
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            StringBuilder string = new StringBuilder();
            for (int i = 0; i< text.length(); i++){
                if (i%100==0){
                    string.append("\n");
                }
                string.append(text.charAt(i));
            }
            return string.toString();
        }
    }




}
