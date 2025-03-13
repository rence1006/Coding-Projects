
import java.util.Date;

public class CodeSpace {
    public static void main(String[] args) {
        Date time = new Date(); 
        
        while (true) {
            JOptionPane.showMessageDialog(null, "WELCOME TO DMORVIE!");

            String name = JOptionPane.showInputDialog("Enter your name:");
            if (name == null || name.isEmpty()) {
                JOptionPane.showMessageDialog(null, "The name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            String inputAge = JOptionPane.showInputDialog("Enter your Age:");
            if (inputAge == null || inputAge.isEmpty()) {
                JOptionPane.showMessageDialog(null, "The age cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            String gender = JOptionPane.showInputDialog("Enter your gender:");
            if (gender == null || gender.isEmpty()) {
                JOptionPane.showMessageDialog(null, "The gender cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                break;  
            }

            JOptionPane.showMessageDialog(null, "Choose a room:");
            String rooms = JOptionPane.showInputDialog(
                "\n1. Small room: ₱500 per night\n" +
                "2. Medium room: ₱700 per night\n" +
                "3. Large room ₱1000 per night"
            );

            int roomChoice = Integer.parseInt(rooms);
            int roomPrice = 0;

            if (roomChoice == 1) {
                roomPrice = 500;
            } else if (roomChoice == 2) {
                roomPrice = 700;
            } else if (roomChoice == 3) {
                roomPrice = 1000;
            }

            String daysInput = JOptionPane.showInputDialog("How many days do you wish to stay?");
            int days = Integer.parseInt(daysInput);

            int totalRoomCost = roomPrice * days;

            String foodChoice = JOptionPane.showInputDialog("Do you want to purchase foods and drinks? \n1. Yes\n2. No");
            int food = Integer.parseInt(foodChoice);

            int foodCost = 0;

            if (food == 1) {
                String foodOrder = JOptionPane.showInputDialog(
                    "Select food: \n1. Pancit Canton with Egg ₱45\n" +
                    "2. Hotdog with Egg ₱35\n" +
                    "3. Rice and Fried Chicken ₱55\n" +
                    "4. Coke ₱20\n" +
                    "5. Rice ₱10\n" +
                    "6. Pastil ₱15"
                );

                int foodChoiceNumber = Integer.parseInt(foodOrder);

                switch (foodChoiceNumber) {
                    case 1:
                        foodCost = 45;
                        break;

                    case 2:
                        foodCost = 35;
                        break;

                    case 3:
                        foodCost = 55;
                        break;

                    case 4:
                        foodCost = 20;
                        break;

                    case 5:
                        foodCost = 10;
                        break;

                    case 6:
                        foodCost = 15;
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Invalid choice. Please select a valid food option.", "Error", JOptionPane.ERROR_MESSAGE);
                          
                        
                        break;

                }
            }

            JOptionPane.showMessageDialog(null,
                "Dmorvie Receipt: \n" +
                "Name of Customer: " + name + "\n" +
                "Age of Customer: " + inputAge + "\n" +
                "Gender: " + gender + "\n" +
                "Room Price: ₱" + totalRoomCost + "\n" +
                "Food Price: ₱" + foodCost + "\n" +
                "Total Bill: ₱" + (totalRoomCost + foodCost) + "\n" +
                "Date and Time: " + time,
                "Receipt",
                JOptionPane.INFORMATION_MESSAGE);

                break;
            
        }
    }
}
