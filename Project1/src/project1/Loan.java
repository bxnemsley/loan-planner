package project1;

public class Loan {
		private final double principal;
		private final double annualInterestRate;
		private final int termInYears;

		public Loan(double principal, double annualInterestRate, int termInYears) {
			if (principal <= 0) {
				throw new IllegalArgumentException("Loan amount must be higher than 0.");
			}
			if (annualInterestRate < 0) {
				throw new IllegalArgumentException("Interest rate can never be negative.");
			}
			if (termInYears <= 0) {
				throw new IllegalArgumentException("Loan term has to be higher than 0.");
			}

			this.principal = principal;
			this.annualInterestRate = annualInterestRate;
			this.termInYears = termInYears;
		}

	public double getPrincipal() {
		return principal;
		}

	public double getAnnualInterestRate() {
		return annualInterestRate;
		}

	public int getTermInYears() {
		return termInYears;
		}

	public int getNumberOfPayments() {
		return termInYears * 12;
		}

	public double getMonthlyInterestRate() {
		return annualInterestRate / 100 / 12;
		}
	}

