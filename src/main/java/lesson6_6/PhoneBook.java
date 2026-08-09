package lesson6_6;

import java.util.*;

public class PhoneBook {
    // Key: last name, Value: list of phone numbers
    private final Map<String, List<String>> directory;

    public PhoneBook() {
        directory = new HashMap<>();
    }


    public void add(String lastName, String phoneNumber) {
        directory.computeIfAbsent(lastName, _ -> new ArrayList<>()).add(phoneNumber);
    }


    public List<String> get(String lastName) {
        List<String> numbers = directory.get(lastName);
        return numbers != null ? numbers : Collections.emptyList();
    }
}