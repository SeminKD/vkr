package vkr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "company")
public class Company implements Serializable {

    @Id
    @Column(name = "id_company")
    private int id_company;

    @Column(name = "name_company")
    private String name_company;

    @Column(name = "information_company")
    private String information_company;

    public Company() {
    }

    public Company(int id_company, String name_company, String information_company) {
        this.id_company = id_company;
        this.name_company = name_company;
        this.information_company = information_company;
    }

    public void setId_company(int id_company) {
        this.id_company = id_company;
    }

    public int getId_company() {
        return id_company;
    }

    public String getName_company() {
        return name_company;
    }

    public void setName_company(String name_company) {
        this.name_company = name_company;
    }

    public String getInformation_company() {
        return information_company;
    }

    public void setInformation_company(String information_company) {
        this.information_company = information_company;
    }
}
