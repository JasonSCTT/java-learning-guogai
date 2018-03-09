## 容器相关的操作及其源码分析

## 说明
- 1、本文是基于JDK 7 分析的。JDK 8 待我工作了得好好研究下。Lambda、Stream。
- 2、本文会贴出大量的官方注释文档，强迫自己学英语，篇幅比较长，还请谅解。
- 3、笔记放[github](https://github.com/guoxiaoxu/java-learning-guogai/tree/master/code/CollectionMap/note)了，有兴趣的可以看看。喜欢的可以点个star。
- 4、读过源码的可以快速浏览一遍，也能加深自己的理解。
- 5、源码是个好东东，各种编码技巧，再次佩服老外！！！
- 6、为了加强自己读英文文档的能力，文档会保留(部分)。
- 7、其中方法会插一些测试用例，并不是完整的
-

### Collections
来源于网上(感谢大佬的制作)

![](https://i.imgur.com/p9KuGv7.png)

容器，就是可以容纳其他Java对象的对象,在正式进入容器之前，我们先来看几个接口的定义，后学的方法会用到。

需要注意的是，Collection继承的是`Iterable<T>`,Collection中有个iterator()方法，它的作用是返回一个Iterator接口。通常，我们通过Iterator迭代器来遍历集合。ListIterator是List接口所特有的，在List接口中，通过ListIterator()返回一个ListIterator对象。

`Implementing this interface allows an object to be the target of the "foreach" statement` 最为关键那，实现此接口的都可以用 `foreach`进行遍历，为啥啊。为了偷懒啊，foreeach底层使用了迭代器。

重点需要注意的是`Iterable<T>`这个泛型，用处非常非常大。可以迭代任何类型的对象，泛型是JDK5提出的一个"语法糖"，编译后会擦除。关于这里的知识点后续在研究。

**个人感觉泛型 + 反射，能力大过天。要在加一点那就是内部类。**

### Iterable
```java
import java.util.Iterator;

/**
 * Implementing this interface allows an object to be the target of
 * the "foreach" statement.
 */
public interface Iterable<T> {

    /**
     * Returns an iterator over a set of elements of type T..
     */
    Iterator<T> iterator();
}

```
### Iterator
```java
/**
 * An iterator over a collection.  {@code Iterator} takes the place of
 * {@link Enumeration} in the Java Collections Framework.
 */
public interface Iterator<E> {
    /**
     * Returns {@code true} if the iteration has more elements.
     */
    boolean hasNext();

    /**
     * Returns the next element in the iteration.
     */
    E next();

    /**
     * Removes from the underlying collection the last element returned
     * by this iterator (optional operation).
     */
    void remove();
}

```

### ListIterator
```java

/**
 * An iterator for lists that allows the programmer
 * to traverse the list in either direction, modify
 * the list during iteration, and obtain the iterator's
 * current position in the list.
 */
public interface ListIterator<E> extends Iterator<E> {
    // Query Operations

    /**
     * Returns {@code true} if this list iterator has more elements when
     * traversing the list in the forward direction.
     */
    boolean hasNext();

    /**
     * Returns the next element in the list and advances the cursor position.
     */
    E next();

    /**
     * Returns {@code true} if this list iterator has more elements when
     * traversing the list in the reverse direction.  (In other words,
     * returns {@code true} if {@link #previous} would return an element
     * rather than throwing an exception.)
     */
    boolean hasPrevious();

    /**
     * Returns the previous element in the list and moves the cursor
     * position backwards.  This method may be called repeatedly to
     * iterate through the list backwards, or intermixed with calls to
     * {@link #next} to go back and forth.
     */
    E previous();

    /**
     * Returns the index of the element that would be returned by a
     * subsequent call to {@link #next}. (Returns list size if the list
     * iterator is at the end of the list.)
     */
    int nextIndex();

    /**
     * Returns the index of the element that would be returned by a
     * subsequent call to {@link #previous}.
     */
    int previousIndex();


    /**
     * Removes from the list the last element that was returned by {@link
     * #next} or {@link #previous} (optional operation).
     */
    void remove();

    /**
     * Replaces the last element returned by {@link #next} or
     * {@link #previous} with the specified element (optional operation).
     */
    void set(E e);

    /**
     * Inserts the specified element into the list (optional operation).
     */
    void add(E e);
}
```
### Collection

接下来，我们正式进入主题吧，有些英文不会解释。

首先看看Collection中具体有哪些方法。

`Josh Bloch`这位大佬曾是Google的首席架构师，[他的Twitter](https://twitter.com/joshbloch?ref_src=twsrc%5Egoogle%7Ctwcamp%5Eserp%7Ctwgr%5Eauthor)。

**9行代码，索赔超过10亿美金，那么每一行代码价值一亿多美金，这也一度被外界解读为史上最昂贵的代码之一。**

想知道在哪里吗？上次我们分析了数组检查是否越界

```java
/**
 * Checks that {@code fromIndex} and {@code toIndex} are in
 * the range and throws an exception if they aren't.
 */
private static void rangeCheck(int arrayLength, int fromIndex, int toIndex) {
    if (fromIndex > toIndex) {
        throw new IllegalArgumentException(
                "fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")");
    }
    if (fromIndex < 0) {
        throw new ArrayIndexOutOfBoundsException(fromIndex);
    }
    if (toIndex > arrayLength) {
        throw new ArrayIndexOutOfBoundsException(toIndex);
    }
}
```
注意这里使用了泛型，具体的在实现类中，在这里只是定义了一些通用的方法，在抽象类中类中实现。凡是继承此接口的都可以用。最好的设计理念是中间加入了`AbstractCollection`，为啥要这么设计呢？因为抽象类天生就是用来被继承的，你要是实现接口，你必须实现所有的方法，JDK8中加入了默认方法，来看图。

来源于网上(感谢大佬的制作)
![](https://i.imgur.com/TfaArlS.jpg)

在来看看在IDEA中的结构图，

![](https://i.imgur.com/BhXIOS8.jpg)

`public class ArrayList<E> extends AbstractList<E implements List<E>{..}`

这样设计的主要目的是方便扩展，接下来，我们简单看看`AbstractCollection`是怎么实现的
```java
/**
 * The root interface in the <i>collection hierarchy</i>.  A collection
 * represents a group of objects, known as its <i>elements</i>.  Some
 * collections allow duplicate elements and others do not.  Some are ordered
 * and others unordered.  The JDK does not provide any <i>direct</i>
 * implementations of this interface: it provides implementations of more
 * specific subinterfaces like <tt>Set</tt> and <tt>List</tt>.  This interface is typically used to pass collections around and manipulate them where maximum generality is desired.
 *
 *
 * @param <E> the type of elements in this collection   //注意泛型
 *
 * @author  Josh Bloch
 * @author  Neal Gafter
 * @since 1.2
 */

public interface Collection<E> extends Iterable<E> {
    // Query Operations

    /**
     * Returns the number of elements in this collection.
     */
    int size();

    /**
     * Returns <tt>true</tt> if this collection contains no elements.
     */
    boolean isEmpty();

    /**
     * Returns <tt>true</tt> if this collection contains the specified element.
     */
    boolean contains(Object o);

    /**
     * Returns an iterator over the elements in this collection.  There are no
     * guarantees concerning the order in which the elements are returned
     * (unless this collection is an instance of some class that provides a
     * guarantee).
     *
     * @return an <tt>Iterator</tt> over the elements in this collection
     */
    Iterator<E> iterator();

    /**
     * Returns an array containing all of the elements in this collection.
     * If this collection makes any guarantees as to what order its elements
     * are returned by its iterator, this method must return the elements in
     * the same order.
     */
    Object[] toArray();

    // Modification Operations

    /**
     * Ensures that this collection contains the specified element (optional
     * operation).  Returns <tt>true</tt> if this collection changed as a
     * result of the call.
     */
    boolean add(E e);

    /**
     * Removes a single instance of the specified element from this
     * collection, if it is present (optional operation).
     */
    boolean remove(Object o);


    // Bulk Operations

    /**
     * Returns <tt>true</tt> if this collection contains all of the elements
     * in the specified collection.
     */
    boolean containsAll(Collection<?> c);

    /**
     * Adds all of the elements in the specified collection to this collection
     * (optional operation).  The behavior of this operation is undefined if
     * the specified collection is modified while the operation is in progress.
     */
    boolean addAll(Collection<? extends E> c);

    /**
     * Removes all of this collection's elements that are also contained in the
     * specified collection (optional operation).  After this call returns,
     * this collection will contain no elements in common with the specified
     * collection.
     */
    boolean removeAll(Collection<?> c);

    /**
     * Retains only the elements in this collection that are contained in the
     * specified collection (optional operation).
     */
    boolean retainAll(Collection<?> c);

    /**
     * Removes all of the elements from this collection (optional operation).
     * The collection will be empty after this method returns.
     */
    void clear();

    /**
     * Compares the specified object with this collection for equality. <p>
     */
    boolean equals(Object o);

    /**
     * Returns the hash code value for this collection.
     */
    int hashCode();
}
```
---------------------------------------------------------------------------------

### AbstractCollection
`to minimize the effort required to implement this interface`这里解释了原因


```java
**
 * This class provides a skeletal implementation of the <tt>Collection</tt>
 * interface, to minimize the effort required to implement this interface. <p>
 */

public abstract class AbstractCollection<E> implements Collection<E> {
    /**
     * Sole constructor.  (For invocation by subclass constructors, typically
     * implicit.)
     */
    protected AbstractCollection() {
    }

    // Query Operations

    /**
     * Returns an iterator over the elements contained in this collection
     */
    public abstract Iterator<E> iterator();

    public abstract int size();
---------------------------------------------------------------------------------
    /**
     * <p>This implementation returns <tt>size() == 0</tt>.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * <p>This implementation iterates over the elements in the collection,
     * checking each element in turn for equality with the specified element.
     */
    public boolean contains(Object o) {
        Iterator<E> it = iterator();      //使用迭代器进行遍历选择
        if (o==null) {
            while (it.hasNext())
                if (it.next()==null)       //注意null
                    return true;
        } else {
            while (it.hasNext())
                if (o.equals(it.next()))   //调用o。qualse()方法。
                    return true;
        }
        return false;                       //木有，不含够

        //大体思路就是首先判断传进来的是否为null，是否还有还一个，下一个等于null，成为返回true。
        //传进来o不等于null，是否还有还一个，下一个等于null，成为返回true。
    }
---------------------------------------------------------------------------------
    /**
     * {@inheritDoc}
     *
     * <p>This implementation returns an array containing all the elements
     * returned by this collection's iterator, in the same order, stored in
     * consecutive elements of the array, starting with index {@code 0}.
     * <p>This method is equivalent to:
     *  //方法等同于下面这个，
     *  <pre> {@code
     * List<E> list = new ArrayList<E>(size());
     * for (E e : this)
     *     list.add(e);
     * return list.toArray();
     * }</pre>
     */
    public Object[] toArray() {
        // Estimate size of array; be prepared to see more or fewer elements
        Object[] r = new Object[size()];
        Iterator<E> it = iterator();
        for (int i = 0; i < r.length; i++) {
            if (! it.hasNext()) // fewer elements than expected
                return Arrays.copyOf(r, i);     //这里使用了Arrays.copyOf(),之前讲过,此时返回一个空数组
            r[i] = it.next();                   //取出下一个放到r[i]中，如if中有一条语句老外一般不用{}
        }
        return it.hasNext() ? finishToArray(r, it) : r;   //还没有的元素的话就完成ToArary。
    }
------------------------------------------------------------------------------------------
/**
 *处于安全性考虑，Collections提供了大量额外的非功能性方法，其中之一便是生成原Collection的不可修改视图。
 *即返回的Collection和原Collection在元素上保持一致，但不可修改。
 *该实现主要是通过重写add，remove等方法来实现的。即在可能修改的方法中直接抛出异常。
 * <p>This implementation always throws an
 * <tt>UnsupportedOperationException</tt>.
 *
 * @throws UnsupportedOperationException {@inheritDoc}
 * @throws ClassCastException            {@inheritDoc}
 * @throws NullPointerException          {@inheritDoc}
 * @throws IllegalArgumentException      {@inheritDoc}
 * @throws IllegalStateException         {@inheritDoc}
 */
public boolean add(E e) {
    throw new UnsupportedOperationException();
}

  /**
   * <p>This implementation iterates over the collection looking for the
   * specified element.
   */
   public boolean remove(Object o) {
     Iterator<E> it = iterator();
     if (o==null) {
         while (it.hasNext()) {
             if (it.next()==null) {
                 it.remove();               //和上面contains逻辑差不多
                 return true;               //这里调用迭代器的remove方法移除
             }
         }
     } else {
         while (it.hasNext()) {
             if (o.equals(it.next())) {
                 it.remove();             //同理
                 return true;
             }
         }
     }
     return false;
 }

-------------------------------------------------------------------------------------
// Bulk Operations

/**
 * <p>This implementation iterates over the specified collection,
 * checking each element returned by the iterator in turn to see
 * if it's contained in this collection.
 */
public boolean containsAll(Collection<?> c) {
    for (Object e : c)
        if (!contains(e))            //这里循环遍历，只要一个不包含则fasle。
            return false;
    return true;
}

/**
 * <p>This implementation iterates over the specified collection, and adds
 * each object returned by the iterator to this collection, in turn.
 */
public boolean addAll(Collection<? extends E> c) {
    boolean modified = false;         //使用一个标记
    for (E e : c)                     //这里没用大括号是不是简洁多了？
        if (add(e))                   //循环添加，如果为真，修改modified，返回
            modified = true;
    return modified;
}

/**
 * <p>This implementation iterates over this collection, checking each
 * element returned by the iterator in turn to see if it's contained
 * in the specified collection.
 */
public boolean removeAll(Collection<?> c) {
    boolean modified = false;              //修改标记
    Iterator<?> it = iterator();           //使用迭代，
    while (it.hasNext()) {                 //判断是否还有下一个，
        if (c.contains(it.next())) {      //有才能删除，没有删除毛线
            it.remove();
            modified = true;              //删除之后修改标记，
        }
    }
    return modified;
}
---------------------------------------------------------------------------
/**
 * <p>This implementation iterates over this collection, removing each
 * element using the <tt>Iterator.remove</tt> operation.  Most
 * implementations will probably choose to override this method for
 * efficiency.
 */
public void clear() {
    Iterator<E> it = iterator();
    while (it.hasNext()) {
        it.next();
        it.remove();
    }
}

-------------------------------------------------------------------------------
//  String conversion
/**
 * Returns a string representation of this collection.  The string
 * representation consists of a list of the collection's elements in the
 * order they are returned by its iterator, enclosed in square brackets
 * (<tt>"[]"</tt>).
 * @return a string representation of this collection
 */
public String toString() {
    Iterator<E> it = iterator();
    if (! it.hasNext())          //没有下一个直接返回"[]"，熟悉吧？
        return "[]";

    StringBuilder sb = new StringBuilder();     这里使用了StringBuilder追加形式，
    sb.append('[');
    for (;;) {                                    //可以这样写吗？
        E e = it.next();
        sb.append(e == this ? "(this Collection)" : e);
        if (! it.hasNext())
            return sb.append(']').toString();   //这里判断是否没有元素来。
        sb.append(',').append(' ');             //[1,2,3,4,5]
    }
}
```
其实很多类中的方法差不多，只是逻辑上有细微的变化。看人家的代码，再看看我们自己写的代码。

加油吧，希望自己也可以写出这样的代码。gogogo。

接下来我们在看看List中特有的方法，具体的实现在AbstrctList中。和上面重复的就不在多介绍了

## List
摘抄之前的一些笔记

选择List的具体实现：
- １.安全性问题
- ２.是否频繁的插入、删除操作
- ３.是否存储后遍历
-
List接口：1,有序的，2.允许有多个null元素，3、具体的实现类常用的有：ArrayList，Vector，LinkedList

List接口特有的方法，带有索引的功能

`also known as a sequence`  通常第一段是重点

```java
/**
 * An ordered collection (also known as a <i>sequence</i>).  The user of this
 * interface has precise control over where in the list each element is
 * inserted.  The user can access elements by their integer index (position in
 * the list), and search for elements in the list.<p>
 * @author  Josh Bloch
 */
public interface List<E> extends Collection<E> {
    //省略Collection中的方法
    // Positional Access Operations     //位置访问操作

    /**
     * Returns the element at the specified position in this list.  返回指定位置上的元素
     */
    E get(int index);

    /**
     * Replaces the element at the specified position in this list with the
     * specified element (optional operation).
     */
    E set(int index, E element);

    /**
     * Inserts the specified element at the specified position in this list
     * (optional operation).  Shifts the element currently at that position
     * (if any) and any subsequent elements to the right (adds one to their
     * indices).
     */
    void add(int index, E element);

    /**
     * Removes the element at the specified position in this list (optional
     * operation).  Shifts any subsequent elements to the left (subtracts one
     * from their indices).  Returns the element that was removed from the
     * list.
     */
    E remove(int index);


    // Search Operations        //查询操作

    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     *第一次出现的位置，不存在-1
     */
    int indexOf(Object o);

    /**
     * Returns the index of the last occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     *最后出现的索引位置,不存在则返回-1
     */
    int lastIndexOf(Object o);


    // List Iterators

    /**
     * Returns a list iterator over the elements in this list (in proper
     * sequence).
     */
    ListIterator<E> listIterator();

    /**
     * Returns a list iterator over the elements in this list (in proper
     * sequence), starting at the specified position in the list.
     * 指定开始的索引
     */
    ListIterator<E> listIterator(int index);

    // View

    /**
     * Returns a view of the portion of this list between the specified
     * <tt>fromIndex</tt>, inclusive, and <tt>toIndex</tt>, exclusive.
     * 返回子List的视图，可能会抛出索引越界异常，需要检查。也就是用那九行代码。
     */
    List<E> subList(int fromIndex, int toIndex);
}
```

### AbstrctList

接下来我们看看这个具体的实现，重复的忽略。这个也是重点，

![](https://i.imgur.com/CUPjZn5.jpg)

注意注意的是：

1、LinkedList是继承`AbstractSequentialList`的然后他在继承AbstractList。为啥要这样设计？留点疑问，待会到LinkdedList的时候讲。注意一定看认真看类名。

2、锁住的是内部类，在Collections中定义的，`@return an immutable list containing only the specified object`

```java
/**
 * Returns an immutable list containing only the specified object.
 * The returned list is serializable.
 */
public static <T> List<T> singletonList(T o) {
    return new SingletonList<>(o);
}
----------------------------------------------------------------------------------
private static class SingletonList<E> extends AbstractList<E>
    implements RandomAccess, Serializable {
       public int size() {return 1;}  //直接给你返回1，你服不服？
      public E get(int index) {
        if (index != 0)                //只要索引不为0就给你抛个异常，
          throw new IndexOutOfBoundsException("Index: "+index+", Size: 1");
        return element;
    }
}
--------------------------------回顾---------------------------------------------

public static final List EMPTY_LIST = new EmptyList<>();

/**
* Returns the empty list (immutable).
*/
@SuppressWarnings("unchecked")
public static final <T> List<T> emptyList() {
   return (List<T>) EMPTY_LIST;
}
--------------------------------------------------------------------------------

    /**
     * Returns a fixed-size list backed by the specified array.
     */
    public static <T> List<T> asList(T... a) {
        return new ArrayList<>(a);
    }

    private static class ArrayList<E> extends AbstractList<E>
        implements RandomAccess, java.io.Serializable
    {

        public int size() {
            return a.length;
        }

        public E get(int index) {
            return a[index];
        }
}

```
3、ArrayLIst接下来就是我们的真正主题了，

4、注意Vector也是实现AbstractList的(List)，他有个子类是`Stack`因为是同步的，线程安全的。所有效率比较低。

```java
import java.util.LinkedList;
public class Stack<T> {
  private LinkedList<T> storage = new LinkedList<T>();
  public void push(T v) { storage.addFirst(v); }
  public T peek() { return storage.getFirst(); }
  public T pop() { return storage.removeFirst(); }
  public boolean empty() { return storage.isEmpty(); }
  public String toString() { return storage.toString(); }
}
```
```java
//对方法添加了synchronized以保证线程安全，读多写少的情景下建议使用CopyOnWriteArrayList
public class Vector<E>extends AbstractList<E>
          implements List<E>, RandomAccess, Cloneable, java.io.Serializable{}
```
以上Vector类不过多介绍，Stack可以先看看, last-in-first-out
* (LIFO)后进先出的，先进后出一样，有五个方法可以操作Vector，

```java
/**
 * The <code>Stack</code> class represents a last-in-first-out
 * (LIFO) stack of objects. It extends class <tt>Vector</tt> with five
  *
  */
publicclass Stack<E> extends Vector<E> { ...}
```
先到这里吧，下一个主题具体到ArrayList和LinkdedList。gogogo。
