import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class JSealDesktop extends JFrame implements ActionListener
{
  JTextField location = new JTextField("C:\\", 25);
  JPanel pane = new JPanel();
  JButton selectFile = new JButton("Browse");
  JButton proceed = new JButton("Proceed");
  Choice selector = new Choice();

  //Default Constructor.
  JSealDesktop()
  {
	//Call Super Constructor.
    super("JSeal"); setBounds(100,100,550,65);

	//JFrame Settings.
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);

	//Declare and Instantiate Panels.
    Container con = this.getContentPane(); // inherit main frame
    con.add(pane);

	//JTextField Settings.

	//ActionListener Stuff
    selectFile.addActionListener(this);
    proceed.addActionListener(this);

    //Choice Selection.
    selector.add("--------");
    selector.add("Encode");
    selector.add("Decode");

	//Populate Panel
    pane.add(selectFile); pane.add(location); pane.add(selector); pane.add(proceed);

    //Misc.

	//Display GUI
    setVisible(true); // make frame visible
  }

  //Event Handler for buttons
  public void actionPerformed(ActionEvent event)
  {
    Object source = event.getSource();
    if (source == selectFile)
    {
      location.setText("Browse selected");
      JOptionPane.showMessageDialog(null,"File Select Activated","Message Dialog",
      JOptionPane.PLAIN_MESSAGE); setVisible(true);  // show something
    }

    if (source == proceed)
	{
	  location.setText("Proceed selected");
	  JOptionPane.showMessageDialog(null,"Proceed!","Message Dialog",
	  JOptionPane.PLAIN_MESSAGE); setVisible(true);  // show something
    }
  }

  //Instantiate Main.
  public static void main(String args[]) {new JSealDesktop();}
}