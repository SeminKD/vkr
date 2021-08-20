package vkr.model.dto;

import java.util.Date;

public class NewsDto {

    private int id;
    private String name_article;
    private String preview_article;
    private String text_article;
    private Date date_article;

    public NewsDto(int id, String name_article, String preview_article, String text_article, Date date_article) {
        this.name_article = name_article;
        this.preview_article = preview_article;
        this.text_article = text_article;
        this.date_article = date_article;
        this.id = id;
    }

    public NewsDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
