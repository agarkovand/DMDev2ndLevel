package dmdev.mentoring.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FootballClubTest extends AbstractEntityTest {

    @Test
    void whenSaved_thenPersisted() {
        City expectedCity = City.builder()
                .name("Kielce")
                .region("Świętokrzyskie województwo")
                .country(Country.POLAND)
                .build();

        FootballClub expectedFC = FootballClub.builder()
                .name("Korona Kielce")
                .build();

        expectedFC.setCity(expectedCity);

        session.persist(expectedCity);
        session.persist(expectedFC);

        session.evict(expectedCity);
        session.evict(expectedFC);

        FootballClub actualFC = session.get(FootballClub.class, expectedFC.getId());
        City actualCity = session.get(City.class, expectedCity.getId());

        assertThat(actualFC).isEqualTo(expectedFC);
        assertThat(actualCity).isEqualTo(expectedCity);
    }
}