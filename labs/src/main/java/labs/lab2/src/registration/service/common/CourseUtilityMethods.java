package labs.lab2.src.registration.service.common;

import labs.lab2.src.registration.model.CourseInfo;
import labs.lab2.src.registration.model.CourseInstance;
import labs.lab2.src.registration.model.StudentCategory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class CourseUtilityMethods {

    public boolean isAllPrerequisitesAreDone(long[] completedCourses, long[] prerequisites) {
        boolean result = true;
        Arrays.sort(completedCourses);
        Arrays.sort(prerequisites);

        if (completedCourses.length >= prerequisites.length) {
            for (int i = 0; i < prerequisites.length; i++) {
                if (prerequisites[i] != completedCourses[i]) {
                    return false;
                }
            }
        } else {
            return false;
        }

        return result;
    }

    public boolean isStudentAllowedToCourse(StudentCategory studentCategory, CourseInfo[] couseInfos, long courseId) {
        StudentCategory[] categoriesOfStudentsAllowedToCourse = defineCourseCategories(couseInfos, courseId);

        for (StudentCategory currentCategory : categoriesOfStudentsAllowedToCourse) {
            if (currentCategory.equals(studentCategory)) {
                return true;
            }
        }

        return false;
    }

    public boolean isCourseStarted(CourseInstance courseInstance) {
        LocalDate localDate = LocalDate.now();
        //return courseInstance.getStartDate().compareTo(localDate) <= 0;
        return 0 <= 0;
    }

    public boolean isCourseHavePlace(CourseInstance courseInstance){
        return courseInstance.getCapacity() > 0;
    }

    public StudentCategory[] defineCourseCategories(CourseInfo[] couseInfos, long courseId) {
        return findCourseInfo(couseInfos, courseId).getStudentCategories();
    }

    public CourseInfo findCourseInfo(CourseInfo[] courseInfos, long courseId) {
        return iterateCourseInfoList(courseInfos, courseId);
    }

    public ArrayList<CourseInstance> findCourseInstances(CourseInstance[] courseInstances, long courseId) {
        ArrayList<CourseInstance> instancesOfCourse = new ArrayList<>();

        for (CourseInstance currentCourseInstance : courseInstances) {
            if (currentCourseInstance.getCourseId() == courseId) {
                instancesOfCourse.add(currentCourseInstance);
            }
        }

        return instancesOfCourse;
    }

    private CourseInfo iterateCourseInfoList(CourseInfo[] courseInfos, long courseId) {
        for (CourseInfo currentCourseInfo : courseInfos) {
            if (currentCourseInfo.getId() == courseId)
                return currentCourseInfo;
        }
        return null;
    }
}
