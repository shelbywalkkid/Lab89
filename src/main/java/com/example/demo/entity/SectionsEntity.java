package com.example.demo.entity;
//import jakarta.persistence.*;
import jakarta.persistence.*;

@Entity
@Table(name = "sections", schema = "users20")
public class SectionsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Section_Id", nullable = false, insertable = true, updatable = true)
    private int sectionId;
    @Basic
    @Column(name = "Section")
    private String section;

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public SectionsEntity(String name){

        this.section=name;
    }
    public SectionsEntity(){

    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SectionsEntity that = (SectionsEntity) o;

        if (sectionId != that.sectionId) return false;
        if (section != null ? !section.equals(that.section) : that.section != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        Integer result = sectionId;
        result = 31 * result + (section != null ? section.hashCode() : 0);
        return Math.toIntExact(result);
    }
}
