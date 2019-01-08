package apio.architect.example

import com.liferay.apio.architect.router.ActionRouter
import com.liferay.apio.architect.annotation.Actions.Retrieve
import com.liferay.apio.architect.annotation.EntryPoint
import com.liferay.apio.architect.annotation.Id

import org.osgi.service.component.annotations.Component

@Component
class PersonActionRouter : ActionRouter<Person> {

    @EntryPoint @Retrieve fun getPersons() =
            listOf(Person.of(1, "Alex", "Developer"), Person.of(2, "David", "Developer"))

    @Retrieve fun getPerson(@Id personId: Long) = Person.of(1, "Alex", "Developer")

}