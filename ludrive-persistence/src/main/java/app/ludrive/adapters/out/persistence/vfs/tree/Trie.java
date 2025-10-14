package app.ludrive.adapters.out.persistence.vfs.tree;

public class Trie<T> {

    private TrieNode<T> root;

    public Trie() {}

    public TrieNode<T> getRoot() {
        return root;
    }

    public void setRoot(TrieNode<T> root) {
        this.root = root;
    }
}
