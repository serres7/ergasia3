package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
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
	private JPanel controlPanel;
	private JButton newCustomer;
	private JButton updateCustomer;
	private ArrayList<Customer> customerList;
	
	public MainFrame( ArrayList<Customer> customerList )
	{
		this.customerList = customerList;
		
		initializeCustomerTable();
		initializeControlPanel();
		
		SpringLayout sp = new SpringLayout();
		this.mainPanel = new JPanel( sp );
		
		sp.putConstraint( SpringLayout.WEST, this.controlPanel, 5,SpringLayout.WEST, this.mainPanel );
		sp.putConstraint( SpringLayout.NORTH, this.controlPanel, 5,SpringLayout.NORTH, this.mainPanel );
		sp.putConstraint( SpringLayout.WEST, this.customerPanel, 0,SpringLayout.WEST, this.controlPanel );
		sp.putConstraint( SpringLayout.NORTH, this.customerPanel, 5,SpringLayout.SOUTH, this.controlPanel );
		this.mainPanel.add( this.controlPanel );
		this.mainPanel.add( this.customerPanel );
		
		
		//MainFrame
		this.mainFrame = new JFrame( "ΕΡΓΑΣΙΑ 3/ΚΥΡΙΑ ΟΘΟΝΗ" );
		this.mainFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.mainFrame.setPreferredSize( new Dimension(700, 300) );
		this.mainFrame.add( this.mainPanel );
		this.mainFrame.pack();
		this.mainFrame.setVisible( true );
		
	}//Constructor
	
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
		this.customerTable.getColumnModel().getColumn(0).setPreferredWidth(33);
		this.customerTable.getColumnModel().getColumn(1).setPreferredWidth(60);
		this.customerTable.getColumnModel().getColumn(2).setPreferredWidth(190);
		DefaultTableCellRenderer rightRenderer = (DefaultTableCellRenderer) this.customerTable.getDefaultRenderer(String.class);
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		this.customerPanel.setPreferredSize( new Dimension(300,700));
		
	}//initializeCustomerTable
	
	private void initializeControlPanel()
	{				
		this.controlPanel = new JPanel();
		this.controlPanel.setPreferredSize( new Dimension( 700, 100 ) );
		this.newCustomer = new JButton( "New Customer" );
		this.updateCustomer = new JButton( "Update Customer" );
		this.controlPanel.add( this.newCustomer );
		this.controlPanel.add( this.updateCustomer );
		this.newCustomer.addActionListener( new ButtonListener() );
		this.updateCustomer.addActionListener( new UpdateCustomerListener() );
		
	}//initializeControlPanel
	
	class ButtonListener implements ActionListener 
	{
        public void actionPerformed( ActionEvent e ) 
        {   
        	AddCustomer a = new AddCustomer( mainFrame, customerList );
        	DefaultTableModel tableModel = (DefaultTableModel) customerTable.getModel();
        	tableModel.setRowCount(0);
        	int c=1;
        	for( Customer x: customerList )
			{
        		
        		
				tableModel.addRow(new Object[] {c,x.getId(), x.getName()});
				c++;
			}
        	
        }//actionPerformed
        
    }//ButtonListener
	
	class UpdateCustomerListener implements ActionListener 
	{
        public void actionPerformed( ActionEvent e ) 
        {   
        	Customer selectedCustomer = null;
        	int rowIndex = customerTable.getSelectedRow();
        	String selectedId = (String) customerTable.getModel().getValueAt(rowIndex, 1 );
        	for( Customer x: customerList )
			{
        		if( x.getId().equals( selectedId ) )
				{
        			selectedCustomer = x;
        			break;
	
				}	
			}
        	 
        	UpdateCustomer a = new UpdateCustomer( mainFrame, customerList, selectedCustomer );
        	DefaultTableModel tableModel = (DefaultTableModel) customerTable.getModel();
        	tableModel.setRowCount(0);
        	int c=1;
        	for( Customer x: customerList )
			{
        		
        		
				tableModel.addRow(new Object[] {c,x.getId(), x.getName()});
				c++;
			}
        	
        }//actionPerformed
        
    }//ButtonListener

}
