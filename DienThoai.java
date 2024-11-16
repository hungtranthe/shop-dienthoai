import java.util.Scanner;
public class DienThoai
{
    int MaDT;
    String TenDT;
    float DonGia;
    int Soluong;
    String MaDVT;
    private DienThoai()
    {
       MaDT = 0;
       TenDT = " ";
       DonGia = 0.0f;
       Soluong = 0;
       MaDVT = "";
    }
    public static final int max = 1000;
    public static  DienThoai[] danhSachDienThoai = new DienThoai[max];
    public static int index;
    public DienThoai(int MaDT, String TenDT, float DonGia, int Soluong, String MaDVT)
    {
            this.MaDT = MaDT;
            this.TenDT = TenDT;
            this.DonGia = DonGia;
            this.Soluong = Soluong;
            this.MaDVT = MaDVT;
    }

    /**
     *
     * @param MaDT
     * @param TenDT
     * @param DonGia
     * @param Soluong
     * @param MaDVT
     */
    public static void nhap(int MaDT, String TenDT, float DonGia, int Soluong, String MaDVT)
    {
        if(index < max)
        {
            danhSachDienThoai[index] = new DienThoai(MaDT, TenDT, DonGia, Soluong, MaDVT);
            index++;
        }
        else
        {
            System.out.println("Du lieu da day khong the nhap them!");
        }        
    }
    int searchByName(String TenDT)
    {
        for(int index = 0; index < max; index++)
        {
            if(danhSachDienThoai[index].TenDT == TenDT)
                break;
        }
        return index;
    }
    void xuat(int selection)
    {
        if(selection == 1)//Selection = 1 mean show all of list.
        {
            for(int index=0; index< max; index++)
            {
            System.out.println("Ma dien thoai: " + danhSachDienThoai[index].MaDT);
            System.out.println("Ten dien thoai: " + danhSachDienThoai[index].TenDT);
            System.out.println("Don gia: " + danhSachDienThoai[index].DonGia);
            System.out.println("So luong co trong kho: " + danhSachDienThoai[index].Soluong);
            }
        }else
        {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Nhap ten dien thoai ban muon xem!");
            this.TenDT = scanner.nextLine();
            index = searchByName(this.TenDT);
            System.out.println("Ma dien thoai: " + danhSachDienThoai[index].MaDT);
            System.out.println("Ten dien thoai: " + danhSachDienThoai[index].TenDT);
            System.out.println("Don gia: " + danhSachDienThoai[index].DonGia);
            System.out.println("So luong co trong kho: " + danhSachDienThoai[index].Soluong);            
        }
    }
    void deleteByName(String TenDT)
    {
        index = searchByName(this.TenDT);
        if(searchByName(this.TenDT))
        {
            for(int j = index; j < max -1; j++)
            {
                
            }
        }
    }
    void update()
    {
        Scanner scanner = new Scanner(System.in);        
        System.out.print("Nhap ma dien thoai muon cap nhat: ");
        this.MaDT = scanner.nextInt();
        System.out.println("Nhap so luong muon cap nhat: ");
        
    }
}