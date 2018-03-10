
## 说明
- 1、本文是基于JDK 7 分析的。JDK 8 待我工作了得好好研究下。Lambda、Stream。
- 2、本文会贴出大量的官方注释文档，强迫自己学英语，篇幅比较长，还请谅解。
- 3、笔记放[github](https://github.com/guoxiaoxu/java-learning-guogai/tree/master/jdk7u-jdk/note)了，有兴趣的可以看看。喜欢的可以点个star。
- 4、读过源码的可以快速浏览一遍，也能加深自己的理解。
- 5、源码是个好东东，各种编码技巧，再次佩服老外！！！
- 6、既然官方文档简单明了，为啥还要多此一举呢？有删除写中文的时间还不如好好学英文！

### Set
来源于网上(感谢大佬的制作)

![](https://i.imgur.com/p9KuGv7.png)

在正式进入主题之前，先看看鄙人画的这张对比图。左青龙(HashSet)，右白虎(TreeSet)、前朱雀（Collection(容器)），后玄武(LinKedHashSet).

不管三七二十一，先看接口的定义，再来看抽象类，最后才是具体的实现。其他标识接口先不考虑。

本篇的风格会变，尽量会把分析过程写在外边，保持源代码的整洁。我看懂的英文就不过多解释。望大家也可以养成一个看英文文档的习惯(如果有幸能看到)。

![](https://i.imgur.com/JVKDPVf.jpg)


gogogo，正式开始


```java
/**
 * A collection that contains no duplicate elements.  More formally, sets
 * contain no pair of elements <code>e1</code> and <code>e2</code> such that
 * <code>e1.equals(e2)</code>, and at most one null element.  As implied by
 * its name, this interface models the mathematical <i>set</i> abstraction.
 *
 * <p>The <tt>Set</tt> interface places additional stipulations, beyond those
 * inherited from the <tt>Collection</tt> interface, on the contracts of all
 * constructors and on the contracts of the <tt>add</tt>, <tt>equals</tt> and
 * <tt>hashCode</tt> methods.  Declarations for other inherited methods are
 * also included here for convenience.  (The specifications accompanying these
 * declarations have been tailored to the <tt>Set</tt> interface, but they do
 * not contain any additional stipulations.)
 *
 * <p>The additional stipulation on constructors is, not surprisingly,
 * that all constructors must create a set that contains no duplicate elements
 * (as defined above).
 *
 * <p>Note: Great care must be exercised if mutable objects are used as set
 * elements.  The behavior of a set is not specified if the value of an object
 * is changed in a manner that affects <tt>equals</tt> comparisons while the
 * object is an element in the set.  A special case of this prohibition is
 * that it is not permissible for a set to contain itself as an element.
 *
 * <p>Some set implementations have restrictions on the elements that
 * they may contain.  For example, some implementations prohibit null elements,
 * and some have restrictions on the types of their elements.  Attempting to
 * add an ineligible element throws an unchecked exception, typically
 * <tt>NullPointerException</tt> or <tt>ClassCastException</tt>.  Attempting
 * to query the presence of an ineligible element may throw an exception,
 * or it may simply return false; some implementations will exhibit the former
 * behavior and some will exhibit the latter.  More generally, attempting an
 * operation on an ineligible element whose completion would not result in
 * the insertion of an ineligible element into the set may throw an
 * exception or it may succeed, at the option of the implementation.
 * Such exceptions are marked as "optional" in the specification for this
 * interface.
 *
 * @param <E> the type of elements maintained by this set
 *
 * @author  Josh Bloch
 * @author  Neal Gafter
 * @since 1.2
 */

public interface Set<E> extends Collection<E> {...}

```
文档主要意思：

- 1、A collection that contains no duplicate(重复) elements.(！e1.equals(e2))，at most one null element.至少怎么表达呢？

- 2、he <tt>Set</tt> interface places additional stipulations, beyond those inherited from the Collectioninterface
- 3、Declarations(声明) for other inherited methods are also included here for convenience.
- 4、all constructors must create a set that contains no duplicate elements
- 5、Note: Great care must be exercised if mutable objects are used as set elements.
- 6、it is not permissible for a set to contain itself as an element.
- 7、some implementations prohibit null elements, and some have restrictions on the types of their elements.
- 8、 Attempting to  add an ineligible element throws an unchecked exception, typically <tt>NullPointerException</tt> or <tt>ClassCastException</tt>.
- 9、some implementations will exhibit the former behavior and some will exhibit the latter.

Guo1_9全是重点，没有废话。具体看看有哪些方法，

```java
public interface Set<E> extends Collection<E> {
    // Query Operations

    /**
     * Returns the number of elements in this set (its cardinality).
     */
    int size();

    /**
     * Returns <tt>true</tt> if this set contains no elements.
     */
    boolean isEmpty();

    /**
     * Returns <tt>true</tt> if this set contains the specified element.
     */
    boolean contains(Object o);

    /**
     * Returns an iterator over the elements in this set.
     */
    Iterator<E> iterator();

    /**
     * Returns an array containing all of the elements in this set.(all)
     */
    Object[] toArray();

    /**
     * Returns an array containing all of the elements in this set; the
     * runtime type of the returned array is that of the specified array.
     * If the set fits(符合) in the specified array, it is returned therein.
     * Otherwise, a new array is allocated with the runtime type of the
     * specified array and the size of this set.
     *
     * 符合指定的数组就返回，否则，重新用返回值类型和size of this set.分配一个新数组
     */
    <T> T[] toArray(T[] a);


    // Modification Operations(修改)

    /**
     * Adds the specified element to this set if it is not already present
     * (optional operation).
     */
    boolean add(E e);


    /**
     * Removes the specified element from this set if it is present
     * (optional operation).
     */
    boolean remove(Object o);


    // Bulk Operations

    /**
     * Returns <tt>true</tt> if this set contains all of the elements of the
     * specified collection.  If the specified collection is also a set, this
     * method returns <tt>true</tt> if it is a <i>subset</i> of this set.
     */
    boolean containsAll(Collection<?> c);

    /**
     * Adds all of the elements in the specified collection to this set if
     * they're not already present (optional operation).
     */
    boolean addAll(Collection<? extends E> c);

    /**
     * Retains only the elements in this set that are contained in the
     * specified collection (optional operation).  (仅保留)
     */
    boolean retainAll(Collection<?> c);

    /**
     * Removes from this set all of its elements that are contained in the
     * specified collection (optional operation).
     */
    boolean removeAll(Collection<?> c);

    /**
     * Removes all of the elements from this set (optional operation).
     */
    void clear();


    // Comparison and hashing

    /**
     * Compares the specified object with this set for equality.
     */
    boolean equals(Object o);

    /**
     * Returns the hash code value for this set.  The hash code of a set is
     * defined to be the sum of the hash codes of the elements in the set,
     * where the hash code of a <tt>null</tt> element is defined to be zero.
     *
     *This ensures that s1.equals(s2) implies that
     * s1.hashCode()==s2.hashCode() for any two sets s1 and s2  （*）
     */
    int hashCode();
}

```

接着我们看看这些方法具体在`AbstactSet`中是如何实现的。 主要注意的是Set默认是用Collection中定义的方法。

- 1、`to minimize the effort required to implement this interface.` 以最小的工作量满足Set接口的需求

- 2、`The process of implementing a set by extending this class is identical to that of implementing a Collection by extending AbstractCollection` 继承此类和继承`AbstractCollection`的效果是相同的。
- 3、Note that this class does not override any of the implementations from the AbstractCollectionclass. 只是增加了equals和hashCode的实现。


```java

package java.util;

/**
 * This class provides a skeletal implementation of the <tt>Set</tt>
 * interface to minimize the effort required to implement this
 * interface. <p>
 *
 * The process of implementing a set by extending this class is identical
 * to that of implementing a Collection by extending AbstractCollection,
 * except that all of the methods and constructors in subclasses of this
 * class must obey the additional constraints imposed by the <tt>Set</tt>
 * interface (for instance, the add method must not permit addition of
 * multiple instances of an object to a set).<p>
 *
 * Note that this class does not override any of the implementations from
 * the <tt>AbstractCollection</tt> class.  It merely adds implementations
 * for <tt>equals</tt> and <tt>hashCode</tt>.<p>
 *
 * @param <E> the type of elements maintained by this set
 * @author  Josh Bloch
 */

public abstract class AbstractSet<E> extends AbstractCollection<E> implements Set<E> {
    /**
     * Sole constructor.
     */
    protected AbstractSet() {
    }
}
```
### equals

```java
/**
 * Compares the specified object with this set for equality.  Returns
 * <tt>true</tt> if the given object is also a set, the two sets have
 * the same size, and every member of the given set is contained in
 * this set.  This ensures that the <tt>equals</tt> method works
 * properly across different implementations of the <tt>Set</tt>
 * interface.<p>
 *
 * @return <tt>true</tt> if the specified object is equal to this set
 */
public boolean equals(Object o) {
    if (o == this)
        return true;

    if (!(o instanceof Set))
        return false;
    Collection c = (Collection) o;
    if (c.size() != size())
        return false;
    try {
        return containsAll(c);
    } catch (ClassCastException unused)   {
        return false;
    } catch (NullPointerException unused) {
        return false;
    }
}
```

- 1、This implementation first checks if the specified object is this set; if so it returns true

- 2、Then, it checks if the specified object is a set whose size is identical to the size of
this set;
- 3、if not, it returns false.  If so, it returns containsAll((Collection) o)。

### hashCode

```java
/**
 * Returns the hash code value for this set.  The hash code of a set is
 * defined to be the sum of the hash codes of the elements in the set,
 * where the hash code of a <tt>null</tt> element is defined to be zero.
 *
 *This ensures that s1.equals(s2) implies that
 * s1.hashCode()==s2.hashCode() for any two sets s1 and s2  （*）
 *
 * @return the hash code value for this set

 */
public int hashCode() {
    int h = 0;
    Iterator<E> i = iterator();
    while (i.hasNext()) {
        E obj = i.next();
        if (obj != null)
            h += obj.hashCode();
    }
    return h;
}
```
 This implementation iterates over the set, calling the hashCode</tt> method on each element in the set, and adding up the results.

```java
/**
 * Removes from this set all of its elements that are contained in the
 * specified collection (optional operation).  If the specified
 * collection is also a set, this operation effectively modifies this
 * set so that its value is the <i>asymmetric set difference</i> of
 * the two sets.(差集)
 *
 * <p>Note that this implementation will throw an
 * <tt>UnsupportedOperationException</tt> if the iterator returned by the
 * <tt>iterator</tt> method does not implement the <tt>remove</tt> method.
 *
* @return <tt>true</tt> if this set changed as a result of the call
 */
public boolean removeAll(Collection<?> c) {
    boolean modified = false;

    if (size() > c.size()) {
        for (Iterator<?> i = c.iterator(); i.hasNext(); )
            modified |= remove(i.next());                  //set的remove方法
    } else {                                                //2
        for (Iterator<?> i = iterator(); i.hasNext(); ) {
            if (c.contains(i.next())) {
                i.remove();                                 //迭代器的remove
                modified = true;
            }
        }
    }
    return modified;
}


```


- 1、This implementation determines which is the smaller of this set and the specified collection, by invoking the <tt>size</tt> method on each.
 - 2、If this set has fewer elements, then the implementation iterates over this set,
 - 3、checking each element returned by the iterator in turn to see if it is contained in
 the specified collection.
 - 4、 If it is so contained, it is removed
 from this set with the iterator's <tt>remove</tt> method.
 - 5、If the specified collection has fewer elements, then the
 implementation iterates over the specified collection, removing
 from this set each element returned by the iterator, using this
 set's <tt>remove</tt> method.

判断哪个元素少，需要注意的是老外是反套路，先讲解else{....}  然后才if{...}

gogogo,接下来HashSet。为了好复习，分开记录。
