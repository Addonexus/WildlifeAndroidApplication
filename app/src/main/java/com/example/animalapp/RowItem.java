package com.example.animalapp;

public class RowItem {

    private String reserves;
    private int pic_id;
    private String shortDesc;
    private String reserveType;

    public RowItem(String reserves, int pic_id, String shortDesc,
                   String reserveType) {

        this.reserves = reserves;
        this.pic_id = pic_id;
        this.shortDesc = shortDesc;
        this.reserveType = reserveType;
    }

    public String getReserves() {
        return reserves;
    }

    public void setReserves(String reserves) {
        this.reserves = reserves;
    }

    public int getPic_id() {
        return pic_id;
    }

    public void setPic_id(int pic_id) {
        this.pic_id = pic_id;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getReserveType() {
        return reserveType;
    }

    public void setReserveType(String reserveType) {
        this.reserveType = reserveType;
    }
}
