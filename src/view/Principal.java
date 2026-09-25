package view;

import javax.swing.SwingUtilities;

// Aluno: Guilherme Relva Paiva
public class Principal {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaInvestimento().setVisible(true);
            }
        });
    }
}