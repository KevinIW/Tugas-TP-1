package assignments.assignment2;

import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;

import assignments.assignment1.NotaGenerator;

public class Nota {
    
    private Member member;
    private String paket;
    private String tanggalMasuk;
    private int berat;
    private int idNota;
    private int sisaHariPengerjaan;
    private Boolean isReady;
    public Nota(Member member, String paket, int berat, String tanggalMasuk, int idNota) {
        
        this.member = member;
        this.paket = paket ;
        this.berat = berat;
        this.tanggalMasuk = tanggalMasuk;
        this.idNota = idNota;
        member.tambahBonusCounter();
        setsisaHariPengerjaaan(paket);
    }
    public String getPaket(){
        return this.paket;
    }
    public String getTanggalMasuk(){
        return this.tanggalMasuk;
    }
    public int getBerat(){
        return this.berat;
    }
    public String getNota(){
        String nota2 = generateNota(member.getId(),getPaket(),getBerat(),getTanggalMasuk(), getIdnota());
        return nota2;
    }
    public int getIdnota(){
        return this.idNota;
    }

    public void setsisaHariPengerjaaan(String paket){
       

        int hari = 0;
        if(paket.equals("express")) {
            
            hari = 1;
        }
        else if(paket.equals("fast")){
            
            hari = 2;

        }
        else {
            
            hari = 3;
        }
        this.sisaHariPengerjaan = hari;

    }

    public int getSisaHari(){
        return this.sisaHariPengerjaan;
        
    }

    
    public void setSisaHari(int hari){
        this.sisaHariPengerjaan = hari;
    }

    public Boolean isReady(){
        if(this.sisaHariPengerjaan<=0) return true;
        return false;
    }

     public  String generateNota(String id, String paket, int berat, String tanggalTerima, int idNota){
       
        String nota = "Berhasil menambahkan nota!\n ";
        String formatStr0 = String.format("[ID Nota = %d]\n",idNota);
        String awal = "";
        String tengah="Harga :\n";
        String formatStr1 = String.format("ID    :  %s\n", id);
        String formatStr2 = String.format("Paket : %s\n",paket);
        if(berat<2){//jika berat kurang 2
            berat = 2;
            awal = "Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg\n";
        }
        int harga;
        int hari;
        if(paket.equals("express")) {
            harga = 12000;
            hari = 1;
        }
        else if( paket.equals("fast")){
            harga = 10000;
            hari = 2;

        }
        else {
            harga = 7000;
            hari = 3;
        }
        int hargaTotal = harga * berat;
        String formatStr3 = "";
        if(member.getBonusCounter() == 3){
            
            formatStr3 = String.format("%d kg x %d = %d = %d  (Discount member 50 persen !!!) \n", berat, harga, hargaTotal, hargaTotal/2);
        }
        else{
            formatStr3 = String.format("%d kg x %d = %d\n", berat, harga, hargaTotal);
        }
        //formatting string 
       
        String formatStr4 = String.format("Tanggal Terima  : %s\n", tanggalTerima);


        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); //men format calendar

        Calendar cal = Calendar.getInstance();  
        try{  
           cal.setTime(sdf.parse(tanggalTerima));  
        }catch(ParseException e){  
            e.printStackTrace();  
         }  
             
        
        cal.add(Calendar.DAY_OF_MONTH, hari);  //menanmbah hari di calendar
        String tanggalJadi = sdf.format(cal.getTime()); 

        String formatStr5 = String.format("Tanggal Selesai  : %s\n", tanggalJadi);
        String status = "Status      	: Belum bisa diambil :(";
        
        nota = (awal + nota + formatStr0 + formatStr1 + formatStr2 + tengah + formatStr3 + formatStr4 + formatStr5 + status);
        
        
       
        return nota;
    }
    


}
