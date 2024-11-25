import java.util.Date;
import java.util.Scanner;

interface ThanhToan{
    double getThanhTien();
}

abstract class HoaDonCoSo implements ThanhToan {
    protected String maHD;
    protected Date ngayNhapHD;
    public HoaDonCoSo (String maHD, Date ngaynhapHD){
        this.maHD = maHD;
        this.ngayNhapHD = ngaynhapHD;
    }
    public abstract double tinhTongTien();
}

class HoaDon implements ThanhToan{
    private String maHD;
    private Date ngayNhapHD;
    private String maNV;
    private String maKH;
    private double tongTien;
    private ChiTietHoaDon[] chiTietHoaDonList; 
    private int soChiTiet;
    //Contructor
    public HoaDon(String maHD,Date ngaynhapHD,String maNV,String maKH,double tongTien, int maxChiTiet){
        this.maHD = maHD;
        this.ngayNhapHD = ngaynhapHD;
        this.maNV = maNV;
        this.maKH = maKH;
        this.tongTien = tongTien;    
        this.chiTietHoaDonList = new ChiTietHoaDon[maxChiTiet];
        this.soChiTiet = 0;     
    }

    public String getMaHD(){
        return this.maHD;
    }
    
    public Date getNgayNhapHD(){
        return this.ngayNhapHD;
    }

    public String getMaNV(){
        return this.maNV;
    }

    public String getMaKH(){
        return this.maKH;
    }

    public double  getTongTien(){
        return this.tongTien;
    }

    public void themChiTietHoaDon(ChiTietHoaDon chiTiet){
        if (soChiTiet < chiTietHoaDonList.length){
            chiTietHoaDonList[soChiTiet] = chiTiet;
            tongTien += chiTiet.getThanhTien();
            soChiTiet++;
        }
    }
    
    public void nhapHoaDon() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap ma hoa don: ");
        this.maHD = scanner.nextLine();
        System.out.print("Nhap ma nhan vien: ");
        this.maNV = scanner.nextLine();
        System.out.print("Nhap ma khach hang: ");
        this.maKH = scanner.nextLine();
    }

    public void nhapChiTietHoaDon() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap so luong chi tiet hoa don: ");
        int soLuongChiTiet = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i <= soLuongChiTiet; i++) {
            if (soLuongChiTiet == i){
                soLuongChiTiet = 100;
            }
            System.out.print("Nhap ma hoa don: ");
            String maHD = scanner.nextLine();
            System.out.print("Nhap ma san pham: ");
            String maSP = scanner.nextLine();
            System.out.print("Nhap ma so luong ban: ");
            int soLuongBan = scanner.nextInt();
            System.out.print("Nhap ma đon gia ban: ");
            double donGiaBan = scanner.nextDouble();
            scanner.nextLine();
            ChiTietHoaDon chiTiet = new ChiTietHoaDon(maHD, maSP, soLuongBan, donGiaBan);
            themChiTietHoaDon(chiTiet);
        }
    }

    public void xuatChiTietHoaDon() {
        for (int i = 0; i < soChiTiet; i++) {
            System.out.println(chiTietHoaDonList[i]);
        }
    }

    public void themChiTietHoaDon(){
    }

    @Override
    public double getThanhTien(){
        return this.tongTien;
    } 

    @Override
    public String toString() {
        return "Hoa Đon{" +
            "Ma hoa đon='" + maHD + '\'' +
            ", Ngay nhap='" + ngayNhapHD + '\'' +
            ", Ma nhan vien='" + maNV + '\'' +
            ", Ma khach hang='" + maKH + '\'' +
            ", Tong tien=" + tongTien + getThanhTien() +
            '}';
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HoaDon hoaDon = new HoaDon("", new Date(), "", "", 0, 10);
        int ch;
        do { 
            System.out.println("1.Them chi tiet hoa don");
            System.out.println("2.Nhap hoa don va chi tiet hoa don");
            System.out.println("3.Xuat chi tiet hoa don");
            ch = scanner.nextInt();
            scanner.nextLine();
            switch (ch){
                case 1: 
                    hoaDon.themChiTietHoaDon();
                    break;
                case 2:
                    hoaDon.nhapHoaDon();
                    hoaDon.nhapChiTietHoaDon();
                    break;
                case 3:
                    hoaDon.xuatChiTietHoaDon();
                    break;
                default: 
                    System.out.println("Lua chon khong hop le");
            }
        } while (ch != 0);
            scanner.close();
       
    }
}




