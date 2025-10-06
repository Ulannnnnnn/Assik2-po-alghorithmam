package algorithms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MaxHeapTest {

    private MaxHeap heap;

    @BeforeEach
    void setUp() {
        heap = new MaxHeap(10);
    }

    @Test
    void testInsertAndExtractMax() {
        heap.insert(10);
        heap.insert(20);
        heap.insert(5);

        assertEquals(20, heap.extractMax());
        assertEquals(10, heap.extractMax());
        assertEquals(5, heap.extractMax());
    }

    @Test
    void testIncreaseKey() {
        heap.insert(10);
        heap.insert(15);
        heap.insert(5);

        heap.increaseKey(2, 25); // увеличиваем ключ на индексе 2
        assertEquals(25, heap.getMax());
    }

    @Test
    void testExtractMaxFromEmptyHeap() {
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            heap.extractMax();
        });
        assertEquals("Heap is empty", exception.getMessage());
    }

    @Test
    void testHeapifyMaintainsMaxProperty() {
        heap.insert(3);
        heap.insert(8);
        heap.insert(2);
        heap.insert(10);
        heap.insert(7);

        assertEquals(10, heap.getMax());
    }

    @Test
    void testMergeTwoHeaps() {
        MaxHeap heap1 = new MaxHeap(10);
        MaxHeap heap2 = new MaxHeap(10);

        heap1.insert(10);
        heap1.insert(5);
        heap2.insert(20);
        heap2.insert(15);

        MaxHeap merged = heap1.merge(heap2);
        assertEquals(20, merged.getMax());
    }

    @Test
    void testHeapSizeAfterInsertAndExtract() {
        heap.insert(10);
        heap.insert(20);
        heap.insert(30);
        assertEquals(3, heap.size());

        heap.extractMax();
        assertEquals(2, heap.size());
    }

    @Test
    void testBuildHeapFromArray() {
        int[] arr = {3, 1, 6, 5, 2, 4};
        heap = new MaxHeap(arr);

        assertEquals(6, heap.getMax());
    }
}
