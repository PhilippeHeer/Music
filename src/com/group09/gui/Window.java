package com.group09.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.group09.database.Database;
import com.group09.database.Query;
import com.group09.entities.Genre;
import com.group09.entities.Male;

/**
 * 
 * @author Group 09
 * 
 */
@SuppressWarnings("serial")
public class Window extends JFrame implements ActionListener {
	private Database database;

	private JPanel jPanel0;

	private JButton jButton1;
	private JButton jButton2;
	private JButton jButtons[];

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
		jPanel0 = new JPanel(true);

		initializeButton();
		initializeJTable();
		initializeButtons();

		add(jPanel0);
		pack();

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.out.println("Goodbye");
				database.close();
			}
		});

		setTitle("Assignment 2");
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
		jButton1.setPreferredSize(new Dimension(500, 30));
		jPanel.add(jButton1);

		jButton2 = new JButton(Strings.ADD_ROW);
		jButton2.setToolTipText(Strings.ADD_ROW_TOOLTIP);
		jButton2.addActionListener(this);
		jButton2.setPreferredSize(new Dimension(500, 30));
		jPanel.add(jButton2);

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
				jButtons[i].setEnabled(false);
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
		jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jTable.setEnabled(false);

		JScrollPane scrollPane = new JScrollPane(jTable,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(1000, 630));

		jPanel0.add(new JPanel().add(scrollPane));
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

			while (resultSet.next()) {
				String data[] = new String[columnCount];

				for (int i = 0; i < columnCount; i++) {
					data[i] = resultSet.getString(columnNames[i]);
				}

				defaultTableModel.addRow(data);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jButton1) {
			System.out.println(jButton1.getText());

			String name2 = JOptionPane.showInputDialog(this,
					"Please enter your search query\n(\"SELECT * FROM GENRE\")", null);
			updateJTable(database.query(name2));

		} else if (e.getSource() == jButton2) {
			System.out.println(jButton2.getText());

			database.addGenre(new Genre(6, "Rock", 6));
			database.addGender(new Male(7, "Fritz"));
		} else {
			for (int index = 0; index < Strings.NAMES.length; index++) {
				if (e.getSource() == jButtons[index]) {
					System.out.println(jButtons[index].getText());

					updateJTable(database.query(Query.QUERIES[index]));
					break;
				}
			}
		}
	}
}
