package FuncionFinal;

import java.time.LocalDateTime;
import java.util.List;

import TecnoViaje.RegistroDeViajes;
import ServicioDeTransito.RegistroDeMovibilidad;

public class TecnoViajeapp {

    public static void main(String[] args) {
        List<RegistroDeViajes> registros = List.of(
            new RegistroDeViajes(LocalDateTime.now(), "Estacion Norte", "Estacion Centro", 5.2, 2500.0),
            new RegistroDeViajes(LocalDateTime.now().plusMinutes(10), "Estacion Norte", "Estacion Sur", 8.0, 3000.0),
            new RegistroDeViajes(LocalDateTime.now().plusMinutes(25), "Estacion Centro", "Estacion Sur", 3.5, 2000.0),
            new RegistroDeViajes(LocalDateTime.now().plusMinutes(40), "Estacion Sur", "Estacion Norte", 8.0, 3000.0)
        );

        RegistroDeMovibilidad servicio = new RegistroDeMovibilidad();

        System.out.println("=== RESULTADOS DEL SISTEMA TECNOMOVIL DATA ===");
        System.out.println("a) Afluencia por Estación: " + servicio.calcularFrecuenciaPorEstacion(registros));
        System.out.println("b) Horas Pico: " + servicio.identificarHorasPico(registros));
        System.out.println("c) Rutas Usadas: " + servicio.calcularRutasMasUtilizadas(registros));
        System.out.println("d) Patrones de Viaje: " + servicio.obtenerPatronesViaje(registros));
        System.out.println("e) Tiempo Promedio: " + servicio.calcularTiempoPromedioEntreEstaciones(registros) + " min");
        System.out.println("f) Rutas Críticas (Umbral > 1): " + servicio.detectarRutasCriticas(registros, 1L));
    }
}