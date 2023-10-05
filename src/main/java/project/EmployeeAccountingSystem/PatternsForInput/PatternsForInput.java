package project.EmployeeAccountingSystem.PatternsForInput;

import project.DB_consts.DBConsts;
import project.Enteties.Post;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternsForInput {
    private static Connection connection = DBConsts.connection;


    public static String correctFullName(){
        String name = new Scanner(System.in).nextLine();
        Pattern pattern = Pattern.compile("[A-Z][a-z]+\\s[A-Z][a-z]+\\s[A-Z][a-z]+\\s*");
        Matcher matcher = pattern.matcher(name);
        if (matcher.matches()) return name;
        else {
            System.out.print("Incorrect format. Try again: ");
            name = correctFullName();
        }
        return name;
    }
    public static LocalDate correctBirthDate(){
        String date = new Scanner(System.in).nextLine();
        LocalDate bDate=null;

        Pattern pattern = Pattern.compile("(0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[0-2])\\.(19[3-9][0-9]|20([01][0-9]|2[0-3]))\\s*");
        Matcher matcher = pattern.matcher(date);
        if (matcher.matches()){
            bDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            return bDate;
        }
        else {
            System.out.print("Incorrect format. Try again: ");
            bDate = correctBirthDate();
        }

        return bDate;
    }
    public static String correctGender(){
        String gender = new Scanner(System.in).nextLine();

        Pattern pattegnGender = Pattern.compile("([Mm])|([Ww])\\s*");
        Matcher matcher = pattegnGender.matcher(gender);

        if (matcher.matches()) return gender;
        else{
            System.out.print("Incorrect value. Try again: ");
            gender = correctGender();
        }

        return gender;
    }
    public static String correctPhone(){
        String phone = new Scanner(System.in).nextLine();

        Pattern patternPhone = Pattern.compile("8[89]\\d{9}\\s*");
        Matcher matcher = patternPhone.matcher(phone);

        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT phone FROM employees");
            String temp;
            if (matcher.matches()){

                while(result.next()){
                    temp = result.getString("phone");
                    if (temp.equals(phone)){
                        System.out.print("Employee with this phone already exists. Try again: ");
                        phone = correctGender();
                    }
                }
                return phone;
            }
            else{
                System.out.print("Incorrect value. Try again: ");
                phone = correctGender();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }





        return phone;
    }

    public static Post correctPost(){
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine();

        Post myPost = null;

        String post = temp.toUpperCase();
        try{
            if (Arrays.stream(Post.values()).toList().contains(Post.valueOf(post))) return Post.valueOf(post);

        }catch (IllegalArgumentException ex){
            System.out.print("Incorrect value. Try again: ");
            myPost = correctPost();
        }

        return myPost;
    }
    public static String correctBossName(){
        String bossSurname = new Scanner(System.in).nextLine();


        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, name FROM employees");
            String temp;
            if (bossSurname.equals("null")) return null;
            while(resultSet.next()){
                temp = resultSet.getString("name").split(" ")[0];
                if (bossSurname.equals(temp)){
                    return resultSet.getString("id");
                }
            }
            System.out.print("An employee with this surname was not found. Try again: ");
            bossSurname = correctBossName();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return bossSurname;
    }

    public static double correctSalary(){
        String salary = new Scanner(System.in).nextLine();


        Pattern patternSalary = Pattern.compile("\\d+");
        Matcher matcher = patternSalary.matcher(salary);

        if (Double.parseDouble(salary)<0){
            System.out.print("Salary can't be < 0. Try again: ");
            salary = String.valueOf(correctSalary());
        }
        else{
            if (matcher.matches()) return Double.parseDouble(salary);
            else{
                System.out.print("Incorrect value. Try again: ");
               salary = String.valueOf(correctSalary());
            }

        }


        return Double.parseDouble(salary);
    }

}
