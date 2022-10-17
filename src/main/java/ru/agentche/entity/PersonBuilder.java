package ru.agentche.entity;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 17.10.2022
 */
public class PersonBuilder {
    private String name;
    private String surname;
    private int age = -1;
    private String address;

    public PersonBuilder name(String name) {
        this.name = name;
        return this;
    }

    public PersonBuilder surname(String surname) {
        this.surname = surname;
        return this;
    }

    public PersonBuilder age(int age) {
        if (this.age < 0) {
            if (age >= 0) {
                this.age = age;
            } else {
                throw new IllegalArgumentException("Не бывает возраст отрицательным");
            }
        } else {
            throw new IllegalArgumentException("Возраст изменить нереально, как бы этого не хотелось...");
        }
        return this;
    }

    public PersonBuilder address(String address) {
        if (this.address == null) {
            this.address = address;
        } else {
            throw new IllegalArgumentException("город уже указан, для изменения воспользуйтесь методом setAddress");
        }
        return this;
    }

    public PersonBuilder setName(String name) {
        if (this.name == null) {
            this.name = name;
        } else {
            throw new IllegalStateException("Имя уже заполнено");
        }
        return this;
    }

    public Person build() {
        if (this.name == null || this.surname == null) {
            throw new IllegalStateException("Поля имя и фамилия обязательны для заполнения");
        }
        return new Person(name, surname, age, address);
    }
}
