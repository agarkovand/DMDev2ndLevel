package dmdev.mentoring.dao.querydsl;

import com.querydsl.jpa.impl.JPAQuery;
import dmdev.mentoring.dao.FootballClubDao;
import dmdev.mentoring.dto.FootballClubFilter;
import dmdev.mentoring.entity.FootballClub;
import dmdev.mentoring.entity.enums.Country;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import java.util.List;

import static dmdev.mentoring.entity.QFootballClub.footballClub;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FootballClubDaoQueryDslImpl implements FootballClubDao {

    private static final FootballClubDao INSTANCE = new FootballClubDaoQueryDslImpl();

    public static FootballClubDao getInstance() {
        return INSTANCE;
    }

    @Override
    public List<FootballClub> findAll(Session session) {
        return new JPAQuery<FootballClub>(session)
                .select((footballClub))
                .from(footballClub)
                .fetch();
    }

    @Override
    public List<FootballClub> findByCountries(Session session, List<Country> countries) {
        var countryIn = QPredicate.builder()
                .add(countries, footballClub.city.country::in).buildOr();

        return new JPAQuery<FootballClub>(session)
                .select(footballClub)
                .from(footballClub)
                .where(countryIn)
                .fetch();
    }

    @Override
    public List<FootballClub> findByCountryAndRegion(Session session, Country country, String region) {
        var filter = FootballClubFilter.builder()
                .country(country)
                .region(region)
                .build();

        var predicate = QPredicate.builder()
                .add(filter.getCountry(), footballClub.city.country::eq)
                .add(filter.getRegion(), footballClub.city.region::eq)
                .buildAnd();

        return new JPAQuery<FootballClub>(session)
                .select(footballClub)
                .from(footballClub)
                .where(predicate)
                .fetch();
    }
}
