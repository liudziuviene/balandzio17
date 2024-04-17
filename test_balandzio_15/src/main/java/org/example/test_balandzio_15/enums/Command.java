package main.java.org.example.test_balandzio_15.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Command {

    A("Prideti nauja knyga"),
    B("Pasalinti knyga"),
    C("Pakeisti knygos vieta"),
    D("Atspausdinti knygu sarasa pagal zanra"),
    E("Atspausdinti siu metu fantastikos zanro knygas"),
    F("Atspausdinti visas knygas"),
    Z("Baigti");

    private final String description;

    public static void printCommands() {
        for (Command command : Command.values()) {
            System.out.println(command.name() + "-" + command.getDescription());
        }
    }
}
