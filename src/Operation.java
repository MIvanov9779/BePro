import javax.jws.soap.SOAPBinding;
import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;


class Operation {
    private File file;


    public Operation() {
        file = new File("users.txt");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void start() {

        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.println("Menu: ");
            System.out.println();
            System.out.println("1. Sign In.");
            System.out.println("2. Sing Up.");
            System.out.println("3. Users.");
            System.out.println("4.Admin panel.");
            System.out.println("5.Exit.");
            option = scanner.nextInt();

            if (option == 1) {
                SignIn();
            } else if (option == 2) {
                SingUp();
            } else if (option == 3) {
                allUsers();
            }else if (option == 4){
                AdminLogin();
            }else if (option == 5) {
                break;
            }

        } while (option != 5);

    }


    public void SignIn() {

        String Login;
        String Password;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your login: ");
        Login = scanner.next();
        System.out.println("Enter your password: ");
        Password = scanner.next();

        User user = getUser(Login);

        if (user == null) {
            System.out.println("Nu user exists");
            return;
        }

        if (!user.getPassword().equals(Password)) {
            System.out.println("Password is't correct");
            return;
        }


        System.out.println("Hello " + user.getLogin());
        System.out.println("");

        Personal(user);

    }


    public void SingUp() {


        String Login;
        String Password;
        String ConfirmPassword;


        Scanner scanner = new Scanner(System.in);


        System.out.println("1.Create new Login: ");
        Login = scanner.next();

        if (exist(Login)) {
            System.out.println("User already exist");
            return;
        }
        System.out.println("2. Create new password: ");
        Password = scanner.next();
        System.out.println("3. Confirm password: ");
        ConfirmPassword = scanner.next();

        if (Password.equals(ConfirmPassword)) {

            User user = new User(Login, Password);
            boolean result = writeUser(user);
            if (result) {
                System.out.println("User registered");
            } else {
                System.out.println("Error");
            }
        } else {
            System.out.println("Error");
        }

    }

    public boolean writeUser(User user) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.file, true))) {
            writer.write(user.getLogin() + ":" +user.getPassword()+":" + "..."+ ":"+ "..."+ ":" + "..."+ ":" + "..."+ ":" + "...");
            writer.newLine();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return false;
        }

        return true;
    }


    public ArrayList<User> getUsers() {
        ArrayList<User> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(this.file))) {
            String line;
            while ((line = reader.readLine()) != null) {
               String[] date = line.split(":");

               User user = new User(date[0], date[1]);
               user.setName(date[2]);
               user.setEastName(date[3]);
               user.setMiddleName(date[4]);
               user.setEmail(date[5]);
               user.setPhoneNumber(date[6]);

                result.add(user);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return result;
    }


    public User getUser(String Login) {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] date = line.split(":");

                if (date[0].equals(Login)){
                    User user = new User(date[0], date[1]);
                    user.setName(date[2]);
                    user.setEastName(date[3]);
                    user.setMiddleName(date[4]);
                    user.setEmail(date[5]);
                    user.setPhoneNumber(date[6]);
                    return user;
                }



                }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }


        return null;
    }


    public void Personal(User user) {

        String Name = "";
        String EastName = "";
        String MiddleName = "";
        String Email = "";
        String PhoneNumber = "";


        System.out.println("Name: " + user.getName());
        System.out.println("East Name: " + user.getEastName());
        System.out.println("Middle Name: " + user.getMiddleName());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Phone number: " + user.getPhoneNumber());
        System.out.println("Balance: " + user.getBalance() + "$");


        System.out.println();
        System.out.println("1.Edit");
        System.out.println("2. Delete User");
        System.out.println("3.Messages");
        System.out.println("4.Deposit.");
        System.out.println("5.Withdraw");
        System.out.println("6.Transfer");
        System.out.println("7.Main menu: ");


        Scanner scanner = new Scanner(System.in);
        int operation = scanner.nextInt();


        if (operation == 1) {
            Edit(user);
        } else if (operation == 2) {
            ConfirmDelete(user);
        } else if (operation == 3) {
            // Messages();
        } else if (operation == 4) {
            Deposit(user);
        } else if (operation == 5) {
            Withdraw(user);
        } else if (operation == 6) {
           // Transfer(user);
        } else if (operation == 7) {
            return;
        }


        Personal(user);
        System.out.println();

    }


    public void Edit(User user) {


        ArrayList<User> users = getUsers();

        String Name = "";
        String EastName = "";
        String MiddleName = "";
        String Email = "";
        String PhoneNumber = "";


        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name:");
        Name = scanner.next();
        user.setName(Name);
        System.out.println("Enter your East Name");
        EastName = scanner.next();
        user.setEastName(EastName);
        System.out.println("Enter your middle name");
        MiddleName = scanner.next();
        user.setMiddleName(MiddleName);
        System.out.println("Enter your Email:");
        Email = scanner.next();
        user.setEmail(Email);
        System.out.println("Enter your phone number:");
        PhoneNumber = scanner.next();
        user.setPhoneNumber(PhoneNumber);


        for (int i = 0; i <users.size(); i++) {
            if (users.get(i).getLogin().equals(user.getLogin())) {
                users.set(i, user);
                break;
            }
        }


        try(BufferedWriter writer = new BufferedWriter(new FileWriter(this.file))){
            for (int i = 0; i <users.size() ; i++) {
                User u = users.get(i);
                writer.write(u.getLogin()+":" + u.getPassword() + ":" + u.getName() + ":" + u.getEastName()+ ":" + u.getMiddleName() + ":" + u.getEmail() + ":" + u.getPhoneNumber());
                writer.newLine();
            }
        }catch (IOException ex){
            ex.getMessage();
        }

    }

    public boolean exist(String Login) {

        ArrayList<User> users = getUsers();


        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getLogin().equals(Login)) {
                return true;
            }
        }
        return false;
    }


    public void allUsers() {

        System.out.println("Users: ");
        ArrayList<User> users = getUsers();
        for (int i = 0; i < users.size(); i++) {
            System.out.println(users.get(i).getLogin());

        }
        System.out.println();
    }


    public void ConfirmDelete(User user) {
        ArrayList<User> users = getUsers();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Confirm delete your user? ");

        System.out.println("1. Yes");

        System.out.println("2. No");

        int operation = scanner.nextInt();

        if (operation == 1){

                for (int i = 0; i <users.size() ; i++) {
                    if (users.get(i).getLogin().equals(user.getLogin())){
                        users.remove(i);
                        break;
                    }
                }

            try(BufferedWriter writer = new BufferedWriter(new FileWriter(this.file))){
                for (int i = 0; i <users.size() ; i++) {
                    User u = users.get(i);
                    writer.write(u.getLogin()+":" + u.getPassword() + ":" + u.getName() + ":" + u.getEastName()+ ":" + u.getMiddleName() + ":" + u.getEmail() + ":" + u.getPhoneNumber());
                    writer.newLine();
                }
            }catch (IOException ex){
                ex.getMessage();
            }
            start();

        }else if (operation == 2){
            Personal(user);
        }

    }



/*
    public void Transfer(User from) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<User> users = getUsers();

        System.out.println("Users: ");
        for (int i = 0; i < users.size(); i++) {
            System.out.println(i + ". " + "Name: " + users.get(i).getLogin());

        }
        System.out.println("\n");

        System.out.print("Enter id: ");
        int ID = scanner.nextInt();
        System.out.println("Enter transfer amount: ");
        double amount = scanner.nextDouble();

    }
*/





public void Deposit(User user){
        ArrayList<User> users = getUsers();
    System.out.println("Enter amount");
        Scanner scanner = new Scanner(System.in);
   double amount = scanner.nextDouble();

    boolean success = user.plusBalance(amount);

    if(success){
              System.out.println("The deposit");

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(this.file))) {
            for (int i = 0; i <users.size() ; i++) {
                User u = users.get(i);
            }


        }catch (IOException ex){
            ex.getMessage();
        }

    }else{
        System.out.println("Error");
    }

    System.out.println();

}


public void Withdraw (User user){
    ArrayList<User> users = getUsers();
    System.out.println("Enter withdraw amount");

    Scanner scanner = new Scanner(System.in);

    double amount = scanner.nextDouble();

    boolean success = user.minusBalance(amount);
    if (success){
        System.out.println("Withdraw");
    }else {
        System.out.println("Error");
    }
    System.out.println();
}




public void AdminLogin(){
    Scanner scanner = new Scanner(System.in);
    ArrayList<User> users = getUsers();
    String Login;
    String Password;

    System.out.println("Enter your login: ");
    Login = scanner.next();
    System.out.println("Enter your password: ");
    Password = scanner.next();

    User user = getUser(Login);

    if (user == null) {
        System.out.println("No user exists");
        return;
    }
    if (!user.getPassword().equals(Password)) {
        System.out.println("Password is't correct");
        return;
    }
    System.out.println("Admin Panel: ");
    AdminPanel(user);
}

public void AdminPanel(User user){
    Scanner scanner = new Scanner(System.in);
    ArrayList<User> users = getUsers();

    System.out.println("1. Users.");
    System.out.println("2.Delete all users");
    System.out.println("3.Edit users.");
    System.out.println("4.Main menu");

    int option = scanner.nextInt();

    if (option == 1){
        System.out.println("Users: ");

        for (int i = 0; i <users.size(); i++) {
            System.out.println(users.get(i).getLogin());
        }

        AdminPanel(user);

        System.out.println("\n");

    }else if (option == 2){

        Scanner in = new Scanner(System.in);
        System.out.println("Delete?");
        System.out.println("Yes");
        System.out.println("No");

        int operation = in.nextInt();

        if (operation == 1){
            for (int i = 0; i <users.size() ; i++) {
                users.remove(i);
                break;
            }
        }else if (operation == 2){
            AdminPanel(user);
        }

    }else if (option == 3){
/*
        for (int i = 0; i <users.size() ; i++) {
            if (users.get(i).equals(user.getLogin())){

            }
        }
*/

    return;
    }else if (option == 4){
        start();
    }

}

}