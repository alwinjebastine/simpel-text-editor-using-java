import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.plaf.metal.*;
import javax.swing.text.*;
class mini extends JFrame implements ActionListener {
	
	//textarea
	JTextArea ta;
	//frame
	JFrame fra;

	mini()
	{

		fra = new JFrame("Text Editor");

		try {

			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			MetalLookAndFeel.setCurrentTheme(new OceanTheme());
		}
		catch (Exception e) {
		}
		ta = new JTextArea();
		//menubar
		JMenuBar mb = new JMenuBar();
		//menu
		JMenu m1 = new JMenu("FILE"); 
		
		//menuitem
		JMenuItem mi1 = new JMenuItem("New");
		JMenuItem mi2 = new JMenuItem("Open");
		JMenuItem mi3 = new JMenuItem("Save");
		JMenuItem mi4 = new JMenuItem("Print");

        //actionlistener
		mi1.addActionListener(this);
		mi2.addActionListener(this);
		mi3.addActionListener(this);
		mi4.addActionListener(this);
        
        //adding menuitem to menu
		m1.add(mi1);
		m1.add(mi2);
		m1.add(mi3);
		m1.add(mi4);
		
		JMenu m2 = new JMenu("EDIT");
		JMenuItem mi5 = new JMenuItem("Cut");
		JMenuItem mi6 = new JMenuItem("Copy");
		JMenuItem mi7 = new JMenuItem("Paste");
		
		mi5.addActionListener(this);
		mi6.addActionListener(this);
		mi7.addActionListener(this);

		m2.add(mi5);
		m2.add(mi6);
		m2.add(mi7);
		
		JMenuItem mc = new JMenuItem("CLOSE");
		mc.addActionListener(this);
	
		mb.add(m1);
		mb.add(m2);
		mb.add(mc);
		fra.setJMenuBar(mb);
		fra.add(ta);
		fra.setSize(650,650);
		fra.show();
	}
	
	public void actionPerformed(ActionEvent e)
	{
		String s = e.getActionCommand();

		if (s.equals("Cut")) {
			ta.cut();
		}
		else if (s.equals("Copy")) {
			ta.copy();
		}
		else if (s.equals("Paste")) {
			ta.paste();
		}
		else if (s.equals("New")) {
			ta.setText("");
		}
		else if (s.equals("CLOSE")) {
			fra.setVisible(false);
		}
		else if (s.equals("Print")) {
			try {
				ta.print();
			}
			catch (Exception evt) {
				JOptionPane.showMessageDialog(fra, evt.getMessage());
			}
		}
		else if(s.equals("Save")){
			JFileChooser j = new JFileChooser();
			int r=j.showSaveDialog(null);
			if(r==JFileChooser.APPROVE_OPTION){
				File fi = new File(j.getSelectedFile().getAbsolutePath());
				
				try{
					FileWriter wr = new FileWriter(fi, false);

					BufferedWriter w = new BufferedWriter(wr);
					w.write(ta.getText());
					w.flush();
					w.close();
				}
				catch (Exception evt) {
					JOptionPane.showMessageDialog(fra,evt.getMessage());
				}
			}
			else{
				JOptionPane.showMessageDialog(fra,"the user cancelled the operation");
			}
		}
		else if (s.equals("Open")) {
			JFileChooser j = new JFileChooser();
			int r = j.showOpenDialog(null);
			if (r == JFileChooser.APPROVE_OPTION) {

				File fi = new File(j.getSelectedFile().getAbsolutePath());
				try {
					String s1 = "", sl = "";

					FileReader fr = new FileReader(fi);
					BufferedReader br = new BufferedReader(fr);
					sl = br.readLine();
					while ((s1 = br.readLine()) != null) {
						sl = sl + "\n" + s1;
					}
					ta.setText(sl);
				}
				catch (Exception evt) {
					JOptionPane.showMessageDialog(fra,evt.getMessage());
				}
			}
			else{
				JOptionPane.showMessageDialog(fra, "the user cancelled the operation");}
		}
	}
	public static void main(String args[])
	{
		mini e = new mini();
	}
}
