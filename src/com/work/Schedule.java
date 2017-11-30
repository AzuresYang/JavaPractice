package com.work;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 28029 on 2017/11/29.
 */
public class Schedule {
    private static class Player{
        public String name;
        Player(String name){
            this.name = name;
        }
    }
    List<Player> players;
    int playersNum;
    int [][]shedule;
    Schedule(int num){
        playersNum = num;
        players = new ArrayList<>();
        int power = 0;
        int number = 1;
        while(number <num)
        {
            number *=2;
            power++;
        }

        for(int i = 0; i < num; i++){
            String name = ""+(i+1);
            players.add(new Player(name));
        }
        for(int i = num; i < number;i++){
            players.add(new Player("*"));
        }
        shedule = new int[number][];
        for(int i = 0; i <number; i++){
            shedule[i] = new int[number];
        }
    }
    public void print(){
        arrangeSchedule(shedule,players.size());
        for(int i = 0; i < playersNum; i++)
        {
            for(int j = 0; j <playersNum; j++)
            {
                System.out.print("  "+players.get(shedule[i][j]-1).name);
            }
            System.out.println("");
        }
    }
    public static void arrangeSchedule(int[][]table ,int n){
        if(n==1)
        {
            table[0][0] = 1;
            return;
        }
        int m = n/2;
        arrangeSchedule(table,m);

        //右上角区域
        for(int i = 0; i < m; i++)
        {
            for(int j = m; j < n; j++)
                table[i][j] = table[i][j-m]+m;
        }

        for (int i = m; i < n; i++) {
            for (int j = 0; j < m; j++) {
                table[i][j] = table[i - m][j] + m;
            }
        }
        // 右下区域
        for (int i = m; i < n; i++) {
            for (int j = m; j < n; j++) {
                table[i][j] = table[i - m][j - m];
            }
        }

    }
    public static void main(String[] args) {
        int size = 4;
        int[][] table = new int[size][size];
        Schedule.arrangeSchedule(table, size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        Schedule sc = new Schedule(size);
        sc.print();
    }
}
