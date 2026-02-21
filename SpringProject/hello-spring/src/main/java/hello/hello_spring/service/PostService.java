package hello.hello_spring.service;

import hello.hello_spring.repository.PostRepository;
import hello.hello_spring.domain.Post;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import java.util.NoSuchElementException;

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

    public Post findOne(Long postid) {
        return postRepository.findById(postid)
                .orElseThrow(() -> new NoSuchElementException("게시글을 찾을 수 없습니다."));
    }

    public void update(Long postid, String currentLoginId, String title, String content) {
        Post post = findOne(postid);
        if (!post.getAuthorLoginId().equals(currentLoginId)) {
            throw new IllegalStateException("게시글 수정 권한이 없습니다.");
        }
        post.setTitle(title);
        post.setContent(content);
    }

    public void delete(Long postid, String currentLoginId) {
        Post post = findOne(postid);
        if (!post.getAuthorLoginId().equals(currentLoginId)) {
            throw new IllegalStateException("게시글 삭제 권한이 없습니다.");
        }
        postRepository.delete(post);
    }
}
