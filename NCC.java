import java.util.Scanner;  

public class NCC {  
    private int MaNCC;  
    private String DiaChi;   
    private String TenNCC;  
    private int SDT;  
    private String Mail;  

    // Constructor mac dinh  
    public NCC() {  
        this.MaNCC = 0;  
        this.DiaChi = "None";  
        this.TenNCC = "None";  
        this.SDT = 0;  
        this.Mail = "None";  
    }  

    // Constructor voi tham so  
    public NCC(int MaNCC, String DiaChi, String TenNCC, int SDT, String Mail) {  
        this.MaNCC = MaNCC;  
        this.DiaChi = DiaChi;  
        this.TenNCC = TenNCC;  
        this.SDT = SDT;  
        this.Mail = Mail;  
    }  

    // Phuong thuc nhap   
    public void nhap() {  
        Scanner scanner = new Scanner(System.in);  
        System.out.print("Nhap ma nha cung cap: ");  
        this.MaNCC = scanner.nextInt();  
        scanner.nextLine(); // Doc ky tu newline  
        System.out.print("Nhap dia chi: ");  
        this.DiaChi = scanner.nextLine();  // Doc vao chuoi  
        System.out.print("Nhap ten nha cung cap: ");  
        this.TenNCC = scanner.nextLine();  
        System.out.print("Nhap so dien thoai: ");  
        this.SDT = scanner.nextInt();   
        scanner.nextLine(); // Doc ky tu newline  
        System.out.print("Nhap email: ");  
        this.Mail = scanner.nextLine(); // Doc vao chuoi  
    }  

    // Phuong thuc xuat  
    public void xuat() {  
        System.out.println("Ma nha cung cap: " + this.MaNCC);   
        System.out.println("Dia chi: " + this.DiaChi);  
        System.out.println("Ten nha cung cap: " + this.TenNCC);  
        System.out.println("SDT: " + this.SDT);  
        System.out.println("Email: " + this.Mail);  
    }  

    // Phuong thuc main de thu nghiem  
    public static void main(String[] args) {   
        Scanner scanner = new Scanner(System.in);  

        System.out.print("Nhap so nha cung cap: ");   
        int n = scanner.nextInt();  
        NCC[] ncc = new NCC[n];  // Tao mang voi kich thuoc n  

        // Khoi tao cac doi tuong NCC  
        for (int i = 0; i < n; i++) {  
            ncc[i] = new NCC();  // Khoi tao NCC cho moi phan tu  
            ncc[i].nhap();  
        }  
        
        // Xuat du lieu  
        System.out.println("\nDanh sach nha cung cap: ");  
        for (int i = 0; i < n; i++) {  // Chi lap qua so nha cung cap da nhap  
            ncc[i].xuat();   
        }  
        scanner.close(); // Dong Scanner  
    }  
}