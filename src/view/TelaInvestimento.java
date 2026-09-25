package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import business.Aplicacao;

public class TelaInvestimento extends JFrame {

    private static final long serialVersionUID = 1L;

    private JLabel lblValor;
    private JLabel lblPrazo;
    private JLabel lblIndexador;
    private JLabel lblResultado;

    private JTextField txtValor;
    private JTextField txtPrazo;

    private JComboBox<String> cbIndexador;
    private JButton btnCalcular;

    public TelaInvestimento() {
        super("Simulador de Investimento");
        setLayout(null);
        setSize(420, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        iniciarComponentes();
    }

    private void iniciarComponentes() {
        lblValor = new JLabel("Valor aplicado (R$):");
        lblValor.setBounds(30, 20, 200, 25);
        add(lblValor);

        txtValor = new JTextField();
        txtValor.setBounds(30, 45, 340, 25);
        txtValor.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != '.' && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
                    e.consume();
                }
                if (c == '.' && txtValor.getText().contains(".")) {
                    e.consume();
                }
            }
        });
        add(txtValor);

        lblPrazo = new JLabel("Prazo (meses):");
        lblPrazo.setBounds(30, 80, 200, 25);
        add(lblPrazo);

        txtPrazo = new JTextField();
        txtPrazo.setBounds(30, 105, 340, 25);
        txtPrazo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
                    e.consume();
                }
            }
        });
        add(txtPrazo);

        lblIndexador = new JLabel("Indexador:");
        lblIndexador.setBounds(30, 140, 200, 25);
        add(lblIndexador);

        String[] taxas = { "Poupança", "CDI", "Tesouro Direto" };
        cbIndexador = new JComboBox<>(taxas);
        cbIndexador.setBounds(30, 165, 340, 25);
        add(cbIndexador);

        btnCalcular = new JButton("Calcular Rendimento");
        btnCalcular.setBounds(30, 210, 340, 35);
        btnCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcular();
            }
        });
        add(btnCalcular);

        lblResultado = new JLabel("Resultado: aguardando cálculo...");
        lblResultado.setBounds(30, 260, 340, 25);
        add(lblResultado);
    }

    private void calcular() {
        String vStr = txtValor.getText().trim();
        String pStr = txtPrazo.getText().trim();

        if (vStr.isEmpty() || pStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            float valor = Float.parseFloat(vStr);
            int prazo = Integer.parseInt(pStr);
            float taxa = 0.0f;

            String opcao = (String) cbIndexador.getSelectedItem();
            if ("Poupança".equals(opcao)) {
                taxa = 0.38f;
            } else if ("CDI".equals(opcao)) {
                taxa = 0.53f;
            } else if ("Tesouro Direto".equals(opcao)) {
                taxa = 0.65f;
            }

            Aplicacao app = new Aplicacao();
            app.calcularRendimento(valor, prazo, taxa);

            lblResultado.setText(String.format("Montante: R$ %.2f (Taxa: %.2f%% a.m.)", app.getMontante(), taxa));

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Valores informados são inválidos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}