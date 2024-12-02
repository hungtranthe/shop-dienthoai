public class NhanVien {
    private String maNhanVien;
    private String tenNhanVien;
    private String diachi;
    private String soDienThoai;
    private String[] danhSachHoaDon;  // Danh sách mã hóa đơn mà nhân viên đã lập
    private String[] danhSachPhieuNhap;

    public NhanVien(String maNhanVien, String tenNhanVien, String diachi, String soDienThoai) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.diachi = diachi;
        this.soDienThoai = soDienThoai;
        this.danhSachHoaDon = new String[0]; // Ban đầu danh sách hóa đơn là rỗng
        this.danhSachPhieuNhap = new String[0];
    }

    public NhanVien() {
        this.maNhanVien = "";
        this.tenNhanVien = "";
        this.diachi = "";
        this.soDienThoai = "";
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public String getDiaChi() {
        return diachi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public String[] getDanhSachHoaDon() {
        return danhSachHoaDon;
    }

    public String[] getDanhSachPhieuNhap() {
        return danhSachPhieuNhap;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public void setDiaChi(String diaChi) {
        this.diachi = diaChi;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    // Thêm mã hóa đơn vào danh sách
    public void themHoaDon(String maHoaDon) {
        String[] newDanhSach = new String[danhSachHoaDon.length + 1];
        System.arraycopy(danhSachHoaDon, 0, newDanhSach, 0, danhSachHoaDon.length);
        newDanhSach[danhSachHoaDon.length] = maHoaDon;
        this.danhSachHoaDon = newDanhSach;
    }

    // Thêm mã phiếu nhập vào danh sách
    public void themPhieuNhap(String maPhieuNhap) {
        String[] newDanhSach = new String[danhSachPhieuNhap.length + 1];
        System.arraycopy(danhSachPhieuNhap, 0, newDanhSach, 0, danhSachPhieuNhap.length);
        newDanhSach[danhSachPhieuNhap.length] = maPhieuNhap;
        this.danhSachPhieuNhap = newDanhSach;
    }

    @Override
    public String toString() {
        return "NhanVien{" +
                "maNhanVien='" + maNhanVien + '\'' +
                ", tenNhanVien='" + tenNhanVien + '\'' +
                ", diaChi='" + diachi + '\'' +
                ", soDienThoai='" + soDienThoai + '\'' +
                ", danhSachHoaDon=" + String.join(", ", danhSachHoaDon) +
                ", danhSachPhieuNhap=" + String.join(", ", danhSachPhieuNhap) +
                '}';
    }
}
