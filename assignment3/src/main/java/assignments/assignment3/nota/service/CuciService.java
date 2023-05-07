package assignments.assignment3.nota.service;

public class CuciService implements LaundryService{
    public boolean sekali = false;
    @Override
    public String doWork() {
        //lgsg true setelah do work
        sekali = true;
        return "Sedang mencuci";
    }

    @Override
    public boolean isDone() {
        
        if(sekali) return true; //jika sdh sls
        return false;
    }

    @Override
    public long getHarga(int berat) {
        //lgsg return 0 krn sdh dihitung
        return 0;
    }

    @Override
    public String getServiceName() {
        return "Cuci";
    }
}
