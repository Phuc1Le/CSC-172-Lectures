public class bst<K extends  Comparable<K>>{
    private node<K> root;
    public bst(K k){
        root = new node<>(k);
    }
    public node<K> getRoot(){
        return root;
    }
    public node<K> search(K k){
        node<K> cur = root;
        while(cur != null){
            if(k.equals(cur.key)){
                return cur;
            }
            int cmp = k.compareTo(cur.key);
            if(cmp < 0){
                cur = cur.left;
            }
            else{
                cur = cur.right;
            }
        }
        return null;
    }
    public void insertNode(node<K> n){
        if(root == null){
            root = n;
            n.par = null;
            return;
        }
        node<K> cur = root;
        while(cur != null){
            int cmp = n.key.compareTo(cur.key);
            if(cmp < 0){
                if(cur.left == null){
                    cur.left = n;
                    n.par = cur;
                    cur = null;
                }
                else{
                    cur = cur.left;
                }
            }
            else{
                if(cur.right == null){
                    cur.right = n;
                    n.par = cur;
                    cur = null;
                }
                else{
                    cur = cur.right;
                }
            }
        }
    }
    public boolean insertKey(K k){
        if(search(k) != null) return false;
        node<K> ne = new node<>(k);
        insertNode(ne);
        return true;
    }
    public boolean replace(node par, node cur, node n){
        if (!par.left.equals(cur) && !par.right.equals(cur)) {
            return false;
        }
        if (par.left.equals(cur)) {
            par.left = n;
        }
        else {
            par.right = n;
        }

        if (n != null) {
            n.par = par;
        }
        return true;
    }
    public void removeKey(K k){
        node<K> n = search(k);
        remove(n);
    }
    public void remove(node<K> n){
        if(n==null){
            return;
        }
        if(n.left!=null && n.right!=null){
            node<K> succ = n.right;
            while(succ.left!=null){
                succ = succ.left;
            }
            n.key = succ.key;
            remove(succ);
        }
        else if(n == root){
            if(n.left != null){
                root = n.left;
            }
            else root = n.right;
            if(root != null){
                root.par = null;
            }
        }
        else if(n.left != null){
            replace(n.par, n, n.left);
        }
        else{
            replace(n.par, n, n.right);
        }
    }
    public void inorder(node n){
        if(n == null){
            return;
        }
        inorder(n.left);
        System.out.print(n.key + " ");
        inorder(n.right);
    }
    public void revinorder(node n){
        if(n == null){
            return;
        }
        revinorder(n.right);
        System.out.print(n.key + " ");
        revinorder(n.left);
    }
    public void preorder(node n){
        if(n == null){
            return;
        }
        System.out.print(n.key + " ");
        preorder(n.left);
        preorder(n.right);
    }
    public void postorder(node n){
        if(n == null){
            return;
        }
        postorder(n.left);
        postorder(n.right);
        System.out.print(n.key + " ");
    }
    public int getHeight(node n){
        if(n == null){
            return -1;
        }
        int lheight = getHeight(n.left);
        int rheight = getHeight(n.right);
        return 1 + Math.max(lheight, rheight);
    }
    // // AVL algorithms
    // public void updateHeight(node n){
    //     int lheight = -1;
    //     if(n.left != null){
    //         lheight = n.left.hei;
    //     }
    //     int rheight = -1;
    //     if(n.right != null){
    //         rheight = n.right.hei;
    //     }
    //     n.hei = Math.max(lheight, rheight) +1;
        
    // }
    public static void main(String[] args) {
        bst<Integer> tree = new bst<>(5);
        node<Integer> root = tree.getRoot();
        tree.insertKey(3);
        tree.insertKey(7);
        tree.insertKey(10);
        tree.insertKey(4);
        tree.insertKey(1);
        System.out.println(tree.search(7).key);
        tree.removeKey(7);
        System.out.println(tree.search(7));
        tree.inorder(root);
        System.out.println();
        tree.revinorder(root);
        System.out.println();
        tree.preorder(root);
        System.out.println();
        tree.postorder(root);
        System.out.println();
        System.out.println(tree.getHeight(root));
    }
}