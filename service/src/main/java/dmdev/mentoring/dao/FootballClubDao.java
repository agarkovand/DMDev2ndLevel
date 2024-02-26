package dmdev.mentoring.dao;

import dmdev.mentoring.entity.FootballClub;
import dmdev.mentoring.entity.enums.Country;
import org.hibernate.Session;

import java.util.List;

public interface FootballClubDao {
    List<FootballClub> findAll(Session session);

    /**
     * Returns all Football Clubs playing in the countries
     */
    List<FootballClub> findAllByCountries(Session session, List<Country> countries);
}
