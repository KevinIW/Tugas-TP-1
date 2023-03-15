package assignments.assignment2;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.util.ArrayList;

import static assignments.assignment1.NotaGenerator.*;

public class MainMenu {
    private static final Scanner input = new Scanner(System.in);
    private static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    private static Calendar cal = Calendar.getInstance();
    private static ArrayList<Nota> notaList = new ArrayList<Nota>();
    private static ArrayList<Member> memberList = new ArrayList<Member>();
    private static  int totalMember = 0;
    private static  int totalNota = 0;
    
    

    public static void main(String[] args) {
        boolean isRunning = true;
        while (isRunning) {
            printMenu();
            System.out.print("Pilihan : ");
            String command = input.nextLine();
            System.out.println("================================");
            switch (command){
                case "1" -> handleGenerateUser();
                case "2" -> handleGenerateNota();
                case "3" -> handleListNota();
                case "4" -> handleListUser();
                case "5" -> handleAmbilCucian();
                case "6" -> handleNextDay();
                case "0" -> isRunning = false;
                default -> System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
            }
        }
        System.out.println("Terima kasih telah menggunakan NotaGenerator!");
    }

    private static void handleGenerateUser() {
        

        System.out.print("Masukkan nama Anda: \n");
                
        String nama = input.nextLine();

        System.out.print("Masukkan nomor handphone Anda: \n");
        String handphone = input.next();
        input.nextLine();
        
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
                input.nextLine();
                check=0;
                break;
                }
            }
            if(check == len){
                checker = false; //jika benar semua maka keluar
            }
        }
        
           
            if(totalMember == 0){
                Member member = new Member(nama, handphone,0);
                memberList.add(member);
                totalMember++;
                System.out.printf("Berhasil membuat member dengan ID %s \n", member.getId());
            }
            else{
                Boolean isDaftar = true;
                for(Member member : memberList){
                    if(nama.equals(member.getNama()) && handphone.equals(member.getnoHp())){
                        System.out.printf("Member dengan nama %s dan nomor hp %s sudah ada!\n", member.getNama(),member.getId());
                        isDaftar = false;
                        break;
                    }
                }

                if(isDaftar){
                    Member member = new Member(nama, handphone,0);
                    memberList.add(member);
                    totalMember++;
                    System.out.printf("Berhasil membuat member dengan ID %s \n", member.getId());
                }
            }
        
           
                
            
            
        
       
        
    }

    private static void handleGenerateNota() {
    
        System.out.println("Masukan ID member: ");
        String id = input.nextLine();
        Boolean isMember = false;
        for(Member member: memberList){
            if(member.getId().equals(id))
            
                isMember = true;

                break;
        }

        if(isMember){
            Boolean laundry1 = true;
            String laundry =  "";
            while(laundry1) //check laundry
            {
                System.out.println("Masukkan paket laundry: ");
                
                laundry = input.next();
                laundry = laundry.toLowerCase();
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
            Member member = cariMember(id);
            Nota nota = new Nota(member,laundry,beratCuci,fmt.format(cal.getTime()),totalNota);
            notaList.add(nota);
            totalNota++;
            System.out.println(nota.getNota());
        }
        else
        {
            System.out.printf("Member dengan ID %s tidak ditemukan\n", id);
        }
        
    }

    private static void handleListNota() {
        

        if(notaList.size() ==0){
            System.out.println("Terdapat 0 nota dalam sistem");

        }
        else{

            System.out.printf(" Terdaftar %d nota dalam sistem. \n", notaList.size());
            for(Nota nota : notaList){
                if(nota.isReady()){
                    System.out.printf("- [%d] Status      	: Sudah bisa diambil :) \n ",nota.getIdnota());
                }
                else
                {
                    System.out.printf("- [%d] Status      	: Belum bisa diambil :) \n",nota.getIdnota());
                }
            }
        }

       
    }

    private static void handleListUser() {
        
        if(memberList.size() == 0){
            System.out.println("Terdaftar 0 member dalam sistem.");
        }
        else{
            int len = memberList.size();
            System.out.printf("Terdaftar %d member dalam sistem\n",len);
            for(Member member: memberList){
                System.out.printf("- %s : %s\n", member.getId(),member.getNama());
                
            }

        }
        
                
    
        

    }

    private static void handleAmbilCucian() {
        
        System.out.println("Masukan ID nota yang akan diambil: ");
        int idNota;
        idNota = 0;
        Boolean success = true;
        while(success){ //coba berat cucian
            try{
                idNota = input.nextInt();
                input.nextLine();
            }
            catch(Exception e){
                input.nextLine();
                success = false;
                System.out.println("ID nota berbentuk angka!");
                return;
                
                

                
            }
            success = false;
            

        }
        Nota nota = cariNota(idNota);
        int index = 0;
    
        if(nota == null){
            System.out.printf("Nota dengan ID %d tidak ditemukan!\n",idNota);
        }
        else{
            for(int i=0;i<notaList.size();i++){
                if(idNota == notaList.get(i).getIdnota()){
                    index = i;
                    break;
                }
            }
            if(nota.isReady()){
                System.out.printf("Nota dengan ID %d berhasil diambil!\n",idNota);
                
                notaList.remove(index);
            }
            else{
                System.out.printf("Nota dengan ID %d gagal diambil!\n",idNota);
            }
        }
      
        
      

    }

    private static void handleNextDay() {
        
        cal.add(Calendar.DATE,1);
        System.out.println("Dek Depe tidur hari ini... zzz..");
        for(Nota nota : notaList){
            int hari = nota.getSisaHari() - 1;
            nota.setSisaHari(hari);
        }
        for(Nota nota : notaList){
            if(nota.getSisaHari() <= 0){
                System.out.printf("Laundry dengan nota ID %d sudah dapat diambil.\n ",nota.getIdnota());
            }
        }

        System.out.println("Selamat pagi dunia!");
        System.out.println("Dek Depe: It's CuciCuci Time.");
    }

    private static void printMenu() {
        System.out.println("\nSelamat datang di CuciCuci!");
        System.out.printf("Sekarang Tanggal: %s\n", fmt.format(cal.getTime()));
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate User");
        System.out.println("[2] Generate Nota");
        System.out.println("[3] List Nota");
        System.out.println("[4] List User");
        System.out.println("[5] Ambil Cucian");
        System.out.println("[6] Next Day");
        System.out.println("[0] Exit");
    }

    private static void showPaket() {
        System.out.println("+-------------Paket-------------+");
        System.out.println("| Express | 1 Hari | 12000 / Kg |");
        System.out.println("| Fast    | 2 Hari | 10000 / Kg |");
        System.out.println("| Reguler | 3 Hari |  7000 / Kg |");
        System.out.println("+-------------------------------+");
    }

    public static Member cariMember(String nama){
        for(Member member: memberList){
            if(member.getId().equals(nama))
                return member;
        }
        return null;
    }

    public static Nota cariNota(int id){
        for(Nota nota: notaList){
            if(nota.getIdnota()==id){
                return nota;
            }
               
        }
        return null;
    }


    

}
