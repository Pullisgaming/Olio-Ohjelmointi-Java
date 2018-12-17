public class JuomaAutomaatti {
	private int tee;
	private int kahvia;
	private int kaakaota;

	public JuomaAutomaatti(int tee, int kahvia, int kaakaota) {
		this.tee = tee;
		this.kahvia = kahvia;
		this.kaakaota = kaakaota;
	}

	public JuomaAutomaatti() {
		this.tee = 50;
		this.kahvia = 50;
		this.kaakaota = 50;
	}

	public void valmistaKahvia() {
		if (kahvia >= 10) {
			kahvia = (kahvia - 10);
			System.out.println("Odota hetki! Kahvisi valmistuu " + kahvia + " yksikköä jäljellä");

		} else {
			System.out.println("Kahvi lopussa valitse toinen tuote");
		}
	}

	public void valmistaTee() {
		if (tee >= 10) {
			tee -= 10;
			System.out.println("Odota hetki! Teesi valmistuu " + tee + " yksikköä jäljellä");

		} else {
			System.out.println("Tee lopussa valitse toinen tuote");
		}
	}

	public void valmistaKaakaota() {
		if (kaakaota >= 10) {
			kaakaota -= 10;
			System.out.println("Odota hetki! Kahvisi valmistuu " + kaakaota + " yksikköä jäljellä");

		} else {
			System.out.println("Kaakao lopussa valitse toinen tuote");
		}
	}
}