import java.util.List;
import java.util.Scanner;

import controller.BookProgramController;
import dto.BookDTO;
import dto.RentDTO;
import dto.UserDTO;

public class App {

    // 사용자 입력을 위한 도구
    private static Scanner scanner = new Scanner(System.in, "cp949");
    // Controller 레이어
    private static BookProgramController controller = new BookProgramController();

    // 로그인 정보를 저장한 변수
    private static UserDTO userInfo = null; // 로그인하면 정보를 추가, 로그아웃하면 null로 변경.

    public static void main(String[] args) throws Exception {
        System.out.println("[고객 주문 관리 프로그램]");
        menu();
    }

    public static void menu() throws Exception { // 메인 메뉴(View)
        while (true) {
            System.out.println("1. 회원 관리");
            System.out.println("2. 도서 관리");
            System.out.println("0. 종료");
            System.out.print("메뉴 선택 : ");
            char choice = scanner.next().charAt(0);
            switch (choice) {
                case '1':
                    // 회원 가입, 로그인 정보를 출력하는 메뉴 메서드 호출
                    System.out.println("회원 관리 하위 메뉴");
                    userManageMenu();
                    break;
                case '2':
                    // 도서관리메뉴
                    System.out.println("도서 관리 하위 메뉴");
                    bookMenu();
                    break;
                case '0':
                    System.out.println("프로그램을 종료합니다.");
                    scanner.close();
                    return; // 프로세스 종료
                default:
                    System.out.println("메뉴 선택이 잘못됐습니다. 다시 입력해주세요.");
                    break;
            }
        }

    }

    public static void userManageMenu() {

        while (true) {
            System.out.println("1) 회원 가입");
            System.out.println("2) 로그인");
            System.out.println("0) 메인으로 이동");
            System.out.print("메뉴 선택 : ");
            char choice = scanner.next().charAt(0);
            switch (choice) {
                case '1':
                    // 회원 가입 정보 입력 메서드 호출
                    System.out.println("회원 가입 정보 처리 메서드");
                    joinUser();
                    break;
                case '2':
                    // 로그인 처리 메서드 호출
                    System.out.println("로그인 처리 메서드");
                    login();
                    break;
                case '0':
                    System.out.println("메인으로 이동합니다. ");
                    return;
                default:
                    System.out.println("메뉴 선택이 잘못됐습니다. 다시 입력해주세요.");
                    break;
            }
        }
    }

    public static void joinUser() {

        while (true) {
            System.out.println("[회원 가입 정보 처리]");
            System.out.print("사용자 ID를 입력하세요 : ");
            String loginId = scanner.next();
            System.out.print("사용자 PW를 입력하세요 : ");
            String loginPw = scanner.next();
            scanner.nextLine(); // scanner 버퍼 정리
            System.out.print("사용자 이름를 입력하세요 : ");
            String userName = scanner.nextLine();
            System.out.print("사용자 전화번호를 입력하세요 : ");
            String userPhone = scanner.nextLine();
            System.out.println("[입력한 정보를 확인]");
            System.out.println("사용자 ID : " + loginId);
            System.out.println("사용자 PW : " + loginPw);
            System.out.println("사용자 이름 : " + userName);
            System.out.println("사용자 전화번호 : " + userPhone);
            System.out.print("입력한 정보를 회원 가입하시겠습니까?(y/n)");
            char done = scanner.next().toLowerCase().charAt(0);
            System.out.println(done);
            if (done == 'y') {
                // 회원 가입 처리(controller)
                boolean status = controller.join(loginId, loginPw, userName, userPhone);
                if (status)
                    return; // 회원 가입 메뉴 나가고, 실패면, 다시 while문으로
                else
                    System.out.println("회원 가입 실패했습니다.");
            }
        }
    }

    public static void login() {
        while (true) {
            System.out.println("[회원 로그인 처리]");
            System.out.print("사용자 ID를 입력하세요 : ");
            String userId = scanner.next();
            System.out.print("사용자 PW를 입력하세요 : ");
            String userPw = scanner.next();
            System.out.print("로그인 하시겠습니까? y/n ");
            scanner.nextLine();
            char done = scanner.nextLine().toLowerCase().charAt(0);
            if (done == 'y') {
                // controller를 통한 login 작업
                // 로그인 성공시 : 정보 확인, 수정, 탈퇴 메뉴를 연결 - 메서드
                // 로그인 실패시 : 아이디 또는 패스워드가 다릅니다.
                // 다시 입력 반복(계속여부확인)
                userInfo = controller.login(userId, userPw);
                // userInfo에 로그인 정보를 저장하고, userInfo의 내용이 있는지 검증
                // userInfo는 로그인 상태 정보를 저장.
                if (!userInfo.getLoginId().isEmpty()) {
                    userManage();
                } else {
                    System.out.println("아이디 또는 패스워드가 다릅니다.");
                }
            }
            System.out.print("이전 메뉴로 이동하겠습니까? (y/n) ");
            done = scanner.next().toLowerCase().charAt(0);
            if (done == 'y') {
                return;
            }

        }
    }

    public static void userManage() {
        while (true) {
            // controller를 통해서 정보를 입력
            System.out.println("1) 로그인 정보 확인");
            System.out.println("2) 로그인 정보 수정");
            System.out.println("3) 회원 탈퇴");
            System.out.println("0) 이전 메뉴로 이동");
            System.out.print("메뉴 선택 : ");
            char choice = scanner.next().charAt(0);
            switch (choice) {
                case '1':
                    // 회원 정보 출력
                    System.out.println("[회원 정보 확인]");
                    System.out.println("사용자 ID : " + userInfo.getLoginId());
                    System.out.println("사용자 PW : " + "*******");
                    System.out.println("사용자 이름 : " + userInfo.getUserName());
                    System.out.println("사용자 전화번호 : "
                            + userInfo.getPhone1() + "-" + userInfo.getPhone2() + "-" + userInfo.getPhone3());
                    break;
                case '2':
                    // 회원 정보 출력 후 수정
                    System.out.println("[회원 정보 확인 수정]");
                    System.out.println("사용자 ID : " + userInfo.getLoginId());
                    // 원래 패스워드 수정은 별로의 로직으로 구성해야 합니다.
                    System.out.printf("사용자 PW(%s) : ", userInfo.getLoginPw());
                    String userPw = scanner.next();
                    scanner.nextLine();
                    System.out.printf("사용자 이름(%s) : ", userInfo.getUserName());
                    String userName = scanner.nextLine();
                    System.out.printf("사용자 전화번호(%s-%s-%s) : ",
                            userInfo.getPhone1(), userInfo.getPhone2(), userInfo.getPhone3());
                    String userPhone = scanner.nextLine();
                    boolean status = controller.userModify(userInfo.getUserId(), userInfo.getLoginId(), userPw,
                            userName, userPhone);
                    if (status) {
                        System.out.println("회원 정보가 수정되었습니다.");
                        // 회원 정보 갱신 처리
                        userInfo = controller.userInfo(userInfo.getLoginId());
                    } else
                        System.out.println("회원 정보 수정 실패했습니다.");
                    break;
                case '3':
                    // 회원 정보 출력 후 삭제
                    System.out.println("[회원 정보 확인]");
                    System.out.println("사용자 ID : " + userInfo.getUserId());
                    System.out.print("회원 탈퇴하시겠습니까?(y/n)");
                    char done = scanner.nextLine().toLowerCase().charAt(0);
                    if (done == 'y') {
                        System.out.print("사용자 PW() : ");
                        String pw = scanner.next();
                        scanner.nextLine(); // 버퍼문제... 위에서 choice에 에러는 여기
                        // 회원 정보 넣어서 보낼 Pw
                        // 회원 정보 삭제를 위한 확인 처리할 패스워드. 변경해서
                        System.out.println("입력한 패스워드 : " + pw);
                        userInfo.setLoginPw(pw);
                        System.out.println("userInfo의 패스워드 : " + userInfo.getLoginPw());
                        status = controller.revokeUser(userInfo);
                        if (status) {// 회원 탈퇴
                            System.out.println("회원 탈퇴 성공했습니다.");
                            // 1. userInfo을 정리 => null
                            userInfo = null;
                            // 2. 이전 메뉴로 이동.
                            return;
                        } else {// 회원 탈퇴 실패
                            System.out.println("회원 탈퇴 실패했습니다.");
                        }
                    }
                    break;
                case '0':
                    System.out.println("이전 메뉴로 이동합니다.");
                    return;
                default:
                    System.out.println("메뉴 선택이 잘못됐습니다. 다시 입력해주세요.");
                    break;
            }
        }
    }

    public static void bookMenu() {
        while (true) {
            System.out.println("1) 도서 등록");
            System.out.println("2) 도서 조회");
            System.out.println("3) 도서 대여/반납");
            System.out.println("0) 이전 메뉴");
            System.out.print("메뉴 선택 : ");
            char choice = scanner.next().charAt(0);
            switch (choice) {
                case '1':
                    System.out.println("[도서 등록]");
                    addBook();
                    break;
                case '2':
                    System.out.println("[도서 조회]");
                    searchBook();
                    break;
                case '3':
                    System.out.println("[도서 대여/반납]");
                    rentManage();
                    break;
                case '0':
                    System.out.println("이전 메뉴로 이동합니다.");
                    return;
                default:
                    System.out.println("메뉴 선택이 잘못됐습니다. 다시 선택하세요.");
                    break;
            }
        }
    }

    public static void addBook() {

        while (true) {
            System.out.println("[등록 도서 정보]");
            System.out.print("도서명을 입력하세요 : ");
            String title = scanner.nextLine();
            System.out.print("저자명을 입력하세요 : ");
            String author = scanner.nextLine();
            System.out.println("[입력한 정보를 확인]");
            System.out.println("도서명 : " + title);
            System.out.println("저자명 : " + author);
            System.out.print("입력한 정보가 맞습니까?(y/n)");
            char done = scanner.next().toLowerCase().charAt(0);
            System.out.println(done);
            if (done == 'y') {
                if (title.length() < 1) {
                    System.out.println("도서명은 필수항목입니다.");
                    return;
                } else {
                    boolean status = controller.addBook(title, author);
                    if (status)
                        return;
                    else
                        System.out.println("도서 등록에 실패했습니다.");
                }
            }
        }
    }

    public static void searchBook() {

        System.out.println("[등록 도서 조회]");
        List<BookDTO> bookList = controller.BookList();
        if (!bookList.isEmpty()) {
            for (int i = 0; i < bookList.size(); i++) {
                System.out.println("[도서 정보]");
                System.out.println("도서명: " + bookList.get(i).getTitle());
                System.out.println("저자명: " + bookList.get(i).getAuthor());
                if (bookList.get(i).getState().equals("Y"))
                    System.out.println("대여 가능 여부: 가능");
                else
                    System.out.println("대여 가능 여부: 불가능");
                System.out.println("");
            }
        } else {
            System.out.println("도서 조회에 실패했습니다.");
        }
    }

    public static void rentManage() {
        if (userInfo == null || userInfo.getUserId() == -1) {
            System.out.println("로그인한 사용자만 사용할 수 있어요. ");
            login();
            return;
        }
        int status = 0;
        List<RentDTO> list = null;
        while (true) {
            System.out.println("1) 도서 대여");
            System.out.println("2) 도서 반납");
            System.out.println("0) 이전 메뉴로");
            System.out.print("메뉴 선택 : ");
            char choice = scanner.next().charAt(0);
            switch (choice) {
                case '1':
                    System.out.println("[도서 대여]");
                    System.out.print("도서명 :");
                    scanner.nextLine();
                    String title = scanner.nextLine();
                    // controller에서 메뉴 처리...
                    status = controller.rentBook(title, userInfo.getUserId());
                    if (status == 1)
                        System.out.println("도서 대여 성공");
                    else
                        System.out.println("도서 대여 실패");
                    break;
                case '2':
                    System.out.println("[도서 반납]");
                    System.out.print("도서명 :");
                    scanner.nextLine();
                    title = scanner.nextLine();
                    status = controller.returnBook(title, userInfo.getUserId());
                    if (status == 1)
                        System.out.println("도서 반납 성공");
                    else
                        System.out.println("도서 반납 실패");
                    break;
                case '0':
                    System.out.println("이전 메뉴로 이동합니다.");
                    return;
                default:
                    System.out.println("메뉴 선택이 잘못됐습니다. 다시 선택하세요.");
                    break;
            }

        }
    }

}
