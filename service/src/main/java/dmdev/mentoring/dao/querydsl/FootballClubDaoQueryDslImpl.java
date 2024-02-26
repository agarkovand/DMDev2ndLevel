package dmdev.mentoring.dao.querydsl;

import dmdev.mentoring.dao.FootballClubDao;
import dmdev.mentoring.entity.FootballClub;
import dmdev.mentoring.entity.enums.Country;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FootballClubDaoQueryDslImpl implements FootballClubDao {

    private static final FootballClubDao INSTANCE = new FootballClubDaoQueryDslImpl();

    public static FootballClubDao getInstance() {
        return INSTANCE;
    }

    @Override
    public List<FootballClub> findAll(Session session) {
        return null;
    }

    @Override
    public List<FootballClub> finaAllByCountry(Session session, Country country) {
        return null;
    }
}
