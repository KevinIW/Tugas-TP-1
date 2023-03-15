package assignments.assignment1;

import java.util.Scanner;
import java.text.SimpleDateFormat;  
import java.util.Calendar;  
import java.text.ParseException;  

public class NotaGenerator {
    private static final Scanner input = new Scanner(System.in);

    /**
     * Method main, program utama kalian berjalan disini.
     */
    public static void main(String[] args) {
        
        
        int n = 1; //integer awal untuk memulai menu
        while(n==1){
            //format awal
            printMenu();
            System.out.print("Pilihan: ");
            char pilihan = input.next().charAt(0);
            input.nextLine();
          
            
            if(pilihan == '1' || pilihan == '2'){ //memilih pilihan 1 atau pilihan 2
                System.out.print("================================\n");
                System.out.print("Masukkan nama Anda: ");
                
                String nama = input.nextLine();

                System.out.print("Masukkan nomor handphone Anda: ");
                String handphone = input.nextLine();
                Boolean checker = true;
                while(checker){ //check nomor HP 
                    char[] m = handphone.toCharArray(); 
        
                    int len = m.length;
                    int check = 0;
                    for (int i = 0; i < len; i++){ //iterasi setiap element no HP
                        int x = (int) m[i];

                        if(x>=48 && x <=57){ //check apakah bilangan atau bukan
                            check+=1;
                        }
                        else
                        {
                        System.out.print("Nomor hp hanya menerima digit\n");
                        handphone = input.next();
                        check=0;
                        break;
                        }
                    }
                    if(check == len){
                        checker = false; //jika benar semua maka keluar
                    }
                }
                String id;
                id = generateId(nama, handphone);
                if(pilihan == '1'){
                    System.out.printf("ID anda: %s",id);
                    System.out.println("");
                }
                else //jika pilihan 2
                {
                    System.out.print("Masukkan tanggal terima: \n");
                    String terima = input.next();
                    String laundry = "";
                    Boolean laundry1 = true;
                    while(laundry1) //check laundry
                    {
                        System.out.println("Masukkan paket laundry: ");
                        
                        laundry = input.next();
                        
                        if(laundry.equals("?"))
                        {
                            showPaket();
                        }
    
                       else if(laundry.equals("express") || laundry.equals("fast")  || laundry.equals("reguler")  )
                        {
                            laundry1=false;
                        }
                        else{
                            
                            System.out.printf("Paket %s tidak diketahui\n", laundry);
                            System.out.println("[ketik ? untuk mencari tahu jenis paket]");
                        }
                    }


                   
                    System.out.println("Masukkan berat cucian Anda [Kg]:");
                   
                    int beratCuci;
                    beratCuci = 0;
                    while(true){ //coba berat cucian
                        try{
                            beratCuci = input.nextInt();
                            input.nextLine();
                        }
                        catch(Exception e){
                            input.nextLine();
                            System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                            continue;
                        }
                        if(beratCuci<0){ //jika kurang dari 0
                            System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                            
                        }
                        else{ // jika sudah valid
                            break;
                        }

                    }
                    
                    String hasil = generateNota(id, laundry, beratCuci, terima);
                    System.out.println(hasil);
                    
                }
                    
                   

            }
               
            else if(pilihan == '0'){
                n=0;//menyelesaikan program
                System.out.println("================================");
                System.out.println("Terima kasih telah menggunakan NotaGenerator!");
    
            }
            
            else { //jika perintah tidak diketahui
                    System.out.println("================================");
                    System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
                }
                
    
            }
    
            input.close();
            
        }
            

    

    /**
     * Method untuk menampilkan menu di NotaGenerator.
     */
    private static void printMenu() {
        System.out.println("Selamat datang di NotaGenerator!");
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate ID");
        System.out.println("[2] Generate Nota");
        System.out.println("[0] Exit");
    }

    /**
     * Method untuk menampilkan paket.
     */
    private static void showPaket() {
        System.out.println("+-------------Paket-------------+");
        System.out.println("| Express | 1 Hari | 12000 / Kg |");
        System.out.println("| Fast    | 2 Hari | 10000 / Kg |");
        System.out.println("| Reguler | 3 Hari |  7000 / Kg |");
        System.out.println("+-------------------------------+");
    }

    /**
     * Method untuk membuat ID dari nama dan nomor handphone.
     * Parameter dan return type dari method ini tidak boleh diganti agar tidak mengganggu testing
     *
     * @return String ID anggota dengan format [NAMADEPAN]-[nomorHP]-[2digitChecksum]
     */
    public static String generateId(String nama, String nomorHP){
        
        
        String arr[] = nama.split(" ",2);
        String firstword = arr[0];
        firstword = firstword.toUpperCase(); //ke Upperacse
        char[] x = firstword.toCharArray(); //jadikan array of characters
        int len = x.length;
        int total = 0;
        int temp;
            for(int i = 0 ;i<=len-1;i++){ // mencari total nilai dari string
                if((int)(x[i])>=65 && (int)x[i]<=90){//jika huruf besar A-z
                    temp = x[i] - 'A'+ 1;
                    total +=temp;
                }
                else{ //jika bukan aplhabet
                    total +=7;
                }
              
            
            }

        char[] y = nomorHP.toCharArray();
        int len2 = y.length;
        int temp2;
            for(int i = 0 ;i<=len2-1;i++){ // mencari total nilai dari string
                temp2 = y[i] - '0';
                total +=temp2;
            
            }
        total+=7;
        String akhir = Integer.toString(total); 
        if(akhir.length()<2){ //modif jika digit kurang dari 2
            akhir =   "0" + akhir;
        }
        else if(akhir.length()>2){//modif jika digit lebih dari 2
            akhir = akhir.substring(akhir.length()-2);
        }
        String hasil = firstword + "-" + nomorHP + "-"+akhir;
        return hasil;



    }
    

    /**
     *
     * Method untuk membuat Nota.
     * Parameter dan return type dari method ini tidak boleh diganti agar tidak mengganggu testing.
     *
     * @return string nota dengan format di bawah:
     *         <p>ID    : [id]
     *         <p>Paket : [paket]
     *         <p>Harga :
     *         <p>[berat] kg x [hargaPaketPerKg] = [totalHarga]
     *         <p>Tanggal Terima  : [tanggalTerima]
     *         <p>Tanggal Selesai : [tanggalTerima + LamaHariPaket]
     */

    public static String generateNota(String id, String paket, int berat, String tanggalTerima){
        String nota = "Nota Laundry\n";
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
        //formatting string 
        String formatStr3 = String.format("%d kg x %d = %d\n", berat, harga, hargaTotal);
        String formatStr4 = String.format("Tanggal Terima  : %s\n", tanggalTerima);


        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); //men format calendar

        Calendar cal = Calendar.getInstance();  
        try{  
           cal.setTime(sdf.parse(tanggalTerima));  
        }catch(ParseException e){  
            e.printStackTrace();  
         }  
             
        
        cal.add(Calendar.DAY_OF_MONTH, hari);  //menanmbah hari di calendar
        String tanggalJadi = sdf.format(cal.getTime());  //meminta hasil

        String formatStr5 = String.format("Tanggal Selesai  : %s\n", tanggalJadi);
        nota = (awal + nota + formatStr1 + formatStr2 + tengah + formatStr3 + formatStr4 + formatStr5);
        
        
       
        return nota;
    }
}
