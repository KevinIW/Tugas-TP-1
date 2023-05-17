package assignments.assignment4.gui.member.member;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;

import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.CuciService;
import assignments.assignment3.nota.service.LaundryService;
import assignments.assignment3.nota.service.SetrikaService;
import assignments.assignment3.user.Member;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CreateNotaGUI extends JPanel {
    public static final String KEY = "CREATE_NOTA";
    private JLabel paketLabel;
    private JComboBox<String> paketComboBox;
    private JButton showPaketButton;
    private JLabel beratLabel;
    private JTextField beratTextField;
    private JCheckBox setrikaCheckBox;
    private JCheckBox antarCheckBox;
    private JButton createNotaButton;
    private JButton backButton;
    private final SimpleDateFormat fmt;
    private final Calendar cal;
    private final MemberSystemGUI memberSystemGUI;
    private JPanel mainPanel;
    private GridBagConstraints g;

    public CreateNotaGUI(MemberSystemGUI memberSystemGUI) {
        this.memberSystemGUI = memberSystemGUI;
        this.fmt = NotaManager.fmt;
        this.cal = NotaManager.cal;

        // Set up main panel, Feel free to make any changes
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        g = new GridBagConstraints();
        initGUI();
        
        add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Method untuk menginisialisasi GUI.
     * Selama funsionalitas sesuai dengan soal, tidak apa apa tidak 100% sama.
     * Be creative and have fun!
     * */
    private void initGUI() {
        // TODO
      
        g.anchor = GridBagConstraints.WEST;
        g.weighty = 1.0;
        g.insets = new Insets(10, 10, 10, 10);

        g.gridx = 0;
        g.gridy = 0;
        paketLabel = new JLabel("Paket laundry: ");
        mainPanel.add(paketLabel,g);

        String [] paket = {"Express", "Fast", "Reguler"};
        paketComboBox = new JComboBox<>(paket);
        paketComboBox.setEditable(false);
        g.gridx = 1;
        g.gridy = 0;
        mainPanel.add(paketComboBox,g);

        g.gridx = 2;
        g.gridy = 0;
        showPaketButton = new JButton("Show Paket");
        showPaketButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                showPaket();
            }
        });
        mainPanel.add(showPaketButton,g);

        g.gridx = 0;
        g.gridy = 1;
        beratLabel = new JLabel("Berat Cucian (Kg): ");
        mainPanel.add(beratLabel,g);

        g.gridx = 1;
        g.gridy = 1;
        g.ipadx = 70;
        beratTextField = new JTextField();
        mainPanel.add(beratTextField,g);
        
        g.gridx = 0;
        g.gridy = 2;
        setrikaCheckBox = new JCheckBox("Tambah Setrika Service (1000/kg)");
        mainPanel.add(setrikaCheckBox,g);

        g.gridx = 0;
        g.gridy = 3;
        antarCheckBox = new JCheckBox("Tambah Antar Service (2000/4kg pertama),kemudian 500/kg ");
        mainPanel.add(antarCheckBox,g);

        g.gridx = 0;
        g.gridy = 4;
        g.fill = GridBagConstraints.HORIZONTAL;
        g.gridwidth = 3;
        createNotaButton =new JButton("Buat Nota");
        createNotaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Boolean setrika = setrikaCheckBox.isSelected();
                Boolean antar = antarCheckBox.isSelected();
                createNota(setrika,antar);
            }
        });
        mainPanel.add(createNotaButton,g);

        g.gridx = 0;
        g.gridy = 5;
        backButton = new JButton("Kembali");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                handleBack();
            }
        });
        mainPanel.add(backButton,g);

        

    }

    /**
     * Menampilkan list paket pada user.
     * Akan dipanggil jika pengguna menekan "showPaketButton"
     * */
    private void showPaket() {
        String paketInfo = """
                        <html><pre>
                        +-------------Paket-------------+
                        | Express | 1 Hari | 12000 / Kg |
                        | Fast    | 2 Hari | 10000 / Kg |
                        | Reguler | 3 Hari |  7000 / Kg |
                        +-------------------------------+
                        </pre></html>
                        """;

        JLabel label = new JLabel(paketInfo);
        label.setFont(new Font("monospaced", Font.PLAIN, 12));
        JOptionPane.showMessageDialog(this, label, "Paket Information", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Method untuk melakukan pengecekan input user dan mendaftarkan nota yang sudah valid pada sistem.
     * Akan dipanggil jika pengguna menekan "createNotaButton"
     * */
    private void createNota(Boolean setrika, Boolean antar) {
        // TODO
        Member member = memberSystemGUI.getLoggedInMember();
        int beratx = 0;
        try{
            String berat = beratTextField.getText();
            beratx = Integer.parseInt(berat);
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,"Berat harus angka","Invalid message",0);
            return;
        }
        if(beratx<2){
            JOptionPane.showMessageDialog(null,"Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg","Info",1);
            
        }
        String paket = paketComboBox.getSelectedItem().toString();
        String tanggalMasuk = NotaManager.fmt.format(cal.getTime());
        Nota nota = new Nota(member, beratx, paket,tanggalMasuk);
        NotaManager.addNota(nota);
        member.addNota(nota);
    
        if(setrika == true){
            nota.addService(new SetrikaService());
        }
        if(antar == true){
            nota.addService(new AntarService());
        }
        

        JOptionPane.showMessageDialog(null, "Nota berhasil dibuat", "Berhasil", 1);
        beratTextField.setText("");
        paketComboBox.setSelectedIndex(0);
        setrikaCheckBox.setSelected(false);
        antarCheckBox.setSelected(false);

    }
            
    

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        // TODO
        MainFrame mainFrame = MainFrame.getInstance();
        mainFrame.navigateTo(MemberSystemGUI.KEY);
    }
}
