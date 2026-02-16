package display;

import observer.Observer;
import subject.WeatherData;

/**
 * Display que muestra estadísticas (promedio, mínimo y máximo)
 */
public class StatisticsDisplay implements Observer, DisplayElement {
    private double maxTemp = Double.MIN_VALUE;
    private double minTemp = Double.MAX_VALUE;
    private double tempSum = 0.0;
    private int numReadings = 0;
    private WeatherData weatherData;
    
    /**
     * Constructor
     * @param weatherData referencia al sujeto WeatherData
     */
    public StatisticsDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }
    
    @Override
    public void update() {
        double temp = weatherData.getTemperature();
        tempSum += temp;
        numReadings++;
        
        if (temp > maxTemp) {
            maxTemp = temp;
        }
        
        if (temp < minTemp) {
            minTemp = temp;
        }
        
        display();
    }
    
    @Override
    public void display() {
        System.out.println("\n=== Estadísticas ===");
        System.out.println("Temperatura promedio: " + (tempSum / numReadings) + "°C");
        System.out.println("Temperatura mínima: " + minTemp + "°C");
        System.out.println("Temperatura máxima: " + maxTemp + "°C");
    }
}
