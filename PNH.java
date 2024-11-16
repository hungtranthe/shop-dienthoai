import java.util.Scanner;  

public class PNH {  
    private int MaPNH;  
    private int NgayNH;   
    private DTHOAI MaDT;  
    private NCC MaNCC;  
    private NHANVIEN NVlapphieu;  

    // Constructor mac dinh  
    public PNH() { 
        this.MaPNH = 0;  
        this.NgayNH = 0;  
        this.MaDT = 0;  
        this.MaNCC = 0;  
        this.NVlapphieu = "None";  
    }  

    // Constructor voi tham so  
    public PNH( int MaPNH, int NgayNH, int MaDT, int MaNCC, String NVlapphieu) {  
        this.MaPNH = MaPNH;  
        this.NgayNH  = NgayNH ;  
        this.MaDT = MaDT; 
        this.MaNCC = MaNCC;  
        this.NVlapphieu = NVlapphieu;  
    }  

    // Phuong thuc nhap   
  public int getMaPNH{
    return MaPNH;
  } 
  public int getNgayNH{
    return NgayNH;
  }
  public DTHOAI getMaDT{
    return MaDT;
  }
  public NCC getMaNCC{
    return MaNCC;
  }
  public NHANVIEN getNVlapphieu{
    return NVlapphieu;
  }

    // Phuong thuc xuat  
    public void xuat() {  
        System.out.println("Ma phieu nhap hanh: " + this.MaPNH);  
        System.out.println("Ngay nhap hang: " + this.NgayNH);  
        System.out.println("Ma dien thoai: " + this.MaDT);  
        System.out.println("Ma nha cung cap: " + this.MaNCC);   

        System.out.println("Nhan vien nhap phieu: " + this.NVlapphieu);  
    }  

    // Phuong thuc main de thu nghiem  
    public static void main(String[] args) {   
        PNH pnh =new PNH();

        System.out.print("Nhap so phieu nhap hang: ");   
       

    
        
        // Xuat du lieu  
        System.out.println("\nDanh sach phieu nhap hang: ");  
        for (int i = 0; i < pnh.length(); i++) {  
            pnh.xuat();   
        }  
    }  

   
}