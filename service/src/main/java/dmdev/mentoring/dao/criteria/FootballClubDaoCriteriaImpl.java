package dmdev.mentoring.dao.criteria;

import dmdev.mentoring.dao.FootballClubDao;
import dmdev.mentoring.entity.FootballClub;
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
        var criteria = cb.createQuery(FootballClub.class);
        var footballClub = criteria.from(FootballClub.class);
        criteria.select(footballClub);
        return session.createQuery(criteria).list();
    }

    @Override
    public List<FootballClub> findAllByCountry(Session session, Country country) {
        var cb = session.getCriteriaBuilder();
        var criteria = cb.createQuery(FootballClub.class);
        var footballClub = criteria.from(FootballClub.class);

        criteria.select(footballClub).where(
                cb.equal(footballClub.get("city").get("country"), country)
        );

        return session.createQuery(criteria).list();
    }
}
