package project.Storage.TasksMenu;

import project.Authorization.Authorization;
import project.DocumentsCollection.DocumentCollection;
import project.EmployeeAccountingSystem.EmployeeAccountingSystem;

import java.util.Scanner;

public class MenuTasks {
    public static void menu(){
        System.out.print("[1]. Document collection\n" +
                           "[2]. Employees system\n" +
                "[0]. Exit\n>>> "
        );
        int choice = new Scanner(System.in).nextInt();

        switch (choice){
            case 1:
                DocumentCollection.start();
                break;
            case 2:
                EmployeeAccountingSystem employeeAccountingSystem = new EmployeeAccountingSystem();
                employeeAccountingSystem.start();
                break;
            case 0:
                Authorization.authStart();
                break;
        }
    }
}
