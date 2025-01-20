package pc_database.components;
public class Gettersetter {
    private int id;
    private String name;
    private String type;
    private String brand;
    private double price;
    public Gettersetter(int id, String name, String type, String brand, double price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.brand = brand;
        this.price = price;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Type: " + type + " | Brand: " + brand + " | Price: ₹ " + price;
    }
}