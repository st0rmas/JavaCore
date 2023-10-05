package project.Authorization.UserAuth;

import project.Authorization.Authorization;
import project.Constants.FilesConstants;
import project.Enteties.User;
import project.Storage.StorageUser;
import project.Storage.TasksMenu.*;
import project.WorkWithFiles.AddUserToFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class UserAuth {
    private static ArrayList<User> users;


    public static void userAuth(){
        System.out.print("[1]. Authorization\n[2]. Registration\n[0]. Go back\n>>> ");
        int choice = new Scanner(System.in).nextInt();
        switch (choice){
            case 1 -> auth();
            case 2 -> reg();
            case 0 -> Authorization.authStart();
        }
    }

    private static User getUserInfo(){
        User currentUser = new User();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        currentUser.setLogin(login);
        currentUser.setPassword(password);
        return currentUser;
    }
    private static void auth(){
        System.out.println("Authorization");
        User user = getUserInfo();

        if (userExists(user.getLogin())){
            if (user.getPassword().equals(StorageUser.currentUser.getPassword())){
                user = StorageUser.currentUser;

            }
            else{
                System.out.println("Incorrect password");
                auth();
            }
        }
        else{
            System.out.println("User with login" + user.getLogin() + " doesn't exist\nDo you want to reg new account?(y/n)");
            String answer = new Scanner(System.in).next();
            if (answer.equals("y") || answer.equals("Y")) reg();
            else userAuth();
        }

    }

    private static void reg(){
        System.out.println("Registration");
        User user = getUserInfo();
        System.out.print("Repeat password: ");
        String repeatPassword = new Scanner(System.in).nextLine();
        if (repeatPassword.equals(user.getPassword())) {
            try {
                user.setId(getRandomId());
                AddUserToFile.addUser(user);
                System.out.println("Successful registration");
                userAuth();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            System.out.println("Passwords are not equals");
            reg();
        }

    }
    private static int getRandomId(){
        File folder = new File(FilesConstants.USERS_FILE_PATH);
        File[] files = folder.listFiles();
        List<Integer> usersIDs = new ArrayList<>();

        for (File file : files){
            usersIDs.add(Integer.parseInt(file.getName().substring(0, file.getName().lastIndexOf("."))));
        }
        System.out.println(usersIDs);
        int id = new Random().nextInt(1000);
        if (usersIDs.contains(id)) getRandomId();

        return id;
    }

    private static boolean userExists(String login){
        try {
            users = StorageUser.getUsers();
            for (User user : users){
                if (user.getLogin().equals(login)){
                    StorageUser.currentUser = user;
                    return true;
                }
            }
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
