import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import com.mysql.cj.protocol.PacketSentTimeHolder;

public class reviewSort {
	//배달의 DB의 배달 리뷰 분석의 주요 기능을 소개하는 메소드
   public static void RAlanding(Connection conn,String ID) {
      System.out.println();
      System.out.println("***" + ID + " 배달 리뷰 분석***");

        System.out.println("0. 전체 메뉴로 가기");
        System.out.println("1. 평점별 리뷰 조회");
        System.out.println("2. 기간별 리뷰 조회");
        System.out.println("3. 배달소요시간별 리뷰 조회");
        System.out.println("4. 긍부정별 리뷰 조회");
        System.out.println("5. 단골 손님 조회");
        System.out.println("6. 전체 리뷰 분석");


        System.out.println();
        Scanner sc = new Scanner(System.in);
        System.out.print("메뉴를 선택하세요 : ");
        int num = sc.nextInt();
        
    	//배달 리뷰 분석의 세부 기능을 담당하는 메소드 호출
        switch(num) {
        case 0 : System.out.println("전체 메뉴로 가기가 선택되었습니다..");System.out.println(); start.Startlanding(conn,ID);break;
        case 1 : System.out.println("평점별 리뷰 조회가 선택되었습니다.");System.out.println(); grade(conn, ID);RAlanding(conn, ID);break;
        case 2 : System.out.println("기간별 리뷰 조회가 선택되었습니다.");System.out.println();gap(conn,ID);RAlanding(conn, ID);break;
        case 3 : System.out.println("배달소요시간별 리뷰 조회가 선택되었습니다.");System.out.println(); deliveryTime(conn, ID);RAlanding(conn, ID);break;
        case 4 : System.out.println("긍부정별 리뷰 조회가 선택되었습니다.");System.out.println(); posneg(conn,ID); RAlanding(conn, ID);break;
        case 5 : System.out.println("단골 손님 조회가 선택되었습니다.");System.out.println(); regular(conn, ID);RAlanding(conn, ID);break;
        case 6 : System.out.println("전체 리뷰 조회가 선택되었습니다.");System.out.println(); totalreview(conn, ID);RAlanding(conn, ID);break;
        default : System.out.println("번호가 잘못 입력되었습니다.\n다시 입력하십시오."); return;
        }
      
   }
   
 //단골 손님 조회하는 메소드
   public static void regular(Connection conn,String ID) { 
      // TODO Auto-generated method stub
      
	   //from 내부 쿼리: 리뷰 릴레이션에서 고객아이디로 그룹화하여 고객별주문횟수를 db2022_단골손님으로 select
	   //외부 쿼리: from으로 만들어진 db2022_단골손님으로부터 고객별주문횟수가 2 이상인 손님(단골손님이라 정의)의 고객아이디를 보여줌 
      String query = "select 고객아이디 from (select 고객아이디, count(고객아이디) as 고객별주문횟수 from db2022_리뷰 where 식당이름=? group by 고객아이디) db2022_단골손님 where 고객별주문횟수>=2;";

      try {
            PreparedStatement pStmt = conn.prepareStatement(query);
            pStmt.setString(1, ID);
            
            ResultSet rs1 = pStmt.executeQuery();
        
            System.out.println("단골 손님 명단입니다.");
            System.out.println("고객아이디");
            System.out.println("------------");
            while (rs1.next()) {
               String client = rs1.getString(1);
               System.out.println(client);
            }
               
         }catch(SQLException se) {
            System.out.println("오류");}
      
      }
   
 //긍부정을 조회하는 메소드
   public static void posneg(Connection conn,String ID) {
      // TODO Auto-generated method stub
      
	   //배달 릴레이션과 리뷰 릴레이션을 natural join
	   //메소드의 parameter인 ID를 첫번재 ?에, 사용자에게 입력받을 긍부정 반응을 두번째 ?에 셋팅
	   //콘솔창에서 사용자에게 보여줄 리뷰 관련 내용들 select 
      String query = "select 고객아이디,리뷰평점,배달소요시간,긍부정,어플,주문일자,리뷰내용 from DB2022_배달 natural join DB2022_리뷰 where 식당이름=? and 긍부정=?";

      Scanner sc=new Scanner(System.in);
      System.out.println("조회를 원하는 반응을 입력하시오. 긍=1, 부=0");
      int reaction = sc.nextInt();
      try {
            PreparedStatement pStmt = conn.prepareStatement(query); 
            pStmt.setString(1, ID);
            pStmt.setInt(2, reaction);
            
            if (reaction == 1)
               System.out.println("긍정 리뷰입니다.");
            else 
               System.out.println("부정 리뷰입니다.");
               
            ResultSet rs1 = pStmt.executeQuery();
            
            System.out.printf("%-20s| %-20s| %-20s| %-20s| %-20s  | %-20s | %-20s ","고객아이디","리뷰평점","배달소요시간","긍부정","어플","주문일자","리뷰내용");
            System.out.println();
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            while (rs1.next()) { 
               String Id = rs1.getString(1);
               int Score = rs1.getInt(2);
               int Time = rs1.getInt(3);
               int Yn = rs1.getInt(4);
               String App = rs1.getString(5);
               Timestamp Date = rs1.getTimestamp(6);
               String Review = rs1.getString(7);
               System.out.printf("%-20s  | %-20d  | %-20d  | %-20d  | %-20s  | %-20s  | %-20s  ",Id,Score,Time,Yn,App,Date,Review);
               System.out.println();
            }
            
               
         }catch(SQLException se) {
            System.out.println("오류");}
      
      }
   //별점을 조회하는 메소드
   public static void grade(Connection conn,String ID) { 
      // TODO Auto-generated method stub
	   
	 //배달 릴레이션과 리뷰 릴레이션을 natural join
	   //메소드의 parameter인 ID를 첫번재 ?에, 사용자에게 입력받을 리뷰평점을 두번째 ?에 셋팅
	   //콘솔창에서 사용자에게 보여줄 리뷰 관련 내용들 select 
      String query = "select 고객아이디,리뷰평점,배달소요시간,긍부정,어플,주문일자,리뷰내용 from DB2022_배달 natural join DB2022_리뷰 where 식당이름=? and 리뷰평점=?";
      Scanner sc= new Scanner(System.in);
      System.out.println("조회를 원하는 평점을 입력하시오.(1~5)");
      int grade=sc.nextInt();
      try {
            PreparedStatement pStmt = conn.prepareStatement(query); //사용자에게 배달소요시간을 입력받을 preparedStatement 생성
            pStmt.setString(1, ID);
            pStmt.setInt(2, grade);
             
            System.out.println("평점이 "+grade+"인 리뷰입니다.");   
            
            ResultSet rs1 = pStmt.executeQuery();
            //select 고객아이디,리뷰평점,배달소요시간,긍부정,어플,주문일자,리뷰내용
            System.out.printf("%-20s| %-20s| %-20s| %-20s| %-20s  | %-20s | %-20s ","고객아이디","리뷰평점","배달소요시간","긍부정","어플","주문일자","리뷰내용");
            System.out.println();
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            while (rs1.next()) { //이름, 배달소요시간, 리뷰, 평점, 고객아이디, 주문일자를 출력
               String Id = rs1.getString(1);
               int Score = rs1.getInt(2);
               int Time = rs1.getInt(3);
               int Yn = rs1.getInt(4);
               String App = rs1.getString(5);
               Timestamp Date = rs1.getTimestamp(6);
               String Review = rs1.getString(7);
               System.out.printf("%-20s  | %-20d  | %-20d  | %-20d  | %-20s  | %-20s  | %-20s  ",Id,Score,Time,Yn,App,Date,Review);
               System.out.println();
            }   
         }catch(SQLException se) {
            System.out.println("오류");}
      
      }
   
   //전체 리뷰를 조회하는 메소드
   public static void totalreview(Connection conn,String ID) {
      // TODO Auto-generated method stub
	   
	 //배달 릴레이션과 리뷰 릴레이션을 natural join
	   //메소드의 parameter인 식당이름(ID)를 ?에 셋팅
	   //콘솔창에서 사용자에게 보여줄 리뷰 관련 내용들 select 
      String query = "select 고객아이디,리뷰평점,배달소요시간,긍부정,어플,주문일자,리뷰내용 from DB2022_배달 natural join DB2022_리뷰 where 식당이름=?";
      
      try {
            PreparedStatement pStmt = conn.prepareStatement(query); 
            pStmt.setString(1, ID);
            
            System.out.println("전체 리뷰입니다.");
            ResultSet rs1 = pStmt.executeQuery();
            System.out.printf("%-20s| %-20s| %-20s| %-20s| %-20s  | %-20s | %-20s ","고객아이디","리뷰평점","배달소요시간","긍부정","어플","주문일자","리뷰내용");
            System.out.println();
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            while (rs1.next()) { 
               String Id = rs1.getString(1);
               int Score = rs1.getInt(2);
               int Time = rs1.getInt(3);
               int Yn = rs1.getInt(4);
               String App = rs1.getString(5);
               Timestamp Date = rs1.getTimestamp(6);
               String Review = rs1.getString(7);
               System.out.printf("%-20s  | %-20d  | %-20d  | %-20d  | %-20s  | %-20s  | %-20s  ",Id,Score,Time,Yn,App,Date,Review);
               System.out.println();
            }

         }catch(SQLException se) {
            System.out.println("오류");}
      
      }
   
   //배달소요시간별 조회를 하는 메소드
   public static void deliveryTime(Connection conn,String ID) {
      // TODO Auto-generated method stub
      
	   //배달 릴레이션과 리뷰 릴레이션을 natural join
	   //메소드의 parameter인 식당이름(ID)를 첫번째 ?에 셋팅, 사용자에게 입력받는 배달소요시간을 두번째 ?에 셋팅
	   //콘솔창에서 사용자에게 보여줄 리뷰 관련 내용들 select
      String query = "select 고객아이디,리뷰평점,배달소요시간,긍부정,어플,주문일자,리뷰내용 from DB2022_배달 natural join DB2022_리뷰 where 식당이름=? AND 배달소요시간<=?";
      Scanner sc = new Scanner(System.in); 
      
      
      System.out.println("조회를 원하는 배달소요시간을 입력하시오."); 
      
      int deliverytime = sc.nextInt();
      
      try {
            PreparedStatement pStmt = conn.prepareStatement(query); //사용자에게 배달소요시간을 입력받을 preparedStatement 생성
            pStmt.setString(1, ID);
            pStmt.setInt(2, deliverytime); //사용자에게 입력받은 deliverytime을 query의 ?에 set함
            
               System.out.println("배달소요시간이 " + deliverytime + "분 이하인 리뷰입니다."); //사용자가 입력한 배달소요시간 이하의 리뷰들 출력
               ResultSet rs1 = pStmt.executeQuery();
               //select 고객아이디,리뷰평점,배달소요시간,긍부정,어플,주문일자,리뷰내용
               System.out.printf("%-20s| %-20s| %-20s| %-20s| %-20s  | %-20s | %-20s ","고객아이디","리뷰평점","배달소요시간","긍부정","어플","주문일자","리뷰내용");
               System.out.println();
               System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
               while (rs1.next()) { 
                  String Id = rs1.getString(1);
                  int Score = rs1.getInt(2);
                  int Time = rs1.getInt(3);
                  int Yn = rs1.getInt(4);
                  String App = rs1.getString(5);
                  Timestamp Date = rs1.getTimestamp(6);
                  String Review = rs1.getString(7);
                  System.out.printf("%-20s  | %-20d  | %-20d  | %-20d  | %-20s  | %-20s  | %-20s  ",Id,Score,Time,Yn,App,Date,Review);
                  System.out.println();
               }   
               
         }catch(SQLException se) {
            System.out.println("오류");}
      
      }  
   //기간별 조회를 하는 메소드
   public static void gap(Connection conn,String ID) {
            String showQuery,newdate,today = null;
            int month,week;
            Scanner sc = new Scanner(System.in);
            Calendar cal = Calendar.getInstance();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            
            //배달 릴레이션과 리뷰 릴레이션을 natural join
     	   //메소드의 parameter인 식당이름(ID)를 두번째 ?에 셋팅, 사용자에게 입력받는 newdate를 두번째 ?에 셋팅
     	   //콘솔창에서 사용자에게 보여줄 리뷰 관련 내용들 select
            showQuery = "select 고객아이디,리뷰평점,배달소요시간,긍부정,어플,주문일자,리뷰내용 from DB2022_배달 natural join DB2022_리뷰 where 주문일자 > str_to_date(?, '%Y-%m-%d') and 식당이름=?";
            try {
               
               
               cal.setTime(new Date());
               
               System.out.println();
               System.out.println("몇 개월 전 리뷰를 보고 싶으십니까?(0~12): ");
               month = sc.nextInt();
               System.out.println("몇 일 전 리뷰를 보고 싶으십니까?(0~12): ");
               week = sc.nextInt();
               today = df.format(cal.getTime());
               // 새 날짜 구하기
               // 원하는 리뷰 작성 기간 범위 설정
               cal.add(Calendar.MONTH, -month);
                cal.add(Calendar.DATE, -week);
               newdate = df.format(cal.getTime());
               System.out.println(newdate + "~" + today + "까지 작성 된 리뷰를 조회합니다.");
               System.out.println();
               PreparedStatement pStmt1 = conn.prepareStatement(showQuery);
               pStmt1.setString(1,newdate);
               pStmt1.setString(2,ID);
               
               ResultSet rs1 = pStmt1.executeQuery();
               System.out.printf("%-20s| %-20s| %-20s| %-20s| %-20s  | %-20s | %-20s ","고객아이디","리뷰평점","배달소요시간","긍부정","어플","주문일자","리뷰내용");
               System.out.println();
               System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
               while (rs1.next()) { 
                  String Id = rs1.getString(1);
                  int Score = rs1.getInt(2);
                  int Time = rs1.getInt(3);
                  int Yn = rs1.getInt(4);
                  String App = rs1.getString(5);
                  Timestamp Date = rs1.getTimestamp(6);
                  String Review = rs1.getString(7);
                  System.out.printf("%-20s  | %-20d  | %-20d  | %-20d  | %-20s  | %-20s  | %-20s  ",Id,Score,Time,Yn,App,Date,Review);
                  System.out.println();
               }
               
            } catch(SQLException sqle) {
               System.out.println("DB 접속실패 : " + sqle.toString());
            } catch (Exception e) {
               System.out.println("Unknown error");
               e.printStackTrace();
            }
         }
         
         
         
      }