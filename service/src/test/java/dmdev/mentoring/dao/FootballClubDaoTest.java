package dmdev.mentoring.dao;

import dmdev.mentoring.entity.FootballClub;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static dmdev.mentoring.entity.enums.Country.BELARUS;
import static dmdev.mentoring.entity.enums.Country.POLAND;
import static dmdev.mentoring.util.DatabaseUtil.*;
import static org.assertj.core.api.Assertions.assertThat;

class FootballClubDaoTest extends AbstractDaoTest {

    @ParameterizedTest
    @MethodSource("footballClubDaoSource")
    void findAll(FootballClubDao fcDao) {
        List<FootballClub> results = fcDao.findAll(session);
        assertThat(results).hasSize(ALL_FC.length);

        List<String> fcNames = results.stream().map(FootballClub::getName).collect(Collectors.toList());
        assertThat(fcNames).containsAll(Stream.of(ALL_FC_NAMES).toList());
    }

    @ParameterizedTest
    @MethodSource("footballClubDaoSource")
    void findByMultipleCountries(FootballClubDao fcDao) {
        var footballClubs = fcDao.findByCountries(session, Arrays.asList(POLAND, BELARUS));
        assertThat(footballClubs).hasSize(POLAND_FC.length + BELARUS_FC.length);

        var fcNames = footballClubs.stream().map(FootballClub::getName).collect(Collectors.toList());

        assertThat(fcNames)
                .containsAll(Stream.of(Stream.of(POLAND_FC_NAMES), Stream.of(BELARUS_FC_NAMES)).flatMap(s -> s).toList());
    }

    @ParameterizedTest
    @MethodSource("footballClubDaoSource")
    void findByCountryAndRegion(FootballClubDao fcDao){
        var footballClubs = fcDao.findByCountryAndRegion(session, POLAND, "Świętokrzyskie");
        assertThat(footballClubs).hasSize(SWIETOKRZYSKIE_FC.length);

        var fcNames = footballClubs.stream().map(FootballClub::getName).collect(Collectors.toList());

        assertThat(fcNames)
                .containsAll((Stream.of(SWIETOKRZYSKIE_FC_NAMES)).toList());
    }
}