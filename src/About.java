
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class About extends JFrame implements ActionListener{
	About()
	{
		setLayout(null);
		setBounds(600, 200, 700, 600);
		
		ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("icons/windows.png"));
		Image newimg = img.getImage().getScaledInstance(400, 80, Image.SCALE_DEFAULT);
		ImageIcon imgicon = new ImageIcon(newimg);
		JLabel jl1 = new JLabel(imgicon);
		jl1.setBounds(150, 50, 400, 80);
		add(jl1);
		
		JLabel jl2 = new JLabel("Notepad By Nikhil Deshnehare");
		jl2.setBounds(150, 150, 400, 80);
		jl2.setFont(new Font("SAN SERIF",Font.PLAIN,20));
		add(jl2);
		
		
		
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.setVisible(false);
	}
	
	public static void main(String[] args) {
		new About().setVisible(true);
	}

	
}
