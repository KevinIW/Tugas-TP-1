package assignments.assignment4.gui;

import assignments.assignment3.LoginManager;
import assignments.assignment3.user.menu.EmployeeSystem;
import assignments.assignment3.user.menu.MemberSystem;
import assignments.assignment3.user.menu.SystemCLI;
import assignments.assignment4.MainFrame;
import assignments.assignment4.gui.member.employee.EmployeeSystemGUI;
import assignments.assignment4.gui.member.member.MemberSystemGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JPanel {
    public static final String KEY = "LOGIN";
    private JPanel mainPanel;
    private JLabel idLabel;
    private JTextField idTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton backButton;
    private LoginManager loginManager;

    public LoginGUI(LoginManager loginManager) {
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
        
        //set grid nya
        GridBagConstraints g = new GridBagConstraints();
        g.anchor = GridBagConstraints.LINE_START;
        g.weighty = 1.0;
        g.insets = new Insets(2, 0, 2, 0);
        //set id nya
        g.gridx = 0;
        g.gridy = 0;
        idLabel = new JLabel("Masukkan id anda: ");
        mainPanel.add(idLabel,g);
        //set text field id
        g.gridx = 0;
        g.gridy = 1;
        g.ipadx = 400;
        idTextField = new JTextField();
        mainPanel.add(idTextField,g);
        //set label password
        g.gridx = 0;
        g.gridy = 2;
        passwordLabel = new JLabel("Masukkan password anda: ");
        mainPanel.add(passwordLabel,g);
        //set password field
        g.gridx = 0;
        g.gridy = 3;
        g.ipadx = 400;
        passwordField = new JPasswordField();
        mainPanel.add(passwordField,g);
        //set button login
        g.gridx = 0;
        g.gridy = 4;
        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String id = idTextField.getText();
            
                String password = new String(passwordField.getPassword());
            
                handleLogin(id,password); // pergi ke void ini
            }
        });
        mainPanel.add(loginButton,g);

        //set button kembali
        g.gridx = 0;
        g.gridy = 5;
        backButton = new JButton("Kembali");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                handleBack(); //ke void ini
            }
            
        });
        mainPanel.add(backButton,g);



    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        //balik ke home
        idTextField.setText("");
        passwordField.setText("");
        MainFrame mainFrame = MainFrame.getInstance();
        mainFrame.navigateTo(HomeGUI.KEY);
    }

    /**
     * Method untuk login pada sistem.
     * Akan dipanggil jika pengguna menekan "loginButton"
     * */
    private void handleLogin(String id, String password) {
        
        SystemCLI syscall = loginManager.getSystem(id); // ambil id
        if(syscall == null){ // jika salah id or password
            JOptionPane.showMessageDialog(null, "Invalid id or password", "Login failed", 0);
            return;
        }
        MainFrame mainFrame = MainFrame.getInstance();
        Boolean loginis = false;
        loginis = mainFrame.login(id, password); //ambil boolean nya
        
        if(loginis == true){ //jika benar pass sama id nya
            if(syscall instanceof MemberSystem){
                idTextField.setText("");
                passwordField.setText("");
                mainFrame.navigateTo(MemberSystemGUI.KEY); // ke memberSystem
            }
            else if(syscall instanceof EmployeeSystem){
                idTextField.setText("");
                passwordField.setText("");
                mainFrame.navigateTo(EmployeeSystemGUI.KEY);// ke employeeSystem
            }

        }
        else{
            JOptionPane.showMessageDialog(null, "Invalid id or password", "Login Failed", 0);
            idTextField.setText("");
            passwordField.setText("");
            return;
        }
            
           
        
       
        


    }
}
