package TecnoViaje;

import java.time.LocalDateTime;

public class RegistroDeViajes {
    private LocalDateTime fechaYHora;
    private String origen;
    private String destino;
    private double distancia;
    private double costo;

    public RegistroDeViajes(LocalDateTime fechaYHora, String origen, String destino, double distancia, double costo) {
        this.fechaYHora = fechaYHora;
        this.origen = origen;
        this.destino = destino;
        this.distancia = distancia;
        this.costo = costo;
    }

    // Getters and Setters
    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
}