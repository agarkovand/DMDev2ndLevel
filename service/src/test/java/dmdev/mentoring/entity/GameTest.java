package dmdev.mentoring.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class GameTest extends AbstractEntityTest {

    @Test
    void testPersistence() {
        Stadium expectedStadium = Stadium.builder()
                .name("Suzuki Arena")
                .build();
        City expectedHostFcCity = City.builder()
                .name("Kielce")
                .region("Świętokrzyskie województwo")
                .country(Country.POLAND)
                .build();
        FootballClub expectedHostFC = FootballClub.builder()
                .name("Korona")
                .build();
        City expectedGuestFcCity = City.builder()
                .name("Warszawa")
                .region("Mazowieckie województwo")
                .country(Country.POLAND)
                .build();
        FootballClub expectedGuestFC = FootballClub.builder()
                .name("Legia")
                .build();
        Game expectedGame = Game.builder()
                .tournament("Premier League Polska")
                .scheduledOn(LocalDateTime.of(2024, 2, 25, 19, 0, 0))
                .status(GameStatus.SCHEDULED)
                .build();

        expectedStadium.setCity(expectedHostFcCity);
        expectedHostFC.setCity(expectedHostFcCity);
        expectedGuestFC.setCity(expectedGuestFcCity);

        expectedGame.setHostTeam(expectedHostFC);
        expectedGame.setGuestTeam(expectedGuestFC);
        expectedGame.setStadium(expectedStadium);

        session.persist(expectedHostFcCity);
        session.persist(expectedStadium);
        session.persist(expectedGuestFcCity);
        session.persist(expectedHostFC);
        session.persist(expectedGuestFC);
        session.persist(expectedGame);

        session.evict(expectedHostFcCity);
        session.evict(expectedStadium);
        session.evict(expectedGuestFcCity);
        session.evict(expectedHostFC);
        session.evict(expectedGuestFC);
        session.evict(expectedGame);

        Game actualGame = session.get(Game.class, expectedGame.getId());

        FootballClub actualHostFC = actualGame.getHostTeam();
        FootballClub actualGuestFC = actualGame.getGuestTeam();
        City actualHostFcCity = actualHostFC.getCity();
        City actualGuestFcCity = actualGuestFC.getCity();
        Stadium actualStadium = actualGame.getStadium();
        City actualStadiumCity = actualStadium.getCity();

        assertThat(actualGame).isEqualTo(expectedGame);
        assertThat(actualHostFC).isEqualTo(expectedHostFC);
        assertThat(actualGuestFC).isEqualTo(expectedGuestFC);
        assertThat(actualHostFcCity).isEqualTo(expectedHostFcCity);
        assertThat(actualGuestFcCity).isEqualTo(expectedGuestFcCity);
        assertThat(actualStadiumCity).isEqualTo(expectedHostFcCity);

    }
}