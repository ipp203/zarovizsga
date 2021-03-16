package hu.nive.ujratervezes.zarovizsga.kennel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Kennel {
    private final List<Dog> dogs = new ArrayList<>();

    public List<Dog> getDogs() {
        return new ArrayList<>(dogs);
    }

    public void addDog(Dog dog) {
        dogs.add(dog);
    }

    public void feedAll() {
        for (Dog dog : dogs) {
            dog.feed();
        }
    }

    public Dog findByName(String name) {
        Optional<Dog> dog = dogs.stream()
                .filter(d -> d.getName().equals(name))
                .findFirst();
        if (dog.isPresent()) {
            return dog.get();
        }
        throw new IllegalArgumentException("Can not find dog. " + name);
    }


    public void playWith(String name, int hours) {
        findByName(name).play(hours);
    }

    public List<String> getHappyDogNames(int minHappiness) {
        return dogs.stream()
                .filter(d -> d.getHappiness() > minHappiness)
                .map(Dog::getName)
                .collect(Collectors.toList());
    }

}
