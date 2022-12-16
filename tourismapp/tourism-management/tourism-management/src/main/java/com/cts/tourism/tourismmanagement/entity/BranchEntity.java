package com.cts.tourism.tourismmanagement.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "tourism")
public class BranchEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long branchId;

        private String branchName;

        private String place;

        private String website;

        private String contact;

        private String email;

        private int tariff;

        public String getContact() {
                return contact;
        }

        public void setContact(String contact) {
                this.contact = contact;
        }

        public String getPlace() {
                return place;
        }

        public void setPlace(String place) {
                this.place = place;
        }

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




        public String getWebsite() {
                return website;
        }

        public void setWebsite(String website) {
                this.website = website;
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

