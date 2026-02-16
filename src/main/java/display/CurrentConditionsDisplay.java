package display;

import observer.Observer;
import subject.WeatherData;

/**
 * Display que muestra las condiciones actuales
 */
public class CurrentConditionsDisplay implements Observer, DisplayElement {
    private double temperature;
    private double humidity;
    private double pressure;
    private WeatherData weatherData;
    
    /**
     * Constructor
     * @param weatherData referencia al sujeto WeatherData
     */
    public CurrentConditionsDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }
    
    @Override
    public void update() {
        this.temperature = weatherData.getTemperature();
        this.humidity = weatherData.getHumidity();
        this.pressure = weatherData.getPressure();
        display();
    }
    
    @Override
    public void display() {
        System.out.println("\n=== Condiciones Actuales ===");
        System.out.println("Temperatura: " + temperature + "°C");
        System.out.println("Humedad: " + humidity + "%");
        System.out.println("Presión: " + pressure + " hPa");
    }
}
