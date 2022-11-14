package com.example.client.model;


import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Subject {
    private Long id;
    private String subjectname;
    private String courseDuration;
    private String credit;
    private String price;

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", subjectname='" + subjectname + '\'' +
                ", courseDuration='" + courseDuration + '\'' +
                ", credit='" + credit + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
