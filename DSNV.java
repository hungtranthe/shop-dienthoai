
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DSNV {
    private NhanVien[] danhSachNhanVien;
    private int soLuongNhanVien; 
    
    
    String tenFile="NhanVien.txt";//vi tri luu file data nhan vien
    
    public DSNV(int maxNhanVien, String tenfile) {
        this.danhSachNhanVien = new NhanVien[maxNhanVien];
        this.soLuongNhanVien = 0;
        
        docDuLieu(tenFile);
    }

    public NhanVien[] getDanhSacNhanVien(){
        return danhSachNhanVien;
    }

    // Menu quản lý nhân viên
    public void menu(DSHoaDon dshd, DSPNH pnh) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== QUẢN LÝ NHÂN VIÊN =====");
            System.out.println("1. Thêm nhân viên mới");
            System.out.println("2. Hiển thị danh sách nhân viên");
            System.out.println("3. Tìm nhân viên theo mã");
            System.out.println("4. Tìm nhân viên theo tên");
            System.out.println("5. Sửa thông tin nhân viên");
            System.out.println("6. xoa nhan vien");
            System.out.println("7. thoat");
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = Integer.parseInt(scanner.nextLine());
           // scanner.nextLine();

            switch (choice) {
                case 1:
                    themNhanVienMoi(scanner, dshd, pnh); // Thêm nhân viên mới  ok
                    break;
                case 2:
                    hienThiDanhSachNhanVien(dshd, pnh); // Hiển thị danh sách nhân viên ok
                    break;
                case 3:
                    timKiemNhanVien(scanner); // Tìm nhân viên theo mã ok
                    break;
                case 4:
                    timNhanVienTheoTen(scanner); // Tìm nhân viên theo tên ok
                    break; // Bổ sung break
                case 5:
                    suaThongTinNhanVien(scanner); // Sửa thông tin nhân viên
                    break; // Bổ sung break
                case 6:
                	xoaNhanVien(scanner); //xoa nhan vien ok
                	break;
                case 7:
                    System.out.println("Thoát chương trình quản lý nhân viên.");
                    return; // Thoát khỏi chương trình
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng thử lại.");
            }
        } while (choice != 7); // Vòng lặp menu tiếp tục đến khi chọn Thoát
    }
    public void xoaNhanVien(Scanner sc) {
    	System.out.println("Nhap ID nhan vien can xoa: ");
    	String maNV =sc.nextLine();
    	boolean kt=false;
    	for (int i=0;i<soLuongNhanVien;i++) {
    		if(danhSachNhanVien[i].getMaNhanVien().equals(maNV)) {
    			kt=true;
    			danhSachNhanVien[i].setMaNhanVien("X"+maNV);
    			luuDuLieu(tenFile);
    			System.out.println("xoa thanh cong");
    			break;
    		}
    		
    	}
    	if(!kt) System.out.println("Khong tim thay nhan vien co ma "+maNV);
    		
    }
    //tim nhan vien theo ten
    public void timNhanVienTheoTen(Scanner sc) {
        System.out.println("Nhập tên nhân viên cần tìm: ");
        String ten = sc.nextLine();
        boolean kt = false;

        for (int i = 0; i < soLuongNhanVien; i++) {
            NhanVien nv = danhSachNhanVien[i];
            // Kiểm tra nhân viên chưa bị đánh dấu xóa (mã không bắt đầu bằng 'x') và tên trùng khớp
            if (nv != null && 'X' != nv.getMaNhanVien().charAt(0) && nv.getTenNhanVien().equalsIgnoreCase(ten)) {
                nv.hienThongTin();
                kt = true; // Đánh dấu tìm thấy
            }
        }

        if (!kt) { // Nếu không tìm thấy, thông báo
            System.out.println("Không tìm thấy nhân viên có tên: " + ten);
        }
    }

    // Thêm nhân viên mới
    public void themNhanVienMoi(Scanner scanner, DSHoaDon dshd, DSPNH pnh) {
//        System.out.print("Nhap ma nhan vien: ");
//        String maNhanVien = scanner.nextLine();
    	int x=soLuongNhanVien+1;
    	String maNhanVien = "NV"+x;
        System.out.println("nhap ho nhan vien");
        String hoNhanVien= scanner.nextLine();
        
        System.out.print("Nhap ten nhan vien: ");
        String tenNhanVien = scanner.nextLine();

        System.out.print("Nhap dia chi: ");
        String diaChi = scanner.nextLine();

        System.out.print("Nhap so dien thoai: ");
       
        String soDienThoai = KTSDT.kiemTra();
        System.out.println("Nhap Luong Nhan Vien: ");
        String luong=scanner.nextLine();

        NhanVien nhanVien = new NhanVien(maNhanVien,hoNhanVien, tenNhanVien, diaChi, soDienThoai,luong);
        themNhanVien(nhanVien);

        // Lưu lại danh sách nhân viên sau khi thêm
        luuDuLieu(tenFile);
        System.out.println("Thong tin nhan vien da duoc them.");
    }

    // Thêm nhân viên vào danh sách
    public void themNhanVien(NhanVien nhanVien) {
        
        if (soLuongNhanVien < danhSachNhanVien.length) {
            danhSachNhanVien[soLuongNhanVien++] = nhanVien;
            System.out.println("Them nhan vien: " + nhanVien.getTenNhanVien());
            
        } else {
            System.out.println("Danh sach nhan vien da day.");
        }
        
    }
   
    // Hiển thị danh sách nhân viên cùng với hóa đơn và phiếu nhập của họ
    public void hienThiDanhSachNhanVien(DSHoaDon dshd, DSPNH pnh) {
            for (int i = 0; i < soLuongNhanVien; i++) {
            	
                NhanVien nv = danhSachNhanVien[i];
                if (nv.getMaNhanVien().charAt(0)=='X') {
                	continue;
                
                }
                System.out.println(nv);
                System.out.println("Hoa don nhan vien da lap:");
                for (String maHoaDon : nv.getDanhSachHoaDon()) {
                    System.out.println("Ma hoa don: " + maHoaDon);
                }
                System.out.println("Phieu nhap nhan vien da lap:");
                for (String maPhieuNhap : nv.getDanhSachPhieuNhap()) {
                    System.out.println("Ma phieu nhap: " + maPhieuNhap);
                }
                System.out.println("-------------------------------");
            }
        
    }

    // Tìm kiếm nhân viên theo mã
    public void timKiemNhanVien(Scanner scanner) {
        System.out.print("Nhap ma nhan vien: ");
        String maNhanVien = scanner.nextLine();

        NhanVien nv = timNhanVienTheoMa(maNhanVien);
        if (nv != null) {
            System.out.println("Thong tin nhan vien:");
            System.out.println(nv);
        } else {
            System.out.println("Khong tim thay nhan vien voi ma: " + maNhanVien);
        }
    }

    // Tìm nhân viên theo mã
    public NhanVien timNhanVienTheoMa(String maNhanVien) {
        for (int i = 0; i < soLuongNhanVien; i++) {
            if (danhSachNhanVien[i].getMaNhanVien().equals(maNhanVien)) {
                return danhSachNhanVien[i];
            }
        }
        return null;
    }

    // Sửa thông tin nhân viên
    public void suaThongTinNhanVien(Scanner scanner) {
    	System.out.print("Nhap ma nhan vien can sua: ");
    	String maNhanVien = scanner.nextLine();

    // Tìm nhân viên theo mã
    NhanVien nhanVien = timNhanVienTheoMa(maNhanVien);
    	if (nhanVien == null) {
    		System.out.println("Khong tim thay nhan vien voi ma: " + maNhanVien);
    		return;
    }

    // Hiển thị thông tin hiện tại
    System.out.println("Thong tin hien tai cua nhan vien:");
    System.out.println("Ma nhan vien: " + nhanVien.getMaNhanVien());
    System.out.println("Ten nhan vien: " + nhanVien.getTenNhanVien());
    System.out.println("Dia chi: " + nhanVien.getDiaChi());
    System.out.println("So dien thoai: " + nhanVien.getSoDienThoai());

    // Nhập thông tin mới
    System.out.print("Nhap ten nhan vien moi (nhap de giu nguyen): ");
    String tenNhanVienMoi = scanner.nextLine();
    if (!tenNhanVienMoi.isEmpty()) {
        nhanVien.setTenNhanVien(tenNhanVienMoi);
    }

    System.out.print("Nhap dia chi moi (nhap de giu nguyen): ");
    String diaChiMoi = scanner.nextLine();
    if (!diaChiMoi.isEmpty()) {
        nhanVien.setDiaChi(diaChiMoi);
    }

    System.out.print("Nhap so dien thoai moi (nhap de giu nguyen): ");
    String soDienThoaiMoi = scanner.nextLine();
    if (!soDienThoaiMoi.isEmpty()) {
        nhanVien.setSoDienThoai(soDienThoaiMoi);
    }

    System.out.println("Thong tin nhan vien da duoc cap nhat.");
    luuDuLieu("tenFile"); // Ghi lại vào file
}


    // Đọc danh sách nhân viên từ file
    public void docDuLieu(String tenFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(tenFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length < 6) {
                    System.out.println("Du lieu khong hop le: " + line);
                    continue;
                }
                
                String maNhanVien = parts[0];	
                String hoNhanVien=parts[1];
                String tenNhanVien = parts[2];
                String diaChi = parts[3];
                String soDienThoai = parts[4];
                String luong=parts[5];
                NhanVien nhanVien = new NhanVien(maNhanVien,hoNhanVien, tenNhanVien, diaChi, soDienThoai, luong);
                themNhanVien(nhanVien);
            }
            
            System.out.println("Du lieu da duoc doc tu file: " + tenFile);
        } catch (IOException e) {
            System.out.println("Loi khi doc du lieu: " + e.getMessage());
        }
    }

    // Lưu danh sách nhân viên vào file
    public void luuDuLieu(String tenFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tenFile))) {
            for (int i = 0; i < soLuongNhanVien; i++) {
                NhanVien nv = danhSachNhanVien[i];
                writer.write(nv.getMaNhanVien() + ";"
                		+ nv.getHoNhanVien() +";"
                        + nv.getTenNhanVien() + ";"
                        + nv.getDiaChi() + ";"
                        + nv.getSoDienThoai()+";"
                        +nv.getLuong());
                
                writer.newLine();
            }
            System.out.println("Du lieu da duoc luu vao file: " + tenFile);
        } catch (IOException e) {
            System.out.println("Loi khi luu du lieu: " + e.getMessage());
        }
    }
    
    
    
    
    
    
    
}
