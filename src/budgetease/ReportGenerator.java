package budgetease;

interface ReportGenerator {
    DetailedReport generateReport(BudgetManager manager);
    String getReportType();
}