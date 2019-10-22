package com.ifmo.lesson2;

public class  LuckyTickets {
    /*
    В городе N проезд в трамвае осуществляется по бумажным отрывным билетам. Каждую
    неделю трамвайное депо заказывает в местной типографии рулон билетов с номерами от
    000001 до 999999. «Счастливым» считается билетик у которого сумма первых трёх цифр
    номера равна сумме последних трёх цифр, как, например, в билетах с номерами 003102 или
    567576. Трамвайное депо решило подарить сувенир обладателю каждого счастливого билета
    и теперь раздумывает, как много сувениров потребуется. С помощью программы подсчитайте
    сколько счастливых билетов в одном рулоне?
     */
    public static void main(String[] args) {
        System.out.println(luckyTickets());
    }

    public static int luckyTickets() {
        int count = 0;
        int leftPart;
        int rightPart;
        for (int i = 1001; i<=999999; i++){
            leftPart=i/1000;
            rightPart=i%1000;
            if (getSumm(leftPart)==getSumm(rightPart)){
                count++;
            }
        }
        return count;
    }
    private static int getSumm(int nubmer){
        return nubmer/100 + nubmer/10%10 + nubmer%10;
    }
}
