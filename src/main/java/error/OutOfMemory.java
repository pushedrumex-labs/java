package error;

import java.util.ArrayList;

public class OutOfMemory {

    public static void overArraySize() {
        int[] array = new int[Integer.MAX_VALUE];
    }

    public static void overHeapSize() {
        ArrayList<String> list = new ArrayList();

        while (true) {
            list.add(new String());
        }
    }

}
