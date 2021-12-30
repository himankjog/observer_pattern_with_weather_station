package displays;

import elements.DisplayElement;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import models.WeatherData;
import publishers.WeatherDataPublisher;

import java.util.Observable;
import java.util.Observer;


@Log4j2
@Getter
@Setter
public class CurrentConditionsDisplay implements Observer, DisplayElement {
    private Integer temperature;
    private Integer humidity;
    private final Observable observable;

    public CurrentConditionsDisplay(final Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }
    @Override
    public void display() {
        log.info("Current conditions: {}C degrees and {}% humidity", getTemperature(), getHumidity());
    }

    @Override
    public void update(Observable obs, Object arg) {
        if (obs instanceof WeatherDataPublisher) {
            final WeatherData weatherData = ((WeatherDataPublisher) obs).getWeatherData();
            setTemperature(weatherData.getTemperature());
            setHumidity(weatherData.getHumidity());
            display();
        }
    }
}
