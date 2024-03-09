package main;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class URLshortenerService{

    Node root;
    Map<Character, String> mp = new HashMap<>();

    // Encodes a URL to a shortened URL.
    static class Node{
        char c;
        int val;
        Node left;
        Node right;
        public Node(char c, int val){
            this.c = c;
            this.val = val;
            this.left = null;
            this.right = null ;
        }

        public Node(char c, int val,  Node left,Node right){
            this.c = c;
            this.val = val;
            this.left = left;
            this.right = right;
        }

    }

    public void createHuffmanTree(Node root, String encoding)
    {
        if(root.left == null && root.right == null){
            mp.put(root.c, encoding);
            return ;
        }

        createHuffmanTree(root.left, encoding+"0");
        createHuffmanTree(root.right, encoding+"1");
    }

    public String encode(String longUrl) {
        Map<Character, Integer> mp2 = new HashMap<>();
        for(int i =0; i<longUrl.length(); i++){
            char key = longUrl.charAt(i);
            if(mp2.containsKey(key)){
                mp2.put(key, mp2.get(key)+1);
            }else{
                mp2.put(key, 1);
            }

        }
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
        for(Map.Entry<Character, Integer> entry: mp2.entrySet()){
            pq.add(new Node(entry.getKey(), entry.getValue()));
        }
        root = null;
        while(pq.size()> 1){
            Node a = pq.poll();
            Node b = pq.poll();

            Node currRoot = new Node('\u0000', a.val+b.val, a,b);
            root = currRoot;
            pq.add(currRoot);
        }
        createHuffmanTree(root, "");
        showHuffManTreeMap();
        StringBuilder url = new StringBuilder("http://tinyurl.com/");
        for(int i =0; i< longUrl.length();i++){
            String encoding = mp.get(longUrl.charAt(i));
            url.append(encoding);
        }
        return url.toString();

    }

    private void showHuffManTreeMap() {
        for(Map.Entry<Character, String> entry: mp.entrySet()){
            System.out.println("key->"+ entry.getKey()+": value ->"+ entry.getValue());
        }
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        int len = shortUrl.length();
        StringBuilder decodeUrl = new StringBuilder("");
        for(int i =19;i< len; ){
            Node start = root;
            while(start.left != null && start.right != null){
                if(shortUrl.charAt(i) == '0'){
                    start = start.left;
                }else{
                    start = start.right;
                }
                i++;
            }
            System.out.println("Decoding url..."+ start.c);
            decodeUrl.append(start.c);
        }
        return decodeUrl.toString();
    }
}
