package phndrctry;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.*;

public class Numbers extends JFrame implements ActionListener{
	ImageIcon icon;
	
	JMenuBar menu;
	JMenu options;
	JMenuItem edit;
	JMenuItem add;
	JMenuItem delete;
	JMenu exitM;
	JMenuItem exit;

	JTextArea contactInfo;
	
	File file;
	FileReader reader;
	
	JPanel nums;
	JComboBox number_;
	String data,info="Choose a contact for contact info";
	ArrayList<String> infos=new ArrayList();
	Numbers() throws IOException{
		//setted the features of the "Numbers" frame
		icon=new ImageIcon("phoneDirIcon.jpg");
		this.setIconImage(icon.getImage());
		this.setTitle("Phone Directory");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		this.setSize(315,500);
		this.setLayout(null);
		
		//setted the features of the "menu" menubar
		menu=new JMenuBar();
		menu.setBounds(0,0,300,25);
		
		options=new JMenu("options");
		
		edit=new JMenuItem("edit contact");
		edit.addActionListener(this);
		
		add=new JMenuItem("add contact");
		add.addActionListener(this);
		
		delete=new JMenuItem("delete contact");
		delete.addActionListener(this);
		
		exitM=new JMenu("Exit");
		exit=new JMenuItem("Exit");
		exit.addActionListener(this);
		
		//added child menu components to parent menu components
		options.add(add);
		options.add(edit);
		options.add(delete);
		exitM.add(exit);
		
		//added parent menu components to menu
		menu.add(options);
		menu.add(exitM);

		//setted the features of the "contact numbers" combobox
		number_=new JComboBox();
		number_.setBounds(0,25,300,50);
		
		//setted the features of the "contact information" textarea
		contactInfo=new JTextArea(info);
		contactInfo.setBounds(0,75,300,385);
		contactInfo.setEditable(false);
		contactInfo.setLineWrap(true);
		
		//added components to the frame
		this.add(contactInfo);
		this.add(number_);
		this.add(menu);
		
		readTheNumbers();
		number_.addActionListener(this);
	}
	
	public void readTheNumbers() throws IOException {
		//it read the file and decides whether the text is info or number
		file=new File("numbers.txt");
		reader=new FileReader(file);
		BufferedReader bReader=new BufferedReader(reader);
		int data=reader.read();
		String num="";
		while(data!=-1) {
			while((char)data!='|' &&data!=-1) {
				//numbers
				num=num+(char)data;
				data=reader.read();
			}
			number_.addItem(num);
			num="";
			info="";
			data=reader.read();
			while((char)data!='|'&&data!=-1) {
				//infos
				info=info+(char)data;
				data=reader.read();				
			}
			this.infos.add(info);
			data=reader.read();
		}
		reader.close();
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//The current frame is closed and the "AddNumber" frame is opened.
		if(e.getSource()==add) {
			this.dispose();
			AddNumber an=new AddNumber();
		}
		//The current frame is closed and the "editNumber" frame is opened.
		if(e.getSource()==edit) {
			this.dispose();
			try {
				editNumber en=new editNumber(data,number_.getSelectedIndex());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource()==delete) {
			//The current frame is closed and the "deleteNumber" frame is opened.
			this.dispose();
			try {
				deleteNumber dn=new deleteNumber(data,number_.getSelectedIndex());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource()==exit) {
			//exit the app
			System.exit(0);
		}
		if(e.getSource()==number_) {
			//enter information by number
			data=(String)number_.getSelectedItem();
			contactInfo.setText(infos.get(number_.getSelectedIndex()));
			
		}
				
	}
}