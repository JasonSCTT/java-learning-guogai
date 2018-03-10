## 容器相关的操作及其源码分析

## 说明
- 1、本文是基于JDK 7 分析的。JDK 8 待我工作了得好好研究下。Lambda、Stream。
- 2、本文会贴出大量的官方注释文档，强迫自己学英语，篇幅比较长，还请谅解。
- 3、笔记放[github](https://github.com/guoxiaoxu/java-learning-guogai/tree/master/jdk7u-jdk/note)了，有兴趣的可以看看。喜欢的可以点个star。
- 4、读过源码的可以快速浏览一遍，也能加深自己的理解。
- 5、源码是个好东东，各种编码技巧，再次佩服老外！！！
- 6、既然官方文档简单明了，为啥还要多此一举呢？有删除写中文的时间还不如好好学英文！

### LinkedList
LinkedList是List的链表实现，内部使用双向链表，即每个节点保存前后指针。因为链表的特性，不需要提前设置好空间，所以不需要扩容机制。基于链表实现的方式使得LinkedList在插入和删除时更优于ArrayList，而随机访问则比ArrayList逊色些。

本文暂时不考录Queue，下下次单独分析。这个Queue也是重点，各种阻塞队列，优先级队列。有兴趣的可以看看我之前的笔记[基于LinkedBlockingQueue实现股票交易系统](https://juejin.im/post/5a87f78a5188257a865d782f)

来源于网上(感谢大佬的制作)

![](https://i.imgur.com/p9KuGv7.png)

图二直接在IDEA中右击，或者在keymap中搜索UML，类图也非常非常重要。默认是`Alt + Ctrl + U`

![](https://i.imgur.com/F7c2j2W.jpg)

正式进入`LinkedList`之前，我们先来看看AbstractList的另一个实现类`AbstractSequentialList`补充上一篇留下的问题,同样`LinkedList`继承自此抽象类，同时实现List接口。

`"sequential access" data store `顺序访问存储啊，对于数组随机访问，应该优先使用此类。

在`Collections` 中我们介绍了 `RandomAccess`，里面提到，支持 RandomAccess 的对象，遍历时使用 indexedBinarySearch 比 迭代器更快。

而 AbstractSequentialList 只支持迭代器按顺序 访问，不支持 RandomAccess，所以遍历 AbstractSequentialList 的子类，使用 for 循环 get() 的效率要 <= 迭代器遍历：

注意log(n),如果没有实现RandomAcces接口，或者太大，则查询为`O(n)`,`O(log n)`比较

```java
/**
 * <p>This method runs in log(n) time for a "random access" list (which
 * provides near-constant-time positional access).  If the specified list
 * does not implement the {@link RandomAccess} interface and is large,
 * this method will do an iterator-based binary search that performs
 * O(n) link traversals and O(log n) element comparisons.
 */
public static <T>
int binarySearch(List<? extends Comparable<? super T>> list, T key) {
    if (list instanceof RandomAccess || list.size()<BINARYSEARCH_THRESHOLD)
        return Collections.indexedBinarySearch(list, key);
    else
        return Collections.iteratorBinarySearch(list, key);
}
```

解释有些多，但都很关键。具体的看LinkedList里面的吧
```java
/**
 * This class provides a skeletal implementation of the <tt>List</tt>
 * interface to minimize the effort required to implement this interface
 * backed by a "sequential access" data store (such as a linked list).  For
 * random access data (such as an array), <tt>AbstractList</tt> should be used
 * in preference to this class.<p>
 *
 * This class is the opposite of the <tt>AbstractList</tt> class in the sense
 * that it implements the "random access" methods (<tt>get(int index)</tt>,
 * <tt>set(int index, E element)</tt>, <tt>add(int index, E element)</tt> and
 * <tt>remove(int index)</tt>) on top of the list's list iterator, instead of
 * the other way around.（原因）


  * To implement a list the programmer needs only to extend this class and
  * provide implementations for the <tt>listIterator</tt> and <tt>size</tt>
  * methods.
  *
  *
  * The programmer should generally provide a void (no argument) and collection
 * constructor, as per the recommendation in the <tt>Collection</tt> interface
 * specification.<p>
 */

public abstract class AbstractSequentialList<E> extends AbstractList<E> {

    protected AbstractSequentialList() {
    }
    public E get(int index) {
        try {
            return listIterator(index).next();
        } catch (NoSuchElementException exc) {
            throw new IndexOutOfBoundsException("Index: "+index);
        }
    }

    public E set(int index, E element) {  }


    public void add(int index, E element) {}


    public E remove(int index) {  }

    public boolean addAll(int index, Collection<? extends E> c) {...}

    public Iterator<E> iterator() {..}

    public abstract ListIterator<E> listIterator(int index);
}

```
正式介绍前，先看一张图，
![](https://i.imgur.com/PeymCkQ.jpg)


- `Doubly-linked list`、

- `including {@code null}`、
- `will traverse the list from the beginning or the end, whichever is closer to the specified index.`
- `Note that this implementation is not synchronized`
- `If multiple threads access a linked list concurrently, and at least one of the threads modifies the list structurally, it <i>must</i> be synchronized externally `

- `If no such object exists, the list should be "wrapped" using the Collections#synchronizedList Collections.synchronizedList method.  `

`List list = Collections.synchronizedList(new LinkedList(...));`

The iterators returned by this class's iterator and listIterator methods are **fail-fast**:the iterator will throw a  ConcurrentModificationException

抽了这么多，只想说明一点：**English is very important**

PS：英文我可以看懂的就不在写中文注释了。

```java
/**
 * Doubly-linked list implementation of the {@code List} and {@code Deque}
 * interfaces.  Implements all optional list operations, and permits all
 * elements (including {@code null}).
 *
 * <p>All of the operations perform as could be expected for a doubly-linked
 * list.  Operations that index into the list will traverse the list from
 * the beginning or the end, whichever is closer to the specified index.
 *
 * <p><strong>Note that this implementation is not synchronized.</strong>
 * If multiple threads access a linked list concurrently, and at least
 * one of the threads modifies the list structurally, it <i>must</i> be
 * synchronized externally.  (A structural modification is any operation
 * that adds or deletes one or more elements; merely setting the value of
 * an element is not a structural modification.)  This is typically
 * accomplished by synchronizing on some object that naturally
 * encapsulates the list.
 *
 * If no such object exists, the list should be "wrapped" using the
 * {@link Collections#synchronizedList Collections.synchronizedList}
 * method.  This is best done at creation time, to prevent accidental
 * unsynchronized access to the list:<pre>
 *   List list = Collections.synchronizedList(new LinkedList(...));</pre>
 *
 * <p>The iterators returned by this class's {@code iterator} and
 * {@code listIterator} methods are <i>fail-fast</i>: if the list is
 * structurally modified at any time after the iterator is created, in
 * any way except through the Iterator's own {@code remove} or
 * {@code add} methods, the iterator will throw a {@link
 * ConcurrentModificationException}.  Thus, in the face of concurrent
 * modification, the iterator fails quickly and cleanly, rather than
 * risking arbitrary, non-deterministic behavior at an undetermined
 * time in the future.
 *
 * <p>Note that the fail-fast behavior of an iterator cannot be guaranteed
 * as it is, generally speaking, impossible to make any hard guarantees in the
 * presence of unsynchronized concurrent modification.  Fail-fast iterators
 * throw {@code ConcurrentModificationException} on a best-effort basis.
 * Therefore, it would be wrong to write a program that depended on this
 * exception for its correctness:   <i>the fail-fast behavior of iterators
 * should be used only to detect bugs.</i>
 *
 * @author  Josh Bloch  (真正的大佬，一行代码一亿美金)
 */

 public class LinkedList<E>
     extends AbstractSequentialList<E>
     implements List<E>, Deque<E>, Cloneable, java.io.Serializable
 {
     transient int size = 0;    //元素个数

     /**
      * Pointer to first node.
      */
     transient Node<E> first;

     /**
      * Pointer to last node.
      */
     transient Node<E> last;

     private static class Node<E> {
    E item;                   //存储的数据
    Node<E> next;             //指向链表的下一个数据
    Node<E> prev;             //链表的前一个数组，

    Node(Node<E> prev, E element, Node<E> next) {
        this.item = element;
        this.next = next;
        this.prev = prev;
    }
   }


     /**
      * Constructs an empty list.
      */
     public LinkedList() {
     }

     /**
      * Constructs a list containing the elements of the specified
      * collection, in the order they are returned by the collection's
      * iterator.、排序是根据集合迭代器返回的顺序排列的
      *
      * @throws NullPointerException if the specified collection is null
      */
     public LinkedList(Collection<? extends E> c) {
         this();
         addAll(c);          //调用addAll(c)，进行初始化。并没有初始容量。
     }
}
```
接着，我们看几个比较重要的方法

```java
/**
 * Links e as first element.(链接)
 */
private void linkFirst(E e) {
    final Node<E> f = first;                             //获取第一个节点元素
    final Node<E> newNode = new Node<>(null, e, f);      //e作为新节点，它前面为null，f就变老二排后面了
    first = newNode;                                    //设置newNode指向第一个节点
    if (f == null)                                      //f为null，意味着没节点。newNode前后都是它自己
        last = newNode;
    else
        f.prev = newNode;                             //注意f排老二了，它前面当然是老大了，
    size++;                           //元素个数也得加啊， -newNode-f- 向后呢？你猜！
    modCount++;                                      //1、重点：fail-fast就是通过这个变量实现的。
}

/**
 * Links e as last element.
 */
void linkLast(E e) {
    final Node<E> l = last;
    final Node<E> newNode = new Node<>(l, e, null);   //世道变了，当老二了
    last = newNode;
    if (l == null)
        first = newNode;
    else
        l.next = newNode;                            //  -l-newNode
    size++;
    modCount++;                                      //2、最后通过对比判断LinkedList结果是否发生变化                                               //在遍历时假设内部发生变化了，那遍历的结果还有毛用。
}

/**
 * Inserts element e before non-null Node succ.
 */
void linkBefore(E e, Node<E> succ) {                //插队的来了
    // assert succ != null;
    final Node<E> pred = succ.prev;
    final Node<E> newNode = new Node<>(pred, e, succ);
    succ.prev = newNode;                        // -newNode-succ
    if (pred == null)
        first = newNode;                        //为真则succ前面没节点，newNode排它前面
    else
        pred.next = newNode;                    // -pred-newNode-succ
    size++;
    modCount++;                                 //3、那怎么办呢？赶快抛异常啊，
}                                               // `ConcurrentModificationException`登场了，

-----------------以上1、2、3纯属娱乐。注意以上是双链表--------------------------
```
自行脑补过程：

![](https://i.imgur.com/JnllZx4.jpg)

-----------------------

看另外三个比较重要的

```java
/**
     * Unlinks non-null first node f.
     */
    private E unlinkFirst(Node<E> f) {
        // assert f == first && f != null;
        final E element = f.item;
        final Node<E> next = f.next;
        f.item = null;
        f.next = null; // help GC
        first = next;                       //这里是重点啊，
        if (next == null)
            last = null;                  //第一个设置为nul
        else
            next.prev = null;              //
        size--;
        modCount++;
        return element;
    }

    /**
     * Unlinks non-null last node l.
     */
    private E unlinkLast(Node<E> l) {
        // assert l == last && l != null;
        final E element = l.item;
        final Node<E> prev = l.prev;
        l.item = null;
        l.prev = null; // help GC
        last = prev;
        if (prev == null)
            first = null;
        else
            prev.next = null;               //prev不为null，则把下一个设置为null
        size--;
        modCount++;
        return element;
    }

    /**
     * Unlinks non-null node x.
     */
    E unlink(Node<E> x) {
        // assert x != null;
        final E element = x.item;
        final Node<E> next = x.next;        //注意理解的时候先别管这里。
        final Node<E> prev = x.prev;

        if (prev == null) {
            first = next;                     //这是重点
        } else {
            prev.next = next;                // x = 最后一个
            x.prev = null;                   // 移除x前面的。
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;                  //移除x后面的
        }

        x.item = null;                      //最后设置x.item = null; 返回。
        size--;
        modCount++;
        return element;
    }

```
```java


   /**
    * Returns {@code true} if this list contains the specified element.(待会看)
    */
   public boolean contains(Object o) {
       return indexOf(o) != -1;              //注意：indexOf()是判断元素首次出现的索引位置
   }

   /**
    * Returns the number of elements in this list.
    */
   public int size() {
       return size;
   }
   /**
 * Removes all of the elements from this list.
 * The list will be empty after this call returns.
 */
public void clear() {
    for (Node<E> x = first; x != null; ) {
        Node<E> next = x.next;
        x.item = null;
        x.next = null;                  //各种设置为null，size=0
        x.prev = null;
        x = next;                       //循环到x为null，
    }
    first = last = null;
    size = 0;
    modCount++;
}

```

### getXxx()

```java
/**
 * Returns the first element in this list.
 *
 * @return the first element in this list
 * @throws NoSuchElementException if this list is empty
 */
public E getFirst() {
    final Node<E> f = first;
    if (f == null)
        throw new NoSuchElementException();
    return f.item;                              //直接调用Node的item
}

/**
 * Returns the last element in this list.
 *
 * @return the last element in this list
 * @throws NoSuchElementException if this list is empty
 */
public E getLast() {
    final Node<E> l = last;
    if (l == null)
        throw new NoSuchElementException();
    return l.item;
}
```

### removeXxx()

```java
/**
 * Removes and returns the first element from this list.
 */
public E removeFirst() {
    final Node<E> f = first;
    if (f == null)
        throw new NoSuchElementException();           //同样会抛出NoSunchElementException异常
    return unlinkFirst(f);                            //这里调用了刚才分析的那几个方法
}

/**
 * Removes and returns the last element from this list.

 */
public E removeLast() {
    final Node<E> l = last;
    if (l == null)
        throw new NoSuchElementException();
    return unlinkLast(l);
}

/**
     * Removes the first occurrence of the specified element from this list,
     * if it is present.  If this list does not contain the element, it is
     * unchanged.
     */
    public boolean remove(Object o) {
        if (o == null) {                       //从这里可以看出List是可以有null元素的。
            for (Node<E> x = first; x != null; x = x.next) {      //第一个节点不能为null。
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item)) {                            //注意这里，调用Object中的equals（）
                    unlink(x);
                    return true;
                }
            }
        }
        return false;                                             //最后返回false
    }

    /**
 * Removes the element at the specified position in this list.  Shifts any
 * subsequent elements to the left (subtracts one from their indices).
 * Returns the element that was removed from the list.
 */
public E remove(int index) {
    checkElementIndex(index);           //指定索引需要检查边界
    return unlink(node(index));
}
```
### addXxx()
```java

/**
    * Inserts the specified element at the beginning of this list.
    */
   public void addFirst(E e) {
       linkFirst(e);
   }

   /**
    * Appends the specified element to the end of this list.
    *
    * <p>This method is equivalent to {@link #add}.  //等同于add()方法
    *
    * @param e the element to add
    */
   public void addLast(E e) {
       linkLast(e);
   }


   /**
    * Appends the specified element to the end of this list.
    */
   public boolean add(E e) {
       linkLast(e);              //注意这里的add是从后向前添加
       return true;
   }
   /**
    * Inserts all of the elements in the specified collection into this
    * list, starting at the specified position. (指定索引开始
    * @throws IndexOutOfBoundsException {@inheritDoc}
    * @throws NullPointerException if the specified collection is null
    */
   public boolean addAll(int index, Collection<? extends E> c) {
       checkPositionIndex(index);            //和数组一样，涉及是否越界问题，待会看

       Object[] a = c.toArray();             //基本都是直接转toArray，
       int numNew = a.length;
       if (numNew == 0)
           return false;

       Node<E> pred, succ;
       if (index == size) {
           succ = null;
           pred = last;                          //当指定的索引和size大小一样时，pred始终是最后一个
       } else {
           succ = node(index);                    //index位置的元素，
           pred = succ.prev;
       }

       for (Object o : a) {
           E e = (E) o;
           Node<E> newNode = new Node<>(pred, e, null);
           if (pred == null)
               first = newNode;                   //其实代码就是注释
           else
               pred.next = newNode;
           pred = newNode;
       }

       if (succ == null) {
           last = pred;
       } else {
           pred.next = succ;
           succ.prev = pred;
       }

       size += numNew;
       modCount++;
       return true;
   }
```
### setXxx()
```java
/**
     * Replaces the element at the specified position in this list with the
     * specified element.
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public E set(int index, E element) {
        checkElementIndex(index);
        Node<E> x = node(index);
        E oldVal = x.item;
        x.item = element;
        return oldVal;                      //注意此时会返回老元素，知道下面两行什么意思吗？
    }                                       // final List l = list;
                                            // l.set(i,l.set(j,l.get(i)));

```


### 检查

```java
/**
 * Tells if the argument is the index of an existing element.(是否是已存在元素的索引)
 */
private boolean isElementIndex(int index) {
    return index >= 0 && index < size;
}

/**
 * Tells if the argument is the index of a valid(有效) position for an
 * iterator or an add operation.
 */
private boolean isPositionIndex(int index) {
    return index >= 0 && index <= size;        //不能超过size。
}

/**
 * Constructs an IndexOutOfBoundsException detail message.
 * Of the many possible refactorings of the error handling code,
 * this "outlining" performs best with both server and client VMs.
 */
private String outOfBoundsMsg(int index) {
    return "Index: "+index+", Size: "+size;
}

private void checkElementIndex(int index) {
    if (!isElementIndex(index))                 //如果不是存在元素的索引，则抛出
        throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
}

private void checkPositionIndex(int index) {
    if (!isPositionIndex(index))               //该index是否是有效位置上的索引，
        throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
}
```

### Search Operations



```java
/**
  * Returns the index of the first occurrence of the specified element
  * in this list, or -1 if this list does not contain the element.(first 或 -1)
  */
 public int indexOf(Object o) {
     int index = 0;
     if (o == null) {                               //判断是否为null元素，
         for (Node<E> x = first; x != null; x = x.next) {
             if (x.item == null)
                 return index;
             index++;                                //直到找到为止
         }
     } else {
         for (Node<E> x = first; x != null; x = x.next) {
             if (o.equals(x.item))                  //和上面同理
                 return index;
             index++;
         }
     }
     return -1;                                     //返回-1
 }                                                  //在数组中返回 -(low + 1);

 /**
 * Returns the index of the last occurrence of the specified element
 * in this list, or -1 if this list does not contain the element.(last 或 -1)
 */
public int lastIndexOf(Object o) {
    int index = size;                                     //注意是从后向前开始的，
    if (o == null) {
        for (Node<E> x = last; x != null; x = x.prev) {  // x的前一个
            index--;
            if (x.item == null)
                return index;
        }
    } else {
        for (Node<E> x = last; x != null; x = x.prev) {  //x是最后一个
            index--;
            if (o.equals(x.item))
                return index;
        }
    }
    return -1;                                          //最后返回-1
}

```
### Queue operations.
既然出现了，我们来看看，不过先让你看一段《Java编程思想》关于Queue的实现,是不是超级简单。


```java
//: generics/SimpleQueue.java
// A different kind of container that is Iterable
import java.util.*;

public class SimpleQueue<T> implements Iterable<T> {
  private LinkedList<T> storage = new LinkedList<T>();
  public void add(T t) { storage.offer(t); }
  public T get() { return storage.poll(); }
  public Iterator<T> iterator() {
    return storage.iterator();
  }
} ///:~

```
```java


   /**
    * Retrieves, but does not remove, the head (first element) of this list.
    * 注意：只是检索出(head)，并不是删除，poll才会删除，
    */
   public E peek() {
       final Node<E> f = first;
       return (f == null) ? null : f.item;     //三目：表达式成立吗，成立就f=null了。否则返回f.item
   }

   /**
    * Retrieves, but does not remove, the head (first element) of this list.
    *
    * @return the head of this list（同理）
    * @throws NoSuchElementException if this list is empty
    * @since 1.5
    */
   public E element() {
       return getFirst();                           //取得第一个
   }

   /**
    * Retrieves and removes the head (first element) of this list.
    *
    * @return the head of this list, or {@code null} if this list is empty
    */
   public E poll() {
       final Node<E> f = first;
       return (f == null) ? null : unlinkFirst(f);   //不成立，则移除第一个
   }

   /**
    * Retrieves and removes the head (first element) of this list.
    *
    * @return the head of this list
    * @throws NoSuchElementException if this list is empty
    * @since 1.5
    */
   public E remove() {
       return removeFirst();
   }

   /**
    * Adds the specified element as the tail (last element) of this list.
    *
    * @param e the element to add
    * @return {@code true} (as specified by {@link Queue#offer})
    * @since 1.5
    */
   public boolean offer(E e) {
       return add(e);                //注意添加到尾部
   }


```

### Deque operations

```java
/**
 * Inserts the specified element at the front of this list.(指定元素到后面)
 */
public boolean offerFirst(E e) {
    addFirst(e);
    return true;
}

/**
 * Inserts the specified element at the end of this list.(指定元素)
 */
public boolean offerLast(E e) {
    addLast(e);
    return true;
}

/**
 * Retrieves, but does not remove, the first element of this list,
 * or returns {@code null} if this list is empty.(返回但不删除)
 */
public E peekFirst() {
    final Node<E> f = first;
    return (f == null) ? null : f.item;
 }

/**
 * Retrieves, but does not remove, the last element of this list,
 * or returns {@code null} if this list is empty.(返回但不删除)
 */
public E peekLast() {
    final Node<E> l = last;
    return (l == null) ? null : l.item;
}

/**
 * Retrieves and removes the first element of this list,
 * or returns {@code null} if this list is empty.(移除第一个)
 */
public E pollFirst() {
    final Node<E> f = first;
    return (f == null) ? null : unlinkFirst(f);
}

/**
 * Retrieves and removes the last element of this list,
 * or returns {@code null} if this list is empty.（移除最后一个）
 */
public E pollLast() {
    final Node<E> l = last;
    return (l == null) ? null : unlinkLast(l);
}

/**
 * Pushes an element onto the stack represented by this list.  In other
 * words, inserts the element at the front of this list. (Stack)
 */
public void push(E e) {
    addFirst(e);
}

/**
 * Pops an element from the stack represented by this list.  In other
 * words, removes and returns the first element of this list.(返回)
 */
public E pop() {
    return removeFirst();
}
```

### listIterator

以下代码就涉及我之前1、2、3纯属娱乐中的`modCount`如果结构发生变化了则抛出`ConcurrentModificationException`.remove或add时modCount都会++

The list-iterator is **fail-fast**:
- if the list is structurally modified at any time after the Iterator is created,

-  in any way except through the list-iterator's own  remove}or  add
methods, the list-iterator will throw a **ConcurrentModificationException**.
- Thus, in the face(面对) of  concurrent modification, the iterator fails quickly (快速失败)and cleanly, rather  than risking arbitrary(任意), non-deterministic(不确定) behavior at an undetermined  time in the future.

```java
final void checkForComodification() {
    if (modCount != expectedModCount)
        throw new ConcurrentModificationException();
}
```

```java
/**
     * Returns a list-iterator of the elements in this list (in proper
     * sequence), starting at the specified position in the list.
     * Obeys the general contract of {@code List.listIterator(int)}.<p>
     *
     * The list-iterator is <i>fail-fast</i>: if the list is structurally
     * modified at any time after the Iterator is created, in any way except
     * through the list-iterator's own {@code remove} or {@code add}
     * methods, the list-iterator will throw a
     * {@code ConcurrentModificationException}.  Thus, in the face of
     * concurrent modification, the iterator fails quickly and cleanly, rather
     * than risking arbitrary, non-deterministic behavior at an undetermined
     * time in the future.
     */
    public ListIterator<E> listIterator(int index) {
        checkPositionIndex(index);               //检查是否是有效索引
        return new ListItr(index);
    }

    private class ListItr implements ListIterator<E> {      //这里和之前不一样，之前还可以newItr
        private Node<E> lastReturned = null;
        private Node<E> next;
        private int nextIndex;
        private int expectedModCount = modCount;            //注意这里，刚开始一样的。

        ListItr(int index) {
            // assert isPositionIndex(index);
            next = (index == size) ? null : node(index);
            nextIndex = index;
        }

        //忽略一些方法

        public void remove() {
            checkForComodification();     //移除时，首先检查是否并发操作
            if (lastReturned == null)
                throw new IllegalStateException();

            Node<E> lastNext = lastReturned.next;
            unlink(lastReturned);         //移除最后返回的，
            if (next == lastReturned)
                next = lastNext;
            else
                nextIndex--;
            lastReturned = null;
            expectedModCount++;       //这里++，最后和modCount比较。
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }
```

### toArray

```java
/**
 * Returns a shallow（浅） copy of this {@code LinkedList}. (The elements
 * themselves are not cloned.)(只复制引用，没有复制引用指向内存的地址)
 *
 * @return a shallow copy of this {@code LinkedList} instance
 */
public Object clone() {
    LinkedList<E> clone = superClone();

    // Put clone into "virgin" state
    clone.first = clone.last = null;
    clone.size = 0;
    clone.modCount = 0;

    // Initialize clone with our elements
    for (Node<E> x = first; x != null; x = x.next)
        clone.add(x.item);      //通过遍历链表，将x.item加入到新的链表中

    return clone;
}

/**
 * Returns an array containing all of the elements in this list
 * in proper sequence (from first to last element).
 * In other words, this method must allocate a new array
 */
public Object[] toArray() {
    Object[] result = new Object[size];
    int i = 0;
    for (Node<E> x = first; x != null; x = x.next)
        result[i++] = x.item;      //循环遍历赋值
    return result;
}

/**
 * Returns an array containing all of the elements in this list in
 * proper sequence (from first to last element); the runtime type of
 * the returned array is that of the specified array.  If the list fits
 * in the specified array, it is returned therein.  Otherwise, a new
 * array is allocated with the runtime type of the specified array and
 * the size of this list.
 * 注意：这里需要返回值的类型，符合就返回，否则重新分配一个返回值类型和size
 */
public <T> T[] toArray(T[] a) {
    if (a.length < size)
        a = (T[])java.lang.reflect.Array.newInstance(
                            a.getClass().getComponentType(), size);
    int i = 0;
    Object[] result = a;
    for (Node<E> x = first; x != null; x = x.next)
        result[i++] = x.item;

    if (a.length > size)
        a[size] = null;

    return a;                     //返回Object[]类型
}
```
### node


最后再来看看node方法

```java
/**
 * Returns the (non-null) Node at the specified element index.
 */
Node<E> node(int index) {
    // assert isElementIndex(index);

    if (index < (size >> 1)) {        //和二分法类似，小于中间位置，则从头开始，
        Node<E> x = first;
        for (int i = 0; i < index; i++)
            x = x.next;               //找到，则返回
        return x;
    } else {
        Node<E> x = last;             //否则，从尾部开始，向前遍历(size - 1)，
        for (int i = size - 1; i > index; i--)
            x = x.prev;
        return x;
    }
}

```

### 小节
LinkedList特点：
- 1、优点：LinkedList 没有大小限制，add，remove，时间复杂度固定的，

- 2、缺点：除了对首尾元素外，对其他节点，进行add，remove，set，get等操作，都需要进行遍历查找的，时间复杂度为O(n)

- 3、应用场景：查询操作少，存储大量数据，可以考虑使用LinkedList

ArrayList和LinkedList的大致区别：
- 1.ArrayList是实现了基于动态数组的数据结构，LinkedList基于链表的数据结构。
- 2.对于随机访问get和set，ArrayList觉得优于LinkedList，因为LinkedList要移动指针。
- 3.对于新增和删除操作add和remove，LinedList比较占优势，因为ArrayList要移动数据。
