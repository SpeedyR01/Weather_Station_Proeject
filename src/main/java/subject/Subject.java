package subject;

import observer.Observer;

/**
 * Interfaz Subject del patrón Observer
 * Define los métodos para gestionar observadores
 */
public interface Subject {
    /**
     * Registra un nuevo observador
     * @param o el observador a registrar
     */
    void registerObserver(Observer o);
    
    /**
     * Remueve un observador
     * @param o el observador a remover
     */
    void removeObserver(Observer o);
    
    /**
     * Notifica a todos los observadores registrados
     */
    void notifyObservers();
}
