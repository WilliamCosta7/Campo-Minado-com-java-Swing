package br.com.udemy.cm.visao;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import br.com.udemy.cm.modelo.Tabuleiro;

@SuppressWarnings("serial")
public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {
        setTitle("Campo Minado - William Costa");
        setSize(690, 438);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        adicionarBotoesNiveis();
        setVisible(true);
    }

    private void adicionarBotoesNiveis() {
        JPanel painelBotoes = new JPanel();
        
        JButton btnFacil = new JButton("Fácil");
        btnFacil.addActionListener(e -> iniciarJogo(9, 9, 10));
        painelBotoes.add(btnFacil);
        
        JButton btnMedio = new JButton("Médio");
        btnMedio.addActionListener(e -> iniciarJogo(16, 20, 50));
        painelBotoes.add(btnMedio);
        
        JButton btnDificil = new JButton("Difícil");
        btnDificil.addActionListener(e -> iniciarJogo(16, 30, 99));
        painelBotoes.add(btnDificil);
        
        add(painelBotoes);
    }

    private void iniciarJogo(int linhas, int colunas, int minas) {
        getContentPane().removeAll(); // Remove qualquer componente anterior
        Tabuleiro tabuleiro = new Tabuleiro(linhas, colunas, minas);
        add(new PainelTabuleiro(tabuleiro));
        revalidate(); // Atualiza o conteúdo do frame
    }

    public static void main(String[] args) {
        new TelaPrincipal();
    }
}
