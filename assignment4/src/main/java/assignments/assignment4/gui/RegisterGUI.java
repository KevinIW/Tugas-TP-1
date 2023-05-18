package assignments.assignment4.gui;

import assignments.assignment3.LoginManager;
import assignments.assignment3.user.Member;
import assignments.assignment4.MainFrame;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterGUI extends JPanel {
    public static final String KEY = "REGISTER";
    private JPanel mainPanel;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel phoneLabel;
    private JTextField phoneTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton registerButton;
    private LoginManager loginManager;
    private JButton backButton;

    public RegisterGUI(LoginManager loginManager) {
        super(new BorderLayout()); // Setup layout, Feel free to make any changes
        this.loginManager = loginManager;

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
        //set grid bag constraint
        GridBagConstraints g = new GridBagConstraints();
        g.anchor = GridBagConstraints.LINE_START;
        g.weighty = 1.0;
        g.insets = new Insets(2, 0, 2, 0);

        //set label nama
        g.gridx = 0;
        g.gridy = 0;
        nameLabel = new JLabel("Masukkan nama anda: ");
        mainPanel.add(nameLabel,g);

        //set textField nama
        g.gridx = 0;
        g.gridy = 1;
        g.ipadx = 400;

        nameTextField = new JTextField();
        mainPanel.add(nameTextField,g);

        //set label HP
        g.gridx = 0;
        g.gridy = 2;
        phoneLabel = new JLabel("Masukkan nomor HP anda: ");
        mainPanel.add(phoneLabel,g);

        //set textField HP
        g.gridx = 0;
        g.gridy = 3;
        g.ipadx = 400;
        phoneTextField = new JTextField();
        mainPanel.add(phoneTextField,g);

        //set label password
        g.gridx = 0;
        g.gridy = 4;
        passwordLabel = new JLabel("Masukkan password anda: ");
        mainPanel.add(passwordLabel,g);

        //set passwordfield
        g.gridx = 0;
        g.gridy = 5;
        g.ipadx = 400;
        passwordField = new JPasswordField();
        mainPanel.add(passwordField,g);

        //set register button
        g.anchor = GridBagConstraints.CENTER;
        g.ipadx = 0;
        g.gridx = 0;
        g.gridy = 6;
        registerButton = new JButton("Register", null);
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String nama = nameTextField.getText();
                String noHp = phoneTextField.getText();
            
                String password = new String( passwordField.getPassword());
                handleRegister(nama, noHp, password);
               
            }
        });
        mainPanel.add(registerButton,g);

        //set button kembali
        g.gridx = 0;
        g.gridy = 7;
        backButton = new JButton("Kembali");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                handleBack();
            }
        });

        mainPanel.add(backButton, g);








    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        //pergi ke panel home
        MainFrame mainFrame = MainFrame.getInstance();
        mainFrame.navigateTo(HomeGUI.KEY);
    }

    /**
    * Method untuk mendaftarkan member pada sistem.
    * Akan dipanggil jika pengguna menekan "registerButton"
    * */
    private void handleRegister(String nama, String noHp, String password) {
        //jika empty
        if(nama.equals("") || noHp.equals("") || password.equals("")){
            JOptionPane.showMessageDialog(null, "Semua field diatas harus disi", "Empty Field", 0);
            return;
        }
        long nomor = 0;
        //validasi nomor
        try{
            nomor = Long.parseLong(noHp);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Nomor handphone harus berisi angka !!!","Invalid Phone Number" , 0);
            return;
        }
        Member member = loginManager.register(nama,noHp,password); // register
        if(member == null){ //jika sudah ada id nya
            String total = String.format("User dengan nama %s dan no HP %s sudah ada", nama, noHp);
            JOptionPane.showMessageDialog(null, total, "Registration failed", 0);
            
        }
        else{ //berhasil
            String berhasil = String.format("Berhasil membuat user dengan ID %s", member.getId());
            JOptionPane.showMessageDialog(null, berhasil, "Registration successfull", 1);
            
        }
        nameTextField.setText("");
        phoneTextField.setText("");
        passwordField.setText("");
        MainFrame mainFrame = MainFrame.getInstance();
        mainFrame.navigateTo(HomeGUI.KEY); //balik ke home
        


    }
}
