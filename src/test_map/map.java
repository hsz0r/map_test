/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test_map;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hsz0r
 * @param <K>
 * @param <V>
 */
public class map<K extends Comparable<K>, V extends Comparable<V>> {
    
    private Node root;
    private int size;

    class Comparator<T extends Comparable>{
        public boolean Comparator(T object1, T object2) {
            if (object1.compareTo(object2) < 0){
                return true;
            } else {
            return false;
            }
        }
    }


  
    private class Node {
        
        private K key;
        private V value;
        private int height;
        private Node left;
        private Node right;
        
        Node(V value, K key){
            this.value = value;
            this.key = key;
            this.height = 0;
        }
    }
    
    public map(){
        this.root = null;
        this.size = 0;

    }
    public map(map map){ 
        this.root = recursiveCopy(map.root);
    }
    
    public boolean isEmpty() {
        return root == null;
    }
    public boolean contains(K key) {
        return get(key) != null;
    }
    
    public void clear() {
        root = null;
    }
    
    public V get(K key) {
        Node node = get(root, key);
        if (node == null) return null;
        return node.value;
    }
    
    public void add(V value, K key) {
        this.root = add(root, key, value);
        this.size++;
    }
    
    public int getSize(){
        return this.size;
    }
    
    public boolean maxHeightTest(){
        return this.root.height <= (1.45*(Math.log((getSize() + 2))/Math.log(2)));
    }
    
    private Node add(Node node, K key, V value){
        if (node == null) return new Node(value, key);
        if (node.value.equals(value)) throw new IllegalArgumentException("Values should be different");
        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else {
            return node;
        }
        return balance(node);
    }
    
    private Node get(Node node, K key) {
        if (node == null) return null;
        if (key.compareTo(node.key) < 0) return get(node.left, key);
        else if (key.compareTo(node.key) > 0) return get(node.right, key);
        else return node;
    }
    
    private Node recursiveCopy(Node node){
        if(node == null){
            return null;
        }
        Node newNode = new Node(null,null);
        if(node.value == null && node.key == null){
            newNode.value = null;
            newNode.key = null;
        }else{
            newNode.value = node.value;
            newNode.key = node.key;
        }
        newNode.left = recursiveCopy(node.left);
        newNode.right = recursiveCopy(node.right);
        return newNode;
    }
    private Node balance(Node node) {
        updateHeight(node);
        if (getBalance(node) < -1) {
            if (getBalance(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            node = rotateLeft(node);
        }
        else if (getBalance(node) > 1) {
            if (getBalance(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            node = rotateRight(node);
        }
        return node;
    }   
    
    private void updateHeight(Node node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }
    
    private int getBalance(Node x) {
        return height(x.left) - height(x.right);
    }
    public int height(Node node) {
        if (node == null) return -1;
        return node.height;
    }
    
    private Node rotateRight(Node node) {
        Node newNode = node.left;
        node.left = newNode.right;
        newNode.right = node;
        updateHeight(node);
        updateHeight(newNode);
        return newNode;
    }

    private Node rotateLeft(Node node) {
        Node newNode = node.right;
        node.right = newNode.left;
        newNode.left = node;
        updateHeight(node);
        updateHeight(newNode);
        return newNode;
    }

    public void printTree() {
        toTreeString(root, 0);
    }
    public void printKeyTree() {
        toTreeString(root, 1);
    }
    public void printValTree() {
        toTreeString(root, 2);
    }

	private void toTreeString(Node root, int ctrl)
	{
		List<List<String>> lines = new ArrayList<>();

		List<Node> level = new ArrayList<>();
		List<Node> next = new ArrayList<>();

		level.add(root);
		int nn = 1;

		int widest = 0;

		while (nn != 0) {
			List<String> line = new ArrayList<>();

			nn = 0;

			for (Node n : level) {
				if (n == null) {
					line.add(null);

					next.add(null);
					next.add(null);
				} else {
					String aa;
                                    switch (ctrl) {
                                        case 0:
                                            aa = n.toString();
                                            break;
                                        case 1:
                                            aa = n.key.toString();
                                            break;
                                        default:
                                            aa = n.value.toString();
                                            break;
                                    }
					line.add(aa);
					if (aa.length() > widest) widest = aa.length();

					next.add(n.left);
					next.add(n.right);

					if (n.left != null) nn++;
					if (n.right != null) nn++;
				}
			}

			if (widest % 2 == 1) widest++;

			lines.add(line);

			List<Node> tmp = level;
			level = next;
			next = tmp;
			next.clear();
		}

		int perpiece = lines.get(lines.size() - 1).size() * (widest + 4);
		for (int i = 0; i < lines.size(); i++) {
			List<String> line = lines.get(i);
			int hpw = (int) Math.floor(perpiece / 2f) - 1;

			if (i > 0) {
				for (int j = 0; j < line.size(); j++) {

					char c = ' ';
					if (j % 2 == 1) {
						if (line.get(j - 1) != null) {
							c = (line.get(j) != null) ? '┴' : '┘';
						} else {
							if (j < line.size() && line.get(j) != null) c = '└';
						}
					}
					System.out.print(c);

					if (line.get(j) == null) {
						for (int k = 0; k < perpiece - 1; k++) {
							System.out.print(" ");
						}
					} else {

						for (int k = 0; k < hpw; k++) {
							System.out.print(j % 2 == 0 ? " " : "─");
						}
						System.out.print(j % 2 == 0 ? "┌" : "┐");
						for (int k = 0; k < hpw; k++) {
							System.out.print(j % 2 == 0 ? "─" : " ");
						}
					}
				}
				System.out.println();
			}

			for (int j = 0; j < line.size(); j++) {

				String f = line.get(j);
				if (f == null) f = "";
				int gap1 = (int) Math.ceil(perpiece / 2f - f.length() / 2f);
				int gap2 = (int) Math.floor(perpiece / 2f - f.length() / 2f);

				for (int k = 0; k < gap1; k++) {
					System.out.print(" ");
				}
				System.out.print(f);
				for (int k = 0; k < gap2; k++) {
					System.out.print(" ");
				}
			}
			System.out.println();

			perpiece /= 2;

		}
		return;
	}
}