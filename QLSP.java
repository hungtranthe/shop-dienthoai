import java.util.Scanner;

public class QLSP {
    private DSDT dsdt;

    public QLSP() {
        this.dsdt = new DSDT("dienThoai.dat");  // Đọc file ngay khi khởi tạo
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        int luaChon;
        do {
            System.out.println("\n===== QUAN LY SAN PHAM =====");
            System.out.println("1. Hien thi danh sach san pham");
            System.out.println("2. Them san pham moi");
            System.out.println("3. Tim kiem san pham");
            System.out.println("4. Xoa san pham");
            System.out.println("5. Cap nhat thong tin san pham");
            System.out.println("6. Quay lai menu chinh");
            System.out.print("Nhap lua chon cua ban: ");
            luaChon = scanner.nextInt();
            scanner.nextLine();  // Đọc dòng dư thừa sau khi nhập số

            switch (luaChon) {
                case 1 -> dsdt.hienThiDanhSach();
                case 2 -> dsdt.nhapthongtin(scanner);
                case 3 -> dsdt.timKiemNangCao(scanner);
                case 4 -> dsdt.xoaDienThoai(scanner);
                case 5 -> dsdt.capNhatDienThoai();
                case 6 -> {
                    System.out.println("Quay lai menu chinh...");
                    return; // Thoát khỏi menu quản lý sản phẩm để quay lại menu chính
                }
                /*case 7 ->{ 
                    String Thongtincanlay =scanner.nextLine();
                    String masanpham = scanner.nextLine();

                    Object kq = dsdt.timThongTintheoMasp(masanpham, Thongtincanlay);
                    System.out.print(kq);
                }*/
                default -> System.out.println("Lua chon khong hop le. Vui long thu lai!");
            }
        } while (luaChon != 6);
    }
}
