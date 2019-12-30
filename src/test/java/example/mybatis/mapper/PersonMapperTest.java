package example.mybatis.mapper;

import example.mybatis.model.Person;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class PersonMapperTest {

    private PersonMapper personMapper;

    @Mock
    private SqlSessionFactory sqlSessionFactory;

    @Mock
    private SqlSession sqlSession;

    @Autowired
    PersonMapperTest(PersonMapper personMapper, SqlSessionFactory sqlSessionFactory) {
        this.personMapper = personMapper;
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Test
    void shouldReturnPersonWithGivenIdIfDatabaseIsNotEmpty() {
        when(sqlSessionFactory.openSession()).thenReturn(sqlSession);
        when(sqlSession.getMapper(PersonMapper.class)).thenReturn(personMapper);

        Person person = personMapper.getPerson(1L);

        assertThat(person).isNotNull();
        assertThat(person.getId()).isEqualTo(1L);
        assertThat(person.getFirstName()).isEqualTo("Krzysztof");
        assertThat(person.getLastName()).isEqualTo("Krawczyk");
    }

    @Test
    void shouldAddPerson() {
        when(sqlSessionFactory.openSession()).thenReturn(sqlSession);
        when(sqlSession.getMapper(PersonMapper.class)).thenReturn(personMapper);

        personMapper.save(new Person("Adam", "Malysz"));
        Person savedPerson = personMapper.getPerson(4L);

        assertThat(savedPerson.getId()).isEqualTo(4L);
        assertThat(savedPerson.getFirstName()).isEqualTo("Adam");
        assertThat(savedPerson.getLastName()).isEqualTo("Malysz");
    }

    @Test
    void shouldUpdatePersonIfDatabaseIsNotEmpty() {
        when(sqlSessionFactory.openSession()).thenReturn(sqlSession);
        when(sqlSession.getMapper(PersonMapper.class)).thenReturn(personMapper);

        personMapper.updatePersonById(3L, new Person("Jan", "Pawel II"));
        Person updatedPerson = personMapper.getPerson(3L);

        assertThat(updatedPerson.getId()).isEqualTo(3L);
        assertThat(updatedPerson.getFirstName()).isEqualTo("Jan");
        assertThat(updatedPerson.getLastName()).isEqualTo("Pawel II");
    }

    @Test
    void shouldDeletePersonIfDatabaseIsNotEmpty() {
        when(sqlSessionFactory.openSession()).thenReturn(sqlSession);
        when(sqlSession.getMapper(PersonMapper.class)).thenReturn(personMapper);

        personMapper.deletePersonById(2L);
        Optional<Person> person = Optional.ofNullable(personMapper.getPerson(2L));

        assertThat(person).isEqualTo(Optional.empty());
    }
}
