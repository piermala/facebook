package main;

import java.util.ArrayList;
import java.util.List;

import facebook.Eccezione;
import facebook.Facebook;
import facebook.Utente;

public class FacebookMain {
	

	public static void main(String[] args) throws Eccezione {
		
		Facebook f = new Facebook();
		 Utente u = new Utente();
		
		
		Utente u1 = new Utente("P", "M", 23, 'M', "ciao@mail.com");
		Utente u2 = new Utente("C", "M", 26, 'M', "ciaobello@mail.com");
		Utente u3 = new Utente("A", "M", 27, 'M', "ciaobella@mail.com");
		Utente u4 = new Utente("M", "MG", 27, 'M', "ciaobelli@mail.com");
		Utente u5 = new Utente("E", "MTL", 27, 'M', "ciadddò@mail.com");
		Utente u6 = new Utente("C", "DA", 22, 'M', "tiziocaio@gmail.com");
		
		f.registraUtente(u1.getNome(), u1.getCognome(), u1.getEtà(), u1.getSesso(), u1.getEmail());
		f.registraUtente(u2.getNome(), u2.getCognome(), u2.getEtà(), u2.getSesso(), u2.getEmail());
		f.registraUtente(u3.getNome(), u3.getCognome(), u3.getEtà(), u3.getSesso(), u3.getEmail());
		f.registraUtente(u4.getNome(), u4.getCognome(), u4.getEtà(), u4.getSesso(), u4.getEmail());
		f.registraUtente(u5.getNome(), u5.getCognome(), u5.getEtà(), u5.getSesso(), u5.getEmail());
		f.registraUtente(u6.getNome(), u6.getCognome(), u6.getEtà(), u6.getSesso(), u6.getEmail());
		
	
		
		////VISUALIZZIAMO QUANTI UTENTI ABBIAMO
		System.out.println("Utenti di facebook: ");
		f.stampaUtenti();
		
		
		///AGGIUNGIAMO GLI AMICI
		System.out.println("\n\nAGGIUNGI AMICI");
		f.addAmici("P M", "C M");
		f.addAmici("P M", "A M");
		f.addAmici("P M", "E MTL");
		f.addAmici("P M", "M MG");
		f.addAmici("M MG", "C DA");
		f.addAmici("P M", "C DA");   /// non prende né l'uno nè l'altro perché P M è l'utente di riferimento, e C DA è presente anche nei suoi amici
		f.addAmici("M MG", "E MTL");
		
		System.out.println("\n ***AMICI DI AMICI***");	
		System.out.println(f.getAmiciDiAmici("M", "MG"));
		System.out.println(f.getAmiciDiAmici("M", "MG").size());
		
		
		/// AMICI IN COMUNE
		System.out.println("\n\n***AMICI IN COMUNE***");
		System.out.println(f.getAmiciInComune("P M", "M MG"));
		
		
		/// CERCA UTENTE
		System.out.println("\n\n**** CERCA UTENTE ****");
		System.out.println(f.cercaUtente("P M"));
		
		
		/// CERCA AMICI
		System.out.println("\n\n **** CERCA AMICI ****");
		System.out.println(f.cercaAmico(u6, "M MG"));
		
	}
}



