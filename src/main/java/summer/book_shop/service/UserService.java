package summer.book_shop.service;

// 메인 기능
// 1. 회원 관리, 2. 상품 관리 3. 고객센터 4. 주문
// 1.1 회원 정보 수정, 회원 삭제, 회원 가입, 회원 등급 적용
// 2.1 상품 등록, 상품 수정, 상품 삭제, 등급별 할인율 적용?, 결제 방식에 따른 할인율 적용
// 3.1 1대1 문의, 후기, 별점, 추천 등
// 4.1 주문, 주문 수정, 주문 취소

// 예외처리, 테스트 코드 작성

import org.springframework.stereotype.Service;
import summer.book_shop.domain.User;
import summer.book_shop.exception.UserException;
import summer.book_shop.exception.UserExceptionType;
import summer.book_shop.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public void signUp(User user) throws UserException {
        if (userRepository.existByUserId(user.getUserId())) {
            throw new UserException(UserExceptionType.ALREADY_EXIST_ID);
        }
        if (userInfoNotValidate(user)) {
            throw new UserException(UserExceptionType.CANNOT_REGISTER);
        }

        userRepository.save(user);
    }

    public boolean login(String userId, String password) throws UserException {
        if (!userRepository.existByUserId(userId)) {
            throw new UserException(UserExceptionType.NOT_FOUND_MEMBER);
        }

        User user = userRepository.findByUserId(userId);

        return user.getPassword().equals(password);
    }

    public void signOut(String userId, String password, String cPassword) throws UserException {
        if (!userRepository.existByUserId(userId)) {
            throw new UserException(UserExceptionType.NOT_FOUND_MEMBER);
        }
        if (!password.equals(cPassword)) {
            throw new UserException(UserExceptionType.NOT_EQUAL_PASSWORD);
        }
        User user = userRepository.findByUserId(userId);

        if (user.getPassword().equals(password)) {
            userRepository.delete(userId);
        }
    }

    public void updateUserInfo(String userId, String password) throws UserException { // 유저 정보 수정 시 인증 코드와 일치하면 수정되도록 변경 예정
        if (!userRepository.existByUserId(userId)) {
            throw new UserException(UserExceptionType.NOT_FOUND_MEMBER);
        }

        userRepository.updatePassword(userId, password);
    }

    public User getUserInfo(String userId) {
        return userRepository.findByUserId(userId);
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }

    public List<User> getAllUserInfo() {
        return userRepository.findAll();
    }

    private boolean userInfoNotValidate(User user) {
        if (user.getUserId().length() < 4 || user.getUserId().length() > 11) return false;
        if (user.getPassword().length() < 4 || user.getPassword().length() > 11) return false;
        if (user.getNickname().startsWith(" ") || user.getNickname().length() < 4) return false;
        return true;
    }
}
