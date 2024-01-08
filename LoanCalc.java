/**
* Computes the periodical payment necessary to re-pay a given loan.
*/
public class LoanCalc {
	
	static double epsilon = 0.001;  // The computation tolerance (estimation error)
	static int iterationCounter;    // Monitors the efficiency of the calculation
	
    /** 
     * Gets the loan data and computes the periodical payment.
     * Expects to get three command-line arguments: sum of the loan (double),
     * interest rate (double, as a percentage), and number of payments (int).  
     */
	public static void main(String[] args) {		
		// Gets the loan data
		double loan = Double.parseDouble(args[0]);
		double rate = Double.parseDouble(args[1]);
		int n = Integer.parseInt(args[2]);
		System.out.println("Loan sum = " + loan + ", interest rate = " + rate + "%, periods = " + n);
		
		// Computes the periodical payment using brute force search
		System.out.print("Periodical payment, using brute force: ");
		System.out.printf("%.2f", bruteForceSolver(loan, rate, n, epsilon));
		System.out.println();
		System.out.println("number of iterations: " + iterationCounter);

		// Computes the periodical payment using bisection search
		System.out.print("Periodical payment, using bi-section search: ");
		System.out.printf("%.2f", bisectionSolver(loan, rate, n, epsilon));
		System.out.println();
		System.out.println("number of iterations: " + iterationCounter);
	}
	
	/**
	* Uses a sequential search method  ("brute force") to compute an approximation
	* of the periodical payment that will bring the ending balance of a loan close to 0.
	* Given: the sum of the loan, the periodical interest rate (as a percentage),
	* the number of periods (n), and epsilon, a tolerance level.
	*/
	// Side effect: modifies the class variable iterationCounter.
    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {  
    	double g = loan / n;
    	double f = endBalance(loan, rate, n, g);
    	while (f > 0) {
    		g = g + epsilon;
    		iterationCounter++;
    		f = endBalance(loan, rate, n, g);
    	}
    	return g;
    }
    
    /**
	* Uses bisection search to compute an approximation of the periodical payment 
	* that will bring the ending balance of a loan close to 0.
	* Given: the sum of theloan, the periodical interest rate (as a percentage),
	* the number of periods (n), and epsilon, a tolerance level.
	*/
	// Side effect: modifies the class variable iterationCounter.
    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {  
    	iterationCounter = 0;
    	double l = loan / n;
    	double h = loan + 1;
    	double g = (l + h) / 2;
// Sets L and H to initial values such that 𝑓(𝐿) > 0, 𝑓(𝐻) < 0,
// implying that the function evaluates to zero somewhere between L and H.
// So, let’s assume that L and H were set to such initial values. // Set g to (𝐿 + 𝐻)/2
		while (h - l > epsilon) {
			if (endBalance (loan, rate, n, g) * endBalance (loan, rate, n, l) > 0) {
				l = g;
			}
			else {
				h = g;
			}
			g = (l + h) / 2;
			iterationCounter++;
		}
    	return g;
    }
	
	/**
	* Computes the ending balance of a loan, given the sum of the loan, the periodical
	* interest rate (as a percentage), the number of periods (n), and the periodical payment.
	*/
	private static double endBalance(double loan, double rate, int n, double payment) {
		double endBalance = 0;
		double interest = (rate / 100) + 1;
		for (int i = 1; i <= n; i++) {
			loan = (loan - payment) * interest;
		}
		endBalance = loan;
    	return endBalance;
	}
}