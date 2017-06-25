package com.jsonfloyd.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import javax.persistence.*;

/**
 * Created by mrflo on 18.06.2017.
 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter int id;
    @Getter @Setter int userId;

    @Getter @Setter int positionId;
    @Column( insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Getter @Setter Date orderDate;
}
