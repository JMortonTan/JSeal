//import com.sun.javafx.iio.ImageFrame;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.nio.file.Files;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.beans.PropertyChangeEvent.*;

public class JSealDesktop extends JFrame implements ActionListener {
    //Flags
    boolean fileSelect = false;
    int selectedAction;

    //GUI Components
    JTextField location = new JTextField("C:\\", 25);
    JPanel pane = new JPanel();
    JButton selectFile = new JButton("Browse");
    JButton start = new JButton("Start");
    Choice selector = new Choice();
    JLabel imageFrame = new JLabel();

    //File Chooser
    JFileChooser chooser = new JFileChooser();
    ImagePreviewPanel previewer = new ImagePreviewPanel();

    String path;

    //Default Constructor.
    JSealDesktop() {
        //Call Super Constructor. (sets program name in header)
        super("JSeal");
        setBounds(100, 100, 600, 600);

        //JFrame Settings.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        //Declare and Instantiate Panels.
        Container con = this.getContentPane(); // inherit main frame
        con.add(pane);

        //JTextField Settings.

        //ActionListener Stuff
        selectFile.addActionListener(this);
        start.addActionListener(this);

        //Set Start button to default disabled.
        start.setEnabled(false);
        selector.setEnabled(false);

        //Choice Selection.
        selector.add("--------");
        selector.add("Encode");
        selector.add("Decode");

        //Populate Panel
        pane.add(selectFile);
        pane.add(location);
        pane.add(selector);
        pane.add(start);
        pane.add(imageFrame);

        //Misc.

        //Display GUI
        setVisible(true); // make frame visible
    }

    //Event Handler for buttons
    public void actionPerformed(ActionEvent event) {
        setVisible(true);  // show something

        Object source = event.getSource();
        if (source == selectFile) {

            //Enable previewer.
            chooser.setAccessory(this.previewer);
            chooser.addPropertyChangeListener(previewer);

            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            int returnVal = chooser.showOpenDialog(JSealDesktop.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File inputFile = chooser.getSelectedFile();

                path = inputFile.getAbsolutePath();
                location.setText(path);

                //Check if selected file is png.
                if (path.endsWith(".png")) {
                    this.fileSelect = true;
                    try {
                        this.imageFrame.setIcon(new ImageIcon(ImageIO.read(new File(path))));
                    } catch (Exception e) {};
                }
                else {
                    JOptionPane.showMessageDialog(null, "File selected is not a .png image",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        if (source == start) {
            setVisible(true);  // show something

            int action = selector.getSelectedIndex();
            System.out.print(action);

            switch(action) {
                case 1:
                    //JSeal.encode(this.path);
                    break;
                case 2:
                    //JSeal.decode(this.path);
                    break;
                default: JOptionPane.showMessageDialog(null, "Please select an action from the dropdown menu.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                    break;
            }

            this.fileSelect = false;
        }

        if (source == selector) {

        }

        //Enable options.
        this.enableSelector();
    }

    public void enableSelector() {
        if (this.fileSelect) {
            this.selector.setEnabled(true);
            this.start.setEnabled(true);
        }

    }

    //Instantiate Main.
    public static void main(String args[]) {
        new JSealDesktop();
    }
}