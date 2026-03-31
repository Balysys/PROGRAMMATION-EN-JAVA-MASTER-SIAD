import java.util.Scanner; // Import the tool to read user input

public class LoanAdvisor {
    public static void main(String[] args) {
        // 1. Initialize the Scanner
        Scanner input = new Scanner(System.in);

        System.out.println("--- SIAD Loan Decision Support System ---");

        // 2. Collect Data
        System.out.print("Enter Annual Income (CFA): ");
        double income = input.nextDouble();

        System.out.print("Enter Credit Score (300-850): ");
        int creditScore = input.nextInt();

        // 3. Decision Logic (The "Aide à la Décision" part)
        boolean isApproved = false;

        if (income >= 5000000 && creditScore >= 650) {
            isApproved = true;
        } else if (income >= 10000000 && creditScore >= 500) {
            isApproved = true; // Approved due to high income despite lower credit
        }

        // 4. Output the Result
        System.out.println("\n--- Audit Result ---");
        if (isApproved) {
            System.out.println("Status: APPROVED");
            System.out.println("Reason: Profile meets risk management criteria.");
        } else {
            System.out.println("Status: REJECTED");
            System.out.println("Reason: High risk detected based on income/credit ratio.");
        }

        input.close();
    }
}