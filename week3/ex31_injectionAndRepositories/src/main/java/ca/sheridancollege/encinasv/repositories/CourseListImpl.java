package ca.sheridancollege.encinasv.repositories;

import ca.sheridancollege.encinasv.beans.Course;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class CourseListImpl implements CourseList {
  private List<Course> courseList = new CopyOnWriteArrayList<Course>();
  @Override
  public List<Course> getCourseList() {
    return courseList;
  }
  @Override
  public void setCourseList(List<Course> courseList) {
    this.courseList = courseList;
  }
  @Override
  public void emptyList() {
    courseList.clear();
  }
}
