import java.sql.Connection;
import java.util.Scanner;

public class start {
	// Startlanding: 배달의 DB의 전체 메뉴를 소개하는 메소드
	public static void Startlanding(Connection conn,String ID) {
		System.out.println();
		System.out.println("***전체 메뉴입니다***");
        System.out.println("1. 가게 메뉴 정보 수정");
        System.out.println("2. 배달 리뷰 분석 ");
        System.out.println("3. 어플 현황 분석 ");

        System.out.println();
        Scanner sc = new Scanner(System.in);
        System.out.print("메뉴를 선택하시오 (종료하려면 0을 입력하시오) : ");
        int num = sc.nextInt();
        
     // 사용자 입력에 따라 배달의DB의 주요 기능을 구현한 객체 생성
        switch(num) {
	        case 1 : System.out.println("가게 메뉴 정보 수정이 선택되었습니다."); editStore.ESlanding(conn,ID);break;
	        case 2 : System.out.println("배달 리뷰 분석이 선택되었습니다."); reviewSort.RAlanding(conn, ID);break;
	        case 3 : System.out.println("어플 현황 분석이 선택되었습니다."); appAnal.APPlanding(conn,ID); break;
	        case 0 : System.out.println("프로그램이 종료됩니다."); break;
	        default : System.out.println("번호가 잘못 입력되었습니다.\n다시 입력하십시오."); return;
        }
		
	}
}

