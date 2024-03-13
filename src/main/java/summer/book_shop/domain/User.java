package summer.book_shop.domain;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {

    private String userId; // PK
    private String password;
    private String phoneNum;
    private String nickname;
    private Date birthDate;
    private Grade grade;

    private Date createdAt;

    public User(String userId, String password, String phoneNum, String nickname) {
        this.userId = userId;
        this.password = password;
        this.phoneNum = phoneNum;
        this.nickname = nickname;
    }


}
