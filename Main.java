import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import java.util.Scanner;


public class Main {
	
	public static void main(String[] args) {
		try {
			// �����ͺ��̽� ������ ���� ������ ���� ���� ���� �� mysql �� �ڹ� ����
            String DB_URL = "jdbc:mysql://localhost:3306/db2022team02";
            String USER = "DB2022Team02";
            String PASS = "DB2022Team02";
            
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			// ����ڿ��� �Ĵ��̸��� �Է� �޾� �α���
			Scanner sc = new Scanner(System.in);
			System.out.println("���̵�(�Ĵ��̸�) �Է� : ");
			String ID = sc.next();
			
			// ����� �α��� ���� �� startlanding �޼ҵ� ȣ��
			System.out.println("-------" + ID + " �����, �ȳ��ϼ��� !-------");
			start.Startlanding(conn,ID);

		} catch (SQLException sqle) {
			System.out.println("DB ���ӽ��� : " + sqle.toString());
		} catch (Exception e) {
			System.out.println("Unknown error");
			e.printStackTrace();
		}
		
		
		// Landing Page

	}
}
