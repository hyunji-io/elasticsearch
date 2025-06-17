package com.example.demo;

import org.apache.catalina.User;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {
	private final UserDocumentRepository userDocumentRepository;

	public UserController(UserDocumentRepository userDocumentRepository) {
		this.userDocumentRepository = userDocumentRepository;
	}

	@PostMapping
	public UserDocument createUser(@RequestBody UserCreateRequestDto requestDto) {
		UserDocument user = new UserDocument(
			requestDto.getId(),
			requestDto.getName(),
			requestDto.getAge(),
			requestDto.getIsActive());

		return userDocumentRepository.save(user);
	}

	@GetMapping
	public Page<UserDocument> getUsers() {
		return userDocumentRepository.findAll(PageRequest.of(0, 10));
	}

	@GetMapping("/{userId}")
	public UserDocument getUser(@PathVariable String userId) {
		return userDocumentRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
	}

	@PutMapping("/{userId}")
	public UserDocument modifyUser(@PathVariable String userId, @RequestBody UserUpdateRequestDto requestDto) {
		UserDocument findUserDocument = userDocumentRepository.findById(userId)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자 입니다."));

		findUserDocument.setAge(requestDto.getAge());
		findUserDocument.setName(requestDto.getName());
		findUserDocument.setIsActive(requestDto.getIsActive());

		return userDocumentRepository.save(findUserDocument);
	}

	@DeleteMapping("/{userId}")
	public void removeUser(@PathVariable String userId) {
		UserDocument findUserDocument = userDocumentRepository.findById(userId)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자 입니다."));
		userDocumentRepository.delete(findUserDocument);
	}
}
