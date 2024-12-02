
import java.util.Scanner;

public class FoldablePhone extends DienThoai {
    private double kichThuocGap;
    private double kichThuocMo;

    public FoldablePhone(String id, String ten, String hangSanXuat, double gia, int soLuong, double kichThuocGap, double kichThuocMo) {
        super(id, ten, hangSanXuat, gia, soLuong);
        this.kichThuocGap = kichThuocGap;
        this.kichThuocMo = kichThuocMo;
    }

    public FoldablePhone() {
        super();
        this.kichThuocGap = 0.0;
        this.kichThuocMo = 0.0;
    }

    @Override
    public void nhapThongTin(Scanner scanner) {
        super.nhapThongTin(scanner);
        System.out.print("Enter Kich thuoc gap (inch): ");
        this.kichThuocGap = scanner.nextDouble();
        System.out.print("Enter Kich thuoc mo (inch): ");
        this.kichThuocMo = scanner.nextDouble();
        scanner.nextLine();
    }

    @Override
    public void hienThiThongTin() {
        System.out.printf("Kich thuoc gap: %.1f\", Kich thuoc mo: %.1f\" |\n", kichThuocGap, kichThuocMo);
    }


    @Override
    public String getLoai() {
        return "FoldablePhone";
    }

    public double getKichThuocGap() {
       return kichThuocGap;
    }

    public double getKichThuocMo() {
        return kichThuocMo;
    }
    public String getHangSanXuat() {
        return hangSanXuat;
    }
    public double getGia() {
        return gia;
    }
    public String toString(){
        return getLoai() + ";" + getId() + ";" + getTen() + ";" + getHangSanXuat() + ";" + getGia() + ";" + getSoLuong() 
        + ";" + kichThuocGap + ";" + kichThuocMo;
    }


}
