import java.sql.Connection;
import java.util.Scanner;

public class start {
	// Startlanding: ����� DB�� ��ü �޴��� �Ұ��ϴ� �޼ҵ�
	public static void Startlanding(Connection conn,String ID) {
		System.out.println();
		System.out.println("***��ü �޴��Դϴ�***");
        System.out.println("1. ���� �޴� ���� ����");
        System.out.println("2. ��� ���� �м� ");
        System.out.println("3. ���� ��Ȳ �м� ");

        System.out.println();
        Scanner sc = new Scanner(System.in);
        System.out.print("�޴��� �����Ͻÿ� (�����Ϸ��� 0�� �Է��Ͻÿ�) : ");
        int num = sc.nextInt();
        
     // ����� �Է¿� ���� �����DB�� �ֿ� ����� ������ ��ü ����
        switch(num) {
	        case 1 : System.out.println("���� �޴� ���� ������ ���õǾ����ϴ�."); editStore.ESlanding(conn,ID);break;
	        case 2 : System.out.println("��� ���� �м��� ���õǾ����ϴ�."); reviewSort.RAlanding(conn, ID);break;
	        case 3 : System.out.println("���� ��Ȳ �м��� ���õǾ����ϴ�."); appAnal.APPlanding(conn,ID); break;
	        case 0 : System.out.println("���α׷��� ����˴ϴ�."); break;
	        default : System.out.println("��ȣ�� �߸� �ԷµǾ����ϴ�.\n�ٽ� �Է��Ͻʽÿ�."); return;
        }
		
	}
}

