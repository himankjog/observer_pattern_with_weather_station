package displays;

import elements.DisplayElement;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import models.BaseData;
import models.WeatherData;
import publishers.WeatherDataPublisher;
import subscribers.Subscriber;


@Log4j2
@Getter
@Setter
public class ForecastDisplay implements Subscriber, DisplayElement {
    private static final String GOOD_FORECAST = "Forecast is good for the day.";
    private static final String RAIN_FORECAST = "Forecast is rainy for the day.";

    private Integer currentHumidity;
    private String forecast;
    private final WeatherDataPublisher weatherDataPublisher;

    public ForecastDisplay(final WeatherDataPublisher weatherDataPublisher) {
        this.weatherDataPublisher = weatherDataPublisher;
        weatherDataPublisher.addSubscriber(this);
    }

    @Override
    public void display() {
        log.info("{} as humidity is: {}", getForecast(), getCurrentHumidity());
    }

    @Override
    public void update(BaseData data) {
        if (data instanceof WeatherData) {
            final WeatherData weatherData = (WeatherData) data;
            setCurrentHumidity(weatherData.getHumidity());
            if (currentHumidity >= 50) setForecast(RAIN_FORECAST);
            else setForecast(GOOD_FORECAST);

            display();
        }
    }
}
