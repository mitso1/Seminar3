package model;

public class BikeDTO{
    String model;
    String brand;
    int id;


    public BikeDTO(int id, String model, String brand){
        this.id = id;
        this.model = model;
        this.brand = brand;
    }

    public int getId(){
        return id;
    }

    public String getModel(){
        return model;
    }

    public String getBrand(){
        return brand;
    }
}