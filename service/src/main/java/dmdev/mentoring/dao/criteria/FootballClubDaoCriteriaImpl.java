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
        return null;
    }

    @Override
    public List<FootballClub> finaAllByCountry(Session session, Country country) {
        return null;
    }
}
