package ouohoon.doby.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ouohoon.doby.post.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
