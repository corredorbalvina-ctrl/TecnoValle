package ServicioDeTránsito;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import TecnoViaje.RegistroDeViajes;


public class RegistroDeMovibilidad {
public class ProcesadorTransitoServicio {

    // a) Cálculo de afluencia por estación
    public Map<String, Long> calcularFrecuenciaPorEstacion(List<RegistroViaje> registros) {
        return registros.stream()
            .filter(r -> "entrada".equalsIgnoreCase(r.accion()))
            .collect(Collectors.groupingBy(r -> r.estacion(), Collectors.counting()));
    }

    // b) Identificación de horas pico
    public Map<Integer, Long> identificarHorasPico(List<RegistroViaje> registros) {
        return registros.stream()
            .collect(Collectors.groupingBy(r -> r.timestamp().getHour(), Collectors.counting()));
    }

    // c) Rutas más utilizadas
    public Map<String, Long> calcularRutasMasUtilizadas(List<RegistroViaje> registros) {
        return registros.stream()
            .collect(Collectors.groupingBy(r -> r.ruta(), Collectors.counting()));
    }

    // d) Patrones de viaje por usuario
    public Map<String, List<String>> obtenerPatronesViajePorUsuario(List<RegistroViaje> registros) {
        return registros.stream()
            .sorted(Comparator.comparing(r -> r.timestamp()))
            .collect(Collectors.groupingBy(
                r -> r.idUsuario(),
                Collectors.mapping(r -> r.estacion(), Collectors.toList())
            ));
    }

    // e) Cálculo de tiempo promedio entre estaciones
    public double calcularTiempoPromedioEntreEstaciones(List<RegistroViaje> registros) {
        Map<String, List<RegistroViaje>> viajesPorUsuario = registros.stream()
            .sorted(Comparator.comparing(r -> r.timestamp()))
            .collect(Collectors.groupingBy(r -> r.idUsuario()));

        return viajesPorUsuario.values().stream()
            .mapToDouble(lista -> {
                long minutosTotales = 0;
                int conteo = 0;
                for (int i = 0; i < lista.size() - 1; i++) {
                    if ("entrada".equalsIgnoreCase(lista.get(i).accion()) && 
                        "salida".equalsIgnoreCase(lista.get(i + 1).accion())) {
                        minutosTotales += Duration.between(lista.get(i).timestamp(), lista.get(i + 1).timestamp()).toMinutes();
                        conteo++;
                    }
                }
                return conteo > 0 ? (double) minutosTotales / conteo : 0.0;
            })
            .average()
            .orElse(0.0);
    }

    // f) Detección de sobrecarga en rutas
    public List<String> detectarRutasCriticas(List<RegistroViaje> registros, long umbralSobrecarga) {
        return registros.stream()
            .collect(Collectors.groupingBy(r -> r.ruta(), Collectors.counting()))
            .entrySet().stream()
            .filter(entry -> entry.getValue() > umbralSobrecarga)
            .map(entry -> entry.getKey())
            .collect(Collectors.toList());
    }
}
}