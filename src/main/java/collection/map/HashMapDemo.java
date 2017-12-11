package collection.map;

import java.util.HashMap;

/**
 * Created by ShannonAJ on 2017/11/29.
 */
public class HashMapDemo {

    public static void main(String[] args) {
        Object o = new Object();
        o.hashCode();
        o.equals(null);

        String a = "abc";
        a.equals("abc");

        HashMap map = new HashMap();
        map.put("1", "a");
        map.put(null, "b");
        map.put("3", null);
        map.put(null, null);

    }
}
