import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DSKH {
    private KhachHang[] danhSachKhachHang;
    private int soLuongKhachHang;

    public DSKH(int maxKhachHang, String tenfile) {
        this.danhSachKhachHang = new KhachHang[maxKhachHang];
        this.soLuongKhachHang = 0;
        docDuLieu(tenfile);
    }

    // Menu quản lý khách hàng
    public void menu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n===== QUAN LY KHACH HANG =====");
            System.out.println("1. Them khach hang moi");
            System.out.println("2. Hien thi danh sach khach hang");
            System.out.println("3. Tim khach hang theo ma");
            System.out.println("4. Sua thong tin khach hang");
            System.out.println("5. Thoat");
            System.out.print("Nhap lua chon cua ban: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    themKhachHangMoi(scanner); // Thêm khách hàng mới
                    break;
                case 2:
                    hienThiDanhSachKhachHang(); // Hiển thị danh sách khách hàng
                    break;
                case 3:
                    timKiemKhachHang(scanner); // Tìm khách hàng theo mã
                    break;
                case 4: 
                    suaThongTinKhachHang(scanner);
                case 5:
                    System.out.println("Quay lai menu chinh.");
                    return;
                default:
                    System.out.println("Lua chon khong hop le, vui long thu lai.");
            }
        } while (choice != 5);

    }

    // Thêm khách hàng mới
    public void themKhachHangMoi(Scanner scanner) {
        System.out.print("Nhap ma khach hang: ");
        String maKhachHang = scanner.nextLine();

        System.out.print("Nhap ten khach hang: ");
        String tenKhachHang = scanner.nextLine();

        System.out.print("Nhap so dien thoai: ");
        String soDienThoai = scanner.nextLine();

        System.out.print("Nhap dia chi: ");
        String diaChi = scanner.nextLine();

        KhachHang khachHang = new KhachHang(maKhachHang, tenKhachHang, soDienThoai, diaChi);
        themKhachHang(khachHang);
    }

    // Thêm khách hàng vào danh sách
    public void themKhachHang(KhachHang khachHang) {
        if (timKhachHangTheoMa(khachHang.getMaKhachHang()) != null) {
            System.out.println("Khach hang da ton tai voi ma: " + khachHang.getMaKhachHang());
            return;
        }
        if (soLuongKhachHang < danhSachKhachHang.length) {
            danhSachKhachHang[soLuongKhachHang++] = khachHang;
            System.out.println("Them khach hang: " + khachHang.getTenKhachHang());
        } else {
            System.out.println("Danh sach khach hang da day.");
        }
        luuDuLieu("D:\\OOP\\kh.dat"); // Lưu lại danh sách sau khi thêm
    }

    // Hiển thị danh sách khách hàng
    public void hienThiDanhSachKhachHang() {
            for (int i = 0; i < soLuongKhachHang; i++) {
                KhachHang kh = danhSachKhachHang[i];
                System.out.println("Ma khach hang: " + kh.getMaKhachHang());
                System.out.println("Ten khach hang: " + kh.getTenKhachHang());
                System.out.println("So dien thoai: " + kh.getSoDienThoai());
                System.out.println("Dia chi: " + kh.getDiaChi());
                System.out.println("Ma hoa don da mua: " + String.join(", ", kh.getDanhSachHoaDon()));
                System.out.println("-------------------------------");
            }
        
    }

    // Tìm kiếm khách hàng theo mã
    public void timKiemKhachHang(Scanner scanner) {
        System.out.print("Nhap ma khach hang: ");
        String maKhachHang = scanner.nextLine();

        KhachHang kh = timKhachHangTheoMa(maKhachHang);
        if (kh != null) {
            System.out.println("Thong tin khach hang:");
            System.out.println("Ma khach hang: " + kh.getMaKhachHang());
            System.out.println("Ten khach hang: " + kh.getTenKhachHang());
            System.out.println("So dien thoai: " + kh.getSoDienThoai());
            System.out.println("Dia chi: " + kh.getDiaChi());
            System.out.println("Ma hoa don da mua: " + String.join(", ", kh.getDanhSachHoaDon()));
        } else {
            System.out.println("Khong tim thay khach hang voi ma: " + maKhachHang);
        }
    }

    // Tìm khách hàng theo mã
    public KhachHang timKhachHangTheoMa(String maKhachHang) {
        for (KhachHang kh : danhSachKhachHang) {
            if (kh != null && kh.getMaKhachHang().equals(maKhachHang)) {
                return kh;
            }
        }
        return null;
    } 

    // Đọc danh sách khách hàng từ file
    public void docDuLieu(String tenFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(tenFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
    
                // Kiểm tra nếu dòng dữ liệu có ít nhất 4 phần tử (mã khách hàng, tên, số điện thoại, địa chỉ)
                if (parts.length < 4) {
                    System.out.println("Du lieu khong hop le: " + line);
                    continue;  // Bỏ qua dòng không hợp lệ
                }
    
                String maKhachHang = parts[0];
                String tenKhachHang = parts[1];
                String soDienThoai = parts[2];
                String diaChi = parts[3];
                
                // Mặc định danh sách hóa đơn là rỗng
                String[] danhSachHoaDon = new String[0];
                
                // Nếu có dữ liệu về hóa đơn (phần tử thứ 5)
                if (parts.length > 4) {
                    danhSachHoaDon = parts[4].split(",");  // Tách danh sách hóa đơn
                }
    
                // Tạo đối tượng KhachHang
                KhachHang khachHang = new KhachHang(maKhachHang, tenKhachHang, soDienThoai, diaChi);
                khachHang.setDanhSachHoaDon(danhSachHoaDon);  // Cập nhật danh sách hóa đơn (có thể rỗng)
    
                // Thêm khách hàng vào danh sách
                themKhachHang(khachHang);
            }
            System.out.println("Du lieu da duoc doc tu file: " + tenFile);
        } catch (IOException e) {
            System.out.println("Loi khi doc du lieu: " + e.getMessage());
        }
    }

    // Sửa thông tin khách hàng
public void suaThongTinKhachHang(Scanner scanner) {
    System.out.print("Nhap ma khach hang can sua: ");
    String maKhachHang = scanner.nextLine();

    // Tìm khách hàng theo mã
    KhachHang khachHang = timKhachHangTheoMa(maKhachHang);
    if (khachHang == null) {
        System.out.println("Khong tim thay khach hang voi ma: " + maKhachHang);
        return;
    }

    // Hiển thị thông tin hiện tại
    System.out.println("Thong tin hien tai cua khach hang:");
    System.out.println("Ma khach hang: " + khachHang.getMaKhachHang());
    System.out.println("Ten khach hang: " + khachHang.getTenKhachHang());
    System.out.println("So dien thoai: " + khachHang.getSoDienThoai());
    System.out.println("Dia chi: " + khachHang.getDiaChi());

    // Nhập thông tin mới
    System.out.print("Nhap ten khach hang moi (nhap de giu nguyen): ");
    String tenKhachHangMoi = scanner.nextLine();
    if (!tenKhachHangMoi.isEmpty()) {
        khachHang.setTenKhachHang(tenKhachHangMoi);
    }

    System.out.print("Nhap so dien thoai moi (nhap de giu nguyen): ");
    String soDienThoaiMoi = scanner.nextLine();
    if (!soDienThoaiMoi.isEmpty()) {
        khachHang.setSoDienThoai(soDienThoaiMoi);
    }

    System.out.print("Nhap dia chi moi (nhap de giu nguyen): ");
    String diaChiMoi = scanner.nextLine();
    if (!diaChiMoi.isEmpty()) {
        khachHang.setDiaChi(diaChiMoi);
    }

    System.out.println("Thong tin khach hang da duoc cap nhat.");
    luuDuLieu("D:\\OOP\\kh.dat"); // Ghi lại vào file
}

    

    // Lưu danh sách khách hàng vào file
    public void luuDuLieu(String tenFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tenFile))) {
            for (int i = 0; i < soLuongKhachHang; i++) {
                KhachHang kh = danhSachKhachHang[i];
                writer.write(kh.getMaKhachHang() + ";"
                        + kh.getTenKhachHang() + ";"
                        + kh.getSoDienThoai() + ";"
                        + kh.getDiaChi() + ";"
                        + String.join(",", kh.getDanhSachHoaDon()));
                writer.newLine();
            }
            System.out.println("Du lieu da duoc luu vao file: " + tenFile);
        } catch (IOException e) {
            System.out.println("Loi khi luu du lieu: " + e.getMessage());
        }
    }
}
