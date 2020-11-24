package com.unicom.wasalakclientproduct.model.cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConfirmOrderDTO {
    @SerializedName("paymentMethod")
    @Expose
    private Integer paymentMethod;

    @SerializedName("notes")
    @Expose
    private String notes;

    public ConfirmOrderDTO(Integer paymentMethod, String notes) {
        this.paymentMethod = paymentMethod;
        this.notes = notes;
    }

    public Integer getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Integer paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
