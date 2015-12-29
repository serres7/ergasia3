package gui;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import classes.Customer;

public class UpdateCustomer 
{
	private JDialog mainWindow;
	private JPanel mainPanel;
	private JButton btnAccept;
	private JButton btnCancel;
	private JLabel lblId;
	private JLabel lblName;
	private JTextField txtId;
	private JTextField txtName;
	private ArrayList<Customer> customerList;
				
	public UpdateCustomer( Window owner, ArrayList<Customer> customerList, Customer selectedCustomer )
	{	
		this.customerList = customerList;
		initializeComponents();
		txtId.setText(selectedCustomer.getId());
		txtName.setText(selectedCustomer.getName());
		//mainWindow
		this.mainWindow = new JDialog( owner, "ΑΝΕΠΑΡΚΗ ΚΕΦΑΛΑΙΑ", Dialog.ModalityType.APPLICATION_MODAL );
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
		this.lblName = new JLabel( "Όνομα" );
		this.txtId = new JTextField();
		this.txtName = new JTextField();
		this.txtId.setPreferredSize( new Dimension( 100, 30 ) );
		this.txtName.setPreferredSize( new Dimension( 100, 30 ) );
		
		sp.putConstraint( SpringLayout.WEST, this.lblId, 15, SpringLayout.WEST, this.mainPanel );
		sp.putConstraint( SpringLayout.NORTH, this.lblId, 55, SpringLayout.NORTH, this.mainPanel );
		sp.putConstraint( SpringLayout.WEST, this.txtId, 5, SpringLayout.EAST, this.lblId );
		sp.putConstraint( SpringLayout.NORTH, this.txtId, -3, SpringLayout.NORTH, this.lblId );
		
		sp.putConstraint( SpringLayout.WEST, this.lblName, 15, SpringLayout.EAST, this.txtId );
		sp.putConstraint( SpringLayout.NORTH, this.lblName, 0, SpringLayout.NORTH, this.txtId );
		sp.putConstraint( SpringLayout.WEST, this.txtName, 5, SpringLayout.EAST, this.lblName );
		sp.putConstraint( SpringLayout.NORTH, this.txtName, -3, SpringLayout.NORTH, this.lblName );
		
		
		sp.putConstraint( SpringLayout.WEST, this.btnCancel, 25, SpringLayout.WEST, this.txtId );
		sp.putConstraint( SpringLayout.NORTH, this.btnCancel, 25, SpringLayout.SOUTH, this.txtId );
		sp.putConstraint( SpringLayout.WEST, this.btnAccept, 5, SpringLayout.EAST, this.btnCancel );
		sp.putConstraint( SpringLayout.NORTH, this.btnAccept, 0, SpringLayout.NORTH, this.btnCancel );
		
		this.mainPanel.add( this.btnCancel );
		this.mainPanel.add( this.btnAccept );
		this.mainPanel.add( this.lblId );
		this.mainPanel.add( this.txtId );
		this.mainPanel.add( this.txtName );
		this.mainPanel.add( this.lblName );
		
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
        	for( Customer customer: customerList )
        	{
        		if( customer.getId().equals( txtId.getText() ) )
        		{
        			return;
        		}
        	}
        	customerList.add( new Customer( txtId.getText(), txtName.getText() ) );
        	
        	mainWindow.dispose();
        	
        }//actionPerformed
        
    }//ButtonListener

}//Class