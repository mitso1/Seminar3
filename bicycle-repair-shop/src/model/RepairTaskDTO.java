package model;

public class RepairTaskDTO {
    String taskDescription;
    int estimatedTime;
    int taskId;
    int bikeId;


    public RepairTaskDTO(String taskDescription, int estimatedTime, int taskId, int bikeId){
        this.taskDescription = taskDescription;
        this.estimatedTime = estimatedTime;
        this.taskId = taskId;
        this.bikeId = bikeId;
    }

    public String getTaskDescription(){
        return taskDescription;
    }

    public int getEstimatedTime(){
        return estimatedTime;
    }

    public int getTaskId(){
        return taskId;
    }

    public int getBikeId(){
        return bikeId;
    }
}
