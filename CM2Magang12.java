import java.util.Scanner;

public class CM2Magang12 {

    static String[][] data = new String[100][6];
    static int jumlahData = 0;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int pilihan;
        do {
            System.out.println("\n=== Sistem Pendaftaran Magang Mahasiswa ===");
            System.out.println("1. Tambah Data Magang");
            System.out.println("2. Tampilkan Semua Pendaftar Magang");
            System.out.println("3. Cari Pendaftar berdasarkan Program Studi");
            System.out.println("4. Hitung Jumlah Pendaftar untuk Setiap Status");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu (1-5): ");

            pilihan = sc.nextInt();
            sc.nextLine();

            switch (pilihan) {
                case 1 -> tambahData();
                case 2 -> tampilkanData();
                case 3 -> cariProdi();
                case 4 -> hitungStatus();
                case 5 -> System.out.println("Program selesai.");
                default -> System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 5);
    }

    // Tambah Data Magang
    public static void tambahData() {
        System.out.println("\n=== Tambah Data Magang ===");
        System.out.print("Nama Mahasiswa: ");
        data[jumlahData][0] = sc.nextLine();
        System.out.print("NIM: ");
        data[jumlahData][1] = sc.nextLine();
        System.out.print("Program Studi: ");
        data[jumlahData][2] = sc.nextLine();
        System.out.print("Perusahaan Tujuan Magang: ");
        data[jumlahData][3] = sc.nextLine();

        // Validasi semester
        while (true) {
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
        while (true) {
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
        System.out.println("---------------------------------------------------------------------------------------------------------------");
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
        boolean ada = false;
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

    // Menghitung Status Pendaftar
    public static void hitungStatus() {
        int diterima = 0, menunggu = 0, ditolak = 0;
        for (int i = 0; i < jumlahData; i++) {
            String st = data[i][5].toLowerCase();
            if (st.equals("diterima")) diterima++;
            else if (st.equals("menunggu")) menunggu++;
            else if (st.equals("ditolak")) ditolak++;
        }
        System.out.println("\n=== Jumlah Pendaftar Berdasarkan Status ===");
        System.out.println("Diterima : " + diterima);
        System.out.println("Menunggu : " + menunggu);
        System.out.println("Ditolak  : " + ditolak);
        System.out.println("Total    : " + jumlahData);
    }
}