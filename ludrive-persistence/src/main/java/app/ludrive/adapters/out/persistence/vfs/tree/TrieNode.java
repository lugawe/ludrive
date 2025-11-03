package app.ludrive.adapters.out.persistence.vfs.tree;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public final class TrieNode<T> {

    private final Map<String, TrieNode<T>> children = new ConcurrentHashMap<>();

    private final String segment;

    private volatile T value;

    private TrieNode() {
        this.segment = null;
        this.value = null;
    }

    public TrieNode(String segment, T value) {
        this.segment = Objects.requireNonNull(segment);
        this.value = Objects.requireNonNull(value);
    }

    public TrieNode<T> putChild(String segment, T value) {

        TrieNode<T> node = new TrieNode<>(segment, value);

        return children.put(segment, node);
    }

    public TrieNode<T> getChild(String segment) {

        if (segment == null) {
            throw new NullPointerException("segment");
        }

        return children.get(segment);
    }

    public Collection<TrieNode<T>> getChildren() {
        return Collections.unmodifiableCollection(children.values());
    }

    public String getSegment() {
        return segment;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void clear() {
        children.forEach((k, v) -> v.clear());
        children.clear();
        value = null;
    }

    public static <T> TrieNode<T> createEmptyTreeNode() {
        return new TrieNode<>();
    }
}
