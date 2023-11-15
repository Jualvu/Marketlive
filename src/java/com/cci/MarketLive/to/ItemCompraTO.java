package com.cci.MarketLive.to;

public class ItemCompraTO {

    private int id;
    private int compraId;
    private int itemId;

    public ItemCompraTO() {
    }

    public ItemCompraTO(int id, int compraId, int itemId) {
        this.id = id;
        this.compraId = compraId;
        this.itemId = itemId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompraId() {
        return compraId;
    }

    public void setCompraId(int compraId) {
        this.compraId = compraId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

}
