package hello.hello_spring.repository;

import hello.hello_spring.domain.Post;
import java.util.Optional;
import java.util.List;

public interface PostRepository {
    Post save(Post post);

    Optional<Post> findById(Long id);

    List<Post> findAll();

    void delete(Post post);
}
