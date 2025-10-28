public class node<K extends Comparable<K>> {
    K key;
    node<K> left, right, par;
    int hei;
    public node(K key) {
        this.key = key;
        this.left = null;
        this.right = null;
        this.par = null;
        this.hei = 0;
    }
}
