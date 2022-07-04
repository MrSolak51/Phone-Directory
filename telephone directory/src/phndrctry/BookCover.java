package phndrctry;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class BookCover extends JFrame implements ActionListener{
	JLabel phone,directory;
	BookCoverJPanel bc;
	ImageIcon icon;
	JButton openTheBook;
	BookCover(){
		//setted the features of the "book cover" frame
		icon=new ImageIcon("phoneDirIcon.jpg");
		this.setTitle("Phone Directory");
		this.setIconImage(icon.getImage());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		this.setSize(315,500);
		this.setLayout(null);
		
		//Called the "cover image" panel
		bc=new BookCoverJPanel();
		
		//setted the features of the "open" button
		openTheBook=new JButton("OPEN");
		openTheBook.addActionListener(this);
		openTheBook.setBounds(0,400,300,50);
		
		//setted the features of the "cover text" labels
		phone=new JLabel("PHONE");
		directory=new JLabel("DIRECTORY");
		phone.setBounds(0,0,250,50);
		directory.setBounds(75,50,250,50);
		phone.setFont(new Font("MV Boli",Font.BOLD,35));
		directory.setFont(new Font("MV Boli",Font.BOLD,35));
		
		//added components to the frame
		this.add(openTheBook);
		this.add(bc);
		this.add(phone);
		this.add(directory);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		//The current frame is closed and the "Numbers" frame is opened.
		if(e.getSource()==openTheBook) {
			this.dispose();
			try {
				Numbers num=new Numbers();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
