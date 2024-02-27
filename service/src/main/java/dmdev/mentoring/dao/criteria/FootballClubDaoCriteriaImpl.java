package dmdev.mentoring.dao.criteria;

import dmdev.mentoring.dao.FootballClubDao;
import dmdev.mentoring.entity.City_;
import dmdev.mentoring.entity.FootballClub;
import dmdev.mentoring.entity.FootballClub_;
import dmdev.mentoring.entity.enums.Country;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FootballClubDaoCriteriaImpl implements FootballClubDao {

    private static final FootballClubDao INSTANCE = new FootballClubDaoCriteriaImpl();

    public static FootballClubDao getInstance() {
        return INSTANCE;
    }

    @Override
    public List<FootballClub> findAll(Session session) {
        var cb = session.getCriteriaBuilder();
        var query = cb.createQuery(FootballClub.class);
        var footballClub = query.from(FootballClub.class);
        query.select(footballClub);
        return session.createQuery(query).list();
    }

    @Override
    public List<FootballClub> findAllByCountries(Session session, List<Country> countries) {
        var cb = session.getCriteriaBuilder();
        var query = cb.createQuery(FootballClub.class);
        var footballClub = query.from(FootballClub.class);

        query.select(footballClub)
                .where(
                        cb.in(footballClub.get(FootballClub_.city).get(City_.COUNTRY), countries)
                );

        return session.createQuery(query).list();
    }
}
