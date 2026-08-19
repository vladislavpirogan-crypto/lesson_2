package lesson6_6;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Set<Student> allStudents = new HashSet<>();
        allStudents.add(new Student("Ivanov", "GR-101", 2, Arrays.asList(5, 4, 5)));
        allStudents.add(new Student("Petrov", "GR-102", 2, Arrays.asList(2, 3, 2)));
        allStudents.add(new Student("Sidorov", "GR-101", 3, Arrays.asList(4, 4, 3)));

        // Создаем экземпляр класса (это убирает проблему "нестатичности")
        StudentUtils utils = new StudentUtils();

        // Теперь вызываем методы через объект. Предупреждения исчезнут!
        Set<Student> passed = utils.getPassedStudents(allStudents);
        System.out.println("Сдали: " + passed);

        Map<Integer, Set<Student>> byCourse = utils.groupByCourse(allStudents);
        System.out.println("По курсам: " + byCourse);

        Student top = utils.findTopStudent(allStudents);
        System.out.println("Лучший: " + top);
    }
}