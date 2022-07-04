package phndrctry;

import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class editNumber extends JFrame implements ActionListener {
	ImageIcon icon;
	
	JButton editNumber;
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
	
	String mainData;
	ArrayList<String> numbers=new ArrayList();
	
	JTextArea contactInfo;
	JLabel contactInfoL;
	
	String info="Choose a contact for contact info";
	ArrayList<String> infos=new ArrayList();
	editNumber(){}
	editNumber (String MainData,int index)throws IOException{
		//setted the features of the "editNumber" frame		
		icon=new ImageIcon("phoneDirIcon.jpg");
		this.setIconImage(icon.getImage());
		this.setTitle("Edit");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(300,600);
		this.setLayout(null);
		this.setResizable(false);

		//trim the blanks in main data
		mainData=MainData.trim();
		
		//setted the features of the "edit number" button
		editNumber=new JButton("Edit Contact");
		editNumber.setBounds(0,510,300,25);
		
		//setted the features of the "cancel" button
		cancel=new JButton("Cancel");
		cancel.setBounds(0,535,300,25);
		cancel.addActionListener(this);
		
		//setted the features of the "name-surname-phoneticName-number" label and textfields
		name=new JTextField("");
		surName=new JTextField("");
		phoneticName=new JTextField("");
		number=new JTextField("");
		name.setBounds(0,25,150,25);
		surName.setBounds(150,25,150,25);
		phoneticName.setBounds(0,75,150,25);
		number.setBounds(150,75,150,25);
				
		nameL=new JLabel("name");
		surNameL=new JLabel("surname");
		phoneticNameL=new JLabel("phonetic name");
		numberL=new JLabel("number");
		
		nameL.setBounds(0,0,150,25);
		surNameL.setBounds(150,0,150,25);
		phoneticNameL.setBounds(0,50,150,25);
		numberL.setBounds(150,50,150,25);
		
		
		

		int a=0;
		for(int i=0;i<mainData.length();i++) {
			if(mainData.charAt(i)==' ') {
				a++;
			}
			else {
				switch (a) {
				case 0:name.setText(name.getText()+mainData.charAt(i));break;
				case 1:surName.setText(surName.getText()+mainData.charAt(i));break;
				case 2:if(mainData.charAt(i)=='\'') {}
					else 
						phoneticName.setText(phoneticName.getText()+mainData.charAt(i));
				break;
				case 3:number.setText(number.getText()+mainData.charAt(i));break;
				}
			}
			
		}
		a=0;
		
		//setted the features of the "contact info" label and textarea
		contactInfoL=new JLabel("Contact Info");
		contactInfoL.setBounds(0,100,300,25);
		
		contactInfo=new JTextArea();
		contactInfo.setBounds(0,125,300,385);
		contactInfo.setLineWrap(true);
		
		//added components to the frame
		this.add(contactInfoL);
		this.add(contactInfo);
		this.add(editNumber);
		this.add(cancel);
		this.add(numberL);
		this.add(nameL);
		this.add(surNameL);
		this.add(phoneticNameL);
		this.add(name);
		this.add(surName);
		this.add(phoneticName);
		this.add(number);
		readTheNumbers();
		contactInfo.setText(infos.get(index));
		editNumber.addActionListener(this);
	}
	
	public void readTheNumbers() throws IOException {
		////read file and find the contact to edit
		file=new File("numbers.txt");
		reader=new FileReader(file);
		BufferedReader bReader=new BufferedReader(reader);
		int data=reader.read();
		String num="";
		while(data!=-1) {
			while((char)data!='|' &&data!=-1) {
				num=num+(char)data;
				data=reader.read();
			}
			numbers.add(num);
			num="";
			info="";
			data=reader.read();
			while((char)data!='|'&&data!=-1) {
				info=info+(char)data;
				data=reader.read();				
			}
			this.infos.add(info);
			data=reader.read();
		}
		reader.close();
	}
	public void writeTheNumbers() {
		//write the new contacts to the file
		file=new File("numbers.txt");
		try {
			writer=new FileWriter(file,false);
			reader=new FileReader(file);
			for(int i=0;i<numbers.size();i++) {
				if(i==0)
					writer.write(numbers.get(i)+" |"+infos.get(i)+"|");
				else
					writer.write(numbers.get(i)+" |"+infos.get(i)+"|");
			
			}	
			reader.close();
			writer.flush();
			writer.close();
			}
		catch(IOException e1) {
			System.out.println("error"+e1.getMessage());
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==editNumber) {
			//edit the file by new inputs,close the current frame and open "Numbers" frame
			
				for(int i=0;i<numbers.size();i++) {
					if(numbers.get(i).contains(mainData)) {
						numbers.set(i,name.getText()+" "+surName.getText()+" "+"'"+phoneticName.getText()+"'"+" "+number.getText());
						infos.set(i, contactInfo.getText());
					}
				}
				writeTheNumbers();
				this.dispose();
				try {
					Numbers num=new Numbers();
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