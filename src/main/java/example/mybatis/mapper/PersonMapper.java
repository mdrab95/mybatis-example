package example.mybatis.mapper;

import example.mybatis.model.Person;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface PersonMapper {
    @Select("SELECT * FROM PEOPLE WHERE id = #{id}")
    Person getPerson(@Param("id") Long id);

    @Insert("INSERT INTO PEOPLE(id, firstName, lastName) VALUES(#{id}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void save(Person person);

    @Update("UPDATE PEOPLE SET firstName=#{person.firstName}, lastName=#{person.lastName} WHERE id=#{personId}")
    void updatePersonById(@Param("personId") Long personId, Person person);

    @Delete("DELETE FROM PEOPLE WHERE id=#{id}")
    void deletePersonById(@Param("id") Long id);

    @Select("SELECT * FROM PEOPLE")
    List<Person> getListOfPeople();
}