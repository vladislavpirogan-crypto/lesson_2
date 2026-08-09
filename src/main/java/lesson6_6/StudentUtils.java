package lesson6_6;

import java.util.*;

public class StudentUtils {

    /**
     * Удаляет из коллекции студентов со средним баллом < 3.
     * Возвращает новую коллекцию без таких студентов (не меняет исходную).
     */
    public static Set<Student> removeStudentsWithLowAverage(Set<Student> students) {
        Set<Student> result = new HashSet<>();
        for (Student s : students) {
            if (s.getAverageGrade() >= 3.0) {
                result.add(s);
            }
        }
        return result;
    }

    /**
     * Переводит студентов на следующий курс, если средний балл >= 3.
     * Меняет состояние объектов внутри коллекции.
     */
    public static void promoteStudentsWithGoodAverage(Collection<Student> students) {
        for (Student s : students) {
            if (s.getAverageGrade() >= 3.0) {
                s.promoteToNextCourse();
            }
        }
    }

    /**
     * Печатает имена студентов, которые учатся на указанном курсе.
     */
    public static void printStudents(Set<Student> students, int course) {
        boolean found = false;
        for (Student s : students) {
            if (s.getCourse() == course) {
                System.out.println(s.getName());
                found = true;
            }
        }
        if (!found) {
            System.out.println("Студентов на курсе " + course + " не найдено.");
        }
    }
}
