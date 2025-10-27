/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplikasidiskon;

/**
 *
 * @author Hinokaa
 */
public class AplikasiDiskon {
    
    public static void main(String[] args) {
        // Jalankan frame di thread Event Dispatch Thread
        javax.swing.SwingUtilities.invokeLater(() -> {
            AplikasiDiskonFrame frame = new AplikasiDiskonFrame();
            frame.setVisible(true); // pastikan frame terlihat
        });
    }
}
