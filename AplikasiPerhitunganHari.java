import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.*;
import com.toedter.calendar.JCalendar;

public class AplikasiPerhitunganHari extends JFrame {
    private JComboBox<String> comboBulan;
    private JSpinner spinnerTahun;
    private JButton tombolHitung;
    private JLabel labelHasil, labelInfo;
    private JCalendar kalender;

    public AplikasiPerhitunganHari() {
        setTitle("Aplikasi Perhitungan Hari");
        setSize(500, 480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelUtama = new JPanel(new BorderLayout(10, 10));
        panelUtama.setBackground(new Color(245, 245, 255));
        add(panelUtama);

        JLabel judul = new JLabel("Aplikasi Perhitungan Hari", JLabel.CENTER);
        judul.setFont(new Font("Segoe UI", Font.BOLD, 20));
        judul.setForeground(new Color(60, 60, 150));
        panelUtama.add(judul, BorderLayout.NORTH);

        JPanel panelInput = new JPanel(new GridLayout(3, 2, 10, 10));
        panelInput.setBackground(new Color(245, 245, 255));
        panelInput.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

        panelInput.add(new JLabel("Pilih Bulan:"));
        String[] bulan = {"Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"};
        comboBulan = new JComboBox<>(bulan);
        panelInput.add(comboBulan);

        panelInput.add(new JLabel("Masukkan Tahun:"));
        spinnerTahun = new JSpinner(new SpinnerNumberModel(LocalDate.now().getYear(), 1900, 3000, 1));
        panelInput.add(spinnerTahun);

        panelInput.add(new JLabel("Kalender:"));
        kalender = new JCalendar();
        panelInput.add(kalender);

        panelUtama.add(panelInput, BorderLayout.CENTER);

        JPanel panelBawah = new JPanel(new GridLayout(3, 1, 10, 10));
        panelBawah.setBackground(new Color(240, 240, 255));
        panelBawah.setBorder(BorderFactory.createEmptyBorder(10, 30, 20, 30));

        tombolHitung = new JButton("Hitung Jumlah Hari");
        tombolHitung.setBackground(new Color(100, 120, 230));
        tombolHitung.setForeground(Color.WHITE);
        tombolHitung.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tombolHitung.setFocusPainted(false);
        panelBawah.add(tombolHitung);

        labelHasil = new JLabel("Jumlah hari dalam bulan: -", JLabel.CENTER);
        labelHasil.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        labelInfo = new JLabel("", JLabel.CENTER);
        labelInfo.setFont(new Font("Segoe UI", Font.ITALIC, 13));
        labelInfo.setForeground(new Color(80, 80, 80));

        panelBawah.add(labelHasil);
        panelBawah.add(labelInfo);
        panelUtama.add(panelBawah, BorderLayout.SOUTH);

        tombolHitung.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int bulanIndex = comboBulan.getSelectedIndex() + 1;
                int tahun = (int) spinnerTahun.getValue();
                YearMonth ym = YearMonth.of(tahun, bulanIndex);
                int jumlahHari = ym.lengthOfMonth();
                LocalDate pertama = LocalDate.of(tahun, bulanIndex, 1);
                LocalDate terakhir = LocalDate.of(tahun, bulanIndex, jumlahHari);
                labelHasil.setText("Jumlah hari dalam " + comboBulan.getSelectedItem() + " " + tahun + ": " + jumlahHari + " hari");
                labelInfo.setText("Hari pertama: " + pertama.getDayOfWeek() + " | Hari terakhir: " + terakhir.getDayOfWeek());
            }
        });

        spinnerTahun.addChangeListener(e -> labelHasil.setText("Jumlah hari dalam bulan: -"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AplikasiPerhitunganHari().setVisible(true));
    }
}
