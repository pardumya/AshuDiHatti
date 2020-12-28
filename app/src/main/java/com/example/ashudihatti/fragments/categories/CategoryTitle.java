package com.example.ashudihatti.fragments.categories;

public class CategoryTitle {
    int id;
    String name;
    String slug;
    public CategoryTitle(int id,String slug,String name)
    {
        this.id = id;
        this.name = name;
        this.slug = slug;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }
}
