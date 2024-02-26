package dmdev.mentoring.dao;

import dmdev.mentoring.entity.FootballClub;
import dmdev.mentoring.entity.enums.Country;
import org.hibernate.Session;

import java.util.List;

public interface FootballClubDao {
    List<FootballClub> findAll(Session session);
    List<FootballClub> finaAllByCountry(Session session, Country country);
}
