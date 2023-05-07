package assignments.assignment3.nota.service;

public class SetrikaService implements LaundryService{
    public boolean sekali = false;
    @Override
    public String doWork() {
        // TODO
        sekali = true;
        return "Sedang menyentrika...";
    }

    @Override
    public boolean isDone() {
        // TODO
        if(sekali) return true;
        return false;
    }

    @Override
    public long getHarga(int berat) {
        // TODO
        long total = berat*1000;
        return total;
    }

    @Override
    public String getServiceName() {
        return "Setrika";
    }
}
