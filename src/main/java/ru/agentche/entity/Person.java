package ru.agentche.entity;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 17.10.2022
 */
public class Person {
    private final String name;
    private final String surname;
    private int age;
    private String address;

    protected Person(String name, String lastName, int age, String address) {
        this.name = name;
        this.surname = lastName;
        this.age = age;
        this.address = address;
    }

    public boolean hasAge() {
        return this.age >= 0;
    }

    public boolean hasAddress() {
        return this.address != null;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public void setAge(int age) {
        if (hasAge()) {
            throw new IllegalArgumentException("Возраст уже установлен, воспользуйтесь методом happyBirthday()");
        } else {
            this.age = age;
        }
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void happyBirthday() {
        this.age = hasAge() ? this.age + 1 : age;
    }

    public PersonBuilder newChildBuilder() {
        PersonBuilder child = new PersonBuilder();
        child.surname(this.surname);
        child.address(this.address);
        child.age(10);
        return child;
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", lastName='" + surname + '\'' +
                ", age=" + age +
                ", city='" + address + '\'' +
                '}';
    }
}
