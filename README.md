![Creation patterns](https://cdn0.iconfinder.com/data/icons/aami-web-internet/64/aami15-07-256.png)

# *«Порождающие шаблоны. Builder, Singleton, Factory Method, Abstract Factory, Prototype»*

# Задача №1. “Люди” (обязательная)

## Описание

В этом задании попрактикуемся с шаблоном Builder (Строитель).
Мы спроектируем класс ru.agentche.entity.PersonBuilder, в котором будут храниться данные о человеке:

- Имя (String). Каждый человек обязан иметь имя, причём с момента создания объекта изменить его нельзя.
- Фамилия (String). Каждый человек обязан иметь фамилию, причём с момента создания объекта изменить её нельзя.
- Возраст (int). Если возраст человека известен, то с момента создания объекта он может быть изменён только увеличением
  на единицу через вызов метода happyBirthday(). Возраст человека может быть неизвестен, в этом случае метод boolean
  hasAge() должен вернуть false, иначе - true. Подумайте, как эффективнее хранить в объекте информацию о том, известен
  ли возраст человека.
- Текущий город жительства (String). Может быть известен (в этом случае метод boolean hasAddress() должен вернуть true,
  иначе - false) и выставлен в любой через setAddress(String city).
- Все данные о человеке должны быть доступны через соответствующие методы (например, String getName()), поля же класса
  не должны быть public.

Также надо создать класс ru.agentche.entity.PersonBuilder для конструирования объектов класса
ru.agentche.entity.PersonBuilder. Объекту этого класса (далее - билдер) можно выставлять любые
данные для будущего объекта класса ru.agentche.entity.PersonBuilder через методы (например,
setName(String name)). И в этом объекте будет метод ru.agentche.entity.PersonBuilder build(),
возвращающий объект класса ru.agentche.entity.PersonBuilder с указанными билдеру данными. В случае,
если мы билдеру не указали достаточное количество данных (например,
не указали фамилию), то метод build() должен выкинуть
IllegalStateException с осмысленным сообщением. Если же мы передали
неподходящие данные билдеру (например, некорректный возраст
builder.setAge(-100)), то именно этот метод должен выкинуть
IllegalArgumentException с осмысленным сообщением. Каждый метод
добавления данных в билдер должен возвращать самого себя чтобы можно
было сделать, например, вот так:

```java
ru.agentche.entity.PersonBuilder person=new ru.agentche.entity.PersonBuilder()
        .setName("Антошка")
        .setSurname("Лопатов")
        .setAge(48)
        .build();
```

Также в класс ru.agentche.entity.PersonBuilder надо добавить метод ru.agentche.entity.PersonBuilder newChildBuilder(),
который будет возвращать полу-заполненный билдер для ребёнка, а именно:
с уже заполненными фамилией (родительской), возрастом и текущим городом
жительства (родительским).

Продемонстрируйте работу ваших классов в классе ru.agentche.entity.Main (необязательно
реализовывать ввод данных от пользователя).

# Реализация

1. Создайте класс ru.agentche.entity.PersonBuilder с полями, необходимыми для хранения данных, указанных в условии.

```java
public class ru.agentche.entity.PersonBuilder {
    protected final String name;
    protected final String surname;
    //...

    public ru.agentche.entity.PersonBuilder(String name, String surname) {
        //...
    }

    public ru.agentche.entity.PersonBuilder(String name, String surname, int age) {
        //...
    }
}
```

2. Наполните класс ru.agentche.entity.PersonBuilder методами, нужными для реализации поведения объектов этого класса как описано выше в условии.

```java
public class ru.agentche.entity.PersonBuilder {
    //...

    public boolean hasAge() { /*...*/ }

    public boolean hasAddress() { /*...*/ }

    public String getName() { /*...*/ }

    public String getSurname() { /*...*/ }

    public OptionalInt getAge() { /*...*/ }

    public String getAddress() { /*...*/ }

    public void setAddress(String address) { /*...*/ }

    public void happyBirthday() { /*...*/ }

    @Override
    public String toString() { /*...*/ }

    @Override
    public int hashCode() { /*...*/ }
}
```

3. Создайте класс ru.agentche.entity.PersonBuilder, наполните его полями для данных будущего объекта класса ru.agentche.entity.PersonBuilder и методами их
   наполняющими (не забудьте про IllegalArgumentException в случае ввода недопустимых данных)

```java
public class ru.agentche.entity.PersonBuilder {
    //...

    public ru.agentche.entity.PersonBuilder setName(String name) { /*...*/ }

    public ru.agentche.entity.PersonBuilder setSurname(String surname) { /*...*/ }

    public ru.agentche.entity.PersonBuilder setAge(int age) { /*...*/ }

    public ru.agentche.entity.PersonBuilder setAddress(String address) { /*...*/ }

    public ru.agentche.entity.PersonBuilder build() { /*...*/ }
}
```

4. Добавьте метод для получения полу-заполненного билдера для ребёнка в класс ru.agentche.entity.PersonBuilder

```java
public class ru.agentche.entity.PersonBuilder {
    //...

    public ru.agentche.entity.PersonBuilder newChildBuilder() { /*...*/ }
}
```

5. Добавьте класс ru.agentche.entity.Main для демонстрации

```java
public class ru.agentche.entity.Main {
    public static void main(String[] args) {
        ru.agentche.entity.PersonBuilder mom = new ru.agentche.entity.PersonBuilder()
                .setName("Анна")
                .setSurname("Вольф")
                .setAge(31)
                .setAddress("Сидней")
                .build();
        ru.agentche.entity.PersonBuilder son = mom.newChildBuilder()
                .setName("Антошка")
                .build();
        System.out.println("У " + mom + " есть сын, " + son);

        try {
            // Не хватает обязательных полей
            new ru.agentche.entity.PersonBuilder().build();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

        try {
            // Возраст недопустимый
            new ru.agentche.entity.PersonBuilder().setAge(-100).build();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
```

6. Протестируйте работу программы.

