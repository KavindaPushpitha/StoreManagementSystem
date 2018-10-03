package sample;

import java.util.Scanner;


public class NIC_Details {

        String id;
        int month[] = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        public  void setNic(String nic) {
            id = nic;
        }

        public int getYear() {
            return (1900 + Integer.parseInt(id.substring(0, 2)));

        }

        public int getDays() {
            int d = Integer.parseInt(id.substring(2, 5));
            if (d > 500) {
                return (d - 500);
            } else {
                return d;
            }
        }

        public String setMonth() {
            int mo = 0, da = 0;
            int days = getDays();

            for (int i = 0; i < month.length; i++) {
                if (days < month[i]) {
                    mo = i + 1;
                    da = days;
                    break;
                } else {
                    days = days - month[i];
                }
            }
            if(Integer.toString(mo).length()>1){
                return getYear()+"-"+mo+"-"+da;
            }else {
                return getYear()+"-0"+mo+"-"+da;
            }


        }



    }

