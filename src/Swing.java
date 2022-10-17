import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class Swing {

	public static void main(String[] args) {
		// skapar ett fönster
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setSize(1300, 800);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Hassan");
		frame.getContentPane().setLayout(null);
		JButton btn = new JButton();
		btn.setBounds(500, 300, 300, 50);
		btn.setText("Click here to read csv file");
		frame.add(btn);
		btn.revalidate();
		btn.repaint();

		ActionListener ac = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fch = new JFileChooser();
				fch.setFileFilter(new FileNameExtensionFilter("Open:", ".txt", ".csv", ".json"));
				fch.addChoosableFileFilter(new FileNameExtensionFilter(".txt", "txt"));
				fch.addChoosableFileFilter(new FileNameExtensionFilter(".csv", "csv"));
				fch.addChoosableFileFilter(new FileNameExtensionFilter(".json", "json"));
				int showOpenDialog = fch.showOpenDialog(null);
				String path = fch.getSelectedFile().getPath();
				String line = "";
				JFrame window = new JFrame("JFrame with text");
				window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				window.setLayout(new BorderLayout());
				window.setPreferredSize(new Dimension(800, 800));
				window.pack();
				JTable table = new JTable();

				table.setBounds(0, 0, 600, 600);
				window.getContentPane().setLayout(null);

				window.add(table);
				window.pack();
				window.setVisible(true);
				window.setLocationRelativeTo(null);
				window.setResizable(true);
				frame.setVisible(false);
				
				
				
				

				int dotIndex = path.indexOf(".");
				String extension = path.substring(dotIndex + 1, path.length());
				switch (extension) {
				case "csv":
					parseCsv(path, table);

					break;

				case "json":
					parseJson(path, table);
					break;

				case "txt":

					break;
				}

			}
		};
		btn.addActionListener(ac);

	}

	public static void parseCsv(String path, JTable table) {
		String line;
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			ArrayList<String[]> arl = new ArrayList<>();
			while ((line = br.readLine()) != null) {
				arl.add(line.split(","));
			}

			String[][] values = new String[arl.size()][];
			values = arl.toArray(values);

			table.setModel(new DefaultTableModel(values, new String[] { "Title 1", "Title 2", "Title 3", "Title 4",
					"Title 5", "Title 6", "Title 7", "Title 8" }));

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static void parseJson(String path, JTable table) {
		String line;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			ArrayList<String[]> arl = new ArrayList<>();
			int count=0;
			String[] tempArray = new String[8];
			String[][] values = new String[arl.size()][];

			while ((line = br.readLine()) != null) {
				String erase = line.replaceAll("[^a-zA-Z0-9/:.]", "");
				if (!erase.matches(".*[a-zA-Z]+.*") || erase.contains("\n")) continue;
				
				int dotIndex2 = erase.indexOf(":");
			    erase = erase.substring(dotIndex2 + 1, erase.length());
				
				tempArray[count] = erase;
				
				count++;

				if(count>7) {
                    arl.add(tempArray);
                    count=0;
                    tempArray = new String[8];
                }

				}
			
			values = arl.toArray(values);
			

			table.setModel(new DefaultTableModel(values, new String[] { "Title 1", "Title 2", "Title 3", "Title 4",
					"Title 5", "Title 6", "Title 7", "Title 8" }));

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
