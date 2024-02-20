package dmdev.mentoring.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class SpectatorTest extends AbstractEntityTest {

    @Test
    void testPersistence() {
        Spectator expectedSpectator1 = Spectator.builder()
                .fullName("Marek")
                .birthday(LocalDate.of(2002, 2,23))
                .gender(Gender.MALE)
                .email("marek@gmail.com")
                .password("pass1")
                .phone("222-333-555")
                .build();
        Spectator expectedSpectator2 = Spectator.builder()
                .fullName("Leszek")
                .birthday(LocalDate.of(1995, 7,2))
                .gender(Gender.MALE)
                .email("leszek@gmail.com")
                .password("pass2")
                .phone("333-333-999")
                .build();
        City expectedCity = City.builder()
                .name("Kielce")
                .region("Świętokrzyskie województwo")
                .country(Country.POLAND)
                .build();
        FootballClub expectedFC = FootballClub.builder()
                .name("Korona Kielce")
                .build();

        expectedFC.setCity(expectedCity);

        expectedSpectator1.setFootballClub(expectedFC);
        expectedSpectator1.setCity(expectedCity);

        expectedSpectator2.setCity(expectedCity);

        session.persist(expectedCity);
        session.persist(expectedFC);
        session.persist(expectedSpectator1);
        session.persist(expectedSpectator2);

        session.evict(expectedCity);
        session.evict(expectedFC);
        session.evict(expectedSpectator1);
        session.evict(expectedSpectator2);

        FootballClub actualFC = session.get(FootballClub.class, expectedFC.getId());
        City actualFcCity = actualFC.getCity();

        Spectator actualSpectator1 = session.get(Spectator.class, expectedSpectator1.getId());
        Spectator actualSpectator2 = session.get(Spectator.class, expectedSpectator2.getId());

        City actualSpectator1_City = actualSpectator1.getCity();
        City actualSpectator2_City = actualSpectator2.getCity();
        FootballClub actualSpectator1_FC = actualSpectator1.getFootballClub();
        FootballClub actualSpectator2_FC = actualSpectator2.getFootballClub();

        assertThat(actualFC).isEqualTo(expectedFC);
        assertThat(actualFcCity).isEqualTo(expectedCity);

        assertThat(actualSpectator1).isEqualTo(expectedSpectator1);
        assertThat(actualSpectator2).isEqualTo(expectedSpectator2);
        assertThat(actualSpectator1_City).isEqualTo(expectedCity);
        assertThat(actualSpectator2_City).isEqualTo(expectedCity);
        assertThat(actualSpectator1_FC).isEqualTo(expectedFC);
        assertThat(actualSpectator2_FC).isNull();

    }
}