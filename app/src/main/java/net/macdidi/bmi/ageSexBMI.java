package net.macdidi.bmi;

import android.content.Context;

/**
 * Created by jiahunghsu on 2016/10/17.
 */

public class ageSexBMI {
    private Context context;

    public ageSexBMI(Context context){
        this.context = context;
    }


    protected String getBMIRlt(int age, String sex, double bmi){
        String result = "";
        if(age >= 18){
            result = weightSt(bmi);
        }else{
            result = weightSt(age, sex, bmi);
        }
        return result;
    }
    private String weightSt(double bmi){
        String status = "";
        status = getBMISt(bmi, 18.5, 24, 27);
        return status;
    }
    private String weightSt(int age, String sex, double bmi){
        String status = "";
        switch (age){
            case 0:
                status = bmiAdjust(sex, bmi, 11.5, 14.8, 15.8, 11.5, 14.7, 15.5);
                break;
            case 1:
                status = bmiAdjust(sex, bmi, 14.8, 18.3, 19.2, 14.2, 17.9, 19);
                break;
            case 2:
                status = bmiAdjust(sex, bmi, 14.2, 17.4, 18.3, 13.7, 17.2, 18.1);
                break;
            case 3:
                status = bmiAdjust(sex, bmi, 13.7, 17, 17.8, 13.5, 16.9, 17.8);
                break;
            case 4:
                status = bmiAdjust(sex, bmi, 13.4, 16.7, 17.6, 13.2, 16.8, 17.9);
                break;
            case 5:
                status = bmiAdjust(sex, bmi, 13.3, 16.7, 17.7, 13.1, 17, 18.1);
                break;
            case 6:
                status = bmiAdjust(sex, bmi, 13.5, 16.9, 18.5, 13.1, 17.2, 18.8);
                break;
            case 7:
                status = bmiAdjust(sex, bmi, 13.8, 17.9, 10.3, 13.4, 17.7, 19.6);
                break;
            case 8:
                status = bmiAdjust(sex, bmi, 14.1, 19, 21.6, 13.8, 18.4, 20.7);
                break;
            case 9:
                status = bmiAdjust(sex, bmi, 14.3, 19.5, 22.3, 14, 19.1, 21.3);
                break;
            case 10:
                status = bmiAdjust(sex, bmi, 14.5, 20, 22.7, 14.3, 19.7, 22);
                break;
            case 11:
                status = bmiAdjust(sex, bmi, 14.8, 20.7, 23.2, 14.7, 20.5, 22.7);
                break;
            case 12:
                status = bmiAdjust(sex, bmi, 15.2, 21.3, 23.9, 15.2, 21.3, 23.5);
                break;
            case 13:
                status = bmiAdjust(sex, bmi, 15.7, 21.9, 24.5, 15.7, 21.9, 24.3);
                break;
            case 14:
                status = bmiAdjust(sex, bmi, 16.3, 22.5, 25, 16.3, 22.5, 24.9);
                break;
            case 15:
                status = bmiAdjust(sex, bmi, 16.9, 22.9, 25.4, 16.7, 22.7, 25.2);
                break;
            case 16:
                status = bmiAdjust(sex, bmi, 17.4, 23.3, 25.6, 17.1, 22.7, 25.3);
                break;
            case 17:
                status = bmiAdjust(sex, bmi, 17.8, 23.5, 25.6, 17.3, 22.7, 25.3);
                break;
        }
        return status;
    }

    private String bmiAdjust(String sex, double bmi, double maleLowest, double maleMiddle, double maleHightest, double femaleLowest, double femaleMiddle, double femaleHightest){
        String status = "";
        if(sex.equals("Male")){
            status = getBMISt(bmi, maleLowest, maleMiddle, maleHightest);
        }else{
            status = getBMISt(bmi, femaleLowest, femaleMiddle, femaleHightest);
        }

        return status;
    }

    private String getBMISt(double bmi, double lowest, double middle, double hightest){
        String status = "";
        if(bmi < lowest){
            status = context.getResources().getString(R.string.underWt);
        }else if(bmi >= lowest && bmi < middle){
            status = context.getResources().getString(R.string.nomalWt);
        }else if(bmi >= middle && bmi < hightest){
            status = context.getResources().getString(R.string.overWt);
        }else{
            status = context.getResources().getString(R.string.fat);
        }
        return status;
    }
}
