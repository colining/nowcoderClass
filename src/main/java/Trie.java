/**
 * Created by colin on 2017/7/23.
 */
public class Trie {
    TrieNode root;
    int i = 97;

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        root = new TrieNode();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.children[c - i] == null) {
                node.children[c - i] = new TrieNode();
            }
            node = node.children[c - i];
        }
        node.string = word;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.children[c - i] == null) {
                return false;
            }
            node = node.children[c - i];
        }
        return node.string.equals(word);
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            if (node.children[c - i] == null) {
                return false;
            }
            node = node.children[c - i];
        }
        return true;
    }
}

class TrieNode {
    String string = "";
    TrieNode[] children = new TrieNode[26];
    public TrieNode() {
    }
}