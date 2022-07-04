package phndrctry;

import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class AddNumber extends JFrame implements ActionListener {
	ImageIcon icon;
	JButton addNumber;
	JButton cancel;
	
	JTextField phoneticName;
	JTextField name;
	JTextField surName;
	JTextField number;
	
	JLabel phoneticNameL;
	JLabel nameL;
	JLabel surNameL;
	JLabel numberL;

	File file;
	FileReader reader;
	FileWriter writer;
	
	JTextArea contactInfo;
	JLabel contactInfoL;
	AddNumber(){
		//setted the features of the "AddNumber" frame
		icon=new ImageIcon("phoneDirIcon.jpg");
		this.setIconImage(icon.getImage());
		this.setTitle("Add");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(300,600);
		this.setLayout(null);
		this.setResizable(false);
		
		//setted the features of the "addNumber" button
		addNumber=new JButton("Add");
		addNumber.setBounds(0,510,300,25);
		addNumber.addActionListener(this);
		
		//setted the features of the "cancel" button
		cancel=new JButton("Cancel");
		cancel.setBounds(0,535,300,25);
		cancel.addActionListener(this);
		
		//setted the features of the "name-surname-phoneticName-number" label and textfields
		name=new JTextField();
		surName=new JTextField();
		phoneticName=new JTextField();
		number=new JTextField();
		nameL=new JLabel("name");
		surNameL=new JLabel("surname");
		phoneticNameL=new JLabel("phonetic name");
		numberL=new JLabel("number");
		
		nameL.setBounds(0,0,150,25);
		surNameL.setBounds(150,0,150,25);
		phoneticNameL.setBounds(0,50,150,25);
		numberL.setBounds(150,50,150,25);
		
		name.setBounds(0,25,150,25);
		surName.setBounds(150,25,150,25);
		phoneticName.setBounds(0,75,150,25);
		number.setBounds(150,75,150,25);
		
		//setted the features of the "contact info" label and textarea
		contactInfoL=new JLabel("Contact Info");
		contactInfoL.setBounds(0,100,300,25);
		
		contactInfo=new JTextArea();
		contactInfo.setBounds(0,125,300,385);
		contactInfo.setLineWrap(true);
		
		//added components to the frame
		this.add(contactInfoL);
		this.add(contactInfo);
		this.add(addNumber);
		this.add(cancel);
		this.add(numberL);
		this.add(nameL);
		this.add(surNameL);
		this.add(phoneticNameL);
		this.add(name);
		this.add(surName);
		this.add(phoneticName);
		this.add(number);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==addNumber) {
			//add next line to the file by inputs,close the current frame and open "Numbers" frame
			file=new File("numbers.txt");
			try {
				writer=new FileWriter(file,true);
				reader=new FileReader(file);
				BufferedReader bReader=new BufferedReader(reader);
				String data;
				data = bReader.readLine();
				writer.write("\n"+name.getText()+" "+surName.getText()+" "+"'"+phoneticName.getText()+"'"+" "+number.getText()+" |"+contactInfo.getText()+"|");
				reader.close();
				writer.flush();
				writer.close();
			}
			catch(IOException e1) {
				System.out.println(e1.getMessage());
			}
			this.dispose();
			try {
				Numbers num =new Numbers();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		if(e.getSource()==cancel) {
			//close the current frame and open "Numbers" frame
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
