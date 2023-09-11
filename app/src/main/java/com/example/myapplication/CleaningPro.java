package com.example.myapplication;

public class CleaningPro {
    private int hoId;
    private String CustomerName;
    private String CustomerAddress;
    private String CustomerPhone;
    private int Rooms;
    private int Living;
    private int Bath;
    private int Kitchan;
    private int Floor;
    private byte[] hOImg;
    private String status;
    private int totalPrice;

    public CleaningPro() {
    }

    public CleaningPro(int hoId, String customerName, String customerAddress, String customerPhone, int rooms, int living, int bath, int kitchan, int floor, byte[] hOImg, String status, int totalPrice) {
        this.hoId = hoId;
        this.CustomerName = customerName;
        this.CustomerAddress = customerAddress;
        this.CustomerPhone = customerPhone;
        this.Rooms = rooms;
        this.Living = living;
        this.Bath = bath;
        this.Kitchan = kitchan;
        this.Floor = floor;
        this.hOImg = hOImg;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    public int getHoId() {
        return hoId;
    }

    public void setHoId(int hoId) {
        this.hoId = hoId;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getCustomerAddress() {
        return CustomerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        CustomerAddress = customerAddress;
    }

    public String getCustomerPhone() {
        return CustomerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        CustomerPhone = customerPhone;
    }

    public int getRooms() {
        return Rooms;
    }

    public void setRooms(int rooms) {
        Rooms = rooms;
    }

    public int getLiving() {
        return Living;
    }

    public void setLiving(int living) {
        Living = living;
    }

    public int getBath() {
        return Bath;
    }

    public void setBath(int bath) {
        Bath = bath;
    }

    public int getKitchan() {
        return Kitchan;
    }

    public void setKitchan(int kitchan) {
        Kitchan = kitchan;
    }

    public int getFloor() {
        return Floor;
    }

    public void setFloor(int floor) {
        Floor = floor;
    }

    public byte[] gethOImg() {
        return hOImg;
    }

    public void sethOImg(byte[] hOImg) {
        this.hOImg = hOImg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}