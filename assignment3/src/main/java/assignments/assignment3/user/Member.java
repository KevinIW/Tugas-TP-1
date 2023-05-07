package assignments.assignment3.user;

import java.util.ArrayList;

import assignments.assignment3.nota.Nota;
public class Member {
    protected String id;
    protected String password;
    protected String nama;
    protected String noHp;
 //   protected Nota[] notaList = new Nota[0];
    protected ArrayList<Nota> notaList = new ArrayList<Nota>();

    public Member(String nama, String id, String password) {
        this.nama = nama;
        this.id = id;
        this.password = password;
    }

    /**
     * Method otentikasi member dengan ID dan password yang diberikan.
     *
     * @param id -> ID anggota yang akan diautentikasi.
     * @param password -> password anggota untuk mengautentikasi.
     * @return true jika ID dan password sesuai dengan instance member, false jika tidak.
     */
    public boolean login(String id, String password) {
        return id.equals(this.id) && authenticate(password); //cek apakah sama dan valid
    }

    /**
     * Menambahkan nota baru ke NotaList instance member.
     *
     * @param nota Nota object untuk ditambahkan.
     */
    public void addNota(Nota nota) {
    
        notaList.add(nota); //masukkan ke arrayList Nota
    }

    /**
     * Method otentikasi member dengan password yang diberikan.
     *
     * @param password -> sandi password anggota untuk mengautentikasi.
     * @return true jika ID dan password sesuai dengan instance member, false jika tidak.
     */
    protected boolean authenticate(String password) {
        
        return password.equals(this.password);
    }

    // Dibawah ini adalah getter

    public String getNama() {
        return nama;
    }



    public ArrayList<Nota> getNotaList() {
        return notaList;
    }

   

    public String getId(){
        
        return id;
    
    }

    
}