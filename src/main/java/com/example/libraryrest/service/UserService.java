package com.example.libraryrest.service;

import com.example.libraryrest.model.Book;
import com.example.libraryrest.model.User;
import com.example.libraryrest.repository.BookRepository;
import com.example.libraryrest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public UserService(UserRepository userRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public List<User> allUsers(){return userRepository.findAll();}
    public User addUser(User user) {

        return userRepository.save(user);
    }
    public void deleteUser(Long id) { userRepository.deleteById(id); }
    public User updateUser(Long id, User updatedUser) {
        User user = userRepository.findById(id).orElseThrow();
        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        return userRepository.save(user);
    }

    public String borrowBook(Long userId, Long bookId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        if (user.getBooks().size() >= 5) {
            return "Нельзя взять больше 5 книг";
        }
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Книга не найдена"));
        user.getBooks().add(book);
        userRepository.save(user);
        return "Книга успешно выдана";
    }

    public String returnBook(Long userId, Long bookId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Книга не найдена"));
        if (user.getBooks().remove(book)) {
            userRepository.save(user);
            return "Книга успешно возвращена";
        } else {
            return "У пользователя нет данной книги";
        }
    }
}
