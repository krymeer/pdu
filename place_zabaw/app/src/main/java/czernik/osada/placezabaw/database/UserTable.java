package czernik.osada.placezabaw.database;

import java.util.Date;

public class UserTable {
    private String email;
    private String password;
    private String name;
    private Date registerDate;
    private int numberOfRatings;
    private int numberOfComments;

    public UserTable(String email, String password, String name, Date registerDate, int numberOfComments, int numberOfRatings) {
        setEmail(email);
        setPassword(password);
        setName(name);
        setRegisterDate(registerDate);
        setNumberOfComments(numberOfComments);
        setNumberOfRatings(numberOfRatings);
    }
    public boolean checkCredentials(String email, String password) {
        return getEmail().equals(email) && getPassword().equals(password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public int getNumberOfRatings() {
        return numberOfRatings;
    }

    public void setNumberOfRatings(int numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }

    public int getNumberOfComments() {
        return numberOfComments;
    }

    public void setNumberOfComments(int numberOfComments) {
        this.numberOfComments = numberOfComments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
