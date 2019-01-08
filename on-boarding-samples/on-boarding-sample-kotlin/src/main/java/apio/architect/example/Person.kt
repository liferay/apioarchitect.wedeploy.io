package apio.architect.example

import com.liferay.apio.architect.annotation.Id
import com.liferay.apio.architect.annotation.Vocabulary
import com.liferay.apio.architect.annotation.Vocabulary.Field
import com.liferay.apio.architect.identifier.Identifier

@Vocabulary.Type("Person")
interface Person : Identifier<Long> {

    @get:Field("name") val name: String

    @get:Field("jobTitle") val jobTitle: String

    @get:Id val id: Long

    companion object {
        fun of(id: Long, name: String, jobTitle: String) = object : Person {
            override val name: String = name

            override val jobTitle: String = jobTitle

            override val id: Long = id
        }
    }
}