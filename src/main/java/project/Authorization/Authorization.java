package project.Authorization;

import project.Authorization.UserAuth.UserAuth;
import project.Authorization.AdminAuth.AdminAuth;

import java.util.Scanner;

public class Authorization {
    public static void authStart(){
        System.out.print("[1]. Admin\n[2]. User\n[0]. Exit\n>>> ");
        int choice = new Scanner(System.in).nextInt();
        switch(choice){
            case 1 -> AdminAuth.startAdminAuth();
            case 2 -> UserAuth.userAuth();
            case 0 -> shutDown();
            default -> authStart();
        }
    }
    private static void shutDown(){}


}
