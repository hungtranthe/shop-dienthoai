
class ChiTietHoaDon implements ThanhToan {
    private String maHD;
    private String maSP;
    private int soLuongBan;
    private double donGiaBan;
        
    public ChiTietHoaDon(String maHD,String maSP,int soLuongBan,double donGiaBan){
        this.maHD = maHD;
        this.maSP = maSP;
        this.soLuongBan = soLuongBan;
        this.donGiaBan = donGiaBan;
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

    public double  getDonGiaBan(){
        return donGiaBan;
    }

    public void setDonGiaBan(double donGiaBan){
        this.donGiaBan = donGiaBan;
    } 

    @Override
    public double getThanhTien(){
        return soLuongBan*donGiaBan;
    }

    @Override
    public String toString() {
        return"ChiTietHoaDon{" +
                "Mã hóa đơn='" + maHD + '\'' +
                ",Mã điện thoại='" + maSP + '\'' +
                ", Số lượng bán =" + soLuongBan +
                ", Đơn giá bán=" + donGiaBan +
                ", Thành tiền=" + getThanhTien() +
                '}';
    }
}
