Java容器之Set分析(六)

## 说明
- 1、本文是基于JDK 7 分析的。JDK 8 待我工作了得好好研究下。Lambda、Stream。
- 2、本文会贴出大量的官方注释文档，强迫自己学英语，篇幅比较长，还请谅解。
- 3、笔记放[github](https://github.com/guoxiaoxu/java-learning-guogai/tree/master/jdk7u-jdk/note)了，有兴趣的可以看看。喜欢的可以点个star。
- 4、读过源码的可以快速浏览一遍，也能加深自己的理解。
- 5、源码是个好东东，各种编码技巧，再次佩服老外！！！
- 6、既然官方文档简单明了，为啥还要多此一举呢？有删除写中文的时间还不如好好学英文！

## HashSet
来源于网上(感谢大佬的制作)

![](https://i.imgur.com/p9KuGv7.png)

在正式进入主题之前，先看看鄙人画的这张对比图。左青龙(HashSet)，右白虎(TreeSet)、前朱雀（Collection(容器)），后玄武(LinKedHashSet).

不管三七二十一，先看接口的定义，再来看抽象类，最后才是具体的实现。其他标识接口先不考虑。

本篇的风格会变，尽量会把分析过程写在外边，保持源代码的整洁。我看懂的英文就不过多解释。望大家也可以养成一个看英文文档的习惯(如果有幸能看到)。

![](https://i.imgur.com/JVKDPVf.jpg)

直接进入官方文档吧，里面讲解的很详细了。gogogo。 还是先来看看`Cloneable`和`Serializable`标识接口吧。

## Cloneable

```java
/**
 * A class implements the <code>Cloneable</code> interface to
 * indicate to the {@link java.lang.Object#clone()} method that it
 * is legal for that method to make a
 * field-for-field copy of instances of that class.
 * <p>
 * Invoking Object's clone method on an instance that does not implement the
 * <code>Cloneable</code> interface results in the exception
 * <code>CloneNotSupportedException</code> being thrown.
 * <p>
 * By convention, classes that implement this interface should override
 * <tt>Object.clone</tt> (which is protected) with a public method.
 * See {@link java.lang.Object#clone()} for details on overriding this
 * method.
 * <p>
 * Note that this interface does <i>not</i> contain the <tt>clone</tt> method.
 * Therefore, it is not possible to clone an object merely by virtue of the
 * fact that it implements this interface.  Even if the clone method is invoked
 * reflectively, there is no guarantee that it will succeed.
 *
 * @author  unascribed
 * @see     java.lang.CloneNotSupportedException
 * @see     java.lang.Object#clone()
 * @since   JDK1.0                     //注意JDK1.0
 */
public interface Cloneable {
}
```
大概意思：
- 1、实现类此接口的类可以调用用Object中`clone()`方法,为该类的实例创建一个字段的副本。
- 2、一个实例没有实现`Cloneable`接口，调用Obeject类的clone()方法，结果会导致抛出`CloneNotSupportedException`
- 3、按照约定，实现此接口的类应该覆写Object.clone.受保护的，
- 4、需要注意的是，此接口并没包含clone方法，因此不能仅仅因为实现类此接口就具备了clone，
- 5、甚至通过反射调用clone方法也不能保证会成功。

```java
/**
 * Serializability of a class is enabled by the class implementing the
 * java.io.Serializable interface. Classes that do not implement this
 * interface will not have any of their state serialized or
 * deserialized.  All subtypes of a serializable class are themselves
 * serializable.  The serialization interface has no methods or fields
 * and serves only to identify the semantics of being serializable. <p>
 *
 * To allow subtypes of non-serializable classes to be serialized, the
 * subtype may assume responsibility for saving and restoring the
 * state of the supertype's public, protected, and (if accessible)
 * package fields.  The subtype may assume this responsibility only if
 * the class it extends has an accessible no-arg constructor to
 * initialize the class's state.  It is an error to declare a class
 * Serializable if this is not the case.  The error will be detected at
 * runtime. <p>
 *
 * During deserialization, the fields of non-serializable classes will
 * be initialized using the public or protected no-arg constructor of
 * the class.  A no-arg constructor must be accessible to the subclass
 * that is serializable.  The fields of serializable subclasses will
 * be restored from the stream. <p>
 *
 * When traversing a graph, an object may be encountered that does not
 * support the Serializable interface. In this case the
 * NotSerializableException will be thrown and will identify the class
 * of the non-serializable object. <p>

 *
 * @author  unascribed
 * @since   JDK1.1
 */
public interface Serializable {
}

```

- 1、Serializability of a class is enabled(启动) by the class implementing the
`java.io.Serializable` interface.

- 2、Classes that do not implement this interface will not have any of their state serialized or deserialized(反序列化)
- 3、All subtypes of a serializable class are themselves serializable.
- 4、The serialization interface has no methods or fields and serves only to identify the semantics of being serializable.

看完第一段基本已经得到我们想要的知识点了，gogogo，继续

- 5、为了允许非序列化类的子类型被序列化，子类型可以负责保存恢复父类型的公共、受保护的字符状态。
- 6、在反序列时，非序列化类的字段使用公开或受保护无参构造方法进行初始化。
- 7、挡在进行发送图片时，可能会遇到不支持序列化的接口的对象。在种情况下，`NotSerializableException`将会抛出并标识非序列化对象的类。
- 8、The serialization runtime associates with each serializable class a version number, called a serialVersionUID, which is used during deserialization to verify（验证） that the sender and receiver of a serialized object have loaded classes for that object that are compatible with respect to serialization
- 8、A serializable class can declare its own serialVersionUID explicitly by
  declaring a field named <code>"serialVersionUID"</code> that must be static,
 final, and of type <code>long</code>:<p>

- 9、If a serializable class does not explicitly declare a serialVersionUID, then the serialization runtime will calculate a default serialVersionUID value
for that class based on various aspects of the class, as described in the Java(TM) Object Serialization Specification.

1_9讲解的很清楚了，看不懂的多看几遍。注意：老外的先重点，后废话。(后边才是修饰)。



好了，正式进入HashSet吧，

```java
/**
 * This class implements the <tt>Set</tt> interface, backed by a hash table
 * (actually a <tt>HashMap</tt> instance).  It makes no guarantees as to the
 * iteration order of the set; in particular, it does not guarantee that the
 * order will remain constant over time.  This class permits the <tt>null</tt>
 * element.
 *
 * <p>This class offers constant time performance for the basic operations
 * (<tt>add</tt>, <tt>remove</tt>, <tt>contains</tt> and <tt>size</tt>),
 * assuming the hash function disperses the elements properly among the
 * buckets.  Iterating over this set requires time proportional to the sum of
 * the <tt>HashSet</tt> instance's size (the number of elements) plus the
 * "capacity" of the backing <tt>HashMap</tt> instance (the number of
 * buckets).  Thus, it's very important not to set the initial capacity too
 * high (or the load factor too low) if iteration performance is important.
 *
 * <p><strong>Note that this implementation is not synchronized.</strong>
 * If multiple threads access a hash set concurrently, and at least one of
 * the threads modifies the set, it <i>must</i> be synchronized externally.
 * This is typically accomplished by synchronizing on some object that
 * naturally encapsulates the set.
 *
 * If no such object exists, the set should be "wrapped" using the
 * {@link Collections#synchronizedSet Collections.synchronizedSet}
 * method.  This is best done at creation time, to prevent accidental
 * unsynchronized access to the set:<pre>
 *   Set s = Collections.synchronizedSet(new HashSet(...));</pre>
 *
 * <p>The iterators returned by this class's <tt>iterator</tt> method are
 * <i>fail-fast</i>: if the set is modified at any time after the iterator is
 * created, in any way except through the iterator's own <tt>remove</tt>
 * method, the Iterator throws a {@link ConcurrentModificationException}.
 * Thus, in the face of concurrent modification, the iterator fails quickly
 * and cleanly, rather than risking arbitrary, non-deterministic behavior at
 * an undetermined time in the future.
 *
 * <p>Note that the fail-fast behavior of an iterator cannot be guaranteed
 * as it is, generally speaking, impossible to make any hard guarantees in the
 * presence of unsynchronized concurrent modification.  Fail-fast iterators
 * throw <tt>ConcurrentModificationException</tt> on a best-effort basis.
 * Therefore, it would be wrong to write a program that depended on this
 * exception for its correctness: <i>the fail-fast behavior of iterators
 * should be used only to detect bugs.</i>
 *
 * <p>This class is a member of the
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Java Collections Framework</a>.
 *
 * @param <E> the type of elements maintained by this set
 *
 * @author  Josh Bloch
 * @since   1.2
 */

public class HashSet<E>
    extends AbstractSet<E>
    implements Set<E>, Cloneable, java.io.Serializable {...}
```

- 1、backed（底层） by a hash table(actually a **HashMap** instance).
- 2、It makes no guarantees as to the iteration order(顺序) of the set;
- 3、in particular, it does not guarantee(确保) that the order will remain constant over time.(为了弥补,SortSet、TreeSet就诞生了)
- 4、This class permits(允许) the <tt>null</tt> element.

简单的一段话，包含了重要的4个特性，没有一句废话。待会带着这些特性看源代码。继续。

- 5、This class offers constant time(常数) performance for the basic operations(add,remove, contains, and size), assuming(假设) the hash function disperses the elements properly among the buckets(桶)(在HashMap是重点).


- 6、Iterating over this set requires time proportional(比例) to the sum of
 * the HashSet instance's size (the number of elements) plus the
 * "capacity" of the backing <tt>HashMap</tt> instance (the number of
 * buckets).

- 7、 Thus, it's very important not to set the initial capacity too high (or the load factor too low) if iteration performance(性能) is important.(别设置初始容量不能太high(16)，加载因子不能low(0.75f))
- 8、<strong>Note that this implementation is not synchronized.</strong>


- 9、If multiple threads access a hash set concurrently(并发), and at least one of the threads modifies(修改) the set, it <i>must</i> be synchronized externally. This is typically accomplished by synchronizing on some object that naturally encapsulates(封装) the set。(注意：这里可以使用Collections.synchronizedSet(new HashSet())或者synchronized代码块)。

句句都是精华啊， gogogo，继续。

抢答了！！！

- 10、If no such object exists, the set should be "wrapped" using the  Collections#synchronizedSet Collections.synchronizedSet method.  This is **best** done at creation time, to prevent accidental unsynchronized access to the set:
`Set s = Collections.synchronizedSet(new HashSet(...));`

- 11、The iterators returned by this class's <tt>iterator</tt> method are
  <i>fail-fast</i>:
  - if the set is modified at any time after the iterator is
 created, in any way except through the iterator's own <tt>remove</tt>
  method, the Iterator throws a **ConcurrentModificationException.**
  - Thus, in the face of concurrent modification(修改), the iterator fails quickly and cleanly, rather than risking arbitrary(任意), non-deterministic(不确定) behavior at an undetermined time in the future.
注意：fail-fast是通过对比modCount变量来判断的，执行remove，add，该变化都会++。最后通过对比，如结构发生变化，则抛出`ConcurrentModificationException.`并发修改异常。

- 12、Note that the fail-fast behavior of an iterator cannot be guaranteed(得到保证) as it is, generally speaking, impossible to make any hard guarantees in the presence of unsynchronized concurrent modification.  Fail-fast iterators throw**ConcurrentModificationException** on a best-effort basis.
Therefore, it would be wrong to write a program that depended on this
 exception for its correctness: <i>the fail-fast behavior of iterators
 should be used only to detect(检测) bugs.

 为了方便自己以后复习，最最上面的文档不删除了。接下来，我们看看HashSet具体是怎么实现的。
## variables
 ```java
    private transient HashMap<E,Object> map;

    // Dummy value to associate with an Object in the backing Map
    private static final Object PRESENT = new Object();
```
首先来看看这个`transient`关键字有什么作用？

我们刚开始提到的`Serializable`接口，为Java提供了一种持久化对象实例的机制。当持久化对象时，可能会有一个特殊的对象数据成员，我们不想用序列化机制save它。为了防止它，可以在这个字段前面加上'transient'(瞬态)关键字。

还需要注意一点就是：通过反序列化得到的对象是不同的，且不是通过构造器得到的。换句话说就是不执行构造器。


看见了没？直接持久HashMap的引用，在初始化的时候直接new它。用来保存HashSet中的元素。

`PRESENT`这又是什么鬼？注释说：在backing Map中用虚拟的值与一个Object关联。为啥啊？
其实道理很简单，因为`HashMap`是Key-Value形式的，但你Set存储只是单一的值。你底层想使用HashMap来实现的话，就必须有一个固定的Value值，`HashSet`中存放的每一个值在`HashMap`中作为Key值，对应的Value值都是固定的`PRESENT`对象


## Constructs

```java
    /**
     * Constructs a new, empty set; the backing <tt>HashMap</tt> instance has
     * default initial capacity (16) and load factor (0.75).
     */
    public HashSet() {
        map = new HashMap<>();
    }

    /**
     * Constructs a new set containing the elements in the specified
     * collection.  The HashMapis created with default load factor
     * (0.75) and an initial capacity sufficient(足够) to contain the elements in
     * the specified collection.
     *
     * @param c the collection whose elements are to be placed into this set
     */
    public HashSet(Collection<? extends E> c) {
        map = new HashMap<>(Math.max((int) (c.size()/.75f) + 1, 16));
        addAll(c);
    }

    /**
     * Constructs a new, empty set; the backing HashMap instance has
     * the specified initial capacity and the specified load factor.
     *
     * @param      initialCapacity   the initial capacity of the hash map
     * @param      loadFactor        the load factor of the hash map
     * @throws     IllegalArgumentException if the initial capacity is less
     *             than zero, or if the load factor is nonpositive(负数)
     */
    public HashSet(int initialCapacity, float loadFactor) {
        map = new HashMap<>(initialCapacity, loadFactor);
    }

    /**
     * Constructs a new, empty set; the backing <tt>HashMap</tt> instance has
     * the specified initial capacity and default load factor (0.75).
     *
     * @param      initialCapacity   the initial capacity of the hash table
     * @throws     IllegalArgumentException if the initial capacity is less
     *             than zero
     */
    public HashSet(int initialCapacity) {
        map = new HashMap<>(initialCapacity);
    }

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
     * @throws     IllegalArgumentException if the initial capacity is less
     *             than zero, or if the load factor is nonpositive
     */
    HashSet(int initialCapacity, float loadFactor, boolean dummy) {
        map = new LinkedHashMap<>(initialCapacity, loadFactor);    //注意是空链表哈希集合
    }                                                               //这里保证了迭代的顺序，
 ```

 需要注意的是：在我们创建HashSet的最后能给定合适的初始化容量，避免扩容。因为HashMap底层是数组+链表+红黑树（JKD8）涉及底层复制(桶)、rehash。这个到了HashMap时在唠叨。加载因子就不要变了。

### Method
### iterator
```java
/**
   * Returns an iterator over the elements in this set.  The elements
   * are returned in no particular order.
   *
   * @return an Iterator over the elements in this set
   * @see ConcurrentModificationException
   */
  public Iterator<E> iterator() {
      return map.keySet().iterator();
  }
```
需要注意的是这里调用的是Set中的iterator，还有注释中已经写明了。返回的元素是没有顺序的。而且还有抛出`ConcurrentModificationException`.迭代器的设计非常精妙，建议大家多看看，

### common
```java
  /**
   * Returns the number of elements in this set (its cardinality).
   */
  public int size() {
      return map.size();
  }

  /**
   * Returns <tt>true</tt> if this set contains no elements.
   */
  public boolean isEmpty() {
      return map.isEmpty();
  }

  /**
   * Returns <tt>true</tt> if this set contains the specified element.
   */
  public boolean contains(Object o) {
      return map.containsKey(o);
  }
  /**
  * Removes all of the elements from this set.
  * The set will be empty after this call returns.
  */
 public void clear() {
     map.clear();
 }


```

接下来看两个比较重要的方法，
### add
```java
/**
     * Adds the specified element to this set if it is not already present.
     *
     * @param e element to be added to this set
     */
    public boolean add(E e) {
        return map.put(e, PRESENT)==null;
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
  *
  * @param o object to be removed from this set, if present
  */
 public boolean remove(Object o) {
     return map.remove(o)==PRESENT;
 }
```
如果删除成功，则返回对应的Value，如果不存在对应的entry则返回null。而hashSet中所有key值对应的Value都是固定的`PRESENT`。

### clone

```java
/**
 * Returns a shallow copy of this <tt>HashSet</tt> instance: the elements
 * themselves are not cloned.
 *
 * @return a shallow copy of this set
 */
public Object clone() {
    try {
        HashSet<E> newSet = (HashSet<E>) super.clone();
        newSet.map = (HashMap<E, Object>) map.clone();
        return newSet;
    } catch (CloneNotSupportedException e) {
        throw new InternalError();
    }
}

```

注意，此时返回的是HashSet实例的浅表副本，并没有复制这些元素本身。底层调用了HashMap中的clone()方法，并获取HashMap的浅表副本，并设置到HashSet中。

另外需要说明的是 对于HashSet中保持的对象，需要正确重写equals和HashCode方法，以保证放入对象的唯一性。

重点在HashMap中，gogogo。越努力，越幸运。
