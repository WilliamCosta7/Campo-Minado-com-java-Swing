package br.com.udemy.cm.visao;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import br.com.udemy.cm.modelo.Campo;
import br.com.udemy.cm.modelo.CampoEvento;
import br.com.udemy.cm.modelo.CampoObservador;

@SuppressWarnings("serial")
public class BotaoCampo extends JButton implements CampoObservador, MouseListener {
	
	private final Color BG_PADRAO = new Color(184, 184,184);
	private final Color BG_MARCADO = new Color(8, 179, 247);
	private final Color BG_EXPLOSAO = new Color(189, 66, 68);
	private final Color TEXTO_VERDE = new Color(0 , 100 , 0);
	ImageIcon iconeBombinha = new ImageIcon("src/recursos/bombinha.png");
	ImageIcon iconeBandeira = new ImageIcon("src/recursos/bandeira.png");
	private Campo campo;

	public BotaoCampo(Campo campo) {
		redimensionarImagem();
		this.campo = campo;
		setBackground(BG_PADRAO);
		setBorder(BorderFactory.createBevelBorder(0));
		addMouseListener(this);
		campo.registrarObservador(this);

	}
	
	void redimensionarImagem() {
		Image imagemBombinha = iconeBombinha.getImage().getScaledInstance(23, 27, Image.SCALE_SMOOTH);
		Image imagemBandeira = iconeBandeira.getImage().getScaledInstance(35, 27, Image.SCALE_SMOOTH);
		iconeBombinha = new ImageIcon(imagemBombinha);
		iconeBandeira = new ImageIcon(imagemBandeira);
	}

	@Override
	public void eventoOcorreu(Campo campo, CampoEvento evento) {
		switch (evento) {
		case ABRIR:
			aplicarEstiloAbrir();
			break;
		case MARCAR:
			AplicarEstiloMarcar();
			break;
		case EXPLODIR:
			aplicarEstiloExplodir();
			break;
		default:
			aplicarEstiloPadrao();
		}
		
		SwingUtilities.invokeLater(() ->{
			repaint();
			validate();
		});
	}

	private void aplicarEstiloPadrao() {
		setBackground(BG_PADRAO);
		setBorder(BorderFactory.createBevelBorder(0));
		 setIcon(null);
		setText("");
	}

	private void aplicarEstiloExplodir() {
		setBackground(BG_EXPLOSAO);
		setIcon(iconeBombinha);
	}

	private void AplicarEstiloMarcar() {
		setBackground(BG_MARCADO);
		setIcon(iconeBandeira);
	}

	private void aplicarEstiloAbrir() {
	setBorder(BorderFactory.createLineBorder(Color.GRAY));
	
	if(campo.isMinado()) {
		setIcon(iconeBombinha);
	}
	
	setBackground(BG_PADRAO);
	switch (campo.minasNaVizinhaca()) {
	case 1:
		setForeground(TEXTO_VERDE);
		break;
	case 2: 
		setForeground(Color.BLUE);
		break;
	case 3:
	setForeground(Color.YELLOW);
	break;
	case 4:
	case 5:
	case 6:
		setForeground(Color.RED);
		break;
		default:
			setForeground(Color.PINK);
	}
	if(!campo.isMinado()) {
		String valor = !campo.vizinhacaSegura() ?campo.minasNaVizinhaca() +"" : "";
		setText(valor);
	}
	}
		
	
	//	INTERFACE DOS EVENTOS DO MOUSE
	
	public void mousePressed(MouseEvent e) {
		if(e.getButton() ==1) {
			campo.abrir();
		} else {
			campo.alternarMarcacao();
		}
	}
	
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
}
