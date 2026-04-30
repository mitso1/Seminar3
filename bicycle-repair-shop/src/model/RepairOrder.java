package model;
public class RepairOrder {
    int id;
    String diagnosticReport;
    BikeDTO bike;
    CustomerDTO customer;
    String problemDesc;
    RepairTaskDTO repairTasks;

    public RepairOrder(int id, String diagnosticReport, BikeDTO bike, CustomerDTO customer, String problemDesc, RepairTaskDTO repairTasks){
        this.id = id;
        this.diagnosticReport = diagnosticReport;
        this.bike = bike;
        this.customer = customer;
        this.problemDesc = problemDesc;
        this.repairTasks = repairTasks;
    }

    public String getDiagnosticReport(){
        return diagnosticReport;
    }

    public BikeDTO getBike(){
        return bike;
    }

    public CustomerDTO getCustomer(){
        return customer;
    }

    public String getProblemDesc(){
        return problemDesc;
    }

    public RepairTaskDTO getRepairTasks(){
        return repairTasks;
    }
}
