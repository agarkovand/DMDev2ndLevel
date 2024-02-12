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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class StadiumSector {

    @Id
    @GeneratedValue
    private Long sectorId;
    private String label;
    private Integer seats;
    @Enumerated(EnumType.STRING)
    private StadiumSectorStatus status;
    private Long stadiumId;
}
