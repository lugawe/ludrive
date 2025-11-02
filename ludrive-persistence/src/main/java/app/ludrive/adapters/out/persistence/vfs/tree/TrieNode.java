package app.ludrive.adapters.out.persistence.vfs.tree;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public final class TrieNode<T> {

    private final Map<String, TrieNode<T>> children = new ConcurrentHashMap<>();

    private volatile T content;

    public TrieNode(T content) {
        this.content = Objects.requireNonNull(content);
    }

    public TrieNode() {}

    public TrieNode<T> getChild(String segment) {
        return children.get(segment);
    }

    public TrieNode<T> getOrCreateChild(String segment) {
        return children.computeIfAbsent(segment, (s) -> new TrieNode<>());
    }

    public Collection<TrieNode<T>> getChildren() {
        return children.values();
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public void clear() {
        children.forEach((k, v) -> v.clear());
        children.clear();
        content = null;
    }
}
