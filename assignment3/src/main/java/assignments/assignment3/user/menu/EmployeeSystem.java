package assignments.assignment3.user.menu;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.user.Employee;
import assignments.assignment3.user.Member;

import static assignments.assignment3.nota.NotaManager.notaList;

public class EmployeeSystem extends SystemCLI {

    /**
     * Membuat object baru EmployeeSystem dan mendaftarkan Employee pada CuciCuci
     */
    public EmployeeSystem() {
       /*  memberList = new Member[]{
                new Employee("Dek Depe", "akuDDP"),
                new Employee("Depram", "musiktualembut"),
                new Employee("Lita Duo", "gitCommitPush"),
                new Employee("Ivan Hoshimachi", "SuamiSahSuisei"),
        }; */
        memberList.add( new Employee("Dek Depe", "akuDDP"));
        memberList.add( new Employee("Depram", "musiktualembut"));
        memberList.add( new Employee("Lita Duo", "gitCommitPush"));
        memberList.add( new Employee("Ivan Hoshimachi", "SuamiSahSuisei"));
    }

    /**
     * Memproses pilihan dari employee yang masuk ke sistem ini sesuai dengan menu specific.
     *
     * @param choice -> pilihan pengguna.
     * @return true jika user log.
     */
    @Override
    protected boolean processChoice(int choice) {
        boolean logout = false;
        
        if(choice == 3){
            return true;
        }
        else if(choice == 1){
            System.out.printf("Stand back! %s beginning to nyuci!\n",loginMember.getNama());
            for( Nota nota : notaList){
               String buat = nota.kerjakan();
               System.out.printf("Nota %d : %s", nota.getId() , buat);
            }
            return false;
        }
        else if( choice == 2){
            for( Nota nota : notaList){
            
                System.out.printf("Nota %d : %s \n", nota.getId(),nota.getNotaStatus());
             }
             return false;
        }
        return true;
       
    }

    /**
     * Displays specific menu untuk Employee.
     */
    @Override
    protected void displaySpecificMenu() {
        System.out.println("1. It's nyuci time");
        System.out.println("2. Display List Nota");
        System.out.println("3. Logout");
    }
}