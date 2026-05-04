package project1;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

	public class AppLoan {
		private static final NumberFormat CURRENCY = NumberFormat.getCurrencyInstance(Locale.US);
		public static void main(String[] args) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Loan Planner");
			System.out.println("------------");

			double principal = readPositiveDouble(scanner, "Loan amount: $");
			double annualInterestRate = readNonNegativeDouble(scanner, "Annual interest rate (%): ");
			int termInYears = readPositiveInt(scanner, "Loan term in years: ");

			Loan loan = new Loan(principal, annualInterestRate, termInYears);
			LoanCalculator calculator = new LoanCalculator(loan);
			List<PaymentEntry> schedule = calculator.generateAmortizationSchedule();

			printLoanSummary(loan, calculator);
			printSchedulePreview(schedule);
			offerCsvExport(scanner, schedule);
			scanner.close();
		}
	private static void printLoanSummary(Loan loan, LoanCalculator calculator) {
		System.out.println();
		System.out.println("Loan Summary");
		System.out.println("------------");
		System.out.println("Loan amount:       " + CURRENCY.format(loan.getPrincipal()));
		System.out.println("Interest rate:     " + loan.getAnnualInterestRate() + "%");
		System.out.println("Term:              " + loan.getTermInYears() + " years");
		System.out.println("Monthly payment:   " + CURRENCY.format(calculator.calculateMonthlyPayment()));
		System.out.println("Total paid:        " + CURRENCY.format(calculator.calculateTotalPayment()));
		System.out.println("Total interest:    " + CURRENCY.format(calculator.calculateTotalInterest()));
	}

	private static void printSchedulePreview(List<PaymentEntry> schedule) {
		System.out.println();
		System.out.println("Amortization Schedule Preview");
		System.out.println("-----------------------------");
			System.out.printf("%-8s %-15s %-15s %-15s %-15s%n", "Month", "Payment", "Principal", "Interest",
					"Balance");

		int previewRows = Math.min(12, schedule.size());
		for (int i = 0; i < previewRows; i++) {
			PaymentEntry entry = schedule.get(i);
				System.out.printf("%-8d %-15s %-15s %-15s %-15s%n", entry.getPaymentNumber(),
						CURRENCY.format(entry.getMonthlyPayment()), CURRENCY.format(entry.getPrincipalPaid()),
						CURRENCY.format(entry.getInterestPaid()), CURRENCY.format(entry.getRemainingBalance()));
			}

	if (schedule.size() > previewRows) {
				System.out.println("... showing first 12 months only");
			}
		}

		private static void offerCsvExport(Scanner scanner, List<PaymentEntry> schedule) {
			System.out.print("\nSave full schedule to CSV? (y/n): ");
			String answer = scanner.next();

			if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes")) {
				try {
					saveScheduleToCsv(schedule, "amortization_schedule.csv");
					System.out.println("Saved to amortization_schedule.csv");
				} catch (IOException exception) {
					System.out.println("Sorry, the file could not be saved.");
					System.out.println("Reason: " + exception.getMessage());
				}
			}
		}

		private static void saveScheduleToCsv(List<PaymentEntry> schedule, String fileName) throws IOException {
			try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
				writer.println("Month,Payment,Principal,Interest,Balance");
				for (PaymentEntry entry : schedule) {
					writer.printf(Locale.US, "%d,%.2f,%.2f,%.2f,%.2f%n", entry.getPaymentNumber(),
							entry.getMonthlyPayment(), entry.getPrincipalPaid(), entry.getInterestPaid(),
							entry.getRemainingBalance());
				}
			}
		}
		private static double readPositiveDouble(Scanner scanner, String prompt) {
			double value = readDouble(scanner, prompt);
			while (value <= 0) {
				System.out.println("Please enter a number greater than 0.");
				value = readDouble(scanner, prompt);
			}

			return value;
		}

		private static double readNonNegativeDouble(Scanner scanner, String prompt) {
			double value = readDouble(scanner, prompt);

			while (value < 0) {
				System.out.println("Please enter a number that is 0 or greater.");
				value = readDouble(scanner, prompt);
			}

			return value;
		}

		private static double readDouble(Scanner scanner, String prompt) {
			System.out.print(prompt);

			while (!scanner.hasNextDouble()) {
				System.out.println("Please enter a valid number.");
				scanner.next();
				System.out.print(prompt);
			}

			return scanner.nextDouble();
		}

		private static int readPositiveInt(Scanner scanner, String prompt) {
			System.out.print(prompt);

			while (!scanner.hasNextInt()) {
				System.out.println("Please enter a whole number.");
				scanner.next();
				System.out.print(prompt);
			}

			int value = scanner.nextInt();
			while (value <= 0) {
				System.out.println("Please enter a number greater than 0.");
				System.out.print(prompt);

				while (!scanner.hasNextInt()) {
					System.out.println("Please enter a whole number.");
					scanner.next();
					System.out.print(prompt);
				}

				value = scanner.nextInt();
			}

			return value;
		}
	}


