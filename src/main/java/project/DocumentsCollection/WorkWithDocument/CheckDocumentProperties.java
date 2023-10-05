package project.DocumentsCollection.WorkWithDocument;

import project.DocumentsCollection.DocumentCollection;

import java.io.File;
import java.util.Scanner;

public class CheckDocumentProperties {
    public void start(){
        checkProperties();
    }

    private void checkProperties(){
        File file = getFile();
        showProperties(file);
        System.out.print("Enter something to continue ");
        String temp = new Scanner(System.in).next();
        DocumentCollection.start();
    }
    private File getFile(){
        System.out.print("Enter file name: ");
        String fileName = new Scanner(System.in).next();
        for (File file : DocumentCollection.files){
            if (file.getName().equals(fileName)) return file;
        }
        System.out.println("File not found. Try again");
        getFile();
        return null;
    }
    private void showProperties(File file){
        System.out.println(file.getName());
        System.out.println("--------------");
        String parentFile = file.getParent();
        System.out.println("Parent file: "+ parentFile);
        System.out.println("Path: " + file.getPath());
        System.out.println("Size: " + file.length() + " bytes");
        System.out.println("Last modified: " + file.lastModified());
    }
}
