
public class NhanVien {
    private String maNhanVien;
    private String hoNhanVien;
    private String tenNhanVien;
    private String diachi;
    private String soDienThoai;
    private String luong;
    private String[] danhSachHoaDon;  // Danh sách mã hóa đơn mà nhân viên đã lập
    private String[] danhSachPhieuNhap;

    public NhanVien(String maNhanVien,String hoNhanVien, String tenNhanVien, String diachi, String soDienThoai, String luong) {
        this.maNhanVien = maNhanVien;
        this.hoNhanVien= hoNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.diachi = diachi;
        this.soDienThoai = soDienThoai;
        this.luong=luong;
        this.danhSachHoaDon = new String[0]; // Ban đầu danh sách hóa đơn là rỗng
        this.danhSachPhieuNhap = new String[0];
    }

    public NhanVien() {
        this.maNhanVien = "";
        this.hoNhanVien="";
        this.tenNhanVien = "";
        this.diachi = "";
        this.soDienThoai = "";
        this.luong="";
    }
    
    public String getHoNhanVien() {
		return hoNhanVien;
	}

	public void setHoNhanVien(String hoNhanVien) {
		this.hoNhanVien = hoNhanVien;
	}

	public String getDiachi() {
		return diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	public String getLuong() {
		return luong;
	}

	public void setLuong(String luong) {
		this.luong = luong;
	}

	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public void setDanhSachHoaDon(String[] danhSachHoaDon) {
		this.danhSachHoaDon = danhSachHoaDon;
	}

	public void setDanhSachPhieuNhap(String[] danhSachPhieuNhap) {
		this.danhSachPhieuNhap = danhSachPhieuNhap;
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
    
    public void hienThongTin() {
        System.out.println("Thông tin của nhân viên:");
        System.out.println("Mã nhân viên: " + this.maNhanVien);
        System.out.println("Họ nhân viên: " + this.hoNhanVien);
        System.out.println("Tên nhân viên: " + this.tenNhanVien);
        System.out.println("Địa chỉ: " + this.diachi);
        System.out.println("Số điện thoại: " + this.soDienThoai);
        System.out.println("Luong hien tại: "+this.luong);
        System.out.println("Danh sách hóa đơn: " + String.join(", ", danhSachHoaDon));
        System.out.println("Danh sách phiếu nhập: " + String.join(", ", danhSachPhieuNhap));
        System.out.println("-----------------------");
        System.out.println();
    }

    @Override
    public String toString() {
        return "NhanVien{" +
                "maNhanVien='" + maNhanVien + '\'' +
                ",hoNhanVien='"+hoNhanVien+'\''+
                ", tenNhanVien='" + tenNhanVien + '\'' +
                ", diaChi='" + diachi + '\'' +
                ", soDienThoai='" + soDienThoai + '\'' +
                ", luong='" + luong + '\'' +
                ", danhSachHoaDon=" + String.join(", ", danhSachHoaDon) +
                ", danhSachPhieuNhap=" + String.join(", ", danhSachPhieuNhap) +
                '}';
    }
}
