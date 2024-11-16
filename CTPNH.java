import java.util.Scanner;  

public class CTPNH {
    private PNH MaPNH;  
    private DTHOAI MaDT;  
    private int SL;  
    private DSSP Dgia;  
    private double TTien;  


public  CTPNH() {
   this.MaPNH = 0;  
   this.MaDT = 0;  
   this.SL = 0;  
   this.Dgia = 0;  
   this.TTien = 0;  

}
public  CTPNH(int MaPNH, int MaDT, int SL, int Dgia, int TTien) {
    this.MaPNH = MaPNH;  
    this.MaDT = MaDT;  
    this.SL = SL;  
    this.Dgia = Dgia;  
    this.TTien = TTien;  
 
 }
 public PNH getMaPNH{
    return MaPNH;
  } 
 
  public DTHOAI getMaDT{
    return MaDT;
  }
  public int getSL{
    return SL;
  }
  public DSSP getDgia{
    return Dgia;
  }

 public void xuat() {  
    System.out.println("Ma phieu nhap hanh: " + this.MaPNH); 
    System.out.println("Ma dien thoai: " + this.MaDT);  
    System.out.println("So luong: " + this.SL);  
    System.out.println("Don gia: " + this.Dgia);   
    System.out.print("Thanh tien: " +(this.Dgia * this.SL));  
}  
public static void main(String[] args) {   
   CTPNH ctpnh= new CTPNH();
    // Khoi tao cac doi tuong NCC  
   
    
    // Xuat du lieu  
    System.out.println("\nDanh sach phieu nhap hang: ");  
    for (int i = 0; i < ctpnh.length(); i++) {  
        ctpnh.xuat();   
    }  
}  

}