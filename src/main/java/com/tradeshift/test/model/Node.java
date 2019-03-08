package com.tradeshift.test.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Node {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parent")
    private Node parent;

    @ManyToOne
    @JoinColumn(name = "root")
    private Node root;

    @Column(name = "height")
    private Integer height;

    @JsonIgnore
    @OneToMany(mappedBy = "parent")
    private List<Node> children;

    public Node() {
    }

    public Node(Long id, Node parent, Node root, Integer height, List<Node> children) {
        this.id = id;
        this.parent = parent;
        this.root = root;
        this.height = height;
        this.children = children;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }
}
