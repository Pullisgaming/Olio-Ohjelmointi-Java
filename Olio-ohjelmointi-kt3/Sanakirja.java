import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;

// Luodaan string taulu, muuttujat ja hashmap sanaparit
public class Sanakirja {
	@SuppressWarnings({ "unchecked" })
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		String[] suomi = { "kissa", "koira", "hevonen", "auto", "vene" };
		String[] englanti = { "cat", "dog", "horse", "car", "boat" };
		String ekasana = "";
		String tokasana = "";
		String kolmassana = "";
		HashMap<String, String> sanaparit = new HashMap<>();
		
		for (int o = 0; o < suomi.length; o++) {
			sanaparit.put(suomi[o], englanti[o]);
		}
		
		
		Scanner lukija = new Scanner(System.in);
		Iterator<Entry<String, String>> ite = sanaparit.entrySet().iterator();
		while (ite.hasNext()) {
			HashMap.Entry<String, String> alkio = (HashMap.Entry<String, String>) ite.next();
		}
		
		//tiedoston virhe tarkistin
		XMLDecoder decoder = null;
		try {
			decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("Sanakirja.xml")));
		}catch (FileNotFoundException e) {
			FileOutputStream tiedosto = new FileOutputStream("Sanakirja.xml");
			XMLEncoder enc = new XMLEncoder(new BufferedOutputStream(tiedosto));
			enc.writeObject(sanaparit);
			enc.close();
			decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("Sanakirja.xml")));
		}
		
		
		sanaparit = (HashMap<String, String>) decoder.readObject();
		System.out.print("Sanakirjan sisältö: ");
		System.out.println(sanaparit);
		
		while (ekasana != null) {
		
			System.out.println("Valitse sana, jonka käännöksen haluat (kirjoita \"info\" saadaksesi ohje) ");
			ekasana = lukija.nextLine();
			
			//Tallennus ja ohjelman lopetus
			if (ekasana.equals("")) {
				FileOutputStream tiedosto = new FileOutputStream("Sanakirja.xml");
				XMLEncoder enc = new XMLEncoder(new BufferedOutputStream(tiedosto));
				enc.writeObject(sanaparit);
				enc.close();

				System.out.println("Ohjelma lopetetaan!");
				System.exit(0);
			}
			
			//print komennon tulos
			if (ekasana.equals("print")) {
				System.out.println("Käännökset:");
				Iterator<Entry<String, String>> it = sanaparit.entrySet().iterator();
				while (it.hasNext()) {
					HashMap.Entry<String, String> alkio = (HashMap.Entry<String, String>) it.next();
					System.out.println(alkio.getKey() + " = " + alkio.getValue());
				}
				System.out.println();
			}
			
			//Info komennon tulos
			if (ekasana.equals("info")) {
				System.out.println("Kirjoita \"add\" lisätäksesi käännöksiä.");
				System.out.println("Tyhjä komento lopettaa ohjelman.");
				System.out.println("Kirjoita \"save\" tallentaaksesi uudet käännökset.");
				System.out.println("Kirjoita \"print\" tulostaaksesi kaikki käännökset.");
				System.out.println();
			}
						
			//Virhe huomautus
			if (!sanaparit.containsKey(ekasana) && !ekasana.equals("add") && !ekasana.equals("info") && !ekasana.equals("save") && !ekasana.equals("print")) {
				System.out.println("Sanan \"" + ekasana + "\" käännöstä ei löytynyt!");	
			}
			
			// oikean komennon tulostus
			if (sanaparit.containsKey(ekasana)) {
				System.out.println("Sanan \"" + ekasana + "\" käännös on \"" + sanaparit.get(ekasana) + "\"" );
			}
			
			
			
			//add sanan tulos
			if (ekasana.equals("add")) {
				while (tokasana != null && kolmassana != null) {
					if (ekasana.equals("add")) {
						System.out.println("Anna suomenkielinen sana? (lopeta tyhjällä) "); 
						tokasana = lukija.nextLine();
						if (tokasana.equals("")) {
							break;
						}
						System.out.println("Anna sanan käännös? (lopeta tyhjällä) "); 
						kolmassana = lukija.nextLine();
						if (kolmassana.equals("")) {
							break;
						}
						sanaparit.put(tokasana, kolmassana);
					}	
				}
			}
		}
	}
}
