package io.deeplay.tasks;

import java.util.*;
import java.util.stream.Collectors;

public class Task4 {

    /**
     * Метод, который ищет последовательность натуральных чисел, которые в сумме дают число x.
     * @param x Число, представляющее сумму последовательности.
     * @param k Количество элементов последовательности.
     * @return Список интов. Если последовательности нет - null.
     */
    public static List<Integer> sequence(int x, int k) {
        // Начинаем с наименьшего возможного числ
        int start = 1;

        // Пока не найдем подходящую последовательность
        while (true) {
            // Сумма арифметической прогрессии
            int total = k * (2 * start + k - 1) / 2;

            // Если сумма равна x, то мы нашли последовательность
            if (total == x) {
                List<Integer> sequence = new ArrayList<>();
                for (int i = 0; i < k; i++) {
                    sequence.add(start + i);
                }
                return sequence;
            }

            // Если сумма больше x, то такой последовательности не существует
            else if (total > x) {
                return null;
            }

            // Переходим к следующему числу
            start++;
        }
    }

    /**
     * Рекурсивный метод, возвращающий первую попавшуюся группировку чисел из sumsList на слагаемые из numsList.
     * @param numsList Список "слагаемых".
     * @param sumsList Список чисел, которые мы хотим представить в виде слагаемых.
     * @param result Результирующий список, формирующийся в процессе рекурсии.
     * @return Список списков чисел, на которые можно разбить числа из sumsList.
     */
    public static List<List<Integer>> generateSumsCombinations(List<Integer> numsList, List<Integer> sumsList, List<List<Integer>> result) {
        if(numsList.isEmpty()&&sumsList.isEmpty()){
            return result;
        }
        List<List<Integer>> combinationsList = generateCombinations(numsList, 0,sumsList.get(0),new ArrayList<>());
        if(combinationsList.isEmpty()){
            return null;
        }
        for(List<Integer> comb:combinationsList){
            List<Integer> numsCopy = new ArrayList<>(List.copyOf(numsList));
            comb.forEach(numsCopy::remove);
            List<Integer> sumsCopy = new ArrayList<>(List.copyOf(sumsList));
            sumsCopy.remove(0);
            result.add(comb);
            List<List<Integer>> list = generateSumsCombinations(numsCopy,sumsCopy,result);
            if(list != null){
                return list;
            }else{
                result.remove(result.size()-1);
            }
        }
        return null;
    }

    /**
     * Рекурсивный метод, генерирующий все возможные сочетания чисел из numsList, которые в сумме будут давать result.
     * @param numsList Список чисел.
     * @param numsInd Индекс числа, с которым мы работаем.
     * @param result Число, разбиение на слагаемые которого мы ищем.
     * @param current Текущий список "комбинации".
     * @return Список списков слагаемых, из которых можно собрать result.
     */
    private static List<List<Integer>> generateCombinations(List<Integer> numsList, int numsInd, int result, List<Integer> current) {
        List<List<Integer>> combinations = new ArrayList<>();
        if (result == 0) {
            combinations.add(new ArrayList<>(current));
            return combinations;
        }
        if (result < 0 || numsInd == numsList.size()) {
            return combinations;
        }
        current.add(numsList.get(numsInd));
        combinations.addAll(generateCombinations(numsList,numsInd+1, result - numsList.get(numsInd), current ));
        current.remove(current.size() - 1);
        combinations.addAll(generateCombinations(numsList, numsInd + 1,result, current));
        return combinations;
    }

    /**
     * Метод, формирующий массив из строки чисел через пробел.
     * @param input Входная строка.
     * @return Массив чисел.
     */
    public static List<Integer> stringToArray(String input) {
        return Arrays.stream(input.split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    /**
     * Метод вывода ответа
     */
    public static void print(List<List<Integer>> list){
        StringBuffer sb = new StringBuffer();
        for(int j = 0; j < list.size(); j++){
            List<Integer> l = list.get(j);
            sb.append("[");
            for(int i = 0; i < l.size(); i++){
                sb.append(l.get(i));
                if(i != l.size() - 1){
                    sb.append(",");
                }
            }
            sb.append("]");
            sb.append(l.stream().reduce(Integer::sum).orElse(0));
            if(j != list.size() - 1){
                sb.append(",");
            }
        }
        System.out.println(sb);
    }
    /**
     * Метод решения.
     */
    public static void solve(){
        System.out.println("Задача #4");
        System.out.println("Введите элементы массива через пробел: ");
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println("Введите число групп: ");
        int k = sc.nextInt();
        List<Integer> numsList = stringToArray(s);
        List<Integer> sumsList = sequence(numsList.stream().reduce(0, Integer::sum), k);
        if(sumsList == null){
            System.out.println("Невозможно разгруппировать массив!\n");
            return;
        }
        List<List<Integer>> groupsList = generateSumsCombinations(numsList, sumsList,new ArrayList<>());
        if(groupsList == null){
            System.out.println("Невозможно разгруппировать массив!\n");
        }else{
            print(groupsList);
        }
    }
}
