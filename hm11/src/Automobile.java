public class Automobile {
    private int id;
    private String manufacturer;
    private String brand;
    private double volume;
    private int year;
    private String colour;
    private String type;

    public Automobile(String manufacturer, String brand, double volume,
                      int year, String colour, String type) {
        this.manufacturer = manufacturer;
        this.brand = brand;
        this.volume = volume;
        this.year = year;
        this.colour = colour;
        this.type = type;
    }

    public Automobile(int id, String manufacturer, String brand, double volume,
                      int year, String colour, String type) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.brand = brand;
        this.volume = volume;
        this.year = year;
        this.colour = colour;
        this.type = type;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getmanufacturer() { return manufacturer; }
    public void setmanufacturer(String manufacturer) { this.manufacturer = manufacturer; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public double getVolume() { return volume; }
    public void setVolume(double volume) { this.volume = volume; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public String getColour() { return colour; }
    public void setColour(String colour) { this.colour = colour; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    @Override
    public String toString() {
        return String.format("ID: %d | %s %s | %.1f л | %d г | %s | %s",
                id, manufacturer, brand, volume, year, colour, type);
    }
}