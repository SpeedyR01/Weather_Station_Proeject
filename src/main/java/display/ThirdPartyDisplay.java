package display;

import observer.Observer;
import subject.WeatherData;

/**
 * Display de terceros - Ejemplo de cómo otros desarrolladores pueden crear sus propios displays
 * Este display muestra información personalizada basada en las mediciones
 */
public class ThirdPartyDisplay implements Observer, DisplayElement {
    private double temperature;
    private double humidity;
    private WeatherData weatherData;
    
    /**
     * Constructor
     * @param weatherData referencia al sujeto WeatherData
     */
    public ThirdPartyDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }
    
    @Override
    public void update() {
        this.temperature = weatherData.getTemperature();
        this.humidity = weatherData.getHumidity();
        display();
    }
    
    @Override
    public void display() {
        System.out.println("\n=== Display de Terceros - Análisis Personalizado ===");
        
        // Análisis personalizado del clima
        String comfort = "";
        if (temperature < 10) {
            comfort = "Muy frío";
        } else if (temperature < 18) {
            comfort = "Fresco";
        } else if (temperature < 24) {
            comfort = "Agradable";
        } else if (temperature < 30) {
            comfort = "Cálido";
        } else {
            comfort = "Muy caluroso";
        }
        
        String humidityLevel = "";
        if (humidity < 30) {
            humidityLevel = "Seco";
        } else if (humidity < 60) {
            humidityLevel = "Confortable";
        } else if (humidity < 80) {
            humidityLevel = "Húmedo";
        } else {
            humidityLevel = "Muy húmedo";
        }
        
        System.out.println("Sensación térmica: " + comfort);
        System.out.println("Nivel de humedad: " + humidityLevel);
        System.out.println("Recomendación: " + getRecommendation(temperature, humidity));
    }
    
    /**
     * Genera una recomendación basada en las condiciones
     */
    private String getRecommendation(double temp, double hum) {
        if (temp > 30 && hum > 70) {
            return "Evitar actividad física intensa. Mantenerse hidratado.";
        } else if (temp < 10) {
            return "Vestirse con ropa abrigada. Cuidado con hipotermia.";
        } else if (hum < 30) {
            return "Aire seco. Beber agua frecuentemente.";
        } else {
            return "Condiciones favorables para actividades al aire libre.";
        }
    }
}
