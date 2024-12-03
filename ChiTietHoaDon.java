
class ChiTietHoaDon extends HoaDonCoSo {
    
    private String maSP;
    private int soLuongBan;
    private double donGiaBan;
        
    public ChiTietHoaDon(String maHD,String maSP,int soLuongBan,DienThoai dienThoai){
        super(maHD);
        this.maSP = maSP;
        this.soLuongBan = soLuongBan;
        this.donGiaBan = dienThoai.getGia();
    }
    
    public String getMaHD(){
        return maHD;
    }
    
    public void setMaHD(String maHD){
        this.maHD = maHD;
    }

    public String getMaSP(){
        return maSP;
    }

    public void setMaSP(String maSP){
        this.maSP = maSP;
    }

    public int getSoLuongBan(){
        return soLuongBan;
    }

    public void setSoLuongBan(int soLuongBan){
        this.soLuongBan = soLuongBan;
    }

    public double getDonGiaBan(){
        return donGiaBan;
    }

    public void setDonGiaBan(double donGiaBan){
        this.donGiaBan = donGiaBan;
    } 

    @Override
    public double getThanhTien(){
        return soLuongBan * donGiaBan;
    }
    @Override
    public double  tinhTongTien(){
        return getThanhTien();
    }

    @Override
    public String toString() {
        return"ChiTietHoaDon{" +
                "Ma hoa don ='" + maHD + '\'' +
                ",Ma dien thoai ='" + maSP + '\'' +
                ", So luong ban ='" + soLuongBan +
                ", don gia ban ='" + donGiaBan +
                ", Thanh tien ='" + getThanhTien() +
                '}';
    }
}
