package com.mvc.webcrawler.principal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.mvc.webcrawler.gui.JanelaInfo;

public class Rastreador {
	
	public List<String> lista = new ArrayList<>();
	public JanelaInfo ji;
	
	public void init(StyledDocument doc, String url, int nivel, JanelaInfo ji) {
		var exec = new Thread(() -> {
			this.ji = ji;
			rastrear(url, nivel);
		});
		exec.start();
	}
	
	public void rastrear(String url, int nivel) {
		Document doc = request(url);
		if(doc != null) {
			var linksDaPagina = doc.select("a[href]");
			
			for(var l : linksDaPagina) {
				//System.out.println(l.toString());
				if(nivel != 2 && !(l.absUrl("href").isEmpty())) {
					rastrear(l.absUrl("href"), nivel+1);
				}
			}
		}
	}
		
	public Document request(String url) {
		try {
			var con = Jsoup.connect(url);
			Document doc = con.get();
						
			if(con.response().statusCode() == 200) {
				System.out.println("Link: "+ url);
				System.out.println(doc.title());
				lista.add(url);
				try {
					ji.doc.insertString(0, url+"\n", null);
					ji.repaint();
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
				return doc;
			}
			return null;
		} catch(IOException e) {
			return null;
		}
	}
}
