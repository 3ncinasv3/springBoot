package ca.sheridancollege.encinasv.beans;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Component
public class Student {
  private String firstName;
  private String lastName;
  private String email;
  private Long studentNumber;
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

  public Long getStudentNumber() {
    return studentNumber;
  }

  public void setStudentNumber(Long studentNumber) {
    this.studentNumber = studentNumber;
  }

  public LocalDate getBirthday() {
    return birthday;
  }

  public void setBirthday(LocalDate birthday) {
    this.birthday = birthday;
  }

  @Override
  public String toString() {
    return "Student{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", studentNumber=" + studentNumber +
            ", birthday=" + birthday +
            '}';
  }
}
