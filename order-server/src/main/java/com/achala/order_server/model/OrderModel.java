package com.achala.order_server.model;

import com.achala.order_server.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "order_table")
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer order_id;
    private String reference;
    private BigDecimal totalAmount;
    @Enumerated(value = EnumType.STRING)
    private PaymentMethod paymentMethod;
    private Integer customer_id;
    @OneToMany(mappedBy = "orderItem")
    private List<OrderItem> orderItems;
    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDate;
}
