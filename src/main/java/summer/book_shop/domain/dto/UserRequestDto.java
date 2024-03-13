package summer.book_shop.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import summer.book_shop.domain.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

    private String userId; // PK
    private String password;
    private String phoneNum;
    private String nickname;

    public User toEntity() {
        return new User(userId, password, phoneNum, nickname);
    }
}
