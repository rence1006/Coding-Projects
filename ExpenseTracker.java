import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ExpenseTracker extends JFrame{

private JTextField number, name, cost;
Double TaxRate = 0.12;
private static final String FILE_NAME = "data.txt";
private static final String DELIMITER = ",";


ExpenseTracker () {

getContentPane().setBackground(Color.PINK);
setTitle("Expense Tracker");
setLayout(null);

number = new JTextField();
number.setForeground(Color.BLACK);
add(number).setBounds(120,50,220,40);

name = new JTextField();
name.setForeground(Color.BLACK);
add(name).setBounds(120,130,220,40);

cost = new JTextField();
cost.setForeground(Color.BLACK);
add(cost).setBounds(120,210,220,40);


JLabel number = new JLabel ("Enter Receipt Number");
number.setForeground(Color.BLACK);
add(number).setBounds(165,5,200,70);

JLabel name = new JLabel ("Enter Receipt Name ");
name.setForeground(Color.BLACK);
add(name).setBounds(165,85,200,70);

JLabel cost = new JLabel ("Enter Total Cost");
cost.setForeground(Color.BLACK);
add(cost).setBounds(165,165,200,70);


JButton add = new JButton ("ADD");
add.setForeground(Color.BLACK);
add(add).setBounds(10,270,100,40);

JButton view = new JButton ("VIEW");
view.setForeground(Color.BLACK);
add(view).setBounds(120,270,100,40);

JButton calculate = new JButton ("CALCULATE");
calculate.setForeground(Color.BLACK);
add(calculate).setBounds(230,270,110,40);

JButton delete = new JButton ("DELETE");
delete.setForeground(Color.BLACK);
add(delete).setBounds(350,270,100,40);


    add.addActionListener(new ActionListener(){
    @Override
    public void actionPerformed(ActionEvent e){
     add();
     clearFields();
    }

    });


    view.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e){
      view();
      }

    });

    calculate.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e){
           calculate();
          
      }

    });

    
    delete.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e){
      delete();
      }

    });

setVisible(true);
setResizable(false);
setLocationRelativeTo(null);
setSize(475,370);
setDefaultCloseOperation(EXIT_ON_CLOSE);
}

private void add() {
  try (FileWriter fw = new FileWriter(FILE_NAME, true);
       BufferedWriter bw = new BufferedWriter(fw)) {

      String num = number.getText();
      String nam = name.getText();
      String cos = cost.getText();

      if (num.isEmpty() || nam.isEmpty() || cos.isEmpty()) {
          JOptionPane.showMessageDialog(this, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
      }else{
          JOptionPane.showMessageDialog(this, "Receipt Recorded Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);   
          return; 
      }

      double costValue = Double.parseDouble(cos);
      double tax = costValue * TaxRate;
      double finalAmount = costValue + tax;

bw.write(num + DELIMITER + nam + DELIMITER + cos + DELIMITER + tax + DELIMITER + finalAmount);
bw.newLine();

    
    
  } catch (IOException x) {
      JOptionPane.showMessageDialog(this, "Error Recording!", "Error", JOptionPane.ERROR_MESSAGE);
      x.printStackTrace(); 
  }
}

private void delete(){

  int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete all records?", "JOptionPane | Confirmation Dialog", 
  JOptionPane.YES_NO_OPTION);

     if (choice == JOptionPane.YES_OPTION){

   try {

     File file = new File(FILE_NAME);
   if (file.exists()) {
   if (file.delete()) {
   JOptionPane.showMessageDialog(this, "All records deleted successfully!", "JOptionPane | Show Message", JOptionPane.INFORMATION_MESSAGE);
   } else {

   JOptionPane.showMessageDialog(this, "Failed to delete records!", "JOptionPane | Show Message", JOptionPane.ERROR_MESSAGE);
   }} else {

   JOptionPane.showMessageDialog(this, "No records found to delete!", "JOptionPane | Show Message", JOptionPane.INFORMATION_MESSAGE);
   }} catch (Exception e) {

   JOptionPane.showMessageDialog(this, "Cannot delete file!", "JOptionPane | Show Message", JOptionPane.ERROR_MESSAGE);
   e.printStackTrace();

   }} else {

   JOptionPane.showMessageDialog(this, "Cancelled!", "JOptionPane | Show Message", JOptionPane.INFORMATION_MESSAGE);
   }
   }
private void view() {
 
 String message = "";
 try (BufferedReader br = new BufferedReader (new FileReader(FILE_NAME))){
    String line;
    while ((line =  br.readLine()) !=null) {
      String []details = line.split(DELIMITER);
      if(details.length ==5){
        message += "Receipt Number: " + details[0] + "\nStore Name: " + details[1] + "\nTotal Cost: " + details[2] + "\nTax: " +
        details[3] + "\nFinal Amount: " + details[4] + "\n\n";
      }
    }

 } catch (IOException x) {
  message += "Records Empty!\n";
 }

JOptionPane.showMessageDialog(this,message );
}

private void calculate() {
  double totalCost = 0;
  try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
  String line;
  while ((line = br.readLine()) != null) {
  String[] details = line.split(DELIMITER);
  if (details.length == 5) {
  try {
  double finalAmount = Double.parseDouble(details[4].trim());
  totalCost += finalAmount;
  } catch (NumberFormatException e) {
  JOptionPane.showMessageDialog(this, "Invalid data found in file: " + line, "Error", JOptionPane.ERROR_MESSAGE);
  }
  }
  }
  JOptionPane.showMessageDialog(this, "Total Cost: " + totalCost, "Calculation Result", JOptionPane.INFORMATION_MESSAGE);
  } catch (IOException e) {
  JOptionPane.showMessageDialog(this, "Error reading file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
  }
  }
  
 
   public void clearFields() {
   number.setText("");
   name.setText("");
   cost.setText("");
   }

public static void main (String[]args) {
new ExpenseTracker(); {
  }
 }
}