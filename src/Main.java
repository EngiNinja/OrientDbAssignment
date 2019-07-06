import java.util.*;

public class Main {

    public static void main(String[] args) {
        ArrayList<ArrayList<String>> data = createData();

        ArrayList<Iterator<String>> input = new ArrayList();
        input.add(data.get(0).iterator());
        input.add(data.get(1).iterator());
        input.add(data.get(2).iterator());
        input.add(data.get(3).iterator());
        input.add(data.get(4).iterator());

        List<Iterator<String>> output = solve(input);
        System.out.println("result : " + '\n');
        printIter(output.get(0));
    }

    public static void printIter(Iterator<String> input) {
        while (input.hasNext()) {
            System.out.println(input.next());
        }
        System.out.println();
    }


    // is executed ⌊lg(M)⌋ times
    // 6 times for M = 110
    // 3 times for M = 11
    // 1 times for M = 1
    public static List<Iterator<String>> solve(List<Iterator<String>> input) {
        int size = input.size();
        if (size == 1) {
            return input;
        } else if (size == 2) {
            ArrayList<Iterator<String>> output = new ArrayList();
            output.add(mergeTwoIteratosIntoOne(input.get(0), input.get(1)));
            return output;
        } else {
            List<Iterator<String>> firstHalf = solve(input.subList(0, size / 2));
            List<Iterator<String>> secondHalf = solve(input.subList(size / 2, size));
            ArrayList<Iterator<String>> output = new ArrayList();
            output.add(mergeTwoIteratosIntoOne(firstHalf.get(0), secondHalf.get(0)));
            return output;
        }
    }

    // O(2*M*N) => O(M*N)
    public static Iterator<String> mergeTwoIteratosIntoOne(Iterator<String> iterator1,
        Iterator<String> iterator2) {
        ArrayList<String> output = new ArrayList();
        String copy = null;
        int copyFrom = -1;
        HashSet<String> added = new HashSet();
        while (iterator1.hasNext() || iterator2.hasNext()) {
            String string1 = null;
            String string2 = null;

            // initialize string1 and string2
            if (copyFrom != 1 && iterator1.hasNext()) {
                string1 = iterator1.next();
            } else {
                string1 = copy;
            }

            if (copyFrom != 2 && iterator2.hasNext()) {
                string2 = iterator2.next();
            } else {
                string2 = copy;
            }

            // Let's assume I have implemented a compareTo method, that compares two strings
            // not completely, but char by char and returns the integer as soon as it's clear,
            // that one string should come earlier, than another. Output (-1 or 0 or 1). Complexity
            // would be O(K)
            int result = string1.compareTo(string2);

            if (result < 0) {
                insertString(added, output, string1);
                copy = string2;
                copyFrom = 2;
            } else if (result == 0) {
                if (!added.contains(string1)) {
                    insertString(added, output, string1);
                }
                copyFrom = -1;
            } else {
                insertString(added, output, string2);
                copy = string1;
                copyFrom = 1;
            }
        }
        output.add(copy);
        return output.iterator();
    }


    public static void insertString(HashSet added, ArrayList output, String string) {
        if (!added.contains(string)) {
            output.add(string);
            added.add(string);
        }
    }

    public static ArrayList<ArrayList<String>> createData() {
        ArrayList<String> a1 = new ArrayList();
        ArrayList<String> a2 = new ArrayList();
        ArrayList<String> a3 = new ArrayList();
        ArrayList<String> a4 = new ArrayList();
        ArrayList<String> a5 = new ArrayList();

        a1.add("vayvmhq");
        a1.add("ngtaaoix");
        a1.add("vrnatdggol");
        a1.add("efcrsipdb");

        a2.add("iglyauowfz");
        a2.add("evqcwehl");
        a2.add("wvujqghne");
        a2.add("cljfcaba");

        a3.add("ztgxgkvqrj");
        a3.add("yartqqdm");
        a3.add("klkwxoohgr");
        a3.add("balxfik");

        a4.add("camasbjhb");
        a4.add("cxdtafze");
        a4.add("qhmxjs");
        a4.add("gbcmiepdd");

        a5.add("qfckbyvtx");
        a5.add("bfxioimm");
        a5.add("whdlvouut");
        a5.add("wnruywv");

        Collections.sort(a1);
        Collections.sort(a2);
        Collections.sort(a3);
        Collections.sort(a4);
        Collections.sort(a5);

        ArrayList<ArrayList<String>> output = new ArrayList();
        output.add(a1);
        output.add(a2);
        output.add(a3);
        output.add(a4);
        output.add(a5);

        return output;
    }


}
