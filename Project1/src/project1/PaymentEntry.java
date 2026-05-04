package project1;

public class PaymentEntry {

private final int paymentNumber;
private final double monthlyPayment;
private final double principalPaid;
private final double interestPaid;
private final double remainingBalance;

public PaymentEntry(int paymentNumber, double monthlyPayment, double principalPaid, double interestPaid,
	double remainingBalance) {
	  this.paymentNumber = paymentNumber;
	  this.monthlyPayment = monthlyPayment;
	  this.principalPaid = principalPaid;
	  this.interestPaid = interestPaid;
	  this.remainingBalance = remainingBalance;
			}
		public int getPaymentNumber() {
			return paymentNumber;
			}
		public double getMonthlyPayment() {
			return monthlyPayment;
			}
		public double getPrincipalPaid() {
			return principalPaid;
			}
		public double getInterestPaid() {
			return interestPaid;
			}
		public double getRemainingBalance() {
			return remainingBalance;
			}
		
	}


