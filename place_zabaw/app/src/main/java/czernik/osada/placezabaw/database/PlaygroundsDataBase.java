package czernik.osada.placezabaw.database;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlaygroundsDataBase {
    private static final PlaygroundsDataBase ourInstance = new PlaygroundsDataBase();

    private List<PlaygroundTable> playgrounds;
    private List<UserTable> users;
    private UserTable loggedUser;

    public static PlaygroundsDataBase getInstance() {
        return ourInstance;
    }

    private PlaygroundsDataBase() {
        playgrounds = initPlaygrounds();
        users = initUsers();
    }

    private List<PlaygroundTable> initPlaygrounds() {
        /*  (String address, double rating, String description, Bitmap image, double distance, List<String> functionalities) */
        PlaygroundTable p1 = new PlaygroundTable("Wrocław, Jana Długosza 59", 3, "Chcemy Was oderwać od wszystkiego co Wam ciąży. Nieważne czy to codzienność, nierozciągniete mięśnie, oponka, przywiązanie do monitora czy po prostu grawitacja. W Akademii GOjump przygotowaliśmy absolutnie niebanalny zestaw zajęć dla dzieci, młodzieży i dorosłych.", null,  6.0, 30.0, new String[] {"Trampolina"});
        PlaygroundTable p2 = new PlaygroundTable("Wrocław, ul. Nowowiejska 74", 3.72, "Park Świętej Edyty Stein to niewielki park we Wrocławiu położony w całości na osiedlu Ołbin. Został wydzielony z Parku Stanisława Tołpy na mocy uchwały Rady Miejskiej Wrocławia z dnia 17 marca 2011 r. nr VIII/98/11", null,  3.9, 0.0, new String[] {"Huśtawka", "Ślizgawka", "Piaskownica"});
        PlaygroundTable p3 = new PlaygroundTable("Wrocław, ul. Łukasiewicza 6", 3.90, "Plac zabaw dla dzieci", null,  0.74, 0.0, new String[] {"Huśtawka", "Ślizgawka", "Piaskownica", "Drabinki"});
        PlaygroundTable p4 = new PlaygroundTable("Wrocław, pl. Grunwaldzki 22", 4.0, "\n" +
                "W Kinderplanecie dzieci mogą spędzić przyjemnie czas, przedzierając się przez gęstwinę małpiego gaju, skacząc jak kangury na trampolinie. A gdy zmęczą się dzikimi harcami, mogą odpocząć budując zamki i domki z dużych plastikowych klocków.", null,  0.53, 30.0, new String[] {"Huśtawka", "Ślizgawka", "Piaskownica", "Drabinki", "Trampolina"});
        PlaygroundTable p5 = new PlaygroundTable("Wrocław, ul. Grunwaldzka 15A", 5, "\n" +
                "Mały plac zabaw dla dzieci", null,  0.99, 30.0, new String[] {"Huśtawka", "Ślizgawka", "Piaskownica"});
        PlaygroundTable p6 = new PlaygroundTable("Wrocław, al. Ludomira Różyckiego 1D", 1, "\n" +
                "Mały plac zabaw dla dzieci", null,  6.51, 30.0, new String[] {"Huśtawka", "Ślizgawka", "Piaskownica"});

        List<PlaygroundTable> playgrounds = new ArrayList<>();
        playgrounds.add(p1);
        playgrounds.add(p2);
        playgrounds.add(p3);
        playgrounds.add(p4);
        playgrounds.add(p5);
        playgrounds.add(p6);

        loggedUser = null;

        return playgrounds;
    }

    private List<UserTable> initUsers() {
        /* UserTable(String email, String password, Date registerDate, int numberOfComments, int numberOfRatings) */
        UserTable u1 = new UserTable("a@a.pl", "12345", "Jan Kowalski", new Date(1544389935), 3, 5);

        List<UserTable> users = new ArrayList<>();
        users.add(u1);
        return users;
    }

    public void addUser(String email, String password, String name, Date registerDate, int numberOfComments, int numberOfRatings) {
        UserTable u = new UserTable(email, password, name, registerDate, numberOfComments, numberOfRatings);
        users.add(u);
        loggedUser = u;
    }

    public void logOutUser() {
        loggedUser = null;
    }

    public UserTable getLoggedUser() {
        return loggedUser;
    }

    public boolean isUserLogged() {
        return loggedUser != null;
    }

    public boolean checkCredentials(String email, String password) {
        for (UserTable user: users) {
            if (user.checkCredentials(email, password)) {
                loggedUser = user;
                return true;
            }
        }

        return false;
    }

    public List<PlaygroundTable> getPlaygrounds() {
        return playgrounds;
    }

    public PlaygroundTable getPlayground(String address) {
        for (PlaygroundTable playgroundTable: playgrounds) {
            if (playgroundTable.getAddress().equals(address)) {
                return playgroundTable;
            }
        }

        return null;
    }

    public List<PlaygroundTable> getPlaygrounds(double priceFrom, double priceTo, double ratingFrom, double ratingTo, List<String> functionalities) {
        List<PlaygroundTable> result = new ArrayList<>();
        for (PlaygroundTable playgroundTable: playgrounds) {
            if (playgroundTable.getPrice() >= priceFrom
                    && playgroundTable.getPrice() <= priceTo
                    && playgroundTable.getRating() >= ratingFrom
                    && playgroundTable.getRating() <= ratingTo
                    && playgroundTable.containsAllFunctionalities(functionalities)) {
                result.add(playgroundTable);
            }
        }

        return result;
    }
}
