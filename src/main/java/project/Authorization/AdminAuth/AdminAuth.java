package project.Authorization.AdminAuth;

import project.Authorization.Authorization;
import project.Storage.StorageUser;
import project.Enteties.User;

import java.util.Scanner;

public class AdminAuth {
    public static void startAdminAuth(){
        User user = new User();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (login.equals("admin") && password.equals("admin")){
            System.out.println("You entered like admin");
            user.setId(0);
            user.setLogin(login);
            user.setPassword(password);
            StorageUser.currentUser = user;
        }
        else{
            System.out.println("Unknown user");
            Authorization.authStart();
        }
    }
}
