//https://github.com/artasdaa/CM2Magang12.git
import java.util.Scanner;

public class CM2Magang12 {

    static String[][] data = new String[100][7];//array2 diemnsi
    static int jumlahData = 0;//menghitung jumlah data yg dimasukkan
    static Scanner sc = new Scanner(System.in);//membaca inputan dari user

    public static void main(String[] args) {//method utama
        int pilihan;
        do {//tampil min 1x sampai user memilih keluar
            System.out.println("\n=== Sistem Pendaftaran Magang Mahasiswa ===");
            System.out.println("1. Tambah Data Magang");
            System.out.println("2. Tampilkan Semua Pendaftar Magang");
            System.out.println("3. Cari Pendaftar berdasarkan Program Studi");
            System.out.println("4. Hitung Jumlah Pendaftar untuk Setiap Status");
            System.out.println("5. Update Status Magang berdasarkan NIM");
            System.out.println("6. Keluar");
            System.out.print("Pilih menu (1-6): ");

            pilihan = sc.nextInt();//membaca inputan
            sc.nextLine();

            switch (pilihan) {
                case 1:
                    tambahData();
                    break;
                case 2:
                    tampilkanData();
                    break;
                case 3:
                    cariProdi();
                    break;
                case 4:
                    hitungStatus();
                    break;
                case 5:
                    updateStatus();
                    break;
                case 6:
                    System.out.println("Program selesai.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 6);
    }

    // Tambah Data Magang
    public static void tambahData() {//method
        if (jumlahData >= data.length) {
            System.out.println("Data sudah penuh.");
            return;
        }

        System.out.println("\n=== Tambah Data Magang ===");
        System.out.print("Nama Mahasiswa: ");
        data[jumlahData][0] = sc.nextLine();
        System.out.print("NIM: ");
        String nim = sc.nextLine();
        if (cekNim(nim)) {
            System.out.println("NIM sudah terdaftar! Data gagal ditambahkan.");
            return;
        }
        data[jumlahData][1] = nim;
        System.out.print("Program Studi: ");
        data[jumlahData][2] = sc.nextLine();
        System.out.print("Perusahaan Tujuan Magang: ");
        data[jumlahData][3] = sc.nextLine();

        // Validasi semester
        while (true) {//perulangan tak terbatas
            System.out.print("Semester pengambilan magang (6 atau 7): ");
            int smt = sc.nextInt();
            sc.nextLine();
            if (smt == 6 || smt == 7) {
                data[jumlahData][4] = String.valueOf(smt);
                break;
            } else {
                System.out.println("Semester hanya boleh 6 atau 7!");
            }
        }

        // Validasi status
        while (true) {//perulangan tanpa batas
            System.out.print("Status magang (Diterima/Menunggu/Ditolak): ");
            String st = sc.nextLine();
            if (st.equalsIgnoreCase("Diterima") ||
                st.equalsIgnoreCase("Menunggu") ||
                st.equalsIgnoreCase("Ditolak")) {
                data[jumlahData][5] = st;
                break;
            } else {
                System.out.println("Status tidak valid!");
            }
        }

        jumlahData++;
        System.out.println("Data pendaftaran berhasil ditambahkan. Total: " + jumlahData);
    }

    // Menampilkan Semua Data
    public static void tampilkanData() {
        if (jumlahData == 0) {
            System.out.println("\nBelum ada pendaftar.");
            return;
        }
        System.out.println("\n=== Daftar Semua Pendaftar Magang ===");
        System.out.printf(
            "%-4s %-20s %-12s %-20s %-20s %-10s %-12s\n",
            "No", "Nama", "NIM", "Prodi", "Perusahaan", "Semester", "Status"
        );
        System.out.println("----------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < jumlahData; i++) {
            System.out.printf(
                "%-4d %-20s %-12s %-20s %-20s %-10s %-12s\n",
                (i + 1), data[i][0], data[i][1], data[i][2],
                data[i][3], data[i][4], data[i][5]
            );
        }
    }

    // Mencari Pendaftar Berdasarkan Prodi
    public static void cariProdi() {
        System.out.print("\nMasukkan Program Studi: ");
        String cari = sc.nextLine();
        boolean ada = false;//mencari data yg cocok jika ditemukan akan true, jika tidak ada tetap false
        System.out.printf(
            "%-4s %-20s %-12s %-20s %-20s %-10s %-12s\n",
            "No", "Nama", "NIM", "Prodi", "Perusahaan", "Semester", "Status"
        );
        System.out.println("---------------------------------------------------------------------------------------------------------------");
        int nomor = 1;
        for (int i = 0; i < jumlahData; i++) {
            if (data[i][2].equalsIgnoreCase(cari)) {
                ada = true;
                System.out.printf(
                    "%-4d %-20s %-12s %-20s %-20s %-10s %-12s\n",
                    nomor++, data[i][0], data[i][1], data[i][2],
                    data[i][3], data[i][4], data[i][5]
                );
            }
        }
        if (!ada) {
            System.out.println("Tidak ada pendaftar dari program studi tersebut.");
        }
    }

    //Cek NIM Sudah terdaftar atau belum
    public static boolean cekNim(String nim) {
        for (int i = 0; i < jumlahData; i++) {
            if (data[i][1].equals(nim)) {
                return true;
            }
        }
        return false;
    }

    //Update Status Magang Berdasarkan NIM
    public static void updateStatus() {
        System.out.print("\nMasukkan NIM mahasiswa: ");
        String nim = sc.nextLine();
        boolean ditemukan = false;

        for (int i = 0; i < jumlahData; i++) {
            if (data[i][1].equals(nim)) {
                ditemukan = true;

                while (true) {
                    System.out.print("Masukkan status baru (Diterima/Menunggu/Ditolak): ");
                    String status = sc.nextLine();

                    if (status.equalsIgnoreCase("Diterima") ||
                        status.equalsIgnoreCase("Menunggu") ||
                        status.equalsIgnoreCase("Ditolak")) {

                        data[i][5] = status;
                        System.out.println("Status berhasil diperbarui.");
                        break;
                    } else {
                        System.out.println("Status tidak valid!");
                    }
                }
                break;
            }
        }

        if (!ditemukan) {
            System.out.println("NIM tidak ditemukan.");
        }
    }

    // Menghitung Status Pendaftar
    public static void hitungStatus() {
        int diterima = 0, menunggu = 0, ditolak = 0;
        for (int i = 0; i < jumlahData; i++) {//membaca data
            String st = data[i][5].toLowerCase();//berisi status magang
            if (st.equals("diterima")) diterima++;
            else if (st.equals("menunggu")) menunggu++;
            else if (st.equals("ditolak")) ditolak++;
        }
        System.out.println("\n=== Jumlah Pendaftar Berdasarkan Status ===");
        System.out.println("Diterima : " + diterima);//variabel diterima bertambah 1
        System.out.println("Menunggu : " + menunggu);
        System.out.println("Ditolak  : " + ditolak);
        System.out.println("Total    : " + jumlahData);
    }
}