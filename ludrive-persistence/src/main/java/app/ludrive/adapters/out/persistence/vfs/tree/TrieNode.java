package app.ludrive.adapters.out.persistence.vfs.tree;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public final class TrieNode<T> {

    private final Map<String, TrieNode<T>> children = new ConcurrentHashMap<>();

    private volatile T value;

    private TrieNode() {}

    public TrieNode(T value) {
        this.value = Objects.requireNonNull(value);
    }

    public TrieNode<T> getChild(String segment) {

        if (segment == null) {
            throw new NullPointerException("segment");
        }

        return children.get(segment);
    }

    public TrieNode<T> getOrCreateChild(String segment, Function<String, T> valueFactory) {

        if (segment == null) {
            throw new NullPointerException("segment");
        }

        if (valueFactory == null) {
            throw new NullPointerException("valueFactory");
        }

        return children.computeIfAbsent(segment, (s) -> new TrieNode<>(valueFactory.apply(s)));
    }

    public Collection<TrieNode<T>> getChildren() {
        return Collections.unmodifiableCollection(children.values());
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
