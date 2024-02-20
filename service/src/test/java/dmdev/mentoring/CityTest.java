package dmdev.mentoring;

import dmdev.mentoring.entity.City;
import dmdev.mentoring.entity.Country;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CityTest extends AbstractEntityTest {

    @Test
    public void whenSave_thenCityPersisted() {
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
