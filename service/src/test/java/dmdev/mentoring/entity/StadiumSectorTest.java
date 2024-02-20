package dmdev.mentoring.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StadiumSectorTest extends AbstractEntityTest{

    @Test
    void whenSave_thenPersisted() {
        Stadium expectedStadium = Stadium.builder()
                .name("Suzuki Arena")
                .build();
        City expectedCity = City.builder()
                .name("Kielce")
                .region("Świętokrzyskie województwo")
                .country(Country.POLAND)
                .build();
        StadiumSector expectedSector = StadiumSector.builder()
                .label("VIP1")
                .seats(150)
                .status(StadiumSectorStatus.VIP)
                .build();

        expectedStadium.setCity(expectedCity);
        expectedSector.setStadium(expectedStadium);

        session.persist(expectedCity);
        session.persist(expectedStadium);
        session.persist(expectedSector);

        session.evict(expectedCity);
        session.evict(expectedStadium);
        session.evict(expectedSector);

        StadiumSector actualSector = session.get(StadiumSector.class, expectedSector.getId());
        Stadium actualStadium = actualSector.getStadium();
        City actualCity = actualStadium.getCity();

        assertThat(actualSector).isEqualTo(expectedSector);
        assertThat(actualStadium).isEqualTo(expectedStadium);
        assertThat(actualCity).isEqualTo(expectedCity);


    }

}