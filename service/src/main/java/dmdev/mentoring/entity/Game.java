package dmdev.mentoring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Game {

    @Id
    @GeneratedValue
    private Long gameId;
    private String tournament;
    private LocalDateTime dateTime;
    @Enumerated(EnumType.STRING)
    private GameStatus status;
    private Long stadiumId;
    private Long hostTeamId;
    private Long guestTeamId;
}
