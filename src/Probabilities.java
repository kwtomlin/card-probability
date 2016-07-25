/**
 * Created by kennytomlinson on 4/28/14.
 */
import java.util.*;
import java.util.Map;
import java.util.Stack;
import java.io.*;


public class Probabilities {

    public static void main(String [ ] args) throws InterruptedException{
        Integer Dealt = 0, Decks = 0, Size = 0, TotalPerCardType = 0, Remaining = 0, TenCount = 0;
        Float startNonTen, startTen;
        Queue recentCardQueue = new LinkedList();
        Map<String, Integer> recentQueueMap = new HashMap<String, Integer>();

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter # of Decks: ");
        Decks = scan.nextInt();
        System.out.println("Deck Count: " + Decks);
        Size = Decks * 52;
        System.out.println("Total Card Count:  " + Size);
        TotalPerCardType = Decks * 4;
        System.out.println("Total Per Type: " + TotalPerCardType);

        startNonTen = (TotalPerCardType/(float)Size) * 100;
        startTen = ((TotalPerCardType * 4)/(float)Size * 100);

        System.out.println("NonTen: \t" + startNonTen + "\tTen: \t" + startTen);

        // Remaining number per card
        Integer Ace = TotalPerCardType, King = TotalPerCardType, Queen = TotalPerCardType, Jack = TotalPerCardType, Ten = TotalPerCardType,
                Nine = TotalPerCardType, Eight = TotalPerCardType, Seven = TotalPerCardType, Six = TotalPerCardType, Five = TotalPerCardType,
                Four = TotalPerCardType, Three = TotalPerCardType, Two = TotalPerCardType
        ;

        String s = scan.nextLine().toLowerCase();

        while (!s.equalsIgnoreCase("exit")) {
            if (!s.equalsIgnoreCase("shuffle")){
                recentCardQueue.add(s);
            }
            if (s.equalsIgnoreCase("a")) {
                Ace--;
                Dealt++;
            } else if (s.equalsIgnoreCase("k")) {
                    if(King > 0){
                        King--;
                        Dealt++;
                    } else System.out.printf("You have been cheated in Kings\n");
            } else if (s.equalsIgnoreCase("q")) {
                if(Queen > 0){
                    Queen--;
                    Dealt++;
                } else System.out.printf("You have been cheated in Queens\n");
            } else if (s.equalsIgnoreCase("j")) {
                if(Jack > 0){
                    Jack--;
                    Dealt++;
                } else System.out.printf("You have been cheated in Jacks\n");
            } else if (s.equalsIgnoreCase("10")) {
                if(Ten > 0){
                    Ten--;
                    Dealt++;
                } else System.out.printf("You have been cheated in Tens\n");
            } else if (s.equalsIgnoreCase("9")) {
                if(Nine > 0){
                    Nine--;
                    Dealt++;
                } else System.out.printf("You have been cheated in Nines\n");
            } else if (s.equalsIgnoreCase("8")) {
                if(Eight > 0){
                    Eight--;
                    Dealt++;
                } else System.out.printf("You have been cheated in Eights\n");
            } else if (s.equalsIgnoreCase("7")) {
                if(Seven > 0){
                    Seven--;
                    Dealt++;
                } else System.out.printf("You have been cheated in Sevens\n");
            } else if (s.equalsIgnoreCase("6")) {
                if(Six > 0){
                    Six--;
                    Dealt++;
                } else System.out.printf("You have been cheated in Sixes\n");
            } else if (s.equalsIgnoreCase("5")) {
                if(Five > 0){
                    Five--;
                    Dealt++;
                } else System.out.printf("You have been cheated in Fives\n");
            } else if (s.equalsIgnoreCase("4")) {
                if(Four > 0){
                    Four--;
                    Dealt++;
                } else System.out.printf("You have been cheated in Fours\n");
            } else if (s.equalsIgnoreCase("3")) {
                if(Three > 0){
                    Three--;
                    Dealt++;
                } else System.out.printf("You have been cheated in Threes\n");
            } else if (s.equalsIgnoreCase("2")) {
                if(Two > 0){
                    Two--;
                    Dealt++;
                } else System.out.printf("You have been cheated in Twos\n");
            } else if (s.equalsIgnoreCase("shuffle")) {
                Dealt = 0;
                Ace = TotalPerCardType; King = TotalPerCardType; Queen = TotalPerCardType; Jack = TotalPerCardType; Ten = TotalPerCardType;
                Nine = TotalPerCardType; Eight = TotalPerCardType; Seven = TotalPerCardType; Six = TotalPerCardType; Five = TotalPerCardType;
                Four = TotalPerCardType; Three = TotalPerCardType; Two = TotalPerCardType;
                while(recentCardQueue.size() > 0){
                    recentCardQueue.remove();
                    System.out.println(recentCardQueue.size());
                }
            }
            System.out.printf("\n");
            Remaining = Size - Dealt;
            System.out.println("Size: "+ Size + ", Remaining: " + Remaining);

            recentQueueMap.clear();
            if (recentCardQueue.size() > 3){
                System.out.println(recentCardQueue.toString());
                if (recentCardQueue.size() > 24){
                    recentCardQueue.remove();
                }
                // Increase existing entries' count in HashMap
                for (Iterator i = recentCardQueue.iterator(); i.hasNext();) {
                    String key=i.next().toString();
                    if (recentQueueMap.containsKey(key)) {
                        int rr =Integer.valueOf(recentQueueMap.get(key));
                        rr++;
                        recentQueueMap.put(key, rr);
                    }else{
                        recentQueueMap.put(key, 1);
                    }
                }

                recentQueueMap = sortByValue(recentQueueMap);

                for (Map.Entry<String, Integer> entry : recentQueueMap.entrySet()){
                    System.out.printf("%s:\t %s %n", entry.getKey().toUpperCase(),
                            entry.getValue());
                }
            }




            // Remaining count of 10 value cards
            TenCount = King + Queen + Jack + Ten;

            Float threeOrLow = (Two/(float)Remaining * 100) + (Three/(float)Remaining * 100);
            Float threeOrHigh = 100 - (Two/(float)Remaining * 100);
            Float fourOrLow = threeOrLow + (Four/(float)Remaining * 100);
            Float fourOrHigh = 100 - threeOrLow;
            Float fiveOrLow = fourOrLow + (Five/(float)Remaining * 100);
            Float fiveOrHigh = 100 - fourOrLow;
            Float sixOrLow = fiveOrLow + (Six/(float)Remaining * 100);
            Float sixOrHigh = 100 - fiveOrLow;
            Float sevenOrLow = sixOrLow + (Seven/(float)Remaining * 100);
            Float sevenOrHigh = 100 - sixOrLow;
            Float eightOrLow = sevenOrLow + (Eight/(float)Remaining * 100);
            Float eightOrHigh = 100 - sevenOrLow;
            Float nineOrLow = eightOrLow + (Nine/(float)Remaining * 100);
            Float nineOrHigh = 100 - eightOrLow;

            System.out.printf("------------------------------\n");
            System.out.printf("Probabilities of Next Card:\n");
            System.out.printf("Card\tProb.\tStartDev\tProbEQorLess\tProbEQorGreat\n");

            System.out.printf("11 : \t %.2f\t%.2f\n", (Ace/(float)Remaining * 100), ((Ace/(float)Remaining * 100) - startNonTen));
            System.out.printf("10 : \t %.2f\t%.2f\n", (TenCount/(float)Remaining * 100), ((TenCount/(float)Remaining * 100) - startTen));
            System.out.printf("9 : \t %.2f\t%.2f\t\t\t%.2f\t\t\t\t%.2f\n", (Nine/(float)Remaining * 100), ((Nine/(float)Remaining * 100) - startNonTen),
                    nineOrLow, nineOrHigh);
            System.out.printf("8 : \t %.2f\t%.2f\t\t\t%.2f\t\t\t\t%.2f\n", (Eight/(float)Remaining * 100), ((Eight/(float)Remaining * 100) - startNonTen),
                    eightOrLow, eightOrHigh);
            System.out.printf("7 : \t %.2f\t%.2f\t\t\t%.2f\t\t\t\t%.2f\n", (Seven/(float)Remaining * 100), ((Seven/(float)Remaining * 100) - startNonTen),
                    sevenOrLow, sevenOrHigh);
            System.out.printf("6 : \t %.2f\t%.2f\t\t\t%.2f\t\t\t\t%.2f\n", (Six/(float)Remaining * 100), ((Six/(float)Remaining * 100) - startNonTen),
                    sixOrLow, sixOrHigh);
            System.out.printf("5 : \t %.2f\t%.2f\t\t\t%.2f\t\t\t\t%.2f\n", (Five/(float)Remaining * 100), ((Five/(float)Remaining * 100) - startNonTen),
                    fiveOrLow, fiveOrHigh);
            System.out.printf("4 : \t %.2f\t%.2f\t\t\t%.2f\t\t\t\t%.2f\n", (Four/(float)Remaining * 100), ((Four/(float)Remaining * 100) - startNonTen),
                    fourOrLow, fourOrHigh);
            System.out.printf("3 : \t %.2f\t%.2f\t\t\t%.2f\t\t\t\t%.2f\n", (Three/(float)Remaining * 100), ((Three/(float)Remaining * 100) - startNonTen),
                    threeOrLow, threeOrHigh);
            System.out.printf("2 : \t %.2f\t%.2f\n", (Two/(float)Remaining * 100), ((Two/(float)Remaining * 100) - startNonTen));
            System.out.printf("------------------------------\n");

            System.out.println("Enter Card: ");
            s = scan.nextLine().toLowerCase();
            System.out.printf("\n\n\n");
        }
    }

    public static <K, V extends Comparable<? super V>> Map<K, V>
    sortByValue( Map<K, V> map )
    {
        List<Map.Entry<K, V>> list =
                new LinkedList<Map.Entry<K, V>>( map.entrySet() );
        Collections.sort( list, new Comparator<Map.Entry<K, V>>()
        {
            public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 )
            {
                return (o1.getValue()).compareTo( o2.getValue() );
            }
        } );

        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list)
        {
            result.put( entry.getKey(), entry.getValue() );
        }
        return result;
    }

}
