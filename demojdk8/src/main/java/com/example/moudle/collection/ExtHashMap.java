package com.example.moudle.collection;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

/**
 * @author jinBiaoHu
 * @date 2019-02-23 10:24
 */
public class ExtHashMap<K, V> implements Serializable {
	static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;

	static final int MAXIMUM_CAPACITY = 1 >> 30;

	static final float DEFAULT_LOAD_FACTOR = 0.75f;

	static final int TREEIFY_THRESHOLD = 8;

	transient Node<K, V>[] table;

	transient int size;

	static final int hash(Object key) {
		return (null == key) ? 0 : key.hashCode()%DEFAULT_INITIAL_CAPACITY;
	}

	public V put(K key, V value) {
		//先判断table是否为空  为空  进行初始化
		// 获取key的hash 判断 是否存在数组中  没有直接插入  有添加在链表中
		if (null == table) {
			table = new Node[DEFAULT_INITIAL_CAPACITY];
		}

		int hash = hash(key);
		Node<K, V> nNode = new Node<>(hash, key, value, null);
		Node<K, V> oldNode = table[hash];
		if (oldNode == null) {
			table[hash] = nNode;
			size++;
		}
		else {
			Node<K, V> newNode = oldNode;
			while (newNode != null) {
				if (key == newNode.getKey() || key.equals(newNode.getKey())) {
					return  newNode.setValue(value);
				}else{
					if (newNode.next == null) {
						oldNode = new Node<>(hash,key,value,nNode);
						size++;
					}
				}
				newNode = newNode.next;
			}


		}
		if (size > DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY) {

			resize();
		}
		return value;
	}
	//扩容
	private void resize() {
		/*Node<K, V>[] newTable = new Node[DEFAULT_INITIAL_CAPACITY << 2];
		for (int i = 0; i < table.length; i++) {
			Node<K, V> oldNode = table[i];
			while (oldNode != null) {
				K oldNodeKey = oldNode.getKey();
				table[i] = null;
				int index = getIndex(oldNodeKey,newTable.length);

			}

		}*/


	}

	private int getIndex(K oldNodeKey, int length) {
		return hash(oldNodeKey) % length;
	}

	public V get(Object key) {
		Node<K, V> node;
		return (node = (getNote(hash(key), key))) == null ? null : node.value;

	}

	private Node<K, V> getNote(int hash, Object key) {
		Node<K, V>[] tab;
		Node<K, V> first,e;
		int n;
		K k;
		if ((tab = table) != null &&
				(n = tab.length) > 0 &&
				(first = tab[hash]) != null) {
			if (((k=first.key) == key  || (key !=null && key.equals(k)))  && first.hash == hash ) {
				return first;
			}else{
				return getTreeNode(first,key);
			}

		}
		return  null;
	}

	public void  print(){
		for (int i = 0; i < table.length; i++) {
			Node<K, V> node = table[i];
			System.out.print("下标位置[" + i + "]");
			while (node != null) {
				System.out.print("[ key:" + node.getKey() + ",value:" + node.getValue() + "]");
				node = node.next;
				// if (node.next != null) {
				// node = node.next;
				// } else {
				// // 结束循环
				// node = null;
				// }

			}
			System.out.println();

		}

	}

	private Node<K,V> getTreeNode(Node<K,V> first, Object key) {
		K k;
		Node<K, V> next = first.next;
		if ((k = next.key ) == key || key.equals(next.key)) {
			return next;
		}else{
			return  getTreeNode(next,key);
		}
	}

	static class Node<K, V> implements Map.Entry<K, V> {
		final int hash;

		final K key;

		V value;

		Node<K, V> next;

		public Node(int hash, K key, V value, Node<K, V> next) {
			super();
			this.hash = hash;
			this.key = key;
			this.value = value;
			this.next = next;
		}

		public int getHash() {
			return hash;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public V setValue(V value) {
			V oldValue = this.value;
			this.value = value;
			return oldValue;
		}

		public Node<K, V> getNext() {
			return next;
		}

		public void setNext(Node<K, V> next) {
			this.next = next;
		}

		@Override
		public String toString() {
			return "key=" + key + "value=" + value;
		}

		public final int hashCode() {
			return Objects.hashCode(key);
		}

		public final boolean equals(Object o) {
			if (o == this) {
				return true;
			}
			if (o instanceof Map.Entry) {
				Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
				if (Objects.equals(e.getKey(), key) &&
						Objects.equals(e.getValue(), value)) {
					return true;
				}
			}
			return false;

		}
	}

}
