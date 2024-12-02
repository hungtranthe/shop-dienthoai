public class KhachHang {
    private String maKhachHang;
    private String tenKhachHang;
    private String diaChi;
    private String soDienThoai;
    private String[] danhSachHoaDon;

    public KhachHang(String maKhachHang, String tenKhachHang, String diaChi, String soDienThoai) {
        this.maKhachHang = maKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.danhSachHoaDon = new String[0];
    }

    public KhachHang() {
        this.maKhachHang = "";
        this.tenKhachHang = "";
        this.diaChi = "";
        this.soDienThoai = "";
    }

    public String[] getDanhSachHoaDon() {
        return danhSachHoaDon;
    }

    public void setDanhSachHoaDon(String[] danhSachHoaDon) {
        this.danhSachHoaDon = danhSachHoaDon;
    }

    public void themHoaDon(String maHoaDon) {
        String[] newDanhSach = new String[danhSachHoaDon.length + 1];
        System.arraycopy(danhSachHoaDon, 0, newDanhSach, 0, danhSachHoaDon.length);
        newDanhSach[danhSachHoaDon.length] = maHoaDon;
        this.danhSachHoaDon = newDanhSach;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }



    @Override
    public String toString() {
        return "KhachHang{" +
                "maKhachHang='" + maKhachHang + '\'' +
                ", tenKhachHang='" + tenKhachHang + '\'' +
                ", soDienThoai='" + soDienThoai + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", danhSachHoaDon=" + String.join(", ", danhSachHoaDon) +
                '}';
    }
}
