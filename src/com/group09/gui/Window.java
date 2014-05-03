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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import com.group09.database.Database;
import com.group09.database.Query;

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
		jButton1.setPreferredSize(new Dimension(330, 30));
		jPanel.add(jButton1);

		jButton2 = new JButton(Strings.ADD_ROW + " " + Query.TABLE_NAMES[0]);
		jButton2.setToolTipText(Strings.ADD_ROW_TOOLTIP);
		jButton2.addActionListener(this);
		jButton2.setPreferredSize(new Dimension(330, 30));
		jPanel.add(jButton2);

		jComboBox = new JComboBox<String>(Query.TABLE_NAMES);
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
//		jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
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

			String name2 = JOptionPane
					.showInputDialog(
							this,
							"Please enter your search query\n(\"SELECT * FROM GENRE\")",
							null);
			updateJTable(database.query(name2));

		} else if (e.getSource() == jComboBox) {
			jButton2.setText(Strings.ADD_ROW + " "
					+ Query.TABLE_NAMES[jComboBox.getSelectedIndex()]);
		} else if (e.getSource() == jButton2) {
			new TableSelect(database, jComboBox.getSelectedIndex());

		} else {
			for (int index = 0; index < Strings.NAMES.length; index++) {
				if (e.getSource() == jButtons[index]) {
					updateJTable(database.query(Query.QUERIES[index]));
					break;
				}
			}
		}
	}
}
