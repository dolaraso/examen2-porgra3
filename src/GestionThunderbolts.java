import javax.swing.*;
import java.util.*;

public class GestionThunderbolts {
    public List<Thunderbolt> listaThunderbolts;

    public GestionThunderbolts() {
        listaThunderbolts = new ArrayList<>();
    }

    public void agregarThunderbolt(Thunderbolt t) {
        for (Thunderbolt tb : listaThunderbolts) {
            if (tb.getCodigo() == t.getCodigo()) {
                JOptionPane.showMessageDialog(null, "El código ya existe.");
                return;
            }
        }
        listaThunderbolts.add(0, t);
    }

    public Thunderbolt buscarThunderbolt(int codigo) {
        for (Thunderbolt tb : listaThunderbolts) {
            if (tb.getCodigo() == codigo) {
                return tb;
            }
        }
        JOptionPane.showMessageDialog(null, "No se encontró el Thunderbolt.");
        return null;
    }

    public List<Thunderbolt> filtrarPorHabilidad(String habilidad) {
        List<Thunderbolt> filtrados = new ArrayList<>();
        for (Thunderbolt tb : listaThunderbolts) {
            if (tb.getHabilidadPrincipal().equals(habilidad)) {
                filtrados.add(tb);
            }
        }
        return filtrados;
    }

    public void ordenarPorNivelRedencion(List<Thunderbolt> lista) {
        for (int i = 0; i < lista.size() - 1; i++) {
            for (int j = 0; j < lista.size() - i - 1; j++) {
                if (lista.get(j).getNivelRedencion() > lista.get(j + 1).getNivelRedencion()) {
                    Thunderbolt temp = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, temp);
                }
            }
        }
    }

    public void contarMisionesRecursivo(List<Thunderbolt> lista, int index, Map<String, Integer> misionesPorHabilidad) {
        if (index >= lista.size()) {
            return;
        }
        Thunderbolt tb = lista.get(index);
        String habilidad = tb.getHabilidadPrincipal();
        misionesPorHabilidad.put(habilidad, misionesPorHabilidad.getOrDefault(habilidad, 0) + 1);
        contarMisionesRecursivo(lista, index + 1, misionesPorHabilidad);
    }

    public List<Thunderbolt> getListaThunderbolts() {
        return listaThunderbolts;
    }
}
