package display;

import observer.Observer;
import subject.WeatherData;

/**
 * Display que muestra un pronóstico simple basado en los cambios de presión
 */
public class ForecastDisplay implements Observer, DisplayElement {
    private double currentPressure = 1013.0; // Presión estándar al nivel del mar
    private double lastPressure;
    private WeatherData weatherData;
    
    /**
     * Constructor
     * @param weatherData referencia al sujeto WeatherData
     */
    public ForecastDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }
    
    @Override
    public void update() {
        lastPressure = currentPressure;
        currentPressure = weatherData.getPressure();
        display();
    }
    
    @Override
    public void display() {
        System.out.println("\n=== Pronóstico ===");
        if (currentPressure > lastPressure) {
            System.out.println("Mejorando el clima!");
        } else if (currentPressure == lastPressure) {
            System.out.println("Más de lo mismo");
        } else if (currentPressure < lastPressure) {
            System.out.println("Atención: clima más fresco y lluvioso en camino");
        }
    }
}
