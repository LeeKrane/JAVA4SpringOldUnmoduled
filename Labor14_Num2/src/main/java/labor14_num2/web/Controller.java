package labor14_num2.web;

import labor14_num2.db.PostRepository;
import labor14_num2.db.UserRepository;
import labor14_num2.exceptions.*;
import labor14_num2.model.*;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class Controller {
	private final PostRepository postRep;
	private final UserRepository userRep;
	
	@GetMapping(path = "/users")
	public List<User> getUsers () {
		return userRep.findAll();
	}
	
	@PostMapping(path = "/users")
	public ResponseEntity<User> postUser (@RequestBody User user) {
		if (user.getId() != null)
			throw new IdIsNotNullException("The user id must be null.");
		try {
			User savedUser = userRep.save(user);
		
			String path = "/users";
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().replacePath(path).build(savedUser.getId());
			return ResponseEntity.created(uri).body(savedUser);
		} catch (DataIntegrityViolationException ex) {
			if (ex.getMessage().contains("ConstraintViolationException"))
				throw new UserNameAlreadyExistsException("The given username already exists.");
			throw ex;
		}
	}
	
	@GetMapping(path = "/users/{id}")
	public User getUserById (@PathVariable Integer id) {
		if (id == null)
			throw new IdIsNullException("The user id must not be null.");
		Optional<User> userOptional = userRep.findById(id);
		
		if (userOptional.isPresent())
			return userOptional.get();
		throw new UserNotFoundException("No user with the given id has been found.");
	}
	
	@DeleteMapping(path = "/users/{id}")
	public void deleteUserById (@PathVariable Integer id) {
		if (id == null)
			throw new IdIsNullException("The user id must not be null.");
		Optional<User> userOptional = userRep.findById(id);
		
		if (userOptional.isEmpty())
			throw new UserNotFoundException("No user with the given id has been found.");
		userRep.delete(userOptional.get());
	}
	
	@GetMapping(path = "/users/{id}/posts")
	public List<Post> getPostsByUserId (@PathVariable Integer id) {
		if (id == null)
			throw new IdIsNullException("The user id must not be null.");
		return postRep.findPostsByUserId(id);
	}
	
	@PostMapping(path = "/users/{id}/posts")
	public ResponseEntity<Post> postPost (@RequestBody Post post, @PathVariable Integer id) {
		if (id == null)
			throw new IdIsNullException("The user id must not be null.");
		if (post.getId() != null)
			throw new IdIsNotNullException("The post id must be null.");
		Optional<User> optionalUser = userRep.findById(id);
		
		if (optionalUser.isEmpty())
			throw new UserNotFoundException("No user with the given id has been found.");
		post.setUser(optionalUser.get());
		Post savedPost = postRep.save(post);
		
		String path = "/users/{id}/posts";
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().replacePath(path).build(savedPost.getId());
		return ResponseEntity.created(uri).body(savedPost);
	}
	
	@GetMapping(path = "/posts/{id}")
	public Post getPostById (@PathVariable Integer id) {
		if (id == null)
			throw new IdIsNullException("The post id must not be null.");
		Optional<Post> postOptional = postRep.findById(id);
		
		if (postOptional.isPresent())
			return postOptional.get();
		throw new PostNotFoundException("No post with the given id has been found.");
	}
}