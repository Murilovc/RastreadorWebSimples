package com.mvc.webcrawler.principal;

import javax.swing.SwingUtilities;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.mvc.webcrawler.gui.JanelaInfo;

public class Principal {

	public static void main(String[] args) {
		//var url = "https://www.calculusmadeeasy.org/";
		FlatDarculaLaf.setup();
		
		SwingUtilities.invokeLater(() -> {
			JanelaInfo f = new JanelaInfo();
			f.setVisible(true);
		});
	}
}
