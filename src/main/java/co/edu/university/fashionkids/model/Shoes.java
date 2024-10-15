package co.edu.university.fashionkids.model;

public class Shoes extends Product {
    public enum Size {
        TODDLERS,
        BIG_KIDS
    }

    public enum Color {
        RED, GREEN, BLUE, YELLOW, ORANGE, PINK
    }

    private Size size;
    private Color color;

    public Shoes(int id, String name, String description, double price, int quantity, Size size, Color color) {
        super(id, name, description, price, quantity, ProductType.SHOES);
        this.size = size;
        this.color = color;
    }
    public Size getSize() {
        return size;
    }
    public void setSize(Size size) {
        this.size = size;
    }
    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
}

