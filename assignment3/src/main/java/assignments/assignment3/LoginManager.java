package assignments.assignment3;

import assignments.assignment1.NotaGenerator;
import assignments.assignment3.user.Member;
import assignments.assignment3.user.menu.EmployeeSystem;
import assignments.assignment3.user.menu.MemberSystem;
import assignments.assignment3.user.menu.SystemCLI;

public class LoginManager {
    private final EmployeeSystem employeeSystem;
    private final MemberSystem memberSystem;

    public LoginManager(EmployeeSystem employeeSystem, MemberSystem memberSystem) {
        this.employeeSystem = employeeSystem;
        this.memberSystem = memberSystem;
    }

    /**
     * Method mapping dari ke SystemCLI yang sesuai.
     *
     * @param id -> ID dari user yang akan menggunakan SystemCLI
     * @return SystemCLI object yang sesuai dengan ID, null if  ID tidak ditemukan.
     */
    public SystemCLI getSystem(String id){
        if(memberSystem.isMemberExist(id)){
            return memberSystem;
        }
        if(employeeSystem.isMemberExist(id)){
            return employeeSystem;
        }
        return null;
    }

    /**
     * Mendaftarkan member baru dengan informasi yang diberikan.
     *
     * @param nama -> Nama member.
     * @param noHp -> Nomor handphone member.
     * @param password -> Password akun member.
     * @return Member object yang berhasil mendaftar, return null jika gagal mendaftar.
     */
    public Member register(String nama, String noHp, String password) {

        String id = generateId(nama,noHp); //ambil id seperti TP 1
        SystemCLI checker = getSystem(id);

        if(checker == null){ // nge check apakah kosong atau sudah ada
            Member member = new Member(nama,id,password);
            memberSystem.addMember(member); //masukan ke arrayList member jika blm ada
            return member;

        }
        return null;

    }

    public static String generateId(String nama, String nomorHP){ //generate ID seperti TP 1
        
        
        String arr[] = nama.split(" ",2);
        String firstword = arr[0];
        firstword = firstword.toUpperCase(); //ke Uppercase
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
    

    

}