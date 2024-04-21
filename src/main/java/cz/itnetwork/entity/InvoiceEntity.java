package cz.itnetwork.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
@Entity(name = "invoice")
@Getter
@Setter
public class InvoiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String invoiceNumber;

    @Column
    private LocalDate issued;

    @Column
    private LocalDate dueDate;

    @Column
    private String product;

    @Column
    private long price;

    @Column
    private int vat;

    @Column
    private String note;

    @ManyToOne
    private PersonEntity buyer;

    @ManyToOne
    private PersonEntity seller;
}
