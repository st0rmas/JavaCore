package project.EmployeeAccountingSystem.Authorization;

import project.EmployeeAccountingSystem.EmployeesMenuTasks.EmployeesMenuTasks;

import java.util.Scanner;

public class AuthEmployee {
    public void startAuth(){
        System.out.println("Welcome to Employee's Accounting System");
        System.out.println("----------------------------------------");
        System.out.println("Enter to your account");
        System.out.print("Login: ");
        String login = new Scanner(System.in).nextLine();
        System.out.print("Password: ");
        String password = new Scanner(System.in).nextLine();
        if (isAdmin(login,password)){
            EmployeesMenuTasks menuTasks = new EmployeesMenuTasks();
            menuTasks.start();
        }
        else{
            System.out.println("Incorrect login or password ");
            startAuth();
        }
    }
    private boolean isAdmin(String login, String password){
        return (login.equals("admin") && password.equals("admin"));
    }

}
