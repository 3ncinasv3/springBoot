package ca.sheridancollege.encinasv.repositories;

import ca.sheridancollege.encinasv.beans.Course;

import java.util.List;

public interface CourseList {
  public List<Course> getCourseList();
  public void setCourseList(List<Course> courseList);
  public void emptyList();

}
