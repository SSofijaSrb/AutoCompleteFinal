package autocomplete;

import java.util.*;

public class Trie {
    private final TrieNode root = new TrieNode();

    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node = node.children.computeIfAbsent(c, k -> new TrieNode());
        }
        node.isWord = true;
        node.frequency++;
    }

    public List<String> autoComplete(String prefix) {
        List<String> results = new ArrayList<>();
        TrieNode node = root;

        for (char c : prefix.toCharArray()) {
            if (!node.children.containsKey(c)) return results;
            node = node.children.get(c);
        }

        dfs(node, new StringBuilder(prefix), results);
        results.sort(Comparator.comparingInt(this::getFrequency).reversed());
        return results;
    }

    private void dfs(TrieNode node, StringBuilder current, List<String> results) {
        if (node.isWord) results.add(current.toString());
        for (char c : node.children.keySet()) {
            current.append(c);
            dfs(node.children.get(c), current, results);
            current.setLength(current.length() - 1);
        }
    }

    private int getFrequency(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node = node.children.get(c);
        }
        return node.frequency;
    }
}
