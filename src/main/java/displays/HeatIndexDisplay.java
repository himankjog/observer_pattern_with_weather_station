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
public class HeatIndexDisplay implements Observer, DisplayElement {
    private Integer temperature;
    private Integer humidity;
    private final Observable observable;

    public HeatIndexDisplay(final Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void display() {
        log.info("Heat index is {}", (getHumidity()*getTemperature())/2);
    }


    public void removeSubscription() {
        log.warn("Removing HeatIndexDisplay as subscriber");
        observable.deleteObserver(this);
    }

    @Override
    public void update(Observable obs, Object arg) {
        if (obs instanceof WeatherDataPublisher) {
            final WeatherData weatherData = ((WeatherDataPublisher) obs).getWeatherData();
            setHumidity(weatherData.getHumidity());
            setTemperature(weatherData.getTemperature());
            display();
        }
    }
}
