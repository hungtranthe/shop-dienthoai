
import java.util.Scanner;

public class GamingPhone extends DienThoai {
    private int ram;
    private int cpu;

    public GamingPhone(String id, String ten, String hangSanXuat, double gia, int soLuong, int ram, int cpu) {
        super(id, ten, hangSanXuat, gia, soLuong);
        this.ram = ram;
        this.cpu = cpu;
    }

    public GamingPhone() {
        super();
        this.ram = 0;
        this.cpu = 0;
    }

    @Override
    public void nhapThongTin(Scanner scanner) {
        super.nhapThongTin(scanner);
        System.out.print("Enter RAM (GB): ");
        this.ram = scanner.nextInt();
        System.out.print("Enter CPU (GHz): ");
        this.cpu = scanner.nextInt();
        scanner.nextLine();
    }

    @Override
        public void hienThiThongTin() {
        System.out.printf("RAM: %dGB, CPU: %d nhan |\n", ram, cpu);
    }


    @Override
    public String getLoai() {
        return "GamingPhone";
    }

    public int getRam() {
        return ram;
    }

    public int getcpu() {
        return cpu;
    }
    @Override
    public String getHangSanXuat() {
        return hangSanXuat;
    }
    @Override
    public double getGia() {
        return gia;
    }
    @Override
    public String toString() {
    return getLoai() + ";" + getId() + ";" + getTen() + ";" + getHangSanXuat() + ";" + getGia() + ";" + getSoLuong() 
           + ";" + ram + ";" + cpu;
    }
}
