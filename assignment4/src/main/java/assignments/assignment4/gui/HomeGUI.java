package assignments.assignment4.gui;

import assignments.assignment3.nota.NotaManager;

import static assignments.assignment3.nota.NotaManager.cal;

import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import static assignments.assignment3.nota.NotaManager.toNextDay;

public class HomeGUI extends JPanel {
    public static final String KEY = "HOME";
    private JLabel titleLabel;
    private JLabel dateLabel;
    private JPanel mainPanel;
    private JButton loginButton;
    private JButton registerButton;
    private JButton toNextDayButton;

    public HomeGUI(){
        super(new BorderLayout()); // Setup layout, Feel free to make any changes

        // Set up main panel, Feel free to make any changes
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        initGUI();

        add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Method untuk menginisialisasi GUI.
     * Selama funsionalitas sesuai dengan soal, tidak apa apa tidak 100% sama.
     * Be creative and have fun!
     * */
    private void initGUI() {
        GridBagConstraints g = new GridBagConstraints();
        g.weighty = 1.0;
        g.insets = new Insets(2, 0, 2, 0);

        g.gridx = 1;
        g.gridy = 0;
        titleLabel = new JLabel("Selamat datang di CuciCuci System");
        Font font = new Font("SansSerif",Font.BOLD,24);
        titleLabel.setFont(font);
        mainPanel.add(titleLabel, g);

        g.gridx = 1;
        g.gridy = 2;
        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                handleToLogin();
            }

        });
        mainPanel.add(loginButton,g);

        g.gridx = 1;
        g.gridy = 4;
        registerButton = new JButton("Register", null);
        registerButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e){
                handleToRegister();
            }
            
        });
        mainPanel.add(registerButton,g);

        g.gridx = 1;
        g.gridy = 6;
        toNextDayButton = new JButton("Next Day", null);
        toNextDayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                handleNextDay();
            }
        });
        mainPanel.add(toNextDayButton,g);
        
        g.gridx = 1;
        g.gridy = 8;
        String hari = NotaManager.fmt.format(cal.getTime());
        String hariFull = String.format("Hari ini : %s",hari);
        dateLabel = new JLabel(hariFull);
        mainPanel.add(dateLabel,g);





    }

    /**
     * Method untuk pergi ke halaman register.
     * Akan dipanggil jika pengguna menekan "registerButton"
     * */
    private static void handleToRegister() {
        
    MainFrame mainFrame = MainFrame.getInstance();
    mainFrame.navigateTo(RegisterGUI.KEY);
    
    }


    /**
     * Method untuk pergi ke halaman login.
     * Akan dipanggil jika pengguna menekan "loginButton"
     * */
    private static void handleToLogin() {
        MainFrame mainFrame = MainFrame.getInstance();
        mainFrame.navigateTo(LoginGUI.KEY);
    }

    /**
     * Method untuk skip hari.
     * Akan dipanggil jika pengguna menekan "toNextDayButton"
     * */
    private void handleNextDay() {
        NotaManager.toNextDay();
        JOptionPane.showMessageDialog(null,"Kamu tidur hari ini...zzz...");
        String hari = NotaManager.fmt.format(cal.getTime());
        String hariFull = String.format("Hari ini : %s",hari);
        dateLabel.setText(hariFull);
    }
}
