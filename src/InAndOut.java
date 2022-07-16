import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class InAndOut extends Frame implements ActionListener {
    public static void main(String[] args) {
        new InAndOut();
    }
    JMenuItem openMenuItem;
    JMenuItem saveMenuItem;
    JMenuItem exitMenuItem;
    JTextArea textArea;
    JFrame frame ;
    InAndOut(){
        textArea =new JTextArea();
        frame =new JFrame("Input and output");
        JMenuBar menuBar =new JMenuBar();
        openMenuItem =new JMenuItem("Open",'O');
        openMenuItem.addActionListener(this);
        openMenuItem.setActionCommand("Open");
        openMenuItem.setAccelerator(KeyStroke.getKeyStroke('O', KeyEvent.CTRL_DOWN_MASK));
        saveMenuItem =new JMenuItem("Save",'S');
        saveMenuItem.setActionCommand("Save");
        saveMenuItem.addActionListener(this);
        saveMenuItem.setAccelerator(KeyStroke.getKeyStroke('S', KeyEvent.CTRL_DOWN_MASK));
        exitMenuItem =new JMenuItem("Exit",'X');
        exitMenuItem.setActionCommand("Exit");
        exitMenuItem.addActionListener(this);
        exitMenuItem.setAccelerator(KeyStroke.getKeyStroke('X', KeyEvent.CTRL_DOWN_MASK));
        JMenu fileMenu =new JMenu("File");
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);
        frame.setSize(600,600);
        frame.setJMenuBar(menuBar);
        frame.add(textArea);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "Open":openFile();break;
            case "Save":saveFile();break;
            case "Exit":System.exit(0);break;
            default:System.out.println("default");break;
        }

    }
    void openFile(){
                JFileChooser fileChooser =new JFileChooser();
        FileInputStream fileInputStream =null;
                if(fileChooser.showOpenDialog(frame)==JFileChooser.APPROVE_OPTION){
                 try {
                     String path= fileChooser.getSelectedFile().getPath();
                     fileInputStream =new FileInputStream(path);
                     int size =fileInputStream.available();
                     byte[] b =new byte [size];
                     String text = String.valueOf(fileInputStream.read(b));
                     textArea.setText(new String(text));
                 }catch (Exception e){

                 }finally {
                     try {
                         fileInputStream.close();
                     } catch (IOException e) {
                         e.printStackTrace();
                     }
                 }


                }


        System.out.println("file is Opened");
    }
    void saveFile(){
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(frame)==JFileChooser.APPROVE_OPTION){
            try {
                String path = fileChooser.getSelectedFile().getPath();
                FileOutputStream fileOutputStream =new FileOutputStream(path);
                byte b []=textArea.getText().getBytes(StandardCharsets.UTF_8);
                fileOutputStream.write(b);
            }catch (Exception e){

            }

        }


        System.out.println("Save");
    }
}
