package com.work;

/**
 * Created by 28029 on 2017/11/29.
 */
public class RabitQuestion {
    public static void main(String[] args) {
        boolean []caves = new boolean[10];
        int index = 0;
        for(int i = 1;  i <= 1000; i++)
        {
            index +=i;
            index = index%10;
            caves[index] = true;
        }
        for(int i = 0; i < 10; i++){
            if(!caves[i])
                System.out.println(i);
        }

    }
}
