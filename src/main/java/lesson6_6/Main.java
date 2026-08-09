package lesson6_6;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Создаём коллекцию студентов
        Set<Student> students = new HashSet<>();

        students.add(new Student("Анна", "ИВТ-21", 2, Arrays.asList(4, 5, 4, 3)));
        students.add(new Student("Борис", "ИВТ-22", 2, Arrays.asList(2, 2, 3, 2))); // средний < 3
        students.add(new Student("Виктор", "ИВТ-23", 3, Arrays.asList(5, 5, 5, 4)));
        students.add(new Student("Галина", "ИВТ-24", 3, Arrays.asList(3, 3, 4, 3)));

        System.out.println("=== Все студенты ===");
        for (Student s : students) {
            System.out.println(s);
        }

        // Удаляем студентов со средним < 3 (создаём новую коллекцию)
        Set<Student> filtered = StudentUtils.removeStudentsWithLowAverage(students);

        System.out.println("\n=== После удаления студентов со средним < 3 ===");
        for (Student s : filtered) {
            System.out.println(s);
        }

        // Переводим студентов с хорошим средним на следующий курс (меняем состояние)
        StudentUtils.promoteStudentsWithGoodAverage(filtered);

        System.out.println("\n=== После перевода на следующий курс ===");
        for (Student s : filtered) {
            System.out.println(s);
        }

        // Печатаем имена студентов 3-го курса
        System.out.println("\n=== Студенты 3-го курса ===");
        StudentUtils.printStudents(filtered, 3);
    }
}

public static void main(String[] args) {
    PhoneBook phoneBook = new PhoneBook();

    phoneBook.add("Ivanov", "+79001112233");
    phoneBook.add("Ivanov", "+79004445566"); // another Ivanov
    phoneBook.add("Petrov", "+79007778899");
    phoneBook.add("Sidorov", "+79003334455");

    String lastNameToSearch = "Ivanov";
    List<String> phones = phoneBook.get(lastNameToSearch);

    System.out.println("Phones for '" + lastNameToSearch + "':");
    if (phones.isEmpty()) {
        System.out.println("No entries found.");
    } else {
        for (String number : phones) {
            System.out.println(number);
        }
    }
}
}
