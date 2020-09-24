package org.chu.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long student_id;
    private String surname;
    private String name;
    private String secondName;
    private int yearAdmission;
    @Enumerated(EnumType.ORDINAL)
    private EducationForm educationForm;
    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private StudentGroup studentGroup;

    public Student(String surname, String name, String secondName, int yearAdmission, EducationForm educationForm, StudentGroup studentGroup) {
        this.surname = surname;
        this.name = name;
        this.secondName = secondName;
        this.yearAdmission = yearAdmission;
        this.educationForm = educationForm;
        this.studentGroup = studentGroup;
    }
}
