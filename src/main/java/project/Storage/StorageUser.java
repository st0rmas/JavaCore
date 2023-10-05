package project.Storage;


import project.Constants.FilesConstants;
import project.Enteties.User;

import java.io.*;
import java.util.ArrayList;

public class StorageUser {
    public static User currentUser;

    public static ArrayList<User> getUsers() throws IOException {
        ArrayList<User> users = new ArrayList<>();



        File folder = new File(FilesConstants.USERS_FILE_PATH);
        File[] files = folder.listFiles();


        for (File file : files){
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            User user = new User();
            user.setId(Integer.parseInt(bufferedReader.readLine()));
            user.setLogin(String.valueOf(bufferedReader.readLine()));
            user.setPassword(String.valueOf(bufferedReader.readLine()));
            users.add(user);
        }

        return users;
    }

}
