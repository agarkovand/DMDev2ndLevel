package dmdev.mentoring.dto;

import dmdev.mentoring.entity.enums.Country;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class FootballClubFilter {
    Country country;
    String region;
}
