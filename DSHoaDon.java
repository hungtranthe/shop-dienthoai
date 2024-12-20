
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class DSHoaDon{
    private HoaDon[] dshd;
    private DSNV dsnv;
    private DSKH dskh;
    private int soHoaDon;

    public DSHoaDon(int maxHoaDon,String tenFile){
        this.dshd = new HoaDon[maxHoaDon];
        this.dsnv = new DSNV(maxHoaDon, tenFile);
        this.dskh = new DSKH(maxHoaDon, tenFile);
        this.soHoaDon = 0;
        docFileHoaDon(tenFile);
    }
    // Đọc file hóa đơn
    public void docFileHoaDon(String tenfile){
        try {
            BufferedReader dfile = new BufferedReader(new FileReader("hoadon.dat"));
            String line;
            while ((line = dfile.readLine()) != null){
                String[] data = line.split(";");
                if(data.length >= 5){
                    String maHD = data[0];
                    Date ngayNhapHD = new SimpleDateFormat("yyyy-MM-dd").parse(data[1]);
                    String maNV = data[2];
                    String maKH = data[3];
                    double tongTien = Double.parseDouble(data[4]);

                    KhachHang kh = dskh.timKhachHangTheoMa(maKH);
                    NhanVien nv = dsnv.timNhanVienTheoMa(maNV);
                    HoaDon hoaDon = new HoaDon(maHD, ngayNhapHD, nv, kh, tongTien);
                    System.out.println(line);
                } else {
                    System.out.println("Du lieu khong hop le.");
                }
            }
            System.out.println("Da doc du lieu thanh cong.");
            dfile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Lưu hóa đơn vào file
    public void luuFileHoaDon(String tenFile){
        try {
            BufferedWriter wfile = new BufferedWriter(new FileWriter(tenFile));
            String line;
            for (int i = 0; i < soHoaDon; i++) {
                if(dshd[i] != null){
                    String line1 = dshd[i].getMaHD() + ";" +
                                  dshd[i].getNgayNhapHD() + ";" +
                                  dshd[i].getMaNV() + ";" +
                                  dshd[i].getMaKH() + ";" +
                                  dshd[i].getTongTien();
                    wfile.write(line1);
                    wfile.newLine();
                }
            }
            wfile.close();
            System.out.println("Du lieu da duoc luu vao file " + tenFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Hiện thị danh sách hóa đơn
    public void hienThiDanhSachHoaDon(){
        if (soHoaDon == 0){
            System.out.println("Khong co hoa don nao trong danh sach");
            return;
        } else {
            System.out.println("---------- DANH SACH HOA DON -------");
            for (int i = 0; i < soHoaDon; i++) {
                if(dshd[i] != null){
                    System.out.println(dshd[i].toString());
                }
            }
        }
    }
    // Thêm hóa đơn
    public void themHoaDon(Scanner scanner){
        if (soHoaDon < dshd.length){
            
            System.out.println("Nhap ma hoa don: ");
            String maHD = scanner.nextLine();
            String ngayNhapStr = scanner.nextLine();
            Date ngayNhapHD = new Date();
            System.out.println("Nhap ma nhan vien: ");
            String maNV = scanner.nextLine();
            NhanVien nv = dsnv.timNhanVienTheoMa(maNV);
            System.out.println("Nhap ma khach hang: ");
            String maKH = scanner.nextLine();
            KhachHang kh = dskh.timKhachHangTheoMa(maKH);

            HoaDon hoaDon = new HoaDon(maHD, new Date(), nv, kh, soHoaDon);
            dshd[soHoaDon] = hoaDon;
            soHoaDon++;
        } else {
            System.out.println("Khong the them hoa don, da dat toi da");
        }
    }
    // Sửa hóa đơn
    public void suaHoaDon(String maHD){
        for (int i = 0; i < soHoaDon; i++) {
            if(dshd[i].getMaHD().equals(maHD)){
                Scanner scanner = new Scanner(System.in);
                System.out.println("Nhap ma nhan vien moi: ");
                String maNV = scanner.nextLine();
                NhanVien nv = dsnv.timNhanVienTheoMa(maNV);
                System.out.println("Nhap ma khach hang moi: ");
                String maKH = scanner.nextLine();
                KhachHang kh = dskh.timKhachHangTheoMa(maKH);
                dshd[i].setMaNV();
                dshd[i].setMaKH();
                System.out.println("Hóa đơn đã được cập nhật.");
                return;
            }
            System.out.println("Khong the tim thay hoa don co ma: "+ maHD);
        }

    }
    // Tìm kiếm hóa đơn theo mã hóa đơn
    public void timKiemHoaDonTheoMaHD(String maHD){
        for (int i = 0; i < soHoaDon; i++) {
            if(dshd[i] != null && dshd[i].getMaHD().equals(maHD)){
                System.out.println("Hoa don tim thay" + dshd[i].toString());
                return;
            }
        }
        System.out.println("Khong the tim thay hoa don co ma: "+ maHD);
    }
    // Tìm kiếm hóa đơn theo mã nhân viên
    public void timKiemHoaDonTheoMaNV(String maNV){
        for (int i = 0; i < soHoaDon; i++) {
            if(dshd[i] != null && dshd[i].getMaNV().equals(maNV)){
                System.out.println("Hoa don tim thay" + dshd[i].toString());
                return;
            }
        }
            System.out.println("Khong the tim thay hoa don co ma: "+ maNV);
    }
    // Tìm kiếm hóa đơn theo mã khách hàng
    public void timKiemHoaDonTheoMaKH(String maKH){
        for (int i = 0; i < soHoaDon; i++) {
            if(dshd[i] != null && dshd[i].getMaKH().equals(maKH)){
                System.out.println("Hoa don tim thay" + dshd[i].toString());
                return;
            }
        }
            System.out.println("Khong the tim thay hoa don co ma: "+ maKH);
    }

    //Xóa hóa đơn
    public void xoaHoaDon(String maHD){
        for (int i = 0; i < soHoaDon; i++) {
            if(dshd[i] != null && dshd[i].getMaHD().equals(maHD)){
                for (int j = 0; j < soHoaDon - 1; j++) {
                    dshd[i] = dshd[i +1];
                }
                dshd[soHoaDon-1] = null;
                soHoaDon--;
                System.out.println("Hoa don co ma : " + maHD + "da duoc xoa");
                return;
            }
        }
        System.out.println("Khong tim thay hoa don co ma: " + maHD);
    }
    //Xuất hóa đơn
    public void xuatHoaDon(String maHD){
        for (int i = 0; i < soHoaDon; i++) {
            if(dshd[i] != null && dshd[i].getMaHD().equals(maHD)){
                System.out.println("------------------XUAT HOA DON---------------");
                System.out.println("Ma hoa don: " + dshd[i].getMaHD());
                System.out.println("Ngay nhap: " + dshd[i].getNgayNhapHD());
                System.out.println("Ma nhan vien: " + dshd[i].getMaNV());
                System.out.println("Ma khach hang: " + dshd[i].getMaKH());
                System.out.println("Tong tien: " + dshd[i].getTongTien());
                System.out.println("=============================================");

                System.out.println("---------- CHI TIET HOA DON ----------");
                ChiTietHoaDon[] chiTietList = dshd[i].getDSChiTietHoaDon();
                for (int j = 0; j < chiTietList.length; j++) {
                    if (chiTietList[j] != null) {
                        System.out.println(chiTietList[j].toString());
                    }
                }
            }
        }
        System.out.println("Khong tim thay hoa don co ma: " + maHD);
    }
    // Tính tổng tiền
    public double tinhTongTienHoaDon(){
        double tongTien = 0;
        for (int i = 0; i < soHoaDon; i++) {
            if(dshd[i] != null){
                tongTien += dshd[i].getTongTien();
            }
        }
        return tongTien;
    }
}


