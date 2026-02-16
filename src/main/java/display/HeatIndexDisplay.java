package display;

import observer.Observer;
import subject.WeatherData;

/**
 * Display que muestra el índice de calor (Heat Index)
 * Calcula qué tan caliente se siente basado en temperatura y humedad
 */
public class HeatIndexDisplay implements Observer, DisplayElement {
    private double heatIndex = 0.0;
    private WeatherData weatherData;
    
    /**
     * Constructor
     * @param weatherData referencia al sujeto WeatherData
     */
    public HeatIndexDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }
    
    @Override
    public void update() {
        double t = weatherData.getTemperature();
        double r = weatherData.getHumidity();
        this.heatIndex = calculateHeatIndex(t, r);
        display();
    }
    
    /**
     * Calcula el índice de calor usando la fórmula proporcionada
     * HI = c₁ + c₂T + c₃R + c₄TR + c₅T² + c₆R² + c₇T²R + c₈TR² + c₉T²R²
     * 
     * @param T temperatura ambiente de bulbo seco (en grados Celsius)
     * @param R humedad relativa (valor porcentual entre 0 y 100)
     * @return índice de calor en grados Celsius
     */
    private double calculateHeatIndex(double T, double R) {
        // Coeficientes de la fórmula
        double c1 = -8.78469475556;
        double c2 = 1.61139411;
        double c3 = 2.33854883889;
        double c4 = -0.14611605;
        double c5 = -0.012308094;
        double c6 = -0.0164248277778;
        double c7 = 2.211732e-3;
        double c8 = 7.2546e-4;
        double c9 = -3.582e-6;
        
        // Cálculo del índice de calor según la fórmula
        double HI = c1 
                  + c2 * T 
                  + c3 * R 
                  + c4 * T * R 
                  + c5 * T * T 
                  + c6 * R * R 
                  + c7 * T * T * R 
                  + c8 * T * R * R 
                  + c9 * T * T * R * R;
        
        return HI;
    }
    
    @Override
    public void display() {
        System.out.println("\n=== Índice de Calor ===");
        System.out.println("Heat Index: " + String.format("%.2f", heatIndex) + "°C");
        
        // Clasificación del índice de calor
        if (heatIndex < 27) {
            System.out.println("Precaución: No hay riesgo significativo");
        } else if (heatIndex < 32) {
            System.out.println("Precaución extrema: Posible fatiga con exposición prolongada");
        } else if (heatIndex < 41) {
            System.out.println("Peligro: Calambres por calor y agotamiento probable");
        } else if (heatIndex < 54) {
            System.out.println("Peligro extremo: Insolación muy probable");
        } else {
            System.out.println("Peligro extremo: Insolación o golpe de calor inminente");
        }
    }
}
