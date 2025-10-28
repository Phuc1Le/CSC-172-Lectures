public class QuadraticProbing<K,V>{
    private static class Entry<K, V> {
        K key;
        V value;
        int state;
        Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.state = 2; // 1:empty after removal; 2:occupied
        }
    }

    private Entry<K, V>[] table;
    private int capacity, c1, c2;
    public QuadraticProbing(int capacity, int c1, int c2) {
        this.capacity = capacity;
        table = (Entry<K, V>[]) new Entry[capacity];
        this.c1 = c1;
        this.c2 = c2;
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
        int ini = hash(k);
        for(int i=0; i <capacity;i++){
            int ind = (ini + c1*i + c2*i*i)%capacity;
            if(table[ind] == null){
                break;
            }
            else if(table[ind].state==2){
                if(k.equals(table[ind].key)){
                    table[ind].value = v;
                    return true;
                }
            }
        }
        for(int i=0;i<capacity;i++){
            int ind = (ini + c1*i + c2*i*i)%capacity;
            if(table[ind] == null || table[ind].state != 2){
                Entry<K, V> ent = new Entry<>(k,v);
                table[ind] = ent;
                return true;
            }
        }
        return false;
    }
    public V get(K k){
        int ini = hash(k);
        int ind;
        for(int i=0;i<capacity;i++){
            ind = (ini + c1*i + c2*i*i)%capacity;
            if(table[ind] == null){
                return null;
            }
            if(table[ind].state == 2){
                if(k.equals(table[ind].key)){
                    return table[ind].value;
                }
            }
        }
        return null;
    }
    public boolean remove(K k){
        int ini = hash(k);
        int ind = 0;
        for(int i=0;i<capacity;i++){
            ind = (ini + c1*i + c2*i*i)%capacity;
            if(table[ind] == null){
                return false;
            }
            if(table[ind].state == 2){
                if(k.equals(table[ind].key)){
                    table[ind].state = 1;
                    return true;
                }
            }
        }
        return false;
    }
    public static void main(String[] args) {
        LinearProbing<String, Integer> map = new LinearProbing<>(10);

        System.out.println("Insert A=1: " + map.insert("A", 1));
        System.out.println("Insert B=2: " + map.insert("B", 2));
        System.out.println("Insert A=10 (update): " + map.insert("A", 10));

        System.out.println("Remove B: " + map.remove("B"));
        System.out.println("Insert C=3: " + map.insert("C", 3));
        System.out.println("Insert D=4: " + map.insert("D", 4));
        System.out.println("Insert E=5: " + map.insert("E", 5));
        System.out.println(map.get("A"));
        System.out.println(map.get("B"));
        System.out.println(map.get("C"));
        System.out.println(map.get("D"));
        System.out.println(map.get("E"));
    }

}