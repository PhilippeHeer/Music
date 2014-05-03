package com.group09.gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.group09.database.Database;

/**
 * 
 * @author Philippe Heer
 * 
 */
@SuppressWarnings("serial")
public class TableSelect extends JFrame {

	private Database database;
	private DefaultTableModel defaultTableModel;
	private JPanel jPanel0;

	/**
	 * @param database
	 * 
	 */
	public TableSelect(Database database) {
		this.database = database;

		// database.addGenre(new Genre(6, "Rock", 6));
		// database.addGender(new Male(7, "Fritz"));
		
		initializeWindow();
		initializeJTable();
	}

	/**
		 * 
		 */
	private void initializeWindow() {
		jPanel0 = new JPanel();
		add(jPanel0);
		
		setTitle("TableSelect");
		setSize(320, 240);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	/**
	 * 
	 */
	private void initializeJTable() {
		defaultTableModel = new DefaultTableModel();

		JTable jTable = new JTable(defaultTableModel);
		jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		JScrollPane scrollPane = new JScrollPane(jTable,
				JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(300, 100));

		jPanel0.add(scrollPane);
	}
}
