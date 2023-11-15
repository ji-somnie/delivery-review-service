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
	 // ����� DB �� ���� ��Ȳ �м��� �ֿ� ����� �Ұ��ϴ� �޼ҵ�
	public static void APPlanding(Connection conn,String ID) {
		System.out.println();
		System.out.println("***" + ID + " ���� ��Ȳ �м�***");
		System.out.println("0. ��ü �޴��� ����");
        System.out.println("1. ���� ��Ȳ �м�");
		System.out.println("2. ����ǹ��� ��Ȳ �м�");
        System.out.println("3. �������� ��Ȳ �м�");
        System.out.println("4. ��� ���� ��Ȳ �м�");
		
		Scanner sc = new Scanner(System.in);
		System.out.print("�޴��� �����Ͻÿ� : ");
        int num = sc.nextInt();
        
      //����� �Է¿� ���� ���� ��Ȳ �м��� �ֿ� ��� ��� �޼ҵ� ȣ��
        switch(num) {
        case 0 : System.out.println("��ü �޴��� ���Ⱑ ���õǾ����ϴ�..");System.out.println(); start.Startlanding(conn,ID);break;
        case 1 : System.out.println("���� ��Ȳ �м��� ���� �Ǿ����ϴ�.");System.out.println(); yogiyo(conn, ID);APPlanding(conn, ID);break;
        case 2 : System.out.println("����ǹ��� ��Ȳ �м��� ���� �Ǿ����ϴ�.");System.out.println(); baemin(conn,ID);APPlanding(conn, ID);break;
        case 3 : System.out.println("�������� ��Ȳ �м��� ���� �Ǿ����ϴ�.");System.out.println(); coupangeats(conn, ID);APPlanding(conn, ID);break;
        case 4 : System.out.println("��� ���� ��Ȳ �м��� ���� �Ǿ����ϴ�.");System.out.println(); allapps(conn,ID); APPlanding(conn, ID);break;
        default : System.out.println("��ȣ�� �߸� �ԷµǾ����ϴ�.\n�ٽ� �Է��Ͻʽÿ�."); return;
        }
        
 }
	  // ���� ������ ��� ��Ȳ�� �м��ϴ� �޼ҵ�
	   public static void yogiyo(Connection conn,String ID) {
	      // TODO Auto-generated method stub
		// ���� ������ ��� ��Ȳ�� ��ȸ�ϴ� ����
	      String query = "select �����̵�,��������,��޼ҿ�ð�,�����,�ֹ�����,���䳻�� from DB2022_��� natural join DB2022_���� where �Ĵ��̸�=? and ����=?";
	      // ���� ������ ��� ������ ��ȸ�ϴ� ����
	      String querySales = "select ����, sum(�ֹ��ݾ�) as �Ѹ���, count(*) as ���䰳�� from DB2022_yogiyo where �Ĵ��̸�=?";
	      
	      Scanner sc = new Scanner(System.in);
	      
	      try {
	            PreparedStatement pStmt = conn.prepareStatement(query); //����ڿ��� ��޼ҿ�ð��� �Է¹��� preparedStatement ����
	            pStmt.setString(1, ID);
	            pStmt.setString(2,"����");
	            ResultSet rs1 = pStmt.executeQuery();
	            
	            
	            PreparedStatement ppStmt = conn.prepareStatement(querySales);
	            ppStmt.setString(1, ID);
	            ResultSet rs2 = ppStmt.executeQuery();
	               
	            //select �����̵�,��������,��޼ҿ�ð�,�����,����,�ֹ�����,���䳻��
	            System.out.printf("%-20s| %-20s| %-20s| %-20s| %-20s | %-20s ","�����̵�","��������","��޼ҿ�ð�","�����","�ֹ�����","���䳻��");
	            System.out.println();
	            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
	            while (rs1.next()) { //�̸�, ��޼ҿ�ð�, ����, ����, �����̵�, �ֹ����ڸ� ���
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
	            System.out.println("����"+"\t"+"| �Ѹ���"+"\t       "+"| ���䰳��"+"\t");
	            System.out.println("--------------------------------");
	            while (rs2.next()) {
	            	String app = rs2.getString(1);
	            	int totalsales = rs2.getInt(2);
	            	int totalreviewnum = rs2.getInt(3);
	            	System.out.println(app+"\t"+"| "+totalsales+"\t"+"| "+totalreviewnum+"\t");
	            }
	            
	               
	         }catch(SQLException se) {
	            System.out.println("����");}
	      
	      }
	   
	   // ����ǹ��� ������ ��� ��Ȳ�� �м��ϴ� �޼ҵ�
	   public static void baemin(Connection conn,String ID) {
		      // TODO Auto-generated method stub
		// ����� ���� ������ ��� ��Ȳ�� ��ȸ�ϴ� ����
		      String query = "select �����̵�,��������,��޼ҿ�ð�,�����,�ֹ�����,���䳻�� from DB2022_��� natural join DB2022_���� where �Ĵ��̸�=? and ����=?";
		   // ����� ���� ������ ��� ������ ��ȸ�ϴ� ����
		      String querySales = "select ����, sum(�ֹ��ݾ�) as �Ѹ���, count(*) as ���䰳�� from DB2022_baemin where �Ĵ��̸�=?";
		      
		      Scanner sc = new Scanner(System.in);
		      
		      try {
		            PreparedStatement pStmt = conn.prepareStatement(query); //����ڿ��� ��޼ҿ�ð��� �Է¹��� preparedStatement ����
		            pStmt.setString(1, ID);
		            pStmt.setString(2,"����ǹ���");
		            ResultSet rs1 = pStmt.executeQuery();
		            
		            
		            PreparedStatement ppStmt = conn.prepareStatement(querySales);
		            ppStmt.setString(1, ID);
		            ResultSet rs2 = ppStmt.executeQuery();
		               
		            //select �����̵�,��������,��޼ҿ�ð�,�����,����,�ֹ�����,���䳻��
		            System.out.printf("%-20s| %-20s| %-20s| %-20s| %-20s | %-20s ","�����̵�","��������","��޼ҿ�ð�","�����","�ֹ�����","���䳻��");
		            System.out.println();
		            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		            while (rs1.next()) { //���� �̸�, �����̵�, ����, ��޼ҿ�ð�, �����, �ֹ�����, ���並 ���
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
		            System.out.println("����"+"\t"+"| �Ѹ���"+"\t       "+"| ���䰳��"+"\t");
		            System.out.println("--------------------------------");
		            while (rs2.next()) {
		            	String app = rs2.getString(1);
		            	int totalsales = rs2.getInt(2);
		            	int totalreviewnum = rs2.getInt(3);
		            	System.out.println(app+"\t"+"| "+totalsales+"\t"+"| "+totalreviewnum+"\t");
		            
		            }
		            
		               
		         }catch(SQLException se) {
		            System.out.println("����");}
		      
		      }
	// �������� ������ ��� ��Ȳ�� �м��ϴ� �޼ҵ�
	   public static void coupangeats(Connection conn,String ID) {
		      // TODO Auto-generated method stub
		// �������� ������ ��� ��Ȳ�� ��ȸ�ϴ� ����
		      String query = "select �����̵�,��������,��޼ҿ�ð�,�����,�ֹ�����,���䳻�� from DB2022_��� natural join DB2022_���� where �Ĵ��̸�=? and ����=?";
		      // �������� ������ ��� ������ ��ȸ�ϴ� ����
		      String querySales = "select ����, sum(�ֹ��ݾ�) as �Ѹ���, count(*) as ���䰳�� from DB2022_coupangeats where �Ĵ��̸�=?";
		      
		      Scanner sc = new Scanner(System.in);
		      
		      try {
		            PreparedStatement pStmt = conn.prepareStatement(query); //����ڿ��� ��޼ҿ�ð��� �Է¹��� preparedStatement ����
		            pStmt.setString(1, ID);
		            pStmt.setString(2,"��������");
		            ResultSet rs1 = pStmt.executeQuery();
		            
		            
		            PreparedStatement ppStmt = conn.prepareStatement(querySales);
		            ppStmt.setString(1, ID);
		            ResultSet rs2 = ppStmt.executeQuery();
		               
		            //select �����̵�,��������,��޼ҿ�ð�,�����,����,�ֹ�����,���䳻��
		            System.out.printf("%-20s| %-20s| %-20s| %-20s| %-20s | %-20s ","�����̵�","��������","��޼ҿ�ð�","�����","�ֹ�����","���䳻��");
		            System.out.println();
		            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		            while (rs1.next()) { //���� �̸�, �����̵�, ����, ��޼ҿ�ð�, �����, �ֹ�����, ���並 ���
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
		            System.out.println("����"+"\t"+"| �Ѹ���"+"\t       "+"| ���䰳��"+"\t");
		            System.out.println("--------------------------------");
		            while (rs2.next()) {
		            	String app = rs2.getString(1);
		            	int totalsales = rs2.getInt(2);
		            	int totalreviewnum = rs2.getInt(3);
		            	System.out.println(app+"\t"+"| "+totalsales+"\t"+"| "+totalreviewnum+"\t");
		            }
		            
		               
		         }catch(SQLException se) {
		            System.out.println("����");}
		      
		      }
	   
	// ��� ������ ���� ��� ��Ȳ�� �м��ϴ� �޼ҵ�
	   public static void allapps(Connection conn,String ID) {
		      // TODO Auto-generated method stub
		// ��� ������ ��� ��Ȳ�� ��ȸ�ϴ� ����
		      String query = "select ����,�����̵�,��������,��޼ҿ�ð�,�����,�ֹ�����,���䳻�� from DB2022_��� natural join DB2022_���� where �Ĵ��̸�=? order by ����";
		   // ��� ������ ��� ������ ��ȸ�ϴ� ���� 
		      String querySales = "select �Ĵ��̸�, ����, sum(�ֹ��ݾ�) as �Ѹ���, count(*) as ���䰳�� from (select �Ĵ��̸�, ����, �ֹ��ݾ� from db2022_sales_app where �Ĵ��̸�=?) new group by ����";
	
		      Scanner sc = new Scanner(System.in);
		      
		      try {
		            PreparedStatement pStmt = conn.prepareStatement(query); //����ڿ��� ��޼ҿ�ð��� �Է¹��� preparedStatement ����
		            pStmt.setString(1, ID);
		            ResultSet rs1 = pStmt.executeQuery();
		            
		            
		            PreparedStatement ppStmt = conn.prepareStatement(querySales);
		            ppStmt.setString(1, ID);
		            ResultSet rs2 = ppStmt.executeQuery();
		               
		            //select �����̵�,��������,��޼ҿ�ð�,�����,����,�ֹ�����,���䳻��
		            System.out.printf("%-20s| %-20s| %-20s| %-20s| %-20s| %-20s | %-20s ","����","�����̵�","��������","��޼ҿ�ð�","�����","�ֹ�����","���䳻��");
		            System.out.println();
		            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		            while (rs1.next()) { //���� �̸�, �����̵�, ����, ��޼ҿ�ð�, �����, �ֹ�����, ���並 ���
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
		            System.out.printf("%-20s  | %-20s  | %-20s  | %-20s  ","�����̸�","����","�Ѹ���","���䰳��");
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
		            System.out.println("����");}
		      
		      }
	
}
