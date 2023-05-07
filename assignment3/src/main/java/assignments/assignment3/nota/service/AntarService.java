package assignments.assignment3.nota.service;

import assignments.assignment3.nota.Nota;

public class AntarService implements LaundryService{
    public boolean sekali = false;
    @Override
    public String doWork() {
        sekali = true;
        return "Sedang mengantar...";
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
        long total = 0;
        if(berat>4){
            total= berat *500 ;

        }
        else
        {
            total = 2000;
        }
        return total;
    }

    @Override
    public String getServiceName() {
        return "Antar";
    }
}
