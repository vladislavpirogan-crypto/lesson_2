package lesson6_6;

import java.util.*;

public class PhoneBook {
    private final Map<String, List<String>> directory;
    public PhoneBook() {
        directory = new HashMap<>();
    }
    public void add(String lastName, String phoneNumber) {
        directory.computeIfAbsent(lastName, _ -> new ArrayList<>()).add(phoneNumber);
    }
}