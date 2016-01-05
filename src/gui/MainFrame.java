package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import classes.Customer;
import classes.Invoice;
import classes.InvoiceList;
import classes.State;


public class MainFrame 
{	
	private JFrame mainFrame;
	private JPanel mainPanel;
	private JScrollPane customerPanel;
	private JTable customerTable;
	private JScrollPane invoicePanel;
	private JTable invoiceTable;
	private JPanel controlPanel;
	private JButton newCustomer;
	private JButton updateCustomer;
	private JButton insertInvoice;
	private JButton payInvoice;
	private ArrayList<Customer> customerList;
	private InvoiceList invoiceList;
	
	public MainFrame( ArrayList<Customer> customerList, InvoiceList invoiceList )
	{
		this.customerList = customerList;
		this.invoiceList = invoiceList;
		
		initializeCustomerTable();
		initializeControlPanel();
		initializeInvoicePanel();
		
		SpringLayout sp = new SpringLayout();
		this.mainPanel = new JPanel( sp );
		
		sp.putConstraint( SpringLayout.WEST, this.controlPanel, 5,SpringLayout.WEST, this.mainPanel );
		sp.putConstraint( SpringLayout.NORTH, this.controlPanel, 5,SpringLayout.NORTH, this.mainPanel );
		sp.putConstraint( SpringLayout.WEST, this.customerPanel, 0,SpringLayout.WEST, this.controlPanel );
		sp.putConstraint( SpringLayout.NORTH, this.customerPanel, 5,SpringLayout.SOUTH, this.controlPanel );
		
		sp.putConstraint( SpringLayout.WEST, this.invoicePanel, 100,SpringLayout.EAST, this.customerPanel );
		sp.putConstraint( SpringLayout.NORTH, this.invoicePanel, 0,SpringLayout.NORTH, this.customerPanel );
		
		this.mainPanel.add( this.controlPanel );
		this.mainPanel.add( this.customerPanel );
		this.mainPanel.add( this.invoicePanel );
		
		
		//MainFrame
		this.mainFrame = new JFrame( "ΕΡΓΑΣΙΑ 3 / ΚΥΡΙΑ ΟΘΟΝΗ" );
		this.mainFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.mainFrame.setPreferredSize( new Dimension(750, 600) );
		this.mainFrame.add( this.mainPanel );
		this.mainFrame.pack();
		this.mainFrame.setVisible( true );
		this.customerTable.setRowSelectionInterval(0, 0);
		
	}//Constructor
	
	private void initializeInvoicePanel()
	{
		String col[] = {"α/α","ID","ID ΠΕΛΑΤΗ", "ΠΟΣΟ"};
		DefaultTableModel tableModel = new CustomerTableModel(col, 0);
		
		  int c=1;
		  for( Invoice x: this.invoiceList.getInvoicesList() )
			{
				tableModel.addRow(new Object[] {c,x.getId(), x.getCustomerId(),x.getAmount() });
			
				c++;
			}
		this.invoiceTable = new JTable(tableModel);
		
		this.invoicePanel = new JScrollPane(this.invoiceTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.invoiceTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.invoiceTable.getColumnModel().getColumn(0).setPreferredWidth(33);
		this.invoiceTable.getColumnModel().getColumn(1).setPreferredWidth(60);
		this.invoiceTable.getColumnModel().getColumn(2).setPreferredWidth(80);
		this.invoiceTable.getColumnModel().getColumn(3).setPreferredWidth(60);
		this.invoicePanel.setPreferredSize( new Dimension(300,500));
		this.invoiceTable.setDefaultRenderer(Object.class, new invoiceTableRenderer() );
		    
		
	}//initializeInvoicePanel
	
	
	private void initializeCustomerTable()
	{
		String col[] = { "α/α", "ID", "ΟΝΟΜΑ" };
		DefaultTableModel tableModel = new CustomerTableModel( col, 0 );
		int c = 1;
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
		this.customerPanel.setPreferredSize( new Dimension(300,500));
		this.customerTable.getSelectionModel().addListSelectionListener( new SelectionListener() );

		    
		
	}//initializeCustomerTable
	
	
	private void updateInvoiceTable( String customerId )
	{
		DefaultTableModel tb = (DefaultTableModel) invoiceTable.getModel();
		int c = 1;
		tb.setRowCount( 0 );
		
		for( Invoice x:this.invoiceList.getInvoicesList() )
		{
			if( x.getCustomerId().equals( customerId) )
			{
				tb.addRow(new Object[] { c, x.getId(), x.getCustomerId(), x.getAmount() } );
				c++;
				
			}//if
			
		}//for
		
	}//updateInvoiceTable
	
	
	private void initializeControlPanel()
	{				
		this.controlPanel = new JPanel();
		this.controlPanel.setPreferredSize( new Dimension( 800, 50 ) );
		this.newCustomer = new JButton( "Εισαγωγή Πελάτη" );
		this.updateCustomer = new JButton( "Ενημέρωση Πελάτη" );
		this.insertInvoice = new JButton( "Εισαγωγή Τιμολογίου" );
		this.payInvoice = new JButton( "Εξόφληση Τιμολογίου" );
		this.controlPanel.add( this.newCustomer );
		this.controlPanel.add( this.updateCustomer );
		this.controlPanel.add( this.insertInvoice );
		this.controlPanel.add( this.payInvoice );
		this.newCustomer.addActionListener( new ButtonListener() );
		this.updateCustomer.addActionListener( new UpdateCustomerListener() );
		this.insertInvoice.addActionListener( new InsertInvoiceListener() );
		this.payInvoice.addActionListener( new PayInvoiceListener() );
		this.controlPanel.setBorder( BorderFactory.createEtchedBorder( EtchedBorder.LOWERED ) );
		
	}//initializeControlPanel
	
	
	class ButtonListener implements ActionListener 
	{
        public void actionPerformed( ActionEvent e ) 
        {   
        	new AddCustomer( mainFrame, customerList );
        	DefaultTableModel tableModel = (DefaultTableModel) customerTable.getModel();
        	tableModel.setRowCount( 0 );
        	int c = 1;
        	
        	for( Customer x: customerList )
			{        		
				tableModel.addRow( new Object[] { c, x.getId(), x.getName() } );
				c++;
				
			}//for
        	
        	if(!customerList.isEmpty())
        	{
        		customerTable.setRowSelectionInterval( 0, 0 );
        	}
        }//actionPerformed
        
    }//Class ButtonListener
	
	
	class UpdateCustomerListener implements ActionListener 
	{
        public void actionPerformed( ActionEvent e ) 
        {   
        	Customer selectedCustomer = null;
        	int rowIndex = customerTable.getSelectedRow();
        	if( rowIndex == -1 )
        	{
        		return;
        	}
        	String selectedId = (String) customerTable.getModel().getValueAt( rowIndex, 1 );
        	
        	for( Customer x: customerList )
			{
        		if( x.getId().equals( selectedId ) )
				{
        			selectedCustomer = x;
        			break;
	
				}//if
        		
			}//for
        	        	
			new UpdateCustomer( mainFrame, customerList, selectedCustomer );
        	DefaultTableModel tableModel = (DefaultTableModel) customerTable.getModel();
        	tableModel.setRowCount( 0 );
        	int c = 1;
        	
        	for( Customer x: customerList )
			{        		        		
				tableModel.addRow( new Object[] { c, x.getId(), x.getName() } );
				
				c++;
			}//for
        	customerTable.setRowSelectionInterval( rowIndex, rowIndex );
        }//actionPerformed
        
    }//ButtonListener
	

	class InsertInvoiceListener implements ActionListener 
	{
		public void actionPerformed( ActionEvent e ) 
        {   
			int rowIndex = customerTable.getSelectedRow();
			if( rowIndex == -1 )
			{
				return;
			}
			String id = (String) customerTable.getModel().getValueAt( rowIndex, 1);
        	new InsertInvoice( mainFrame, invoiceList, id );
        	updateInvoiceTable( id );
        	        	
        }//actionPerformed
        
    }//ButtonListener
	
	class PayInvoiceListener implements ActionListener 
	{
		public void actionPerformed( ActionEvent e ) 
        {   
			int rowIndex = customerTable.getSelectedRow();
			if( rowIndex == -1 )
			{
				return;
			}
			String customerId = (String) customerTable.getModel().getValueAt( rowIndex, 1);

			Invoice selectedInvoice = null;
        	int rowIndex2 = invoiceTable.getSelectedRow();
        	if( rowIndex2 == -1 )
        	{
        		return;
        	}
        	String selectedId = (String) invoiceTable.getModel().getValueAt( rowIndex2, 1 );
        	
        	for( Invoice x: invoiceList.getInvoicesList() )
			{
        		if( x.getId().equals( selectedId ) )
				{
        			selectedInvoice = x;
        			break;
	
				}//if
        		
			}//for
			
        	if ( selectedInvoice.getState().getColor().equals(Color.red) )  // Unpaid
			{
        		new PayInvoice( mainFrame, invoiceList, customerId, selectedInvoice );
            	updateInvoiceTable( selectedInvoice.getCustomerId() );
				return;
			}
        	
         }//actionPerformed
        
    }//ButtonListener
	
	
	class SelectionListener implements ListSelectionListener
	{
		public void valueChanged(ListSelectionEvent lse) 
		{
	        if ( !lse.getValueIsAdjusting() ) 
	        {
	        	int index = customerTable.getSelectedRow();
	        	
	        	if( index == -1)
	        	{
	        		return;
	        		
	        	}//if
	        	
	        	updateInvoiceTable( (String) customerTable.getModel().getValueAt( index, 1 ) );
	        	
	        }//if
	        
	    }//valueChanged
	
	}//class SelectionListener
	
	
	class CustomerTableModel extends DefaultTableModel implements Serializable
	{
		private static final long serialVersionUID = 1L;
		
		public CustomerTableModel( String a[], int rows )
		{
			super( a, rows );
			
		}//Constructor
		
		public boolean isCellEditable( int row, int column )
	    {
	      return false;
	      
	    }//isCellEditable
		
	}//Class CustomerTableModel
	
	
	class invoiceTableRenderer extends DefaultTableCellRenderer
	{
		private static final long serialVersionUID = 1L;
		
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) 
		{

	        super.getTableCellRendererComponent( table, value, isSelected, hasFocus, row, col );
	        State status = null;
	        
	        for( Invoice x: invoiceList.getInvoicesList() )
	        {
	        	if( x.getId().equals( (String) table.getModel().getValueAt( row, 1 ) ) )
	        	{
	        		status = x.getState();
	        		break;
	        		
	        	}//if
	        	
	        }//for
	        
	        setBackground( status.getColor() );
	        return this;
	        
	    }//getTableCellRendererComponent  
		
	}//invoiceTableRenderer
	
	
}//Class MainFrame
