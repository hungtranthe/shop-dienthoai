
import java.util.Scanner;

public class KTSDT {
		 // Hàm kiểm tra và trả về số điện thoại hợp lệ
	    public static String kiemTra() {
	        Scanner scanner = new Scanner(System.in); 
	        
	        String sDT;

	        while (true) {
	            // Yêu cầu người dùng nhập số điện thoại
	            System.out.print("Nhập số điện thoại: ");
	            sDT = scanner.nextLine();
	            long so = Long.parseLong(sDT); // Ép kiểu sang long
	            // Kiểm tra số điện thoại
	            if (sDT.length()==10 && so>=300000000 && so <=999999999) {
	                return sDT; // Trả về số điện thoại hợp lệ
	            } else {
	                System.out.println("Lỗi: Số điện thoại bắt đầu bằng số 0 và có 10 chữ số.");
	            }
	        }
	    }

	   

	}
