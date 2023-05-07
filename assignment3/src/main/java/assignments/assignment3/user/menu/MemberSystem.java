package assignments.assignment3.user.menu;
import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;
import static assignments.assignment3.nota.NotaManager.fmt;
import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.SetrikaService;
import assignments.assignment3.nota.service.CuciService;
import assignments.assignment3.user.Member;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import java.util.ArrayList;
public class MemberSystem extends SystemCLI {
    /**
     * Memproses pilihan dari Member yang masuk ke sistem ini sesuai dengan menu specific.
     *
     * @param choice -> pilihan pengguna.
     * @return true jika user log.
     */
    @Override
    protected boolean processChoice(int choice) {
        boolean logout = false;
       
        
        if(choice == 3){ //jika user pilih choice 3
            return true; // logout
        }
        else if(choice == 1){
            
            
            System.out.println("Masukkan paket laundry: "); //sesuai permintaan template
            showPaket();
            //anggapan dijamin valid
            String paket = in.nextLine();
            paket = paket.toLowerCase();
            System.out.println("Masukan berat cucian anda [Kg]: ");
            int berat = in.nextInt();
            in.nextLine();
            if(berat<2){//jika berat kurang 2
                berat = 2;
                System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kgS") ;
            }
            System.out.println("Apakah kamu ingin cucianmu disetrika oleh staff professional kami?");
            System.out.println("Hanya tambah 1000 / kg :0");
            System.out.println("[Ketik x untuk tidak mau]: ");
            String setrika = in.nextLine();
            System.out.println("Mau diantar oleh kurir kami? Dijamin aman dan cepat sampai tujuan!");
            System.out.println("Cuma 2000 / 4kg, kemudian 500 / kg");
            System.out.println("[Ketik x untuk tidak mau]: ");
            String antar = in.nextLine();
            System.out.println("Nota berhasil dibuat!");
            

            String tanggal = fmt.format(NotaManager.cal.getTime()); 
            Nota nota = new Nota(loginMember,berat,paket,tanggal);
            
            if(!setrika.equals("x")){
                SetrikaService service2 = new SetrikaService(); //jika bukan x bikin service setrika
                nota.addService(service2);

            }

            if(!antar.equals("x")){ //jika bukan x bikin service antar
                AntarService service3 = new AntarService();
                nota.addService(service3);

            }
            NotaManager.addNota(nota); //add ke notamanager
            loginMember.addNota(nota); // add ke loginMember
            return false; //tidak logout
        }
        else if(choice == 2){
            for(Nota nota : loginMember.getNotaList()){
                System.out.println(nota.toString()); //print semua nota member
            }
            return false; //tidak logout
        }
        return true;
    
    }

    /**
     * Displays specific menu untuk Member biasa.
     */
    @Override
    protected void displaySpecificMenu() {
        System.out.println("1. Saya ingin laundry");
        System.out.println("2. Lihat detail nota saya");
        System.out.println("3. Logout");
    }

    /**
     * Menambahkan Member baru ke sistem.
     *
     * @param member -> Member baru yang akan ditambahkan.
     */
    public void addMember(Member member) {
        //add member
        memberList.add(member);
       
    }

    private static void showPaket() {
        System.out.println("+-------------Paket-------------+");
        System.out.println("| Express | 1 Hari | 12000 / Kg |");
        System.out.println("| Fast    | 2 Hari | 10000 / Kg |");
        System.out.println("| Reguler | 3 Hari |  7000 / Kg |");
        System.out.println("+-------------------------------+");
    }

}