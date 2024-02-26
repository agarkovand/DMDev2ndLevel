package dmdev.mentoring.entity;

import dmdev.mentoring.entity.enums.Country;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FootballClubTest extends AbstractEntityTest {

    @Test
    void testPersistence() {
        City expectedCity = City.builder()
                .name("Kielce")
                .region("Świętokrzyskie województwo")
                .country(Country.POLAND)
                .build();

        FootballClub expectedFootballClub = FootballClub.builder()
                .name("Korona Kielce")
                .build();

        expectedFootballClub.setCity(expectedCity);

        session.persist(expectedCity);
        session.persist(expectedFootballClub);

        session.clear();

        FootballClub actualFootballClub = session.get(FootballClub.class, expectedFootballClub.getId());
        City actualCity = session.get(City.class, expectedCity.getId());

        assertThat(actualFootballClub).isEqualTo(expectedFootballClub);
        assertThat(actualCity).isEqualTo(expectedCity);
    }
}