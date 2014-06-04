package com.group09.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import com.group09.constants.Constants;
import com.group09.database.Database;
import com.group09.entities.Area;
import com.group09.entities.Artist;
import com.group09.entities.Gender;
import com.group09.entities.Genre;
import com.group09.entities.Has_recorded;
import com.group09.entities.Is_Genre;
import com.group09.entities.Is_released;
import com.group09.entities.Is_track_on;
import com.group09.entities.Medium;
import com.group09.entities.Recording;
import com.group09.entities.Release;
import com.group09.time.Timing;

/**
 * 
 * @author Philippe
 * 
 */
@SuppressWarnings("serial")
public class Window extends JFrame implements ActionListener {
	private Database database;

	private JPanel jPanel0;

	private JButton jButton1;
	private JButton jButton2;
	private JButton jButtons[];

	private JComboBox<String> jComboBox;

	private DefaultTableModel defaultTableModel;

	/**
	 * 
	 * @param database
	 */
	public Window(Database database) {
		this.database = database;

		initializeWindow();
	}

	/**
	 * 
	 */
	private void initializeWindow() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}

		jPanel0 = new JPanel(true);

		initializeButton();
		initializeJTable();
		initializeButtons();

		add(jPanel0);
		pack();

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				database.close();
			}
		});

		setTitle("Assignment Databse");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1024, 768);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	/**
	 * 
	 */
	private void initializeButton() {
		JPanel jPanel = new JPanel(true);

		jButton1 = new JButton(Strings.SEARCH_TABLE);
		jButton1.setToolTipText(Strings.SEARCH_TABLE_TOOL_TIP);
		jButton1.addActionListener(this);
		jButton1.setPreferredSize(new Dimension(330, 30));
		jPanel.add(jButton1);

		jButton2 = new JButton(Strings.ADD_ROW + " " + Strings.TABLE_NAMES[0]);
		jButton2.setToolTipText(Strings.ADD_ROW_TOOLTIP);
		jButton2.addActionListener(this);
		jButton2.setPreferredSize(new Dimension(330, 30));
		jPanel.add(jButton2);

		jComboBox = new JComboBox<String>(Strings.TABLE_NAMES);
		jComboBox.addActionListener(this);
		jComboBox.setPreferredSize(new Dimension(330, 30));
		jPanel.add(jComboBox);

		jPanel0.add(jPanel);
	}

	/**
	 * 
	 */
	private void initializeButtons() {
		JPanel jPanel = new JPanel(true);

		jButtons = new JButton[Strings.NAMES.length];
		for (int i = 0; i < Strings.NAMES.length; i++) {
			jButtons[i] = new JButton(Strings.NAMES[i]);
			jButtons[i].setToolTipText(Strings.NAMES_TOOL_TIPS[i]);
			jButtons[i].addActionListener(this);
			jButtons[i].setPreferredSize(new Dimension(45, 30));

			// TODO only for assignment 2
			if (i >= 7) {
				// jButtons[i].setEnabled(false);
			}

			jPanel.add(jButtons[i]);
		}

		jPanel0.add(jPanel);
	}

	/**
	 * 
	 */
	private void initializeJTable() {
		defaultTableModel = new DefaultTableModel();

		JTable jTable = new JTable(defaultTableModel);
		jTable.setEnabled(false);

		JScrollPane scrollPane = new JScrollPane(jTable,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(1000, 630));

		jPanel0.add(scrollPane);
	}

	/**
	 * 
	 * @param resultSet
	 */
	private void updateJTable(ResultSet resultSet) {
		defaultTableModel.getDataVector().removeAllElements();
		defaultTableModel.fireTableDataChanged();
		defaultTableModel.setColumnCount(0);

		try {
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			int columnCount = resultSetMetaData.getColumnCount();

			String columnNames[] = new String[columnCount];

			for (int i = 0; i < columnCount; i++) {
				columnNames[i] = resultSetMetaData.getColumnName(i + 1);
				defaultTableModel.addColumn(columnNames[i]);
			}

			int line = 0;
			while (resultSet.next() && (line <= Constants.MAX_LINE)) {
				String data[] = new String[columnCount];

				for (int i = 0; i < columnCount; i++) {
					data[i] = resultSet.getString(columnNames[i]);
				}

				defaultTableModel.addRow(data);

				line++;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * 
	 */
	@SuppressWarnings("resource")
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jButton1) {

			String name2 = JOptionPane
					.showInputDialog(
							this,
							"Please enter your search query\n(\"SELECT * FROM GENRE\")",
							null);
			updateJTable(database.query(name2));

		} else if (e.getSource() == jComboBox) {
			jButton2.setText(Strings.ADD_ROW + " "
					+ Strings.TABLE_NAMES[jComboBox.getSelectedIndex()]);
			String query = "SELECT * FROM " + Strings.TABLE_NAMES[jComboBox.getSelectedIndex()];
			updateJTable(database.query(query));
		} else if (e.getSource() == jButton2) {
			Object object = null;

			switch (jComboBox.getSelectedIndex()) {
			case 0:
				object = new com.group09.entities.Type(0, "");
				break;
			case 1:
				object = new Gender(1, "");
				break;
			case 2:
				object = new Genre(2, "", -1);
				break;
			case 3:
				object = new Area(3, "", "");
				break;
			case 4:
				object = new Artist(4, "", -1, -1, -1);
				break;
			case 5:
				object = new Is_Genre(5, -1);
				break;
			case 6:
				object = new Recording(6, "", -1);
				break;
			case 7:
				object = new Has_recorded(7, -1);
				break;
			case 8:
				object = new Medium(8, "");
				break;
			case 9:
				object = new Release(9, "");
				break;
			case 10:
				object = new Is_track_on(10, -1, -1);
				break;
			case 11:
				object = new Is_released(11, -1);
				break;

			default:
				break;
			}

			new TableSelect(database, object);

		} else {
			for (int index = 0; index < Strings.NAMES.length; index++) {
				if (e.getSource() == jButtons[index]) {

					String query = "";
					Scanner scanner;
					try {
						scanner = new Scanner(new File("queries/"
								+ jButtons[index].getText() + ".sql"));
						while (scanner.hasNext()) {
							query += scanner.nextLine();
						}
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}

					Timing.startCounter();

					ResultSet resultSet = database.query(query); 
					
					Timing.stopCounter();

					updateJTable(resultSet);
					break;
				}
			}
		}
	}
}
