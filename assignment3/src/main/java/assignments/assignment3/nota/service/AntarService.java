package assignments.assignment3.nota.service;

import assignments.assignment3.nota.Nota;

public class AntarService implements LaundryService{
    public boolean sekali = false; //jika false
    @Override
    public String doWork() {
        sekali = true; //lgsg true setelah do work
        return "Sedang mengantar...";
    }

    @Override
    public boolean isDone() {
    
        if(sekali) return true; //jika sdh sls
        return false;
    }

    @Override
    public long getHarga(int berat) {
    
        long total = 0;
        if(berat>4){ //diatas 4 kali 500 tiap berat
            total= berat *500 ;

        }
        else
        {
            total = 2000; //set minimal 2000
        }
        return total;
    }

    @Override
    public String getServiceName() {
        return "Antar";
    }
}
