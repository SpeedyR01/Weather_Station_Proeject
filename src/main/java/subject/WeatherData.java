package subject;

import observer.Observer;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase WeatherData que implementa Subject
 * Mantiene los datos meteorológicos y notifica a los observadores cuando cambian
 */
public class WeatherData implements Subject {
    private List<Observer> observers;
    private double temperature;
    private double humidity;
    private double pressure;
    
    /**
     * Constructor de WeatherData
     * Inicializa la lista de observadores
     */
    public WeatherData() {
        observers = new ArrayList<>();
    }
    
    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
        System.out.println("Observador registrado: " + o.getClass().getSimpleName());
    }
    
    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
        System.out.println("Observador removido: " + o.getClass().getSimpleName());
    }
    
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
    
    /**
     * Método llamado cuando las mediciones han cambiado
     */
    public void measurementsChanged() {
        notifyObservers();
    }
    
    /**
     * Establece nuevas mediciones y notifica a los observadores
     * @param temperature temperatura en grados Celsius
     * @param humidity humedad relativa (0-100)
     * @param pressure presión atmosférica en hPa
     */
    public void setMeasurements(double temperature, double humidity, double pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }
    
    // Getters para que los displays puedan obtener los datos
    public double getTemperature() {
        return temperature;
    }
    
    public double getHumidity() {
        return humidity;
    }
    
    public double getPressure() {
        return pressure;
    }
}
