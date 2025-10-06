package algorithms;

import java.util.Arrays;

public class MaxHeap {
    private int[] heap;
    private int size;
    private int capacity;

    // ✅ Конструктор с заданной ёмкостью
    public MaxHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity];
    }

    // ✅ Конструктор из массива
    public MaxHeap(int[] array) {
        this.capacity = array.length;
        this.size = array.length;
        this.heap = Arrays.copyOf(array, array.length);
        buildHeap();
    }

    // ✅ Вставка элемента
    public void insert(int value) {
        if (size == capacity) {
            expandCapacity();
        }
        heap[size] = value;
        siftUp(size);
        size++;
    }

    // ✅ Извлечение максимума
    public int extractMax() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }
        int max = heap[0];
        heap[0] = heap[size - 1];
        size--;
        siftDown(0);
        return max;
    }

    // ✅ Просмотр максимума
    public int getMax() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }
        return heap[0];
    }

    // ✅ Увеличение ключа
    public void increaseKey(int index, int newValue) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Invalid index");
        }
        if (newValue < heap[index]) {
            throw new IllegalArgumentException("New value must be larger than current value");
        }
        heap[index] = newValue;
        siftUp(index);
    }

    // ✅ Построение кучи
    private void buildHeap() {
        for (int i = size / 2 - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    // ✅ Слияние двух куч
    public MaxHeap merge(MaxHeap other) {
        int[] mergedArray = new int[this.size + other.size];
        System.arraycopy(this.heap, 0, mergedArray, 0, this.size);
        System.arraycopy(other.heap, 0, mergedArray, this.size, other.size);
        return new MaxHeap(mergedArray);
    }

    // ✅ Размер
    public int size() {
        return size;
    }

    // ✅ Расширение массива
    private void expandCapacity() {
        capacity *= 2;
        heap = Arrays.copyOf(heap, capacity);
    }

    // ✅ Просеивание вверх
    private void siftUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (heap[parent] < heap[index]) {
                swap(parent, index);
                index = parent;
            } else {
                break;
            }
        }
    }

    // ✅ Просеивание вниз
    private void siftDown(int index) {
        while (index < size) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int largest = index;

            if (left < size && heap[left] > heap[largest]) {
                largest = left;
            }
            if (right < size && heap[right] > heap[largest]) {
                largest = right;
            }

            if (largest != index) {
                swap(index, largest);
                index = largest;
            } else {
                break;
            }
        }
    }

    // ✅ Обмен элементов
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // ✅ Для отладки
    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(heap, size));
    }
}
