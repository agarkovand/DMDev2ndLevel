package dmdev.mentoring.entity;

import dmdev.mentoring.entity.enums.GameStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "id")
@Builder
@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tournament;
    private LocalDateTime scheduledOn;

    @Enumerated(EnumType.STRING)
    private GameStatus status;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Stadium stadium;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private FootballClub hostTeam;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private FootballClub guestTeam;
}
