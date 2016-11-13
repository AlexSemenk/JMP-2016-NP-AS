package com.epam.jmp.library.rest.dto;

import com.epam.jmp.library.entity.Library;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LibraryDto {

    @XmlElement
    private Long id;
    @XmlElement
    private String name;
    @XmlElement
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean hasDescription() {
        return description != null;
    }

    public Library toJavaBean() {
        Library library = new Library(this.getName());
        if (this.hasDescription()) {
            library.setDescription(this.getDescription());
        }
        return library;
    }

    public static LibraryDto fromJavaBean(Library library) {
        if (library == null) {
            return null;
        }
        LibraryDto dto = new LibraryDto();
        dto.setId(library.getId());
        dto.setName(library.getName());
        dto.setDescription(library.getDescription());
        return dto;
    }

}
