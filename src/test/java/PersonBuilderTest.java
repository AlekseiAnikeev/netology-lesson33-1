import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.agentche.entity.Person;
import ru.agentche.entity.PersonBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 18.10.2022
 */
public class PersonBuilderTest {
    Person person = new PersonBuilder()
            .name("Иван")
            .surname("Колобков")
            .age(22)
            .build();
    Person son = person.newChildBuilder()
            .setName("Антошка")
            .build();

    @Test
    @DisplayName("Челобрек должен иметь имя и фамилию")
    public void shouldBeNamed() {
        assertThrows(IllegalStateException.class, () -> new PersonBuilder().name("Иван").build());
        assertThrows(IllegalStateException.class, () -> new PersonBuilder().surname("Колобков").build());
    }

    @Test
    @DisplayName("Возраст увеличиваться только методом на 1 год")
    public void shouldIncreaseInTheMethod() {
        person.happyBirthday();
        assertEquals(23, person.getAge());
        assertThrows(IllegalArgumentException.class, () -> person.setAge(25));
    }

    @Test
    @DisplayName("Детеныш должен получить фамилию и адрес родителя, и установленный заранее возраст")
    public void shouldInheritTheFieldsOfTheParent(){
        assertEquals(person.getSurname(), son.getSurname());
        assertEquals(person.getAddress(), son.getAddress());
        assertEquals(10, son.getAge());
    }
}
