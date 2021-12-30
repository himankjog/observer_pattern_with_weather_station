package publishers;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import models.WeatherData;

import java.util.Observable;

@Builder
@Getter
@Setter
public class WeatherDataPublisher extends Observable {
    private WeatherData weatherData;

    public void measurementsChanged() {
        setChanged();
        notifyObservers();
    }
}

