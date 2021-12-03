package com.jiankowalski.basicspring.domain;


public class Pet {
    
    public Pet(String name, String tag, Long id) {
        this.name = name;
        this.tag = tag;
        this.id = id;
    }

    private String name;

    private String tag;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long id;
}
