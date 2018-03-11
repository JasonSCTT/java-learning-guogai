![](https://user-gold-cdn.xitu.io/2018/3/11/162114a3f0e8af6c?w=640&h=360&f=jpeg&s=62834)

本文属于学习笔记，合适自己的就是最好的学习方式。

## 说明
- 1、本文是基于JDK 7 分析的。JDK 8 待我工作了得好好研究下。Lambda、Stream。
- 2、本文会贴出大量的官方注释文档，强迫自己学英语，篇幅比较长，还请谅解。
- 3、笔记放[github](https://github.com/guoxiaoxu/java-learning-guogai/tree/master/jdk7u-jdk/note)了，有兴趣的可以看看。喜欢的可以点个star。
- 4、读过源码的可以快速浏览一遍，也能加深自己的理解。
- 5、源码是个好东东，各种编码技巧，再次佩服老外！！！
- 6、既然官方文档简单明了，为啥还要多此一举呢？有删除写中文的时间还不如好好学英文！

## TreeSet
来源于网上(感谢大佬的制作)

![](https://i.imgur.com/p9KuGv7.png)

在正式进入主题之前，先看看鄙人画的这张对比图。左青龙(HashSet)，右白虎(TreeSet)、前朱雀（Collection(容器)），后玄武(LinKedHashSet).

![](https://i.imgur.com/JVKDPVf.jpg)


从图中我们可以看到TreeSet继承了`AbstractSet`,同时又实现了`NavigableSet`接口，它又继承自`SortedSet`，此接口又继承`Set`. 相比`HashSet`它多个了两个接口，所以也就具备了父类的特性。

我们先从顶层开始吧，
### SortedSet

从下面第一段话我们可以得知此接口为元素提供了**total ordering**.元素的排序顺序可以使用`Comparable`自然顺序，或者通过`Comparator`比较器在已排序的集合创建时提供，Set集合提供的迭代器会升序遍历这些集合。还提供了一些有利于排序的操作。此接口是`SortedMap`的集合模拟。

所有插入SortSet集合的元素必须实现`Comparable`接口。或者接受指定的`Comparator`。此外所有元素之间相互可以比较。`e1.compareTo(e2)`or`comparator.compare(e1, e2)`.前面两个比较不能抛出`ClassCastException`。尝试违反此限制将会导致出错的方法，或者构造器调用时会抛出`ClassCastException`.

<p>Note: several methods return subsets with restricted ranges.
 * Such ranges are <i>half-open</i>, that is, they include their low
 * endpoint but not their high endpoint (where applicable).

```java

/**
 * A {@link Set} that further provides a <i>total ordering</i> on its elements.
 * The elements are ordered using their {@linkplain Comparable natural
 * ordering}, or by a {@link Comparator} typically provided at sorted
 * set creation time.  The set's iterator will traverse the set in
 * ascending element order. Several additional operations are provided
 * to take advantage of the ordering.  (This interface is the set
 * analogue of {@link SortedMap}.)
 *
 * <p>All elements inserted into a sorted set must implement the <tt>Comparable</tt>
 * interface (or be accepted by the specified comparator).  Furthermore, all
 * such elements must be <i>mutually comparable</i>: <tt>e1.compareTo(e2)</tt>
 * (or <tt>comparator.compare(e1, e2)</tt>) must not throw a
 * <tt>ClassCastException</tt> for any elements <tt>e1</tt> and <tt>e2</tt> in
 * the sorted set.  Attempts to violate this restriction will cause the
 * offending method or constructor invocation to throw a
 * <tt>ClassCastException</tt>.
 *
 * <p>Note that the ordering maintained by a sorted set (whether or not an
 * explicit comparator is provided) must be <i>consistent with equals</i> if
 * the sorted set is to correctly implement the <tt>Set</tt> interface.  (See
 * the <tt>Comparable</tt> interface or <tt>Comparator</tt> interface for a
 * precise definition of <i>consistent with equals</i>.)  This is so because
 * the <tt>Set</tt> interface is defined in terms of the <tt>equals</tt>
 * operation, but a sorted set performs all element comparisons using its
 * <tt>compareTo</tt> (or <tt>compare</tt>) method, so two elements that are
 * deemed equal by this method are, from the standpoint of the sorted set,
 * equal.  The behavior of a sorted set <i>is</i> well-defined even if its
 * ordering is inconsistent with equals; it just fails to obey the general
 * contract of the <tt>Set</tt> interface.
 *
 * <p>Note: several methods return subsets with restricted ranges.
 * Such ranges are <i>half-open</i>, that is, they include their low
 * endpoint but not their high endpoint (where applicable).
 * If you need a <i>closed range</i> (which includes both endpoints), and
 * the element type allows for calculation of the successor of a given
 * value, merely request the subrange from <tt>lowEndpoint</tt> to
 * <tt>successor(highEndpoint)</tt>.  For example, suppose that <tt>s</tt>
 * is a sorted set of strings.  The following idiom obtains a view
 * containing all of the strings in <tt>s</tt> from <tt>low</tt> to
 * <tt>high</tt>, inclusive:<pre>
 *   SortedSet&lt;String&gt; sub = s.subSet(low, high+"\0");</pre>
 *
 * @param <E> the type of elements maintained by this set
 *
 * @author  Josh Bloch

 * @since 1.2
 */

public interface SortedSet<E> extends Set<E> {...}
```

接着，我们来看看具体有哪些方法

```java
public interface SortedSet<E> extends Set<E> {
    /**
     * Returns the comparator used to order the elements in this set,
     * or <tt>null</tt> if this set uses the  Comparable
     * natural ordering of its elements.
     */
    Comparator<? super E> comparator();    //注意返回值类型

    /**
     * Returns a view of the portion of this set whose elements range
     * from <tt>fromElement</tt>, inclusive, to <tt>toElement</tt>,
     * exclusive.  (If <tt>fromElement</tt> and <tt>toElement</tt> are
     * equal, the returned set is empty.)  The returned set is backed
     * by this set, so changes in the returned set are reflected in
     * this set, and vice-versa.  The returned set supports all
     * optional set operations that this set supports.
     *
     * <p>The returned set will throw an <tt>IllegalArgumentException</tt>
     * on an attempt to insert an element outside its range.
     *
     * @param fromElement low endpoint (inclusive) of the returned set
     * @param toElement high endpoint (exclusive) of the returned set
     * @return a view of the portion of this set whose elements range from
     *         <tt>fromElement</tt>, inclusive, to <tt>toElement</tt>, exclusive
     * @throws ClassCastException if <tt>fromElement</tt> and
     *         <tt>toElement</tt> cannot be compared to one another using this
     *         set's comparator (or, if the set has no comparator, using
     *         natural ordering).  Implementations may, but are not required
     *         to, throw this exception if <tt>fromElement</tt> or
     *         <tt>toElement</tt> cannot be compared to elements currently in
     *         the set.
     * @throws NullPointerException if <tt>fromElement</tt> or
     *         <tt>toElement</tt> is null and this set does not permit null
     *         elements
     * @throws IllegalArgumentException if <tt>fromElement</tt> is
     *         greater than <tt>toElement</tt>; or if this set itself
     *         has a restricted range, and <tt>fromElement</tt> or
     *         <tt>toElement</tt> lies outside the bounds of the range
     */
    SortedSet<E> subSet(E fromElement, E toElement);

}
```
主要注意的是上面三个异常：
- `ClassCastException`类型转换异常，导致此异常是因为compare方法不能比较两个不匹配的类型，或者该集合没有自定义比较器，使用自然排序。
- `NullPointerException`，fromElement</tt> or <tt>toElement</tt> is null and this set does not permit null  elements(Set集合不允许出现空null元素)
- `IllegalArgumentException`非法参数异常，fromElement大于toElement,或者Set集合本身有一个范围限制，两者超出此范围界限。

还有就是SortSet集合在Redis中非常非常有用。有兴趣的可以看看。复习下，贴一些常用命令：


```java
zadd key score member             //添加元素到集合，元素在集合中存在则更新对应的score
zrem key member                   //1表示成功，如果元素不存在则返回0

zremrangebyrank min max           //删除集合中排名在给定的区间

zincrvy key member                //增加对于member的score的值。

zcard key                         //返回集合中元素的个数

//获取排名
zrank key member                 //返回指定元素在集合中的排名，
zrebrank key member              //同时，但是集合中的元素是按score从大到小排序的

//获取排行榜
zrange key start end            //类似lrange操作从集合中去指定区间的元素，返回时有序的。

//给给定分数区间的元素
zrangebyscore key min max

//评分的聚合
ZUNIONSTORE destination numkeys key [key ...] [WEIGHTS weight] [AGGREGATE SUM|MIN|MAX]

```


```java

/**
 * Returns a view of the portion of this set whose elements are
 * strictly less than <tt>toElement</tt>.  The returned set is
 * backed by this set, so changes in the returned set are
 * reflected in this set, and vice-versa.  The returned set
 * supports all optional set operations that this set supports.
 *
 * <p>The returned set will throw an <tt>IllegalArgumentException</tt>
 * on an attempt to insert an element outside its range.
 *
 * @param toElement high endpoint (exclusive) of the returned set
 * @return a view of the portion of this set whose elements are strictly
 *         less than <tt>toElement</tt>
 */
SortedSet<E> headSet(E toElement);
```
注意上下对比，一个是headSet一个是tailSet.两者同样会抛出三个异常。
- headSet： toElement high endpoint (exclusive) of the returned set  (headSet)
- tailSet：fromElement low endpoint (inclusive) of the returned set （tailSet）

还有就是两者的返回：
- headSet小于toElement区间，
- tailSet一个大于fromElement，

```java

/**
 * Returns a view of the portion of this set whose elements are
 * greater than or equal to <tt>fromElement</tt>.  The returned
 * set is backed by this set, so changes in the returned set are
 * reflected in this set, and vice-versa.  The returned set
 * supports all optional set operations that this set supports.
 *
 * <p>The returned set will throw an <tt>IllegalArgumentException</tt>
 * on an attempt to insert an element outside its range.
 *
 * @param fromElement low endpoint (inclusive) of the returned set
 * @return a view of the portion of this set whose elements are greater
 *         than or equal to <tt>fromElement</tt>
 */
SortedSet<E> tailSet(E fromElement);
```


```java

/**
 * Returns the first (lowest) element currently in this set.
 *
 * @return the first (lowest) element currently in this set
 * @throws NoSuchElementException if this set is empty
 */
E first();

/**
 * Returns the last (highest) element currently in this set.
 *
 * @return the last (highest) element currently in this set
 * @throws NoSuchElementException if this set is empty
 */
E last();
```

如果该集合为空，两者都后抛出`NoSuchElementException`,

接下来我们在来看看`NavigableSet`,初看名字是导航Set。那到底怎么导航的呢？或者为他的实现类增加了哪些功能？带着这些问题，我们直接看官方文档。

首先它继承了`SortedSet`,扩展了一些导航方法，具备了为给定搜索目标报告最接近匹配的导航方法。后边介绍了很多方法，下面在分析。也可以通过升序或降序访问，此接口还额外定义了一些方法，如`pollFirst`,`pollLast`.
```java
/**
 * A  SortedSet extended with navigation methods reporting
 * closest matches for given search targets. Methods  lower,
 *  floor,  ceiling, and  higher return elements
 * respectively less than, less than or equal, greater than or equal,
 * and greater than a given element, returning  null}if there
 * is no such element.  A  NavigableSet may be accessed and
 * traversed in either ascending or descending order.  The {@code
 * descendingSet} method returns a view of the set with the senses of
 * all relational and directional methods inverted. The performance of
 * ascending operations and views is likely to be faster than that of
 * descending ones.  This interface additionally defines methods
 * {@code pollFirst} and {@code pollLast} that return and remove the
 * lowest and highest element, if one exists, else returning {@code
 * null}.  Methods {@code subSet}, {@code headSet},
 * and {@code tailSet} differ from the like-named {@code
 * SortedSet} methods in accepting additional arguments describing
 * whether lower and upper bounds are inclusive versus exclusive.
 * Subsets of any {@code NavigableSet} must implement the {@code
 * NavigableSet} interface.
 *
 * @author Doug Lea
 * @author Josh Bloch
 * @param <E> the type of elements maintained by this set
 * @since 1.6
 */
public interface NavigableSet<E> extends SortedSet<E> {}
    /**
```

注意上面两位作者，后者之前介绍过了，9行代码9亿美刀。前者也是牛逼哄哄。[Doug Lea维基百科](https://en.wikipedia.org/wiki/Doug_Lea)

以下都是此大佬的杰作，后续得好好学习下这个包

![](https://i.imgur.com/DT9mupT.jpg)

接着我们看看具体的实现方法

```java
public interface NavigableSet<E> extends SortedSet<E> {
    /**
     * Returns the greatest element in this set strictly less than the
     * given element, or {@code null} if there is no such element.
     *
     * @param e the value to match
     * @return the greatest element less than {@code e},
     *         or {@code null} if there is no such element
     */
    E lower(E e);

    /**
     * Returns the greatest element(最大) in this set less than(小于) or equal to
     * the given element, or {@code null} if there is no such element.
     *
     * @param e the value to match
     * @return the greatest element less than or equal to {@code e},
     *         or {@code null} if there is no such element
     */
    E floor(E e);

    /**
     * Returns the least element in this set greater than(大于) or equal to
     * the given element, or {@code null} if there is no such element.
     *
     * @param e the value to match
     * @return the least element greater than or equal to {@code e},
     *         or {@code null} if there is no such element
     */
    E ceiling(E e);

    /**
     * Returns the least element(最小) in this set strictly greater than the
     * given element, or {@code null} if there is no such element.
     *
     * @param e the value to match
     * @return the least element greater than {@code e},
     *         or {@code null} if there is no such element
     */
    E higher(E e);

```

注意，以上方法都会抛出`ClassCastException`和`NullPointerException`异常。文档已经解释的很清楚了，这里就不浪费时间了。

具体到了TreeSet中在详细解释。

```java
/**
     * Retrieves and removes the first (lowest) element,
     * or returns {@code null} if this set is empty.
     *
     * @return the first element, or {@code null} if this set is empty
     */
    E pollFirst();

    /**
     * Retrieves and removes the last (highest) element,
     * or returns {@code null} if this set is empty.
     *
     * @return the last element, or {@code null} if this set is empty
     */
    E pollLast();

```


```java
/**
  * Returns an iterator over the elements in this set, in ascending order.
  *
  * @return an iterator over the elements in this set, in ascending（升序） order
  */
 Iterator<E> iterator();

 /**
  * Returns a reverse order view of the elements contained in this set.
  * The descending set is backed by this set, so changes to the set are
  * reflected in the descending set, and vice-versa.  If either set is
  * modified while an iteration over either set is in progress (except
  * through the iterator's own {@code remove} operation), the results of
  * the iteration are undefined.
  *
  * @return a reverse order view of this set
  */
 NavigableSet<E> descendingSet();

 /**
  * Returns an iterator over the elements in this set, in descending order.
  * Equivalent in effect to {@code descendingSet().iterator()}.
  *
  * @return an iterator over the elements in this set, in descending order
  */
 Iterator<E> descendingIterator();
```
注意，一下三个方法会排出之前讲的三个异常，还需要注意的是包含前面和不包含后面。还需要注意`less than`（小于）和`greater than`(大于)，和返回值类型
```java
 /**
  * Returns a view of the portion of this set whose elements range from
  * {@code fromElement} to {@code toElement}.  If {@code fromElement} and
  * {@code toElement} are equal, the returned set is empty unless {@code
  * fromInclusive} and {@code toInclusive} are both true.  The returned set
  * is backed by this set, so changes in the returned set are reflected in
  * this set, and vice-versa.  The returned set supports all optional set
  * operations that this set supports.
  *
  * @param fromElement low endpoint of the returned set
  * @param fromInclusive {@code true} if the low endpoint
  *        is to be included in the returned view
  * @param toElement high endpoint of the returned set
  * @param toInclusive {@code true} if the high endpoint
  *        is to be included in the returned view
  * @return a view of the portion of this set whose elements range from
  *         {@code fromElement}, inclusive, to {@code toElement}, exclusive
  */
 NavigableSet<E> subSet(E fromElement, boolean fromInclusive,
                        E toElement,   boolean toInclusive);

  /**
   * <p>Equivalent to {@code subSet(fromElement, true, toElement, false)}.
   */
  SortedSet<E> subSet(E fromElement, E toElement);

 /**
  * Returns a view of the portion of this set whose elements are less than
  * (or equal to, if {@code inclusive} is true) {@code toElement}.  The
  * returned set is backed by this set, so changes in the returned set are
  * reflected in this set, and vice-versa.  The returned set supports all
  * optional set operations that this set supports.
  *
  * <p>The returned set will throw an {@code IllegalArgumentException}
  * on an attempt to insert an element outside its range.
  *
  * @param toElement high endpoint of the returned set
  * @param inclusive {@code true} if the high endpoint
  *        is to be included in the returned view
  * @return a view of the portion of this set whose elements are less than
  *         (or equal to, if {@code inclusive} is true) {@code toElement}
  */
 NavigableSet<E> headSet(E toElement, boolean inclusive);

 /**
  * <p>Equivalent to {@code headSet(toElement, false)}.
  */
 SortedSet<E> headSet(E toElement);

 /**
  * Returns a view of the portion of this set whose elements are greater
  * than (or equal to, if {@code inclusive} is true) {@code fromElement}.
  * The returned set is backed by this set, so changes in the returned set
  * are reflected in this set, and vice-versa.  The returned set supports
  * all optional set operations that this set supports.
  *
  * <p>The returned set will throw an {@code IllegalArgumentException}
  * on an attempt to insert an element outside its range.
  *
  * @param fromElement low endpoint of the returned set
  * @param inclusive {@code true} if the low endpoint
  *        is to be included in the returned view
  * @return a view of the portion of this set whose elements are greater
  *         than or equal to {@code fromElement}
  */
 NavigableSet<E> tailSet(E fromElement, boolean inclusive);

 /**
  * {@inheritDoc}
  * <p>Equivalent to {@code tailSet(fromElement, true)}.
  */
 SortedSet<E> tailSet(E fromElement);
```

粘一位大佬的笔记[点这里](http://blog.csdn.net/u010126792/article/details/62236367)
```java
//利用实现了NavigableSet的TreeSet做实验
NavigableSet<String> sortedTreeSet = new TreeSet<String>(); // SortedSet接收TreeSet的实例
// 增加元素
sortedTreeSet.add("aa");
sortedTreeSet.add("bb");
sortedTreeSet.add("cc");
sortedTreeSet.add("dd");
sortedTreeSet.add("ee");

System.out.println(sortedTreeSet.size());//5个元素：5
System.out.println( sortedTreeSet.ceiling("cc"));//大于等于cc的最小值，不存在返回null：cc
System.out.println(sortedTreeSet.descendingSet());//返回Set的逆序视图：<span style="font-size:14px;"><span style="color:#000000;background:rgb(255,255,255);"><span style="color:#000000;">[ee, dd, cc, bb, aa]</span></span></span>
System.out.println(sortedTreeSet.floor("cc"));//返回小于等于cc的元素的最大值，不存在返回null：cc
System.out.println( sortedTreeSet.headSet("cc"));//返回元素小于cc的元素：[aa,bb]
System.out.println( sortedTreeSet.headSet("cc", true));//返回元素小于等于cc的元素视图:[aa,bb,cc]

System.out.println(sortedTreeSet.higher("cc"));//返回大于给定元素的最小元素:dd
System.out.println( sortedTreeSet.lower("cc"));//返回小于cc的最大元素:bb
System.out.println(sortedTreeSet.pollFirst());//移除第一个元素:aa
System.out.println(sortedTreeSet.pollLast());//移除最后一个元素:ee
System.out.println( sortedTreeSet.subSet("aa",true,"dd",true));//返回部分视图，true表示包括当前元素:[bb,cc,dd]
System.out.println( sortedTreeSet.subSet("bb","dd"));//返回部分视图包括前面的，不包括后面的:[bb,cc]
System.out.println( sortedTreeSet.tailSet("cc"));//返回元素大于cc的元素视图,包括cc:[cc,dd]
System.out.println( sortedTreeSet.tailSet("cc", false));//返回元素大于等于cc的元素视图:[dd]
System.out.println( sortedTreeSet.iterator());//返回set上的升序排序的迭代器
System.out.println( sortedTreeSet.descendingIterator());//返回set上的降序排序的迭代器
```


好吧，我们正式进入TreeSet集合，其实只要掌握了`HashMap`、`TreeMap`、`SortedMap`，关于Set中的知识也就理解了，无非就是调用Map中的方法，

你看第一段：

- 1、、TreeSet是基于一个`TreeMap`的`NavigableSet`的实现。在Set容器创建时，元素可以根据`Comparable`自然排序，或者是自定义的`Comparator`进行排序，依赖于构造器。

- 2、This implementation provides guaranteed **log(n)** time cost for the basic operations ( add,@code remove and  contains. 时间复杂度也很重要，算法更加重要。
- 3、Note that the ordering maintained by a set (whether or not an explicit
 comparator is provided) must be **consistent with equals** if it is to
 correctly implement the Set interface.(需要满足equals的一致性)。
- 4、**Note that this implementation is not synchronized.** If multiple threads access a tree set concurrently, and at least one of the threads modifies the set, it <i>must</i> be synchronized externally.
`SortedSet s = Collections.synchronizedSortedSet(new TreeSet(...));`
- 5、The iterators returned by this class's  iterator method are
<i>fail-fast</i>(之前讲过，the iterator will throw a `ConcurrentModificationException`. Thus, in the face of concurrent modification)
- 6、Note that the fail-fast behavior of an iterator cannot be guaranteed as it is。the fail-fast behavior of iterators should be used only to detect bugs.(可以通过对比modCount变量看结果是否发生了变化，如变，则抛异常)。
-
```java
/**
 * A {@link NavigableSet} implementation based on a {@link TreeMap}.
 * The elements are ordered using their {@linkplain Comparable natural
 * ordering}, or by a {@link Comparator} provided at set creation
 * time, depending on which constructor is used.
 *
 * <p>This implementation provides guaranteed log(n) time cost for the basic
 * operations ({@code add}, {@code remove} and {@code contains}).
 *
 * <p>Note that the ordering maintained by a set (whether or not an explicit
* comparator is provided) must be <i>consistent with equals</i> if it is to
* correctly implement the {@code Set} interface.  (See {@code Comparable}
* or {@code Comparator} for a precise definition of <i>consistent with
* equals</i>.)  This is so because the {@code Set} interface is defined in
* terms of the {@code equals} operation, but a {@code TreeSet} instance
* performs all element comparisons using its {@code compareTo} (or
* {@code compare}) method, so two elements that are deemed equal by this method
* are, from the standpoint of the set, equal.  The behavior of a set
* <i>is</i> well-defined even if its ordering is inconsistent with equals; it
* just fails to obey the general contract of the {@code Set} interface.
*
* Created by guo on 2018/1/31.
* 对象的哈希值，普通的十进制整数
* 父类Object，方法public void hashCode()计算结果是一个int类型的整数
*
* equals方法早非空队形引用啥概念实现相等关系
*  自反性，对于任何非空引用值x, x.equals(x) 都应该返回true
*  对称性：对于任何非空引用值x，y,当且仅当y.equals(x)返回true时，x.equals(y)才应该返回true
*  传递性：对于任何非空引用值 x、y 和 z，如果 x.equals(y) 返回 true，并且 y.equals(z) 返回 true，那么 x.equals(z) 应返回 true。
*  一致性：对于任何非空引用值 x 和 y，多次调用 x.equals(y) 始终返回 true 或始终返回 false，前提是对象上 equals 比较中所用的信息没有被修改
*  对于任何引用空值x，e.equals(null),都应该返回false；
*
* <p><strong>Note that this implementation is not synchronized.</strong>
* If multiple threads access a tree set concurrently, and at least one
* of the threads modifies the set, it <i>must</i> be synchronized
* externally.  This is typically accomplished by synchronizing on some
* object that naturally encapsulates the set.
* If no such object exists, the set should be "wrapped" using the
* {@link Collections#synchronizedSortedSet Collections.synchronizedSortedSet}
* method.  This is best done at creation time, to prevent accidental
* unsynchronized access to the set: <pre>
*   SortedSet s = Collections.synchronizedSortedSet(new TreeSet(...));</pre>
*
* <p>The iterators returned by this class's {@code iterator} method are
* <i>fail-fast</i>: if the set is modified at any time after the iterator is
* created, in any way except through the iterator's own {@code remove}
* method, the iterator will throw a {@link ConcurrentModificationException}.
* Thus, in the face of concurrent modification, the iterator fails quickly
* and cleanly, rather than risking arbitrary, non-deterministic behavior at
* an undetermined time in the future.
*
* <p>Note that the fail-fast behavior of an iterator cannot be guaranteed
* as it is, generally speaking, impossible to make any hard guarantees in the
* presence of unsynchronized concurrent modification.  Fail-fast iterators
* throw {@code ConcurrentModificationException} on a best-effort basis.
* Therefore, it would be wrong to write a program that depended on this
* exception for its correctness:   <i>the fail-fast behavior of iterators
* should be used only to detect bugs.</i>
*
 * @author  Josh Bloch
 */

public class TreeSet<E> extends AbstractSet<E>
    implements NavigableSet<E>, Cloneable, java.io.Serializable {...}
```

为了方便自己以后复习，上面的文档不删除了。接下来，我们看看TreeSet具体是怎么实现的。(应该所如何调用TreeMap的方法)因为讲过以下的知识点，就粘一段自己的笔记

## variables
```java
/**
 * The backing map.
 */
private transient NavigableMap<E,Object> m;

// Dummy value to associate with an Object in the backing Map
private static final Object PRESENT = new Object();

```
首先来看看这个`transient`关键字有什么作用？

我们刚开始提到的`Serializable`接口，为Java提供了一种持久化对象实例的机制。当持久化对象时，可能会有一个特殊的对象数据成员，我们不想用序列化机制save它。为了防止它，可以在这个字段前面加上'transient'(瞬态)关键字。

还需要注意一点就是：通过反序列化得到的对象是不同的，且不是通过构造器得到的。换句话说就是不执行构造器。

直接持久NavigableMap的引用，具备了映射导航，因为TreeSet底层是`TreeMap`实现的。

`PRESENT`这又是什么鬼？注释说：在backing Map中用虚拟的值与一个Object关联。为啥啊？
其实道理很简单，因为`TreeMap`是Key-Value形式的，但你Set存储只是单一的值。你底层想使用HashMap来实现的话，就必须有一个固定的Value值，`HashSet`中存放的每一个值在`TreeMap`中作为Key值，对应的Value值都是固定的`PRESENT`对象


## Constructs

```java
/**
    * Constructs a set backed(支持) by the specified navigable map.
    */
   TreeSet(NavigableMap<E,Object> m) {
       this.m = m;
   }

   /**
    * Constructs a new, empty tree set, sorted according(默认是升序) to the
    * natural ordering of its elements.  All elements inserted into
    * the set must(必须) implement the  Comparable interface.
    */
   public TreeSet() {
       this(new TreeMap<E,Object>());    //这里直接new TreeMap(..) // Value是固定的。
   }

   /**
    * Constructs a new, empty tree set, sorted according to the specified
    * comparator.  All elements inserted into the set must be <i>mutually
    * comparable</i> by the specified comparator。(注意)
    *
    * @param comparator the comparator that will be used to order this set.
    *        If  null, the Comparable natural
    *        ordering of the elements will be used.(如果null，则默认上面那个构造)
    */
   public TreeSet(Comparator<? super E> comparator) {
       this(new TreeMap<>(comparator));
   }

   /**
    * Constructs a new tree set containing the elements in the specified
    * collection, sorted according to the <i>natural ordering</i> of its
    * elements.
    *
    * @param c collection whose elements will comprise the new set
    */
   public TreeSet(Collection<? extends E> c) {
       this();
       addAll(c);
   }

   /**
    * Constructs a new tree set containing the same elements and
    * using the same ordering as the specified sorted set.
    *
    * @param s sorted set whose elements will comprise the new set
    */
   public TreeSet(SortedSet<E> s) {
       this(s.comparator());
       addAll(s);
   }
```



## Methods

因为都是基于Map实现的，所以这里简单罗列下。重要的放在最后将。
```java
/**
   * Returns an iterator over the elements in this set in ascending order.
   */
  public Iterator<E> iterator() {
      return m.navigableKeySet().iterator();
  }

  /**
   * Returns an iterator over the elements in this set in descending order.
   */
  public Iterator<E> descendingIterator() {
      return m.descendingKeySet().iterator();
  }

  /**
   * @since 1.6
   */
  public NavigableSet<E> descendingSet() {
      return new TreeSet<>(m.descendingMap());
  }

  /**
   * Returns the number of elements in this set (its cardinality).
   */
  public int size() {
      return m.size();
  }

  /**
   * Returns {@code true} if this set contains no elements.
   */
  public boolean isEmpty() {
      return m.isEmpty();
  }

  /**
   * Returns {@code true} if this set contains the specified element.
   */
  public boolean contains(Object o) {
      return m.containsKey(o);
  }
  /**
   * Removes all of the elements from this set.
   * The set will be empty after this call returns.
   */
  public void clear() {
      m.clear();
  }

```
### add
```java
/**
 * Adds the specified element to this set if it is not already present.
 */
public boolean add(E e) {
    return m.put(e, PRESENT)==null;
}
```
这里会啥会出现null呢？

底层实际将将该元素作为 key 放入 HashMap。

由于 HashMap 的 put()方法添加 key-value 对时，当新放入 HashMap 的 Entry 中 key
与集合中原有 Entry 的 key 相同（hashCode()返回值相等，通过 equals 比较也返回 true），新添加的 Entry 的 value 会将覆盖原来 Entry 的 value，但 key 不会有任何改变， 因此如果向 HashSet 中添加一个已经存在的元素时，新添加的集合元素将不会被放入 HashMap 中， 原来的元素也不会有任何改变。由于HashSet中，所有key值对应的value都是PRESENT对象，并没有在HashMap中插入新的key值，从而保证了HashSet中值的唯一性

### remove
```java
/**
 * Removes the specified element from this set if it is present.
 */
public boolean remove(Object o) {
    return m.remove(o)==PRESENT;
}
```

如果删除成功，则返回对应的Value，如果不存在对应的entry则返回null。而hashSet中所有key值对应的Value都是固定的`PRESENT`。

```java
/**
 * Returns a shallow copy of this {@code TreeSet} instance. (The elements
 * themselves are not cloned.)
 */
public Object clone() {
    TreeSet<E> clone = null;
    try {
        clone = (TreeSet<E>) super.clone();
    } catch (CloneNotSupportedException e) {
        throw new InternalError();
    }

    clone.m = new TreeMap<>(m);
    return clone;
}

```

注意，此时返回的是TreeSet实例的浅表副本，并没有复制这些元素本身。底层调用了TreeMap中的clone()方法，并获取TreeMap的浅表副本，并设置到TreeSet中。

另外需要说明的是 对于HashSet中保持的对象，需要正确重写equals和HashCode方法，以保证放入对象的唯一性。


```java
/**
 * Adds all of the elements in the specified collection to this set.
 *
 * @param c collection containing elements to be added to this set
 */
public  boolean addAll(Collection<? extends E> c) {
    // Use linear-time version if applicable
    if (m.size()==0 && c.size() > 0 &&
        c instanceof SortedSet &&
        m instanceof TreeMap) {
        SortedSet<? extends E> set = (SortedSet<? extends E>) c;
        TreeMap<E,Object> map = (TreeMap<E, Object>) m;
        Comparator<? super E> cc = (Comparator<? super E>) set.comparator();
        Comparator<? super E> mc = map.comparator();
        if (cc==mc || (cc != null && cc.equals(mc))) {
            map.addAllForTreeSet(set, PRESENT);
            return true;
        }
    }
    return super.addAll(c);
}
```

关键点在于把c集合强制转换为`SortedSet`集合，还有m集合强制转为`TreeMap`集合。如果条件满足则把Collection中所有元素添加TreeMap集合的key，Value则是`PRESENT`.  最后直接调用父类的addAll()方法。

最后我们复习下`this`和`super`关键字

### this关键字

>this关键字表示指向对象本身的一个指针，this只能在类中的非静态方法中使用。

**1、调用本类的构造方法**

仅仅在类的构造函数中调用本类的其他构造，`this(参数列表)`，注意必须是第一句，一个构造函数只能有一个this调用另一个构造函数。

**2.表示类中属性和方法**

用this来访问类中的属性和方法，如果函数参数或者参数中的局部变量和成员变量同名的情况下，成员变量被屏蔽，此时要访问的成员变量则需要用`this.成员变量名`来访问成员变量。

**3.表示当前对象**

在函数中，需要引用该函数的所属类的当前对象时候

**4.匿名类和内部类**


在匿名类或内部类中用`this`，这个this指的是匿名类或内部类本身。这时，如果我们需要调用外部类的方法或变量时，则因该加上外部类的类名。

### super关键字

>super关键和this作用类似，是使被屏蔽的成员变量或者成员方法变为可见，或者说用来引用被屏蔽的成员变量和成员成员方法。不过super是用在子类中，目的是访问直接父类中被屏蔽的成员，注意是直接父类（就是类之上最近的超类）。

**1.在子类构造方法中调用父类的构造方法**

用`super(参数列表)`的方式调用，参数不是必须的。同时，还要注意`super(参数列表)`这条语句只能在子类构造方法中的第一行

**2.访问父类中被覆盖的同名变量或者方法**

当子类方法中的局部变量或者子类的成员变量与父类成员变量同名时，也就是子类变量覆盖同名父类变量时，可以使用`super.成员变量名`引用父类成员变量。同时，若子类的成员方法覆盖了父类的成员方法时，也可以使用`super.方法名(参数列表)`的方式访问父类的方法。

以上参考[这里](https://www.zybuluo.com/Sakura-W/note/427461)

最最后，我们还是罗列下其他方法，重点都是在TreeMap中实现

```java
   public NavigableSet<E> subSet(E fromElement, boolean fromInclusive,
                                 E toElement,   boolean toInclusive) {
       return new TreeSet<>(m.subMap(fromElement, fromInclusive,
                                      toElement,   toInclusive));
   }

   public NavigableSet<E> headSet(E toElement, boolean inclusive) {
       return new TreeSet<>(m.headMap(toElement, inclusive));
   }

   public NavigableSet<E> tailSet(E fromElement, boolean inclusive) {
       return new TreeSet<>(m.tailMap(fromElement, inclusive));
   }

   public SortedSet<E> subSet(E fromElement, E toElement) {
       return subSet(fromElement, true, toElement, false);
   }


   public SortedSet<E> headSet(E toElement) {
       return headSet(toElement, false);
   }


   public SortedSet<E> tailSet(E fromElement) {
       return tailSet(fromElement, true);
   }

   public Comparator<? super E> comparator() {
       return m.comparator();
   }

   public E first() {
       return m.firstKey();
   }

   public E last() {
       return m.lastKey();
   }

   // NavigableSet API methods

   public E lower(E e) {
       return m.lowerKey(e);
   }

   public E floor(E e) {
       return m.floorKey(e);
   }


   public E ceiling(E e) {
       return m.ceilingKey(e);
   }

   public E higher(E e) {
       return m.higherKey(e);
   }

   public E pollFirst() {
       Map.Entry<E,?> e = m.pollFirstEntry();
       return (e == null) ? null : e.getKey();
   }

   public E pollLast() {
       Map.Entry<E,?> e = m.pollLastEntry();
       return (e == null) ? null : e.getKey();
   }
```

注意此文是自己的学习笔记，gogogo.待会看LinkedHashSet。越努力，越幸运。
