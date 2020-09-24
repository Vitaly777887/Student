package org.chu.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
public class Specially {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long specially_id;
    private String name;
}
