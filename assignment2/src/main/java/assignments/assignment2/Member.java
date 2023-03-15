package assignments.assignment2;

import assignments.assignment1.NotaGenerator;

public class Member {

    private String nama;
    private String noHp;
    private int bonusCounter = 0;
    public Member(String nama, String noHp, int bonusCounter) {
        
        this.nama = nama;
        this.noHp = noHp;
        this.bonusCounter = bonusCounter;

    }

   

    public String getId(){
        String id = generateId(getNama(), getnoHp());
        return id;
    
    }
    public String getNama(){
        return this.nama;
    }

    public String getnoHp(){
        return this.noHp;
    }
    public void tambahBonusCounter(){
        if(this.bonusCounter >= 3){
            resetBonusCounter();
        }
        else
        {
            this.bonusCounter+=1;
        }
       
        
    }
    public void resetBonusCounter(){
        this.bonusCounter = 1;
    }
    public int getBonusCounter(){
        return this.bonusCounter;
    }

    public static String generateId(String nama, String nomorHP){
        
        
        String arr[] = nama.split(" ",2);
        String firstword = arr[0];
        firstword = firstword.toUpperCase(); //ke Upperacse
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
