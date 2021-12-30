package displays;

import elements.DisplayElement;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import models.BaseData;
import models.WeatherData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import publishers.WeatherDataPublisher;
import subscribers.Subscriber;


@Log4j2
@Getter
@Setter
public class CurrentConditionsDisplay implements Subscriber, DisplayElement {
    private Integer temperature;
    private Integer humidity;
    private WeatherDataPublisher weatherDataPublisher;

    public CurrentConditionsDisplay(final WeatherDataPublisher weatherDataPublisher) {
        this.weatherDataPublisher = weatherDataPublisher;
        weatherDataPublisher.addSubscriber(this);
    }
    @Override
    public void display() {
        log.info("Current conditions: {}C degrees and {}% humidity", getTemperature(), getHumidity());
    }

    @Override
    public void update(BaseData data) {
        if (data instanceof WeatherData) {
            final WeatherData weatherData = (WeatherData) data;
            setTemperature(weatherData.getTemperature());
            setHumidity(weatherData.getHumidity());
            display();
        }
    }
}
