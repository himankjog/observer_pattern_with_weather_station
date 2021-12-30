import displays.CurrentConditionsDisplay;
import displays.ForecastDisplay;
import displays.HeatIndexDisplay;
import displays.StatisticsDisplay;
import models.WeatherData;
import publishers.WeatherDataPublisher;

public class WeatherStationApplication {

    public static void main(String[] args) {
        final WeatherDataPublisher weatherDataPublisher = WeatherDataPublisher.builder().build();
        final CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay(weatherDataPublisher);
        final ForecastDisplay forecastDisplay = new ForecastDisplay(weatherDataPublisher);
        final HeatIndexDisplay heatIndexDisplay = new HeatIndexDisplay(weatherDataPublisher);
        final StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherDataPublisher);

        weatherDataPublisher.setWeatherData(WeatherData.builder().humidity(10).temperature(30).pressure(2).build());
        weatherDataPublisher.measurementsChanged();
        heatIndexDisplay.removeSubscription();
        weatherDataPublisher.setWeatherData(WeatherData.builder().humidity(55).temperature(40).pressure(1).build());
        weatherDataPublisher.measurementsChanged();
    }
}
