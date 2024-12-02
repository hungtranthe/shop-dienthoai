import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DSPNH {
    private PNH[] danhSachPNH;
    private int soLuongPNH;
    private DSDT dsdt;
    private DSKH dskh;
    private DSNV dsnv;
    private DienThoai dienThoai;

    public DSPNH(String tenfile, DSDT dsdt, DSKH dskh, DSNV dsnv, int maxPNH) {
        this.danhSachPNH = new PNH[maxPNH];
        this.soLuongPNH = 0;
        this.dsdt = dsdt;
        this.dskh = dskh;
        this.dsnv = dsnv;
        docDuLieu(tenfile);

    }

    public PNH[] getDanhSachPNH() {
        return danhSachPNH;
    }


        public void docDuLieu(String tenFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(tenFile))) {
            String line;
            PNH pnhMoi = null;
        
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                
                // Kiem tra neu dong du lieu it hon 5 phan tu, thi khong hop le
                if (parts.length < 5) {
                    System.out.println("Dong du lieu khong hop le hoac thieu thong tin: " + line);
                    continue;  // Bo qua dong khong hop le
                }
        
                String maPNH = parts[0];
                String ngaylap = parts[1];
                String maNV = parts[2];
                String maNCC = parts[3];
                int soLuongChiTiet = Integer.parseInt(parts[4]);
                int soLuongSP = Integer.parseInt(parts[5]);
        
                // Tạo một đối tượng PNH mới
                pnhMoi = new PNH(maPNH, ngaylap, maNV, maNCC, soLuongChiTiet);
                danhSachPNH[soLuongPNH++] = pnhMoi;

                // Đọc chi tiết phiếu nhập hàng
                for (int i = 0; i < soLuongChiTiet; i++) {
                    if ((line = reader.readLine()) != null) {
                        String[] chiTietParts = line.split(";");
                        if (chiTietParts.length == 3) {
                            String maSanPham = chiTietParts[0];
                            int soLuong = Integer.parseInt(chiTietParts[1]);
                            double donGia = Double.parseDouble(chiTietParts[2]);

                            CTPNH chiTietPNH = new CTPNH(maPNH, maSanPham, soLuong, donGia);
                            pnhMoi.themChiTietPNH(chiTietPNH);
                        }
                    }
                }
            }
            System.out.println("Du lieu da duoc doc tu file. Tong so phieu nhap: " + soLuongPNH);
        } catch (IOException | NumberFormatException e) {
            System.out.println("Loi khi doc du lieu: " + e.getMessage());
        }
    }

    
    // Lưu dữ liệu vào file
    public void luuDuLieu(String tenFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tenFile))) {
            for (int i = 0; i < soLuongPNH; i++) {
                PNH pnh = danhSachPNH[i];
                // Ghi thông tin phiếu nhập hàng
                writer.write(pnh.getMaPNH() + ";" + pnh.getNgayNhap() + ";" + pnh.getNhanVien().getMaNhanVien() + ";" + pnh.getMaNCC() + ";" + pnh.getSoLuongChiTiet() + "\n");

                // Ghi chi tiết phiếu nhập
                for (CTPNH chiTiet : pnh.getChiTietPNHList()) {
                    writer.write(chiTiet.getMaSanPham() + ";" + chiTiet.getSoLuong() + ";" + chiTiet.getDonGia() + "\n");
                }
            }
            System.out.println("Du lieu da duoc luu vao file: " + tenFile);
        } catch (IOException e) {
            System.out.println("Loi khi luu du lieu: " + e.getMessage());
        }
    }


    // Them phieu nhap hang moi
    public void themPNH(Scanner scanner) {
        System.out.print("Nhap ma phieu nhap: ");
        String maPNH = scanner.nextLine();

        System.out.print("Nhap ngay lap phieu (yyyy-mm-dd): ");
        String ngaylap = scanner.nextLine();

        System.out.print("Nhap ma nhan vien lap phieu: ");
        String maNhanVien = scanner.nextLine();
        NhanVien nhanVien = dsnv.timNhanVienTheoMa(maNhanVien);
        if (nhanVien == null) {
            System.out.println("Khong tim thay nhan vien.");
            return;
        }

        System.out.print("Nhap ma nha cung cap: ");
        String maNCC = scanner.nextLine();

        PNH pnhMoi = new PNH(maPNH, ngaylap, maNhanVien, maNCC, 10);  // Tao phieu nhap moi
        danhSachPNH[soLuongPNH++] = pnhMoi;

        // Them chi tiet phieu nhap
        while (true) {
            System.out.print("Nhap ma san pham (hoac 'stop' de dung): ");
            String maSanPham = scanner.nextLine();
            if (maSanPham.equalsIgnoreCase("stop")) break;

            System.out.print("Nhap so luong san pham: ");
            int soLuong = Integer.parseInt(scanner.nextLine());

            // Lay gia san pham tu DSDT
            Object donGia = dsdt.timThongTintheoMasp(maSanPham, "dongia");
            if (donGia instanceof Double) {
                // Them chi tiet phieu nhap
                pnhMoi.themChiTietPNH(new CTPNH(maPNH, maSanPham, soLuong, (double) donGia));
                System.out.println("Da them san pham: " + maSanPham + " | So luong: " + soLuong);

                // Cap nhat so luong san pham trong kho (DSDT)
                capNhatSoLuongSPTrongDSDT(maSanPham, soLuong);
            } else {
                System.out.println("Khong tim thay san pham voi ma: " + maSanPham);
            }
        }
        luuDuLieu("D:\\OOP\\pnh.dat");
    }

    // Cap nhat so luong san pham trong DSDT
    private void capNhatSoLuongSPTrongDSDT(String maSanPham, int soLuongNhap) {
        dsdt.capNhatSoLuong(maSanPham, soLuongNhap);
        // for (int i = 0; i < dsdt.getSoluong(); i++) {
        //     DienThoai dienThoai = dsdt.getDanhSach()[i];
        //     if (dienThoai.getId().equals(maSanPham)) {
        //         int soLuongKho = dienThoai.getSoLuong();
        //         dienThoai.setSoLuong(soLuongKho + soLuongNhap); // Cap nhat so luong
                
        //         return;
        //     }
        // }
        System.out.println("Khong tim thay san pham voi ma: " + maSanPham);
    }

    // Hien thi danh sach phieu nhap
    public void hienThiDanhSachPNH() {
        if (soLuongPNH == 0) {
            System.out.println("Danh sach phieu nhap trong.");
        } else {
            for (int i = 0; i < soLuongPNH; i++) {
                danhSachPNH[i].hienThiChiTietPNH();
            }
        }
    }

    // Tim kiem phieu nhap hang theo ma
    public void timKiemPNH(Scanner scanner) {
        System.out.print("Nhap ma phieu nhap can tim: ");
        String maPNH = scanner.nextLine();

        boolean timThay = false;
        for (int i = 0; i < soLuongPNH; i++) {
            if (danhSachPNH[i].getMaPNH().equals(maPNH)) {
                danhSachPNH[i].hienThiChiTietPNH();
                timThay = true;
            }
        }

        if (!timThay) {
            System.out.println("Khong tim thay phieu nhap voi ma: " + maPNH);
        }
    }

    // Thong ke phiếu nhập hàng
    public void thongKePNH() {
        double tongDoanhThu = 0.0;
        for (int i = 0; i < soLuongPNH; i++) {
            System.out.println("\nPhieu nhap hang: " + danhSachPNH[i].getMaPNH() + " | Ngay nhap: " + danhSachPNH[i].getNgayNhap());
            for (CTPNH chiTiet : danhSachPNH[i].getChiTietPNHList()) {
                System.out.println("San pham: " + chiTiet.getMaSanPham() + " | So luong: " + chiTiet.getSoLuong() + " | Thanh tien: " + chiTiet.getThanhTien());
                tongDoanhThu += chiTiet.getThanhTien();
            }
        }
        System.out.println("\nTong doanh thu tu cac phieu nhap: " + tongDoanhThu);
    }
}
