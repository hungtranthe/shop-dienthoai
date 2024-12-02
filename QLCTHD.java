
import java.util.Scanner;

public class QLCTHD {
    private DSChiTietHoaDon dscthd;
    
    public QLCTHD(DSDT dsdt,int maxChiTiet){
        this.dscthd = new DSChiTietHoaDon(dsdt, maxChiTiet, "cthd.dat");
    }

    public void menu(){
        Scanner scanner = new Scanner(System.in);
        int ch;
        do{
            System.out.println("=============QUAN LY CHI TIET HOA DON==============");
            System.out.println("1.Them chi tiet hoa don");
            System.out.println("2.Sua chi tiet hoa don");
            System.out.println("3.Xoa chi tiet hoa don theo tuy chon");
            System.out.println("4.Tim kiem chi tiet hoa don tuy chon");
            System.out.println("5.Hien thi danh sach chi tiet hoa don ");
            System.out.println("6.Luu chi tiet hoa don vao file ");
            System.out.println("7.Doc file chi tiet hoa don ");
            System.out.println("0.Quay lai");
            System.out.println("==================================================");
            ch = scanner.nextInt();
            scanner.nextLine();
            switch (ch) {
                case 1:
                    System.out.println("Nhap ma hoa don: ");
                    String maHD = scanner.nextLine();
                    dscthd.themChiTietHoaDon(maHD);
                    break;
                case 2:
                    System.out.println("Nhap ma hoa don: ");
                    String maHDSua = scanner.nextLine();
                    dscthd.suaChiTietHoaDon(maHDSua);
                    break;
                case 3:
                    System.out.print("Nhap ma hoa don de xoa: ");
                    String maHDXoa = scanner.nextLine();
                    dscthd.xoaChiTietHoaDon(maHDXoa);
                    break;
                case 4:
                    int tk;
                    do { 
                        System.out.println("1.Tim kiem theo ma hoa don");
                        System.out.println("2.Tim kiem theo ma san pham");
                        System.out.println("0.Quay lai");
                        tk = scanner.nextInt();
                        scanner.nextLine();
                        switch (tk) {
                            case 1:
                                System.out.print("Nhap ma hoa don can tim: ");
                                String tkmaHD = scanner.nextLine();
                                dscthd.timKiemHoaChiTietDonTheoMaHD(tkmaHD);
                                break;
                            case 2:
                                System.out.println("Nhap ma dien thoai can tim: ");
                                String tkmaSP = scanner.nextLine();
                                dscthd.timKiemHoaDonChiTietTheoMaSP(tkmaSP);
                                break;
                            default:
                                System.out.println("Lua chon khong hop le. Vui long chon lai.");
                        }
                    } while (tk != 0);
                    break;
                case 5:
                    dscthd.hienThiDanhSachChiTietHoaDon();
                    break;
                case 6:
                    dscthd.luuFileChiTietHoaDon("DSChiTietHoaDon.txt");
                    break;
                case 7:
                    dscthd.docFileChiTietHoaDon("DSChiTietHoaDon.txt");
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai.");
            }
        } while (ch != 0);
        scanner.close();
    }
}
