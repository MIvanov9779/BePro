import javax.jws.soap.SOAPBinding;
import java.io.*;
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
            System.out.println("4.Exit.");
            option = scanner.nextInt();

            if (option == 1) {
                SignIn();
            } else if (option == 2) {
                SingUp();
            } else if (option == 3) {
                allUsers();
            } else if (option == 4) {
                break;
            }

        } while (option != 4);

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
            writer.write(user.getLogin());
            writer.newLine();
            writer.write(user.getPassword());
            writer.newLine();
            writer.write(user.getEmail() + "");
            writer.newLine();
            //New Lines
            writer.write(user.getPhoneNumber() + "");
            writer.newLine();
            writer.write(user.getName() + "");
            writer.newLine();
            writer.write(user.getEastName() + "");
            writer.newLine();
            writer.write(user.getMiddleName() + "");
            writer.newLine();
      /**/

//End of new lines
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return false;
        }

        return true;
    }


    public ArrayList<User> getUsers() {
        ArrayList<User> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(this.file))) {
            String Login;
            while ((Login = reader.readLine()) != null) {
                String Password = reader.readLine();
                User user = new User(Login, Password);
                user.setEmail(reader.readLine());

                //New Lines
                user.setName(reader.readLine());
                user.setMiddleName(reader.readLine());
                user.setEastName(reader.readLine());
                user.setPhoneNumber(reader.readLine());


                //end of new lines
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
                String Password = reader.readLine();
                String Email = reader.readLine();
                String PhoneNumber = reader.readLine();
                String Name = reader.readLine();
                String EastName = reader.readLine();
                String MiddleName = reader.readLine();
                if (line.equals(Login)) {
                    User user = new User(line, Password);
                    user.setEmail(Email);

                    //new lines
                    user.setPhoneNumber(PhoneNumber);
                    user.setName(Name);
                    user.setEastName(EastName);
                    user.setMiddleName(MiddleName);
                    //end of new lines
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
            Transfer(user);
        } else if (operation == 7) {
            return;
        }


        Personal(user);
        System.out.println();

    }


public void Edit(User user){

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



}

    public boolean exist(String Login){

            ArrayList<User>users = getUsers();


        for (int i = 0; i <users.size(); i++) {
            if (users.get(i).getLogin().equals(Login)){
                return true;
            }
        }
        return false;
    }

   /* public void deleteUser(User user){
        for (int i = 0; i <users.size() ; i++) {
            if (users.get(i).getLogin().equals(user.getLogin())){
                users.remove(i);
                return;
            }
        }
    }*/

    public void allUsers(){
        System.out.println("Users: ");
        ArrayList<User> users = getUsers();
        for (int i = 0; i <users.size() ; i++) {

            System.out.println(users.get(i).getLogin());

        }
        System.out.println();
    }



    public void ConfirmDelete(User user){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Confirm delete your user? ");

        System.out.println("1. Yes");

        System.out.println("2. No");

        int operation = scanner.nextInt();

        if (operation == 1){
            //    deleteUser(user);
        }else if (operation == 2){
            return;
        }

    }


/*public void Messages(){

        Scanner scanner = new Scanner(System.in);
    System.out.println("1.New messages");
    System.out.println("2.Write message");
    System.out.println("3.Main menu");

    int operator = scanner.nextInt();


    if (operator == 1){

    }else if (operator == 2){

    }else if (operator == 3){
        return;
    }

}

public void NewMessage(){

}

public void WriteMessage(){

}*/


public void Transfer(User from){

        Scanner scanner = new Scanner(System.in);

        ArrayList<User> users = getUsers();

    System.out.println("Users: ");
    for (int i = 0; i <users.size() ; i++) {
        System.out.println(i + ". " + "Name: " + users.get(i).getLogin());

    }

        System.out.println();

    System.out.print("Enter id: ");
        int ID = scanner.nextInt();
    System.out.println("Enter transfer amount: ");
        double amount = scanner.nextDouble();




}



public void Deposit(User user){

    System.out.println("Enter amount");
        Scanner scanner = new Scanner(System.in);
   double amount = scanner.nextDouble();

    boolean success = user.plusBalance(amount);

    if(success){
              System.out.println("The deposit");
    }else{
        System.out.println("Error");
    }

    System.out.println();

}


public void Withdraw (User user){

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
}

