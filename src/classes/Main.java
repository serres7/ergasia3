package classes;

import java.util.ArrayList;

import gui.MainFrame;

public class Main 
{

	public static void main( String[] args ) 
	{

		Customer qq=new Customer("0001", "ΓΑΪΤΑΝΗΣ ΙΩΑΝΝΗΣ");
		Customer q1=new Customer("0002", "ΑΘΑΝΑΣΙΟΣ ΔΗΜΗΤΡΙΑΔΗΣ");
		ArrayList<Customer> b = new ArrayList<Customer>();
		b.add(qq);
		b.add(q1);
		b.add(qq);
		b.add(qq);b.add(qq);
		b.add(qq);b.add(qq);
		b.add(qq);b.add(qq);
		b.add(qq);b.add(qq);
		b.add(qq);b.add(qq);
		b.add(qq);b.add(qq);
		b.add(qq);b.add(qq);
		b.add(qq);b.add(qq);
		b.add(qq);
		
		b.add(qq);b.add(qq);
		b.add(qq);b.add(qq);
		b.add(qq);b.add(qq);
		b.add(qq);b.add(qq);
		b.add(qq);b.add(qq);
		b.add(qq);b.add(qq);
		MainFrame a = new MainFrame( b );

		new LoginForm().setVisible(true);


	}//Method main

}//Class Main
