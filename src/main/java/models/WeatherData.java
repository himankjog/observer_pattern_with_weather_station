package models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class WeatherData implements BaseData {
    private Integer temperature;
    private Integer humidity;
    private Integer pressure;
}
