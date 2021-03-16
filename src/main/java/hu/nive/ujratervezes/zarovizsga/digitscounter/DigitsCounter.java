package hu.nive.ujratervezes.zarovizsga.digitscounter;

public class DigitsCounter {
    public int getCountOfDigits(String s) {
        if (s == null) {
            return 0;
        }

        return (int) s.chars()
                .distinct()
                .filter(Character::isDigit)
                .count();
    }
}
