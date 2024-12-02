public class PNH {
    private String maPNH;
    private String ngayNhap;
    private NhanVien nhanVien;
    private String maNCC;
    private double tongTien;
    private CTPNH[] chiTietPNHList;
    private int soLuongChiTiet;

    public PNH(String maPNH, String ngayNhap, String maNhanVien, String maNCC, int maxChiTiet) {
        this.maPNH = maPNH;
        this.ngayNhap = ngayNhap;
        this.nhanVien = nhanVien;
        this.maNCC = maNCC;
        this.tongTien = 0.0;
        this.chiTietPNHList = new CTPNH[maxChiTiet];
        this.soLuongChiTiet = 0;
    }

    // Getter cho cac thuoc tinh
    public String getMaPNH() {
        return maPNH;
    }

    public String getNgayNhap() {
        return ngayNhap;
    }

    public NhanVien getNhanVien() {
        return nhanVien;  // Trả về đối tượng NhanVien liên kết với phiếu nhập
    }

    public String getMaNCC() {
        return maNCC;
    }

    public double getTongTien() {
        return tongTien;
    }

    // Them chi tiet phieu nhap hang
    public void themChiTietPNH(CTPNH chiTiet) {
        if (soLuongChiTiet < chiTietPNHList.length) {
            chiTietPNHList[soLuongChiTiet++] = chiTiet;
            tinhTongTien();
        }
    }

    // Tinh tong tien
    public void tinhTongTien() {
        this.tongTien = 0.0;
        for (int i = 0; i < soLuongChiTiet; i++) {
            this.tongTien += chiTietPNHList[i].getThanhTien();
        }
    }

    // Hien thi chi tiet phieu nhap hang
    public void hienThiChiTietPNH() {
        System.out.println("Phieu nhap hang: " + maPNH + " | Ngay nhap: " + ngayNhap + " | Ma nha cung cap: " + maNCC + " | Tong tien: " + tongTien);
        for (int i = 0; i < soLuongChiTiet; i++) {
            System.out.println(chiTietPNHList[i]);
        }
    }

    // Getter danh sach chi tiet phieu nhap
    public CTPNH[] getChiTietPNHList() {
        return chiTietPNHList;
    }

    public int getSoLuongChiTiet() {
        return soLuongChiTiet;
    }
    @Override
    public String toString() {
        return "PNH{" +
                "maPNH='" + maPNH + '\'' +
                ", ngayNhap='" + ngayNhap + '\'' +
                ", nhanVien=" + nhanVien.getMaNhanVien() +  // Hiển thị mã nhân viên
                ", maNCC='" + maNCC + '\'' +
                '}';
    }
}
