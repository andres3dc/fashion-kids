package co.edu.university.fashionkids.model;

public class Clothes extends Product {
    public enum Size {
        NEWBORN, SIZE_0_3_MONTHS, SIZE_3_6_MONTHS, SIZE_6_9_MONTHS, SIZE_9_12_MONTHS,
        SIZE_12_18_MONTHS, SIZE_18_24_MONTHS, SIZE_2_YEARS, SIZE_3_YEARS, SIZE_4_YEARS,
        SIZE_5_YEARS, SIZE_6_YEARS, SIZE_8_YEARS, SIZE_10_YEARS, SIZE_12_YEARS, SIZE_14_YEARS
    }
    private Size size;

    public Clothes(int id, String name, String description, double price, int quantity, Size size) {
        super(id, name, description, price, quantity, ProductType.CLOTHE);
        this.size = size;
    }
    public Size getSize() {
        return size;
    }
    public void setSize(Size size) {
        this.size = size;
    }
}
