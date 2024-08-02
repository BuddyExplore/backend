package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Builder
@Table(name="Blogs")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Data
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String content;
    private String posted_date;

}
