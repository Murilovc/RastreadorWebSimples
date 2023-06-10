package com.mvc.webcrawler.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.StyledDocument;

import com.mvc.webcrawler.principal.Rastreador;

@SuppressWarnings("serial")
public class JanelaInfo extends JFrame {
	
	public static EmptyBorder b = new EmptyBorder(10,10,10,10);
	public StyledDocument doc;
	
	public JanelaInfo() {
		this.setTitle("Rastreador Web Simples - por Murilo Vieira");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(new Dimension(400,290));
		
		adicionarComponentes();
	}

	private void adicionarComponentes() {
		var labelLink = new JLabel("Link: ");
		var campoLink = new JTextField();
		var campoLista = new JTextPane();
		//campoLista.setWrapStyleWord(true);
		campoLista.setForeground(Color.GREEN);
		campoLista.setBackground(Color.BLACK);
		campoLista.setEditable(false);
        doc = campoLista.getStyledDocument();
		var scroll = new JScrollPane(campoLista, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		var btIniciar = new JButton("Iniciar");
		btIniciar.addActionListener(clique -> {
			var url = campoLink.getText();
			
			var rastreador = new Rastreador();
			rastreador.init(doc, url, 1, this);
		});

		var pNorte = new JPanel(new BorderLayout());
		pNorte.add(labelLink, BorderLayout.WEST);
		pNorte.add(campoLink, BorderLayout.CENTER);
		
		var pTotal = new JPanel(new BorderLayout());
		pTotal.setBorder(b);
		pTotal.add(pNorte, BorderLayout.NORTH);
		pTotal.add(scroll, BorderLayout.CENTER);
		pTotal.add(btIniciar, BorderLayout.SOUTH);
		
		this.add(pTotal, BorderLayout.CENTER);
	}
}
