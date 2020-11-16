package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * LLRB implementation of binary search tree.
 */
public class RedBlackBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private static final boolean BLACK = false;
    private static final boolean RED = true;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        boolean color;

        Node(Key key, Value value, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
        }
    }

    private Node root;
    private int size = 0;

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        Node node = get(root, key);
        return node == null ? null : node.value;
    }

    private Node get(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int keyCompare = key.compareTo(x.key);
        if (keyCompare < 0) {
            return get(x.left, key);
        }
        if (keyCompare > 0) {
            return get(x.right, key);
        }
        return x;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            size++;
            return new Node(key, value, RED);
        }
        int keyCompare = key.compareTo(x.key);
        if (keyCompare < 0) {
            x.left = put(x.left, key, value);
        } else if (keyCompare > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }
        return fixUp(x);
    }

    private boolean isRed(Node x) {
        return x != null && x.color == RED;
    }

    private void flipColor(Node x) {
        x.color = !x.color;
        if (x.left != null) {
            x.left.color = !x.left.color;
        }
        if (x.right != null) {
            x.right.color = !x.right.color;
        }
    }

    private Node fixUp(Node x) {
        if (isRed(x.right) && !isRed(x.left)) {
            x = rotateLeft(x);
        }
        if (isRed(x.left) && isRed(x.left.left)) {
            x = rotateRight(x);
        }
        if (isRed(x.left) && isRed(x.right)) {
            flipColor(x);
        }
        return x;
    }

    private Node rotateRight(Node x) {
        Node left = x.left;
        x.left = left.right;
        left.right = x;
        left.color = x.color;
        x.color = RED;
        return left;
    }

    private Node rotateLeft(Node x) {
        Node right = x.right;
        x.right = right.left;
        right.left = x;
        right.color = x.color;
        x.color = RED;
        return right;
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        Value deletedValue = get(key);
        if (deletedValue != null) {
            root = delete(root, key);
            size--;
        }
        return deletedValue;
    }

    private Node moveRedLeft(Node x) {
        flipColor(x);
        if (isRed(x.right.left)) {
            x.right = rotateRight(x.right);
            x = rotateLeft(x);
            flipColor(x);
        }
        return x;
    }

    private Node moveRedRight(Node x) {
        flipColor(x);
        if (isRed(x.left.right)) {
            x = rotateRight(x);
            flipColor(x);
        }
        return x;
    }

    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int compareKey = key.compareTo(x.key);
        if (compareKey < 0) {
            if (x.left != null) {
                if (!isRed(x.left) && !isRed((x.left.left))) {
                    x = moveRedLeft(x);
                }
                x.left = delete(x.left, key);
            }
        } else {
            if (isRed(x.left)) {
                x = rotateRight(x);
                x.right = delete(x.right, key);
            } else if (compareKey == 0 && x.right == null) {
                return null;
            } else {
                if (x.right != null && !isRed(x.right) && !isRed(x.right.left)) {
                    x = moveRedRight(x);
                }
                if (x.key == key) {
                    Node min = minNode(x.right);
                    if (min != null) {
                        x.key = min.key;
                        x.value = min.value;
                    }
                    if (x.right != null) {
                        x.right = deleteMin(x.right);
                    }
                } else {
                    x.right = delete(x.right, key);
                }
            }
        }
        return fixUp(x);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) {
            return x.right;
        }
        if (!isRed(x.left) && !isRed(x.left.left)) {
            x = moveRedLeft(x);
        }
        x.left = deleteMin(x.left);
        return x;
    }

    @Nullable
    @Override
    public Key min() {
        Node min = minNode(root);
        return min == null ? null : min.key;
    }

    @Nullable
    @Override
    public Value minValue() {
        Node min = minNode(root);
        return min == null ? null : min.value;
    }

    private Node minNode(Node x) {
        if (x == null) {
            return null;
        }
        if (x.left == null) {
            return x;
        }
        return minNode(x.left);
    }

    @Nullable
    @Override
    public Key max() {
        Node max = maxNode(root);
        return max == null ? null : max.key;
    }

    @Nullable
    @Override
    public Value maxValue() {
        Node max = maxNode(root);
        return max == null ? null : max.value;
    }

    private Node maxNode(Node x) {
        if (x == null) {
            return null;
        }
        if (x.right == null) {
            return x;
        }
        return maxNode(x.right);
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        return floor(root, key, null);
    }

    private Key floor(Node x, Key key, Key max) {
        if (x == null) {
            return max;
        }
        int keyCompare = key.compareTo(x.key);
        if (keyCompare < 0) {
            max = floor(x.left, key, max);
        } else if (keyCompare > 0) {
            if (max == null || max.compareTo(x.key) < 0) {
                max = x.key;
            }
            max = floor(x.right, key, max);
        } else {
            max = x.key;
        }
        return max;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        return ceil(root, key, null);
    }

    private Key ceil(Node x, Key key, Key min) {
        if (x == null) {
            return min;
        }
        int keyCompare = key.compareTo(x.key);
        if (keyCompare > 0) {
            min = ceil(x.right, key, min);
        } else if (keyCompare < 0) {
            if (min == null || min.compareTo(x.key) > 0) {
                min = x.key;
            }
            min = ceil(x.left, key, min);
        } else {
            min = x.key;
        }
        return min;
    }

    @Override
    public int size() {
        return size;
    }
}
