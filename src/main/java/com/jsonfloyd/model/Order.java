package com.jsonfloyd.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Created by mrflo on 18.06.2017.
 */
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Getter @Setter int id;
    @Getter @Setter int userId;
    @Getter @Setter int positionId;
    @Getter @Setter Date orderDate;
}
