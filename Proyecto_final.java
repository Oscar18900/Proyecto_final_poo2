/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto_final;

/**
 *
 * @author Oscar
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Proyecto_final {
    // Listas globales para almacenar datos
    private static ArrayList<Vacuna> vacunas = new ArrayList<>();
    private static ArrayList<CitaMedica> citas = new ArrayList<>();
    private static ArrayList<Medicamento> medicamentos = new ArrayList<>();
    private static Usuario usuario;

    public static void main(String[] args) {
        // Crear la ventana principal
        JFrame frame = new JFrame("Sistema de Gestión para Pacientes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Pantalla completa
        frame.setLayout(new BorderLayout());

        // Panel superior: título
        JLabel titulo = new JLabel("Bienvenido al Sistema de Gestión para Pacientes", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        frame.add(titulo, BorderLayout.NORTH);

        // Panel central: botones de opciones
        JPanel panelCentral = new JPanel(new GridLayout(5, 1, 10, 10));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

        JButton btnRegistrarUsuario = new JButton("Registrar Usuario");
        JButton btnVacunas = new JButton("Añadir/Ver Vacunas");
        JButton btnCitas = new JButton("Añadir/Ver Citas Médicas");
        JButton btnMedicamentos = new JButton("Añadir/Ver Medicamentos");
        JButton btnPrioridades = new JButton("Ver Prioridades");

        panelCentral.add(btnRegistrarUsuario);
        panelCentral.add(btnVacunas);
        panelCentral.add(btnCitas);
        panelCentral.add(btnMedicamentos);
        panelCentral.add(btnPrioridades);

        frame.add(panelCentral, BorderLayout.CENTER);

        // Panel inferior: créditos
        JLabel creditos = new JLabel("Proyecto para ODS 3 - Meta 3.3", SwingConstants.CENTER);
        creditos.setFont(new Font("Arial", Font.ITALIC, 14));
        frame.add(creditos, BorderLayout.SOUTH);

        // **Acciones para cada botón**
        // Botón "Registrar Usuario"
        btnRegistrarUsuario.addActionListener(e -> registrarUsuario(frame));

        // Botón "Añadir/Ver Vacunas"
        btnVacunas.addActionListener(e -> gestionarVacunas(frame));

        // Botón "Añadir/Ver Citas Médicas"
        btnCitas.addActionListener(e -> gestionarCitas(frame));

        // Botón "Añadir/Ver Medicamentos"
        btnMedicamentos.addActionListener(e -> gestionarMedicamentos(frame));

        // Botón "Ver Prioridades"
        btnPrioridades.addActionListener(e -> mostrarPrioridades(frame));

        // Mostrar la ventana principal
        frame.setVisible(true);
    }

    /**
     * Método para registrar un usuario
     */
    private static void registrarUsuario(JFrame frame) {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        JTextField nombreField = new JTextField();
        JTextField apellidosField = new JTextField();
        JTextField edadField = new JTextField();
        JTextField pesoField = new JTextField();
        JTextField alturaField = new JTextField();
        JTextField sintomasField = new JTextField();

        panel.add(new JLabel("Nombre:"));
        panel.add(nombreField);
        panel.add(new JLabel("Apellidos:"));
        panel.add(apellidosField);
        panel.add(new JLabel("Edad:"));
        panel.add(edadField);
        panel.add(new JLabel("Peso:"));
        panel.add(pesoField);
        panel.add(new JLabel("Altura:"));
        panel.add(alturaField);
        panel.add(new JLabel("Síntomas:"));
        panel.add(sintomasField);

        int result = JOptionPane.showConfirmDialog(frame, panel, "Registrar Usuario", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                usuario = new Usuario(
                        nombreField.getText(),
                        apellidosField.getText(),
                        Integer.parseInt(edadField.getText()),
                        Double.parseDouble(pesoField.getText()),
                        Double.parseDouble(alturaField.getText()),
                        sintomasField.getText()
                );
                JOptionPane.showMessageDialog(frame, "Usuario registrado exitosamente.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Error: Datos inválidos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Método para gestionar vacunas
     */
    private static void gestionarVacunas(JFrame frame) {
        // Ventana para añadir/ver vacunas
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        JTextField nombreVacunaField = new JTextField();
        JTextField fechaVacunaField = new JTextField();

        panel.add(new JLabel("Nombre de la Vacuna:"));
        panel.add(nombreVacunaField);
        panel.add(new JLabel("Fecha de Aplicación (YYYY-MM-DD):"));
        panel.add(fechaVacunaField);

        int result = JOptionPane.showConfirmDialog(frame, panel, "Añadir Vacuna", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String nombre = nombreVacunaField.getText();
                LocalDate fecha = LocalDate.parse(fechaVacunaField.getText());
                vacunas.add(new Vacuna(nombre, fecha));
                JOptionPane.showMessageDialog(frame, "Vacuna añadida exitosamente.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error: Datos inválidos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Mostrar vacunas registradas
        StringBuilder sb = new StringBuilder("Vacunas Registradas:\n");
        for (Vacuna v : vacunas) {
            sb.append(v.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(frame, sb.toString(), "Vacunas", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Método para gestionar citas médicas
     */
    private static void gestionarCitas(JFrame frame) {
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        JTextField motivoCitaField = new JTextField();
        JTextField fechaCitaField = new JTextField();

        panel.add(new JLabel("Motivo de la Cita:"));
        panel.add(motivoCitaField);
        panel.add(new JLabel("Fecha de la Cita (YYYY-MM-DD):"));
        panel.add(fechaCitaField);

        int result = JOptionPane.showConfirmDialog(frame, panel, "Añadir Cita Médica", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String motivo = motivoCitaField.getText();
                LocalDate fecha = LocalDate.parse(fechaCitaField.getText());
                citas.add(new CitaMedica(motivo, fecha));
                JOptionPane.showMessageDialog(frame, "Cita añadida exitosamente.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error: Datos inválidos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Mostrar citas registradas
        StringBuilder sb = new StringBuilder("Citas Registradas:\n");
        for (CitaMedica c : citas) {
            sb.append(c.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(frame, sb.toString(), "Citas", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Método para mostrar prioridades
     */
    private static void mostrarPrioridades(JFrame frame) {
        ArrayList<Object> prioridades = PrioridadManager.calcularPrioridades(vacunas, citas);

        StringBuilder sb = new StringBuilder("Prioridades:\n");
        for (Object o : prioridades) {
            sb.append(o.toString()).append("\n");
        }

        JOptionPane.showMessageDialog(frame, sb.toString(), "Prioridades", JOptionPane.INFORMATION_MESSAGE);
    }
}
