package main;

import subject.WeatherData;
import display.*;

/**
 * Clase principal del sistema de estación meteorológica
 * Demuestra el patrón Observer en acción
 */
public class WeatherStation {
    
    public static void main(String[] args) {
        // Crear el sujeto (WeatherData)
        WeatherData weatherData = new WeatherData();
        
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║   ESTACIÓN METEOROLÓGICA - PATRÓN OBSERVER                ║");
        System.out.println("║   Sistema de Monitoreo de Clima en Tiempo Real            ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        // Crear los displays (observadores)
        System.out.println(">>> Registrando displays...\n");
        
        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);
        
        // NUEVOS DISPLAYS SOLICITADOS:
        HeatIndexDisplay heatIndexDisplay = new HeatIndexDisplay(weatherData);
        PressureDisplay pressureDisplay = new PressureDisplay(weatherData);
        
        // Display de terceros (ejemplo de extensibilidad)
        ThirdPartyDisplay thirdPartyDisplay = new ThirdPartyDisplay(weatherData);
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("SIMULACIÓN 1: Día caluroso y húmedo");
        System.out.println("=".repeat(60));
        weatherData.setMeasurements(32.0, 75.0, 1012.0);
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("SIMULACIÓN 2: Condiciones frescas");
        System.out.println("=".repeat(60));
        weatherData.setMeasurements(18.0, 55.0, 1020.0);
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("SIMULACIÓN 3: Tormenta aproximándose");
        System.out.println("=".repeat(60));
        weatherData.setMeasurements(22.0, 85.0, 995.0);
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("SIMULACIÓN 4: Día muy caluroso");
        System.out.println("=".repeat(60));
        weatherData.setMeasurements(38.0, 80.0, 1008.0);
        
        // Demostración de remoción de observador
        System.out.println("\n" + "=".repeat(60));
        System.out.println(">>> Removiendo el display de terceros...\n");
        weatherData.removeObserver(thirdPartyDisplay);
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("SIMULACIÓN 5: Después de remover un display");
        System.out.println("=".repeat(60));
        weatherData.setMeasurements(25.0, 60.0, 1015.0);
        
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║   DEMOSTRACIÓN COMPLETADA                                  ║");
        System.out.println("║   Todos los observadores fueron notificados correctamente ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }
}
