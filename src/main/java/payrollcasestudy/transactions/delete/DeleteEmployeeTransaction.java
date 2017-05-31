package payrollcasestudy.transactions.delete;


import payrollcasestudy.boundaries.Repository;
import payrollcasestudy.transactions.Transaction;

public class DeleteEmployeeTransaction implements Transaction{
    private int employeeId;

    public DeleteEmployeeTransaction(int employeeId) {
        this.employeeId = employeeId;
    }

    public void execute(Repository repository) {
    	repository.deleteEmployee(employeeId);
    }
}
