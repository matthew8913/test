package io.deeplay.tasks;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Task1 {
    /**
     * Метод решения.
     */
    public static void solve(){
        System.out.println("Задача #1:\nВведите размер массива: ");
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        List<Integer> array = new ArrayList<>();
        List<Integer> even = new ArrayList<>();
        List<Integer> odd = new ArrayList<>();
        int zeroCount = 0;
        for(int i = 0; i<n;i++){
            int num = (int) (Math.random() * (n + 1));
            array.add(num);
            if (num == 0) {
                zeroCount++;
            } else if (num % 2 == 0) {
                even.add(num);
            } else {
                odd.add(num);
            }
        }
        System.out.println("Исходный массив:");
        System.out.println(array);

        //Сортировка нечетных
        odd.sort(Comparator.naturalOrder());
        //Сортировка четных
        even.sort(Comparator.reverseOrder());
        List<Integer> result = new ArrayList<>(odd);
        //Добавление нулей
        for (int i = 0; i < zeroCount; i++) {
            result.add(0);
        }
        result.addAll(even);

        System.out.println("Отсортированный массив:");
        System.out.println(result+"\n");
    }
}
