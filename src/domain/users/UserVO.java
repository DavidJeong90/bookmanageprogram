package domain.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder // Builder 클래스 사용
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 모든 멤버변수 사용 생성자.
public class UserVO {

    private long userId;
    private String loginId;
    private String loginPw;
    private String userName;
    private String phone1;
    private String phone2;
    private String phone3;

}
