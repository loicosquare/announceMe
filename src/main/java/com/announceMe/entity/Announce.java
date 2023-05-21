package com.announceMe.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "announce")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Announce {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="category_id", referencedColumnName="id")
    private Category category;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName="id")
    private User user;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="isValid")
    private Boolean isValid;

    @Column(name="createdAt")
    private Date createdAt;

    @Column(name="updatedAt")
    private Date updatedAt;

    @Column(name="image")
    private String image;
}
