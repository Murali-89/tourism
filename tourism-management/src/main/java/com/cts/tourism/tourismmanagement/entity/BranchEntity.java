package com.cts.tourism.tourismmanagement.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "tourism")
public class BranchEntity {

        @Id
        @GeneratedValue
        private long branchId;

        private String branchName;

        private String places;
        private String website;
        private int contact;
        private String email;
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

        public String getPlaces() {
                return places;
        }

        public void setPlaces(String places) {
                this.places = places;
        }

        public String getWebsite() {
                return website;
        }

        public void setWebsite(String website) {
                this.website = website;
        }

        public int getContact() {
                return contact;
        }

        public void setContact(int contact) {
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

