package com.leetcode;

/**
 * Created by 28029 on 2017/11/26.
 */
public class c717 {
    public static boolean isOneBitCharacter(int[] bits) {
        if(bits.length == 1)
            return true;
        if(bits.length == 2&& 1== bits[0])
            return false;
        int index = 0;
        int last_2 = bits.length - 2;
        int last = bits.length-1;
            while(index < last){
                if(1 == bits[index])
                    index+=2;
                else
                    index+=1;
                if(index == last_2)
                {
                    if(1 == bits[index])
                        return false;
                    else
                        return true;
                }

            }
        return true;
    }
    public static void main(String[] args) {
        int []bits = new int[]{1,0};
        System.out.println(c717.isOneBitCharacter(bits));
    }
}
