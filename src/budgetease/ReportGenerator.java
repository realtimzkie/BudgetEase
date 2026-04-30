package budgetease;

interface ReportGenerator {
    Report generateReport(BudgetManager manager);
    String getReportType();
}