package com.example.Bill.Generation.System.DTO;

public class BillRequest {
    private long mobileNo;
    private int pID;
    private int quantity;

    public BillRequest(long mobileNo, int pID, int quantity) {
        this.mobileNo = mobileNo;
        this.pID = pID;
        this.quantity = quantity;
    }

    public long getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(long mobileNo) {
        this.mobileNo = mobileNo;
    }

    public int getpID() {
        return pID;
    }

    public void setpID(int pID) {
        this.pID = pID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
