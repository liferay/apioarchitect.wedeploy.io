package apio.architect.example;

import com.liferay.apio.architect.annotation.Id;
import com.liferay.apio.architect.annotation.Vocabulary.Field;
import com.liferay.apio.architect.annotation.Vocabulary.Type;
import com.liferay.apio.architect.identifier.Identifier;

@Type("Person")
public interface Person extends Identifier<Long> {

    @Field("name")
    String getName();

    @Field("jobTitle")
    String getJobTitle();

    @Id
    long getId();

    static Person of(long id, String name, String jobTitle) {
        return new Person() {
            @Override
            public String getName() {
                return name;
            }

            @Override
            public String getJobTitle() {
                return jobTitle;
            }

            @Override
            public long getId() {
                return id;
            }
        };
    }

}