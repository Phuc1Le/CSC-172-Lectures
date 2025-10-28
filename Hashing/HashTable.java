public class HashTable<K,V>{
    private static class Entry<K, V> {
        K key;
        V value;
        Entry<K,V> next;
        Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private Entry<K, V>[] table;
    private int capacity;
    public HashTable(int capacity) {
        this.capacity = capacity;
        table = (Entry<K, V>[]) new Entry[capacity];
    }

    public int hashString(String stringValue) {
        int hashCode = 0;
        for (int i = 0; i < stringValue.length(); i++) {
            hashCode += stringValue.charAt(i);
        }
        return hashCode;
    }

    // Hash an int by forcing its sign bit to 0
    public int hashInt(int intValue) {
        // Clear the sign bit using bitmask
        return intValue & 0x7FFFFFFF;
    }

    public int hash(Object o){
        int h;
        if(o instanceof String){
            h = hashString((String) o);
        }
        else if(o instanceof Integer){
            h = hashInt((Integer)o);
        }
        else {
            throw new IllegalArgumentException("Unsupported key type: " + o.getClass());
        }
        return h % capacity;
    }
    public boolean insert(K k, V v){
        int ind = hash(k);
        Entry<K, V> cur = table[ind];
        Entry<K, V> prev = null;
        while(cur != null){
            if(k == cur.key){
                cur.value = v;
                return true;
            }
            prev = cur;
            cur = cur.next;
        }
        Entry<K,V> n = new Entry<>(k, v);
        if(table[ind] == null){
            table[ind] = n;
        } 
        else{
            prev.next = n;
        }
        return true;
    }
    public V get(K k){
        int ind = hash(k);
        Entry<K, V> cur = table[ind];
        while(cur != null){
            if(k == cur.key){
                return cur.value;
            }
            cur = cur.next;
        }
        return null;
    }
    public boolean remove(K k){
        int ind = hash(k);
        Entry<K, V> cur = table[ind];
        Entry<K, V> prev = null;
        while(cur != null){
            if(k == cur.key){
                if(prev == null){
                    table[ind] = cur.next;
                }
                else{
                    prev.next = cur.next;
                }
                return true;
            }
            prev = cur;
            cur = cur.next;
        }
        return false;
    }
    public static void main(String[] args) {
        HashTable<Object, Integer> myH = new HashTable(10);
        myH.insert(15, 10);
        myH.insert(25, 11);
        System.out.println(myH.get(25));
        myH.remove(25);
        System.out.println(myH.get(25));
    }
}