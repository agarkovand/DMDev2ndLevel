package dmdev.mentoring.entity;

import dmdev.mentoring.entity.enums.Country;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StadiumTest extends AbstractEntityTest {

    @Test
    void testPersistence() {
        Stadium expectedStadium = Stadium.builder()
                .name("PGE Narodowy")
                .build();
        City expectedCity = City.builder()
                .name("Warszawa")
                .region("Mazowieckie województwo")
                .country(Country.POLAND)
                .build();
        expectedStadium.setCity(expectedCity);

        session.persist(expectedCity);
        session.persist(expectedStadium);

        session.evict(expectedCity);
        session.evict(expectedStadium);

        Stadium actualStadium = session.get(Stadium.class, expectedStadium.getId());
        City actualCity = actualStadium.getCity();

        assertThat(actualStadium).isEqualTo(expectedStadium);
        assertThat(actualCity).isEqualTo(expectedCity);
    }
}