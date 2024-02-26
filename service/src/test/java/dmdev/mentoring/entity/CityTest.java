package dmdev.mentoring.entity;

import dmdev.mentoring.entity.enums.Country;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CityTest extends AbstractEntityTest {

    @Test
    void testPersistence() {
        City expected = City.builder()
                .name("Kielce")
                .region("Świętokrzyskie województwo")
                .country(Country.POLAND)
                .build();

        session.persist(expected);
        session.evict(expected);

        City actual = session.get(City.class, expected.getId());

        assertThat(actual).isEqualTo(expected);
    }
}
