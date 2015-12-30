package gui;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import classes.Invoice;
import classes.InvoiceList;


public class InsertInvoice 
{
	private JDialog mainWindow;
	private JPanel mainPanel;
	private JButton btnAccept;
	private JButton btnCancel;
	private JLabel lblId;
	private JLabel lblAmmount;
	private JTextField txtId;
	private JTextField txtAmmount;
	private InvoiceList invoiceList;
	private String customerId;
	
				
	public InsertInvoice( Window owner, InvoiceList invoiceList, String customerId )
	{	
		this.invoiceList = invoiceList;
		this.customerId = customerId;
		initializeComponents();
		
		//mainWindow
		this.mainWindow = new JDialog( owner, "ΕΙΣΑΓΩΓΗ ΝΕΟΥ ΤΙΜΟΛΟΓΙΟΥ ΣΤΟΝ ΠΕΛΑΤΗ " + this.customerId, Dialog.ModalityType.APPLICATION_MODAL );
		this.mainWindow.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		this.mainWindow.setPreferredSize( new Dimension( 400,200 ) );
		this.mainWindow.add( this.mainPanel );
		this.mainWindow.pack();
		this.mainWindow.setLocation( owner.getLocationOnScreen().x, owner.getLocationOnScreen().y );
		this.mainWindow.setVisible( true );
					
	}//Constructor
	
				
	private void initializeComponents()
	{
		SpringLayout sp = new SpringLayout();
		this.mainPanel = new JPanel( sp );
		this.mainPanel.setPreferredSize( new Dimension( 700, 100 ) );
		this.btnCancel = new JButton( "Ακύρωση" );
		this.btnAccept = new JButton( "OK" );
		this.btnAccept.setPreferredSize( this.btnCancel.getPreferredSize() );
		this.btnAccept.addActionListener( new ButtonOKListener() );
		this.btnCancel.addActionListener( new ButtonListener() );
		this.lblId = new JLabel( "Κωδικός" );
		this.lblAmmount = new JLabel( "Χρέωση" );
		this.txtId = new JTextField();
		this.txtAmmount = new JTextField();
		this.txtId.setPreferredSize( new Dimension( 100, 30 ) );
		this.txtAmmount.setPreferredSize( new Dimension( 100, 30 ) );
		sp.putConstraint( SpringLayout.WEST, this.lblId, 15, SpringLayout.WEST, this.mainPanel );
		sp.putConstraint( SpringLayout.NORTH, this.lblId, 55, SpringLayout.NORTH, this.mainPanel );
		sp.putConstraint( SpringLayout.WEST, this.txtId, 5, SpringLayout.EAST, this.lblId );
		sp.putConstraint( SpringLayout.NORTH, this.txtId, -3, SpringLayout.NORTH, this.lblId );
		sp.putConstraint( SpringLayout.WEST, this.lblAmmount, 15, SpringLayout.EAST, this.txtId );
		sp.putConstraint( SpringLayout.NORTH, this.lblAmmount, 0, SpringLayout.NORTH, this.txtId );
		sp.putConstraint( SpringLayout.WEST, this.txtAmmount, 5, SpringLayout.EAST, this.lblAmmount );
		sp.putConstraint( SpringLayout.NORTH, this.txtAmmount, -3, SpringLayout.NORTH, this.lblAmmount );
		sp.putConstraint( SpringLayout.WEST, this.btnCancel, 25, SpringLayout.WEST, this.txtId );
		sp.putConstraint( SpringLayout.NORTH, this.btnCancel, 25, SpringLayout.SOUTH, this.txtId );
		sp.putConstraint( SpringLayout.WEST, this.btnAccept, 5, SpringLayout.EAST, this.btnCancel );
		sp.putConstraint( SpringLayout.NORTH, this.btnAccept, 0, SpringLayout.NORTH, this.btnCancel );
		this.mainPanel.add( this.btnCancel );
		this.mainPanel.add( this.btnAccept );
		this.mainPanel.add( this.lblId );
		this.mainPanel.add( this.txtId );
		this.mainPanel.add( this.txtAmmount );
		this.mainPanel.add( this.lblAmmount );
		
	}//initializeComponents
	
	
	class ButtonListener implements ActionListener 
	{
        public void actionPerformed( ActionEvent e ) 
        {   
        	mainWindow.dispose();
        	
        }//actionPerformed
        
    }//ButtonListener
	
	
	class ButtonOKListener implements ActionListener 
	{
        public void actionPerformed( ActionEvent e ) 
        {   
        	for( Invoice invoice: invoiceList.getInvoicesList() )
        	{
        		if( invoice.getId().equals( txtId.getText() ) )
        		{
        			return;
        		}
        	}
        	
        	Invoice i = new Invoice( txtId.getText(), customerId, Double.parseDouble(txtAmmount.getText() ) );
        	invoiceList.addInvoice( i );
        	mainWindow.dispose();
        	
        }//actionPerformed
        
    }//ButtonListener

}//Class
