import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;
// 가게정보 수정 메뉴
public class editStore {
   
   // 배달의 DB의 가게 메뉴 정보 수정의 주요 기능을 소개하는 메소드
   public static void ESlanding(Connection conn,String ID) {
      System.out.println();
      System.out.println("***" + ID + " 메뉴 정보 수정***");

        System.out.println("0. 전체 메뉴로 가기");
        System.out.println("1. 메뉴 등록하기");
        System.out.println("2. 메뉴 삭제하기");
        System.out.println("3. 메뉴 수정하기");

        System.out.println();
        Scanner sc = new Scanner(System.in);
        System.out.print("메뉴를 선택하시오 : ");
        int num = sc.nextInt();
        
        //사용자 입력에 따라 가게 메뉴 정보 수정의 주요 기능 담당 메소드 호출
        //호출이 끝난 후 메인 메뉴를 재선택할 수 있도록 ESlanding 메소드 호출문 추가
        switch(num) {
           case 1: System.out.println("메뉴 등록하기가 선택되었습니다.");System.out.println(); editStore.insert(conn, ID); editStore.ESlanding(conn, ID);break; 
           case 2 : System.out.println("메뉴 삭제하기가 선택되었습니다.");System.out.println(); editStore.delete(conn, ID);editStore.ESlanding(conn, ID); break;
           case 3 : System.out.println("메뉴 수정하기가 선택되었습니다.");System.out.println(); editStore.update(conn, ID);editStore.ESlanding(conn, ID); break;
           case 0 : System.out.println("전체 메뉴로 가기가 선택되었습니다.");System.out.println(); start.Startlanding(conn,ID);break;
           default : System.out.println("번호가 잘못 입력되었습니다.\n다시 입력하십시오."); return;
        }
      
   }
   // 사용자가 입력한 메뉴 정보(이름, 가격)을 추가하는 메소드
   public static void insert(Connection conn,String ID) {
      String menu,insertQuery,showQuery;
      int price;
      Scanner sc = new Scanner(System.in);
      insertQuery = "insert into DB2022_메뉴 values(?, ?, ?)"; // 사용자에게 입력받은 메뉴 정보를 삽입하는 쿼리
      showQuery = "select * from DB2022_메뉴 where 식당이름=?"; // 삽입 결과를 보여주는 쿼리
      
      try {
        conn.setAutoCommit(false);
         PreparedStatement pStmt = conn.prepareStatement(showQuery);
         pStmt.setString(1,ID);
         ResultSet rs1 = pStmt.executeQuery();

         System.out.printf("%-20s | %-20s | %-20s ","가게이름","메뉴","가격");
         System.out.println();
         System.out.println("-----------------------------------------------------------------");
         while (rs1.next()) { 
            String Store = rs1.getString(1);
            String Menu = rs1.getString(2);
            int Price = rs1.getInt(3);
            System.out.printf("%-20s | %-20s | %-20d ",Store,Menu,Price);
            System.out.println();
         }
         System.out.println();
         
         System.out.println("등록할 메뉴를 입력하시오 : ");
         menu = sc.next();
   
         System.out.println("등록할 메뉴의 가격을 입력하시오 : ");
         price = sc.nextInt();
         


         PreparedStatement  pStmt1 = conn.prepareStatement(insertQuery);
         pStmt1.setString(1,ID);
         pStmt1.setString(2,menu);
         pStmt1.setInt(3, price);
         pStmt1.executeUpdate();
            
         System.out.println();
         System.out.println("등록 완료되었습니다.");
         
         PreparedStatement ppStmt = conn.prepareStatement(showQuery);
         ppStmt.setString(1,ID);
         ResultSet rs2 = ppStmt.executeQuery();
         
         System.out.println();
         System.out.println(" 가게이름 " + "\t\t " + "| 메뉴 " + "\t\t "+ "| 가격 " + "\t\t ");
         System.out.println("-----------------------------------------------------------------");
         while (rs2.next()) { 
            String Store = rs2.getString(1);
            String Menu = rs2.getString(2);
            int Price = rs2.getInt(3);
            System.out.println(Store + "\t\t " + "|"+ Menu + "\t\t"+ "|" + Price + "\t\t");      
         }
       //  트랜잭션  commit 
         conn.commit();
         conn.setAutoCommit(true);
         
      } catch(SQLException se) {
       //  트랜잭션  rollback 
         se.printStackTrace();
         try {
            if (conn!=null)
               conn.rollback();
         }catch(SQLException se2) {
            se2.printStackTrace();
         }
         
      } 
   }
   
   // 사용자가 원하는 메뉴의 정보(이름, 가격)을 수정하는 메소드
   public static void update(Connection conn, String ID) {
         String menu,newMenu,updateQuery,showQuery,updateMenuname,updateprice;
         int newPrice;
         Scanner sc = new Scanner(System.in);
         
         updateMenuname = "update DB2022_메뉴 set 메뉴 = ? where 메뉴 = ?"; // 사용자가 입력한대로 메뉴 이름을 변경하는 쿼리
         updateprice = "update DB2022_메뉴 set 가격 = ? where 메뉴 = ?"; // 사용자가 입력한대로 메뉴 가격을 변경하는 쿼리
         updateQuery = "update DB2022_메뉴 set 메뉴 = ?, 가격 = ? where 메뉴 = ?";  // 사용자가 입력한대로 메뉴 이름과 가격을 변경하는 쿼리
         
         showQuery = "select * from DB2022_메뉴 where 식당이름=?";
         
         try {
           conn.setAutoCommit(false);
            PreparedStatement pStmt = conn.prepareStatement(showQuery);
            pStmt.setString(1,ID);
            ResultSet rs1 = pStmt.executeQuery();
            
            System.out.printf("%-20s | %-20s | %-20s ","가게이름","메뉴","가격");
            System.out.println();
            System.out.println("-----------------------------------------------------------------");
            while (rs1.next()) { 
               String Store = rs1.getString(1);
               String Menu = rs1.getString(2);
               int Price = rs1.getInt(3);
               System.out.printf("%-20s | %-20s | %-20d ",Store,Menu,Price);
               System.out.println();
            }
            System.out.println();
            
            
            System.out.println("1. 메뉴 이름 수정");
            System.out.println("2. 가격 수정");
            System.out.println("3. 메뉴 이름과 가격 모두 수정");
            System.out.println("수정하고 싶은 것을 선택하시오 : ");
            
            int num = sc.nextInt();
            
            switch(num) {
               case 1: 
                  System.out.println();
                  System.out.println("이름을 수정하고 싶은 기존 메뉴를 입력하시오 : ");
                  menu = sc.next();
                  System.out.println("새로운 메뉴 이름을 입력하시오 : ");
                  newMenu = sc.next();
                  PreparedStatement  pStmt1 = conn.prepareStatement(updateMenuname);
                  pStmt1.setString(1,newMenu);
                  pStmt1.setString(2, menu);
                  pStmt1.executeUpdate();
                  
                  break;
               
               case 2: 
                  System.out.println();
                  System.out.println("가격을 수정하고 싶은 메뉴를 입력하시오 : ");
                  menu = sc.next();
                  System.out.println("해당 메뉴의 새로운 가격을 입력하시오 : ");
                  newPrice = sc.nextInt();
                  PreparedStatement  pStmt2 = conn.prepareStatement(updateprice);
                  pStmt2.setInt(1,newPrice);
                  pStmt2.setString(2, menu);
                  pStmt2.executeUpdate();
                  
                  break;
      
               case 3:
                  System.out.println();
                  System.out.println("이름과 가격을 수정하고 싶은 메뉴를 입력하시오 : ");
                  menu = sc.next();
                  System.out.println("새로운 메뉴 이름을 입력하시오 : ");
                  newMenu = sc.next();
                  System.out.println("새로운 메뉴 이름의 새로운 가격을 입력하시오 : ");
                  newPrice = sc.nextInt();
                  PreparedStatement  pStmt3 = conn.prepareStatement(updateQuery);
                  pStmt3.setString(1,newMenu);
                  pStmt3.setInt(2, newPrice);
                  pStmt3.setString(3, menu);
                  pStmt3.executeUpdate();
                  
                  break;
               
               default : System.out.println("번호가 잘못 입력되었습니다.\n다시 입력하십시오."); return;
            }
            
            System.out.println();
            System.out.println("수정 완료되었습니다.");
            
            PreparedStatement ppStmt = conn.prepareStatement(showQuery);
            ppStmt.setString(1,ID);
            ResultSet rs2 = ppStmt.executeQuery();
            
            System.out.printf("%-20s | %-20s | %-20s ","가게이름","메뉴","가격");
            System.out.println();
            System.out.println("-----------------------------------------------------------------");
            while (rs2.next()) { 
               String Store = rs2.getString(1);
               String Menu = rs2.getString(2);
               int Price = rs2.getInt(3);
               System.out.printf("%-20s | %-20s | %-20d ",Store,Menu,Price);
               System.out.println();
            }
            System.out.println();
        //  트랜잭션  commit
            conn.commit();
            conn.setAutoCommit(true);
            
         } catch(SQLException se) {
           //  트랜잭션  rollback 
            se.printStackTrace();
            try {
               if (conn!=null)
                  conn.rollback();
            }catch(SQLException se2) {
               se2.printStackTrace();
            }
         } 
      }
   // 사용자가 원하는 메뉴를 삭제하는 메소드
   public static void delete(Connection conn,String ID) {
      String menu,delQuery,showQuery;
      Scanner sc = new Scanner(System.in);
      
      delQuery = "delete from DB2022_메뉴 where 메뉴 = ?"; // 사용자에게 입력받은 메뉴 정보를 삭제하는 쿼리
      showQuery = "select * from DB2022_메뉴 where 식당이름=?"; // 삭제 결과를 보여주는 쿼리
      
      try {
        conn.setAutoCommit(false);
         PreparedStatement pStmt1 = conn.prepareStatement(showQuery);
         pStmt1.setString(1,ID);
         ResultSet rs1 = pStmt1.executeQuery();
         
         System.out.printf("%-20s | %-20s | %-20s ","가게이름","메뉴","가격");
         System.out.println();
         System.out.println("-----------------------------------------------------------------");
         while (rs1.next()) { 
            String Store = rs1.getString(1);
            String Menu = rs1.getString(2);
            int Price = rs1.getInt(3);
            System.out.printf("%-20s | %-20s | %-20d ",Store,Menu,Price);
            System.out.println();
         }
         System.out.println();
         
         System.out.println("삭제할 메뉴를 입력하시오 : ");
         menu = sc.next();
         
         
         PreparedStatement  pStmt2 = conn.prepareStatement(delQuery);
         pStmt2.setString(1,menu);
         pStmt2.executeUpdate();
      
         
         PreparedStatement pStmt3 = conn.prepareStatement(showQuery);
         pStmt3.setString(1,ID);
         ResultSet rs2 = pStmt3.executeQuery();
         
         System.out.printf("%-20s | %-20s | %-20s ","가게이름","메뉴","가격");
         System.out.println();
         System.out.println("-----------------------------------------------------------------");
         while (rs2.next()) {
            String Store = rs2.getString(1);
            String Menu = rs2.getString(2);
            int Price = rs2.getInt(3);
            System.out.printf("%-20s | %-20s | %-20d ",Store,Menu,Price);
            System.out.println();
         }
         System.out.println();
         System.out.println("삭제 완료되었습니다.");
         
     //  트랜잭션  commit 
         conn.commit();
         conn.setAutoCommit(true);
         
      } catch(SQLException se) {
       //  트랜잭션  rollback 
         se.printStackTrace();
         try {
            if (conn!=null)
               conn.rollback();
         }catch(SQLException se2) {
            se2.printStackTrace();
         }
      } 
   }
}