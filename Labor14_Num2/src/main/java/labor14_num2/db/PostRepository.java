package labor14_num2.db;

import labor14_num2.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
	public List<Post> findPostsByUserId (Integer id);
}
