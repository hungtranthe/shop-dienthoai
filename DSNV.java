import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DSNV {
    private NhanVien[] danhSachNhanVien;
    private int soLuongNhanVien;

    public DSNV(int maxNhanVien, String tenfile) {
        this.danhSachNhanVien = new NhanVien[maxNhanVien];
        this.soLuongNhanVien = 0;
        docDuLieu(tenfile);
    }

    public NhanVien[] getDanhSacNhanVien(){
        return danhSachNhanVien;
    }

    // Menu quản lý nhân viên
    public void menu(DSHoaDon dshd, DSPNH pnh) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n===== QUAN LY NHAN VIEN =====");
            System.out.println("1. Them nhan vien moi");
            System.out.println("2. Hien thi danh sach nhan vien");
            System.out.println("3. Tim nhan vien theo ma");
            System.out.println("4. Sua thong tin nhan vien");
            System.out.println("5. Thoat");
            System.out.print("Nhap lua chon cua ban: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    themNhanVienMoi(scanner, dshd, pnh); // Thêm nhân viên mới
                    break;
                case 2:
                    hienThiDanhSachNhanVien(dshd, pnh); // Hiển thị danh sách nhân viên và thông tin hóa đơn/phieu nhập
                    break;
                case 3:
                    timKiemNhanVien(scanner); // Tìm nhân viên theo mã
                    break;
                case 4: 
                    suaThongTinNhanVien(scanner);
                case 5:
                    System.out.println("Thoat chuong trinh quan ly nhan vien.");
                    return;
                default:
                    System.out.println("Lua chon khong hop le, vui long thu lai.");
            }
        } while (choice != 5);

    }

    // Thêm nhân viên mới
    public void themNhanVienMoi(Scanner scanner, DSHoaDon dshd, DSPNH pnh) {
        System.out.print("Nhap ma nhan vien: ");
        String maNhanVien = scanner.nextLine();

        System.out.print("Nhap ten nhan vien: ");
        String tenNhanVien = scanner.nextLine();

        System.out.print("Nhap dia chi: ");
        String diaChi = scanner.nextLine();

        System.out.print("Nhap so dien thoai: ");
        String soDienThoai = scanner.nextLine();

        NhanVien nhanVien = new NhanVien(maNhanVien, tenNhanVien, diaChi, soDienThoai);
        themNhanVien(nhanVien);

        // Lưu lại danh sách nhân viên sau khi thêm
        luuDuLieu("D:\\OOP\\nv.dat");
        System.out.println("Thong tin nhan vien da duoc them.");
    }

    // Thêm nhân viên vào danh sách
    public void themNhanVien(NhanVien nhanVien) {
        if (timNhanVienTheoMa(nhanVien.getMaNhanVien()) != null) {
            System.out.println("Nhan vien da ton tai voi ma: " + nhanVien.getMaNhanVien());
            return;
        }
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
    luuDuLieu("D:\\OOP\\nv.dat"); // Ghi lại vào file
}


    // Đọc danh sách nhân viên từ file
    public void docDuLieu(String tenFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(tenFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length < 4) {
                    System.out.println("Du lieu khong hop le: " + line);
                    continue;
                }

                String maNhanVien = parts[0];
                String tenNhanVien = parts[1];
                String diaChi = parts[2];
                String soDienThoai = parts[3];

                NhanVien nhanVien = new NhanVien(maNhanVien, tenNhanVien, diaChi, soDienThoai);
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
                        + nv.getTenNhanVien() + ";"
                        + nv.getDiaChi() + ";"
                        + nv.getSoDienThoai());
                writer.newLine();
            }
            System.out.println("Du lieu da duoc luu vao file: " + tenFile);
        } catch (IOException e) {
            System.out.println("Loi khi luu du lieu: " + e.getMessage());
        }
    }
}
