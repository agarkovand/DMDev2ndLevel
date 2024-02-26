package dmdev.mentoring.entity;

import dmdev.mentoring.entity.enums.Country;
import org.junit.jupiter.api.Test;

import static dmdev.mentoring.entity.enums.Country.POLAND;
import static org.assertj.core.api.Assertions.assertThat;

public class CityTest extends AbstractEntityTest {

    @Test
    void testPersistence() {
        City expected = createCity("Kielce", "Świętokrzyskie województwo", POLAND);

        session.persist(expected);
        session.evict(expected);

        City actual = session.get(City.class, expected.getId());

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testHql() {
        City expected = createCity("Kielce", "Świętokrzyskie województwo", POLAND);

        session.persist(expected);
        session.evict(expected);

        var hqlResult = session
                .createQuery("select c from City c where c.name = :city",
                        City.class)
                .setParameter("city", "Kielce")
                .list();
        City actual = hqlResult.get(0);
        assertThat(actual).isEqualTo(expected);

        var nativeQueryResult = session
                .createNativeQuery("select c.* from city c where c.name = :city",
                        City.class)
                .setParameter("city", "Kielce")
                .list();
        actual = nativeQueryResult.get(0);
        assertThat(actual).isEqualTo(expected);
    }

    private static City createCity(String city, String region, Country country) {
        return City.builder()
                .name(city)
                .region(region)
                .country(POLAND)
                .build();
    }
}
