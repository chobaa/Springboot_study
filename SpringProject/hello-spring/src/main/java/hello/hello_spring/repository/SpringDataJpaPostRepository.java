package hello.hello_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import hello.hello_spring.domain.Post;

public interface SpringDataJpaPostRepository extends JpaRepository<Post, Long>, PostRepository {   
}
