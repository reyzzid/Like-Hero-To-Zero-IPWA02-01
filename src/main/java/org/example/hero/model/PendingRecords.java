package org.example.hero.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigInteger;

@Entity
@Table(name = "pending_records")
public class PendingRecords {

    @Id
    private int id;

    @Column(nullable = false)
    private String country;

    @Column (nullable = false)
    private int year;

    @Column (nullable = false)
    private BigInteger emissions;

    public PendingRecords() {}

    public int getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry (String country){
        this.country=country;
    }

    public int getYear() {
        return year;
    }

    public void setYear (int year){
        this.year=year;
    }

    public BigInteger getEmissions() {
        return emissions;
    }

    public void setEmissions (BigInteger  emissions){
        this.emissions=emissions;
    }
}
