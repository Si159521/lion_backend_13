package spirngdatajdbc01;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class MainApplication   {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class);
    }

//    @Override
//    public void run(String... args) throws Exception {
//
//    }
    @Bean
    public Book book(){
        return new Book();
    }

    @Bean
    public CommandLineRunner demo(UserRepository userRepository, JdbcTemplate jdbcTemplate){
//        return new CommandLineRunner() {
//            @Override
//            public void run(String... args) throws Exception {
//
//            }
//        };
        //위 코드를 람다식을 이용해서 간편하게 써준다면~~
        return args -> {
            //DB와 접속해서 일하는 프레임워크는 Spring JDBC, Mybatis, JPA
            //Spring Data JDBC  --  저런 프레임워크들의 사용을 표준화 시켜주는 프레임워크

//            userRepository.save(new User("carami2","carami@gmail.com"));

//                User findUser = userRepository.findById(9L).get();
//            System.out.println(findUser);
//            System.out.println(userRepository.count());
//
//            userRepository.findAll().forEach(System.out::println);
//
//           List<User> users = jdbcTemplate.query("select * from users",new BeanPropertyRowMapper<>(User.class));
//
//           users.stream().forEach(System.out::println);
//
//            System.out.println("========================");
//           userRepository.findByName("carami222").stream()
//                   .forEach(System.out::println);
//
//            System.out.println("++++++++++++++++++++++++++++");
//            PageRequest pageRequest = PageRequest.of(1,5);
//           Page<User> pageUsers = userRepository.findAllUsersWithPagination(pageRequest);
//
//           pageUsers.forEach(System.out::println);
//

        };

    }

    @Bean
    public CommandLineRunner batchUpdateDemo(JdbcTemplate jdbcTemplate){
        return args -> {
          List<User> users = Arrays.asList(
            new User("kang","kang@exam.com"),
                  new User("kim","kim@exam.com"),
                  new User("hong","hong@exam.com"),
                  new User("lee","lee@exam.com"),
                  new User("aaa","aaa@exam.com")
          );

          //여러건을 한번에 입력하는 작업을 수행.
            String sql = "INSERT INTO users(name,email) VALUES(?,?)";
            jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    User user = users.get(i);
                    ps.setString(1, user.getName());
                    ps.setString(2,user.getEmail());
                }

                @Override
                public int getBatchSize() {
                    return users.size();
                }
            });
        };
    }

}
