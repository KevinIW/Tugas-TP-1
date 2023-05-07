package assignments.assignment3.nota.service;

public class CuciService implements LaundryService{
    public boolean sekali = false;
    @Override
    public String doWork() {
        // TODO
        sekali = true;
        return "Sedang mencuci";
    }

    @Override
    public boolean isDone() {
        
        if(sekali) return true;
        return false;
    }

    @Override
    public long getHarga(int berat) {
        // TODO
        return 0;
    }

    @Override
    public String getServiceName() {
        return "Cuci";
    }
}
