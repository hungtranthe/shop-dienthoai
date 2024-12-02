
import java.util.Scanner;

public class QLHD{
    private DSHoaDon dshd;
    private DSDT dsdt;
    public QLHD(int maxHoaDon){
        this.dshd = new DSHoaDon(maxHoaDon,"hoadon.dat");
    }

    public void getDshd(DSHoaDon dshd){
        this.dshd = dshd;
    }
    public void menu(){
        Scanner scanner = new Scanner(System.in);
        dshd.docFileHoaDon("hoadon.dat");
        int ch;
        do { 
            System.out.println("===========Quan Ly Hoa Don=========");
            System.out.println("1.Hien thi danh sach hoa don");
            System.out.println("2.Them hoa don");
            System.out.println("3.Tim kiem hoa don theo tuy chon");
            System.out.println("4.Sua hoa don");
            System.out.println("5.Xoa hoa don");
            System.out.println("6.Xuat hoa don");
            System.out.println("7.Doc file hoa don");
            System.out.println("8.Luu danh sach hoa don vao file");
            System.out.println("0.Quay lai menu chinh");
            System.out.println("====================================");
            ch = scanner.nextInt();
            scanner.nextLine();
            switch (ch) {
                case 1:
                    dshd.hienThiDanhSachHoaDon();
                    break;
                case 2:
                    dshd.themHoaDon(scanner);
                    break;
                case 3:
                    int tk;
                    do { 
                        System.out.println("1.Tim kiem hoa don theo ma hoa don");
                        System.out.println("2.Tim kiem hoa don theo ma nhan vien");
                        System.out.println("3.Tim kiem hoa don theo ma khach hang");
                        System.out.println("0.Quay lai");
                        tk = scanner.nextInt();
                        scanner.nextLine();
                        switch (tk) {
                            case 1:
                                System.out.println("Nhap ma hoa don cua hoa don can tim: ");
                                String maHDtk = scanner.nextLine();
                                dshd.timKiemHoaDonTheoMaHD(maHDtk);
                                break;
                            case 2:
                                System.out.println("Nhap ma nhan vien cua hoa don can tim: ");
                                String maNVtk = scanner.nextLine();
                                dshd.timKiemHoaDonTheoMaNV(maNVtk);
                                break;
                            case 3:
                                System.out.println("Nhap ma khach hang cua hoa don can tim: ");
                                String maKHtk = scanner.nextLine();
                                dshd.timKiemHoaDonTheoMaKH(maKHtk);
                                break;
                            case 0:
                                break;
                            default:
                                System.out.println("Lua chon khong hop le. Vui long nhap lai.");
                                break;
                        }
                    } while (tk != 0);
                    break;
                case 4:
                    System.out.println("Nhap ma hoa don can sua");
                    String maHDSua = scanner.nextLine();
                    break;
                case 5:
                    System.out.println("Nhap ma hoa don can xoa: ");
                    String xoaHD = scanner.nextLine();
                    dshd.xoaHoaDon(xoaHD);
                    break;
                case 6:
                    System.out.println("Nhap ma hoa don can xuat: ");
                    String xuatHD = scanner.nextLine();
                    dshd.xuatHoaDon(xuatHD);
                    break;
                case 7:
                    dshd.docFileHoaDon("hoadon.dat");
                    break;
                case 8:
                    dshd.luuFileHoaDon("hoadon.dat");
                    break;
                default:
                    System.out.println("Lua chon khong hop le");
                    break;
                    
            }
        } while (ch != 0);
    }
}