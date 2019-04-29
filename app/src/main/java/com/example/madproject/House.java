package com.example.madproject;

public class House {

    private String floor;
    private  String unit;
    private String address;
    private String ownerId;

    public House(String floor, String unit, String address, String ownerId) {
        this.floor = floor;
        this.unit = unit;
        this.address = address;
        this.ownerId = ownerId;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
}
