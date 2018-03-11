![](https://user-gold-cdn.xitu.io/2018/3/11/162114a3f0e8af6c?w=640&h=360&f=jpeg&s=62834)

本文属于学习笔记，合适自己的就是最好的学习方式。

## 说明
- 1、本文是基于JDK 7 分析的。JDK 8 待我工作了得好好研究下。Lambda、Stream。
- 2、本文会贴出大量的官方注释文档，强迫自己学英语，篇幅比较长，还请谅解。
- 3、笔记放[github](https://github.com/guoxiaoxu/java-learning-guogai/tree/master/jdk7u-jdk/note)了，有兴趣的可以看看。喜欢的可以点个star。
- 4、读过源码的可以快速浏览一遍，也能加深自己的理解。
- 5、源码是个好东东，各种编码技巧，再次佩服老外！！！
- 6、既然官方文档简单明了，为啥还要多此一举呢？有删除写中文的时间还不如好好学英文！

## LinKedHashSet
来源于网上(感谢大佬的制作)

![](https://i.imgur.com/p9KuGv7.png)

在正式进入主题之前，先看看鄙人画的这张对比图。左青龙(HashSet)，右白虎(TreeSet)、前朱雀（Collection(容器)），后玄武(LinKedHashSet).

![](https://i.imgur.com/JVKDPVf.jpg)


本篇的主角是`LinKedHashSet`,和`HashSet`到底有啥却别呢？多个一个`List`意味着什么？带着这两个问题直奔主题。

从第一段我们可以得知：

- 1、`LinKedHashSet`底层实现是基于哈希表和链表的一个Set的实现。并且具有可预测(predictable)的迭代顺序.不同于`HashSet`,它维护着一个遍历所有条目的双端队列。这个链表定义了迭代的顺序，按照元素的迭代顺序插入到Set集合中，需要注意的是如果一个元素重新插入(re-inserted)迭代的顺序并不会受影响。
- 2、his implementation spares its clients from the unspecified, generally
chaotic ordering provided by  HashSet,   without incurring the
 increased cost associated with TreeSet.可用来产生于元原始集合相同的副本，而不考录原始集合的实现。如果一个模块对输入和copy进行了设置，随后返回的顺序将由副本进行决定。
- 3、该集合提供了Set所有的可以操作，并允许null元素，Like <tt>HashSet</tt>, it provides constant-time(常数) performance for the basic operations (add, contains and  remove), assuming the hash function disperses elements properly among the buckets.
- 4、A linked hash set has two parameters that affect its performance:
 <i>initial capacity</i>(初始容量) and <i>load factor</i>.(加载因子)  They are defined precisely as for <tt>HashSet</tt>.  Note, however, that the penalty for choosing an excessively(过度) high value for initial capacity is less severe for this class than for <tt>HashSet</tt>, as iteration times(迭代) for this class are unaffected by capacity.

 - 5、<strong>Note that this implementation is not synchronized.</strong> This is typically accomplished by synchronizing on some object that naturally encapsulates the `set.Set s = Collections.synchronizedSet(new LinkedHashSet(...))`
- 6、The iterators returned by this class's iterator method are fail-fastif. the set is modified at any time after the iterator is created, in any way except through the iterator's own remove method, the iterator will throw a `ConcurrentModificationException`.

- 7、 Note that the fail-fast behavior of an iterator cannot be guaranteed
 as it is, generally speaking, impossible to make any hard guarantees in the
  presence of `unsynchronized concurrent modification`.  Fail-fast iterators
  throw `ConcurrentModificationException`on a best-effort basis.
  Therefore, it would be wrong to write a program that depended on this
  exception for its correctness:   <i>the fail-fast behavior of iterators
  should be used only to detect(检测) bugs.</i>
```java

- 7、

package java.util;

/**
 * <p>Hash table and linked list implementation of the <tt>Set</tt> interface,
 * with predictable iteration order.  This implementation differs from
 * <tt>HashSet</tt> in that it maintains a doubly-linked list running through
 * all of its entries.  This linked list defines the iteration ordering,
 * which is the order in which elements were inserted into the set
 * (<i>insertion-order</i>).  Note that insertion order is <i>not</i> affected
 * if an element is <i>re-inserted</i> into the set.  (An element <tt>e</tt>
 * is reinserted into a s et <tt>s</tt> if <tt>s.add(e)</tt> is invoked when
 * <tt>s.contains(e)</tt> would return <tt>true</tt> immediately prior to
 * the invocation.)
 *
 * <p>This implementation spares its clients from the unspecified, generally
 * chaotic ordering provided by {@link HashSet}, without incurring the
 * increased cost associated with {@link TreeSet}.  It can be used to
 * produce a copy of a set that has the same order as the original, regardless
 * of the original set's implementation:
 * <pre>
 *     void foo(Set s) {
 *         Set copy = new LinkedHashSet(s);
 *         ...
 *     }
 * </pre>
 * This technique is particularly useful if a module takes a set on input,
 * copies it, and later returns results whose order is determined by that of
 * the copy.  (Clients generally appreciate having things returned in the same
 * order they were presented.)
 *
 * <p>This class provides all of the optional <tt>Set</tt> operations, and
 * permits null elements.  Like <tt>HashSet</tt>, it provides constant-time
 * performance for the basic operations (<tt>add</tt>, <tt>contains</tt> and
 * <tt>remove</tt>), assuming the hash function disperses elements
 * properly among the buckets.  Performance is likely to be just slightly
 * below that of <tt>HashSet</tt>, due to the added expense of maintaining the
 * linked list, with one exception: Iteration over a <tt>LinkedHashSet</tt>
 * requires time proportional to the <i>size</i> of the set, regardless of
 * its capacity.  Iteration over a <tt>HashSet</tt> is likely to be more
 * expensive, requiring time proportional to its <i>capacity</i>.
 *
 * <p>A linked hash set has two parameters that affect its performance:
 * <i>initial capacity</i> and <i>load factor</i>.  They are defined precisely
 * as for <tt>HashSet</tt>.  Note, however, that the penalty for choosing an
 * excessively high value for initial capacity is less severe for this class
 * than for <tt>HashSet</tt>, as iteration times for this class are unaffected
 * by capacity.
 *
 * <p><strong>Note that this implementation is not synchronized.</strong>
 * If multiple threads access a linked hash set concurrently, and at least
 * one of the threads modifies the set, it <em>must</em> be synchronized
 * externally.  This is typically accomplished by synchronizing on some
 * object that naturally encapsulates the set.
 *
 * If no such object exists, the set should be "wrapped" using the
 * {@link Collections#synchronizedSet Collections.synchronizedSet}
 * method.  This is best done at creation time, to prevent accidental
 * unsynchronized access to the set: <pre>
 *   Set s = Collections.synchronizedSet(new LinkedHashSet(...));</pre>
 *
 * <p>The iterators returned by this class's <tt>iterator</tt> method are
 * <em>fail-fast</em>: if the set is modified at any time after the iterator
 * is created, in any way except through the iterator's own <tt>remove</tt>
 * method, the iterator will throw a {@link ConcurrentModificationException}.
 * Thus, in the face of concurrent modification, the iterator fails quickly
 * and cleanly, rather than risking arbitrary, non-deterministic behavior at
 * an undetermined time in the future.
 *
 * <p>Note that the fail-fast behavior of an iterator cannot be guaranteed
 * as it is, generally speaking, impossible to make any hard guarantees in the
 * presence of unsynchronized concurrent modification.  Fail-fast iterators
 * throw <tt>ConcurrentModificationException</tt> on a best-effort basis.
 * Therefore, it would be wrong to write a program that depended on this
 * exception for its correctness:   <i>the fail-fast behavior of iterators
 * should be used only to detect bugs.</i>
 *
 * @param <E> the type of elements maintained by this set
 *
 * @author  Josh Bloch
 * @since   1.4
 */

public class LinkedHashSet<E>
    extends HashSet<E>
    implements Set<E>, Cloneable, java.io.Serializable {}

```
为了方便复习，上面文档不删除。

接下来看看具体的实现

```java
/**
   * Constructs a new, empty linked hash set with the specified initial
   * capacity and load factor.
   *
   * @param      initialCapacity the initial capacity of the linked hash set
   * @param      loadFactor      the load factor of the linked hash set
   */
  public LinkedHashSet(int initialCapacity, float loadFactor) {
      super(initialCapacity, loadFactor, true);    //调用HashSet，
  }

  /**
   * Constructs a new, empty linked hash set with the specified initial
   * capacity and the default load factor (0.75).加载因子，
   *
   * @param   initialCapacity   the initial capacity of the LinkedHashSet
   */
  public LinkedHashSet(int initialCapacity) {
      super(initialCapacity, .75f, true);
  }

  /**
   * Constructs a new, empty linked hash set with the default initial
   * capacity (16) and load factor (0.75). //默认初始容量为16，加载因子0.75
   */
  public LinkedHashSet() {
      super(16, .75f, true);
  }

  /**
   * Constructs a new linked hash set with the same elements as the
   * specified collection.  The linked hash set is created with an initial
   * capacity sufficient to hold the elements in the specified collection
   * and the default load factor (0.75).(相同)
   *
   * @param c  the collection whose elements are to be placed into
   *           this set
   */
  public LinkedHashSet(Collection<? extends E> c) {
      super(Math.max(2*c.size(), 11), .75f, true);    //sufficient to hold
      addAll(c);
  }
```

`LinkedHashSet`实现是通过继承`HashSet`底层使用了`LinkedHashMap` 来保存所有元素，注意super调用的是这个构造函数

```java
/**
 * Constructs a new, empty linked hash set.  (This package private
 * constructor is only used by LinkedHashSet.) The backing
 * HashMap instance is a LinkedHashMap with the specified initial
 * capacity and the specified load factor.
 *
 * @param      initialCapacity   the initial capacity of the hash map
 * @param      loadFactor        the load factor of the hash map
 * @param      dummy             ignored (distinguishes this
 *             constructor from other int, float constructor.)
 */
HashSet(int initialCapacity, float loadFactor, boolean dummy) {
    map = new LinkedHashMap<>(initialCapacity, loadFactor);
}
```

那该如何验证底层的实现是否正确呢？

```java
.....  好复杂，
```

Set集合算是看完了，其实重点在Map中，不过Queue也很重要，其实都很重要啊。gogo
