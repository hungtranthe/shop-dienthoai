import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        
        // Khởi tạo danh sách điện thoại và hóa đơn
        DSDT dsdt = new DSDT("dienthoai.dat"); // Đường dẫn tới file dữ liệu điện thoại
        DSHoaDon dshd = new DSHoaDon(100,"HoaDon.txt"); // Khởi tạo danh sách hóa đơn với kích thước tối đa là 100
        DSChiTietHoaDon dscthd = new DSChiTietHoaDon(dsdt, 100,"DSChiTietHoaDon.txt");
        DSNV dsnv = new DSNV(30, "NhanVien.txt");
        DSKH dskh = new DSKH(30, "KhachHang.txt");
        DSPNH dspnh = new DSPNH("CTPNH.txt", dsdt, dskh, dsnv, 100);
        // Khởi tạo các lớp quản lý
        QLSP qlsp = new QLSP();
        QLHD qlhd = new QLHD(100);
        QLCTHD qlcthd = new QLCTHD(dsdt, 100); 
        QLPNH qlpnh = new QLPNH(dsdt, dskh, dsnv, 100);
        
        do {
            System.out.println("=============MENU CHÍNH=========");
            System.out.println("|1. Quan ly san pham           |");
            System.out.println("|2. Quan ly hoa don            |");
            System.out.println("|3. Quan ly chi tiet hoa don   |");
            System.out.println("|4. Quan ly nhan vien          |");
            System.out.println("|5. Quan ly khach hang         |");
            System.out.println("|6. Quan ly phieu nhap hang    |");
            System.out.println("|0. Thoát                      |");
            System.out.println("|==============================|");
            System.out.print("|||||| Chon chuc nang: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    qlsp.menu();
                    break;
                case 2:
                    qlhd.menu();
                    break;
                case 3:
                    qlcthd.menu(); 
                    break;
                case 4:
                    dsnv.menu(dshd, dspnh);
                case 5:
                    dskh.menu();
                    break;
                case 6:
                    qlpnh.menu();
                    break;
                case 0:
                    System.out.println("Thoat chuong trinh.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        } while (choice != 0);

        scanner.close();
    }
}