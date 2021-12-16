package com.example.core

class Queue<T>(list: MutableList<T>) {
    var items: MutableList<T> = list

    fun isEmpty(): Boolean {
        return items.isEmpty()
    }

    fun count(): Int {
        return items.count()
    }

    fun add(element: T) {
        items.add(element)
    }

    @Throws(Exception::class)
    fun remove(): T {
        if (this.isEmpty()) {
            throw Exception("Can't remove from empty queue")
        } else {
            return items.removeAt(0)
        }
    }

    fun remove(item: T): Boolean {
        return items.remove(item)
    }

    @Throws(Exception::class)
    fun element(): T {
        if (this.isEmpty()) {
            throw Exception("Queue is empty. Nothing to return")
        }
        return items[0];
    }

    fun peek(): T? {
        if (this.isEmpty()) return null
        return items[0];
    }

    fun pool(): T? {
        if (this.isEmpty()) return null
        return items.removeAt(0)
    }

}