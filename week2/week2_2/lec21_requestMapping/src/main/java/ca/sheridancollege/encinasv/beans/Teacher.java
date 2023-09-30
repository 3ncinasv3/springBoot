package ca.sheridancollege.encinasv.beans;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Component
public class Teacher {
  private String firstName;
  private String lastName;
  private String email;
  private Long teacherNumber;
  private LocalDate birthday;

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Long getTeacherNumber() {
    return teacherNumber;
  }

  public void setTeacherNumber(Long teacherNumber) {
    this.teacherNumber = teacherNumber;
  }

  public LocalDate getBirthday() {
    return birthday;
  }

  public void setBirthday(LocalDate birthday) {
    this.birthday = birthday;
  }

  @Override
  public String toString() {
    return "Teacher{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", teacherNumber=" + teacherNumber +
            ", birthday=" + birthday +
            '}';
  }
}
