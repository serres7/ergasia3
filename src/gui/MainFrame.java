package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import classes.Customer;

public class MainFrame 
{
	//Attributes
	private JFrame mainFrame;
	private JPanel mainPanel;
	private JScrollPane customerPanel;
	private JTable customerTable;
	private JPanel invoicePanel;
	private JTable invoiceTable;
	private ArrayList<Customer> customerList;
	
	public MainFrame( ArrayList<Customer> customerList )
	{
		this.customerList = customerList;
		
		initializeCustomerTable();
		
		SpringLayout sp = new SpringLayout();
		this.mainPanel = new JPanel( sp );
		
		sp.putConstraint(SpringLayout.WEST, this.customerPanel, 5,SpringLayout.WEST, this.mainPanel);
		sp.putConstraint(SpringLayout.NORTH, this.customerPanel, 5,SpringLayout.NORTH, this.mainPanel);
		this.mainPanel.add(this.customerPanel);
		
		
		//MainFrame
		this.mainFrame = new JFrame( "ΕΡΓΑΣΙΑ 3/ΚΥΡΙΑ ΟΘΟΝΗ" );
		this.mainFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.mainFrame.setPreferredSize( new Dimension(700, 300) );
		this.mainFrame.add( this.mainPanel );
		this.mainFrame.pack();
		this.mainFrame.setVisible( true );
	}//Condtructor
	
	private void initializeCustomerTable()
	{
		String col[] = {"α/α","ID","ΟΝΟΜΑ"};
		DefaultTableModel tableModel = new DefaultTableModel(col, 0)
		{
		    public boolean isCellEditable(int row, int column)
		    {
		      return false;//This causes all cells to be not editable
		    }
		  };
		  int c=1;
		  for( Customer x: this.customerList )
			{
				tableModel.addRow(new Object[] {c,x.getId(), x.getName()});
				c++;
			}
		this.customerTable = new JTable(tableModel);
		
		this.customerPanel = new JScrollPane(this.customerTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.customerTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.customerTable.getColumnModel().getColumn(0).setPreferredWidth(27);
		this.customerTable.getColumnModel().getColumn(1).setPreferredWidth(60);
		this.customerTable.getColumnModel().getColumn(2).setPreferredWidth(190);
		DefaultTableCellRenderer rightRenderer = (DefaultTableCellRenderer) this.customerTable.getDefaultRenderer(String.class);//                new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		this.customerPanel.setPreferredSize( new Dimension(300,700));
	}
	

}
