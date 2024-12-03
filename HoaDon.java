import java.util.Date;

interface ThanhToan{
    double getThanhTien();
}

abstract class HoaDonCoSo implements ThanhToan {
    protected String maHD;
    
    public HoaDonCoSo (String maHD){
        this.maHD = maHD;
    }
    public abstract double tinhTongTien();
}

class HoaDon extends HoaDonCoSo {
    private  Date ngayNhapHD;
    private String maNV;
    private String maKH;
    private double tongTien;
    private ChiTietHoaDon[] cthd;
    
    // Constructor
    public HoaDon(String maHD, Date ngaynhapHD, NhanVien maNV, String maKH, double tongTien) {
        super(maHD);
        this.ngayNhapHD = ngaynhapHD;
        if (maNV != null) {
            this.maNV = maNV.getMaNhanVien();
        } else {
            throw new IllegalArgumentException("Nhan vien khong duoc null");
        }
        
        this.maKH = maKH;
        this.tongTien = tongTien;
    }

    public ChiTietHoaDon[] getDSChiTietHoaDon(){
        return cthd;
    }

    public String getMaHD() {
        return this.maHD;
    }

    public Date getNgayNhapHD() {
        return this.ngayNhapHD;
    }

    public String getMaNV() {
        return this.maNV;
    }

    public void setMaNV(){
        this.maNV = maNV;
    }

    public void setMaKH(){
        this.maKH = maKH;
    }

    public String getMaKH() {
        return this.maKH;
    }

    public double getTongTien() {
        return this.tongTien;
    }

    @Override
    public double getThanhTien() {
        return this.tongTien;
    }

    @Override
    public double tinhTongTien() {
        return this.tongTien;
    }

    @Override
    public String toString() {
        return "Hoa Don {" +
                "Ma hoa don ='" + maHD + '\'' +
                ", Ngay nhap HD ='" + ngayNhapHD + '\'' +
                ", Ma nhan vien ='" + maNV + '\'' +
                ", Ma khach hang ='" + maKH + '\'' +
                ", Tong tien =" + tongTien +
                '}';
    }
}