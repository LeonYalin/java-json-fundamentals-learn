package com.leony.home;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ProducingJSON {
    public void manualJSONCreation() {
        List<Person> persons = Arrays.asList(PersonFixtures.me, PersonFixtures.nelly, PersonFixtures.lisa);
        System.out.println("using manual conversion to convert a person to JSON string:\n" + toJSONString(PersonFixtures.me));
        System.out.println("\nusing manual conversion to convert a list of persons to JSON string:\n" + toJSONString(persons));
    }

    private CharSequence toJSONString(Person person) {
        return String.format(
                "{\n" +
                        "\tname: %s,\n" +
                        "\tage: %S\n" +
                 "}",
                person.getName(), person.getAge());
    }

    private CharSequence toJSONString(List<Person> persons) {
        return persons.stream().map(this::toJSONString).collect(Collectors.joining(",\n", "[", "]"));
    }

    /**
     * This uses jackson-core JAR file.
     */
    public void usingGeneratorAPI() throws IOException {
        JsonFactory jsonFactory = new JsonFactory();
        JsonGenerator generator = jsonFactory.createGenerator(System.out);
        generator.setPrettyPrinter(new DefaultPrettyPrinter());

        generator.writeStartObject();

        // add some fields
        generator.writeStringField("name", PersonFixtures.me.getName());
        generator.writeNumberField("age", PersonFixtures.me.getAge());

        // add nested object
        generator.writeFieldName("config");
        generator.writeStartObject();
        generator.writeBooleanField("live", true);
        generator.writeStringField("happy", "yes");
        generator.writeEndObject();

        // add array
        generator.writeFieldName("hobbies");
        generator.writeArray(new int[] {1, 2, 3}, 0, 3);
        generator.writeFieldName("pets");
        generator.writeStartArray();
        generator.writeString("dog");
        generator.writeString("cat");
        generator.writeString("rat");
        generator.writeEndArray();

        generator.writeEndObject();
        generator.flush();
        System.out.println();
    }

    public void usingBindingAPI() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter writer = objectMapper.writerWithDefaultPrettyPrinter();
        List<Person> persons = Arrays.asList(PersonFixtures.me, PersonFixtures.nelly, PersonFixtures.lisa);
        writer.writeValue(System.out, persons);

        // or

        // String personsJSONString = writer.writeValueAsString(persons);
        // System.out.println(personsJSONString);
    }
}
