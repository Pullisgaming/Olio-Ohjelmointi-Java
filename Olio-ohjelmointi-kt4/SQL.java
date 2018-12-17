package kt4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SQL {

	public static void tallennaAuto(Auto ajoneuvo) {
	
		String merkki = ajoneuvo.getAutonMerkki();
		String malli = ajoneuvo.getAutonMalli();
		String moottori = ajoneuvo.getAutonMoottori();
		
		try {

			// Luodaan tietokantayhteys
			Connection con = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net/sql7270386","sql7270386", "Vh9MWx7nXf");
			if (con != null) {
				System.out.println("Yhteys muodostettu");
			}
			
			// SQL insert lause
			String sql = "INSERT INTO Autot(Merkki,Malli,Moottori) values(?,?,?)";

			// Luodaan Statement-olio, joka keskustelee tietokannan kanssa
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, merkki);
			stmt.setString(2, malli);
			stmt.setString(3, moottori);

			stmt.execute();
			
			stmt.close();
			con.close();
			
			System.out.println("Uusi tieto on tallennettu");

		} catch (SQLException f) {
			System.out.println(f);
			f.printStackTrace();
		}
		
	}
	
	public static ArrayList<Object[]> AutotKannasta() {
		ArrayList<Object[]> Lista = new ArrayList<Object[]>();
		try {

			// Luodaan tietokantayhteys
			Connection con = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net/sql7270386",
					"sql7270386", "Vh9MWx7nXf");
			if (con != null) {
				System.out.println("Yhteys muodostettu");
			}

			// Luodaan Statement-olio, joka keskustelee tietokannan kanssa
			Statement stmt = con.createStatement();

			// Luodaan tulosjoukko, johon sijoitetaan kyselyn tulos
			ResultSet tulokset = stmt
					.executeQuery("SELECT Merkki, Malli, Moottori FROM Autot");

			// Tulosjoukko käydään silmukassa läpi

			while (tulokset.next()) {
				System.out.println(tulokset.getString(1) + "  " + tulokset.getString(2) + "  " + tulokset.getString(3));

				Lista.add(new Object[] { tulokset.getString(1), tulokset.getString(2), tulokset.getString(3) });

			}
			tulokset.close();
			stmt.close();
			con.close();

			// Varaudutaan virheisiin
		} catch (Exception h) {
			System.out.println(h);
		}
				
		return Lista;
	}	
}