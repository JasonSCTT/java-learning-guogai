![](https://user-gold-cdn.xitu.io/2018/3/12/16216b4d938c05ad?w=640&h=384&f=jpeg&s=72092)
本文属于学习笔记，

## 说明
- 1、本文是基于JDK 7 分析的。JDK 8 待我工作了得好好研究下。Lambda、Stream。
- 2、本文会贴出大量的官方注释文档，强迫自己学英语，篇幅比较长，还请谅解。
- 3、对算法这一块太弱鸡了，加油吧，还差得十万八千里。
- - 4、另外两个阻塞队列，先不考虑了，补完数据结构和算法再来。
## Queue
来源于网上(感谢大佬的制作)

![](https://i.imgur.com/p9KuGv7.png)


![](https://i.imgur.com/cTz8S7y.jpg)
>注：PriorityQueue和PriorityBlockingQueue不再同一个包中。

今天的主题是Queue，我们常说的一种数据结构，**队列**。

直接进入主题吧，文档里有我们需要的一切。

- 1、队列是为处理保存元素之前设计的一种集合。除基本的`Collection`操作外，还提供了额外的插入，取出，检查操作，每个方法存在两种形式，一种是操作失败抛出一个异常，另外一种是返回一个特殊的值。(根据操作的不同返回null或false)，插入操作后后一种形式是专门为容量限制(capacity-restricted)的队列实现而设计的,在大多数实现中，插入操作并不会失败。
- 2、Queues typically, but do not necessarily, order elements in a
 FIFO (first-in-first-out) manne，Among the exceptions are priority queues, which order elements according to a supplied comparator, or the elements' natural ordering, and LIFO queues (or stacks) which order the elements LIFO (last-in-first-out).
 - 3、The offer method inserts an element if possible,otherwise returning <tt>false</tt>.  This differs from the `java.util.Collection#add Collection.add` method, which can fail to add an element only by throwing **an unchecked exception.**
 - 4、The remove() and  poll() methods remove and return the **head** of the queue. Exactly which element is removed from the queue is a function of the queue's ordering policy, which differs from implementation to implementation
 - 5、The element() and peek() methods return, but do
 **not remove**, the head of the queue.
 6、The <tt>Queue</tt> interface does not define the <i>blocking queue methods</i>, which are common in concurrent programming These methods,
which wait for elements to appear or for space to become available, are
 defined in the  **java.util.concurrent.BlockingQueue** interface, which
  extends this interface.
- 7、<tt>Queue</tt> implementations generally do not allow insertion
 of **null** elements, although some implementations, such as
 LinkedList, do not prohibit(禁止) insertion of <tt>null</tt>.
- 8、Queue implementations generally do not define element-based versions of methods equalsand hashCode，but instead inherit the identity based versions
from class <tt>Object</tt>,because element-based equality is not
 always well-defined for queues with the same elements but **different
ordering properties.**
```java

/**
 * A collection designed for holding elements prior to processing.
 * Besides basic {@link java.util.Collection Collection} operations,
 * queues provide additional insertion, extraction, and inspection
 * operations.  Each of these methods exists in two forms: one throws
 * an exception if the operation fails, the other returns a special
 * value (either <tt>null</tt> or <tt>false</tt>, depending on the
 * operation).  The latter form of the insert operation is designed
 * specifically for use with capacity-restricted <tt>Queue</tt>
 * implementations; in most implementations, insert operations cannot
 * fail.
 *
 * <p>Queues typically, but do not necessarily, order elements in a
 * FIFO (first-in-first-out) manner.  Among the exceptions are
 * priority queues, which order elements according to a supplied
 * comparator, or the elements' natural ordering, and LIFO queues (or
 * stacks) which order the elements LIFO (last-in-first-out).
 * Whatever the ordering used, the <em>head</em> of the queue is that
 * element which would be removed by a call to {@link #remove() } or
 * {@link #poll()}.  In a FIFO queue, all new elements are inserted at
 * the <em> tail</em> of the queue. Other kinds of queues may use
 * different placement rules.  Every <tt>Queue</tt> implementation
 * must specify its ordering properties.
 *
 * <p>The {@link #offer offer} method inserts an element if possible,
 * otherwise returning <tt>false</tt>.  This differs from the {@link
 * java.util.Collection#add Collection.add} method, which can fail to
 * add an element only by throwing an unchecked exception.  The
 * <tt>offer</tt> method is designed for use when failure is a normal,
 * rather than exceptional occurrence, for example, in fixed-capacity
 * (or &quot;bounded&quot;) queues.
 *
 * <p>The {@link #remove()} and {@link #poll()} methods remove and
 * return the head of the queue.
 * Exactly which element is removed from the queue is a
 * function of the queue's ordering policy, which differs from
 * implementation to implementation. The <tt>remove()</tt> and
 * <tt>poll()</tt> methods differ only in their behavior when the
 * queue is empty: the <tt>remove()</tt> method throws an exception,
 * while the <tt>poll()</tt> method returns <tt>null</tt>.
 *
 * <p>The {@link #element()} and {@link #peek()} methods return, but do
 * not remove, the head of the queue.
 *
 * <p>The <tt>Queue</tt> interface does not define the <i>blocking queue
 * methods</i>, which are common in concurrent programming.  These methods,
 * which wait for elements to appear or for space to become available, are
 * defined in the {@link java.util.concurrent.BlockingQueue} interface, which
 * extends this interface.
 *
 * <p><tt>Queue</tt> implementations generally do not allow insertion
 * of <tt>null</tt> elements, although some implementations, such as
 * {@link LinkedList}, do not prohibit insertion of <tt>null</tt>.
 * Even in the implementations that permit it, <tt>null</tt> should
 * not be inserted into a <tt>Queue</tt>, as <tt>null</tt> is also
 * used as a special return value by the <tt>poll</tt> method to
 * indicate that the queue contains no elements.
 *
 * <p><tt>Queue</tt> implementations generally do not define
 * element-based versions of methods <tt>equals</tt> and
 * <tt>hashCode</tt> but instead inherit the identity based versions
 * from class <tt>Object</tt>, because element-based equality is not
 * always well-defined for queues with the same elements but different
 * ordering properties.

 * @author Doug Lea
 * @param <E> the type of elements held in this collection
 */
public interface Queue<E> extends Collection<E> {...}

```
我们拿下具体有哪些方法
```java
/**
 * Inserts the specified element into this queue if it is possible to do so
 * immediately without violating(违反) capacity restrictions, returning
 * <tt>true</tt> upon success and throwing an <tt>IllegalStateException</tt>
 * if no space is currently available.
 *
 * @param e the element to add
 * @return <tt>true</tt> (as specified by {@link Collection#add}) //注意
 * @throws IllegalStateException if the element cannot be added at this
 *         time due to capacity restrictions（容量限制）
 * @throws ClassCastException if the class of the specified element
 *         prevents(阻止) it from being added to this queue
 * @throws NullPointerException if the specified element is null and
 *         this queue does not permit null elements
 * @throws IllegalArgumentException if some property of this element
 *         prevents it from being added to this queue
 */
boolean add(E e);

/**
 * Inserts the specified element into this queue if it is possible to do
 * so immediately without violating(违反) capacity restrictions.
 * When using a capacity-restricted queue, this method is generally
 * preferable to {@link #add}, which can fail to insert an element only
 * by throwing an exception.
 *
 * @param e the element to add
 * @return <tt>true</tt> if the element was added to this queue, else
 *         <tt>false</tt>
 * @throws ClassCastException if the class of the specified element
 *         prevents it from being added to this queue
 * @throws NullPointerException if the specified element is null and
 *         this queue does not permit null elements
 * @throws IllegalArgumentException if some property of this element
 *         prevents it from being added to this queue
 */
boolean offer(E e);

/**
 * Retrieves and removes the head of this queue.  This method differs
 * from {@link #poll poll} only in that it throws an exception if this
 * queue is empty.
 *
 * @return the head of this queue
 * @throws NoSuchElementException if this queue is empty
 */
E remove();

/**
 * Retrieves and removes the head of this queue,
 * or returns <tt>null</tt> if this queue is empty.
 *
 * @return the head of this queue, or <tt>null</tt> if this queue is empty
 */
E poll();

/**
 * Retrieves, but does not remove, the head of this queue.  This method
 * differs from {@link #peek peek} only in that it throws an exception
 * if this queue is empty.
 *
 * @return the head of this queue
 * @throws NoSuchElementException if this queue is empty //注意，此时会抛出空指针异常
 */
E element();

/**
 * Retrieves, but does not remove, the head of this queue,这里只是取出，并不移除。
 * or returns <tt>null</tt> if this queue is empty.
 *
 * @return the head of this queue, or <tt>null</tt> if this queue is empty
 */
E peek();
```

接着，我们看看抽象的Queue具体是怎么实现的，注意，此接口继承了`AbstractCollection`,然后才实现类`Queue`接口。
```java
/**
 * This class provides skeletal implementations of some {@link Queue}
 * operations. The implementations in this class are appropriate when
 * the base implementation does <em>not</em> allow <tt>null</tt>
 * elements.
 * @author Doug Lea
 * @param <E> the type of elements held in this collection
 */
public abstract class AbstractQueue<E>
    extends AbstractCollection<E>
    implements Queue<E> {

    /**
     * Constructor for use by subclasses.
     */
    protected AbstractQueue() {
    }

    /**
     * Inserts the specified element into this queue if it is possible to do so
     * immediately without violating capacity restrictions, returning
     * <tt>true</tt> upon success and throwing an <tt>IllegalStateException</tt>
     * if no space is currently available.
     * @param e the element to add
     */
    public boolean add(E e) {
        if (offer(e))            //调用offer
            return true;
        else
            throw new IllegalStateException("Queue full");
    }

    /**
     * Retrieves and removes the head of this queue.  This method differs
     * from {@link #poll poll} only in that it throws an exception if this
     * queue is empty.
     *
     * <p>This implementation returns the result of <tt>poll</tt>
     * unless the queue is empty.
     *
     * @return the head of this queue
     * @throws NoSuchElementException if this queue is empty
     */
    public E remove() {
        E x = poll();
        if (x != null)
            return x;
        else
            throw new NoSuchElementException();
    }

    /**
     * Retrieves, but does not remove, the head of this queue.  This method
     * differs from {@link #peek peek} only in that it throws an exception if
     * this queue is empty.
     *
     * <p>This implementation returns the result of <tt>peek</tt>
     * unless the queue is empty.
     *
     * @return the head of this queue
     * @throws NoSuchElementException if this queue is empty
     */
    public E element() {
        E x = peek();
        if (x != null)       //unless the queue is empty
            return x;
        else
            throw new NoSuchElementException();
    }

    /**
     * Removes all of the elements from this queue.
     * The queue will be empty after this call returns.
     *
     * <p>This implementation repeatedly invokes {@link #poll poll} until it
     * returns <tt>null</tt>.
     */
    public void clear() {
        while (poll() != null);     //直到变为null
    }

    /**
     * Adds all of the elements in the specified collection to this
     * queue.  Attempts to addAll of a queue to itself result in
     * <tt>IllegalArgumentException</tt>. Further, the behavior of
     * this operation is undefined if the specified collection is
     * modified while the operation is in progress.
     *
     * <p>This implementation iterates over the specified collection,
     * and adds each element returned by the iterator to this
     * queue, in turn(依次).  A runtime exception encountered while
     * trying to add an element (including, in particular, a
     * <tt>null</tt> element) may result in only(仅有) some of the elements
     * having been successfully added when the associated exception is
     * thrown.
     *
     * @param c collection containing elements to be added to this queue
     */
    public boolean addAll(Collection<? extends E> c) {
        if (c == null)
            throw new NullPointerException();
        if (c == this)                               //不能添加自己
            throw new IllegalArgumentException();
        boolean modified = false;
        for (E e : c)
            if (add(e))                 //这里是根据迭代器返回的顺序添加进去的，。。
                modified = true;        //此标记，
        return modified;
    }
}
```
接下来我们看看`PriorityQueue`

- 1、基于优先堆的无界优先级队列，优先队列的元素根据`Comparable`自然顺序，或者在队列构造时提供的`Comparator`，取决于构造器是否使用。 A priority queue does not permit ** null** elements.依赖于自然顺序的优先级也不允许插入不可对比的Object
- 2、该队列的头是最小值，与指定的排序顺序有关，If multiple elements are tied for least value, the head is one of those elements.
- 3、优先级队列是无界限的，但其内部有个`capacity`用于管理存储队列元素数组的大小。It is always at least as large as the queue size.
- 4、**Note that this implementation is not synchronized.** Multiple threads should not access a  PriorityQueue}instance concurrently if any of the threads modifies the queue. Instead, use the **thread-safe**
 `java.util.concurrent.PriorityBlockingQueue`class.
- 5、Implementation note: this implementation provides **O(log(n))** time for the enqueing and dequeing methods( offer,poll, remove(), add); **linear time**(线性) for the  remove(Object) and contains(Object) methods; and **constant time**(常数) for the retrieval methods
({@code peek}, {@code element}, and {@code size}).

```java
/**
 * An unbounded priority {@linkplain Queue queue} based on a priority heap.
 * The elements of the priority queue are ordered according to their
 * {@linkplain Comparable natural ordering}, or by a {@link Comparator}
 * provided at queue construction time, depending on which constructor is
 * used.  A priority queue does not permit {@code null} elements.
 * A priority queue relying on natural ordering also does not permit
 * insertion of non-comparable objects (doing so may result in
 * {@code ClassCastException}).
 *
 * <p>The <em>head</em> of this queue is the <em>least</em> element
 * with respect to the specified ordering.  If multiple elements are
 * tied for least value, the head is one of those elements -- ties are
 * broken arbitrarily.  The queue retrieval operations {@code poll},
 * {@code remove}, {@code peek}, and {@code element} access the
 * element at the head of the queue.
 *
 * <p>A priority queue is unbounded, but has an internal
 * <i>capacity</i> governing the size of an array used to store the
 * elements on the queue.  It is always at least as large as the queue
 * size.  As elements are added to a priority queue, its capacity
 * grows automatically.  The details of the growth policy are not
 * specified.
 *
 * <p>This class and its iterator implement all of the
 * <em>optional</em> methods of the {@link Collection} and {@link
 * Iterator} interfaces.  The Iterator provided in method {@link
 * #iterator()} is <em>not</em> guaranteed to traverse the elements of
 * the priority queue in any particular order. If you need ordered
 * traversal, consider using {@code Arrays.sort(pq.toArray())}.
 *
 * <p> <strong>Note that this implementation is not synchronized.</strong>
 * Multiple threads should not access a {@code PriorityQueue}
 * instance concurrently if any of the threads modifies the queue.
 * Instead, use the thread-safe {@link
 * java.util.concurrent.PriorityBlockingQueue} class.
 *
 * <p>Implementation note: this implementation provides
 * O(log(n)) time for the enqueing and dequeing methods
 * ({@code offer}, {@code poll}, {@code remove()} and {@code add});
 * linear time for the {@code remove(Object)} and {@code contains(Object)}
 * methods; and constant time for the retrieval methods
 * ({@code peek}, {@code element}, and {@code size}).
 *
 * <p>This class is a member of the
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Java Collections Framework</a>.
 *
 * @since 1.5
 * @author Josh Bloch, Doug Lea
 * @param <E> the type of elements held in this collection
 */
public class PriorityQueue<E> extends AbstractQueue<E>
    implements java.io.Serializable {
```

具体看看里面有怎么定义的。

Priority queue represented as a balanced binary heap: **the two children of queue[n] are queue[2*n+1] and queue[2*(n+1)].**
![](https://i.imgur.com/BhAG7VW.png)

本文图片来自[这里](http://blog.csdn.net/mbmispig/article/details/78754755)

```java
leftNo = parentNo*2+1

rightNo = parentNo*2+2

parentNo = (nodeNo-1)/2
```


`private static final int DEFAULT_INITIAL_CAPACITY = 11;`

```java

/**
 * Priority queue represented as a balanced binary heap(平衡二叉堆): the two
 * children of queue[n] are queue[2*n+1] and queue[2*(n+1)].  The
 * priority queue is ordered by comparator, or by the elements'
 * natural ordering, if comparator is null: For each node n in the
 * heap and each descendant d of n, n <= d.  The element with the
 * lowest value is in queue[0], assuming the queue is nonempty.
 */
private transient Object[] queue;

/**
 * The number of elements in the priority queue.
 */
private int size = 0;

/**
 * The comparator, or null if priority queue uses elements'
 * natural ordering.(自然排序)
 */
private final Comparator<? super E> comparator;

/**
 * The number of times this priority queue has been
 * <i>structurally modified</i>.  See AbstractList for gory details.
 * 老外也会偷懒
 */
private transient int modCount = 0;

```
关于modCount变量的解释

需要注意的是这个异常，`ConcurrentModificationException`并发修改异常，这里比较这两个变量，能够判断ArrayList的结构是否被改变，比如是否在进行变量的时候添加或者删除了元素。 因为实现Iterator接口是一个fail-fast iterator，在进行遍历集合的时候如果集合的内容发生了变化，在进行遍历 也是不准确的了，所以会抛出这个异常。ArrayList 通过记录 modCount 参数来实现。在面对并发的修改时，迭代器很快就会完全失败，而不是冒着在将来某个不确定时间发生任意不确定行为的风险(参考别人的)

```java
private void checkForComodification() {
    if (ArrayList.this.modCount != this.modCount)
        throw new ConcurrentModificationException();
}
/**
 * The number of times this list has been <i>structurally modified</i>.
 * Structural modifications are those that change the size of the
 * list, or otherwise perturb it in such a fashion that iterations in
 * progress may yield incorrect results.  (List被修改的次数，在add、remove时会+1)
 */
protected transient int modCount = 0;
```

注意默认的初始化容量是11，
```java
/**
 * Creates a {@code PriorityQueue} with the default initial
 * capacity (11) that orders its elements according to their
 * {@linkplain Comparable natural ordering}.//默认
 */
public PriorityQueue() {
    this(DEFAULT_INITIAL_CAPACITY, null);
}

/**
 * Creates a {@code PriorityQueue} with the specified initial
 * capacity that orders its elements according to their
 * {@linkplain Comparable natural ordering}.//默认
 *
 * @param initialCapacity the initial capacity for this priority queue
 * @throws IllegalArgumentException if {@code initialCapacity} is less
 *         than 1
 */
public PriorityQueue(int initialCapacity) {
    this(initialCapacity, null);
}

/**
 * Creates a {@code PriorityQueue} with the specified initial capacity
 * that orders its elements according to the specified comparator.
 *
 * @param  initialCapacity the initial capacity for this priority queue
 * @param  comparator the comparator that will be used to order this
 *         priority queue.  If {@code null}, the {@linkplain Comparable
 *         natural ordering} of the elements will be used.//注意
 * @throws IllegalArgumentException if {@code initialCapacity} is
 *         less than 1
 */
public PriorityQueue(int initialCapacity,
                     Comparator<? super E> comparator) {
    // Note: This restriction of at least one is not actually needed,
    // but continues for 1.5 compatibility(兼容)
    if (initialCapacity < 1)
        throw new IllegalArgumentException();
    this.queue = new Object[initialCapacity];
    this.comparator = comparator;
}

/**
 * Creates a {@code PriorityQueue} containing the elements in the
 * specified collection.  If the specified collection is an instance of
 * a {@link SortedSet} or is another {@code PriorityQueue}, this
 * priority queue will be ordered according to the same ordering.(注意)
 * Otherwise, this priority queue will be ordered according to the
 * {@linkplain Comparable natural ordering} of its elements.
 *
 * @param  c the collection whose elements are to be placed(放)
 *         into this priority queue
 * @throws ClassCastException if elements of the specified collection
 *         cannot be compared to one another according to the priority
 *         queue's ordering
 * @throws NullPointerException if the specified collection or any
 *         of its elements are null
 */
public PriorityQueue(Collection<? extends E> c) {
    if (c instanceof SortedSet<?>) {
        SortedSet<? extends E> ss = (SortedSet<? extends E>) c;            //需要把c转为SortedSet
        this.comparator = (Comparator<? super E>) ss.comparator();
        initElementsFromCollection(ss);           //be ordered according to the same ordering
    }
    else if (c instanceof PriorityQueue<?>) {
        PriorityQueue<? extends E> pq = (PriorityQueue<? extends E>) c;   //PriorityQueue
        this.comparator = (Comparator<? super E>) pq.comparator();        //some ordering
        initFromPriorityQueue(pq);
    }
    else {
        this.comparator = null;
        initFromCollection(c);                     //每次初始化调用不同的方法，
    }
}

/**
 * Creates a {@code PriorityQueue} containing the elements in the
 * specified priority queue.  This priority queue will be
 * ordered according to the same ordering as the given priority(注意)
 * queue.
 *
 * @param  c the priority queue whose elements are to be placed
 *         into this priority queue
 * @throws ClassCastException if elements of {@code c} cannot be
 *         compared to one another according to {@code c}'s
 *         ordering
 * @throws NullPointerException if the specified priority queue or any
 *         of its elements are null                 //为空则抛异常
 */
public PriorityQueue(PriorityQueue<? extends E> c) {
    this.comparator = (Comparator<? super E>) c.comparator();
    initFromPriorityQueue(c);
}

/**
 * Creates a {@code PriorityQueue} containing the elements in the
 * specified sorted set.   This priority queue will be ordered
 * according to the same ordering as the given sorted set.
 *
 * @param  c the sorted set whose elements are to be placed
 *         into this priority queue
 * @throws ClassCastException if elements of the specified sorted
 *         set cannot be compared to one another according to the
 *         sorted set's ordering
 * @throws NullPointerException if the specified sorted set or any
 *         of its elements are null
 */
public PriorityQueue(SortedSet<? extends E> c) {
    this.comparator = (Comparator<? super E>) c.comparator();
    initElementsFromCollection(c);
}

```

**If the specified collection is an instance of a {@link SortedSet} or is another {@code PriorityQueue}, this priority queue will be ordered according to the same ordering.**

接下来我们具体看看这三个方法有什么不同

```java

private void initFromPriorityQueue(PriorityQueue<? extends E> c) {
    if (c.getClass() == PriorityQueue.class) {
        this.queue = c.toArray();      //Arrays.copyOf(queue, size);
        this.size = c.size();
    } else {
        initFromCollection(c);
    }
}
/**
 * Initializes queue array with elements from the given Collection.
 *
 * @param c the collection
 */
private void initFromCollection(Collection<? extends E> c) {
    initElementsFromCollection(c);
    heapify();                              //此方法待会再看，
}

private void initElementsFromCollection(Collection<? extends E> c) {
    Object[] a = c.toArray();
    // If c.toArray incorrectly doesn't return Object[], copy it.  //这是一个bug，
    if (a.getClass() != Object[].class)
        a = Arrays.copyOf(a, a.length, Object[].class);
    int len = a.length;
    if (len == 1 || this.comparator != null)
        for (int i = 0; i < len; i++)
            if (a[i] == null)
                throw new NullPointerException();
    this.queue = a;
    this.size = a.length;
}
```
### grow

```java
/**
    * The maximum size of array to allocate.
    * Some VMs reserve some header words in an array.
    * Attempts to allocate larger arrays may result in
    * OutOfMemoryError: Requested array size exceeds VM limit
    */
   private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;   //MAX_VALUE = 0x7fffffff;

   /**
    * Increases the capacity of the array.
    *
    * @param minCapacity the desired minimum capacity
    */
   private void grow(int minCapacity) {
       int oldCapacity = queue.length;
       // Double size if small; else grow by 50%
       int newCapacity = oldCapacity + ((oldCapacity < 64) ?
                                        (oldCapacity + 2) :
                                        (oldCapacity >> 1));
       // overflow-conscious code
       if (newCapacity - MAX_ARRAY_SIZE > 0)
           newCapacity = hugeCapacity(minCapacity);
       queue = Arrays.copyOf(queue, newCapacity);     //底层使用System.arryscopy()
   }

   private static int hugeCapacity(int minCapacity) {
       if (minCapacity < 0) // overflow
           throw new OutOfMemoryError();
       return (minCapacity > MAX_ARRAY_SIZE) ?
           Integer.MAX_VALUE :
           MAX_ARRAY_SIZE;
   }
```
### offer
1、When using a capacity-restricted queue, this method(offer) is generally preferable to {@link #add}, which can fail to insert an element only by throwing an exception.
2、This class and its iterator implement all of the <em>optional</em> methods of the {@link Collection} and {@link  Iterator} interfaces.  The Iterator provided in method {@link  #iterator()} is <em>not</em> guaranteed(确保) to traverse the elements of the priority queue in any particular order. If you need ordered traversal, consider using `Arrays.sort(pq.toArray())` .

```java
/**
     * Inserts the specified element into this priority queue.
     *
     * @return {@code true} (as specified by {@link Collection#add}) //Note
     * @throws ClassCastException if the specified element cannot be
     *         compared with elements currently in this priority queue
     *         according to the priority queue's ordering
     * @throws NullPointerException if the specified element is null
     */
    public boolean add(E e) {
        return offer(e);                        //Note：1
    }

    /**
     * Inserts the specified element into this priority queue.
     *
     * @return {@code true} (as specified by {@link Queue#offer})
     * @throws ClassCastException if the specified element cannot be
     *         compared with elements currently in this priority queue
     *         according to the priority queue's ordering      //Note
     * @throws NullPointerException if the specified element is null
     */
    public boolean offer(E e) {
        if (e == null)
            throw new NullPointerException();
        modCount++;                              //Note，2
        int i = size;
        if (i >= queue.length)
            grow(i + 1);                          // Double size if small; else grow by 50%   <64
        size = i + 1;
        if (i == 0)                               //队列原来为空，这里插入第一个元素
            queue[0] = e;
        else
            siftUp(i, e);                        //插入元素，并维持堆的特性
        return true;
    }

```
![](https://i.imgur.com/nKeGZxd.png)

```java
/**
 * Inserts item x at position k, maintaining heap invariant by
 * demoting x down the tree repeatedly until it is less than or
 * equal to its children or is a leaf.
 *
 * @param k the position to fill
 * @param x the item to insert
 */
private void siftDown(int k, E x) {
    if (comparator != null)
        siftDownUsingComparator(k, x);
    else
        siftDownComparable(k, x);
}

private void siftDownComparable(int k, E x) {
    Comparable<? super E> key = (Comparable<? super E>)x;
    int half = size >>> 1;        // loop while a non-leaf
    while (k < half) {
        int child = (k << 1) + 1; // assume left child is least
        Object c = queue[child];
        int right = child + 1;
        if (right < size &&
            ((Comparable<? super E>) c).compareTo((E) queue[right]) > 0)
            c = queue[child = right];
        if (key.compareTo((E) c) <= 0)
            break;
        queue[k] = c;
        k = child;
    }
    queue[k] = key;
}

private void siftDownUsingComparator(int k, E x) {
    int half = size >>> 1;
    while (k < half) {
        int child = (k << 1) + 1;
        Object c = queue[child];
        int right = child + 1;
        if (right < size &&
            comparator.compare((E) c, (E) queue[right]) > 0)
            c = queue[child = right];
        if (comparator.compare(x, (E) c) <= 0)
            break;
        queue[k] = c;
        k = child;
    }
    queue[k] = x;
}

```



这里有些难理解，等学完数据结构回头再看。

```java
private int indexOf(Object o) {                //元素首次出现的索引
    if (o != null) {
        for (int i = 0; i < size; i++)
            if (o.equals(queue[i]))
                return i;
    }
    return -1;                                   //-1
}


### peek

![](https://i.imgur.com/NPaNX2T.png)
```java

public E peek() {
    if (size == 0)
        return null;                           //Note null。
    return (E) queue[0];                      //取出最小的，但不删除
}
```

### poll
![](https://i.imgur.com/k1fhKnY.png)


```java
public E poll() {
    if (size == 0)
        return null;
    int s = --size;
    modCount++;
    E result = (E) queue[0];
    E x = (E) queue[s];
    queue[s] = null;
    if (s != 0)
        siftDown(0, x);       //调正
    return result;
}
```
### remove
![](https://i.imgur.com/0ei7WbA.png)
```java
/**
 * Removes a single instance of the specified element from this queue,
 * if it is present.  More formally, removes an element {@code e} such
 * that {@code o.equals(e)}, if this queue contains one or more such
 * elements.  Returns {@code true} if and only if this queue contained
 * the specified element (or equivalently, if this queue changed as a
 * result of the call).
 *
 * @param o element to be removed from this queue, if present
 * @return {@code true} if this queue changed as a result of the call
 */
public boolean remove(Object o) {
    int i = indexOf(o);
    if (i == -1)
        return false;
    else {
        removeAt(i);
        return true;
    }
}
/**
 * Removes the ith element from queue.
 *
 * Normally this method leaves the elements at up to i-1,
 * inclusive, untouched.  Under these circumstances, it returns
 * null.  Occasionally, in order to maintain the heap invariant,
 * it must swap a later element of the list with one earlier than
 * i.  Under these circumstances, this method returns the element
 * that was previously at the end of the list and is now at some
 * position before i. This fact is used by iterator.remove so as to
 * avoid missing traversing elements.
 */
private E removeAt(int i) {
    assert i >= 0 && i < size;
    modCount++;
    int s = --size;
    if (s == i) // removed last element
        queue[i] = null;
    else {
        E moved = (E) queue[s];
        queue[s] = null;
        siftDown(i, moved);
        if (queue[i] == moved) {
            siftUp(i, moved);
            if (queue[i] != moved)
                return moved;
        }
    }
    return null;
}

```
### 其他
```java
private int indexOf(Object o) {                //元素首次出现的索引
    if (o != null) {
        for (int i = 0; i < size; i++)
            if (o.equals(queue[i]))
                return i;
    }
    return -1;                                   //-1
}
/**
 * Returns {@code true} if this queue contains the specified element.
 * More formally, returns {@code true} if and only if this queue contains
 * at least one element {@code e} such that {@code o.equals(e)}.
 *
 * @param o object to be checked for containment in this queue
 * @return {@code true} if this queue contains the specified element
 */
public boolean contains(Object o) {
    return indexOf(o) != -1;
}
public int size() {
    return size;
}

/**
 * Removes all of the elements from this priority queue.
 * The queue will be empty after this call returns.
 */
public void clear() {
    modCount++;                                  //最后通过对比此常量，
    for (int i = 0; i < size; i++)
        queue[i] = null;
    size = 0;
}
```
