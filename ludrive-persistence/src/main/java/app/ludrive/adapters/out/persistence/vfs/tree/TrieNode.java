package app.ludrive.adapters.out.persistence.vfs.tree;

public class TrieNode<T> {

    private String key;
    private T content;

    private TrieNode<T>[] children;

    public TrieNode() {}

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public TrieNode<T>[] getChildren() {
        return children;
    }

    public void setChildren(TrieNode<T>[] children) {
        this.children = children;
    }
}
