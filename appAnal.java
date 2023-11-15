import java.util.Scanner;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class appAnal {
	 // 배달의 DB 의 어플 현황 분석의 주요 기능을 소개하는 메소드
	public static void APPlanding(Connection conn,String ID) {
		System.out.println();
		System.out.println("***" + ID + " 어플 현황 분석***");
		System.out.println("0. 전체 메뉴로 가기");
        System.out.println("1. 요기요 현황 분석");
		System.out.println("2. 배달의민족 현황 분석");
        System.out.println("3. 쿠팡이츠 현황 분석");
        System.out.println("4. 모든 어플 현황 분석");
		
		Scanner sc = new Scanner(System.in);
		System.out.print("메뉴를 선택하시오 : ");
        int num = sc.nextInt();
        
      //사용자 입력에 따라 어플 현황 분석의 주요 기능 담당 메소드 호출
        switch(num) {
        case 0 : System.out.println("전체 메뉴로 가기가 선택되었습니다..");System.out.println(); start.Startlanding(conn,ID);break;
        case 1 : System.out.println("요기요 현황 분석이 선택 되었습니다.");System.out.println(); yogiyo(conn, ID);APPlanding(conn, ID);break;
        case 2 : System.out.println("배달의민족 현황 분석이 선택 되었습니다.");System.out.println(); baemin(conn,ID);APPlanding(conn, ID);break;
        case 3 : System.out.println("쿠팡이츠 현황 분석이 선택 되었습니다.");System.out.println(); coupangeats(conn, ID);APPlanding(conn, ID);break;
        case 4 : System.out.println("모든 어플 현황 분석이 선택 되었습니다.");System.out.println(); allapps(conn,ID); APPlanding(conn, ID);break;
        default : System.out.println("번호가 잘못 입력되었습니다.\n다시 입력하십시오."); return;
        }
        
 }
	  // 요기요 어플의 배달 현황을 분석하는 메소드
	   public static void yogiyo(Connection conn,String ID) {
	      // TODO Auto-generated method stub
		// 요기요 어플의 배달 현황을 조회하는 쿼리
	      String query = "select 고객아이디,리뷰평점,배달소요시간,긍부정,주문일자,리뷰내용 from DB2022_배달 natural join DB2022_리뷰 where 식당이름=? and 어플=?";
	      // 요기요 어플의 배달 매출을 조회하는 쿼리
	      String querySales = "select 어플, sum(주문금액) as 총매출, count(*) as 리뷰개수 from DB2022_yogiyo where 식당이름=?";
	      
	      Scanner sc = new Scanner(System.in);
	      
	      try {
	            PreparedStatement pStmt = conn.prepareStatement(query); //사용자에게 배달소요시간을 입력받을 preparedStatement 생성
	            pStmt.setString(1, ID);
	            pStmt.setString(2,"요기요");
	            ResultSet rs1 = pStmt.executeQuery();
	            
	            
	            PreparedStatement ppStmt = conn.prepareStatement(querySales);
	            ppStmt.setString(1, ID);
	            ResultSet rs2 = ppStmt.executeQuery();
	               
	            //select 고객아이디,리뷰평점,배달소요시간,긍부정,어플,주문일자,리뷰내용
	            System.out.printf("%-20s| %-20s| %-20s| %-20s| %-20s | %-20s ","고객아이디","리뷰평점","배달소요시간","긍부정","주문일자","리뷰내용");
	            System.out.println();
	            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
	            while (rs1.next()) { //이름, 배달소요시간, 리뷰, 평점, 고객아이디, 주문일자를 출력
	               String Id = rs1.getString(1);
	               int Score = rs1.getInt(2);
	               int Time = rs1.getInt(3);
	               int Yn = rs1.getInt(4);
	               Timestamp Date = rs1.getTimestamp(5);
	               String Review = rs1.getString(6);
	               System.out.printf("%-20s  | %-20d  | %-20d  | %-20d| %-20s  | %-20s  ",Id,Score,Time,Yn,Date,Review);
	               System.out.println();
	            }

	            System.out.println();
	            System.out.println("어플"+"\t"+"| 총매출"+"\t       "+"| 리뷰개수"+"\t");
	            System.out.println("--------------------------------");
	            while (rs2.next()) {
	            	String app = rs2.getString(1);
	            	int totalsales = rs2.getInt(2);
	            	int totalreviewnum = rs2.getInt(3);
	            	System.out.println(app+"\t"+"| "+totalsales+"\t"+"| "+totalreviewnum+"\t");
	            }
	            
	               
	         }catch(SQLException se) {
	            System.out.println("오류");}
	      
	      }
	   
	   // 배달의민족 어플의 배달 현황을 분석하는 메소드
	   public static void baemin(Connection conn,String ID) {
		      // TODO Auto-generated method stub
		// 배달의 민족 어플의 배달 현황을 조회하는 쿼리
		      String query = "select 고객아이디,리뷰평점,배달소요시간,긍부정,주문일자,리뷰내용 from DB2022_배달 natural join DB2022_리뷰 where 식당이름=? and 어플=?";
		   // 배달의 민족 어플의 배달 매출을 조회하는 쿼리
		      String querySales = "select 어플, sum(주문금액) as 총매출, count(*) as 리뷰개수 from DB2022_baemin where 식당이름=?";
		      
		      Scanner sc = new Scanner(System.in);
		      
		      try {
		            PreparedStatement pStmt = conn.prepareStatement(query); //사용자에게 배달소요시간을 입력받을 preparedStatement 생성
		            pStmt.setString(1, ID);
		            pStmt.setString(2,"배달의민족");
		            ResultSet rs1 = pStmt.executeQuery();
		            
		            
		            PreparedStatement ppStmt = conn.prepareStatement(querySales);
		            ppStmt.setString(1, ID);
		            ResultSet rs2 = ppStmt.executeQuery();
		               
		            //select 고객아이디,리뷰평점,배달소요시간,긍부정,어플,주문일자,리뷰내용
		            System.out.printf("%-20s| %-20s| %-20s| %-20s| %-20s | %-20s ","고객아이디","리뷰평점","배달소요시간","긍부정","주문일자","리뷰내용");
		            System.out.println();
		            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		            while (rs1.next()) { //어플 이름, 고객아이디, 평점, 배달소요시간, 긍부정, 주문일자, 리뷰를 출력
		               String Id = rs1.getString(1);
		               int Score = rs1.getInt(2);
		               int Time = rs1.getInt(3);
		               int Yn = rs1.getInt(4);
		               Timestamp Date = rs1.getTimestamp(5);
		               String Review = rs1.getString(6);
		               System.out.printf("%-20s  | %-20d  | %-20d  | %-20d| %-20s  | %-20s  ",Id,Score,Time,Yn,Date,Review);
		               System.out.println();
		            }

		            System.out.println();
		            System.out.println("어플"+"\t"+"| 총매출"+"\t       "+"| 리뷰개수"+"\t");
		            System.out.println("--------------------------------");
		            while (rs2.next()) {
		            	String app = rs2.getString(1);
		            	int totalsales = rs2.getInt(2);
		            	int totalreviewnum = rs2.getInt(3);
		            	System.out.println(app+"\t"+"| "+totalsales+"\t"+"| "+totalreviewnum+"\t");
		            
		            }
		            
		               
		         }catch(SQLException se) {
		            System.out.println("오류");}
		      
		      }
	// 쿠팡이츠 어플의 배달 현황을 분석하는 메소드
	   public static void coupangeats(Connection conn,String ID) {
		      // TODO Auto-generated method stub
		// 쿠팡이츠 어플의 배달 현황을 조회하는 쿼리
		      String query = "select 고객아이디,리뷰평점,배달소요시간,긍부정,주문일자,리뷰내용 from DB2022_배달 natural join DB2022_리뷰 where 식당이름=? and 어플=?";
		      // 쿠팡이츠 어플의 배달 매출을 조회하는 쿼리
		      String querySales = "select 어플, sum(주문금액) as 총매출, count(*) as 리뷰개수 from DB2022_coupangeats where 식당이름=?";
		      
		      Scanner sc = new Scanner(System.in);
		      
		      try {
		            PreparedStatement pStmt = conn.prepareStatement(query); //사용자에게 배달소요시간을 입력받을 preparedStatement 생성
		            pStmt.setString(1, ID);
		            pStmt.setString(2,"쿠팡이츠");
		            ResultSet rs1 = pStmt.executeQuery();
		            
		            
		            PreparedStatement ppStmt = conn.prepareStatement(querySales);
		            ppStmt.setString(1, ID);
		            ResultSet rs2 = ppStmt.executeQuery();
		               
		            //select 고객아이디,리뷰평점,배달소요시간,긍부정,어플,주문일자,리뷰내용
		            System.out.printf("%-20s| %-20s| %-20s| %-20s| %-20s | %-20s ","고객아이디","리뷰평점","배달소요시간","긍부정","주문일자","리뷰내용");
		            System.out.println();
		            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		            while (rs1.next()) { //어플 이름, 고객아이디, 평점, 배달소요시간, 긍부정, 주문일자, 리뷰를 출력
		               String Id = rs1.getString(1);
		               int Score = rs1.getInt(2);
		               int Time = rs1.getInt(3);
		               int Yn = rs1.getInt(4);
		               Timestamp Date = rs1.getTimestamp(5);
		               String Review = rs1.getString(6);
		               System.out.printf("%-20s  | %-20d  | %-20d  | %-20d| %-20s  | %-20s  ",Id,Score,Time,Yn,Date,Review);
		               System.out.println();
		            }

		            System.out.println();
		            System.out.println("어플"+"\t"+"| 총매출"+"\t       "+"| 리뷰개수"+"\t");
		            System.out.println("--------------------------------");
		            while (rs2.next()) {
		            	String app = rs2.getString(1);
		            	int totalsales = rs2.getInt(2);
		            	int totalreviewnum = rs2.getInt(3);
		            	System.out.println(app+"\t"+"| "+totalsales+"\t"+"| "+totalreviewnum+"\t");
		            }
		            
		               
		         }catch(SQLException se) {
		            System.out.println("오류");}
		      
		      }
	   
	// 모든 종류의 어플 배달 현황을 분석하는 메소드
	   public static void allapps(Connection conn,String ID) {
		      // TODO Auto-generated method stub
		// 모든 어플의 배달 현황을 조회하는 쿼리
		      String query = "select 어플,고객아이디,리뷰평점,배달소요시간,긍부정,주문일자,리뷰내용 from DB2022_배달 natural join DB2022_리뷰 where 식당이름=? order by 어플";
		   // 모든 어플의 배달 매출을 조회하는 쿼리 
		      String querySales = "select 식당이름, 어플, sum(주문금액) as 총매출, count(*) as 리뷰개수 from (select 식당이름, 어플, 주문금액 from db2022_sales_app where 식당이름=?) new group by 어플";
	
		      Scanner sc = new Scanner(System.in);
		      
		      try {
		            PreparedStatement pStmt = conn.prepareStatement(query); //사용자에게 배달소요시간을 입력받을 preparedStatement 생성
		            pStmt.setString(1, ID);
		            ResultSet rs1 = pStmt.executeQuery();
		            
		            
		            PreparedStatement ppStmt = conn.prepareStatement(querySales);
		            ppStmt.setString(1, ID);
		            ResultSet rs2 = ppStmt.executeQuery();
		               
		            //select 고객아이디,리뷰평점,배달소요시간,긍부정,어플,주문일자,리뷰내용
		            System.out.printf("%-20s| %-20s| %-20s| %-20s| %-20s| %-20s | %-20s ","어플","고객아이디","리뷰평점","배달소요시간","긍부정","주문일자","리뷰내용");
		            System.out.println();
		            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		            while (rs1.next()) { //어플 이름, 고객아이디, 평점, 배달소요시간, 긍부정, 주문일자, 리뷰를 출력
		            	String app = rs1.getString(1);
		               String Id = rs1.getString(2);
		               int Score = rs1.getInt(3);
		               int Time = rs1.getInt(4);
		               int Yn = rs1.getInt(5);
		               Timestamp Date = rs1.getTimestamp(6);
		               String Review = rs1.getString(7);
		               System.out.printf("%-20s  | %-20s  | %-20d  | %-20d  | %-20d| %-20s  | %-20s  ",app,Id,Score,Time,Yn,Date,Review);
		               System.out.println();
		            }
		            System.out.println();
		            System.out.printf("%-20s  | %-20s  | %-20s  | %-20s  ","가게이름","어플","총매출","리뷰개수");
		            System.out.println();
		            System.out.println("--------------------------------------------------------------------------------------------");
		            
		            while (rs2.next()) {
		            	String store=rs2.getString(1);
		            	String app = rs2.getString(2);
		            	int totalsales = rs2.getInt(3);
		            	int totalreviewnum = rs2.getInt(4);
		            	System.out.printf("%-20s  | %-20s  | %-20d  | %-20d  ",store,app,totalsales,totalreviewnum);
			            System.out.println();
		            }
		            
		               
		         }catch(SQLException se) {
		            System.out.println("오류");}
		      
		      }
	
}
