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
	//����� DB�� ��� ���� �м��� �ֿ� ����� �Ұ��ϴ� �޼ҵ�
   public static void RAlanding(Connection conn,String ID) {
      System.out.println();
      System.out.println("***" + ID + " ��� ���� �м�***");

        System.out.println("0. ��ü �޴��� ����");
        System.out.println("1. ������ ���� ��ȸ");
        System.out.println("2. �Ⱓ�� ���� ��ȸ");
        System.out.println("3. ��޼ҿ�ð��� ���� ��ȸ");
        System.out.println("4. ������� ���� ��ȸ");
        System.out.println("5. �ܰ� �մ� ��ȸ");
        System.out.println("6. ��ü ���� �м�");


        System.out.println();
        Scanner sc = new Scanner(System.in);
        System.out.print("�޴��� �����ϼ��� : ");
        int num = sc.nextInt();
        
    	//��� ���� �м��� ���� ����� ����ϴ� �޼ҵ� ȣ��
        switch(num) {
        case 0 : System.out.println("��ü �޴��� ���Ⱑ ���õǾ����ϴ�..");System.out.println(); start.Startlanding(conn,ID);break;
        case 1 : System.out.println("������ ���� ��ȸ�� ���õǾ����ϴ�.");System.out.println(); grade(conn, ID);RAlanding(conn, ID);break;
        case 2 : System.out.println("�Ⱓ�� ���� ��ȸ�� ���õǾ����ϴ�.");System.out.println();gap(conn,ID);RAlanding(conn, ID);break;
        case 3 : System.out.println("��޼ҿ�ð��� ���� ��ȸ�� ���õǾ����ϴ�.");System.out.println(); deliveryTime(conn, ID);RAlanding(conn, ID);break;
        case 4 : System.out.println("������� ���� ��ȸ�� ���õǾ����ϴ�.");System.out.println(); posneg(conn,ID); RAlanding(conn, ID);break;
        case 5 : System.out.println("�ܰ� �մ� ��ȸ�� ���õǾ����ϴ�.");System.out.println(); regular(conn, ID);RAlanding(conn, ID);break;
        case 6 : System.out.println("��ü ���� ��ȸ�� ���õǾ����ϴ�.");System.out.println(); totalreview(conn, ID);RAlanding(conn, ID);break;
        default : System.out.println("��ȣ�� �߸� �ԷµǾ����ϴ�.\n�ٽ� �Է��Ͻʽÿ�."); return;
        }
      
   }
   
 //�ܰ� �մ� ��ȸ�ϴ� �޼ҵ�
   public static void regular(Connection conn,String ID) { 
      // TODO Auto-generated method stub
      
	   //from ���� ����: ���� �����̼ǿ��� �����̵�� �׷�ȭ�Ͽ� �����ֹ�Ƚ���� db2022_�ܰ�մ����� select
	   //�ܺ� ����: from���� ������� db2022_�ܰ�մ����κ��� �����ֹ�Ƚ���� 2 �̻��� �մ�(�ܰ�մ��̶� ����)�� �����̵� ������ 
      String query = "select �����̵� from (select �����̵�, count(�����̵�) as �����ֹ�Ƚ�� from db2022_���� where �Ĵ��̸�=? group by �����̵�) db2022_�ܰ�մ� where �����ֹ�Ƚ��>=2;";

      try {
            PreparedStatement pStmt = conn.prepareStatement(query);
            pStmt.setString(1, ID);
            
            ResultSet rs1 = pStmt.executeQuery();
        
            System.out.println("�ܰ� �մ� ����Դϴ�.");
            System.out.println("�����̵�");
            System.out.println("------------");
            while (rs1.next()) {
               String client = rs1.getString(1);
               System.out.println(client);
            }
               
         }catch(SQLException se) {
            System.out.println("����");}
      
      }
   
 //������� ��ȸ�ϴ� �޼ҵ�
   public static void posneg(Connection conn,String ID) {
      // TODO Auto-generated method stub
      
	   //��� �����̼ǰ� ���� �����̼��� natural join
	   //�޼ҵ��� parameter�� ID�� ù���� ?��, ����ڿ��� �Է¹��� ����� ������ �ι�° ?�� ����
	   //�ܼ�â���� ����ڿ��� ������ ���� ���� ����� select 
      String query = "select �����̵�,��������,��޼ҿ�ð�,�����,����,�ֹ�����,���䳻�� from DB2022_��� natural join DB2022_���� where �Ĵ��̸�=? and �����=?";

      Scanner sc=new Scanner(System.in);
      System.out.println("��ȸ�� ���ϴ� ������ �Է��Ͻÿ�. ��=1, ��=0");
      int reaction = sc.nextInt();
      try {
            PreparedStatement pStmt = conn.prepareStatement(query); 
            pStmt.setString(1, ID);
            pStmt.setInt(2, reaction);
            
            if (reaction == 1)
               System.out.println("���� �����Դϴ�.");
            else 
               System.out.println("���� �����Դϴ�.");
               
            ResultSet rs1 = pStmt.executeQuery();
            
            System.out.printf("%-20s| %-20s| %-20s| %-20s| %-20s  | %-20s | %-20s ","�����̵�","��������","��޼ҿ�ð�","�����","����","�ֹ�����","���䳻��");
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
            System.out.println("����");}
      
      }
   //������ ��ȸ�ϴ� �޼ҵ�
   public static void grade(Connection conn,String ID) { 
      // TODO Auto-generated method stub
	   
	 //��� �����̼ǰ� ���� �����̼��� natural join
	   //�޼ҵ��� parameter�� ID�� ù���� ?��, ����ڿ��� �Է¹��� ���������� �ι�° ?�� ����
	   //�ܼ�â���� ����ڿ��� ������ ���� ���� ����� select 
      String query = "select �����̵�,��������,��޼ҿ�ð�,�����,����,�ֹ�����,���䳻�� from DB2022_��� natural join DB2022_���� where �Ĵ��̸�=? and ��������=?";
      Scanner sc= new Scanner(System.in);
      System.out.println("��ȸ�� ���ϴ� ������ �Է��Ͻÿ�.(1~5)");
      int grade=sc.nextInt();
      try {
            PreparedStatement pStmt = conn.prepareStatement(query); //����ڿ��� ��޼ҿ�ð��� �Է¹��� preparedStatement ����
            pStmt.setString(1, ID);
            pStmt.setInt(2, grade);
             
            System.out.println("������ "+grade+"�� �����Դϴ�.");   
            
            ResultSet rs1 = pStmt.executeQuery();
            //select �����̵�,��������,��޼ҿ�ð�,�����,����,�ֹ�����,���䳻��
            System.out.printf("%-20s| %-20s| %-20s| %-20s| %-20s  | %-20s | %-20s ","�����̵�","��������","��޼ҿ�ð�","�����","����","�ֹ�����","���䳻��");
            System.out.println();
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            while (rs1.next()) { //�̸�, ��޼ҿ�ð�, ����, ����, �����̵�, �ֹ����ڸ� ���
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
            System.out.println("����");}
      
      }
   
   //��ü ���並 ��ȸ�ϴ� �޼ҵ�
   public static void totalreview(Connection conn,String ID) {
      // TODO Auto-generated method stub
	   
	 //��� �����̼ǰ� ���� �����̼��� natural join
	   //�޼ҵ��� parameter�� �Ĵ��̸�(ID)�� ?�� ����
	   //�ܼ�â���� ����ڿ��� ������ ���� ���� ����� select 
      String query = "select �����̵�,��������,��޼ҿ�ð�,�����,����,�ֹ�����,���䳻�� from DB2022_��� natural join DB2022_���� where �Ĵ��̸�=?";
      
      try {
            PreparedStatement pStmt = conn.prepareStatement(query); 
            pStmt.setString(1, ID);
            
            System.out.println("��ü �����Դϴ�.");
            ResultSet rs1 = pStmt.executeQuery();
            System.out.printf("%-20s| %-20s| %-20s| %-20s| %-20s  | %-20s | %-20s ","�����̵�","��������","��޼ҿ�ð�","�����","����","�ֹ�����","���䳻��");
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
            System.out.println("����");}
      
      }
   
   //��޼ҿ�ð��� ��ȸ�� �ϴ� �޼ҵ�
   public static void deliveryTime(Connection conn,String ID) {
      // TODO Auto-generated method stub
      
	   //��� �����̼ǰ� ���� �����̼��� natural join
	   //�޼ҵ��� parameter�� �Ĵ��̸�(ID)�� ù��° ?�� ����, ����ڿ��� �Է¹޴� ��޼ҿ�ð��� �ι�° ?�� ����
	   //�ܼ�â���� ����ڿ��� ������ ���� ���� ����� select
      String query = "select �����̵�,��������,��޼ҿ�ð�,�����,����,�ֹ�����,���䳻�� from DB2022_��� natural join DB2022_���� where �Ĵ��̸�=? AND ��޼ҿ�ð�<=?";
      Scanner sc = new Scanner(System.in); 
      
      
      System.out.println("��ȸ�� ���ϴ� ��޼ҿ�ð��� �Է��Ͻÿ�."); 
      
      int deliverytime = sc.nextInt();
      
      try {
            PreparedStatement pStmt = conn.prepareStatement(query); //����ڿ��� ��޼ҿ�ð��� �Է¹��� preparedStatement ����
            pStmt.setString(1, ID);
            pStmt.setInt(2, deliverytime); //����ڿ��� �Է¹��� deliverytime�� query�� ?�� set��
            
               System.out.println("��޼ҿ�ð��� " + deliverytime + "�� ������ �����Դϴ�."); //����ڰ� �Է��� ��޼ҿ�ð� ������ ����� ���
               ResultSet rs1 = pStmt.executeQuery();
               //select �����̵�,��������,��޼ҿ�ð�,�����,����,�ֹ�����,���䳻��
               System.out.printf("%-20s| %-20s| %-20s| %-20s| %-20s  | %-20s | %-20s ","�����̵�","��������","��޼ҿ�ð�","�����","����","�ֹ�����","���䳻��");
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
            System.out.println("����");}
      
      }  
   //�Ⱓ�� ��ȸ�� �ϴ� �޼ҵ�
   public static void gap(Connection conn,String ID) {
            String showQuery,newdate,today = null;
            int month,week;
            Scanner sc = new Scanner(System.in);
            Calendar cal = Calendar.getInstance();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            
            //��� �����̼ǰ� ���� �����̼��� natural join
     	   //�޼ҵ��� parameter�� �Ĵ��̸�(ID)�� �ι�° ?�� ����, ����ڿ��� �Է¹޴� newdate�� �ι�° ?�� ����
     	   //�ܼ�â���� ����ڿ��� ������ ���� ���� ����� select
            showQuery = "select �����̵�,��������,��޼ҿ�ð�,�����,����,�ֹ�����,���䳻�� from DB2022_��� natural join DB2022_���� where �ֹ����� > str_to_date(?, '%Y-%m-%d') and �Ĵ��̸�=?";
            try {
               
               
               cal.setTime(new Date());
               
               System.out.println();
               System.out.println("�� ���� �� ���並 ���� �����ʴϱ�?(0~12): ");
               month = sc.nextInt();
               System.out.println("�� �� �� ���並 ���� �����ʴϱ�?(0~12): ");
               week = sc.nextInt();
               today = df.format(cal.getTime());
               // �� ��¥ ���ϱ�
               // ���ϴ� ���� �ۼ� �Ⱓ ���� ����
               cal.add(Calendar.MONTH, -month);
                cal.add(Calendar.DATE, -week);
               newdate = df.format(cal.getTime());
               System.out.println(newdate + "~" + today + "���� �ۼ� �� ���並 ��ȸ�մϴ�.");
               System.out.println();
               PreparedStatement pStmt1 = conn.prepareStatement(showQuery);
               pStmt1.setString(1,newdate);
               pStmt1.setString(2,ID);
               
               ResultSet rs1 = pStmt1.executeQuery();
               System.out.printf("%-20s| %-20s| %-20s| %-20s| %-20s  | %-20s | %-20s ","�����̵�","��������","��޼ҿ�ð�","�����","����","�ֹ�����","���䳻��");
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
               System.out.println("DB ���ӽ��� : " + sqle.toString());
            } catch (Exception e) {
               System.out.println("Unknown error");
               e.printStackTrace();
            }
         }
         
         
         
      }