package camt.cbsd.entity;

import camt.cbsd.config.json.View;
import camt.cbsd.entity.security.Authority;
import camt.cbsd.entity.security.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Dto on 3/11/2017.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    @JsonView(View.Login.class)
    String studentId;
    @JsonView(View.Login.class)
    String name;
    @JsonView(View.Login.class)
    String surname;
    double gpa;
    @JsonView(View.Login.class)
    String image;
    boolean feature;
    int penAmount;
    String description;
    @ManyToMany
    List<Course> enrolledCourse = new ArrayList<>();

    @OneToOne
    User user;
    @JsonView(View.Login.class)
    public List<Authority> getAuthorities(){
        return user.getAuthorities();
    }

    public List<Course> addCourse(Course course) {
        enrolledCourse = Optional.ofNullable(enrolledCourse).orElse(new ArrayList<>());
        enrolledCourse.add(course);
        return enrolledCourse;

    }


}
