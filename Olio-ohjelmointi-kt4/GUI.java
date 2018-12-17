package kt4;

import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Label;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Color;

public class GUI extends JFrame {
	private JPanel contentPane;
	private JTable taulu;
	private DefaultTableModel taulukko;
	private JScrollPane scrollPane;

	// Luodaan tietorakenne, jonne tietokannasta haetut rivit voidaan tallentaa
	private static ArrayList<Object[]> Lista = new ArrayList<Object[]>();

	public GUI() throws ParseException {

		// GUI:n alustaminen
		setTitle("TietokantaAutot");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		scrollPane = new JScrollPane(taulu);

		// taulukon alustaminen
		taulukko = new DefaultTableModel();
		taulukko.addColumn("Merkki");
		taulukko.addColumn("Malli");
		taulukko.addColumn("Moottori");
		contentPane.setLayout(null);

		taulu = new JTable(taulukko);

		scrollPane.setBounds(10, 0, 464, 250);
		scrollPane.setPreferredSize(new Dimension(275, 250));
		scrollPane.setBackground(Color.WHITE);
		contentPane.add(scrollPane);

		Label label = new Label("Auton Merkki:");
		label.setBounds(10, 256, 87, 23);
		contentPane.add(label);

		Label label_1 = new Label("Auton Malli:");
		label_1.setBounds(10, 285, 87, 23);
		contentPane.add(label_1);

		Label label_2 = new Label("Auton Moottori:");
		label_2.setBounds(10, 314, 87, 23);
		contentPane.add(label_2);

		JTextField txtMerkki = new JTextField();
		txtMerkki.setBounds(142, 256, 200, 23);
		contentPane.add(txtMerkki);
		txtMerkki.setColumns(10);

		JTextField txtMalli = new JTextField();
		txtMalli.setBounds(142, 285, 200, 23);
		contentPane.add(txtMalli);
		txtMalli.setColumns(10);

		JTextField txtMoottori = new JTextField();
		txtMoottori.setBounds(142, 314, 200, 23);
		contentPane.add(txtMoottori);
		txtMoottori.setColumns(10);

		JButton Lisää = new JButton("Lisää uusi");
		Lisää.setBackground(Color.WHITE);
		Lisää.setForeground(Color.BLACK);
		Lisää.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Tarkastetaan textikenttien tiedon sisältö
				if ((txtMerkki.getText().equals(null)) || (txtMerkki.getText().equals(""))
						|| (txtMerkki.getText().equals(" "))) {
					JPanel varmistus = new JPanel();

					JTextPane varoitus = new JTextPane();
					varoitus.setText("Syötä ajoneuvon merkki!");
					varmistus.add(varoitus);

					JOptionPane.showMessageDialog(null, varmistus, "Huom!", JOptionPane.ERROR_MESSAGE);
				} else if ((txtMalli.getText().equals(null)) || (txtMalli.getText().equals(""))
						|| (txtMalli.getText().equals(" "))) {
					JPanel varmistus = new JPanel();

					JTextPane varoitus = new JTextPane();
					varoitus.setText("Syötä ajoneuvon malli!");
					varmistus.add(varoitus);

					JOptionPane.showMessageDialog(null, varmistus, "Huom!", JOptionPane.ERROR_MESSAGE);
				} else if ((txtMoottori.getText().equals(null)) || (txtMoottori.getText().equals(""))
						|| (txtMoottori.getText().equals(" "))) {
					JPanel varmistus = new JPanel();

					JTextPane varoitus = new JTextPane();
					varoitus.setText("Syötä ajoneuvon moottori!");
					varmistus.add(varoitus);

					JOptionPane.showMessageDialog(null, varmistus, "Huom!", JOptionPane.ERROR_MESSAGE);

				} else {

					// Tekstikenttien tiedot muuttujiin
					String uusiMerkki = txtMerkki.getText();
					String uusiMalli = txtMalli.getText();
					String uusiMoottori = txtMoottori.getText();

					Auto uusiAuto = new Auto(uusiMerkki, uusiMalli, uusiMoottori);
					// kutsutaan metodia SQL
					SQL.tallennaAuto(uusiAuto);
					// lisätään uusi auto
					Lista.add(new Object[] { uusiAuto.getAutonMerkki(), uusiAuto.getAutonMalli(), uusiAuto.getAutonMoottori() });
					System.out.println(uusiAuto.toString());

					tulostaTaulukko();
				}
			}
		});
		setVisible(true);
		Lisää.setBounds(10, 343, 220, 26);
		contentPane.add(Lisää);

		JButton Näytä = new JButton("Näytä autot");
		Näytä.setBounds(244, 343, 230, 26);
		contentPane.add(Näytä);
		Näytä.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Metodit SQL saadut tiedot ja tulosta ne taulukkoon
				Lista = SQL.AutotKannasta();
				tulostaTaulukko();
			}
		});
		setVisible(true);
	}

	// Lisää tiedot taulukkoon
	private void tulostaTaulukko() {
		taulukko.setRowCount(0);
		for (int i = 0; i < Lista.size(); i++) {
			taulukko.addRow(Lista.get(i));
			scrollPane.setViewportView(taulu);
		}
	}
	//main
	public static void main(String[] args) {
		try {
			GUI ikkuna = new GUI();

			ikkuna.setSize(new Dimension(450, 450));
			ikkuna.setPreferredSize(new Dimension(450, 450));
			ikkuna.setVisible(true);

		} catch (Exception g) {
			g.printStackTrace();
		}
	}
}
