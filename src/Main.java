import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        int n = 0;
        for (int i=0;i<300_000;i++) {
            n += 1 * (i+1);
            System.out.println(n);
        }
        System.out.println(n);

    }
}