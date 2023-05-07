package assignments.assignment3.user;

public class Employee extends Member {
    public static int employeeCount = 0;
    public Employee(String nama, String password) {
        super(nama,generateId(nama), password); //constructor super
        employeeCount++; //nambahkan employee
    }

    /**
     * Membuat ID employee dari nama employee dengan format
     * NAMA_DEPAN-[jumlah employee, mulai dari 0]
     * Contoh: Dek Depe adalah employee pertama yang dibuat, sehingga IDnya adalah DEK-0
     *
     * @param nama -> Nama lengkap dari employee
     */

     private static String generateId(String nama){

        String arr[] = nama.split(" ",2); //Split jd 2
        String firstword = arr[0];
        firstword = firstword.toUpperCase(); //ke Uppercase
        String id = Integer.toString(employeeCount);
        String hasil = firstword+"-"+id; // ID nya

        return hasil;

     }

        
}

