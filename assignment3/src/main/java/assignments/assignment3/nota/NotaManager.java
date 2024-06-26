package assignments.assignment3.nota;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class NotaManager {
    public static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    public static Calendar cal = Calendar.getInstance();
  //  static public Nota[] notaList = new Nota[0];
    static public ArrayList<Nota> notaList = new ArrayList <Nota>();

    /**
     * Skips ke hari berikutnya dan update semua entri nota yang sesuai.
     */
    public static void toNextDay(){
        

        for(Nota nota : notaList){
            nota.toNextDay(); //tiap nota di skip 1 hari
        }
        
        cal.add(Calendar.DAY_OF_MONTH,1); //tambahkan 1 hari
    }

    /**
     * Menambahkan nota baru ke NotaList.
     *
     * @param nota Nota object untuk ditambahkan.
     */
    public static void addNota(Nota nota){
        

        notaList.add(nota); //menambahkan nota
    }
}
