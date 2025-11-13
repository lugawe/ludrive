package app.ludrive.adapters.out.persistence.vfs.tree;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

import app.ludrive.core.exception.Exceptions;

public final class Trie<T> {

    private final Map<String, String[]> cache = new HashMap<>();

    private final String separator;
    private final AtomicLong size;
    private final TrieNode<T> root;

    public Trie(String separator) {
        this.separator = Objects.requireNonNull(separator);
        this.size = new AtomicLong(0);
        this.root = TrieNode.createRoot();
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

        if (segments == null || segments.length == 0) {
            return root;
        }

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
            throw Exceptions.createNullPointer("key");
        }

        TrieNode<T> node = findNode(key);
        if (node == null) {
            throw Exceptions.createNotFound(TrieNode.class, key);
        }

        return node.getChildren().values().stream().map(TrieNode::getValue).toList();
    }

    public Optional<T> get(String key) {

        if (key == null) {
            throw Exceptions.createNullPointer("key");
        }

        TrieNode<T> node = findNode(key);
        return node != null ? Optional.ofNullable(node.getValue()) : Optional.empty();
    }

    public void put(String key, T value) {

        if (key == null) {
            throw Exceptions.createNullPointer("key");
        }

        if (value == null) {
            throw Exceptions.createNullPointer("value");
        }

        String[] segments = segments(key);

        if (segments == null || segments.length == 0) {
            throw Exceptions.createNotAllowed(key);
        }

        TrieNode<T> parent = findParentNode(key);
        if (parent == null) {
            throw Exceptions.createNotFound(TrieNode.class, key);
        }

        String lastSegment = segments[segments.length - 1];
        TrieNode<T> existing = parent.getChild(lastSegment);

        if (existing == null) {
            parent.putChild(lastSegment, value);
            size.incrementAndGet();
        } else {
            existing.setValue(value);
        }
    }

    public void remove(String key) {

        if (key == null) {
            throw Exceptions.createNullPointer("key");
        }

        TrieNode<T> node = findNode(key);
        if (node == null) {
            throw Exceptions.createNotFound(TrieNode.class, key);
        }

        size.addAndGet(-node.clear());
    }

    public boolean contains(String key) {
        return findNode(key) != null;
    }

    public long size() {
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
