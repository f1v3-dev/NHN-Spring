package com.nhnacademy.edu.springframework.repository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WaterBill {

    @JsonProperty("순번")
    private Integer id;

    @JsonProperty("지자체명")
    private String city;

    @JsonProperty("업종")
    private String sector;

    @JsonProperty("단계")
    private Integer step;

    @JsonProperty("구간시작(세제곱미터)")
    private Integer beginSection;

    @JsonProperty("구간끝(세제곱미터)")
    private Integer endSection;

    @JsonProperty("구간금액(원)")
    private Integer unitPrice;

    @JsonProperty("단계별 기본요금(원)")
    private Integer basicPriceByStep;

    @JsonIgnore
    private Integer billTotal;

    public WaterBill() {
    }

    public WaterBill(Integer id, String city, String sector, Integer step, Integer beginSection, Integer endSection,
                     Integer unitPrice, Integer basicPriceByStep) {
        this.id = id;
        this.city = city;
        this.sector = sector;
        this.step = step;
        this.beginSection = beginSection;
        this.endSection = endSection;
        this.unitPrice = unitPrice;
        this.basicPriceByStep = basicPriceByStep;
    }


    public Integer getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getSector() {
        return sector;
    }

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public Integer getBillTotal() {
        return billTotal;
    }

    public Integer getBeginSection() {
        return beginSection;
    }

    public Integer getEndSection() {
        return endSection;
    }


    public Integer getStep() {
        return step;
    }

    public Integer getBasicPriceByStep() {
        return basicPriceByStep;
    }


    public void setBasicPriceByStep(Integer basicPriceByStep) {
        this.basicPriceByStep = basicPriceByStep;
    }

    public void setBillTotal(Integer billTotal) {
        this.billTotal = billTotal;
    }

    public boolean isInRange(Integer usage) {
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
