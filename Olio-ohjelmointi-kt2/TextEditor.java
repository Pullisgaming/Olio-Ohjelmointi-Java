import java.awt.EventQueue;

import javax.swing.JFrame;

import javax.swing.UIManager;
import java.util.Formatter;

import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

import java.util.Scanner;
import java.io.*;


public class TextEditor {

	private final String TITLE = "S1mple Text Editor";
	private JFrame frame;
	private JTextArea textArea;
	private File openFile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

					TextEditor window = new TextEditor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TextEditor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("TITLE");
		frame.setBounds(100, 100, 1149, 852);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		textArea = new JTextArea();
		frame.getContentPane().add(textArea, BorderLayout.NORTH);

		JScrollPane Scroll = new JScrollPane(textArea);
		frame.getContentPane().add(Scroll, BorderLayout.CENTER);

		JMenuBar menuBar = new JMenuBar();
		frame.getContentPane().add(menuBar, BorderLayout.NORTH);

		JMenu mnTiedosto = new JMenu("Tiedosto");
		menuBar.add(mnTiedosto);

		JMenuItem mntmAvaa = new JMenuItem("Avaa");
		mntmAvaa.setIcon(
				new ImageIcon(TextEditor.class.getResource("/com/sun/java/swing/plaf/windows/icons/TreeOpen.gif")));
		mntmAvaa.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mntmAvaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				avaa();
			}
		});
		mnTiedosto.add(mntmAvaa);

		JMenuItem mntmTallennaNimell = new JMenuItem("Tallenna Nimellä");
		mntmTallennaNimell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				luo();
			}
		});
		mnTiedosto.add(mntmTallennaNimell);

		JMenuItem mntmTallenna = new JMenuItem("Tallenna");
		mntmTallenna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tallenna();
			}
		});
		mnTiedosto.add(mntmTallenna);

		JMenuItem mntmLopeta = new JMenuItem("Lopeta");
		mntmLopeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sulje();
			}
		});
		mnTiedosto.add(mntmLopeta);

		JMenuItem mntmSulje = new JMenuItem("Sulje");
		mnTiedosto.add(mntmSulje);

		JMenu mnMuokkaa = new JMenu("Muokkaa");
		menuBar.add(mnMuokkaa);

		JMenuItem mntmEtsi = new JMenuItem("Etsi");
		mnMuokkaa.add(mntmEtsi);

		JMenuItem mntmKorvaa = new JMenuItem("Korvaa");
		mnMuokkaa.add(mntmKorvaa);

		JMenu mnTietoja = new JMenu("Tietoja");
		menuBar.add(mnTietoja);

		JMenuItem mntmTietojaOhjelmasta = new JMenuItem("Tietoja ohjelmasta");
		mnTietoja.add(mntmTietojaOhjelmasta);
	}

	// Toiminnot
	// avaa metodi avaa nappulalle
	private void avaa() {
		try {
			JFileChooser chooser = new JFileChooser();
			chooser.setDialogTitle("Valitse teksti tiedosto");
			chooser.showOpenDialog(null);

			openFile = chooser.getSelectedFile();
			if (!openFile.exists()) {
				JOptionPane.showMessageDialog(null, "Avaus epäonnistui, Tiedostoa ei ole!", "Error", JOptionPane.ERROR_MESSAGE);
				openFile = null;
				return;
			}
			Scanner lukija = new Scanner(openFile);
			String tiedosto = "";
			while (lukija.hasNextLine()) {
				tiedosto += lukija.nextLine() + "\n";
			}	
			lukija.close();
			textArea.setText(tiedosto);
			
			frame.setTitle(TITLE + " - "+openFile.getName());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// luo metodi tallenna nimellä nappulalle
	private void luo() {
		try {
			JFileChooser chooser = new JFileChooser();

			chooser.setDialogTitle("save new file");
			chooser.showOpenDialog(null);
			
			openFile = chooser.getSelectedFile();
			
			tallenna();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Tallenna metodi tallenna nappulalle
	private void tallenna() {
		try {
			
			if (openFile == null) {
				JOptionPane.showMessageDialog(null, "Tallennus epäonnistui, Tiedostoa ei valittu!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			String contents = textArea.getText();
			Formatter form = new Formatter(openFile);
			form.format("%s", contents);
			form.close();
			
			frame.setTitle(TITLE + " - "+openFile.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Sulje metodi sulje nappulalle
	private void sulje() {
		if (openFile == null) {
			JOptionPane.showMessageDialog(null, "Tallennus epäonnistui, Tiedostoa ei valittu!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			int input = JOptionPane.showConfirmDialog(null, "Haluatko tallentaa ennen kuin suljet?", "Odota!", JOptionPane.YES_NO_OPTION);
			
			if(input == JOptionPane.YES_NO_OPTION) {
				tallenna();
			}
			
			textArea.setText("");
			openFile = null;
			frame.setTitle(TITLE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
