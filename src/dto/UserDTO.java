package dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import domain.users.UserVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private long userId;
    private String loginId;
    private String loginPw;
    private String userName;
    private String phone1;
    private String phone2;
    private String phone3;

    public static UserVO toUserVO(UserDTO userDTO) {
        return UserVO.builder()
                .userId(userDTO.userId)
                .loginId(userDTO.loginId)
                .loginPw(userDTO.loginPw)
                .userName(userDTO.userName)
                .phone1(userDTO.phone1)
                .phone2(userDTO.phone2)
                .phone3(userDTO.phone3)
                .build();
    }

    public static UserDTO toUserDTO(UserVO userVO) {
        return UserDTO.builder()
                .userId(userVO.getUserId())
                .loginId(userVO.getLoginId())
                .loginPw(userVO.getLoginPw())
                .userName(userVO.getUserName())
                .phone1(userVO.getPhone1())
                .phone2(userVO.getPhone2())
                .phone3(userVO.getPhone3())
                .build();
    }

    // private static byte toChangeAge(int age) {
    // if (age >= 0 && age <= 127)
    // return (byte) age;
    // else
    // return 0;
    // }

    // private static Timestamp toDateTimestamp(String dateString) {
    // // 날짜 문자가 없는 경우에 0으로 생성된 Timestamp 반환.
    // if (dateString == null)
    // return new Timestamp(0); // 1970.1.1 00:00:00

    // String[] arrDayTime = dateString.split(" ");
    // String[] day = arrDayTime[0].split("-");
    // String[] time = arrDayTime[1].split(":");
    // Calendar cal = Calendar.getInstance();
    // cal.set(Integer.parseInt(day[0]), Integer.parseInt(day[1]),
    // Integer.parseInt(day[2]),
    // Integer.parseInt(time[0]), Integer.parseInt(time[1]),
    // Integer.parseInt(time[2]));
    // return new Timestamp(cal.getTimeInMillis());
    // }

    // private static String toTimestampDateString(Timestamp time) {
    // SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    // return sf.format(time);
    // }

}
