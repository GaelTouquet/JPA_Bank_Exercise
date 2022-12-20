package org.example.model;

import javax.persistence.*;
import java.util.concurrent.atomic.AtomicLong;

@Entity
@Table(name = "AGENCY")
public class Agency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    @Override
    public String toString() {
        return "Agency   :   " +
                "id:" + id +
                ", address:'" + address + '\'';
    }

    public Agency() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
