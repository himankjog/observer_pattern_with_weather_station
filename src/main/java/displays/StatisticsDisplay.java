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
public class StatisticsDisplay implements Observer, DisplayElement {
    private Integer averageTemperature;
    private Integer maxTemperature;
    private Integer minTemperature;
    private final Observable observable;

    public StatisticsDisplay(final Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void display() {
        log.info("Avg/Max/Min temperatures = {}/{}/{}",
                getAverageTemperature(), getMaxTemperature(), getMinTemperature());
    }

    @Override
    public void update(Observable obs, Object arg) {
        if (obs instanceof WeatherDataPublisher) {
            final WeatherData weatherData = ((WeatherDataPublisher) obs).getWeatherData();
            setAverageTemperature((weatherData.getTemperature() + weatherData.getHumidity() + weatherData.getPressure())/3);
            setMaxTemperature(Math.max(Math.max(weatherData.getTemperature(), weatherData.getHumidity()), weatherData.getPressure()));
            setMinTemperature(Math.min(Math.min(weatherData.getTemperature(), weatherData.getHumidity()), weatherData.getPressure()));
            display();
        }
    }
}
