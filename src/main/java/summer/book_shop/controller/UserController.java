package summer.book_shop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import summer.book_shop.domain.User;
import summer.book_shop.domain.dto.UserRequestDto;
import summer.book_shop.exception.UserException;
import summer.book_shop.service.UserService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/user/signup")
    public ResponseEntity signUp(@RequestBody UserRequestDto requestDto) {
        try {
            log.info("user = {}", requestDto.toEntity());
            userService.signUp(requestDto.toEntity());

        } catch (UserException e) {
            log.info(e.getExceptionType().getErrorMessage());
            return new ResponseEntity(e.getExceptionType().getErrorMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("register success!", HttpStatus.OK);
    }

    @PostMapping("/user/login")
    public ResponseEntity login(@RequestBody UserRequestDto requestDto) {
        try {
            boolean login = userService.login(requestDto.getUserId(), requestDto.getPassword());
            if (login) {
                return new ResponseEntity("login success!", HttpStatus.OK);
            }
            return new ResponseEntity("login failed!", HttpStatus.OK);
        } catch (UserException e) {
            log.info(e.getExceptionType().getErrorMessage());
            return new ResponseEntity(e.getExceptionType().getErrorMessage(), HttpStatus.BAD_GATEWAY);
        }
    }

    @PostMapping("/user/signout")
    public ResponseEntity signOut(String userId, String password, String cPassword) {
        try {
            userService.signOut(userId, password, cPassword);
            return new ResponseEntity("delete success", HttpStatus.OK);
        } catch (UserException e) {
            System.out.println(e.getExceptionType().getErrorMessage());
            return new ResponseEntity(e.getExceptionType().getErrorMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/user/update")
    public ResponseEntity update(@RequestBody UserRequestDto requestDto, String nPassword) {
        try {
            User user = userService.getUserInfo(requestDto.getUserId());

            userService.updateUserInfo(user.getUserId(), nPassword);
            return new ResponseEntity("update success", HttpStatus.OK);
        } catch (UserException e) {
            System.out.println(e.getExceptionType().getErrorMessage());
            return new ResponseEntity(e.getExceptionType().getErrorMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
