package app.ludrive.adapters.out.persistence.vfs.tree;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import app.ludrive.core.exception.Exceptions;

public final class TrieNode<T> {

    private final Map<String, TrieNode<T>> children = new ConcurrentHashMap<>();

    private final String segment;

    private volatile T value;

    private TrieNode() {
        this.segment = null;
        this.value = null;
    }

    public TrieNode(String segment, T value) {

        if (segment == null) {
            throw Exceptions.createNullPointer("segment");
        }

        if (value == null) {
            throw Exceptions.createNullPointer("value");
        }

        this.segment = segment;
        this.value = value;
    }

    public TrieNode<T> putChild(String segment, T value) {

        if (segment == null) {
            throw Exceptions.createNullPointer("segment");
        }

        if (value == null) {
            throw Exceptions.createNullPointer("value");
        }

        TrieNode<T> node = new TrieNode<>(segment, value);

        return children.put(segment, node);
    }

    public TrieNode<T> getChild(String segment) {

        if (segment == null) {
            throw Exceptions.createNullPointer("segment");
        }

        return children.get(segment);
    }

    public Map<String, TrieNode<T>> getChildren() {
        return Collections.unmodifiableMap(children);
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

    public long clear() {
        long result = 1
                + children.values().parallelStream().mapToLong(TrieNode::clear).sum();
        children.clear();
        value = null;
        return result;
    }

    public static <T> TrieNode<T> createEmptyTreeNode() {
        return new TrieNode<>();
    }
}
