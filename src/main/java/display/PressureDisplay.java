package display;

import observer.Observer;
import subject.WeatherData;

/**
 * Display que muestra solo la presión atmosférica
 */
public class PressureDisplay implements Observer, DisplayElement {
    private double pressure;
    private WeatherData weatherData;
    
    /**
     * Constructor
     * @param weatherData referencia al sujeto WeatherData
     */
    public PressureDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }
    
    @Override
    public void update() {
        this.pressure = weatherData.getPressure();
        display();
    }
    
    @Override
    public void display() {
        System.out.println("\n=== Presión Atmosférica ===");
        System.out.println("Presión: " + pressure + " hPa");
        
        // Clasificación de la presión atmosférica
        if (pressure < 980) {
            System.out.println("Estado: Presión muy baja - Tormenta probable");
        } else if (pressure < 1000) {
            System.out.println("Estado: Presión baja - Clima inestable");
        } else if (pressure < 1020) {
            System.out.println("Estado: Presión normal - Clima estable");
        } else if (pressure < 1040) {
            System.out.println("Estado: Presión alta - Clima despejado");
        } else {
            System.out.println("Estado: Presión muy alta - Clima muy estable");
        }
    }
}
