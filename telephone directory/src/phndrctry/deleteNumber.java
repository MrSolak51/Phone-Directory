package phndrctry;

import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class deleteNumber extends JFrame implements ActionListener {
		ImageIcon icon;	
	
		JButton deleteNumber;
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
		deleteNumber(){}
		deleteNumber (String MainData,int index)throws IOException{
			//setted the features of the "AddNumber" frame
			icon=new ImageIcon("phoneDirIcon.jpg");
			this.setIconImage(icon.getImage());
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.setVisible(true);
			this.setSize(300,600);
			this.setLayout(null);
			this.setResizable(false);
			this.setTitle("Are You Sure ?");
			
			//trim the blanks in main data
			mainData=MainData.trim();
			
			//setted the features of the "delete number" button
			deleteNumber=new JButton("Delete Contact");
			deleteNumber.setBounds(0,510,300,25);
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
			
			name.setEditable(false);
			surName.setEditable(false);
			phoneticName.setEditable(false);
			number.setEditable(false);
			
			nameL.setBounds(0,0,150,25);
			surNameL.setBounds(150,0,150,25);
			phoneticNameL.setBounds(0,50,150,25);
			numberL.setBounds(150,50,150,25);
			
			name.setBounds(0,25,150,25);
			surName.setBounds(150,25,150,25);
			phoneticName.setBounds(0,75,150,25);
			number.setBounds(150,75,150,25);
			
			int a=0;
			for(int i=0;i<mainData.length();i++) {
				if(mainData.charAt(i)==' ') {
					a++;
				}
				else {
					switch (a) {
					case 0:name.setText(name.getText()+mainData.charAt(i));break;
					case 1:surName.setText(surName.getText()+mainData.charAt(i));break;
					case 2:phoneticName.setText(phoneticName.getText()+mainData.charAt(i));break;
					case 3:number.setText(number.getText()+mainData.charAt(i));break;
					}
				}
			}
			
			//setted the features of the "contact info" label and textarea
			contactInfoL=new JLabel("Contact Info");
			contactInfoL.setBounds(0,100,300,25);
			
			contactInfo=new JTextArea();
			contactInfo.setBounds(0,125,300,385);
			contactInfo.setLineWrap(true);
			contactInfo.setEditable(false);
			
			//added components to the frame
			this.add(contactInfoL);
			this.add(contactInfo);
			this.add(deleteNumber);
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
			deleteNumber.addActionListener(this);
		}
		
		public void readTheNumbers() throws IOException {
			//read file and find the contact to delete
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
			if(e.getSource()==deleteNumber) {
				//delete the number,close the current frame and open "Numbers" frame
				
					for(int i=0;i<numbers.size();i++) {
						if(numbers.get(i).contains(mainData)) {
							numbers.remove(i);
							infos.remove(i);
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