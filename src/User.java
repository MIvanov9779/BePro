class User {

    private String login;
    private String password;
    private String ConfirmPasswword;
    private String Name;
    private String EastName;
    private String MiddleName;
    private String PhoneNumber;
    private String Email;
    private double balance;
    private String Message;

    public User(String login, String password){
        setLogin(login);
        setPassword(password);

    }



    public void setBalance(double balance){
        this.balance = balance;
    }

    public double getBalance(){
        return balance;
    }


    public void setLogin(String login){
        this.login = login;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String Name){
        this.Name = Name;
    }

    public void setEastName(String EastName){
        this.EastName = EastName;
    }

    public void setMiddleName(String MiddleName) {
        this.MiddleName = MiddleName;
    }

    public String getName(){
        return Name;
    }

    public String getEastName(){
        return EastName;
    }

    public String getMiddleName(){
        return MiddleName;
    }

    public String getPhoneNumber(){
        return PhoneNumber;
    }

    public String getEmail(){
        return Email;
    }

    public void setEmail(String Email){
        this.Email = Email;
    }

    public void setPhoneNumber(String PhoneNumber){
        this.PhoneNumber = PhoneNumber;
    }


    public String getConfirm() {
        return ConfirmPasswword;
    }

    public void setConfirm(String confirm) {
        this.ConfirmPasswword = confirm;
    }



    public boolean plusBalance(double amount){
        if (amount > 0){
            this.balance += amount;
            return true;

        }
        return false;
    }


    public boolean minusBalance(double amount){
        if (amount > 0 && getBalance() > amount){
            this.balance -= amount;
            return true;
        }
        return false;
    }


public String getMessage(){
        return Message;
}

public void setMessage(String Message){
        this.Message = Message;
}

}




