package com.example.mg_1;

public class stack {
    public int[][] m=new int[1000][2];
    public int k=0;
    public stack(){

    }
    public void push(int i,int j){
        m[k][0]=i;
        m[k][1]=j;
        k++;
    }
    public void pop(int n){
        for(int p=n;p<k-1;p++){
            m[p][0]=m[p+1][0];
            m[p][1]=m[p+1][1];
        }
        k--;
    }


}
