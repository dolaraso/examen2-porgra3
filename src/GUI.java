import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.*;

public class GUI {
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JComboBox<String> comboBox1;
    private JComboBox<Integer> comboBox2;
    private JComboBox<String> comboBox3;
    private JTable table1;
    private JButton AGREGARTHUNDERBOLTButton;
    private JButton BUSCARTHUNDERBOLTButton1;
    private JButton FILTRARYORDENARButton;
    private JButton CONTEODEMISIONESButton;
    private GestionThunderbolts gestionThunderbolts;
    private DefaultTableModel tableModel;

    public GUI() {
        gestionThunderbolts = new GestionThunderbolts();
        tableModel = new DefaultTableModel(new Object[]{"Código", "Nombre", "Habilidad Principal", "Nivel de Redención", "Misión Asignada"}, 0);
        table1.setModel(tableModel);

        AGREGARTHUNDERBOLTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = textField2.getText();
                int codigo;
                try {
                    codigo = Integer.parseInt(textField1.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel1, "Código debe ser un número.");
                    return;
                }
                String habilidad = (String) comboBox1.getSelectedItem();
                String selectedItem = (String) comboBox2.getSelectedItem();
                int nivelRedencion;
                try {
                    nivelRedencion = Integer.parseInt(selectedItem);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel1, "Nivel de Redención debe ser un número.");
                    return;
                }
                String mision = (String) comboBox3.getSelectedItem();
                Thunderbolt thunderbolt = new Thunderbolt(codigo, nombre, habilidad, nivelRedencion, mision);
                gestionThunderbolts.agregarThunderbolt(thunderbolt);
                actualizarTabla();
            }
        });

        BUSCARTHUNDERBOLTButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codigo;
                try {
                    codigo = Integer.parseInt(JOptionPane.showInputDialog(panel1, "Ingrese el código del Thunderbolt a buscar"));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel1, "Código inválido.");
                    return;
                }
                Thunderbolt t = gestionThunderbolts.buscarThunderbolt(codigo);
                if (t != null) {
                    String message = "Thunderbolt Encontrado:\n" +
                            "Código: " + t.getCodigo() + "\n" +
                            "Nombre: " + t.getNombre() + "\n" +
                            "Habilidad Principal: " + t.getHabilidadPrincipal() + "\n" +
                            "Nivel de Redención: " + t.getNivelRedencion() + "\n" +
                            "Misión Asignada: " + t.getMisionAsignada();
                    JOptionPane.showMessageDialog(panel1, message, "Información del Thunderbolt", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(panel1, "Thunderbolt no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        FILTRARYORDENARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String habilidad = (String) comboBox1.getSelectedItem();
                List<Thunderbolt> filtrados = gestionThunderbolts.filtrarPorHabilidad(habilidad);
                gestionThunderbolts.ordenarPorNivelRedencion(filtrados);
                actualizarTabla(filtrados);
            }
        });

        CONTEODEMISIONESButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String habilidad = (String) comboBox1.getSelectedItem();
                String mision = (String) comboBox3.getSelectedItem();
                Map<String, Integer> misionesPorHabilidadYMision = new HashMap<>();
                List<Thunderbolt> listaThunderbolts = gestionThunderbolts.getListaThunderbolts();
                for (Thunderbolt t : listaThunderbolts) {
                    if (t.getHabilidadPrincipal().equals(habilidad) && t.getMisionAsignada().equals(mision)) {
                        String key = habilidad + "-" + mision;
                        misionesPorHabilidadYMision.put(key, misionesPorHabilidadYMision.getOrDefault(key, 0) + 1);
                    }
                }
                StringBuilder sb = new StringBuilder("Conteo de misiones para la habilidad " + habilidad + " y misión " + mision + ":\n");
                if (misionesPorHabilidadYMision.isEmpty()) {
                    sb.append("No se encontraron misiones para esta combinación.");
                } else {
                    for (Map.Entry<String, Integer> entry : misionesPorHabilidadYMision.entrySet()) {
                        sb.append(entry.getKey()).append(": ").append(entry.getValue()).append(" veces\n");
                    }
                }
                JOptionPane.showMessageDialog(panel1, sb.toString());
            }
        });
    }

    public void actualizarTabla() {
        actualizarTabla(gestionThunderbolts.getListaThunderbolts());
    }

    public void actualizarTabla(List<Thunderbolt> lista) {
        tableModel.setRowCount(0);
        for (Thunderbolt t : lista) {
            tableModel.addRow(new Object[]{
                    t.getCodigo(),
                    t.getNombre(),
                    t.getHabilidadPrincipal(),
                    t.getNivelRedencion(),
                    t.getMisionAsignada()
            });
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Gestion de Thunderbolts");
        frame.setContentPane(new GUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
