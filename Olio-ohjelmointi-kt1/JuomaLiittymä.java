import java.util.Scanner;

public class JuomaLiittymä {

	public static void main(String[] args) {
		Scanner lukija = new Scanner(System.in);
		JuomaAutomaatti omakone = new JuomaAutomaatti();
		int i;
		int valinta = 0;

		for (i = 0; i != 4;) {
			System.out.println("*******Juoma-Automaatti*******");
			System.out.println("");
			System.out.println("1. kahvi");
			System.out.println("2. Tee");
			System.out.println("3. Kaakao");
			System.out.println("4. Lopeta");
			System.out.println("");
			System.out.println("******************************");
			valinta = lukija.nextInt();
			i = valinta;
			if (valinta == 1) {
				omakone.valmistaKahvia();
			}
			if (valinta == 2) {
				omakone.valmistaTee();
			}
			if (valinta == 3) {
				omakone.valmistaKaakaota();
			}
			if (valinta == 4) {
				System.out.println("Ohjelma lopetetaan");
			}
		}

	}
}