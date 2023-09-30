package ca.sheridancollege.encinasv.beans;

import java.util.Objects;

public class Course {
  private Long id;
  private String prefix;
  private Long code;
  private String name;

  public Course(Long id, String prefix, Long code, String name) {
    this.id = id;
    this.prefix = prefix;
    this.code = code;
    this.name = name;
  }

  public Course() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPrefix() {
    return prefix;
  }

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

  public Long getCode() {
    return code;
  }

  public void setCode(Long code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Course{" +
            "id=" + id +
            ", prefix='" + prefix + '\'' +
            ", code=" + code +
            ", name='" + name + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Course course = (Course) o;
    return code == course.code && Objects.equals(id, course.id) && Objects.equals(prefix, course.prefix) && Objects.equals(name, course.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, prefix, code, name);
  }

}
