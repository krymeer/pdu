package czernik.osada.placezabaw.database;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlaygroundsDataBase {
    private static final String[] DUMMY_CREDENTIALS = new String[]{"a@a.pl", "12345"};
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
        /* (int id, String town, String street, String distance, Bitmap miniature, float rating), List<String> features)*/

        PlaygroundTable p0 = new PlaygroundTable(0, "Wrocław", "Jana Długosza 59", 3, "Chcemy Was oderwać od wszystkiego co Wam ciąży. Nieważne czy to codzienność, nierozciągniete mięśnie, oponka, przywiązanie do monitora czy po prostu grawitacja. W Akademii GOjump przygotowaliśmy absolutnie niebanalny zestaw zajęć dla dzieci, młodzieży i dorosłych.", null,  6.0, 30.0, new String[] {"Trampolina", "Huśtawka"});
        PlaygroundTable p1 = new PlaygroundTable(1, "Wrocław","Nowowiejska 74", 3.72, "Park Świętej Edyty Stein to niewielki park we Wrocławiu położony w całości na osiedlu Ołbin. Został wydzielony z Parku Stanisława Tołpy na mocy uchwały Rady Miejskiej Wrocławia z dnia 17 marca 2011 r. nr VIII/98/11", null,  3.9, 0.0, new String[] {"Huśtawka", "Ślizgawka", "Piaskownica"});
        PlaygroundTable p2 = new PlaygroundTable(2, "Wrocław","Ignacego Łukasiewicza 6", 3.90, "Plac zabaw dla dzieci", null,  0.74, 0.0, new String[] {"Huśtawka", "Ślizgawka", "Piaskownica", "Drabinki"});
        PlaygroundTable p3 = new PlaygroundTable(3, "Wrocław", "plac Grunwaldzki 22", 4.0, "W Kinderplanecie dzieci mogą spędzić przyjemnie czas, przedzierając się przez gęstwinę małpiego gaju, skacząc jak kangury na trampolinie. A gdy zmęczą się dzikimi harcami, mogą odpocząć budując zamki i domki z dużych plastikowych klocków.", null,  0.53, 30.0, new String[] {"Huśtawka", "Ślizgawka", "Piaskownica", "Drabinki", "Trampolina"});
        PlaygroundTable p4 = new PlaygroundTable(4, "Wrocław", "Grunwaldzka 15A", 5, "Mały plac zabaw dla dzieci", null,  0.99, 30.0, new String[] {"Huśtawka", "Ślizgawka", "Piaskownica"});
        PlaygroundTable p5 = new PlaygroundTable(5, "Wrocław","aleja Ludomira Różyckiego 1D", 1, "Mały plac zabaw dla dzieci", null,  6.51, 30.0, new String[] {"Huśtawka", "Ślizgawka", "Piaskownica"});

        List<PlaygroundTable> playgrounds = new ArrayList<>();
        playgrounds.add(p0);
        playgrounds.add(p1);
        playgrounds.add(p2);
        playgrounds.add(p3);
        playgrounds.add(p4);
        playgrounds.add(p5);

        loggedUser = null;

        return playgrounds;
    }

    private List<UserTable> initUsers() {
        /* UserTable(String email, String password, Date registerDate, int numberOfComments, int numberOfRatings) */
        UserTable u1 = new UserTable(DUMMY_CREDENTIALS[0], DUMMY_CREDENTIALS[1], "Jan Kowalski", new Date(1544389935), 3, 5);
        UserTable u2 = new UserTable("adam.nowak@placezabaw.pl", "1234pass", "Adam Nowak", new Date(1547236321), 10, 10);

        List<UserTable> users = new ArrayList<>();
        users.add(u1);
        users.add(u2);
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

    public String[] getDummyCredentials() {
        return DUMMY_CREDENTIALS;
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

    public PlaygroundTable getPlayground(int id) {
        for (PlaygroundTable playgroundTable : playgrounds)
        {
            if (playgroundTable.getId() == id)
            {
                return playgroundTable;
            }
        }

        return null;
    }

    public List<PlaygroundTable> getPlaygrounds() {
        return playgrounds;
    }

    public List<PlaygroundTable> getPlaygrounds(String address) {
        List<PlaygroundTable> list = new ArrayList<>();

        for (PlaygroundTable playgroundTable : playgrounds)
        {
            if (address.contains(playgroundTable.getStreet()) && address.contains(playgroundTable.getTown()))
            {
                list.add(playgroundTable);
            }
        }

        return list;
    }

    public List<PlaygroundTable> getPlaygrounds(double priceFrom, double priceTo, double ratingFrom, double ratingTo, List<String> features) {
        List<PlaygroundTable> result = new ArrayList<>();
        for (PlaygroundTable playgroundTable : playgrounds)
        {
            if (playgroundTable.getPrice() >= priceFrom && playgroundTable.getPrice() <= priceTo && playgroundTable.getRating() >= ratingFrom && playgroundTable.getRating() <= ratingTo && playgroundTable.containsAllFeatures(features))
            {
                result.add(playgroundTable);
            }
        }

        return result;
    }
}
