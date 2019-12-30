package example.mybatis.util;

import example.mybatis.model.Person;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import example.mybatis.mapper.PersonMapper;

import java.util.List;

public class BasicOperations {

    private static final Logger logger = LoggerFactory.getLogger(BasicOperations.class);

    private PersonMapper mapper;

    private SqlSessionFactory sqlSessionFactory;

    public BasicOperations(PersonMapper mapper, SqlSessionFactory sqlSessionFactory) {
        this.mapper = mapper;
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public void doExampleCrudOperations() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            logger.info("--------------------------");
            logger.info("Print list of people stored in the database");
            List<Person> listOfPeople;
            listOfPeople = mapper.getListOfPeople();
            printList(listOfPeople);
            logger.info("--------------------------");

            logger.info("Add Adam Malysz to the database");
            mapper.save(new Person("Adam", "Malysz"));
            listOfPeople = mapper.getListOfPeople();
            printList(listOfPeople);
            logger.info("--------------------------");

            logger.info("Update Karol Wojtyla to Jan Pawel II");
            mapper.updatePersonById(3L, new Person("Jan", "Pawel II"));
            listOfPeople = mapper.getListOfPeople();
            printList(listOfPeople);
            logger.info("--------------------------");

            logger.info("Delete Mariusz Pudzianowski from the database");
            mapper.deletePersonById(2L);
            listOfPeople = mapper.getListOfPeople();
            printList(listOfPeople);
            logger.info("--------------------------");
        }
    }

    private void printList(List<Person> list) {
        for (Person p : list) {
            logger.info(p.toString());
        }
    }
}
