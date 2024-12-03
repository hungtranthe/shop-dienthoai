import java.util.Scanner;

public abstract class DienThoai {
    protected String id;
    protected String ten;
    protected String hangSanXuat;
    protected double gia;
    protected int soLuong;

    public DienThoai(String id, String ten, String hangSanXuat, double gia, int soLuong) {
        this.id = id;
        this.ten = ten;
        this.hangSanXuat = hangSanXuat;
        this.gia = gia;
        this.soLuong = soLuong;
    }

    public DienThoai() {
        this.id = "";
        this.ten = "";
        this.hangSanXuat = "";
        this.gia = 0.0;
        this.soLuong = 0;
    }

    public void nhapThongTin(Scanner scanner) {
        System.out.print("Nhap ID: ");
        this.id = scanner.nextLine();
        System.out.print("Nhap ten san pham: ");
        this.ten = scanner.nextLine();
        System.out.print("Nhap ten NSX: ");
        this.hangSanXuat = scanner.nextLine();
        System.out.print("Nhap gia: ");
        this.gia = scanner.nextDouble();
        System.out.print("Nhap so luong: ");
        this.soLuong = scanner.nextInt();
        scanner.nextLine();
    }

    public void hienThiThongTin() {
        System.out.printf("| %-10s | %-20s | %-15s | %-10.2f | %-8d |\n", 
                          id, ten, hangSanXuat, gia, soLuong);
    }

    public static int tinhTongSoLuong(DienThoai[] danhSach, int soLuong) {
        int tong = 0;
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i] != null) {
                tong += danhSach[i].soLuong;
            }
        }
        return tong;
    }

    public String getId() {
        return id;
    }
    public void setId(String id){
        this.id = id;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void capNhatSoLuong(int soLuongMoi) {
        this.soLuong = soLuongMoi;
    }

    public abstract String getLoai();

    public String getTen() {
        return ten;
    }
    @Override
    public String toString() {
        return getLoai() + ";" + id + ";" + ten + ";" + hangSanXuat + ";" + gia + ";" + soLuong;
    }

    public abstract Object getHangSanXuat();

    public abstract double getGia();

}
