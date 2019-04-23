package com.ivan.datastructures.tree.bst

import com.ivan.datastructures.tree.BaseBinaryTree
import com.ivan.datastructures.tree.Entry

class BinarySearchTree<K: Comparable<K>, V> : BaseBinaryTree<K, V>() {

    override fun createEntry(key: K, value: V):  Entry<K, V> {
        return Entry(key, value)
    }

    override fun insert(key: K, value: V) {
        insertNode(key, value)
    }

    override fun remove(key: K): V? {
        return removeNode(key)?.value
    }
}