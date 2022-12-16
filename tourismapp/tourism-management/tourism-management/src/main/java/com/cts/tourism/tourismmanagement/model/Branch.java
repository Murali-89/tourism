package com.cts.tourism.tourismmanagement.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Branch {

    private long branchId;
    @NotNull(message="First name cannot be missing or empty")
    private String branchName;

    private String website;
    @Size(min = 10, max = 10, message = "phone must be of 10 digit")
    private String contact;
    @Email
    private String email;

    private String place;

    @Min(50000)
    @Max(100000)
    private int tariff;

    public long getBranchId() {
        return branchId;
    }

    public void setBranchId(long branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }


    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTariff() {
        return tariff;
    }

    public void setTariff(int tariff) {
        this.tariff = tariff;
    }
}
