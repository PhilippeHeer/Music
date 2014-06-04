package com.group09.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

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
public class TableSelect extends JFrame implements ActionListener {
	private Database database;
	private Object object;
	private JPanel jPanel0;
	private DefaultTableModel defaultTableModel;
	private JButton jButton;
	private boolean is_deleting;
	private Window window;
	
	/**
	 * @param database
	 * @param object
	 * 
	 */
	public TableSelect(Database database, Object object, boolean is_deleting, Window window) {
		this.database = database;
		this.object = object;
		this.is_deleting = is_deleting;
		this.window = window;
		
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
		if(is_deleting){
			jButton = new JButton(Strings.DELETE_ROW);
			jButton.setToolTipText(Strings.DELETE_ROW_TOOLTIP);
		}
		else{
			jButton = new JButton(Strings.ADD_ROW);
			jButton.setToolTipText(Strings.ADD_ROW_TOOLTIP);
		}
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

		JScrollPane scrollPane = new JScrollPane(jTable,
				JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(460, 60));

		try {
			for (Field field : object.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				Object value;
				value = field.get(object);
				if (value != null) {
					defaultTableModel.addColumn(field.getName());
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		Object row[] = {};
		defaultTableModel.addRow(row);

		jPanel0.add(scrollPane);
	}

	/**
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jButton) {

			try {
				Field field[] = object.getClass().getDeclaredFields();

				for (int i = 0; i < field.length; i++) {
					field[i].setAccessible(true);

					if (defaultTableModel.getValueAt(0, i) != null) {
						if (field[i].getType().getSimpleName().equals("String")) {
							field[i].set(object,
									defaultTableModel.getValueAt(0, i));
						} else if (field[i].getType().getSimpleName()
								.equals("int")) {
							field[i].setInt(object, Integer
									.parseInt((String) defaultTableModel
											.getValueAt(0, i)));
						}
					}
				}
			} catch (IllegalArgumentException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			}

			if(is_deleting){
				database.deleteRow(object);
			}
			else{
				database.addRow(object);
			}
			window.updateTable();
		}

		setVisible(false);
	}
}
