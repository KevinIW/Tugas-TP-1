package assignments.assignment3.nota;
import assignments.assignment3.nota.service.CuciService;
import assignments.assignment3.nota.service.LaundryService;
import assignments.assignment3.user.Member;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import java.util.ArrayList;
public class Nota {
    private Member member;
    private String paket;
    private ArrayList <LaundryService> services = new ArrayList<LaundryService>();
    private long baseHarga;
    private int sisaHariPengerjaan;
    private int berat;
    private int id;
    private String tanggalMasuk;
    private boolean isDone = false;
    private int hari;
    static public int totalNota = 0 ;

    public Nota(Member member, int berat, String paket, String tanggalMasuk) {

        LaundryService service = new CuciService(); //membuat service cuci karena laundry sudah pasti cuci
        addService(service); //ditambahkan seperti biasa
        
        this.member = member;
        this.paket = paket ;
        this.berat = berat;
        this.tanggalMasuk = tanggalMasuk;
        setsisaHariPengerjaaan(paket); // set sisa hari
        this.id = totalNota;
        totalNota++;
        
       
        
    }

    public void addService(LaundryService service){

        //add services
        services.add(service);
    }

    public String kerjakan(){
    

        
        for( LaundryService service : services){
            if(!service.isDone()){ //jika belum selesai
                return service.doWork(); //kerjakan servicenya
                
            }
            else{
                continue;
            }
            
        }
        this.isDone = true; //set true sudah selesai

        
        return "Sudah Selesai";
    }
    public void toNextDay() {
        String test = getNotaStatus(); // utk cek isDone
        if(isDone){
            //kl selesai tdk dikurang sisa Harinya
        }
        else{
            this.sisaHariPengerjaan-=1; //kurangin 1 tiap hr jika blm sls
        }
       

    
    }

    public long calculateHarga(){
        
        long total = 0;
        for(LaundryService service : services){
            total += service.getHarga(this.berat); //tambahkan semua harga dari servicesnya
        }
        total = total + this.baseHarga*this.berat; //tambahkan yang dari cuci juga ke total

        if(!this.isDone){

            if(this.sisaHariPengerjaan<0){ //tambahkan kompensasi nya
                total = total + 2000*this.sisaHariPengerjaan;
            }
           
        }
        else{
            if(this.sisaHariPengerjaan<0){
                total = total + 2000*this.sisaHariPengerjaan; //kompensasi 
            }

        }

        return total;
    }

    public String getNotaStatus(){
    
        for(LaundryService service : services){
            if(!service.isDone()){ //jika blm sls
                return "Belum Selesai";
            }
        }
        this.isDone = true;
        return "Sudah Selesai"; //jika sudah selesai

        
    }
    public int getId(){
        return this.id;
    }

    @Override
    public String toString(){
        //sesuai format
        String line1 = String.format("[ID Nota = %d]\n", this.id);
        String line2 = String.format("ID    : %s\n",member.getId());
        String line3 = String.format("Paket : %s \nHarga :\n ",this.paket);

        long harga = (long)getBerat() * this.baseHarga;
        String line4 = String.format("%d kg x %d = %d\n",getBerat(),this.baseHarga, harga);
    

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); //men format calendar

        Calendar cal = Calendar.getInstance();  
        try{  
           cal.setTime(sdf.parse(this.tanggalMasuk));  
        }catch(ParseException e){  
            e.printStackTrace();  
         }  
             
        
        cal.add(Calendar.DAY_OF_MONTH, hari);  //menanmbah hari di calendar
        String tanggalSelesai = sdf.format(cal.getTime()); 
        String line5 = String.format ("tanggal terima : %s\n",this.tanggalMasuk);
        String line6 = String.format ("tanggal selesai : %s\n", tanggalSelesai);
        String serviceList = String.format("--- SERVICE LIST --- \n");
        for(LaundryService service : services){ //mengatur tambahan servicenya
            String everyService = service.getServiceName();
            String line7 = String.format("-%s @ Rp. %d \n", everyService, service.getHarga(this.berat));
            serviceList = serviceList + line7;
        }

        String test = getNotaStatus();
        if(!this.isDone){ //jika blm sls
            if(this.sisaHariPengerjaan<0){ //jika ada kompensasi
                int hari = this.sisaHariPengerjaan *-1;
                String kompensasi = String.format("Harga Akhir: %d Ada Kompensasi keterlambatan %d x 2000 hari \n", calculateHarga(), hari);
                serviceList = serviceList + kompensasi;
            }
            else{ //jika tidak ada
                String selesai = String.format("Harga Akhir : %d \n", calculateHarga());
                serviceList = serviceList + selesai;
            }
            
        }
        else{ //jika sudah selesai

            if(this.sisaHariPengerjaan<0){ //ada kompensasi
                int hari = this.sisaHariPengerjaan *-1;
                String kompensasi = String.format("Harga Akhir: %d Ada Kompensasi keterlambatan %d x 2000 hari \n", calculateHarga(), hari);
                serviceList = serviceList + kompensasi;
            }
            else{
                String selesai = String.format("Harga Akhir : %d \n", calculateHarga());
                serviceList = serviceList + selesai;
            }

        }
        String hasil = line1 + line2 + line3 + line4 + line5 + line6 + serviceList;


        return hasil;
    }

    // Dibawah ini adalah getter

    public String getPaket() {
        return this.paket;
    }

    public int getBerat() {

        if(this.berat<2){
            this.berat = 2;
        }
        return this.berat;
    }

    public String getTanggal() {
        return this.tanggalMasuk;
    }

    public int getSisaHariPengerjaan(){
        return this.sisaHariPengerjaan;
    }
    public boolean isDone() {
        return this.isDone;
    }

    public ArrayList<LaundryService> getServices(){
        return services;
    }

    public void setsisaHariPengerjaaan(String paket){
        //set awal sisa hari pengerjaan
 
         
         paket = paket.toLowerCase();
         if(paket.equals("express")) { //set sisa hari nya brp
             
             this.hari = 1;
             this.baseHarga = 12000; //set base harganya
         }
         else if(paket.equals("fast")){
             
             this.hari = 2;
             this.baseHarga = 10000;
 
         }
         else {
             
             this.hari = 3;
             this.baseHarga = 7000;
         }
         this.sisaHariPengerjaan = this.hari;
 
     }

     

   
    


}
