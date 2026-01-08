import java.io.File;
import java.util.Scanner;
import java.util.*;
class Trie{
    Trie ch[];
    int wc;
    boolean ended;
    Trie(){
        ch = new Trie[26];
        wc = 0;
        ended = false;
    }
}
class myclass{
    public static void main(String[] args) {
        Scanner sc = new Scanner(new File("input.txt"));
        System.out.println("Enter No. of Operations : \n");
        int n = sc.nextInt();
        System.out.println("1 : Insert to  Trie\n");
        System.out.println("2 : Search a word  Trie\n");
        System.out.println("3 : Get all words in Trie\n");
        System.out.println("4 : Get all words in Trie with prefix\n");
        Trie root = new Trie();
        while(n-- > 0){
            int x = sc.nextInt();
            if(x == 1){
                String s = sc.next();
                Insert(root,s);
            }
            if(x == 2){
                String s = sc.next();
                if(Search(root, s)){
                    System.out.println("Exist");
                }else{
                    System.out.println("Does Not Exist");
                }
            }
            if(x == 3){
                List<String> li = new ArrayList<>();
                getAllWords(root, li, "");
                System.out.println(li);
            }
            if(x == 4){
                List<String> li = new ArrayList<>();
                String ps = sc.next();
                getAllWordsWithPrefix(root, li, ps);
                System.out.println(li);
            }
        }
        sc.close();
    }
    static void Insert(Trie root , String s){
        Trie te = root;
        for(char c : s.toCharArray()){
            int idx = c - 'a';
            if(te.ch[idx] == null){
                te.ch[idx] = new Trie();
            }
            te = te.ch[idx];
            te.wc++;
        }
        te.ended = true;
    }
    static boolean Search(Trie root , String s){
        Trie te = root;
        for(char c : s.toCharArray()){
            int idx = c - 'a';
            if(te.ch[idx] == null){
                return false;
            }
            te = te.ch[idx];
            
        }
        return te.ended;
    }
    static  void getAllWords(Trie root, List<String> li , String te){
        if(root.ended){
            li.add(te);
        }
        for(int i=0;i<26;i++){
            if(root.ch[i] != null){
                char ch = (char)(i + 'a');
                getAllWords(root.ch[i], li, te+ch);
            }
        }
    }
    static void getAllWordsWithPrefix(Trie root ,List<String> li, String ps){
        Trie te = root;
        for(char c : ps.toCharArray()){
            int idx = c - 'a';
            if(te.ch[idx] == null){
                return;
            }
            te = te.ch[idx];
            
        }
        getAllWords(te, li, ps);
    }
}
