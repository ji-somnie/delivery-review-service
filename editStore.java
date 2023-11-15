import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;
// �������� ���� �޴�
public class editStore {
   
   // ����� DB�� ���� �޴� ���� ������ �ֿ� ����� �Ұ��ϴ� �޼ҵ�
   public static void ESlanding(Connection conn,String ID) {
      System.out.println();
      System.out.println("***" + ID + " �޴� ���� ����***");

        System.out.println("0. ��ü �޴��� ����");
        System.out.println("1. �޴� ����ϱ�");
        System.out.println("2. �޴� �����ϱ�");
        System.out.println("3. �޴� �����ϱ�");

        System.out.println();
        Scanner sc = new Scanner(System.in);
        System.out.print("�޴��� �����Ͻÿ� : ");
        int num = sc.nextInt();
        
        //����� �Է¿� ���� ���� �޴� ���� ������ �ֿ� ��� ��� �޼ҵ� ȣ��
        //ȣ���� ���� �� ���� �޴��� �缱���� �� �ֵ��� ESlanding �޼ҵ� ȣ�⹮ �߰�
        switch(num) {
           case 1: System.out.println("�޴� ����ϱⰡ ���õǾ����ϴ�.");System.out.println(); editStore.insert(conn, ID); editStore.ESlanding(conn, ID);break; 
           case 2 : System.out.println("�޴� �����ϱⰡ ���õǾ����ϴ�.");System.out.println(); editStore.delete(conn, ID);editStore.ESlanding(conn, ID); break;
           case 3 : System.out.println("�޴� �����ϱⰡ ���õǾ����ϴ�.");System.out.println(); editStore.update(conn, ID);editStore.ESlanding(conn, ID); break;
           case 0 : System.out.println("��ü �޴��� ���Ⱑ ���õǾ����ϴ�.");System.out.println(); start.Startlanding(conn,ID);break;
           default : System.out.println("��ȣ�� �߸� �ԷµǾ����ϴ�.\n�ٽ� �Է��Ͻʽÿ�."); return;
        }
      
   }
   // ����ڰ� �Է��� �޴� ����(�̸�, ����)�� �߰��ϴ� �޼ҵ�
   public static void insert(Connection conn,String ID) {
      String menu,insertQuery,showQuery;
      int price;
      Scanner sc = new Scanner(System.in);
      insertQuery = "insert into DB2022_�޴� values(?, ?, ?)"; // ����ڿ��� �Է¹��� �޴� ������ �����ϴ� ����
      showQuery = "select * from DB2022_�޴� where �Ĵ��̸�=?"; // ���� ����� �����ִ� ����
      
      try {
        conn.setAutoCommit(false);
         PreparedStatement pStmt = conn.prepareStatement(showQuery);
         pStmt.setString(1,ID);
         ResultSet rs1 = pStmt.executeQuery();

         System.out.printf("%-20s | %-20s | %-20s ","�����̸�","�޴�","����");
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
         
         System.out.println("����� �޴��� �Է��Ͻÿ� : ");
         menu = sc.next();
   
         System.out.println("����� �޴��� ������ �Է��Ͻÿ� : ");
         price = sc.nextInt();
         


         PreparedStatement  pStmt1 = conn.prepareStatement(insertQuery);
         pStmt1.setString(1,ID);
         pStmt1.setString(2,menu);
         pStmt1.setInt(3, price);
         pStmt1.executeUpdate();
            
         System.out.println();
         System.out.println("��� �Ϸ�Ǿ����ϴ�.");
         
         PreparedStatement ppStmt = conn.prepareStatement(showQuery);
         ppStmt.setString(1,ID);
         ResultSet rs2 = ppStmt.executeQuery();
         
         System.out.println();
         System.out.println(" �����̸� " + "\t\t " + "| �޴� " + "\t\t "+ "| ���� " + "\t\t ");
         System.out.println("-----------------------------------------------------------------");
         while (rs2.next()) { 
            String Store = rs2.getString(1);
            String Menu = rs2.getString(2);
            int Price = rs2.getInt(3);
            System.out.println(Store + "\t\t " + "|"+ Menu + "\t\t"+ "|" + Price + "\t\t");      
         }
       //  Ʈ�����  commit 
         conn.commit();
         conn.setAutoCommit(true);
         
      } catch(SQLException se) {
       //  Ʈ�����  rollback 
         se.printStackTrace();
         try {
            if (conn!=null)
               conn.rollback();
         }catch(SQLException se2) {
            se2.printStackTrace();
         }
         
      } 
   }
   
   // ����ڰ� ���ϴ� �޴��� ����(�̸�, ����)�� �����ϴ� �޼ҵ�
   public static void update(Connection conn, String ID) {
         String menu,newMenu,updateQuery,showQuery,updateMenuname,updateprice;
         int newPrice;
         Scanner sc = new Scanner(System.in);
         
         updateMenuname = "update DB2022_�޴� set �޴� = ? where �޴� = ?"; // ����ڰ� �Է��Ѵ�� �޴� �̸��� �����ϴ� ����
         updateprice = "update DB2022_�޴� set ���� = ? where �޴� = ?"; // ����ڰ� �Է��Ѵ�� �޴� ������ �����ϴ� ����
         updateQuery = "update DB2022_�޴� set �޴� = ?, ���� = ? where �޴� = ?";  // ����ڰ� �Է��Ѵ�� �޴� �̸��� ������ �����ϴ� ����
         
         showQuery = "select * from DB2022_�޴� where �Ĵ��̸�=?";
         
         try {
           conn.setAutoCommit(false);
            PreparedStatement pStmt = conn.prepareStatement(showQuery);
            pStmt.setString(1,ID);
            ResultSet rs1 = pStmt.executeQuery();
            
            System.out.printf("%-20s | %-20s | %-20s ","�����̸�","�޴�","����");
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
            
            
            System.out.println("1. �޴� �̸� ����");
            System.out.println("2. ���� ����");
            System.out.println("3. �޴� �̸��� ���� ��� ����");
            System.out.println("�����ϰ� ���� ���� �����Ͻÿ� : ");
            
            int num = sc.nextInt();
            
            switch(num) {
               case 1: 
                  System.out.println();
                  System.out.println("�̸��� �����ϰ� ���� ���� �޴��� �Է��Ͻÿ� : ");
                  menu = sc.next();
                  System.out.println("���ο� �޴� �̸��� �Է��Ͻÿ� : ");
                  newMenu = sc.next();
                  PreparedStatement  pStmt1 = conn.prepareStatement(updateMenuname);
                  pStmt1.setString(1,newMenu);
                  pStmt1.setString(2, menu);
                  pStmt1.executeUpdate();
                  
                  break;
               
               case 2: 
                  System.out.println();
                  System.out.println("������ �����ϰ� ���� �޴��� �Է��Ͻÿ� : ");
                  menu = sc.next();
                  System.out.println("�ش� �޴��� ���ο� ������ �Է��Ͻÿ� : ");
                  newPrice = sc.nextInt();
                  PreparedStatement  pStmt2 = conn.prepareStatement(updateprice);
                  pStmt2.setInt(1,newPrice);
                  pStmt2.setString(2, menu);
                  pStmt2.executeUpdate();
                  
                  break;
      
               case 3:
                  System.out.println();
                  System.out.println("�̸��� ������ �����ϰ� ���� �޴��� �Է��Ͻÿ� : ");
                  menu = sc.next();
                  System.out.println("���ο� �޴� �̸��� �Է��Ͻÿ� : ");
                  newMenu = sc.next();
                  System.out.println("���ο� �޴� �̸��� ���ο� ������ �Է��Ͻÿ� : ");
                  newPrice = sc.nextInt();
                  PreparedStatement  pStmt3 = conn.prepareStatement(updateQuery);
                  pStmt3.setString(1,newMenu);
                  pStmt3.setInt(2, newPrice);
                  pStmt3.setString(3, menu);
                  pStmt3.executeUpdate();
                  
                  break;
               
               default : System.out.println("��ȣ�� �߸� �ԷµǾ����ϴ�.\n�ٽ� �Է��Ͻʽÿ�."); return;
            }
            
            System.out.println();
            System.out.println("���� �Ϸ�Ǿ����ϴ�.");
            
            PreparedStatement ppStmt = conn.prepareStatement(showQuery);
            ppStmt.setString(1,ID);
            ResultSet rs2 = ppStmt.executeQuery();
            
            System.out.printf("%-20s | %-20s | %-20s ","�����̸�","�޴�","����");
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
        //  Ʈ�����  commit
            conn.commit();
            conn.setAutoCommit(true);
            
         } catch(SQLException se) {
           //  Ʈ�����  rollback 
            se.printStackTrace();
            try {
               if (conn!=null)
                  conn.rollback();
            }catch(SQLException se2) {
               se2.printStackTrace();
            }
         } 
      }
   // ����ڰ� ���ϴ� �޴��� �����ϴ� �޼ҵ�
   public static void delete(Connection conn,String ID) {
      String menu,delQuery,showQuery;
      Scanner sc = new Scanner(System.in);
      
      delQuery = "delete from DB2022_�޴� where �޴� = ?"; // ����ڿ��� �Է¹��� �޴� ������ �����ϴ� ����
      showQuery = "select * from DB2022_�޴� where �Ĵ��̸�=?"; // ���� ����� �����ִ� ����
      
      try {
        conn.setAutoCommit(false);
         PreparedStatement pStmt1 = conn.prepareStatement(showQuery);
         pStmt1.setString(1,ID);
         ResultSet rs1 = pStmt1.executeQuery();
         
         System.out.printf("%-20s | %-20s | %-20s ","�����̸�","�޴�","����");
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
         
         System.out.println("������ �޴��� �Է��Ͻÿ� : ");
         menu = sc.next();
         
         
         PreparedStatement  pStmt2 = conn.prepareStatement(delQuery);
         pStmt2.setString(1,menu);
         pStmt2.executeUpdate();
      
         
         PreparedStatement pStmt3 = conn.prepareStatement(showQuery);
         pStmt3.setString(1,ID);
         ResultSet rs2 = pStmt3.executeQuery();
         
         System.out.printf("%-20s | %-20s | %-20s ","�����̸�","�޴�","����");
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
         System.out.println("���� �Ϸ�Ǿ����ϴ�.");
         
     //  Ʈ�����  commit 
         conn.commit();
         conn.setAutoCommit(true);
         
      } catch(SQLException se) {
       //  Ʈ�����  rollback 
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