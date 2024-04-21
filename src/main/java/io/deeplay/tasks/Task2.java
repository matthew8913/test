package io.deeplay.tasks;

import java.util.*;

public class Task2{
    /**
     * Метод решения.
     */
    public static void solve(){
         System.out.println("Задача #2:\nВведите размер массива:");
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        Map<Integer,Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        //Для подсчёта используем мапу
        for(int i = 0; i<n;i++){
            int num = (int) (Math.random() * (n+1));
            list.add(num);
            if(map.containsKey(num)){
                map.put(num, map.get(num)+1);
            }else{
                map.put(num, 1);
            }
        }
        System.out.println(list.stream().sorted().toList());
        List<Integer> result = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        //Формируем результирующий список
        for(Map.Entry<Integer, Integer> e : map.entrySet()){
            if(e.getValue()>max){
                max = e.getValue();
                result.clear();
                result.add(e.getKey());
            } else if (e.getValue()==max) {
                result.add(e.getKey());
            }
        }
        System.out.println(result+"\n");
    }
}
