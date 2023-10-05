package project.DocumentsCollection;

import project.Constants.FilesConstants;
import project.DocumentsCollection.WorkWithDocument.*;
import project.Storage.TasksMenu.MenuCollection;
import project.DocumentsCollection.WorkWithDocument.*;
import project.Storage.TasksMenu.MenuTasks;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DocumentCollection {
    public static List<File> files = new ArrayList<>();

//    public static void main(String[] args) {
//        start();
//    }
    public static void start(){
        showDocuments(FilesConstants.FILES_DOCUMENT_COLLECTION_PATH);
        MenuCollection.menu();
        int choice = new Scanner(System.in).nextInt();
        switch (choice){
            case 1:
                OpenDocument.start();
                break;
            case 2:
                SearchInDocument.start();
                break;
            case 3:
                ReplaceInDocument replace = new ReplaceInDocument();
                replace.start();
                break;
            case 4:
                CreateDocument create = new CreateDocument();
                create.start();
                break;
            case 5:
                CheckDocumentProperties check = new CheckDocumentProperties();
                check.start();
                break;
            case 0:
                MenuTasks.menu();
                break;
            default:
                start();
                break;
        }
    }
    private static void showDocuments(String filePath){
        File folder = new File(filePath);


        System.out.println("Folder: " + folder.getName());
        if (folder.listFiles().length==0){
            System.out.println(folder.getName() + " is empty");
        }
        else{
            for (File file : folder.listFiles()){
                files.add(file);
                System.out.println("File: " + file.getName());
            }
        }

    }

}
