package app.ludrive.adapters.out.persistence.vfs.tree;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import app.ludrive.core.exception.Exceptions;

public final class TrieNode<T> {

    private final Map<String, TrieNode<T>> children = new ConcurrentHashMap<>();

    private final TrieNode<T> parent;

    private final String segment;

    private volatile T value;

    private TrieNode() {
        this.parent = null;
        this.segment = null;
        this.value = null;
    }

    public TrieNode(TrieNode<T> parent, String segment, T value) {

        if (parent == null) {
            throw Exceptions.createNullPointer("parent");
        }

        if (segment == null) {
            throw Exceptions.createNullPointer("segment");
        }

        if (value == null) {
            throw Exceptions.createNullPointer("value");
        }

        this.parent = parent;
        this.segment = segment;
        this.value = value;
    }

    public TrieNode<T> putChild(String segment, T value) {

        TrieNode<T> node = new TrieNode<>(this, segment, value);

        return children.put(segment, node);
    }

    public TrieNode<T> getChild(String segment) {

        if (segment == null) {
            throw Exceptions.createNullPointer("segment");
        }

        return children.get(segment);
    }

    public TrieNode<T> removeChild(String segment) {

        if (segment == null) {
            throw Exceptions.createNullPointer("segment");
        }

        return children.remove(segment);
    }

    public Map<String, TrieNode<T>> getChildren() {
        return Collections.unmodifiableMap(children);
    }

    public TrieNode<T> getParent() {
        return parent;
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
        long result = 1 + children.values().stream().mapToLong(TrieNode::clear).sum();
        children.clear();
        value = null;
        if (parent != null && segment != null) {
            parent.children.remove(segment);
        }
        return result;
    }

    public static <T> TrieNode<T> createEmptyTreeNode() {
        return new TrieNode<>();
    }
}
