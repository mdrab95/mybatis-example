package example.mybatis;

import example.mybatis.util.BasicOperations;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import example.mybatis.mapper.PersonMapper;

@SpringBootApplication
public class Application {

	private static SqlSessionFactory sqlSessionFactory;

	private static PersonMapper personMapper;

	@Autowired
	public Application(PersonMapper personMapper, SqlSessionFactory sqlSessionFactory) {
		Application.sqlSessionFactory = sqlSessionFactory;
		Application.personMapper = personMapper;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		BasicOperations bo = new BasicOperations(personMapper, sqlSessionFactory);
		bo.doExampleCrudOperations();
	}
}
