package dmdev.mentoring.dao;

import dmdev.mentoring.dao.criteria.FootballClubDaoCriteriaImpl;
import dmdev.mentoring.dao.querydsl.FootballClubDaoQueryDslImpl;
import dmdev.mentoring.entity.FootballClub;
import dmdev.mentoring.entity.enums.Country;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static dmdev.mentoring.util.DatabaseUtil.FC_NAMES;
import static org.assertj.core.api.Assertions.assertThat;

class FootballClubDaoTest extends AbstractDaoTest {

    @ParameterizedTest
    @MethodSource("footballClubDaoSource")
    void findAll(FootballClubDao fcDao) {
        List<FootballClub> results = fcDao.findAll(session);
        assertThat(results).hasSize(5);

        List<String> fcNames = results.stream().map(FootballClub::getName).collect(Collectors.toList());
        assertThat(fcNames).containsExactly(FC_NAMES);
    }

    @ParameterizedTest
    @MethodSource("footballClubDaoSource")
    void finaAllByCountry(FootballClubDao fcDao) {
        var polandFootballClubs = fcDao.findAllByCountry(session, Country.POLAND);
        assertThat(polandFootballClubs).hasSize(2);

        var fcNames = polandFootballClubs.stream().map(FootballClub::getName).collect(Collectors.toList());

        assertThat(fcNames).containsExactly(FC_NAMES[0], FC_NAMES[1]);
    }

    public Stream<FootballClubDao> footballClubDaoSource() {
        return Stream.of(FootballClubDaoCriteriaImpl.getInstance(),
                FootballClubDaoQueryDslImpl.getInstance());
    }
}