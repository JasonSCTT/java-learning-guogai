## 容器相关的操作及其源码分析

## 说明
- 1、本文是基于JDK 7 分析的。JDK 8 待我工作了得好好研究下。Lambda、Stream。
- 2、本文会贴出大量的官方注释文档，强迫自己学英语，篇幅比较长，还请谅解。
- 3、笔记放[github](https://github.com/guoxiaoxu/java-learning-guogai/tree/master/jdk7u-jdk/note)了，有兴趣的可以看看。喜欢的可以点个star。
- 4、读过源码的可以快速浏览一遍，也能加深自己的理解。
- 5、源码是个好东东，各种编码技巧，再次佩服老外！！！
- 6、既然官方文档简单明了，为啥还要多此一举呢？有删除写中文的时间还不如好好学英文！

### ArrayList
来源于网上(感谢大佬的制作)

![](https://i.imgur.com/p9KuGv7.png)

ArrayList是List接口的可变数组的实现。实现了所有可选列表操作，并允许包括 null 在
内的所有元素。除了实现 List 接口外，此类还提供一些方法来操作内部用来存储列表的数
组的大小

每个 ArrayList 实例都有一个容量，该容量是指用来存储列表元素的数组的大小。它总
是至少等于列表的大小。随着向 ArrayList 中不断添加元素，其容量也自动增长。 自动增长
会带来数据向新数组的重新拷贝，因此，如果可预知数据量的多少，可在构造 ArrayList 时
指定其容量。在添加大量元素前，应用程序也可以使用 ensureCapacity 操作来增加 ArrayList实例的容量，这可以减少递增式再分配的数量。


注意，此实现不是同步的。如果多个线程同时访问一个 ArrayList 实例，而其中至少一
个线程从结构上修改了列表，那么它必须保持外部同步。
相对比 的， ，Vector 是 是 线程 安全 的 ， 其中 涉及 线程 安全 的 方法 皆 被 同步 操作了。

### ArrayList 的实现

对于 ArrayList 而言，它实现 List 接口、底层使用数组保存所有元素。其操作基本上是
对数组的操作。

- 1. Resizable-array： 可变数组
- 2、实现List中所有可选操作
- 3、持久所有元素，并且可以包含null
- 4、还提供了一些方法用于操作内部数据的大小，
- 5、和Vector，但是他的不是同步的。方法没用`synchronized`修饰

**注意：我能理解的英文注释就不翻译了，大家也要养成这个习惯。**非常重要，一般文档中详细的介绍了其功能，及实现的理由，
### 定义及构造
```java
/**
 * Resizable-array implementation of the <tt>List</tt> interface.  Implements
 * all optional list operations, and permits all elements, including
 * <tt>null</tt>.  In addition to implementing the <tt>List</tt> interface,
 * this class provides methods to manipulate the size of the array that is
 * used internally to store the list.  (This class is roughly equivalent to
 * <tt>Vector</tt>, except that it is unsynchronized.)
 */

public class ArrayList<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable
{
    private static final long serialVersionUID = 8683452581122892189L;

    /**
     * The array buffer into which the elements of the ArrayList are stored.
     * The capacity of the ArrayList is the length of this array buffer.
     * 底层使用了缓冲区数组的概念，用来保存add进来的元素
     * 注意，ArrayList的容量就是缓冲区数据的长度
     */
    private transient Object[] elementData;

    /**
     * The size of the ArrayList (the number of elements it contains).
     *
     */
    private int size;

    /**
     * Constructs an empty list with the specified initial capacity.
     *
     * @param  initialCapacity  the initial capacity of the list
     */
    public ArrayList(int initialCapacity) {
        super();
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal Capacity: "+
                                               initialCapacity);
        this.elementData = new Object[initialCapacity];    //new指定容量的数组
    }

    /**
     * Constructs an empty list with an initial capacity of ten.
     */
    public ArrayList() {
        this(10);                 //如果没有指定，则默认是10
    }

    /**
     * Constructs a list containing(包含) the elements of the specified
     * collection, in the order they are returned by the collection's
     * iterator.(根据集合迭代的顺序返回)
     *
     *注意：集合进来直接toArray，然后在Arrays.copyOf(),其实这个方法背后调用System.arraycopy()
     *只要涉及数组，一般都这样，涉及列表的话，是迭代循环set()进去的。
     */
    public ArrayList(Collection<? extends E> c) {
        elementData = c.toArray();
        size = elementData.length;
        // c.toArray might (incorrectly) not return Object[] (see 6260652)
        if (elementData.getClass() != Object[].class)
            elementData = Arrays.copyOf(elementData, size, Object[].class);
    }
---------------------------------BUG解释--------------------------------------------
上面这个BUG在JDK8中FIXED，
List<Object> l = new ArrayList<Object>(Arrays.asList("foo", "bar"));
// Arrays.asList("foo", "bar").toArray() produces String[],and l is backed by that array

// Causes ArrayStoreException, because you cannot put arbitrary(任意) Object into String[]
 l.set(0, new Object());

  If created with an array of a subtype (e.g. String[]),
  its toArray() will return an array of the same type (because it use clone()) instead of an Object[].
  (有可能返回String[]而不是Object[])底层用clone()
```
### 扩容
```java
--------------------------------扩容------------------------------------------------
    /**
     * Trims the capacity of this <tt>ArrayList</tt> instance to be the
     * list's current size.  An application can use this operation to minimize
     * the storage of an <tt>ArrayList</tt> instance.
     * 调正成当前List`s size,进行扩容， 可以用此操作最小化存储ArrayList实例 。
     */
    public void trimToSize() {
        modCount++;
        int oldCapacity = elementData.length;
        if (size < oldCapacity) {
            elementData = Arrays.copyOf(elementData, size);   //底层复制
        }
    }

    /**
     * Increases the capacity of this <tt>ArrayList</tt> instance, if
     * necessary, to ensure that it can hold at least the number of elements
     * specified by the minimum capacity argument.
     *
     *如果有必要增加ArrayList列表的容量，以确保能装下指定最小的容量。
     */
    public void ensureCapacity(int minCapacity) {
        if (minCapacity > 0)
            ensureCapacityInternal(minCapacity);
    }

    private void ensureCapacityInternal(int minCapacity) {
        modCount++;
        // overflow-conscious code
        if (minCapacity - elementData.length > 0)
            grow(minCapacity);
    }

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /**
     * Increases the capacity to ensure that it can hold at least the
     * number of elements specified by the minimum capacity argument.
     *
     * @param minCapacity the desired minimum capacity
     */
    private void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);    //这里扩容1.5倍
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        if (newCapacity - MAX_ARRAY_SIZE > 0)   //如果超过MAX_VALUE = 0x7fffffff - 8;
            newCapacity = hugeCapacity(minCapacity);
        // minCapacity is usually close to size, so this is a win:(初始化一般情况接近size，双赢)
        elementData = Arrays.copyOf(elementData, newCapacity);  //调用底层System.Arraycopy()
    }
    //每次数组扩容时，会将老数组中的元素重新拷贝一份新的数组中，每次数组的容量增长1.5倍。
    //凡是涉及基层拷贝的效率都很低，避免数据扩容，可以通过构造给定一个适合的初始容量。

  //如果超过MAX_VALUE = 0x7fffffff - 8;
    private static int hugeCapacity(int minCapacity) {
     if (minCapacity < 0) // overflow
         throw new OutOfMemoryError();  //溢出
     return (minCapacity > MAX_ARRAY_SIZE) ?  //表达式为真吗？
         Integer.MAX_VALUE :                  //true
         MAX_ARRAY_SIZE;                      //false
 }

```
### 通用
```Java

    /**
     * Returns the number of elements in this list.
     */
    public int size() {
        return size;
    }

    /**
     * Returns <tt>true</tt> if this list contains no elements.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns <tt>true</tt> if this list contains the specified element.
     */
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     */
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (elementData[i]==null)         //返回为null的索引
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(elementData[i]))     //元素首次出现的位置
                    return i;                     //i
        }
        return -1;
    }

    /**
     * Returns the index of the last occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     */
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size-1; i >= 0; i--)   //从后往前找，，i--
                if (elementData[i]==null)
                    return i;
        } else {
            for (int i = size-1; i >= 0; i--)
                if (o.equals(elementData[i]))  //从后往前找，，i--
                    return i;
        }
        return -1;
    }
---------------------------------------------------------------------------------
/**
 * Returns a shallow copy of this <tt>ArrayList</tt> instance.  (The
 * elements themselves are not copied.)
 * 返回ArrayList的一个浅拷贝，因为元素本身没有被复制，
 *
 * @return a clone of this <tt>ArrayList</tt> instance
 */
public Object clone() {
    try {
            ArrayList<E> v = (ArrayList<E>) super.clone();
        v.elementData = Arrays.copyOf(elementData, size);
        v.modCount = 0;
        return v;
    } catch (CloneNotSupportedException e) {
        // this shouldn't happen, since we are Cloneable
        throw new InternalError();
    }
}
/**
    * The number of times this list has been <i>structurally modified</i>.
    * Structural modifications are those that change the size of the
    * list, or otherwise perturb it in such a fashion that iterations in
    * progress may yield incorrect results.
    *      */
    protected transient int modCount = 0;
---------------------------------------------------------------------------
```
序
```java
/**
     * Returns an array containing all of the elements in this list
     * in proper sequence (from first to last element).(所有)
     */
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    /**
     * Returns an array containing all of the elements in this list in proper
     * sequence (from first to last element); the runtime type of the returned
     * array is that of the specified array.  If the list fits in the
     * specified array, it is returned therein.  Otherwise, a new array is
     * allocated with the runtime type of the specified array and the size of
     * this list.
     * 如果列表在指定的数组中，则返回它们，
     * 否则用指定数组返回的类型，和列表的大小重新分配一个新数组
     */
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            // Make a new array of a's runtime type, but my contents:
            return (T[]) Arrays.copyOf(elementData, size, a.getClass());
        System.arraycopy(elementData, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }
    =============================================================================

    // Positional Access Operations

    E elementData(int index) {
        return (E) elementData[index];    //2、最后返回刚开始定义的数组[index].
    }

    /**
     * Returns the element at the specified position in this list.
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public E get(int index) {
        rangeCheck(index);        //get时首先会检查是否越界

        return elementData(index);      //1、调用上面那个，
    }
------------------------------------------------------------------------------------
    /**
 * Checks if the given index is in range.  If not, throws an appropriate
 * runtime exception.
 */
private void rangeCheck(int index) {
    if (index >= size)   //这里的size是列表的大小
        throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
}

/**
 * A version of rangeCheck used by add and addAll.
 */
private void rangeCheckForAdd(int index) {
    if (index > size || index < 0)
        throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
}

/**
 * Constructs an IndexOutOfBoundsException detail message
 */
private String outOfBoundsMsg(int index) {
    return "Index: "+index+", Size: "+size;
}
```
### 存储
```java
/**
     * Replaces the element at the specified position in this list with
     * the specified element.
     *用指定元素在指定的位置上替换
     */
    public E set(int index, E element) {
        rangeCheck(index);                        //边界检查

        E oldValue = elementData(index);         //这里和get[index]一样
        elementData[index] = element;             //把新的值赋值到[index]索引位置上
        return oldValue;                          //返回以前给予该位置的元素，
    }

    /**
     * Appends the specified element to the end of this list.(尾部)
     */
    public boolean add(E e) {
        ensureCapacityInternal(size + 1);  // Increments modCount!!
        elementData[size++] = e;
        return true;                        //注意返回值boolean，size++赋值。
    }

    /**
     * Inserts the specified element at the specified position in this
     * list. Shifts the element currently at that position (if any) and
     * any subsequent elements to the right (adds one to their indices).
     * 把指定元素到插入指定的位置上。注意数组需要移位
     */
    public void add(int index, E element) {
        rangeCheckForAdd(index);

        ensureCapacityInternal(size + 1);  // 数组长度不够，则进行扩容。
        // 将 elementData 中从 Index 位置开始、长度为 size-index 的元素，
        // 拷贝到从下标为 index+1 位置开始的新的 elementData 数组中。
        // 即将当前位于该位置的元素以及所有后续元素右移一个位置。
        System.arraycopy(elementData, index, elementData, index + 1,
                         size - index);     //插入到index下一个位置，要拷贝几个
        elementData[index] = element;
        size++;
    }

-----------------------------------------------------------------------------------
/**
 * 底层实现的数组拷贝，是一个本地方法
 * void arraycopy(Object src,  int  srcPos,Object dest, int destPos,int length);
 *  src      the source array.
 * srcPos   starting position in the source array.
 * dest     the destination array.
 * destPos  starting position in the destination data.
 * length   the number of array elements to be copied.
 */
System.arraycopy(src,-1,dest,2,3);
/**
 * 参数：
 * src：源，从哪个数组中拷贝数据
* srcpos：从原数组中哪一个位置开始拷贝
 * dest：目标：把数据拷贝到哪一个数组中
 * destPos：在目标数组开始存放的位置
 * length：拷贝的个数
 */

---------------------------------------------------------------------------------------
/**
 * Appends all of the elements in the specified collection to the end of
 * this list, in the order that they are returned by the
 * specified collection's Iterator.  (按照指定迭代器返回的元素顺序添加到尾部)
 */
public boolean addAll(Collection<? extends E> c) {
    Object[] a = c.toArray();       //直接toArray，
    int numNew = a.length;
    ensureCapacityInternal(size + numNew);  //如果数组不够，则进行扩容
    System.arraycopy(a, 0, elementData, size, numNew);     //复制
    size += numNew;
    return numNew != 0;
}

/**
 * Inserts all of the elements in the specified collection into this
 * list, starting at the specified position.  Shifts the element
 * currently at that position (if any) and any subsequent elements to
 * the right (increases their indices).  The new elements will appear
 * in the list in the order that they are returned by the
 * specified collection's iterator.
 * 从指定的位置开始，将集合中所有的元素插入到List中
 */
public boolean addAll(int index, Collection<? extends E> c) {
    rangeCheckForAdd(index);     //进行范围检查

    Object[] a = c.toArray();
    int numNew = a.length;
    ensureCapacityInternal(size + numNew);  // Increments modCount

    int numMoved = size - index;
    if (numMoved > 0)
        System.arraycopy(elementData, index, elementData, index + numNew,
                         numMoved);

    System.arraycopy(a, 0, elementData, index, numNew);
    size += numNew;
    return numNew != 0;     //不懂
}


```




### 移除

```java
/**
   * Removes the element at the specified position in this list.
   * Shifts any subsequent elements to the left (subtracts one from their
   * indices).
   */
  public E remove(int index) {
      rangeCheck(index);

      modCount++;                       //注意这里++，后面会用到
      E oldValue = elementData(index);

      int numMoved = size - index - 1;
      if (numMoved > 0)
          System.arraycopy(elementData, index+1, elementData, index,
                           numMoved);
      elementData[--size] = null; // Let gc do its work

      return oldValue;
  }

  /**
   * Removes the first occurrence of the specified element from this list,
   * if it is present.
   * 移除首次出现的指定元素，
   */
  public boolean remove(Object o) {
      if (o == null) {                            //从这里可以看出，List可以为null，
          for (int index = 0; index < size; index++)
              if (elementData[index] == null) {
                  fastRemove(index);              //移除列表上的指定元素
                  return true;
              }
      } else {
          for (int index = 0; index < size; index++)
              if (o.equals(elementData[index])) {
                  fastRemove(index);               //注意跳过边界检查，且没有返回值。
                  return true;
              }
      }
      return false;
  }

  /*
   * Private remove method that skips bounds checking and does not
   * return the value removed.(解释)
   */
  private void fastRemove(int index) {
      modCount++;
      int numMoved = size - index - 1;
      if (numMoved > 0)
          System.arraycopy(elementData, index+1, elementData, index,
                           numMoved);
      elementData[--size] = null; // Let gc do its work
  }

  /**
   * Removes all of the elements from this list.  The list will
   * be empty after this call returns.
   */
  public void clear() {
      modCount++;

      // Let gc do its work
      for (int i = 0; i < size; i++)
          elementData[i] = null;         //循环设置为null。

      size = 0;
  }
------------------------------------------------------------------------------------------
/**
 * Removes from this list all of its elements that are contained in the
 * specified collection.
 */
public boolean removeAll(Collection<?> c) {
    return batchRemove(c, false);
}
------------------------------区别在第二个参数---------------------------------------------
/**
 * Retains only the elements in this list that are contained(包含) in the
 * specified collection.  In other words, removes from this list all
 * of its elements that are （not contained）（未包含） in the specified collection.
 */
public boolean retainAll(Collection<?> c) {
    return batchRemove(c, true);
}

private boolean batchRemove(Collection<?> c, boolean complement) {
    final Object[] elementData = this.elementData;
    int r = 0, w = 0;
    boolean modified = false;             //标志
    try {
        for (; r < size; r++)
            if (c.contains(elementData[r]) == complement)
                elementData[w++] = elementData[r];
    } finally {
        // Preserve behavioral compatibility with AbstractCollection,
        // even if c.contains() throws.                 |
        if (r != size) {                                丨
            System.arraycopy(elementData, r,      丨-----------丨
                             elementData, w,      丨   不懂    丨
                             size - r);           丨-----------丨
            w += size - r;                              丨
        }                                               丨
        if (w != size) {
            for (int i = w; i < size; i++)
                elementData[i] = null;
            modCount += size - w;
            size = w;
            modified = true;
        }
    }
    return modified;
}
/**
 *                 uuuuuuu
 *             uu$$$$$$$$$$$uu
 *          uu$$$$$$$$$$$$$$$$$uu
 *         u$$$$$$$$$$$$$$$$$$$$$u
 *        u$$$$$$$$$$$$$$$$$$$$$$$u
 *       u$$$$$$$$$$$$$$$$$$$$$$$$$u
 *       u$$$$$$$$$$$$$$$$$$$$$$$$$u
 *       u$$$$$$"   "$$$"   "$$$$$$u
 *       "$$$$"      u$u       $$$$"
 *        $$$u   1   u$u   0   u$$$
 *        $$$u      u$$$u      u$$$
 *         "$$$$uu$$$   $$$uu$$$$"
 *          "$$$$$$$"   "$$$$$$$"
 *            u$$$$$$$u$$$$$$$u
 *             u$"$"$"$"$"$"$u
 *  uuu        $$u$ $ $ $ $u$$       uuu
 * u$$$$        $$$$$u$u$u$$$       u$$$$
 *  $$$$$uu      "$$$$$$$$$"     uu$$$$$$
 *u$$$$$$$$$$$uu    """""    uuuu$$$$$$$$$$
 *$$$$"""$$$$$$$$$$uuu   uu$$$$$$$$$"""$$$"
 * """      ""$$$$$$MARNOuu ""$"""
 *           uuuu ""$$$$$$$$$$uuu
 *  u$$$uuu$$$$$$$$$uu ""$$$$$$$$$$$uuu$$$
 *  $$$$$$$$$$""""           ""$$$$$$$$$$$"
 *   "$$$$$"                      ""$$$$""
 *     $$$"                         $$$$"
 */   2018-3-10 0:50
```
### 迭代
注意设计理念，之前有讲过两遍，先略过。

```java
/**
    * Returns a list iterator over the elements in this list (in proper
    * sequence), starting at the specified position in the list.
    *
    * The returned list iterator is <a href="#fail-fast"><i>fail-fast</i></a>.
    */
   public ListIterator<E> listIterator(int index) {
       if (index < 0 || index > size)
           throw new IndexOutOfBoundsException("Index: "+index);
       return new ListItr(index);
   }

   /**
    * Returns a list iterator over the elements in this list (in proper
    * sequence).
    * The returned list iterator is <a href="#fail-fast"><i>fail-fast</i>
    */
   public ListIterator<E> listIterator() {
       return new ListItr(0);
   }

   /**
    * Returns an iterator over the elements in this list in proper sequence.
    */
   public Iterator<E> iterator() {
       return new Itr();
   }

   /**
    * An optimized version of AbstractList.Itr
    */
   private class Itr implements Iterator<E> {}

   /**
    * An optimized version of AbstractList.ListItr
    */
   private class ListItr extends Itr implements ListIterator<E> {
       ListItr(int index) {
       public E previous() {..}

       public void add(E e) {...}
   }
```

同理，内部类都是这样设计的,
```java
/**
 * Returns a view of the portion of this list between the specified
 * {@code fromIndex}, inclusive, and {@code toIndex}, exclusive.
 */
public List<E> subList(int fromIndex, int toIndex) {
    subListRangeCheck(fromIndex, toIndex, size);
    return new SubList(this, 0, fromIndex, toIndex);
}

static void subListRangeCheck(int fromIndex, int toIndex, int size) {
       if (fromIndex < 0)
           throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
       if (toIndex > size)
           throw new IndexOutOfBoundsException("toIndex = " + toIndex);
       if (fromIndex > toIndex)
           throw new IllegalArgumentException("fromIndex(" + fromIndex +
                                              ") > toIndex(" + toIndex + ")");
   }
-------------------------------------------------------------------------------
   private class SubList extends AbstractList<E> implements RandomAccess {


       public ListIterator<E> listIterator(final int index) {


               public void set(E e) {
                   if (lastRet < 0)
                       throw new IllegalStateException();
                   checkForComodification();

                   try {
                       ArrayList.this.set(offset + lastRet, e);
                   } catch (IndexOutOfBoundsException ex) {
                       throw new ConcurrentModificationException();
                   }
               }

       }

       public List<E> subList(int fromIndex, int toIndex) {
           subListRangeCheck(fromIndex, toIndex, size);
           return new SubList(this, offset, fromIndex, toIndex);
       }

       private void rangeCheck(int index) {
           if (index < 0 || index >= this.size)
               throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
       }

       private void rangeCheckForAdd(int index) {
           if (index < 0 || index > this.size)
               throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
       }

       private String outOfBoundsMsg(int index) {
           return "Index: "+index+", Size: "+this.size;
       }

       private void checkForComodification() {
           if (ArrayList.this.modCount != this.modCount)
               throw new ConcurrentModificationException();
       }
   }
```
### ConcurrentModificationException

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

这个主题算是完成了，gogogo，下一个LinkedList。
