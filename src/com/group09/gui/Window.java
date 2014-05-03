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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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

	private JButton jButton;
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
		jButton = new JButton(Strings.ADD_ROW);
		jButton.setToolTipText(Strings.ADD_ROW_TOOLTIP);
		jButton.addActionListener(this);
		jButton.setPreferredSize(new Dimension(1000, 30));

		jPanel0.add(new JPanel(true).add(jButton));
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
		if (e.getSource() == jButton) {
			System.out.println(jButton.getText());

			// String name2 = JOptionPane.showInputDialog(this,
			// "To which table you want to add some information?", null);

			defaultTableModel.insertRow(0, new Object[] { null, "a" });

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
