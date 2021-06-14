import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;

public class Notepad extends JFrame implements ActionListener{
	JTextArea area;
	JScrollPane pane;
	String text;
	Notepad()
	{
		setBounds(0, 0, 1920, 1080);
		JMenuBar menubar = new JMenuBar();
		
		JMenu file = new JMenu("File");
		
		JMenuItem newdocument = new JMenuItem("New");
		newdocument.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
		newdocument.addActionListener(this);
		
		JMenuItem opendocument = new JMenuItem("Open");
		opendocument.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
		opendocument.addActionListener(this);
		
		JMenuItem savedocument = new JMenuItem("Save");
		savedocument.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
		savedocument.addActionListener(this);
		
		JMenuItem printdocument = new JMenuItem("Print");
		printdocument.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));
		printdocument.addActionListener(this);
		
		JMenuItem exitdocument = new JMenuItem("Exit");
		exitdocument.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0));
		exitdocument.addActionListener(this);
		
		file.add(newdocument);
		file.add(opendocument);
		file.add(savedocument);
		file.add(printdocument);
		file.add(exitdocument);
		
		JMenu edit = new JMenu("Edit");
		
		JMenuItem copy = new JMenuItem("Copy");
		copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
		copy.addActionListener(this);
		
		JMenuItem paste = new JMenuItem("Paste");
		paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK));
		paste.addActionListener(this);
		
		JMenuItem cut = new JMenuItem("Cut");
		cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
		cut.addActionListener(this);
		
		JMenuItem selectAll = new JMenuItem("Select All");
		selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));
		selectAll.addActionListener(this);
		
		edit.add(copy);
		edit.add(paste);
		edit.add(cut);
		edit.add(selectAll);
		
		
		JMenu help = new JMenu("Help");
		
		JMenuItem about = new JMenuItem("About the Notepad");
		about.addActionListener(this);
		
		help.add(about);
		
		menubar.add(file);
		menubar.add(edit);
		menubar.add(help);
		
		setJMenuBar(menubar);
		
		area = new JTextArea();
		area.setFont(new Font("SAN_SERIF", Font.PLAIN, 20));
		area.setLineWrap(true);
		area.setWrapStyleWord(true);
		pane = new JScrollPane(area);
		pane.setBorder(null);
		add(pane, BorderLayout.CENTER);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getActionCommand().equals("New")) {
			area.setText("");
		}
		else if (e.getActionCommand().equals("Open")) {
			JFileChooser chooser = new JFileChooser();
			chooser.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter restriction = new FileNameExtensionFilter("Only .txt File","txt");
			chooser.addChoosableFileFilter(restriction);
			
			
			int action = chooser.showOpenDialog(this);
			if(action != JFileChooser.APPROVE_OPTION)
			{
				return;
			}
			
			File file = chooser.getSelectedFile();
			
			try {
				
				BufferedReader reader = new BufferedReader(new FileReader(file));
				area.read(reader,null);
				
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		else if (e.getActionCommand().equals("Save")) {
			JFileChooser saveas = new JFileChooser();
			saveas.setApproveButtonText("Save");
			int action = saveas.showOpenDialog(this);
			if(action != JFileChooser.APPROVE_OPTION)
			{
				return;
			}
			
			File filename = new File(saveas.getSelectedFile()+".txt");
			BufferedWriter outFile = null;
			
			try {
				outFile = new BufferedWriter(new FileWriter(filename));
				area.write(outFile);
			} catch (Exception e2) {
				// TODO: handle exception
				
			}
		}
		
		
		else if (e.getActionCommand().equals("Print"))
			try {
				area.print();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		
		else if (e.getActionCommand().equals("Exit"))
		{
			System.exit(ABORT);
		}
		
		else if (e.getActionCommand().equals("Copy"))
		{
			text = area.getSelectedText();
		}
		
		else if (e.getActionCommand().equals("Paste"))
		{
			area.insert(text,area.getCaretPosition());
		}
		
		else if (e.getActionCommand().equals("Cut"))
		{
			text = area.getSelectedText();
			area.replaceRange("", area.getSelectionStart(), area.getSelectionEnd());
		}
		
		else if (e.getActionCommand().equals("Select All"))
		{
			area.selectAll();
		}
		
		else if (e.getActionCommand().equals("About the Notepad"))
		{
			new About().setVisible(true);
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Notepad().setVisible(true);
	}


	

}
