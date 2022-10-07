package examples.h_collections;

import java.util.HashMap;

public class StringHashMap {
    public static void main(String[] args) {
        // a hash map, or a look up table has a key and a value
        // this hashmap uses strings for keys, and strings for values
        HashMap<String, String> me = new HashMap<>();
        me.put("name", "Chris");
        me.put("address", "123 Home Street");
        me.put("favColor", "orange");
        me.put("favFood", "chocolate");
        me.remove("favColor");

        // ask for a value, by it's key
        System.out.println(me.get("name"));
    }
}
