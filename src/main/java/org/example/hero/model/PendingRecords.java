package org.example.hero.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
    private long emissions;

    public PendingRecords() {}

    public PendingRecords (int id, String country, int year, long emissions) {
        this.id=id;
        this.country=country;
        this.year=year;
        this.emissions=emissions;
    }

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

    public long getEmissions() {
        return emissions;
    }

    public void setEmissions (long emissions){
        this.emissions=emissions;
    }
}
