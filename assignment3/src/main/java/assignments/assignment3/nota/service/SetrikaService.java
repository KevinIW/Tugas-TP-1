package assignments.assignment3.nota.service;

public class SetrikaService implements LaundryService{
    public boolean sekali = false;
    @Override
    public String doWork() {
        //jika sekali do lgsg true
        sekali = true;
        return "Sedang menyentrika...";
    }

    @Override
    public boolean isDone() {
        
        if(sekali) return true; //jika sdh sls
        return false;
    }

    @Override
    public long getHarga(int berat) {
    
        long total = berat*1000; //tiap berat kali 1000
        return total;
    }

    @Override
    public String getServiceName() {
        return "Setrika";
    }
}
