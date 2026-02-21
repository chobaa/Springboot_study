package hello.hello_spring.service;

import hello.hello_spring.repository.PostRepository;
import hello.hello_spring.domain.Post;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Long register(Post post) {
        postRepository.save(post);
        return post.getId();
    }

    public List<Post> findPosts() {
        return postRepository.findAll();
    }

    public void delete(Long postid, String currentLoginId) {
        Optional<Post> post = postRepository.findById(postid);
        if (post.isPresent() && post.get().getAuthorLoginId().equals(currentLoginId)) {
            postRepository.delete(post.get());
        } else {
            throw new IllegalStateException("게시글 삭제 권한이 없습니다.");
        }
    }
}
