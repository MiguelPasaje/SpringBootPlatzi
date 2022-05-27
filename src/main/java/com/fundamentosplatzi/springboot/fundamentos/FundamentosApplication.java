package com.fundamentosplatzi.springboot.fundamentos;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBean;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentDependency;
import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.pojo.UserPojo;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.ILoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);

	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;

	private UserRepository userRepository;
	//constructor
	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency,  MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties myBeanWithProperties, UserPojo userPojo,UserRepository userRepository){
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
	}//primera dependencia inyectada


	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) { //method de CommandLineRunner
		//classAnterior();
		saveUserInDataBase();
		getInformationJpqlFromUsers();

	}

	private void classAnterior(){
		//ejemplos de las clases anteriores
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		System.out.println(myBeanWithProperties.funtion());
		System.out.println(userPojo.getAge() + " "+ userPojo.getEmail() + " " + userPojo.getPassword() );

		try {
			int value =10/0;
			LOGGER.debug(value);
		}catch (Exception E){
			LOGGER.error("esto es un error por dividir entre cero" + E.getMessage() + E.getStackTrace());
		}
	}

	private void saveUserInDataBase(){
		User user1 = new User("miguel","mig@gmail.com",LocalDate.of(2022,03,20));
		User user2 = new User("Julieth","juley@gmail.com",LocalDate.of(2022,04,10));
		User user3 = new User("Daniela","dan@gmail.com",LocalDate.of(2022,05,27));
		User user4 = new User("andres","andres@gmail.com",LocalDate.of(2022,01,20));
		User user5 = new User("pablo","pablo@gmail.com",LocalDate.of(2022,02,10));
		User user6 = new User("carlos","carlos@gmail.com",LocalDate.of(2022,06,27));
		User user7 = new User("kevin","kevin@gmail.com",LocalDate.of(2022,03,20));
		User user8 = new User("luz","luz@gmail.com",LocalDate.of(2022,07,10));
		User user9 = new User("harol","harol@gmail.com",LocalDate.of(2022,05,27));
		User user10 = new User("jeny","jeny@gmail.com",LocalDate.of(2022,03,20));
		User user11 = new User("juli","juli@gmail.com",LocalDate.of(2022,04,10));
		User user12 = new User("clau","clau@gmail.com",LocalDate.of(2022,05,27));
		User user13 = new User("xime","xime@gmail.com",LocalDate.of(2022,03,20));
		User user14 = new User("antony","antony@gmail.com",LocalDate.of(2022,04,10));
		User user15 = new User("lulu","lulu@gmail.com",LocalDate.of(2022,05,27));
		User user16 = new User("user1","xime@gmail.com",LocalDate.of(2022,03,20));
		User user17 = new User("user2","antony@gmail.com",LocalDate.of(2022,04,10));
		User user18 = new User("user3","lulu@gmail.com",LocalDate.of(2022,05,27));

		List<User> list = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10, user11, user12, user13, user14, user15,user16,user17,user18);

		list.stream().forEach(userRepository::save);

	}

	private void getInformationJpqlFromUsers(){
/*
		 LOGGER.info("user con method findByUserEmail" + userRepository.findByUserEmail("lulu@gmail.com")
				 .orElseThrow(()->new RuntimeException("No se encontro ningun email user")));

		 userRepository.findAndSort("j", Sort.by("id").descending()).stream()
				 .forEach(user ->  LOGGER.info("usuario con metodo sort " + user));

		 userRepository.findByName("juli")
				 .stream()
				 .forEach(user -> LOGGER.info("\n usuario con query method" + user));

		userRepository.findByName("lulu")
				.stream()
				.forEach(user -> LOGGER.info("\n usuario con query method" + user));

		LOGGER.info("user con query method findByNameAndEmail: "
				+ userRepository.findByNameAndEmail("Daniela","dan@gmail.com")
						.orElseThrow(()-> new RuntimeException("user no encontrado")));

		userRepository.findByNameLike("%u%")
				.stream()
				.forEach(user -> LOGGER.info("user con method findByNameLike ->" + user));

		userRepository.findByNameOrEmail(null,"dan@gmail.com")
				.stream()
				.forEach(user -> LOGGER.info(" \n method findByUserOrEmail ->" + user));

		userRepository.findByNameOrEmail("Daniela","dan@gmail.com")
				.stream()
				.forEach(user -> LOGGER.info(" \n method findByUserOrEmail ->" + user));
*/
		userRepository
				.findByBirthdayBetween(LocalDate.of(2022,1,1),LocalDate.of(2022,12,12))
				.stream()
				.forEach(user -> LOGGER.info("intervalo de fechas- >" + user));

		userRepository.findByNameLikeOrderByIdDesc("%user%")
				.stream()
				.forEach(user -> LOGGER.info(" \n -- user encontrado con like y ordenado : -> "+ user));

		userRepository.findByNameContainingOrderByIdDesc("user")
				.stream()
				.forEach(user -> LOGGER.info(" \n -- user encontrado con containing y ordenado : -> "+ user));
	}


}
