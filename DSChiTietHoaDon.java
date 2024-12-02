
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class DSChiTietHoaDon {
    private DSDT dsdt;
    private ChiTietHoaDon[] dscthd;
    private int soChiTiet;

    public DSChiTietHoaDon(DSDT dsdt,int maxChiTiet,String tenFile){
        this.dsdt = dsdt;
        dscthd = new ChiTietHoaDon[maxChiTiet];
        docFileChiTietHoaDon(tenFile);
    }
    
// Hàm tìm kiếm sản phẩm theo mã sản phẩm
    public DienThoai timKiemSPTheoMaSP(String maSP) {
        for (int i = 0; i < dsdt.soLuong; i++) {
            if (dsdt.getDanhSach()[i] != null && dsdt.getDanhSach()[i].getId().equals(maSP)) {
                return dsdt.getDanhSach()[i]; 
            }
        }
        return null;
    }

    public void docFileChiTietHoaDon(String tenFile){
        try {
            BufferedReader dfile= new BufferedReader(new FileReader(tenFile));
            String line;
            while((line = dfile.readLine()) != null){
                String data[] = line.split(",");
                if(data.length >= 4){
                    String maHD = data[0];
                    String maSP = data[1];
                    int soLuongBan = Integer.parseInt(data[2]);
                    double donGiaBan = Double.parseDouble(data[3]);
                    
                    DienThoai dienThoai = timKiemSPTheoMaSP(maSP);
                    if(dienThoai != null){
                        return;
                    }
                    ChiTietHoaDon chiTiet = new ChiTietHoaDon(maHD, maSP, soLuongBan, dienThoai);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void luuFileChiTietHoaDon(String tenFile){
        try {
            BufferedWriter wfile = new BufferedWriter(new FileWriter(tenFile));
            for (int i = 0; i < soChiTiet; i++) {
                if(dscthd[i] != null){
                    wfile.write(dscthd[i].toString());
                    wfile.newLine();
                }
            }
            System.out.println("Du lieu da duoc luu vao file");
        } catch (Exception e) {
        }
    }

    public void themChiTietHoaDon(String maHD){
        if(soChiTiet < dscthd.length){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Nhap ma dien thoai: ");
            String maSP = scanner.nextLine();

            DienThoai dienThoai = timKiemSPTheoMaSP(maSP);
            if(dienThoai != null){
                System.out.println("Khong tim thay dien thoai co ma: " + maSP);
                return;
            }
            
            System.out.println("Nhap so luong ban: ");
            int soLuongBan = scanner.nextInt();
            double donGiaBan = dienThoai.getGia();
            
           
            ChiTietHoaDon chiTiet =  new ChiTietHoaDon(maHD, maSP, soLuongBan, dienThoai);
            dscthd[soChiTiet] = chiTiet;
            soChiTiet++;

            System.out.println("Chi tiet hoa don da duoc them: ");
            System.out.println(chiTiet);
        }
    }

    public ChiTietHoaDon timKiemHoaChiTietDonTheoMaHD(String maHD){
        for (int i = 0; i < soChiTiet; i++) {
            if(dscthd[i] != null && dscthd[i].getMaHD().equals(maHD)){
                System.out.println("Chi tiet hoa don tim thay: " + dscthd[i].toString());
                return dscthd[i];
            }
        }
        System.out.println("Khong the tim thay chi tiet hoa don co ma: " + maHD);
        return null;
    }

    public ChiTietHoaDon timKiemHoaDonChiTietTheoMaSP(String maSP){
        for (int i = 0; i < soChiTiet; i++) {
            if(dscthd[i] != null && dscthd[i].getMaHD().equals(maSP)){
                System.out.println("Chi tiet hoa don tim thay: " + dscthd[i].toString());
                return dscthd[i];
            }
        }
        System.out.println("Khong the tim thay chi tiet hoa don co ma: " + maSP);
        return null;
    }

    public void suaChiTietHoaDon(String maHD){
        for (int i = 0; i < soChiTiet; i++) {
            if(dscthd[i].getMaHD().equals(maHD)){
                Scanner scanner = new Scanner(System.in);
                System.out.println("Nhap ma san pham moi: ");
                String maSP = scanner.nextLine();
                System.out.println("Nhap so luong ban moi: ");
                int soLuongBan = scanner.nextInt();

                DienThoai dienThoai = timKiemSPTheoMaSP(maSP);
                dscthd[i] = new ChiTietHoaDon(maHD, maSP, soLuongBan, dienThoai);
                System.out.println("Sua chi tiet hoa don thanh cong!");
            } else{
                System.out.println("Khong tim thay chi tiet hoa don voi ma: " + maHD);
            }
        }
    }

    public void xoaChiTietHoaDon(String maHD){
        for (int i = 0; i < soChiTiet; i++) {
            if(dscthd[i].getMaHD().equals(maHD)){
                for (int j = 0; j < soChiTiet - 1; j++) {
                    dscthd[j] =  dscthd[j+1];
                }
                dscthd[soChiTiet-1]= null;
                soChiTiet--;
                System.out.println("Xoa hoa don thanh cong!");
                return;
            }
        }
        System.out.println("Khong tim thay chi tiet hoa don voi ma: "+ maHD);
    }

    public void hienThiDanhSachChiTietHoaDon (){
        if(soChiTiet == 0){
            System.out.println("Khong co chi tiet hoa don nao trong danh sach.");
            return;
        }
        System.out.println("--------------------DANH SACH CHI TIET HOA DON--------------------");
        for (int i = 0; i < soChiTiet; i++) {
            if(dscthd[i] != null){
                System.out.println(dscthd[i].toString());
            }
        }
    }
}
