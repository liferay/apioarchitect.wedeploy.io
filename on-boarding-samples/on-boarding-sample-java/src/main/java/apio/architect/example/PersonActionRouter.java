package apio.architect.example;

import com.liferay.apio.architect.annotation.Actions.Retrieve;
import com.liferay.apio.architect.annotation.EntryPoint;
import com.liferay.apio.architect.annotation.Id;
import com.liferay.apio.architect.router.ActionRouter;

import org.osgi.service.component.annotations.Component;

import java.util.Arrays;
import java.util.List;


@Component
public class PersonActionRouter implements ActionRouter<Person> {

    @EntryPoint
    @Retrieve
    public List<Person> getPersons() {
        return Arrays.asList(
            Person.of(1, "Alex", "Developer"),
            Person.of(2, "David", "Developer"));
    }

    @Retrieve
    public Person getPerson(@Id long personId) {
        return Person.of(1, "Alex", "Developer");
    }

}