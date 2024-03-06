package dmdev.mentoring.entity;

import dmdev.mentoring.entity.enums.Country;
import org.junit.jupiter.api.Test;

import static dmdev.mentoring.entity.enums.Country.POLAND;
import static org.assertj.core.api.Assertions.assertThat;

public class CityTest extends AbstractEntityTest {

    @Test
    void saveAndSelectWithHql() {
        City expected = createCity("Kielce", "Świętokrzyskie województwo", POLAND);

        session.persist(expected);
        session.evict(expected);

        City actual = session.get(City.class, expected.getId());

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void saveAndSelectWithHqlAndNativeQuery() {
        var expectedCity = createCity("Kielce", "Świętokrzyskie województwo", POLAND);

        session.persist(expectedCity);
        session.evict(expectedCity);

        var hqlResult = session
                .createQuery("select c from City c where c.name = :city",
                        City.class)
                .setParameter("city", "Kielce")
                .list();
        var actualCityWithHql = hqlResult.get(0);
        assertThat(actualCityWithHql).isEqualTo(expectedCity);

        var nativeQueryResult = session
                .createNativeQuery("select c.* from city c where c.name = :city",
                        City.class)
                .setParameter("city", "Kielce")
                .list();
        var actualCityWithNativeQuery = nativeQueryResult.get(0);
        assertThat(actualCityWithNativeQuery).isEqualTo(expectedCity);
    }

    private static City createCity(String city, String region, Country country) {
        return City.builder()
                .name(city)
                .region(region)
                .country(POLAND)
                .build();
    }
}
