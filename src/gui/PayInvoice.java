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
import javax.swing.SpringLayout;

import classes.Invoice;
import classes.InvoiceList;

public class PayInvoice {

	private JDialog mainWindow;
	private JPanel mainPanel;
	private JButton btnPayment;
	private JButton btnCancel;
	private JLabel lblQuestion;
	private InvoiceList invoiceList;
	private String customerId;
	private Invoice selectedInvoice;
	
	public PayInvoice( Window owner, InvoiceList invoiceList, String customerId, Invoice theInvoice )
	{	
		this.invoiceList = invoiceList;
		this.customerId = customerId;
		this.selectedInvoice = theInvoice;
		initializeComponents();
		
		//mainWindow
		this.mainWindow = new JDialog( owner, "ΕΞΟΦΛΗΣΗ ΤΙΜΟΛΟΓΙΟΥ ΤΟΥ ΠΕΛΑΤΗ " + this.customerId, Dialog.ModalityType.APPLICATION_MODAL );
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
		this.mainPanel.setPreferredSize( new Dimension( 600, 80 ) );
		this.btnCancel = new JButton( "Ακύρωση" );
		this.btnPayment = new JButton( "Εξόφληση" );
		this.btnCancel.setPreferredSize( new Dimension( 90, 30 ) );
		this.btnPayment.setPreferredSize( new Dimension( 90, 30 ) );
		this.btnPayment.addActionListener( new ButtonPaymentListener() );
		this.btnCancel.addActionListener( new ButtonListener() );
		this.lblQuestion = new JLabel( "Εξόφληση του τιμολογίου ;" );
		
		sp.putConstraint( SpringLayout.WEST, this.lblQuestion, 110, SpringLayout.WEST, this.mainPanel );
		sp.putConstraint( SpringLayout.NORTH, this.lblQuestion, 35, SpringLayout.NORTH, this.mainPanel );
		sp.putConstraint( SpringLayout.WEST, this.btnCancel, -50, SpringLayout.WEST, this.lblQuestion );
		sp.putConstraint( SpringLayout.NORTH, this.btnCancel, 35, SpringLayout.SOUTH, this.lblQuestion );
		sp.putConstraint( SpringLayout.WEST, this.btnPayment, 50, SpringLayout.EAST, this.btnCancel );
		sp.putConstraint( SpringLayout.NORTH, this.btnPayment, 0, SpringLayout.NORTH, this.btnCancel );
		this.mainPanel.add( this.btnCancel );
		this.mainPanel.add( this.btnPayment );
		this.mainPanel.add( this.lblQuestion );
		
	}//initializeComponents
	
	class ButtonListener implements ActionListener 
	{
        public void actionPerformed( ActionEvent e ) 
        {   
        	mainWindow.dispose();
        	
        }//actionPerformed
        
    }//ButtonListener
	
	
	class ButtonPaymentListener implements ActionListener 
	{
        public void actionPerformed( ActionEvent e ) 
        {   
        	for( Invoice invoice: invoiceList.getInvoicesList() )
        	{
        		if( invoice.getId().equals( selectedInvoice.getId() ) )
        		{
        			selectedInvoice.updateState();
        		}
        	}

        	mainWindow.dispose();
        	
        }//actionPerformed
        
    }//ButtonListener

}
