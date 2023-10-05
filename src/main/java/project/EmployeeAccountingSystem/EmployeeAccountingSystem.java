package project.EmployeeAccountingSystem;

import project.EmployeeAccountingSystem.Authorization.AuthEmployee;
import project.EmployeeAccountingSystem.WorkWithEmployees.*;
import project.Storage.TasksMenu.MenuEmployeesSystem;

import java.util.Scanner;

public class EmployeeAccountingSystem {
    public static void main(String[] args) {
        start();
    }
    public static void start(){
//        AuthEmployee authEmployee = new AuthEmployee();
//        authEmployee.startAuth();
        MenuEmployeesSystem.showMainMenu();
        int choice = new Scanner(System.in).nextInt();
        switch (choice){
            case 1:
                AddEmployee addEmployee = new AddEmployee();
                addEmployee.start();
                break;
            case 2:
                FireEmployee fireEmployee = new FireEmployee();
                fireEmployee.start();
                break;
            case 3:
                ChangeEmployee changeEmployee = new ChangeEmployee();
                changeEmployee.start();
                break;
            case 4:
                FindEmployee findEmployee = new FindEmployee();
                findEmployee.start();
                break;
            case 5:
                CreateReports reports = new CreateReports();
                reports.start();
        }
    }
}
