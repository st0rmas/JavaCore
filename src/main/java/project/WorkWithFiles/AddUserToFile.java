package project.WorkWithFiles;

import project.Constants.FilesConstants;
import project.Enteties.User;

import java.io.*;

public class AddUserToFile {
  public static void addUser(User user) throws IOException {
      File file = new File(FilesConstants.USERS_FILE_PATH + user.getId() + ".txt");
      FileWriter fw = new FileWriter(file);
      BufferedWriter bw = new BufferedWriter(fw);
      bw.write(user.toFileString());
      bw.flush();
      bw.close();
  }
}
