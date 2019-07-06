import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<Iterator<String>> output = solve(createData());
        System.out.println("result : " + '\n');
        printIter(output.get(0));
    }

    /**
     * This method merges multiple Iterators into one.
     * This method is executed ⌊lg(M)⌋ times
     * 6 times for M = 110
     * 3 times for M = 11
     * 1 times for M = 1
     *
     * @param input arrayList of the Iterators.
     * @return a list that contains one iterator. Reason for using a List st is to be able to apply recursion
     */
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

    /**
     * This method is takes two Iterators and merges them into one Iterator
     * The complexity is O(2*M*N) => O(M*N)
     * @param iterator1 First iterator
     * @param iterator2 Second iterator
     * @return Result of the merging
     */
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

    /**
     * Inserts a String into output if added does not contain it.
     * @param added
     * @param output
     * @param string
     */
    public static void insertString(HashSet added, ArrayList output, String string) {
        if (!added.contains(string)) {
            output.add(string);
            added.add(string);
        }
    }

    /**
     * This method creates data. It is hardcoded.
     * @return an array list of Iterator over sorted strings
     */
    public static  ArrayList<Iterator<String>>  createData() {
        ArrayList<String> arrayList1 = new ArrayList();
        ArrayList<String> arrayList2 = new ArrayList();
        ArrayList<String> arrayList3 = new ArrayList();
        ArrayList<String> arrayList4 = new ArrayList();
        ArrayList<String> arrayList5 = new ArrayList();

        arrayList1.add("vayvmhq");
        arrayList1.add("ngtaaoix");
        arrayList1.add("vrnatdggol");
        arrayList1.add("efcrsipdb");

        arrayList2.add("iglyauowfz");
        arrayList2.add("evqcwehl");
        arrayList2.add("wvujqghne");
        arrayList2.add("cljfcaba");

        arrayList3.add("ztgxgkvqrj");
        arrayList3.add("yartqqdm");
        arrayList3.add("klkwxoohgr");
        arrayList3.add("balxfik");

        arrayList4.add("camasbjhb");
        arrayList4.add("cxdtafze");
        arrayList4.add("qhmxjs");
        arrayList4.add("gbcmiepdd");

        arrayList5.add("qfckbyvtx");
        arrayList5.add("bfxioimm");
        arrayList5.add("whdlvouut");
        arrayList5.add("wnruywv");

        Collections.sort(arrayList1);
        Collections.sort(arrayList2);
        Collections.sort(arrayList3);
        Collections.sort(arrayList4);
        Collections.sort(arrayList5);

        ArrayList<Iterator<String>> output = new ArrayList();
        output.add(arrayList1.iterator());
        output.add(arrayList2.iterator());
        output.add(arrayList3.iterator());
        output.add(arrayList4.iterator());
        output.add(arrayList5.iterator());

        return output;
    }

    /**
     * Prints the data Iterator iterates over.
     * @param input iterator
     */
    public static void printIter(Iterator<String> input) {
        while (input.hasNext()) {
            System.out.println(input.next());
        }
        System.out.println();
    }
}
