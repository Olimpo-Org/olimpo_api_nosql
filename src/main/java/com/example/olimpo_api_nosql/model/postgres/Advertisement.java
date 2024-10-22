package com.example.olimpo_api_nosql.model.postgres;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "advertisement")
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Description cannot be null")
    @Column(name = "title")
    private String title;

    @NotNull(message = "Description cannot be null")
    @Column(name = "description")
    private String description;

    @NotNull(message = "Divulgation date cannot be null")
    @Column(name = "publication_date")
    private LocalDate divulgationDate;

    @Column(name = "category")
    private Integer category;

    @Column(name = "image")
    private String imageUrl;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "plan_id")
    private Long idPlan;

    public Advertisement() {
    }

    public Advertisement(Long id, String title, String description, LocalDate divulgationDate, Integer category, String imageUrl, Long userId, Long idPlan) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.divulgationDate = divulgationDate;
        this.category = category;
        this.imageUrl = imageUrl;
        this.userId = userId;
        this.idPlan = idPlan;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDivulgationDate() {
        return divulgationDate;
    }

    public void setDivulgationDate(LocalDate divulgationDate) {
        this.divulgationDate = divulgationDate;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(Long idPlan) {
        this.idPlan = idPlan;
    }
}
