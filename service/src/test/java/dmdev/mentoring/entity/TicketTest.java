package dmdev.mentoring.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class TicketTest extends AbstractEntityTest {

    @Test
    void testPersistence() {
        Ticket expectedTicket = Ticket.builder()
                .status(TicketStatus.ISSUED)
                .sectorLabel("VIP1")
                .seatNumber(25)
                .distributionDate(LocalDate.of(2024, 2, 21))
                .build();
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

        expectedTicket.setGame(expectedGame);

        session.persist(expectedHostFcCity);
        session.persist(expectedStadium);
        session.persist(expectedGuestFcCity);
        session.persist(expectedHostFC);
        session.persist(expectedGuestFC);
        session.persist(expectedGame);
        session.persist(expectedTicket);

        session.evict(expectedHostFcCity);
        session.evict(expectedStadium);
        session.evict(expectedGuestFcCity);
        session.evict(expectedHostFC);
        session.evict(expectedGuestFC);
        session.evict(expectedGame);
        session.evict(expectedTicket);

        Ticket actualTicket = session.get(Ticket.class, expectedTicket.getId());
        Game actualGame = actualTicket.getGame();

        assertThat(actualTicket).isEqualTo(expectedTicket);
        assertThat(actualGame).isEqualTo(expectedGame);

    }

}