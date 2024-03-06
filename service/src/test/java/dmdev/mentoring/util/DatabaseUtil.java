package dmdev.mentoring.util;

import dmdev.mentoring.entity.City;
import dmdev.mentoring.entity.FootballClub;
import dmdev.mentoring.entity.enums.Country;
import lombok.Cleanup;
import lombok.experimental.UtilityClass;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Map;

import static dmdev.mentoring.entity.enums.Country.BELARUS;
import static dmdev.mentoring.entity.enums.Country.POLAND;
import static dmdev.mentoring.entity.enums.Country.UKRAINE;
import static java.util.Map.entry;

@UtilityClass
public class DatabaseUtil {

    public static FootballClub[] ALL_FC;
    public static String[] ALL_FC_NAMES;
    public static FootballClub[] POLAND_FC;
    public static String[] POLAND_FC_NAMES;
    public static FootballClub[] SWIETOKRZYSKIE_FC;
    public static String[] SWIETOKRZYSKIE_FC_NAMES;
    public static FootballClub[] BELARUS_FC;
    public static String[] BELARUS_FC_NAMES;

    public static void initDataBase(SessionFactory sessionFactory) {
        @Cleanup Session session = sessionFactory.openSession();

        session.beginTransaction();

        var cities = Map.ofEntries(
                entry("Kielce", saveCity(session, "Kielce", "Świętokrzyskie", POLAND)),
                entry("Warszawa", saveCity(session, "Warszawa", "Mazowieckie", POLAND)),
                entry("Krakow", saveCity(session, "Kraków", "Małopolskie", POLAND)),
                entry("Raków", saveCity(session, "Raków", "Świętokrzyskie", POLAND)),
                entry("Kharkov", saveCity(session, "Kharkov", "Kharkov", UKRAINE)),
                entry("Kiev", saveCity(session, "Kiev", "Kiev", UKRAINE)),
                entry("Minsk", saveCity(session, "Minsk", "Minsk", BELARUS))
        );

        var footballClubs = Map.ofEntries(
                entry("Korona Kielce", saveFootbalClub(session, "Korona Kielce", cities.get("Kielce"))),
                entry("Legia Warszawa", saveFootbalClub(session, "Legia Warszawa", cities.get("Warszawa"))),
                entry("Cracovia", saveFootbalClub(session, "Cracovia", cities.get("Krakow"))),
                entry("Raków Częstochowa", saveFootbalClub(session, "Raków Częstochowa", cities.get("Raków"))),

                entry("Metallist Kharkov", saveFootbalClub(session, "Metallist Kharkov", cities.get("Kharkov"))),
                entry("Dinamo Kiev", saveFootbalClub(session, "Dinamo Kiev", cities.get("Kiev"))),
                entry("Minsk", saveFootbalClub(session, "Minsk", cities.get("Minsk")))
        );

        session.getTransaction().commit();

        ALL_FC = footballClubs.values().toArray(FootballClub[]::new);
        ALL_FC_NAMES = footballClubs.values().stream().map(FootballClub::getName).toArray(String[]::new);

        // POLAND Football Clubs
        POLAND_FC = footballClubs.values().stream()
                .filter(fc -> POLAND.equals(fc.getCity().getCountry())).toArray(FootballClub[]::new);
        POLAND_FC_NAMES = footballClubs.values().stream().
                filter(fc -> POLAND.equals(fc.getCity().getCountry()))
                .map(FootballClub::getName).toArray(String[]::new);

        // SWIETOKRZYSKIE in POLAND Football Clubs
        SWIETOKRZYSKIE_FC = footballClubs.values().stream()
                .filter(fc -> POLAND.equals(fc.getCity().getCountry()))
                .filter(fc -> "Świętokrzyskie".equals(fc.getCity().getRegion()))
                .toArray(FootballClub[]::new);
        SWIETOKRZYSKIE_FC_NAMES = footballClubs.values().stream()
                .filter(fc -> POLAND.equals(fc.getCity().getCountry()))
                .filter(fc -> "Świętokrzyskie".equals(fc.getCity().getRegion()))
                .map(FootballClub::getName)
                .toArray(String[]::new);

        // BELARUS Football Clubs
        BELARUS_FC = footballClubs.values().stream()
                .filter(fc -> BELARUS.equals(fc.getCity().getCountry())).toArray(FootballClub[]::new);
        BELARUS_FC_NAMES = footballClubs.values().stream().
                filter(fc -> BELARUS.equals(fc.getCity().getCountry()))
                .map(FootballClub::getName).toArray(String[]::new);
    }

    private static FootballClub saveFootbalClub(Session session, String name, City city) {
        FootballClub footballClub = FootballClub.builder()
                .name(name)
                .city(city)
                .build();
        session.save(footballClub);
        return footballClub;
    }

    private City saveCity(Session session, String cityName, String region, Country country) {
        City city = City.builder()
                .name(cityName)
                .region(region)
                .country(country)
                .build();
        session.save(city);
        return city;
    }

}
