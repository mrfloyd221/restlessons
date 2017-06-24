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
@Table(name="Order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="order_id")
    @Getter @Setter int id;
    @Column(name="user_id")
    @Getter @Setter int userId;
    @Column(name="position_id")
    @Getter @Setter int positionId;
    @Column(name="order_date", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Getter @Setter Date orderDate;
}
