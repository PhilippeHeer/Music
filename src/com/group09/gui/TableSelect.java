package com.group09.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
 * @author Philippe Heer
 * 
 */
@SuppressWarnings("serial")
public class TableSelect extends JFrame implements ActionListener {

	private Database database;
	private JPanel jPanel0;
	private int index;
	private DefaultTableModel defaultTableModel;
	private JButton jButton;
	private int columnCount;

	/**
	 * @param database
	 * @param index
	 * 
	 */
	public TableSelect(Database database, int index) {
		this.database = database;
		this.index = index;

		initializeWindow();
	}

	/**
	 * 
	 */
	private void initializeWindow() {
		jPanel0 = new JPanel();

		initializeJTable();
		initializeJButton();

		add(jPanel0);
		pack();

		setTitle("TableSelect");
		setSize(480, 135);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	/**
	 * 
	 */
	private void initializeJButton() {
		jButton = new JButton(Strings.SEARCH_TABLE);
		jButton.setToolTipText(Strings.SEARCH_TABLE_TOOL_TIP);
		jButton.addActionListener(this);
		jButton.setPreferredSize(new Dimension(460, 30));

		jPanel0.add(jButton);
	}

	/**
	 * 
	 */
	private void initializeJTable() {
		defaultTableModel = new DefaultTableModel();

		JTable jTable = new JTable(defaultTableModel);
		// jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		JScrollPane scrollPane = new JScrollPane(jTable,
				JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(460, 60));

		try {
			ResultSetMetaData resultSetMetaData = database.query(
					"SELECT * FROM " + Query.TABLE_NAMES[index]).getMetaData();
			columnCount = resultSetMetaData.getColumnCount();

			for (int i = 0; i < columnCount; i++) {
				defaultTableModel.addColumn(resultSetMetaData
						.getColumnName(i + 1));
			}

			defaultTableModel.addRow(new String[columnCount]);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		jPanel0.add(scrollPane);
	}

	/**
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jButton) {
			String data[] = new String[columnCount];

			for (int i = 0; i < columnCount; i++) {
				data[i] = (String) defaultTableModel.getValueAt(0, i);
			}

			database.addRow(data, index);
		}

		setVisible(false);
	}
}
