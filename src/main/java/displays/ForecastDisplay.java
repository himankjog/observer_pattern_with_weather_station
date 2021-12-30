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
public class ForecastDisplay implements Observer, DisplayElement {
    private static final String GOOD_FORECAST = "Forecast is good for the day.";
    private static final String RAIN_FORECAST = "Forecast is rainy for the day.";

    private Integer currentHumidity;
    private String forecast;
    private final Observable observable;

    public ForecastDisplay(final Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void display() {
        log.info("{} as humidity is: {}", getForecast(), getCurrentHumidity());
    }

    @Override
    public void update(Observable obs, Object arg) {
        if (obs instanceof WeatherDataPublisher) {
            final WeatherData weatherData = ((WeatherDataPublisher) obs).getWeatherData();
            setCurrentHumidity(weatherData.getHumidity());
            if (currentHumidity >= 50) setForecast(RAIN_FORECAST);
            else setForecast(GOOD_FORECAST);
            display();
        }
    }
}
