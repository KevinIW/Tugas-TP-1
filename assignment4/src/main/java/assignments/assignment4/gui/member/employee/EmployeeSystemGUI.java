package assignments.assignment4.gui.member.employee;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;

import assignments.assignment3.user.menu.SystemCLI;
import assignments.assignment4.gui.member.AbstractMemberGUI;

import javax.swing.*;
import java.awt.event.ActionListener;

public class EmployeeSystemGUI extends AbstractMemberGUI {
    public static final String KEY = "EMPLOYEE";

    public EmployeeSystemGUI(SystemCLI systemCLI) {
        super(systemCLI);
    }


    @Override
    public String getPageName(){
        return KEY;
    }

    /**
     * Method ini mensupply buttons yang sesuai dengan requirements Employee.
     * Button yang disediakan method ini BELUM memiliki ActionListener.
     *
     * @return Array of JButton, berisi button yang sudah stylize namun belum ada ActionListener.
     * */
    @Override
    protected JButton[] createButtons() {
        // set buttonnya
        JButton cuci = new JButton("It's Nyuci time");
        JButton display = new JButton("Display List Nota");
        return new JButton[]{cuci, display
        };
    }

    /**
     * Method ini mensupply ActionListener korespondensi dengan button yang dibuat createButtons()
     * sesuai dengan requirements MemberSystem.
     *
     * @return Array of ActionListener.
     * */
    @Override
    protected ActionListener[] createActionListeners() {
        return new ActionListener[]{
                e -> cuci(),
                e -> displayNota(),
        };
    }

    /**
     * Menampilkan semua Nota yang ada pada sistem.
     * Akan dipanggil jika pengguna menekan button pertama pada createButtons
     * */
    private void displayNota() {

        // display semua nota dengan loop
        String total = "";
        for( Nota nota : NotaManager.notaList){
            
            String display = String.format("Nota %d : %s \n",nota.getId(),nota.getNotaStatus());
            total = total + display;

            
         }
         if(NotaManager.notaList.size() == 0){ //jika belum ada nota
            total = "Belum ada nota";
            JOptionPane.showMessageDialog(null,total,"List Nota",0);
            return;
         }
         //jika ada nota
         JOptionPane.showMessageDialog(null, total, "List Nota", 1);


    }

    /**
     * Menampilkan dan melakukan action mencuci.
     * Akan dipanggil jika pengguna menekan button kedua pada createButtons
     * */
    private void cuci() {
        
        //show stand back
        String message = String.format("Stand back! %s beginning to nyuci!\n",loggedInMember.getNama());
        JOptionPane.showMessageDialog(null, message, "It's Nyuci Time", 1);
        //cuci nota nota yang ada
        String total = "";
        for( Nota nota : NotaManager.notaList){
            String buat = nota.kerjakan(); //setiap nota total yang ada di kerjakan sekali
            String kerja = String.format("Nota %d : %s \n",nota.getId(),buat);
            total = total + kerja;
            
         }
         if(NotaManager.notaList.size() == 0){ //jika blm pernah cuci
            total = "nothing to cuci here";
            JOptionPane.showMessageDialog(null,total,"Nyuci Results",0);
            return;
         }
         //jika sudah pernah cuci
         JOptionPane.showMessageDialog(null, total, "Nyuci Results", 1);


    }
}
