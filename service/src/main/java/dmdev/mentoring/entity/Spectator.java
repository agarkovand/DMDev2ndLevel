package dmdev.mentoring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Spectator {

    @Id
    @GeneratedValue
    private Long spectatorId;
    private String fullName;
    private LocalDate birthday;
    private String gender;
    private String email;
    private String password;
    private String phone;
    private Long cityId;
    private Long fcId;

}
