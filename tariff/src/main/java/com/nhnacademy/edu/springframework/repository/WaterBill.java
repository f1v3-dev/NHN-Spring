package com.nhnacademy.edu.springframework.repository;

public class WaterBill {

    private final String city;

    private final String sector;
    private final int beginSection;

    private final int endSection;

    private final int unitPrice;
    private int billTotal;

    public WaterBill(String city, String sector, int beginSection, int endSection, int unitPrice) {
        this.city = city;
        this.sector = sector;
        this.beginSection = beginSection;
        this.endSection = endSection;
        this.unitPrice = unitPrice;
    }

    public String getCity() {
        return city;
    }

    public String getSector() {
        return sector;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public int getBillTotal() {
        return billTotal;
    }

    public int getBeginSection() {
        return beginSection;
    }

    public int getEndSection() {
        return endSection;
    }

    public void setBillTotal(int billTotal) {
        this.billTotal = billTotal;
    }


    public boolean isInRange(int usage) {
        return beginSection <= usage && usage <= endSection;
    }

    @Override
    public String toString() {
        return "WaterBill{" +
                "city='" + city + '\'' +
                ", sector='" + sector + '\'' +
                ", unitPrice=" + unitPrice +
                ", billTotal=" + billTotal + '}';
    }

}
