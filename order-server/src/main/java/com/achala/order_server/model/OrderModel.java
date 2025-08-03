package com.achala.order_server.model;

import com.achala.order_server.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "order_table")
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer orderId;
    private String reference;
    private BigDecimal totalAmount;
    @Enumerated(value = EnumType.STRING)
    private PaymentMethod paymentMethod;
    private Integer customerId;
    @OneToMany(mappedBy = "orderItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;
    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDate;
}
