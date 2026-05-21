package budgetease;

<<<<<<< HEAD
import java.awt.*;
import java.awt.event.ActionEvent;
=======
>>>>>>> 3c933d9d1f28a1ba9c935108126be1f91dd2cabf
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
<<<<<<< HEAD
=======
import java.awt.*;
import java.awt.event.ActionEvent;
>>>>>>> 3c933d9d1f28a1ba9c935108126be1f91dd2cabf

public class BudgetEaseGUI {
    private final BudgetManager manager;
    private JFrame frame;
    private JLabel balanceLabel;
<<<<<<< HEAD
    private JLabel tipLabel;
=======
>>>>>>> 3c933d9d1f28a1ba9c935108126be1f91dd2cabf
    private DefaultTableModel tableModel;
    private JTable transactionsTable;

    public BudgetEaseGUI() {
        StrictValidator validator = new StrictValidator();
        DetailedReportGenerator reportGen = new DetailedReportGenerator();
        this.manager = new BudgetManager(validator, reportGen);
        createUI();
    }

    private void createUI() {
        frame = new JFrame("BudgetEase");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 650);
        frame.setLayout(new BorderLayout(8, 8));

        frame.setJMenuBar(createMenuBar());
        frame.add(createToolBar(), BorderLayout.NORTH);

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, createFormPanel(), createTablePanel());
        split.setDividerLocation(320);
        frame.add(split, BorderLayout.CENTER);

<<<<<<< HEAD
        JPanel status = new JPanel(new BorderLayout(8, 8));
        balanceLabel = new JLabel("Balance: $0.00");
        tipLabel = new JLabel();
        tipLabel.setFont(tipLabel.getFont().deriveFont(Font.PLAIN, 12f));
        tipLabel.setBorder(BorderFactory.createEmptyBorder(4, 10, 4, 10));
        status.add(balanceLabel, BorderLayout.WEST);
        status.add(tipLabel, BorderLayout.CENTER);
=======
        JPanel status = new JPanel(new FlowLayout(FlowLayout.LEFT));
        balanceLabel = new JLabel("Balance: $0.00");
        status.add(balanceLabel);
>>>>>>> 3c933d9d1f28a1ba9c935108126be1f91dd2cabf
        frame.add(status, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
<<<<<<< HEAD
        showRandomTip();
=======
>>>>>>> 3c933d9d1f28a1ba9c935108126be1f91dd2cabf
    }

    private JMenuBar createMenuBar() {
        JMenuBar mb = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem exit = new JMenuItem(new AbstractAction("Exit") {
            @Override public void actionPerformed(ActionEvent e) { System.exit(0); }
        });
        file.add(exit);

        JMenu help = new JMenu("Help");
        JMenuItem about = new JMenuItem(new AbstractAction("About") {
            @Override public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "BudgetEase — simple budget manager\nBuilt with Swing", "About", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        help.add(about);

        mb.add(file);
        mb.add(help);
        return mb;
    }

    private JToolBar createToolBar() {
        JToolBar tb = new JToolBar();
        JButton addExpenseBtn = new JButton("Add Expense");
        addExpenseBtn.addActionListener(e -> addExpenseDialog());
        JButton addIncomeBtn = new JButton("Add Income");
        addIncomeBtn.addActionListener(e -> addIncomeDialog());
        JButton reportBtn = new JButton("Report");
        reportBtn.addActionListener(e -> showReportDialog());
        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.addActionListener(e -> refreshTable());
<<<<<<< HEAD
        JButton tipBtn = new JButton("Tip");
        tipBtn.addActionListener(e -> showRandomTipDialog());
=======
>>>>>>> 3c933d9d1f28a1ba9c935108126be1f91dd2cabf

        tb.add(addExpenseBtn);
        tb.add(addIncomeBtn);
        tb.add(reportBtn);
        tb.addSeparator();
        tb.add(refreshBtn);
<<<<<<< HEAD
        tb.add(tipBtn);
=======
>>>>>>> 3c933d9d1f28a1ba9c935108126be1f91dd2cabf
        return tb;
    }

    private JPanel createFormPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(6,6));
        JPanel header = new JPanel(new BorderLayout());
        header.add(new JLabel("Quick Add"), BorderLayout.WEST);
        panel.add(header, BorderLayout.NORTH);

        JPanel form = new JPanel();
        form.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6,6,6,6);
        c.fill = GridBagConstraints.HORIZONTAL;

        JTextField descField = new JTextField();
        JTextField amtField = new JTextField();
        JComboBox<Category> catBox = new JComboBox<>(Category.values());

        c.gridx = 0; c.gridy = 0; form.add(new JLabel("Description"), c);
        c.gridx = 1; c.gridy = 0; form.add(descField, c);
        c.gridx = 0; c.gridy = 1; form.add(new JLabel("Amount ($)"), c);
        c.gridx = 1; c.gridy = 1; form.add(amtField, c);
        c.gridx = 0; c.gridy = 2; form.add(new JLabel("Category"), c);
        c.gridx = 1; c.gridy = 2; form.add(catBox, c);

        JButton addExpense = new JButton("Add Expense");
        addExpense.addActionListener(e -> {
            try {
                double amt = Double.parseDouble(amtField.getText().trim());
                Category cat = (Category) catBox.getSelectedItem();
                manager.addExpense(descField.getText().trim(), amt, cat);
                refreshTable();
                descField.setText(""); amtField.setText("");
                updateBalance();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton addIncome = new JButton("Add Income");
        addIncome.addActionListener(e -> {
            try {
                double amt = Double.parseDouble(amtField.getText().trim());
                manager.addIncome(descField.getText().trim(), amt, Category.INCOME);
                refreshTable();
                descField.setText(""); amtField.setText("");
                updateBalance();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        c.gridx = 0; c.gridy = 3; form.add(addExpense, c);
        c.gridx = 1; c.gridy = 3; form.add(addIncome, c);

        panel.add(form, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        tableModel = new DefaultTableModel(new Object[]{"ID","Type","Description","Amount","Date","Category"}, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        transactionsTable = new JTable(tableModel);
        transactionsTable.setFillsViewportHeight(true);
        transactionsTable.setAutoCreateRowSorter(true);
        transactionsTable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        transactionsTable.setRowHeight(30);
        transactionsTable.setIntercellSpacing(new Dimension(8, 4));
        transactionsTable.setShowGrid(true);
        transactionsTable.setGridColor(new Color(220, 220, 220));
        transactionsTable.setDefaultRenderer(Object.class, createStripedRenderer());
        transactionsTable.getTableHeader().setFont(transactionsTable.getTableHeader().getFont().deriveFont(Font.BOLD));
        transactionsTable.getTableHeader().setReorderingAllowed(false);

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        rightRenderer.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 8));
        transactionsTable.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);

        transactionsTable.getColumnModel().getColumn(0).setPreferredWidth(80);
        transactionsTable.getColumnModel().getColumn(1).setPreferredWidth(90);
        transactionsTable.getColumnModel().getColumn(2).setPreferredWidth(260);
        transactionsTable.getColumnModel().getColumn(3).setPreferredWidth(120);
        transactionsTable.getColumnModel().getColumn(4).setPreferredWidth(110);
        transactionsTable.getColumnModel().getColumn(5).setPreferredWidth(110);

        JScrollPane scroll = new JScrollPane(transactionsTable);
        scroll.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)), BorderFactory.createEmptyBorder(4, 4, 4, 4)));
        panel.add(scroll, BorderLayout.CENTER);
        return panel;
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        Transaction[] transactions = manager.getAllTransactions();
        for (Transaction t : transactions) {
            tableModel.addRow(new Object[]{t.getId(), t.getType(), t.getDescription(), String.format("$%.2f", t.getAmount()), t.getDate(), t.getCategory().getDisplayName()});
        }
        updateBalance();
<<<<<<< HEAD
        showRandomTip();
=======
>>>>>>> 3c933d9d1f28a1ba9c935108126be1f91dd2cabf
    }

    private void updateBalance() {
        balanceLabel.setText(String.format("Balance: $%.2f", manager.getBalance()));
    }

<<<<<<< HEAD
    private void showRandomTip() {
        showTip(manager.getRandomBudgetTip());
    }

    private void showRandomTipDialog() {
        String tip = manager.getRandomBudgetTip();
        JOptionPane.showMessageDialog(frame, tip, "Budget Tip", JOptionPane.INFORMATION_MESSAGE);
        showTip(tip);
    }

    private void showTip(String tip) {
        tipLabel.setText(String.format("<html><b>Tip:</b> %s</html>", tip));
    }

=======
>>>>>>> 3c933d9d1f28a1ba9c935108126be1f91dd2cabf
    private TableCellRenderer createStripedRenderer() {
        return new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground((row % 2 == 0) ? Color.WHITE : new Color(245, 245, 245));
                }
                if (c instanceof JComponent) {
                    ((JComponent) c).setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8));
                }
                return c;
            }
        };
    }

    private void addExpenseDialog() {
        JTextField desc = new JTextField();
        JTextField amt = new JTextField();
        JComboBox<Category> combo = new JComboBox<>(new Category[]{Category.FOOD, Category.TRANSPORT, Category.ENTERTAINMENT, Category.UTILITIES, Category.SHOPPING, Category.OTHER});
        Object[] fields = {"Description:", desc, "Amount ($):", amt, "Category:", combo};
        int res = JOptionPane.showConfirmDialog(frame, fields, "Add Expense", JOptionPane.OK_CANCEL_OPTION);
        if (res == JOptionPane.OK_OPTION) {
            try {
                double amount = Double.parseDouble(amt.getText().trim());
                Category cat = (Category) combo.getSelectedItem();
                manager.addExpense(desc.getText().trim(), amount, cat);
                refreshTable();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void addIncomeDialog() {
        JTextField desc = new JTextField();
        JTextField amt = new JTextField();
        Object[] fields = {"Description:", desc, "Amount ($):", amt};
        int res = JOptionPane.showConfirmDialog(frame, fields, "Add Income", JOptionPane.OK_CANCEL_OPTION);
        if (res == JOptionPane.OK_OPTION) {
            try {
                double amount = Double.parseDouble(amt.getText().trim());
                manager.addIncome(desc.getText().trim(), amount, Category.INCOME);
                refreshTable();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void showReportDialog() {
        try {
            DetailedReport report = manager.generateReport();

            JPanel panel = new JPanel(new BorderLayout(8,8));

            // Summary table
            String[] summaryColumns = {"Metric", "Value"};
            Object[][] summaryData = {
                {"Total Income", String.format("$%.2f", report.getTotalIncome())},
                {"Total Expense", String.format("$%.2f", report.getTotalExpense())},
                {"Balance", String.format("$%.2f", report.getBalance())},
                {"Transactions", report.getTransactionCountValue()},
                {"Average Transaction", String.format("$%.2f", report.getAverageTransaction())},
                {"Categories Used", report.countUsedCategories()}
            };
            JTable summaryTable = new JTable(new DefaultTableModel(summaryData, summaryColumns) {
                @Override public boolean isCellEditable(int row, int column) { return false; }
            });
            summaryTable.setRowSelectionAllowed(false);
            summaryTable.setFillsViewportHeight(true);
            summaryTable.setShowGrid(true);
            summaryTable.setGridColor(Color.LIGHT_GRAY);
            summaryTable.setIntercellSpacing(new Dimension(8, 1));
            summaryTable.setRowHeight(26);
            summaryTable.getTableHeader().setFont(summaryTable.getTableHeader().getFont().deriveFont(Font.BOLD));
            summaryTable.getTableHeader().setReorderingAllowed(false);
            summaryTable.getColumnModel().getColumn(0).setPreferredWidth(180);
            summaryTable.getColumnModel().getColumn(1).setPreferredWidth(120);
            summaryTable.setDefaultRenderer(Object.class, createStripedRenderer());
            JScrollPane summaryScroll = new JScrollPane(summaryTable);
            summaryScroll.setBorder(BorderFactory.createTitledBorder("Summary"));
            summaryScroll.setPreferredSize(new Dimension(700, 150));
            panel.add(summaryScroll, BorderLayout.NORTH);

            // Category table
            String[] categoryColumns = {"Category", "Amount", "Share"};
            double[] totals = report.getCategoryTotals();
            Category[] catsEnum = Category.values();
            double totalAll = 0;
            for (double v : totals) totalAll += v;

            Object[][] categoryData = new Object[report.countUsedCategories()][3];
            int rowIndex = 0;
            Integer[] indices = new Integer[catsEnum.length];
            for (int i = 0; i < catsEnum.length; i++) indices[i] = i;
            java.util.Arrays.sort(indices, (a,b) -> Double.compare(totals[b], totals[a]));
            for (int idx : indices) {
                if (totals[idx] <= 0) continue;
                categoryData[rowIndex++] = new Object[]{
                    catsEnum[idx].getDisplayName(),
                    String.format("$%.2f", totals[idx]),
                    totalAll > 0 ? String.format("%.1f%%", totals[idx] / totalAll * 100) : "0.0%"
                };
            }
            JTable categoryTable = new JTable(new DefaultTableModel(categoryData, categoryColumns) {
                @Override public boolean isCellEditable(int row, int column) { return false; }
            });
            categoryTable.setRowSelectionAllowed(false);
            categoryTable.setFillsViewportHeight(true);
            categoryTable.setShowGrid(true);
            categoryTable.setGridColor(Color.LIGHT_GRAY);
            categoryTable.setIntercellSpacing(new Dimension(10, 4));
            categoryTable.setRowHeight(28);
            categoryTable.getTableHeader().setFont(categoryTable.getTableHeader().getFont().deriveFont(Font.BOLD));
            categoryTable.getTableHeader().setReorderingAllowed(false);
            DefaultTableCellRenderer rightRender = new DefaultTableCellRenderer();
            rightRender.setHorizontalAlignment(SwingConstants.RIGHT);
            rightRender.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 8));
            categoryTable.getColumnModel().getColumn(1).setCellRenderer(rightRender);
            categoryTable.getColumnModel().getColumn(2).setCellRenderer(rightRender);
            categoryTable.getColumnModel().getColumn(0).setPreferredWidth(240);
            categoryTable.getColumnModel().getColumn(1).setPreferredWidth(120);
            categoryTable.getColumnModel().getColumn(2).setPreferredWidth(100);
            categoryTable.setDefaultRenderer(Object.class, createStripedRenderer());
            JScrollPane categoryScroll = new JScrollPane(categoryTable);
            categoryScroll.setBorder(BorderFactory.createTitledBorder("Category Breakdown"));
            categoryScroll.setPreferredSize(new Dimension(700, 250));
            panel.add(categoryScroll, BorderLayout.CENTER);

            JOptionPane.showMessageDialog(frame, panel, "Detailed Report", JOptionPane.PLAIN_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error generating report: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BudgetEaseGUI::new);
    }
}
