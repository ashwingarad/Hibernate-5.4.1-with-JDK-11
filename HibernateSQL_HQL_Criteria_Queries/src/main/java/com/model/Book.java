package com.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_name")
    private String bname;

    @Column(name = "book_price")
    private Integer price;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "authorbook",
            joinColumns = { @JoinColumn(name = "bid") },
            inverseJoinColumns = { @JoinColumn(name = "aid") })
    private Set<Author> authorSet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Set<Author> getAuthorSet() {
        return authorSet;
    }

    public void setAuthorSet(Set<Author> authorSet) {
        this.authorSet = authorSet;
    }
}
