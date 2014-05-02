package com.group09.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.group09.database.Database;

/**
 * 
 * @author Group 09
 * 
 */
@SuppressWarnings("serial")
public class Window extends JFrame implements ActionListener {
	private JButton jButton[] = new JButton[Strings.names.length];
	private JButton jButton1 = new JButton("Add to table");
	private JTable jTable;
	private DefaultTableModel model;

	private Database database;

	/**
	 * 
	 */
	public Window(Database database) {
		this.database = database;

		
		
		model = new DefaultTableModel();
		model.addColumn("Id");
		model.addColumn("Name");
		model.addColumn("Count");
		
		
		int j=0;
		

		ResultSet resultSet = database.query("SELECT * FROM GENRE");
		try {
			while (resultSet.next()) {
				model.insertRow(0, new Object[]{resultSet.getInt("ID"), resultSet
						.getString("Name"), resultSet.getInt("Count"), j++});
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		jTable = new JTable(model);
		jTable.setEnabled(false);
		jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		model.insertRow(0, new Object[]{null,"a"});		
		
		


		
		JPanel jPanel0 = new JPanel(true);
		JPanel jPanel1 = new JPanel(true);
		JPanel jPanel2 = new JPanel(true);
		JPanel jPanel3 = new JPanel(true);

		jButton1.addActionListener(this);
		jButton1.setPreferredSize(new Dimension(1000, 30));
		jPanel1.add(jButton1);

		JScrollPane scrollPane = new JScrollPane(jTable,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setEnabled(true);
		scrollPane.setPreferredSize(new Dimension(1000, 630));
		jPanel2.add(scrollPane);

		for (int i = 0; i < Strings.names.length; i++) {
			jButton[i] = new JButton(Strings.names[i]);
			jButton[i].addActionListener(this);
			jButton[i].setToolTipText(Strings.toolTip[i]);

			// TODO only for assignment 2
			if (i >= 7) {
				jButton[i].setEnabled(false);
			}

			jPanel3.add(jButton[i]);
		}
		jPanel3.setPreferredSize(new Dimension(1000, 40));
		
		
		
		jPanel0.add(jPanel1);
		jPanel0.add(jPanel2);
		jPanel0.add(jPanel3);
		add(jPanel0);
		pack();

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
	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < Strings.names.length; i++) {
			if (e.getSource() == jButton[i]) {
				System.out.println(jButton[i].getText());
				model.getDataVector().removeAllElements();
				model.fireTableDataChanged();
				model.setColumnCount(0);
				model.addColumn("Id");
				break;
			}
		}

		if (e.getSource() == jButton1) {
			System.out.println(jButton1.getText());
//
//			String name = JOptionPane.showInputDialog(this,
//					"To which table you want to add some information?", null);
//			String name2 = JOptionPane.showInputDialog(this,
//					"To which table you want to add some information?", null);
			
			jTable = new JTable(model);
			model.insertRow(0, new Object[]{null,"a"});
		}
	}
}
