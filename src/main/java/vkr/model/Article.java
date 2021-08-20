package vkr.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "article")
public class Article implements Serializable {

    @Id
    @Column(name = "id_article")
    private int id_article;

    @Column(name = "name_article")
    private String name_article;

    @Column(name = "preview_article")
    private String preview_article;

    @Column(name = "text_article")
    private String text_article;

    @Column(name = "date_article")
    private Date date_article;

    @JoinColumn(name = "id_company")
    @ManyToOne(cascade = CascadeType.ALL)
    private Company company;

    public Article() {
    }

    public int getId_article() {
        return id_article;
    }

    public void setId_article(int id_article) {
        this.id_article = id_article;
    }

    public String getName_article() {
        return name_article;
    }

    public void setName_article(String name_article) {
        this.name_article = name_article;
    }

    public String getPreview_article() {
        return preview_article;
    }

    public void setPreview_article(String preview_article) {
        this.preview_article = preview_article;
    }

    public String getText_article() {
        return text_article;
    }

    public void setText_article(String text_article) {
        this.text_article = text_article;
    }

    public Date getDate_article() {
        return date_article;
    }

    public void setDate_article(Date date_article) {
        this.date_article = date_article;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
