import javax.swing.JOptionPane;
public class CollegeFeeCalculator {
    
    public static void main(String[] args) {
        int enrollment = 0;
        String residenceHall = null;
        boolean loopMe = false;
        String[] residencyOptions = new String[]{"In-state", "Out-of-state", "International"};
        //JOptionPane.showOptionDialog (null, "Welcome to CollegeFeeCalculator!","CollegeFeeCalculator", YES_NO_OPTION, PLAIN_MESSAGE, null, JOptionPane.INFORMATION_MESSAGE, null);
        String[] optionTwoButtons = new String[]{"Part-time","Full-time"};
        String[] optionRestartButtons = new String[]{"Yes","No"};
        String[] residenceHallOptions = new String[]{"Earhart", "Hillenbrand", "Owen", "Windsor"};
        String[] housingOptions = new String[] {"on-campus", "off-campus"};
        int credit;
        int totalFee = 0;
        int tuition = 0; 
        int expense = 0; 
        int repeat = 0;
        
        JOptionPane.showMessageDialog (null, "Welcome to CollegeFeeCalculator!","CollegeFeeCalculator",JOptionPane.INFORMATION_MESSAGE);
        String name = JOptionPane.showInputDialog(null, "Please enter your name,then press OK", "Name", 3);
        do { 
            do {
                enrollment = JOptionPane.showOptionDialog (null, "Please select your enrollment", "Enrollment", 2, JOptionPane.QUESTION_MESSAGE, null, optionTwoButtons, null);
                String creditString = JOptionPane.showInputDialog(null, "Please enter the no. of credit hours,then press OK", "Credit Hours", JOptionPane.QUESTION_MESSAGE);
                credit = Integer.parseInt(creditString);
                loopMe = false;
                if (((enrollment == 0) && (credit >= 8)) || ((enrollment == 1) && (credit < 8))) {
                    loopMe = true;
                    JOptionPane.showMessageDialog (null, "Please enter valid credit hours for the current enrollment","Invalid no. of credits",JOptionPane.ERROR_MESSAGE);
                }
            } while (loopMe);
            String residency = (String) JOptionPane.showInputDialog(null, "Please select the appropriate residency", "Residency", JOptionPane.QUESTION_MESSAGE, null, residencyOptions, null);
            String housing = (String) JOptionPane.showInputDialog(null, "Please select your housing", "Housing", JOptionPane.QUESTION_MESSAGE, null, housingOptions, null);
            if (housing != "off-campus") {
                residenceHall = (String) JOptionPane.showInputDialog(null, "Please select your housing", "Residence-Hall", JOptionPane.QUESTION_MESSAGE, null, residenceHallOptions, null);
            }
            if (enrollment == 0) {
                switch (residency) {
                    case "In-state": tuition = (350 * credit); 
                    break;
                    case "Out-of-state": tuition = (950 * credit);
                    break;
                    case "International": tuition = (1020 * credit);
                    break;
                }
            }
            if (enrollment == 1) {
                switch (residency) {
                    case "In-state": tuition = 4996; 
                    break;
                    case "Out-of-state": tuition = (9401 + 4996);
                    break;
                    case "International": tuition = (1000 + 9401 + 4996);
                    break;
                }
            }
            if (housing == "on-campus") {
                switch (residenceHall) {
                    case "Earhart": expense = 4745; 
                    break;
                    case "Hillenbrand": expense = (5307);
                    break;
                    case "Owen": expense = (4130);
                    break;
                    case "Windsor": expense = (4150);
                    break;
                }
            }
            totalFee = tuition + expense;
            String infoString = "Name:" + name + "\n" + "Credit Hours:";
            infoString = infoString + credit + "\n" + "Enrollment:" + optionTwoButtons[enrollment] + "\n";
            infoString = infoString + "Residency:" + residency + "\n" + "Tuition fee:" + "$" + tuition + "\n" + "Housing Expense:";
            infoString = infoString + "$" + expense + "\n" + "Total Sem.Fee:" + "$" + totalFee;
            
            JOptionPane.showMessageDialog (null, infoString,"CollegeFeeCalculator",JOptionPane.INFORMATION_MESSAGE);
            repeat = JOptionPane.showOptionDialog (null, "Would you like to perform another fee calculation", "Are you done?", 2, JOptionPane.QUESTION_MESSAGE, null, optionRestartButtons, null);
        } while (repeat == 0);
    
    }
}