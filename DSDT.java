import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DSDT {
    private DienThoai[] danhSach;
    public int soLuong;
    private static final int KICH_THUOC_BAN_DAU = 100;

    public DienThoai[] getDanhSach(){
        return danhSach;
    }

    public DSDT(String tenFile) {
        this.danhSach = new DienThoai[KICH_THUOC_BAN_DAU];
        this.soLuong = 0;
        // Đọc dữ liệu từ file khi khởi tạo
        docDuLieu(tenFile);
    }
    
    public void docDuLieu(String tenFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(tenFile))) {
            String line;
        
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                
                // Kiem tra neu dong du lieu it hon 6 phan tu, thi khong hop le
                if (parts.length < 6) {
                    System.out.println("Dong du lieu khong hop le hoac thieu thong tin: " + line);
                    continue;  // Bo qua dong khong hop le
                }
        
                String loai = parts[0];
                String id = parts[1];
                String ten = parts[2];
                String hangSanXuat = parts[3];
                double gia = Double.parseDouble(parts[4]);
                int soLuongSP = Integer.parseInt(parts[5]);
        
                // Kiem tra loai san pham va tao doi tuong tuong ung
                if (loai.equals("GamingPhone") && parts.length >= 8) {
                    if (parts.length == 8) {  // Dam bao co du thong tin
                        int ram = Integer.parseInt(parts[6]);
                        int boXuLy = Integer.parseInt(parts[7]);
                        themDienThoai(new GamingPhone(id, ten, hangSanXuat, gia, soLuongSP, ram, boXuLy));
                    } else {
                        System.out.println("Du lieu thieu cho GamingPhone: " + line);
                    }
                } else if (loai.equals("FoldablePhone") && parts.length >= 8) {
                    if (parts.length == 8) {  // Dam bao co du thong tin cho FoldablePhone
                        double kichThuocKhiGap = Double.parseDouble(parts[6]);
                        double kichThuocKhiMo = Double.parseDouble(parts[7]);
                        themDienThoai(new FoldablePhone(id, ten, hangSanXuat, gia, soLuongSP, kichThuocKhiGap, kichThuocKhiMo));
                    } else {
                        System.out.println("Du lieu thieu cho FoldablePhone: " + line);
                    }
                } else {
                    System.out.println("Dong du lieu khong hop le hoac thieu thong tin: " + line);
                }
            }
            System.out.println("Du lieu da duoc doc tu file. So luong san pham: " + soLuong);
        } catch (IOException | NumberFormatException e) {
            System.out.println("Loi khi doc du lieu: " + e.getMessage());
        }
    }
    
    // Lưu dữ liệu vào file
    public void luuDuLieu(String tenFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tenFile))) {
            for (int i = 0; i < soLuong; i++) {
                if (danhSach[i] != null) {
                    writer.write(danhSach[i].toString());  // Ghi thông tin của đối tượng
                    writer.newLine();  // Xuống dòng sau mỗi đối tượng
                }
            }
            System.out.println("Du lieu da duoc luu vao file: " + tenFile);
        } catch (IOException e) {
            System.out.println("Loi khi luu du lieu: " + e.getMessage());
        }
    }

    public void capNhatSoLuong(String var1, int var2) {
        boolean var3 = false;
  
        for(int var4 = 0; var4 < this.soLuong; ++var4) {
           if (this.danhSach[var4] != null && this.danhSach[var4].id.equals(var1)) {
              var3 = true;
              DienThoai var10000 = this.danhSach[var4];
              var10000.soLuong += var2;
              System.out.println("San pham " + this.danhSach[var4].getTen() + " da duoc nhap them " + var2 + " san pham. So luong hien tai: " + this.danhSach[var4].getSoLuong());
              this.luuDuLieu("D:\\OOP\\dienThoai.dat");
           }
        }
  
    }

    public void hienThiDanhSach() {
        if (soLuong == 0) {
            System.out.println("Danh sach dien thoai hien dang trong.");
            return;
        }
    
        // Tiêu đề bảng
        System.out.printf("| %-10s | %-20s | %-15s | %-10s | %-8s | %-15s | %-20s |\n",
                          "ID", "Ten", "Hang SX", "Gia", "SL", "Loai SP", "Thong tin them");
        System.out.println("-----------------------------------------------------------------------------------------------");
    
        // Duyệt qua danh sách và hiển thị
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i] != null) {
                // Hiển thị thông tin cơ bản
                System.out.printf("| %-10s | %-20s | %-15s | %-10.2f | %-8d | %-15s | ",
                                  danhSach[i].getId(),
                                  danhSach[i].ten,
                                  danhSach[i].hangSanXuat,
                                  danhSach[i].gia,
                                  danhSach[i].soLuong,
                                  danhSach[i].getLoai());
    
                // Hiển thị thông tin thêm (đặc trưng của từng loại)
                danhSach[i].hienThiThongTin();
            }
        }
    }
    // Cập nhật thông tin điện thoại
    public void capNhatDienThoai() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap ID dien thoai can cap nhat: ");
        String id = scanner.nextLine();

        boolean timThay = false;

        // Tìm sản phẩm cần cập nhật trong danh sách
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i] != null && danhSach[i].id.equals(id)) {
                timThay = true;

                // Cập nhật thông tin
                System.out.println("San pham tim thay: " + danhSach[i].getTen());
                System.out.print("Nhap ten moi (hien tai: " + danhSach[i].getTen() + "): ");
                String tenMoi = scanner.nextLine();
                System.out.print("Nhap gia moi (hien tai: " + danhSach[i].gia + "): ");
                double giaMoi = scanner.nextDouble();
                System.out.print("Nhap so luong moi (hien tai: " + danhSach[i].soLuong + "): ");
                int soLuongMoi = scanner.nextInt();
                scanner.nextLine();  // Đọc dòng dư thừa

                // Cập nhật thông tin trong đối tượng
                danhSach[i].ten = tenMoi;
                danhSach[i].gia = giaMoi;
                danhSach[i].soLuong = soLuongMoi;

                System.out.println("San pham da duoc cap nhat.");
                luuDuLieu("D:\\OOP\\dienThoai.dat");
                break;
            }
        }

        if (!timThay) {
            System.out.println("Khong tim thay san pham voi ID: " + id);
        }
    }

    // Thêm sản phẩm vào danh sách và lưu vào file
    public void themDienThoai(DienThoai dienThoai) {
        if (soLuong == danhSach.length) {
            DienThoai[] danhSachMoi = new DienThoai[danhSach.length * 2];
            System.arraycopy(danhSach, 0, danhSachMoi, 0, danhSach.length);
            danhSach = danhSachMoi;
        }
        danhSach[soLuong++] = dienThoai;
        // Lưu dữ liệu vào file ngay sau khi thêm sản phẩm
        luuDuLieu("D:\\OOP\\dienThoai.dat");
    }

    public boolean kiemTraMaDienThoai(String id) {
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i].getId().equalsIgnoreCase(id)) {
                return true; // Mã đã tồn tại
            }
        }
        return false; // Mã chưa tồn tại
    }

    public void nhapthongtin(Scanner scanner){
        while(true){
            System.out.println("Chon loai dien thoai de nhap thong tin: ");
            System.out.println("1. Gaming Phone");
            System.out.println("2. Foldable Phone");
            System.out.print("Su lua chon cua ban: ");
            
            int luachon = -1;
            try{
                luachon =  Integer.parseInt(scanner.nextLine()); //Doc lua chon cua nguoi dung.
            }catch(NumberFormatException e){
                System.out.println("Lua chon khong hop le, vui long thu lai.");
                continue;
            }
            DienThoai dienthoaiMoi = null;
            switch(luachon){
                case 1: 
                dienthoaiMoi  = new GamingPhone();
                break;
                
                case 2:
                dienthoaiMoi = new FoldablePhone();
                break;

                default:
                System.out.println("Lua chon khong hop le, vui long nhap lai.");
                continue;
            }
            if(dienthoaiMoi != null){
                System.out.println("Nhap thong tin cho san pham: ");
                dienthoaiMoi.nhapThongTin(scanner); //goi ham nhap thong tin tu lop con.

                //kiem tra id
            if(kiemTraMaDienThoai(dienthoaiMoi.getId())){
                System.out.println("Ma dien thoai da ton tai, khong the them.");
            }else{
                themDienThoai(dienthoaiMoi);
                System.out.println("Da them dien thoai thanh cong.");
                break;
                }
            }
        }
    }

    // Tìm kiếm nâng cao
    public void timKiemNangCao(Scanner scanner) {
        System.out.println("Chon tieu chi tim kiem:");
        System.out.println("1. Tim theo ma san pham");
        System.out.println("2. Tim theo ten san pham");
        System.out.println("3. Tim theo loai dien thoai");
        System.out.println("4. Tim theo gia san pham");
        System.out.println("5. Tim theo khoang gia");
        System.out.println("6. Tim kiem theo thuoc tinh cua dienthoai");
        System.out.print("Nhap lua chon cua ban (1-6): ");
        int luaChon = scanner.nextInt();
        scanner.nextLine();  // Đọc dòng dư thừa sau khi nhập số
    
        boolean timThay = false;

        switch (luaChon) {
            case 1://Tìm theo mã sản phẩm
                System.out.print("Nhap ma san pham de tim kiem: ");
                String masanpham = scanner.nextLine();
                System.out.printf("| %-10s | %-20s | %-15s | %-10s | %-8s | %-15s | %-20s |\n",
                "ID", "Ten", "Hang SX", "Gia", "SL", "Loai SP", "Thong tin them");
                System.out.println("-----------------------------------------------------------------------------------------------");
                for (int i = 0; i < soLuong; i++) {
                    if (danhSach[i] != null && danhSach[i].getId().contains(masanpham)) {
                        System.out.printf("| %-10s | %-20s | %-15s | %-10.2f | %-8d | %-15s | ",
                                      danhSach[i].getId(),
                                      danhSach[i].getTen(),
                                      danhSach[i].getHangSanXuat(),
                                      danhSach[i].getGia(),
                                      danhSach[i].getSoLuong(),
                                      danhSach[i].getLoai());
                    danhSach[i].hienThiThongTin(); // Hiển thị thông tin chi tiết
                    timThay = true;
                    }
                }
                break;
            case 2: // Tìm theo tên sản phẩm
                System.out.print("Nhap ten dien thoai de tim kiem: ");
                String tenSanPham = scanner.nextLine();
                System.out.printf("| %-10s | %-20s | %-15s | %-10s | %-8s | %-15s | %-20s |\n",
                "ID", "Ten", "Hang SX", "Gia", "SL", "Loai SP", "Thong tin them");
                System.out.println("-----------------------------------------------------------------------------------------------");
                for (int i = 0; i < soLuong; i++) {
                    if (danhSach[i] != null && danhSach[i].getTen().contains(tenSanPham)) {
                        danhSach[i].hienThiThongTin();
                        timThay = true;
                    }
                }
                break;
    
            case 3: // Tìm theo loại điện thoại
                System.out.print("Nhap loai dien thoai (GamingPhone / FoldablePhone) de tim kiem: ");
                String loaiDienThoai = scanner.nextLine();
                System.out.printf("| %-10s | %-20s | %-15s | %-10s | %-8s | %-15s | %-20s |\n",
                "ID", "Ten", "Hang SX", "Gia", "SL", "Loai SP", "Thong tin them");
                System.out.println("-----------------------------------------------------------------------------------------------");
                for (int i = 0; i < soLuong; i++) {
                    if (danhSach[i] != null && danhSach[i].getLoai().equalsIgnoreCase(loaiDienThoai)) {
                        danhSach[i].hienThiThongTin();
                        timThay = true;
                    }
                }
                break;
    
            case 4: // Tìm theo giá sản phẩm cụ thể
                System.out.print("Nhap gia dien thoai can tim: ");
                double giaSanPham = scanner.nextDouble();
                System.out.printf("| %-10s | %-20s | %-15s | %-10s | %-8s | %-15s | %-20s |\n",
                "ID", "Ten", "Hang SX", "Gia", "SL", "Loai SP", "Thong tin them");
                System.out.println("-----------------------------------------------------------------------------------------------");
                for (int i = 0; i < soLuong; i++) {
                    if (danhSach[i] != null && danhSach[i].gia == giaSanPham) {
                        danhSach[i].hienThiThongTin();
                        timThay = true;
                    }
                }
                break;
    
            case 5: // Tìm theo khoảng giá
                System.out.print("Nhap gia thap nhat: ");
                double giaThapNhat = scanner.nextDouble();
                System.out.print("Nhap gia cao nhat: ");
                double giaCaoNhat = scanner.nextDouble();
                System.out.printf("| %-10s | %-20s | %-15s | %-10s | %-8s | %-15s | %-20s |\n",
                "ID", "Ten", "Hang SX", "Gia", "SL", "Loai SP", "Thong tin them");
                System.out.println("-----------------------------------------------------------------------------------------------");
                for (int i = 0; i < soLuong; i++) {
                    if (danhSach[i] != null && danhSach[i].gia >= giaThapNhat && danhSach[i].gia <= giaCaoNhat) {
                        danhSach[i].hienThiThongTin();
                        timThay = true;
                    }
                }
                break;
            
            case 6: timKiemTheoThuocTinhRieng(scanner);//Tìm kiếm theo thuộc tính riêng
                timThay = true;
                break;
            default:
                System.out.println("Lua chon khong hop le!");
                return; // Nếu lựa chọn không hợp lệ, thoát khỏi phương thức
        }
    
        if (!timThay) {
            System.out.println("Khong tim thay san pham nao phu hop.");
        }
    }

    public Object timThongTintheoMasp(String masanpham, String Thongtincanlay){
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i] != null && danhSach[i].getId().contains(masanpham)) {
                switch (Thongtincanlay.toLowerCase()) {
                    case "dongia":
                        return danhSach[i].getGia(); // Trả về đơn giá
                    case "ten":
                        return danhSach[i].getTen(); // Trả về tên sản phẩm
                    case "hangsanxuat":
                        return danhSach[i].getHangSanXuat(); // Trả về hãng sản xuất
                    case "soluong":
                        return danhSach[i].getSoLuong(); // Trả về số lượng
                    case "loai":
                        return danhSach[i].getLoai(); // Trả về loại sản phẩm
                    default:
                        return "Thông tin yeu cau khong ton tai!";
                }
            }
        }
        return "Khong tim thay san pham voi ma: " + masanpham;
    }


    public void timKiemTheoThuocTinhRieng(Scanner scanner) {
        System.out.println("Tim kiem theo thuoc tinh rieng cua lop con:");
        System.out.println("1. Tim theo RAM (GamingPhone)");
        System.out.println("2. Tim theo CPU (GamingPhone)");
        System.out.println("3. Tim theo kich thuoc khi gap (FoldablePhone)");
        System.out.println("4. Tim theo kich thuoc khi mo (FoldablePhone)");
        System.out.print("Nhap lua chon cua ban: ");
        
        int luaChon = -1;
        try {
            luaChon = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Lua chon khong hop le!");
            return;
        }
        
        boolean timThay = false;
        
       
    
        switch (luaChon) {
            case 1: // Tìm theo RAM trong GamingPhone
                System.out.print("Nhap dung luong RAM can tim: ");
                int ramTimKiem = Integer.parseInt(scanner.nextLine());
                System.out.println("Ket qua tim kiem:");
                 // In tiêu đề bảng
                System.out.printf("| %-10s | %-20s | %-15s | %-10s | %-8s | %-15s | %-20s |\n",
                            "ID", "Ten", "Hang SX", "Gia", "SL", "Loai SP", "Thong tin them");
                System.out.println("-----------------------------------------------------------------------------------------------");
                for (int i = 0; i < soLuong; i++) {
                    if (danhSach[i] instanceof GamingPhone) {
                        GamingPhone gp = (GamingPhone) danhSach[i];
                        if (gp.getRam() == ramTimKiem) {
                            // In thông tin sản phẩm theo định dạng bảng
                            System.out.printf("| %-10s | %-20s | %-15s | %-10.2f | %-8d | %-15s | ",
                                              gp.getId(), gp.getTen(), gp.getHangSanXuat(),
                                              gp.getGia(), gp.getSoLuong(), gp.getLoai());
                            gp.hienThiThongTin();  // Hiển thị thông tin chi tiết của GamingPhone
                            timThay = true;
                        }
                    }
                }
                break;
    
            case 2: // Tìm theo CPU trong GamingPhone
                System.out.print("Nhap CPU can tim: ");
                int cpuTimKiem = Integer.parseInt(scanner.nextLine());
                System.out.println("Ket qua tim kiem:");
                System.out.printf("| %-10s | %-20s | %-15s | %-10s | %-8s | %-15s | %-20s |\n",
                "ID", "Ten", "Hang SX", "Gia", "SL", "Loai SP", "Thong tin them");
                System.out.println("-----------------------------------------------------------------------------------------------");
                for (int i = 0; i < soLuong; i++) {
                    if (danhSach[i] instanceof GamingPhone) {
                        GamingPhone gp = (GamingPhone) danhSach[i];
                        if (gp.getcpu() == cpuTimKiem) {
                            // In thông tin sản phẩm theo định dạng bảng
                            System.out.printf("| %-10s | %-20s | %-15s | %-10.2f | %-8d | %-15s | ",
                                              gp.getId(), gp.getTen(), gp.getHangSanXuat(),
                                              gp.getGia(), gp.getSoLuong(), gp.getLoai());
                            gp.hienThiThongTin();  // Hiển thị thông tin chi tiết của GamingPhone
                            timThay = true;
                        }
                    }
                }
                break;
    
            case 3: // Tìm theo kích thước khi gập trong FoldablePhone
                System.out.print("Nhap kich thuoc khi gap can tim: ");
                double kichThuocGapTimKiem = Double.parseDouble(scanner.nextLine());
                System.out.println("Ket qua tim kiem:");
                System.out.printf("| %-10s | %-20s | %-15s | %-10s | %-8s | %-15s | %-20s |\n",
                "ID", "Ten", "Hang SX", "Gia", "SL", "Loai SP", "Thong tin them");
                System.out.println("-----------------------------------------------------------------------------------------------");
                for (int i = 0; i < soLuong; i++) {
                    if (danhSach[i] instanceof FoldablePhone) {
                        FoldablePhone fp = (FoldablePhone) danhSach[i];
                        if (fp.getKichThuocGap() == kichThuocGapTimKiem) {
                            // In thông tin sản phẩm theo định dạng bảng
                            System.out.printf("| %-10s | %-20s | %-15s | %-10.2f | %-8d | %-15s | ",
                                              fp.getId(), fp.getTen(), fp.getHangSanXuat(),
                                              fp.getGia(), fp.getSoLuong(), fp.getLoai());
                            fp.hienThiThongTin();  // Hiển thị thông tin chi tiết của FoldablePhone
                            timThay = true;
                        }
                    }
                }
                break;
    
            case 4: // Tìm theo kích thước khi mở trong FoldablePhone
                System.out.print("Nhap kich thuoc khi mo can tim: ");
                double kichThuocMoTimKiem = Double.parseDouble(scanner.nextLine());
                System.out.println("Ket qua tim kiem:");
                System.out.printf("| %-10s | %-20s | %-15s | %-10s | %-8s | %-15s | %-20s |\n",
                "ID", "Ten", "Hang SX", "Gia", "SL", "Loai SP", "Thong tin them");
                System.out.println("-----------------------------------------------------------------------------------------------");
                for (int i = 0; i < soLuong; i++) {
                    if (danhSach[i] instanceof FoldablePhone) {
                        FoldablePhone fp = (FoldablePhone) danhSach[i];
                        if (fp.getKichThuocMo() == kichThuocMoTimKiem) {
                            // In thông tin sản phẩm theo định dạng bảng
                            System.out.printf("| %-10s | %-20s | %-15s | %-10.2f | %-8d | %-15s | ",
                                              fp.getId(), fp.getTen(), fp.getHangSanXuat(),
                                              fp.getGia(), fp.getSoLuong(), fp.getLoai());
                            fp.hienThiThongTin();  // Hiển thị thông tin chi tiết của FoldablePhone
                            timThay = true;
                        }
                    }
                }
                break;
    
            default:
                System.out.println("Lua chon khong hop le!");
                return;  // Nếu lựa chọn không hợp lệ, thoát khỏi phương thức
        }
        
        if (!timThay) {
            System.out.println("Khong tim thay san pham nao phu hop.");
        }
    }
    
    
    // Xóa sản phẩm khỏi danh sách
    public void xoaDienThoai(Scanner scanner) {
        System.out.print("Nhap ID dien thoai can xoa: ");
        String id = scanner.nextLine();
    
        boolean xoaThanhCong = false;
        
        // Duyệt qua danh sách và tìm sản phẩm với ID cần xóa
        for (int i = 0; i < soLuong; i++) {
            // Kiểm tra nếu danhSach[i] không phải null và có id trùng với id cần xóa
            if (danhSach[i] != null && danhSach[i].getId().equals(id)) {
                // Xóa sản phẩm bằng cách di chuyển các phần tử phía sau lên
                for (int j = i; j < soLuong - 1; j++) {
                    danhSach[j] = danhSach[j + 1];  // Di chuyển phần tử tiếp theo về phía trước
                }
                danhSach[soLuong - 1] = null;  // Set phần tử cuối cùng thành null
                soLuong--;  // Giảm số lượng sản phẩm
                System.out.println("San pham da duoc xoa.");
                xoaThanhCong = true;
                break;
            }
        }
    
        if (!xoaThanhCong) {
            System.out.println("Khong tim thay san pham voi ID: " + id);
        }
    }
}    
