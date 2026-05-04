package project1;
import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class LoanCalculator {
	private final Loan loan;

	public LoanCalculator(Loan loan) {
		this.loan = loan;
	}

	public double calculateMonthlyPayment() {
		double principal = loan.getPrincipal();
		double monthlyRate = loan.getMonthlyInterestRate();
		int numberOfPayments = loan.getNumberOfPayments();

		if (monthlyRate == 0) {
			return principal / numberOfPayments;
		}

		double rateFactor = Math.pow(1 + monthlyRate, numberOfPayments);
		return principal * (monthlyRate * rateFactor) / (rateFactor - 1);
	}

	public double calculateTotalPayment() {
		return calculateMonthlyPayment() * loan.getNumberOfPayments();
	}

	public double calculateTotalInterest() {
		return calculateTotalPayment() - loan.getPrincipal();
	}

	public List<PaymentEntry> generateAmortizationSchedule() {
		List<PaymentEntry> schedule = new ArrayList<PaymentEntry>();
		double balance = loan.getPrincipal();
		double monthlyRate = loan.getMonthlyInterestRate();
		double monthlyPayment = calculateMonthlyPayment();

		for (int paymentNumber = 1; paymentNumber <= loan.getNumberOfPayments(); paymentNumber++) {
			double interestPaid = balance * monthlyRate;
			double principalPaid = monthlyPayment - interestPaid; 

			if (paymentNumber == loan.getNumberOfPayments()) {
				principalPaid = balance;
				monthlyPayment = principalPaid + interestPaid;
			}

			balance -= principalPaid;
			if (balance < 0.01) {
				balance = 0;
			}

			schedule.add(new PaymentEntry(paymentNumber, monthlyPayment, principalPaid, interestPaid, balance));
		}

		return schedule;
	}
}