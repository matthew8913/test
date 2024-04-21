package io.deeplay.tasks;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Task3 {
    /**
     * Метод ввода пользовательской последовательности для игрока.
     * @return Массив последовательности.
     */
    public static int[] scanSequence() {
        Scanner sc = new Scanner(System.in);
        int[] array = new int[3];

        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.println("Введите 3 числа от 1 до 6 в формате 1 2 3:");
                for (int i = 0; i < 3; i++) {
                    int num = sc.nextInt();
                    if (num >= 1 && num <= 6) {
                        array[i] = num;
                    } else {
                        throw new InputMismatchException();
                    }
                }
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Неправильный формат ввода! Повторите попытку.");
                sc.nextLine();
            }
        }

        return array;
    }

    /**
     * Метод сражения. Подсчитывает, сколько раз не пересекаясь встречаются последовательности игроков.
     * @param seq1 Последовательность первого игрока.
     * @param seq2 Последовательность второго игрока.
     * @param arr Массив с выпавшими числами.
     * @return 0, если ничья; 1, если выиграл первый; 2, если выиграл второй.
     */
    static int battle(int[] seq1, int[] seq2, int[] arr){
        int count1 = 0;
        int count2 = 0;
        int seq1Index = 0;
        int seq2Index = 0;
        for (int num : arr) {
            if (num == seq1[seq1Index]) {
                seq1Index++;
                if (seq1Index == seq1.length) {
                    count1++;
                    seq1Index=0;
                }
            } else {
                seq1Index = 0;
            }
            if (num == seq2[seq2Index]) {
                seq2Index++;
                if (seq2Index == seq2.length) {
                    count2++;
                    seq2Index=0;
                }
            } else {
                seq2Index = 0;
            }
        }
        if(count1 == count2){
            return 0;
        }
        if(count1>count2){
            return 1;
        }else{
            return 2;
        }
    }

    /**
     * Метод генерации массива, заполненного рандомными числами 1-6.
     * @param n Размер массива.
     * @return Массив.
     */
    public static int[] generateArray(int n){
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int)(Math.random()*6+1);
        }
        return arr;
    }

    /**
     * Метод решения.
     */
    public static void solve(){
        System.out.println("Задача #3:\nПоследовательность первого игрока:");
        int[] seq1 = scanSequence();
        System.out.println("Последовательность второго игрока:");
        int[] seq2 = scanSequence();
        System.out.println("Введите количество подбрасываний: ");
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        System.out.println("Введите количество экспериментов: ");
        int experimentsNum = s.nextInt();
        int firstWin=0, secondWin=0, draw=0;
        for(int i = 0; i<experimentsNum;i++){
            int result = battle(seq1,seq2,generateArray(n));
            switch(result){
                case 0->draw++;
                case 1->firstWin++;
                case 2->secondWin++;
            }
        }
        System.out.println("Результаты исследования: \n");
        System.out.println("При последоватльностях:\n" +
                "seq1 = "+ Arrays.toString(seq1) + "\nseq2 = "+ Arrays.toString(seq2));
        System.out.println("Игрок 1 выигрывает с вероятностью: " + (double)firstWin/experimentsNum);
        System.out.println("Игрок 2 выигрывает с вероятностью: " + (double)secondWin/experimentsNum);
        System.out.println("Ничья наступает с вероятностью " + (double)draw/experimentsNum +"\n");
    }
}
