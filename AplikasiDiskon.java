import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class AplikasiDiskon extends JFrame {
    private JTextField txtHargaAsli, txtKodeKupon;
    private JComboBox<String> comboDiskon;
    private JSlider sliderDiskon;
    private JButton btnHitung, btnReset;
    private JTextArea areaRiwayat;
    private JLabel lblHasil;
    private ArrayList<String> riwayatList = new ArrayList<>();

    public AplikasiDiskon() {
        setTitle("Aplikasi Perhitungan Diskon");
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelUtama = new JPanel(new GridLayout(8, 2, 10, 10));
        panelUtama.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelUtama.add(new JLabel("Harga Asli (Rp):"));
        txtHargaAsli = new JTextField();
        panelUtama.add(txtHargaAsli);

        panelUtama.add(new JLabel("Persentase Diskon:"));
        comboDiskon = new JComboBox<>(new String[]{"5%", "10%", "15%", "20%", "25%", "50%"});
        panelUtama.add(comboDiskon);

        panelUtama.add(new JLabel("Atur Diskon Manual (%):"));
        sliderDiskon = new JSlider(0, 50, 10);
        sliderDiskon.setMajorTickSpacing(10);
        sliderDiskon.setPaintTicks(true);
        sliderDiskon.setPaintLabels(true);
        panelUtama.add(sliderDiskon);

        panelUtama.add(new JLabel("Kode Kupon (opsional):"));
        txtKodeKupon = new JTextField();
        panelUtama.add(txtKodeKupon);

        btnHitung = new JButton("Hitung");
        btnReset = new JButton("Reset");
        panelUtama.add(btnHitung);
        panelUtama.add(btnReset);

        panelUtama.add(new JLabel("Hasil:"));
        lblHasil = new JLabel("-");
        lblHasil.setFont(new Font("Arial", Font.BOLD, 14));
        panelUtama.add(lblHasil);

        add(panelUtama, BorderLayout.NORTH);

        areaRiwayat = new JTextArea();
        areaRiwayat.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaRiwayat);
        scroll.setBorder(BorderFactory.createTitledBorder("Riwayat Perhitungan Diskon"));
        add(scroll, BorderLayout.CENTER);

        btnHitung.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hitungDiskon();
            }
        });

        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtHargaAsli.setText("");
                txtKodeKupon.setText("");
                lblHasil.setText("-");
                sliderDiskon.setValue(10);
            }
        });

        comboDiskon.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selected = (String) comboDiskon.getSelectedItem();
                    int value = Integer.parseInt(selected.replace("%", ""));
                    sliderDiskon.setValue(value);
                }
            }
        });
    }

    private void hitungDiskon() {
        try {
            double hargaAsli = Double.parseDouble(txtHargaAsli.getText());
            int persenDiskon = sliderDiskon.getValue();
            String kupon = txtKodeKupon.getText().trim();

            double diskon = hargaAsli * persenDiskon / 100;
            double totalDiskon = diskon;

            if (kupon.equalsIgnoreCase("HEMAT10")) {
                totalDiskon += hargaAsli * 0.10;
            } else if (kupon.equalsIgnoreCase("PROMO5")) {
                totalDiskon += hargaAsli * 0.05;
            }

            if (totalDiskon > hargaAsli) {
                totalDiskon = hargaAsli;
            }

            double hargaAkhir = hargaAsli - totalDiskon;
            lblHasil.setText("Harga Akhir: Rp " + String.format("%.2f", hargaAkhir));

            String hasilRiwayat = "Harga Asli: Rp " + hargaAsli +
                    " | Diskon: " + persenDiskon + "%" +
                    " | Kupon: " + kupon +
                    " | Hemat: Rp " + String.format("%.2f", totalDiskon) +
                    " | Total Bayar: Rp " + String.format("%.2f", hargaAkhir);

            riwayatList.add(hasilRiwayat);
            tampilkanRiwayat();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Masukkan angka yang valid!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void tampilkanRiwayat() {
        StringBuilder sb = new StringBuilder();
        for (String r : riwayatList) {
            sb.append(r).append("\n");
        }
        areaRiwayat.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AplikasiDiskon().setVisible(true);
        });
    }
}
