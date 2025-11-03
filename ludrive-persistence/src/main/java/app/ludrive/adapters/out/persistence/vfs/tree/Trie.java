package app.ludrive.adapters.out.persistence.vfs.tree;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public final class Trie<T> {

    private final Map<String, String[]> cache = new ConcurrentHashMap<>();

    private final String separator;
    private final AtomicInteger size; // TODO
    private final TrieNode<T> root;

    public Trie(String separator) {
        this.separator = Objects.requireNonNull(separator);
        this.size = new AtomicInteger(0);
        this.root = TrieNode.createEmptyTreeNode();
    }

    private String[] split(String key) {

        if (key.equals(separator)) {
            return null;
        } else if (key.startsWith(separator)) {
            key = key.substring(separator.length());
        }

        return key.split(separator);
    }

    private String[] segments(String key) {
        return cache.computeIfAbsent(key, this::split);
    }

    private TrieNode<T> findParentNode(String key) {

        String[] segments = segments(key);

        TrieNode<T> current = root;

        for (int i = 0; i < segments.length - 1; i++) {
            current = current.getChild(segments[i]);
            if (current == null) {
                return null;
            }
        }

        return current;
    }

    private TrieNode<T> findNode(String key) {

        String[] segments = segments(key);

        if (segments == null || segments.length == 0) {
            return root;
        }

        TrieNode<T> current = root;

        for (String segment : segments) {
            current = current.getChild(segment);
            if (current == null) {
                return null;
            }
        }

        return current;
    }

    public List<T> listDirectChildren(String key) {

        if (key == null) {
            throw new NullPointerException("Parameter 'key' cannot be null");
        }

        TrieNode<T> node = findNode(key);
        if (node == null) {
            throw new IllegalArgumentException("Node '" + key + "' not found");
        }

        return node.getChildren().values().stream().map(TrieNode::getValue).toList();
    }

    public Optional<T> get(String key) {

        if (key == null) {
            throw new NullPointerException("Parameter 'key' cannot be null");
        }

        TrieNode<T> node = findNode(key);
        return node != null ? Optional.ofNullable(node.getValue()) : Optional.empty();
    }

    public void put(String key, T value) {

        if (key == null) {
            throw new NullPointerException("Parameter 'key' cannot be null");
        }

        if (value == null) {
            throw new NullPointerException("Parameter 'value' cannot be null");
        }

        String[] segments = segments(key);

        if (segments == null || segments.length == 0) {
            throw new IllegalArgumentException("Root node is not allowed");
        }

        TrieNode<T> parent = findParentNode(key);
        if (parent == null) {
            throw new IllegalArgumentException("Parent node '" + key + "' not found");
        }

        String lastSegment = segments[segments.length - 1];
        TrieNode<T> existing = parent.getChild(lastSegment);

        if (existing == null) {
            parent.putChild(lastSegment, value);
        } else {
            existing.setValue(value);
        }
    }

    public void remove(String key) {

        if (key == null) {
            throw new NullPointerException("Parameter 'key' cannot be null");
        }

        TrieNode<T> node = findNode(key);
        if (node == null) {
            throw new IllegalArgumentException("Node '" + key + "' not found");
        }

        node.clear();
    }

    public boolean contains(String key) {
        return findNode(key) != null;
    }

    public int size() {
        return size.get();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void clear() {
        root.clear();
        size.set(0);
    }
}
