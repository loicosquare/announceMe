package com.announceMe.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "category")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="label")
    private String label;

    @Column(name="description")
    private String description;

    @Column(name="createdAt")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy="category", fetch = FetchType.LAZY)
    private List<Announce> announces;
}
