
import java.util.Scanner;

public class FinanceCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String calculation;

        do {
            System.out.println("Which calculation do you want to perform?");
            System.out.println("1. Compound Interest");
            System.out.println("2. Present Value");
            System.out.println("3. Net Present Value (NPV)");
            System.out.println("4. Internal Rate of Return (IRR)");
            System.out.println("5. Return on Investment (ROI)");
            System.out.println("6. Dividend Yield");
            System.out.println("7. Earnings Per Share (EPS)");
            System.out.println("8. Price-to-Earnings Ratio (P/E Ratio)");
            System.out.println("9. Debt-to-Equity Ratio");
            System.out.println("10. Capital Asset Pricing Model (CAPM)");
            System.out.println("11. Beta");
            System.out.println("12. Standard Deviation");
            System.out.println("13. Sharpe Ratio");
            System.out.println("14. Bond Pricing");
            System.out.println("15. Duration");
            System.out.println("16. Black-Scholes Option Pricing Model");
            System.out.println("17. Break-Even Analysis");
            System.out.println("18. Financial Ratios");
            System.out.println("Type 'exit' to quit.");

            System.out.print("Enter the number of the calculation: ");
            calculation = scanner.nextLine();

            switch (calculation) {
                case "1":
                    calculateCompoundInterest(scanner);
                    break;
                case "2":
                    calculatePresentValue(scanner);
                    break;
                case "3":
                    calculateNPV(scanner);
                    break;
                case "4":
                    calculateIRR(scanner);
                    break;
                case "5":
                    calculateROI(scanner);
                    break;
                case "6":
                    calculateDividendYield(scanner);
                    break;
                case "7":
                    calculateEPS(scanner);
                    break;
                case "8":
                    calculatePERatio(scanner);
                    break;
                case "9":
                    calculateDebtToEquityRatio(scanner);
                    break;
                case "10":
                    calculateCAPM(scanner);
                    break;
                case "11":
                    calculateBeta(scanner);
                    break;
                case "12":
                    calculateStandardDeviation(scanner);
                    break;
                case "13":
                    calculateSharpeRatio(scanner);
                    break;
                case "14":
                    calculateBondPricing(scanner);
                    break;
                case "15":
                    calculateDuration(scanner);
                    break;
                case "16":
                    calculateBlackScholes(scanner);
                    break;
                case "17":
                    calculateBreakEvenAnalysis(scanner);
                    break;
                case "18":
                    calculateFinancialRatios(scanner);
                    break;
                case "exit":
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
                    break;
            }
        } while (!calculation.equalsIgnoreCase("exit"));
    }

    private static void calculateCompoundInterest(Scanner scanner) {
        System.out.println("=== Compound Interest Calculation ===");
        System.out.print("Enter the principal amount (P): ");
        double principal = scanner.nextDouble();

        System.out.print("Enter the annual interest rate (r): ");
        double interestRate = scanner.nextDouble();

        System.out.print("Enter the number of times interest is compounded per year (n): ");
        int compoundingFrequency = scanner.nextInt();

        System.out.print("Enter the number of years (t): ");
        int years = scanner.nextInt();

        double futureValue = principal * Math.pow(1 + (interestRate / compoundingFrequency), compoundingFrequency * years);
        System.out.println("Future Value (FV) = " + futureValue);

        // Consume the remaining newline character after reading integers
        scanner.nextLine();
    }

    private static void calculatePresentValue(Scanner scanner) {
        System.out.println("=== Present Value Calculation ===");
        System.out.print("Enter the future value (FV): ");
        double futureValue = scanner.nextDouble();

        System.out.print("Enter the annual interest rate (r): ");
        double interestRate = scanner.nextDouble();

        System.out.print("Enter the number of years (t): ");
        int years = scanner.nextInt();

        double presentValue = futureValue / Math.pow(1 + interestRate, years);
        System.out.println("Present Value (PV) = " + presentValue);

        // Consume the remaining newline character after reading integers
        scanner.nextLine();
    }

    private static void calculateNPV(Scanner scanner) {
        System.out.println("=== Net Present Value (NPV) Calculation ===");
        System.out.print("Enter the initial investment amount: ");
        double initialInvestment = scanner.nextDouble();

        System.out.print("Enter the discount rate (as a decimal eg ten percent is 10. ): ");
        double discountRate = scanner.nextDouble();

        System.out.print("Enter the number of cash flow periods: ");
        int periods = scanner.nextInt();

        double npv = -initialInvestment;

        for (int i = 1; i <= periods; i++) {
            System.out.print("Enter cash flow for period " + i + ": ");
            double cashFlow = scanner.nextDouble();
            npv += cashFlow / Math.pow(1 + discountRate, i);
        }

        System.out.println("Net Present Value (NPV) = " + npv);

        // Consume the remaining newline character after reading integers
        scanner.nextLine();
    }


   private static void calculateIRR(Scanner scanner) {
        System.out.println("=== Internal Rate of Return (IRR) Calculation ===");
        System.out.print("Enter the initial investment amount: ");
        double initialInvestment = scanner.nextDouble();

        System.out.print("Enter the number of cash flow periods: ");
        int periods = scanner.nextInt();

        double[] cashFlows = new double[periods + 1];
        cashFlows[0] = -initialInvestment;

        for (int i = 1; i <= periods; i++) {
            System.out.print("Enter cash flow for period " + i + ": ");
            cashFlows[i] = scanner.nextDouble();
        }

        double irr = calculateIRR(cashFlows);
        System.out.println("Internal Rate of Return (IRR) = " + irr * 100 + "%");

        // Consume the remaining newline character after reading integers
        scanner.nextLine();
    }

    private static double calculateIRR(double[] cashFlows) {
        double irr = 0.1; // Initial guess for IRR
        double epsilon = 0.0001; // Desired precision

        while (true) {
            double npv = 0;
            double derivative = 0;

            for (int i = 0; i < cashFlows.length; i++) {
                npv += cashFlows[i] / Math.pow(1 + irr, i);
                derivative -= i * cashFlows[i] / Math.pow(1 + irr, i + 1);
            }

            double newIRR = irr - npv / derivative;

            if (Math.abs(newIRR - irr) < epsilon) {
                return newIRR;
            }

            irr = newIRR;
        }
    }

    private static void calculateROI(Scanner scanner) {
        System.out.println("=== Return on Investment (ROI) Calculation ===");
        System.out.print("Enter the initial investment amount: ");
        double initialInvestment = scanner.nextDouble();

        System.out.print("Enter the current value of the investment: ");
        double currentValue = scanner.nextDouble();

        double roi = ((currentValue - initialInvestment) / initialInvestment) * 100;
        System.out.println("Return on Investment (ROI) = " + roi + "%");

        // Consume the remaining newline character after reading doubles
        scanner.nextLine();
    }

    private static void calculateDividendYield(Scanner scanner) {
        System.out.println("=== Dividend Yield Calculation ===");
        System.out.print("Enter the annual dividend per share: ");
        double annualDividendPerShare = scanner.nextDouble();

        System.out.print("Enter the current stock price: ");
        double currentStockPrice = scanner.nextDouble();

        double dividendYield = (annualDividendPerShare / currentStockPrice) * 100;
        System.out.println("Dividend Yield = " + dividendYield + "%");

        // Consume the remaining newline character after reading doubles
        scanner.nextLine();
    }



 private static void calculateEPS(Scanner scanner) {
        System.out.println("=== Earnings Per Share (EPS) Calculation ===");
        System.out.print("Enter the net earnings (profit) of the company: ");
        double netEarnings = scanner.nextDouble();

        System.out.print("Enter the number of outstanding shares: ");
        double outstandingShares = scanner.nextDouble();

        double eps = netEarnings / outstandingShares;
        System.out.println("Earnings Per Share (EPS) = " + eps);

        // Consume the remaining newline character after reading doubles
        scanner.nextLine();
    }

    private static void calculatePERatio(Scanner scanner) {
        System.out.println("=== Price-to-Earnings (P/E) Ratio Calculation ===");
        System.out.print("Enter the current stock price: ");
        double currentStockPrice = scanner.nextDouble();

        System.out.print("Enter the earnings per share (EPS): ");
        double earningsPerShare = scanner.nextDouble();

        double peRatio = currentStockPrice / earningsPerShare;
        System.out.println("Price-to-Earnings (P/E) Ratio = " + peRatio);

        // Consume the remaining newline character after reading doubles
        scanner.nextLine();
    }

    private static void calculateDebtToEquityRatio(Scanner scanner) {
        System.out.println("=== Debt-to-Equity (D/E) Ratio Calculation ===");
        System.out.print("Enter the total debt: ");
        double totalDebt = scanner.nextDouble();

        System.out.print("Enter the total equity: ");
        double totalEquity = scanner.nextDouble();

        double debtToEquityRatio = totalDebt / totalEquity;
        System.out.println("Debt-to-Equity (D/E) Ratio = " + debtToEquityRatio);

        // Consume the remaining newline character after reading doubles
        scanner.nextLine();
    }

   private static void calculateCAPM(Scanner scanner) {
        System.out.println("=== Capital Asset Pricing Model (CAPM) Calculation ===");
        System.out.print("Enter the risk-free rate (as a decimal): ");
        double riskFreeRate = scanner.nextDouble();

        System.out.print("Enter the market rate of return (as a decimal): ");
        double marketRateOfReturn = scanner.nextDouble();

        System.out.print("Enter the beta coefficient of the asset: ");
        double beta = scanner.nextDouble();

        double capm = riskFreeRate + beta * (marketRateOfReturn - riskFreeRate);
        System.out.println("Capital Asset Pricing Model (CAPM) = " + capm);

        // Consume the remaining newline character after reading doubles
        scanner.nextLine();
    }

    private static void calculateBeta(Scanner scanner) {
        // Implement Beta calculation
        // ...
    }

    private static void calculateStandardDeviation(Scanner scanner) {
        System.out.println("=== Standard Deviation Calculation ===");
        System.out.print("Enter the number of data points: ");
        int numDataPoints = scanner.nextInt();

        double[] dataPoints = new double[numDataPoints];
        for (int i = 0; i < numDataPoints; i++) {
            System.out.print("Enter data point " + (i + 1) + ": ");
            dataPoints[i] = scanner.nextDouble();
        }

        double standardDeviation = calculateSampleStandardDeviation(dataPoints);
        System.out.println("Sample Standard Deviation = " + standardDeviation);

        // Consume the remaining newline character after reading doubles
        scanner.nextLine();
    }

    private static double calculateMean(double[] data) {
        double sum = 0;
        for (double value : data) {
            sum += value;
        }
        return sum / data.length;
    }

    private static double calculateSampleStandardDeviation(double[] data) {
        double mean = calculateMean(data);
        double sumOfSquaredDifferences = 0;

        for (double value : data) {
            double difference = value - mean;
            sumOfSquaredDifferences += Math.pow(difference, 2);
        }

        return Math.sqrt(sumOfSquaredDifferences / (data.length - 1));
    }

    private static void calculateSharpeRatio(Scanner scanner) {
        // Implement Sharpe Ratio calculation
        // ...
    }

    private static void calculateBondPricing(Scanner scanner) {
        // Implement Bond Pricing calculation
        // ...
    }

    private static void calculateDuration(Scanner scanner) {
        System.out.println("=== Duration Calculation ===");
        System.out.print("Enter the number of cash flow periods: ");
        int periods = scanner.nextInt();

        double duration = 0;
        double modifiedDuration = 0;

        for (int i = 1; i <= periods; i++) {
            System.out.print("Enter cash flow for period " + i + ": ");
            double cashFlow = scanner.nextDouble();
            System.out.print("Enter yield (as a decimal) for period " + i + ": ");
            double yield = scanner.nextDouble();
            
            duration += (i * cashFlow) / Math.pow(1 + yield, i);
            modifiedDuration += (i * cashFlow) / Math.pow(1 + yield, i + 1);
        }

        duration = duration / modifiedDuration;
        System.out.println("Duration = " + duration);

        // Consume the remaining newline character after reading integers
        scanner.nextLine();
    }

    private static void calculateBlackScholes(Scanner scanner) {
        // Implement Black-Scholes Option Pricing Model calculation
        // ...
    }

    private static void calculateBreakEvenAnalysis(Scanner scanner) {
        System.out.println("=== Break-Even Analysis Calculation ===");
        System.out.print("Enter the fixed costs: ");
        double fixedCosts = scanner.nextDouble();

        System.out.print("Enter the selling price per unit: ");
        double sellingPricePerUnit = scanner.nextDouble();

        System.out.print("Enter the variable costs per unit: ");
        double variableCostsPerUnit = scanner.nextDouble();

        double breakEvenUnits = calculateBreakEvenPoint(fixedCosts, sellingPricePerUnit, variableCostsPerUnit);
        System.out.println("Break-Even Point (Units) = " + breakEvenUnits);

        // Consume the remaining newline character after reading doubles
        scanner.nextLine();
    }

    private static double calculateBreakEvenPoint(double fixedCosts, double sellingPricePerUnit, double variableCostsPerUnit) {
        if (sellingPricePerUnit - variableCostsPerUnit == 0) {
            System.out.println("Cannot calculate the break-even point when selling price per unit and variable costs per unit are equal.");
            return Double.NaN;
        }

        return fixedCosts / (sellingPricePerUnit - variableCostsPerUnit);
    }

    private static void calculateFinancialRatios(Scanner scanner) {
        // Implement Financial Ratios calculation
        // ...
    }
}
