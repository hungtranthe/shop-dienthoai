import java.util.Scanner;

public class QLPNH {
    private DSPNH dspnh;

    public QLPNH(DSDT dsdt, DSKH dskh, DSNV dsnv, int maxPNH) {
        this.dspnh = new DSPNH("D:\\OOP\\pnh.dat", dsdt, dskh, dsnv, maxPNH);
    }

    // Hien thi menu va goi cac chuc nang cua DSPNH
    public void menu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== QUAN LY PHIEU NHAP HANG =====");
            System.out.println("1. Them phieu nhap hang");
            System.out.println("2. Hien thi danh sach phieu nhap");
            System.out.println("3. Tim kiem phieu nhap");
            System.out.println("4. Thong ke phieu nhap");
            System.out.println("5. Thoat");
            System.out.print("Nhap lua chon cua ban: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    dspnh.themPNH(scanner);
                    break;
                case 2:
                    dspnh.hienThiDanhSachPNH();
                    break;
                case 3:
                    dspnh.timKiemPNH(scanner);
                    break;
                case 4:
                    dspnh.thongKePNH();
                    break;
                case 5:
                    System.out.println("Thoat chuong trinh. Hen gap lai!");
                    break;
                default:
                    System.out.println("Lua chon khong hop le, vui long thu lai.");
            }
        } while (choice != 5);

    }
}
