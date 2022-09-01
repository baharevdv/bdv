package com.company.its.entity;

import org.jsoup.Connection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MagicSquareTask {

    public static String calculate(String input){
        String output = "";

        int countMatrix =0;
        int minCost =1000;
        int cost = 0;
        int [][] outMatrix = new int[3][3];

        String[] inputLineArr = input.split("\n",3);
        List<String> inputList = new ArrayList<>(9);
        for(int i=0; i<3; i++){
            Collections.addAll(inputList,inputLineArr[i].split(" "));
        }
        List<Integer> orderedList;
        orderedList = new ArrayList<Integer>();
        inputList.stream().forEachOrdered(v -> {
            Integer i = Integer.valueOf(v);
            orderedList.add(i);
        });
        Collections.sort(orderedList);
        int [][] sortedMatrix = new int[3][3];
        for (int i = 0;i<3;i++){
            for (int j =0; j<3; j++) {
                sortedMatrix[i][j]=orderedList.get(i*3+j);
            }
        }
        int [][] workMatrix = new int[3][3];
        for(int i5 =0;i5<3;i5++){
            for(int j5 =0;j5<3;j5++){
                if(i5!=2&j5!=2){
                    workMatrix[i5][j5] = orderedList.get(4);
                    for(int i9=0;i9<3;i9++){
                        for(int j9=0;j9<3;j9++){
                            if(i9==i5 & j9!=j5){
                                workMatrix[i5][j9] = orderedList.get(8);
                                for(int j1=0;j1<3;j1++){
                                    if(j1!=j5 & j1!=j9) {
                                        workMatrix[i5][j1] = orderedList.get(0);
                                        for(int i7=0;i7<3;i7++){
                                            if(i7!=i5){
                                                workMatrix[i7][j5] = orderedList.get(6);
                                                for(int i3=0;i3<3;i3++) {
                                                    if(i3!=i5 & i3!= i7) {
                                                        workMatrix[i3][j5] = orderedList.get(2);
                                                        workMatrix[i7][j9] = orderedList.get(1);
                                                        workMatrix[i7][j1] = orderedList.get(5);
                                                        workMatrix[i3][j9] = orderedList.get(3);
                                                        workMatrix[i3][j1] = orderedList.get(7);
                                                        cost = checkCost(workMatrix, inputList);
                                                        if (minCost > cost ) {
                                                            minCost = cost;
                                                            outMatrix = workMatrix;
                                                        }
                                                        countMatrix++;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if(j9==j5 & i9!=i5){
                                workMatrix[i9][j9] = orderedList.get(8);
                                for(int i1=0;i1<3;i1++){
                                    if(i1!=i5 & i1!=i9) {
                                        workMatrix[i1][j5] = orderedList.get(0);
                                        for(int j7=0;j7<3;j7++){
                                            if(j7!=j5){
                                                workMatrix[i5][j7] = orderedList.get(6);
                                                for(int j3=0;j3<3;j3++) {
                                                    if(j3!=j5 & j3!= j7) {
                                                        workMatrix[i5][j3] = orderedList.get(2);
                                                        workMatrix[i9][j7] = orderedList.get(1);
                                                        workMatrix[i1][j7] = orderedList.get(5);
                                                        workMatrix[i9][j3] = orderedList.get(3);
                                                        workMatrix[i1][j3] = orderedList.get(7);
                                                        cost = checkCost(workMatrix, inputList);
                                                        if (minCost > cost ) {
                                                            minCost = cost;
                                                            outMatrix = workMatrix;
                                                        }
                                                        countMatrix++;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }//for i9
                }//if(i5!=2&j5!=2)
            }//for j5
        }//for i5

        for(int i=0; i<3;i++) {
            for (int j =0; j<3;j++){
                output = output.concat(outMatrix[i][j] + " ");
            }
            output = output.concat("\n");
        }
        output = output.concat("\n" + "Cтоимость равна " + minCost);

        output = output.concat("\n" + "Проверено матриц " + countMatrix);


        return output;

    }
    private static int checkCost(int[][] workMatrix,List<String> inputList) {
        String mes = "";
        int cost = 0;
        for(int i=0; i<3;i++) {
            for (int j =0; j<3;j++){
                //mes = mes.concat(workMatrix[i][j] + " ");
                cost = cost + Math.abs(Integer.valueOf(inputList.get(i*3+j))-workMatrix[i][j]);
                }
                //if (i<2) mes = mes.concat("\n");
            }

            return cost;
        }
}
