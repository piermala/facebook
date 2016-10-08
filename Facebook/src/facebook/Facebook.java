package facebook;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Facebook {
	
	private ArrayList<Utente> utenti= new ArrayList<Utente>();
	
	public Facebook(){
		
	}

	
	public ArrayList<Utente> getUtenti() {
		return utenti;
	}

	public void setUtenti(ArrayList<Utente> utenti) {
		this.utenti = utenti;
	}	 
	
	
	
	
	/// REGISTRA UTENTE
	public void registraUtente(String nome, String cognome, int età, char sesso, String email) throws Eccezione{
		boolean esistente = false;
		Utente u = new Utente(nome,cognome,età,sesso,email);
		for (int i=0; i<utenti.size(); i++){
			if (utenti.get(i).getEmail().equals(email)){
				esistente=true;
			}
		}
		
		if (esistente==false && email.contains("@")){
			utenti.add(u);
		} else if (esistente){
			System.out.println("Utente già esistente!");
		} else if (email.contains("@") == false){
			System.out.println("Errore: email errata!");
		}
	}
		
		
		
	///AGGIUNGI AMICI 
	public void addAmici(String nomeCognome1, String nomeCognome2){
		Utente u1 = null;
		Utente u2 = null;
		for (Utente u : utenti){
			String strTemp = u.getNome() + " " + u.getCognome();			
			if (nomeCognome1.equals(strTemp)){
				u1 = u;
			}
		}
		for (Utente u : utenti){
			String strTemp = u.getNome() + " " + u.getCognome();			
			if (nomeCognome2.equals(strTemp)){
				u2 = u;
			}
		}
		
		if(u1 != null && u2 != null){
			u1.aggiungiAmico(u2);
			u2.aggiungiAmico(u1);
		} else {
			System.out.println("Errore! ");
		}		
	}
	
	
	////STAMPA UTENTI
	public void stampaUtenti(){
		for (Utente u : utenti ){
			System.out.println(u);
		}
	}
	
	
	///NUMERO AMICI 
	public List<Utente> getAmiciDiUnUtente(String nome, String cognome){
		Utente utente = null;
		for (Utente u : utenti){
			if (u.getNome().equals(nome) && u.getCognome().equals(cognome)){
				utente = u;
			}
		} 		
		if (utente != null){
			return utente.getAmici();
		} else {
			return null;
		}
	}
	
	
	
	
	/// AMICI DI AMICI
	public List<Utente> getAmiciDiAmici(String nome, String cognome){
	
		// verifichiamo se tale utente esista
		Utente u = null;
		List<Utente> amiciPrimoUtente = new ArrayList<Utente>();
		List<Utente> amiciAmici = new ArrayList<Utente>();
		List<Utente> amiciTotali = new ArrayList<Utente>();
		
		for (int i=0; i<utenti.size(); i++){
			if (utenti.get(i).getNome() == nome && utenti.get(i).getCognome() == cognome){
				u = utenti.get(i);
			}
		}
		
		/// una volta verificato, prendiamo tutti i suoi amici
		if (u != null){
			amiciPrimoUtente = u.getAmici();
			for (Utente ut : amiciPrimoUtente){
				amiciAmici = ut.getAmici();
				for (Utente ute : amiciAmici){
						if (ute.getNome().equals(nome) == false && ute.getCognome().equals(cognome) == false && amiciPrimoUtente.contains(ute) == false){
							amiciTotali.add(ute);							
						}
				}
			}
		}
		
		return amiciTotali;
	}
	
	
	
	/// AMICI IN COMUNE
	public List<Utente> getAmiciInComune(String nomecognome1, String nomecognome2){
		
		Utente u1=null;
		Utente u2=null;
		List<Utente> amiciInComune = new ArrayList<Utente>();
		String str = "";
		
		for (Utente u : utenti){
			str=u.getNome()+" "+u.getCognome();
			if ((str.equals(nomecognome1))){
				u1 = u;
			} 
		}
		
		for (Utente u : utenti){
			str = u.getNome() +" "+u.getCognome();
			if (str.equals(nomecognome2)){
				u2 = u;
			}
		}
		
		
		/// una volta verificato, vediamo gli amici in comune
		if (u1!=null && u2!=null){
			for (Utente amico1 : u1.getAmici()){
				for (Utente amico2 : u2.getAmici()){
					if (amico1.equals(amico2)){
						amiciInComune.add(amico1);
					}
				}
			}
		} else {
			System.out.println("Uno o più utenti non esistono!");
		}
		
		System.out.print("AMICI IN COMUNE TRA " + u1.getNome() + " " + u1.getCognome() + " e " + u2.getNome() + " " + u2.getCognome() + ": ");
		System.out.println(amiciInComune.size());
		return amiciInComune;
		
	}
		 
	
	
	/// CERCA UTENTE
	public Utente cercaUtente(String nomecognome) throws Eccezione{
		
		Utente u = null;
		String str = "";
		
		for (Utente utente : utenti){
			str = utente.getNome() + " " + utente.getCognome();
			if (str.equals(nomecognome)){
				u = utente;
			}
		}
		
		if (u != null){
			return u;
		} else {
			throw new Eccezione("L'utente non esiste!");
		}		
	}
	
	
	
	//// CERCA AMICO
	/*public Utente cercaAmico(Utente utente, String nomecognome) throws Eccezione{
		Utente u = null;
		String str = "";
		
		for (Utente ut : utente.getAmici()){
			str = ut.getNome() + " " + ut.getCognome();
			if (str.equals(nomecognome)){
				u = ut;
			}
		}
		
		if (u != null){
			return u;
		} else {
			throw new Eccezione("L'amico cercato non esiste");
		}
	}*/
	
	
	/// CERCA AMICO
	public Utente cercaAmico(Utente utente, String nomecognome) throws Eccezione{
		Utente u = null;
		String str = "";
		
		for (Utente ut : getAmiciDiUnUtente(utente.getNome(), utente.getCognome())){
			str = ut.getNome() + " " + ut.getCognome();
			if (str.equals(nomecognome)){
				u = ut;
			}
		}
		
		if (u != null){
			return u;
		} else {
			throw new Eccezione("L'amico cercato non esiste!");
		}
	}
	
	
	
	
		
		
	
	
	

}
