package lesson6_6;

import java.util.*;
import java.util.stream.Collectors;

public class StudentUtils {

    /**
     * Возвращает новую коллекцию студентов со средним баллом >= 3.
     * Исходная коллекция не изменяется.
     */
    public Set<Student> getPassedStudents(Set<Student> students) {
        if (students == null) {
            return new HashSet<>();
        }

        return students.stream()
                .filter(student -> student.getAverageGrade() >= 3.0)
                .collect(Collectors.toSet());
    }

    /**
     * Группирует студентов по курсу.
     */
    public Map<Integer, Set<Student>> groupByCourse(Set<Student> students) {
        if (students == null) {
            return new HashMap<>();
        }

        return students.stream()
                .collect(Collectors.groupingBy(
                        Student::getCourse,
                        Collectors.toSet()
                ));
    }

    /**
     * Находит студента с максимальным средним баллом.
     */
    public Student findTopStudent(Set<Student> students) {
        if (students == null || students.isEmpty()) {
            return null;
        }

        return students.stream()
                .max(Comparator.comparingDouble(Student::getAverageGrade))
                .orElse(null);
    }
}