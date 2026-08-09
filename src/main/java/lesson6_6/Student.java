package lesson6_6;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private final String name;
    private final String group;
    private int course;
    private final List<Integer> grades; // оценки по предметам

    public Student(String name, String group, int course, List<Integer> grades) {
        this.name = name;
        this.group = group;
        this.course = course;
        // копируем список, чтобы не зависеть от внешней коллекции
        this.grades = new ArrayList<>(grades);
    }

    // Геттеры
    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public int getCourse() {
        return course;
    }

    public List<Integer> getGrades() {
        return new ArrayList<>(grades); // возвращаем копию
    }

    // Средний балл
    public double getAverageGrade() {
        if (grades.isEmpty()) {
            return 0.0;
        }
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return (double) sum / grades.size();
    }

    // Перевод на следующий курс
    public void promoteToNextCourse() {
        course++;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", group='" + group + '\'' +
                ", course=" + course +
                ", average=" + String.format("%.2f", getAverageGrade()) +
                '}';
    }
}