package dmdev.mentoring.util;

import dmdev.mentoring.entity.City;
import dmdev.mentoring.entity.FootballClub;
import dmdev.mentoring.entity.enums.Country;
import lombok.Cleanup;
import lombok.experimental.UtilityClass;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import static dmdev.mentoring.entity.enums.Country.*;

@UtilityClass
public class DatabaseUtil {

    public static final String[] FC_NAMES;

    static {
        FC_NAMES = new String[]{
                "FC Korona Kielce",
                "FC Legia Warszawa",
                "FC Metallist Kharkov",
                "FC Dinamo Kiev",
                "FC Minsk"
        };
    }


    public static void initDataBase(SessionFactory sessionFactory) {
        @Cleanup Session session = sessionFactory.openSession();
        City Kielce = saveCity(session, "Kielce", "Świętokrzyskie województwo", POLAND);
        City Warszawa = saveCity(session, "Warszawa", "Mazowieckie województwo", POLAND);
        City Kharkov = saveCity(session, "Kharkov", "Kharkov", UKRAINE);
        City Kiev = saveCity(session, "Kiev", "Kiev", UKRAINE);
        City Minsk = saveCity(session, "Minsk", "Minsk", BELARUS);

        saveFootbalClub(session, FC_NAMES[0], Kielce);
        saveFootbalClub(session, FC_NAMES[1], Warszawa);
        saveFootbalClub(session, FC_NAMES[2], Kharkov);
        saveFootbalClub(session, FC_NAMES[3], Kiev);
        saveFootbalClub(session, FC_NAMES[4], Minsk);

    }

    private static void saveFootbalClub(Session session, String name, City city) {
        FootballClub footballClub = FootballClub.builder()
                .name(name)
                .city(city)
                .build();
        session.save(footballClub);
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
