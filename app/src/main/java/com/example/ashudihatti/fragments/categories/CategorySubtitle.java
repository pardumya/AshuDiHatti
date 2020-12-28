package com.example.ashudihatti.fragments.categories;

public class CategorySubtitle {
    String id;
    String name;
    String slug;
    String parentcategory;

    public CategorySubtitle(String id, String name, String slug, String parentcategory) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.parentcategory = parentcategory;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public String getParentcategory() {
        return parentcategory;
    }
}
