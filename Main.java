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
			// 데이터베이스 연결을 위한 구성에 대한 지시 사항 및 mysql 과 자바 연결
            String DB_URL = "jdbc:mysql://localhost:3306/db2022team02";
            String USER = "DB2022Team02";
            String PASS = "DB2022Team02";
            
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			// 사용자에게 식당이름을 입력 받아 로그인
			Scanner sc = new Scanner(System.in);
			System.out.println("아이디(식당이름) 입력 : ");
			String ID = sc.next();
			
			// 사용자 로그인 성공 및 startlanding 메소드 호출
			System.out.println("-------" + ID + " 사장님, 안녕하세요 !-------");
			start.Startlanding(conn,ID);

		} catch (SQLException sqle) {
			System.out.println("DB 접속실패 : " + sqle.toString());
		} catch (Exception e) {
			System.out.println("Unknown error");
			e.printStackTrace();
		}
		
		
		// Landing Page

	}
}
