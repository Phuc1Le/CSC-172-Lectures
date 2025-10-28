import java.util.*;
public class Sort{
    public void InsertionSortInterleaved(int[] numbers, int sz, int st, int gap){
        int i;
        int j;
        int tmp;
        for(i=st+gap;i<sz;i+=gap){
            j=i;
            while(j-gap >= st && numbers[i] < numbers[j-gap]){
                tmp = numbers[j];
                numbers[j] = numbers[j-gap];
                numbers[j - gap] = tmp;
                j = j - gap;
            }
        }
    }
    public void shellSort(int[] numbers, int sz, int[] gapVal){
        for(int gap: gapVal){
            for(int i=0;i<gap;i++){
                InsertionSortInterleaved(numbers, sz, i, gap);
            }
        }
    }
    public int partition(int[] numbers, int lo, int hi){
        int mid = lo + (hi-lo)/2;
        int pivot = numbers[mid];
        boolean done = false;
        while(!done){
            while(numbers[lo] < pivot){
                lo++;
            }
            while(numbers[hi] > pivot){
                hi--;
            }
            if(lo >= hi){
                done = true;
            }
            else{
                int tmp = numbers[lo];
                numbers[lo] = numbers[hi];
                numbers[hi] = tmp;
                lo++;
                hi--;
            }
        }
        return hi;
    }
    public void quickSort(int[] numbers, int lo, int hi){
        if(lo >= hi) return;
        int ind = partition(numbers, lo, hi);
        quickSort(numbers, lo, ind);
        quickSort(numbers, ind+1, hi);
    }
    public void merge(int[] numbers, int l1, int l2, int r2){
        int sz = r2-l1+1;
        int[] merged = new int[sz];
        int i = l1;
        int j = l2+1;
        int mpos = 0;
        while(i<=l2 && j <= r2){
            if(numbers[i] <= numbers[j]){
                merged[mpos] = numbers[i];
                i++;
            }
            else{
                merged[mpos] = numbers[j];
                j++;
            }
            mpos++;
        }
        while(i<=l2){
            merged[mpos] = numbers[i];
            i++;
            mpos++;
        }
        while(j<=r2){
            merged[mpos] = numbers[j];
            j++;
            mpos++;
        }
        for(mpos=0;mpos<sz;mpos++){
            numbers[l1+mpos] = merged[mpos];
        }
    }
    public void mergeSort(int[] numbers, int l, int r){
        if(l<r){
            int mid = (l+r)/2;
            mergeSort(numbers,l,mid);
            mergeSort(numbers,mid+1,r);
            merge(numbers,l,mid,r);
        }
    }
    public int getml(int[] num){
        int mx=0;
        for(int i:num){
            mx = Math.max(mx, getl(i));
        }
        return mx;
    }
    public int getl(int val){
        if(val==0) return 1;
        int cnt=0;
        while(val>0){
            val/=10;
            cnt++;
        }
        return cnt;
    }
    public void radixSort(int[] num){
        List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            buckets.add(new ArrayList<>());
        }
        int maxl = getml(num);
        int p=1;
        for(int di=1;di<=maxl;di++){
            for(int i:num){
                int ind = (i/p)%10;
                buckets.get(ind).add(i);
            }
            int ind=0;
            for(int i=0;i<10;i++){
                for(int j:buckets.get(i)){
                    num[ind] = j;
                    ind++;
                }
            }
            p = 10*p;
            for (List<Integer> bucket : buckets) {
                bucket.clear();
            }
        }
    }
    public static void main(String[] args) {
        Sort srt = new Sort();
        int[] arr = {1,7,9,8,3,2,4,6,5,10};
        // int[] gapVal = {5,3,1};
        // srt.shellSort(arr, 10, gapVal);
        // srt.quickSort(arr, 0, 9);
        
        // srt.mergeSort(arr, 0, 9);
        srt.radixSort(arr);
        for(int i:arr){
            System.out.print(i + " ");
        }
    }
}