public class CTPNH {
    private String maPNH;
    private String maSanPham;
    private int soLuong;
    private double donGia;
    private double thanhTien;

    public CTPNH(String maPNH, String maSanPham, int soLuong, double donGia) {
        this.maPNH = maPNH;
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = tinhThanhTien();
    }

    // Tinh thanh tien
    public double tinhThanhTien() {
        return soLuong * donGia;
    }

    // Getter cho cac thuoc tinh
    public String getMaPNH() {
        return maPNH;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    @Override
    public String toString() {
        return "CTPNH{" +
                "maPNH='" + maPNH + '\'' +
                ", maSanPham='" + maSanPham + '\'' +
                ", soLuong=" + soLuong +
                ", donGia=" + donGia +
                ", thanhTien=" + thanhTien +
                '}';
    }
}
