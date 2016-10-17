package net.macdidi.bmi;

import java.math.BigDecimal;

/**
 * Created by jiahunghsu on 2016/8/25.
 */
public class bmiCalculate {
    protected BigDecimal transHeight(String height, String kind){
        BigDecimal rtnHeight = new BigDecimal(height);
        if("0".equals(kind)){
            rtnHeight = rtnHeight.divide(new BigDecimal(100));
        }
        return rtnHeight;
    }

    protected BigDecimal transWeight(String weight, String kind){
        BigDecimal rtnWeight = new BigDecimal(weight);
        if("1".equals(kind)){
            rtnWeight = (rtnWeight.multiply(new BigDecimal(0.454))).setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        return rtnWeight;
    }
}
