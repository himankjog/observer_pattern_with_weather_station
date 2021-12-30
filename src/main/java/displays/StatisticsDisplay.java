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
public class StatisticsDisplay implements Subscriber, DisplayElement {
    private Integer averageTemperature;
    private Integer maxTemperature;
    private Integer minTemperature;
    private final WeatherDataPublisher weatherDataPublisher;

    public StatisticsDisplay(final WeatherDataPublisher weatherDataPublisher) {
        this.weatherDataPublisher = weatherDataPublisher;
        weatherDataPublisher.addSubscriber(this);
    }

    @Override
    public void display() {
        log.info("Avg/Max/Min temperatures = {}/{}/{}",
                getAverageTemperature(), getMaxTemperature(), getMinTemperature());
    }

    @Override
    public void update(BaseData data) {
        if (data instanceof WeatherData) {
            final WeatherData weatherData = (WeatherData) data;
            setAverageTemperature((weatherData.getTemperature() + weatherData.getHumidity() + weatherData.getPressure())/3);
            setMaxTemperature(Math.max(Math.max(weatherData.getTemperature(), weatherData.getHumidity()), weatherData.getPressure()));
            setMinTemperature(Math.min(Math.min(weatherData.getTemperature(), weatherData.getHumidity()), weatherData.getPressure()));
            display();
        }
    }
}
