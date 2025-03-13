import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import java.awt.*;

public class RRK extends JFrame{
private JTextField number, name, cost;
private final String DELIMITER = ",";
public final String FILE_NAME = "data.txt";

RRK (){
   
    getContentPane().setBackground(Color.GRAY);
    setTitle("Receipt Record Keeper");
  
   

    JLabel Title = new JLabel("RECEIPT RECORD KEEPER");
    Title.setFont(new Font("Arial", Font.BOLD, 25));
    Title.setForeground(Color.BLACK);
    add(Title).setBounds(50, 15, 500, 80);

    JLabel lblnumber = new JLabel("Receipt Number");
    lblnumber.setFont(new Font ("Arial", Font.PLAIN, 20));
    lblnumber.setForeground(Color.BLACK);
    add(lblnumber).setBounds(50,90,300,30);

    JLabel lblname =new JLabel("Store Name");
    lblname.setFont(new Font ("Arial", Font.PLAIN, 20));
    lblname.setForeground(Color.BLACK);
    add(lblname).setBounds(50,180,300,30);

    JLabel lblcost = new JLabel("Total Cost");
    lblcost.setFont(new Font ("Arial", Font.PLAIN, 20));
    lblcost.setForeground(Color.BLACK);
    add(lblcost).setBounds(50,270,300,30);


     number = new JTextField();
     number.setForeground(Color.BLACK);
     add(number).setBounds(50,120,330,50);

     name = new JTextField();
     name.setForeground(Color.BLACK);
     add(name).setBounds(50,210,330,50);

     cost = new JTextField();
     cost.setForeground(Color.BLACK);
     add(cost).setBounds(50,300,330,50);

     JButton record = new JButton("Record");
     record.setBackground(new Color(0,255,51));
     add(record).setBounds(50,370,150,50);
     
     JButton view = new JButton("View");
     view.setBackground(new Color(0x6F80FF));
     add(view).setBounds(230,370,150,50);

     JButton delete = new JButton("Delete");
     delete.setBackground(new Color(0x800000));
     add(delete).setBounds(140,440,150,50);

     record.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e){
         Record ();
         clearFields ();

      }
     });

    view.addActionListener(new ActionListener(){
     @Override
     public void actionPerformed(ActionEvent e){
         View();

      }
     });

   delete.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed (ActionEvent e){
         Delete();
      }
    });    
    setSize(500, 600);
    setResizable(false);
    setLocationRelativeTo(null);
    setLayout(null);
    setVisible(true);
}

private void Record() {
    try (FileWriter fw = new FileWriter(FILE_NAME, true);
         BufferedWriter bw = new BufferedWriter(fw)) {

        String num = number.getText();
        String nam = name.getText();
        String cos = cost.getText();

        bw.write(num + DELIMITER + nam + DELIMITER + cos);
        bw.newLine();
        JOptionPane.showMessageDialog(this, "Receipt Recorded Successfully! ◝(ᵔᗜᵔ)◜", "JOptionPane | Show Message", JOptionPane.INFORMATION_MESSAGE);

    } catch (IOException x) {
        JOptionPane.showMessageDialog(this, "Error Recording! ˙◠˙", "JOptionPane | Show Message", JOptionPane.ERROR_MESSAGE);
        
    }
}

private void View() {
    String message = "";

    try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] details = line.split(DELIMITER);
            if (details.length == 3) {
                message += "Receipt Number: " + details[0] + "\n" +
                           "Store Name: " + details[1] + "\n" +
                           "Total Cost: " + details[2] + "\n\n";
            }
        } 
    } catch (IOException x) {
        message += "No records found!\n";
    }

   
    JOptionPane.showMessageDialog(this, message, "JOptionPane | Show Message", JOptionPane.INFORMATION_MESSAGE);
    
}
private void Delete(){

 int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete all records?", "JOptionPane | Confirmation Dialog", JOptionPane.YES_NO_OPTION);
if (choice == JOptionPane.YES_OPTION){
  
   try {
      File file = new File(FILE_NAME);
      if (file.exists()) {
          if (file.delete()) {
              JOptionPane.showMessageDialog(this, "All records deleted successfully!", "JOptionPane | Show Message", JOptionPane.INFORMATION_MESSAGE);
          } else {
              JOptionPane.showMessageDialog(this, "Failed to delete records!", "JOptionPane | Show Message", JOptionPane.ERROR_MESSAGE);
          }
      } else {
          JOptionPane.showMessageDialog(this, "No records found to delete!", "JOptionPane | Show Message", JOptionPane.INFORMATION_MESSAGE);
      }
  } catch (Exception e) {
      JOptionPane.showMessageDialog(this, "Cannot delete file!", "JOptionPane | Show Message", JOptionPane.ERROR_MESSAGE);
      e.printStackTrace();
  }
} else {
  JOptionPane.showMessageDialog(this, "Cancelled!", "JOptionPane | Show Message", JOptionPane.INFORMATION_MESSAGE);
}
}

public void clearFields() {
   number.setText("");
   name.setText("");
   cost.setText("");
}
public static void main(String[] args) {
    new RRK();
}
}