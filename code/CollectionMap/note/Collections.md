## 容器相关的操作及其源码分析

## 说明
- 1、本文是基于JDK 7 分析的。JDK 8 待我工作了得好好研究下。Lambda、Stream。
- 2、因为个人能力有限，只能以模仿的形式+自己的理解写笔记。如有不对的地方还请谅解。
- 3、记录的比较乱，但是对自己加深印象还是蛮有作用的。
- 4、笔记放[github](https://github.com/guoxiaoxu/java-learning-guogai/tree/master/code/CollectionMap/note)了，有兴趣的可以看看。喜欢的可以点个star。
- 5、读过源码的可以快速浏览一遍，也能加深自己的理解。
- 6、源码是个好东东，各种编码技巧，再次佩服老外！！！
- 7、下一个主题是容器Set，gogogo。感觉可以的话可以关注哈

### Collections
来源于网上(感谢大佬的制作)

![](https://i.imgur.com/p9KuGv7.png)

先放一张整体的容器图，在了解容器之前，我们先来看看容器的工具类`Collections`.我有一个习惯，就是每看一个项目之前会先去commons.util包中看看。有哪些抽取出来的工具类。在正式进入主题之前先考大家一个问题，`synchronizedList()`、`synchronizedSet()`、`synchronizedMap()` 这三个方法有啥作用呢？当然让他们变为同步的咯，那怎么变成的呢？其实我也想知道。

首先主要注意的是不会看全部方法，只会看一些常用的，多个重载的只看一个。

正式看之前我们需要知道这个类构造方法是私有的，也就是不能被实例化，方法是static 只能类名.方法();

接着就定义了一些静态常量，大体意思就是List有两个实现，一个是随机的List(RandomAccess)，另外一个是顺序的(sequential)。随机访问在变量在较小的顺序List中有较好的性能。就是一些调优参数，根据他们的经验。反正是定义了各种阈值，看方法的时候在介绍作用。这种技巧叫命名参数，也可以写成SQL语句。方便更改。
```java
public class Collections {
    // Suppresses default constructor, ensuring non-instantiability.
    private Collections() {     //被私有化了，
    }
    // Algorithms(算法)
    /*
     * Tuning parameters for algorithms - Many of the List algorithms have
     * two implementations, one of which is appropriate for RandomAccess
     * lists, the other for "sequential."  Often, the random access variant
     * yields better performance on small sequential access lists.
     */
    private static final int BINARYSEARCH_THRESHOLD   = 5000;
    private static final int REVERSE_THRESHOLD        =   18;
    private static final int SHUFFLE_THRESHOLD        =    5;
    private static final int FILL_THRESHOLD           =   25;
    private static final int ROTATE_THRESHOLD         =  100;
    private static final int COPY_THRESHOLD           =   10;
    private static final int REPLACEALL_THRESHOLD     =   11;
    private static final int INDEXOFSUBLIST_THRESHOLD =   35;
}
-----------------------------插一点-----------------------------------------------
容器的根接口，组要注意的是这里只是定义，具体的实现在子类中，所有容器共有的方法。(Map除外啊)
/**
 * The root interface in the <i>collection hierarchy</i>.  A collection
 * represents a group of objects, known as its <i>elements</i>.  Some
 * collections allow duplicate elements and others do not.  Some are ordered
 * and others unordered.  The JDK does not provide any <i>direct</i>
 * implementations of this interface: it provides implementations of more
 * specific subinterfaces like <tt>Set</tt> and <tt>List</tt>.  This interface
 * is typically used to pass collections around and manipulate them where
 * maximum generality is desired.
 */
public interface Collection<E> extends Iterable<E> {

    int size();
    boolean isEmpty();
    boolean contains(Object o);
    Iterator<E> iterator();
    Object[] toArray();
    <T> T[] toArray(T[] a);
    boolean add(E e);
    boolean remove(Object o);
    boolean containsAll(Collection<?> c);
    boolean addAll(Collection<? extends E> c);
    boolean removeAll(Collection<?> c);
    boolean retainAll(Collection<?> c);
    void clear();
    boolean equals(Object o);
    int hashCode();
}
```
需要注意的是一个是实现了comparable接口，里面有compareTo()方法，另外一个自定义`Comparator`的compare()方法。

首先进来就变成一个数组，需要注意的是调用List，然而List底下还有具体的实现呢.
在调用Arrays.sort()方法排序(默认升序ASC)
```java
/**
 * Sorts the specified list into ascending order, according to the
 * {@linkplain Comparable natural ordering} of its elements.
 * All elements in the list must implement the {@link Comparable}
 * interface.  Furthermore, all elements in the list must be
 * <i>mutually comparable</i> (that is, {@code e1.compareTo(e2)}
 * must not throw a {@code ClassCastException} for any elements
 * {@code e1} and {@code e2} in the list).
 */
public static <T extends Comparable<? super T>> void sort(List<T> list) {
    Object[] a = list.toArray();    //首先进来就变成一个数组，需要注意的是调用List，然而List底下还有具体的实现呢.
    Arrays.sort(a);                 //在调用Arrays.sort()方法排序(默认升序ASC)
    ListIterator<T> i = list.listIterator();  双端迭代器，只能用于List哦，
    for (int j=0; j<a.length; j++) {
        i.next();                            //检查是否有下一个，
        i.set((T)a[j]);                     //进行设置，
    }
}
-----------------------------------------------------------------------------------
public ListIterator<E> listIterator() {
    return new ListItr(0);              //注意这里new了一个内部类，到ArrayList时再看。
                                        //题外话：之前听某培训机构的课说内部类无卵用。
}                                       //可我见谅很多啊Spring的，TreeNode，链表里，事件里...

private class ListItr extends Itr implements ListIterator<E> {
       ListItr(int index) {
           super();
       }
       public boolean hasPrevious() { }
       public int nextIndex() { }
       public int previousIndex() { }
       public E previous() {}
       public void set(E e) {}
       public void add(E e) {}
}
---------------------继续，大家看出区别了吗,下一个主题在将-------------------------

public Iterator<E> iterator() {
    return new Itr();
}
/**
 * An optimized version of AbstractList.Itr
 */
private class Itr implements Iterator<E> {       终点在这里
    public boolean hasNext() {}
    public E next() {}
    public void remove() {}

}
```
### binarySearch

需要注意的是 the list must be sorted,默认是升序的。如果包含多个，则不能确保被找到。

第二段大概意思就是该方法是log（n）的，前提你的实现`RandomAccess`接口啊，或者这个数非常大，则就会执行下面的那个但这里变成了 O(n)遍历，log(n)比较。
```java
/**
 * Searches the specified list for the specified object using the binary
 * search algorithm.  The list must be sorted into ascending order
 * according to the {@linkplain Comparable natural ordering} of its
 * elements (as by the {@link #sort(List)} method) prior to making this
 * call.  If it is not sorted, the results are undefined.  If the list
 * contains multiple elements equal to the specified object, there is no
 * guarantee which one will be found.
 *
 *-这里解释了原因
 *This method runs in log(n) time for a "random access" list (which
 * provides near-constant-time positional access).  If the specified list
 * does not implement the {@link RandomAccess} interface and is large,
 * this method will do an iterator-based binary search that performs
 * O(n) link traversals and O(log n) element comparisons.
 */
public static <T>int binarySearch(List<? extends Comparable<? super T>> list, T key) {

    //首先判断list是否是RandomAccess的实例，在判断list的大小是否小于5000这个阀值，
    //注意丨丨(跟我读gun gun，不信你试试在 )
    if (list instanceof RandomAccess || list.size()<BINARYSEARCH_THRESHOLD)
        return Collections.indexedBinarySearch(list, key);
    else
        return Collections.iteratorBinarySearch(list, key);
}
```
我在思考看那个比较好：

首先想说明的是，我在**Arrays**类里已经分析过二分查找了，所以会简化一下。主要找之间的区别。[点这里直接到Binary](https://juejin.im/post/5aa103b1f265da239611f539#heading-4)

```java
private static <T>
   int indexedBinarySearch(List<? extends Comparable<? super T>> list, T key)
   {
       int low = 0;
       int high = list.size()-1;

       while (low <= high) {
           int mid = (low + high) >>> 1;
           Comparable<? super T> midVal = list.get(mid);
           int cmp = midVal.compareTo(key);

           if (cmp < 0)
               low = mid + 1;
           else if (cmp > 0)
               high = mid - 1;
           else
               return mid; // key found
       }
       return -(low + 1);  // key not found
   }
-----------------------------------区别，多了个i--------------------------------------------
   private static <T>
   int iteratorBinarySearch(List<? extends Comparable<? super T>> list, T key)
   {
       ListIterator<? extends Comparable<? super T>> i = list.listIterator();
           Comparable<? super T> midVal = get(i, mid);
 }
----------------------------------------------------------------

   /**
    *大概意思就是重新定位list的迭代器，获取第i个元素。
    * Gets the ith element from the given list by repositioning the specified
    * list listIterator.
    *
    */
   private static <T> T get(ListIterator<? extends T> i, int index) {
       T obj = null;
       int pos = i.nextIndex();
       if (pos <= index) {               //看下i的下一个元素中间位置的哪边？小于在左边get(i, mid);
           do {
               obj = i.next();           //这这里至少会执行一次。
           } while (pos++ < index);      //如果这里为真，则上面那条一直执行。
       } else {
           do {
               obj = i.previous();      //
           } while (--pos > index);     //直到--到等于minVal为止
       }
       return obj;
   }
```
### reverse

反转指定列表的顺序，需要注意的是时间复杂度为线性的。

可以看到，当 List 支持随机访问时，可以直接从头开始，第一个元素和最后一个元素交换位置，一直交换到中间位置。

swap就是交换两个位置，还有注意反转阀值为18.右移位就是除2,j为最后的一个`REVERSE_THRESHOLD `为18
```java
/**
 * Reverses the order of the elements in the specified list.<p>
 *
 * This method runs in linear time.
 */
public static void reverse(List<?> list) {
    int size = list.size();
    if (size < REVERSE_THRESHOLD || list instanceof RandomAccess) {
        //swap就是交换两个位置，还有注意反转阀值为18.
        for (int i=0, mid=size>>1, j=size-1; i<mid; i++, j--)
            swap(list, i, j);      //这里调用的是----下面的方法
    } else {
        ListIterator fwd = list.listIterator();
        ListIterator rev = list.listIterator(size);
        for (int i=0, mid=list.size()>>1; i<mid; i++) {
            Object tmp = fwd.next();              //获取fwd的下一个，当作临时的
            fwd.set(rev.previous());              //获取反转列表的前一个设置到fwd
            rev.set(tmp);                                     //在设置临时的
          //  rev.set(fwd.next());                //循环交替设置，
        }                            fwd  -------
    }                                rev：-------
}

```
### swap(重点)
```java
/**
 * Swaps the elements at the specified positions in the specified list.
 * (If the specified positions are equal, invoking this method leaves
 * the list unchanged.)
 */
public static void swap(List<?> list, int i, int j) {
    //L.get(i)返回位置i上的元素，
    //l.set(j, l.get(i)) 将i上的元素设置给j，同时由于l.set(i,E)返回这个位置上之前的元素，可以返回原来在j上的元素
    //然后在设置给i
    final List l = list;
    l.set(i, l.set(j, l.get(i)));
}
--------------------------------------------------------------------------------
/**
 * Swaps the two specified elements in the specified array.
 * 这个简单那
 */
private static void swap(Object[] arr, int i, int j) {
    Object tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
}

--------------------------------------------------------------------------------
private static void swap(Object[] arr, int i, int j) {
    arr[i] = num[i] + arr[j];
    arr[j] = num[i] - arr[j];
    num[i] = num[i] -arr[j];
}


----------------------------------------------------------------------------------

private static void swap(Object[] arr, int i, int j) {
  num[i] = num[i]^arr[j];
  max = num[i]^arr[j];
  num[i] = num[i] ^ arr[j];
}

```
### shuffle

使用默认的随机数，随机打算指定的列表，`SHUFFLE_THRESHOLD`默认为5，
```java

/**
 * Randomly permutes the specified list using a default source of
 * randomness.  All permutations occur with approximately equal
 * likelihood.<p>
 */
public static void shuffle(List<?> list) {
    Random rnd = r;
    if (rnd == null)
        r = rnd = new Random();
    shuffle(list, rnd);           //调用下面这个方法。
}
private static Random r;
--------------------------------------------------------------------------------------
/**
 * Randomly permute the specified list using the specified source of
 * randomness.  All permutations occur with equal likelihood
 * assuming that the source of randomness is fair.<p>
 * 大概意思为使用指定的随机数源，假设`the source of randomness`是公平的，所有排列都是相等的。
 */
public static void shuffle(List<?> list, Random rnd) {
    int size = list.size();
    if (size < SHUFFLE_THRESHOLD || list instanceof RandomAccess) {
        for (int i=size; i>1; i--)
            swap(list, i-1, rnd.nextInt(i));    //先判断是否超过阀值，成立则交换。
    } else {
        Object arr[] = list.toArray();

        // Shuffle array
        for (int i=size; i>1; i--)
            swap(arr, i-1, rnd.nextInt(i));

        // Dump array back into list         //将数组返回到列表中
        ListIterator it = list.listIterator();
        for (int i=0; i<arr.length; i++) {
            it.next();
            it.set(arr[i]);
        }
    }
}
```
### fill填充

用指定元素替换指定列表中的元素，线性执行的。`FILL_THRESHOLD`为25
```java
/**
 * Replaces all of the elements of the specified list with the specified
 * element. <p>
 *
 * This method runs in linear time.
 */
public static <T> void fill(List<? super T> list, T obj) {
    int size = list.size();

    if (size < FILL_THRESHOLD || list instanceof RandomAccess) {
        for (int i=0; i<size; i++)
            list.set(i, obj);
    } else {
        ListIterator<? super T> itr = list.listIterator();
        for (int i=0; i<size; i++) {
            itr.next();
            itr.set(obj);
        }
    }
}
```
### min
返回给定集合的最小元素，自然顺序排序。
```java
/**
 * Returns the minimum element of the given collection, according to the
 * <i>natural ordering</i> of its elements.  All elements in the
 * collection must implement the <tt>Comparable</tt> interface.
 * Furthermore, all elements in the collection must be <i>mutually
 * comparable</i> (that is, <tt>e1.compareTo(e2)</tt> must not throw a
 * <tt>ClassCastException</tt> for any elements <tt>e1</tt> and
 * <tt>e2</tt> in the collection).<p>
 */
public static <T extends Object & Comparable<? super T>> T min(Collection<? extends T> coll) {
    Iterator<? extends T> i = coll.iterator();
    T candidate = i.next();                 //作为候补，先拿出一个

    while (i.hasNext()) {
        T next = i.next();
        if (next.compareTo(candidate) < 0)    //比较两者哪个小
            candidate = next;                 //将i位置的下一个作为候补队员
    }
    return candidate;
}
```
### max

返回集合中最大的元素，和上面唯一的却别就是`next.compareTo(candidate) > 0`变为大于号了。
```java

public static <T extends Object & Comparable<? super T>> T max(Collection<? extends T> coll) {
    Iterator<? extends T> i = coll.iterator();
    T candidate = i.next();

    while (i.hasNext()) {
        T next = i.next();
        if (next.compareTo(candidate) > 0)
            candidate = next;
    }
    return candidate;
}
```

### rotate
按照给定的步长旋转指定的列表，同样分为随机存取的List和迭代式后移。`ROTATE_THRESHOLD`默认为100

这里的思想就是如果列表太大则分为两个子列表进行反转。
```java
/**
 * Rotates the elements in the specified list by the specified distance.
 * After calling this method, the element at index <tt>i</tt> will be
 * the element previously at index <tt>(i - distance)</tt> mod
 * <tt>list.size()</tt>, for all values of <tt>i</tt> between <tt>0</tt>
 * and <tt>list.size()-1</tt>, inclusive.  (This method has no effect on
 * the size of the list.)
 *
 * 解释在这里，指定列表较小，或者实现了`RandomAccess`接口，则调用rotate1，
 * 指定的列表非常大，没有实现接口，则调用subList分为两个列表
 *
* <p>If the specified list is small or implements the {@link
 * RandomAccess} interface, this implementation exchanges the first
 * element into the location it should go, and then repeatedly exchanges
 * the displaced element into the location it should go until a displaced
 * element is swapped into the first element.  If necessary, the process
 * is repeated on the second and successive elements, until the rotation
 * is complete.  If the specified list is large and doesn't implement the
 * <tt>RandomAccess</tt> interface, this implementation breaks the
 * list into two sublist views around index <tt>-distance mod size</tt>.
 */
public static void rotate(List<?> list, int distance) {
    if (list instanceof RandomAccess || list.size() < ROTATE_THRESHOLD)
        rotate1(list, distance);
    else
        rotate2(list, distance);
}

private static <T> void rotate1(List<T> list, int distance) {看不懂....}
--------------------------性能好与上面---------------------------------------------
private static void rotate2(List<?> list, int distance) {
    int size = list.size();
    if (size == 0)
        return;
    int mid =  -distance % size;
    if (mid < 0)
        mid += size;                  //控制中间位置
    if (mid == 0)
        return;

    reverse(list.subList(0, mid));     //截取0到中间位置。
    reverse(list.subList(mid, size));
    reverse(list);
}

---------------------------------------------------------------------------------
即对于(1,2,3,4,5,6), distance=2.
反转方式如下(1,2,3,4,5,6)–>(4,3,2,1,5,6)–>(4,3,2,1,6,5)–>(5,6,1,2,3,4)
```

### replaceAll
替换值

### indexOfSubList
查找target在source出现的最小位置。该方法的实现也是通常的挨个元素比较法，没有太大创新。不同的是对于迭代式的list，当最后判断出source的当前位置开始不是target时，需要回退。

### lastIndexOfSubList

查找target在source出现的最大位置。
实现方式和indexOfSubList相似，只是从后面往前查找。

-------
上面介绍了一些算法功能的实现，接下来我们看其他的
### unmodifiable
处于安全性考虑，Collections提供了大量额外的非功能性方法，其中之一便是生成原Collection的不可修改视图。

即返回的Collection和原Collection在元素上保持一致，但不可修改。

该实现主要是通过重写add，remove等方法来实现的。即在可能修改的方法中直接抛出异常。

`the collection to be "wrapped" in a synchronized collection.`

`a synchronized view of the specified collection.`

其实很简单，这里使用了装饰器模式，还有就是内部类+mutex(互斥)。然后所有的继承`UnmodifiableCollection`增加里面没有的方法。

```java

    /**
     * Returns an unmodifiable view of the specified collection.  This method
     * allows modules to provide users with "read-only" access to internal
     * collections.  Query operations on the returned collection "read through"
     * to the specified collection, and attempts to modify the returned
     * collection, whether direct or via its iterator, result in an
     * <tt>UnsupportedOperationException</tt>.<p>
     */
    public static <T> Collection<T> unmodifiableCollection(Collection<? extends T> c) {
        return new UnmodifiableCollection<>(c);
    }
------------------------------------------------------------------------------------
static class UnmodifiableCollection<E> implements Collection<E>, Serializable {
    private static final long serialVersionUID = 1820017752578914078L;

    final Collection<? extends E> c;

    UnmodifiableCollection(Collection<? extends E> c) {
        if (c==null)
            throw new NullPointerException();     //空指针异常
        this.c = c;
    }

    public int size()                   {return c.size();}
    public boolean isEmpty()            {return c.isEmpty();}
    public boolean contains(Object o)   {return c.contains(o);}
    public String toString()            {return c.toString();}

    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private final Iterator<? extends E> i = c.iterator();

            public boolean hasNext() {return i.hasNext();}
            public E next()          {return i.next();}
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public boolean add(E e) {
        throw new UnsupportedOperationException();
    }
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    public boolean containsAll(Collection<?> coll) {
        return c.containsAll(coll);
    }
  //。。。。
}
----------------------------------------------------------------------------------
public static <T> Set<T> unmodifiableSet(Set<? extends T> s) {
    return new UnmodifiableSet<>(s);       //直接new，这个又继承了UnmodifiableCollection
}
---------------------------------------------------------------------------------
static class UnmodifiableSet<E> extends UnmodifiableCollection<E>
                             implements Set<E>, Serializable {
    private static final long serialVersionUID = -9215047833775013803L;

    UnmodifiableSet(Set<? extends E> s)     {super(s);}
    public boolean equals(Object o) {return o == this || c.equals(o);}
    public int hashCode()           {return c.hashCode();}
}
---------------------------------------------------------------------------------

public static <T> SortedSet<T> unmodifiableSortedSet(SortedSet<T> s) {
    return new UnmodifiableSortedSet<>(s);
}

public static <T> List<T> unmodifiableList(List<? extends T> list) {
    return (list instanceof RandomAccess ?
            new UnmodifiableRandomAccessList<>(list) :
            new UnmodifiableList<>(list));
}

public static <K,V> Map<K,V> unmodifiableMap(Map<? extends K, ? extends V> m) {
    return new UnmodifiableMap<>(m);
}
```

### synchronized

是时候回答刚开始的问题了。有些是参考了这篇博文[点一点](http://blog.csdn.net/u012882134/article/details/78165257)


可能你在使用java的Collection集合时就听过过Collection是非线程安全的，但一般很少告诉你如何实现现成安全，于是就有了这个方法

synchronized方法返回线程安全的原Collection。该方法保证线程安全不出意外仍是通过synchronized关键字来实现的。

` In order to guarantee serial access,`

需要注意的是这里new了个内部类，
```java
/**
     * Returns a synchronized (thread-safe) collection backed by the specified
     * collection.  In order to guarantee serial access, it is critical that
     * <strong>all</strong> access to the backing collection is accomplished
     * through the returned collection.<p>

     */
    public static <T> Collection<T> synchronizedCollection(Collection<T> c) {
        return new SynchronizedCollection<>(c);
    }

    static <T> Collection<T> synchronizedCollection(Collection<T> c, Object mutex) {
        return new SynchronizedCollection<>(c, mutex);
    }
-------------------------------------------------------------------------------
    /**
     * 需要注意的是这里new了个内部类，
     */
    static class SynchronizedCollection<E> implements Collection<E>, Serializable {
        private static final long serialVersionUID = 3053995032091335093L;

        final Collection<E> c;  // Backing Collection             //原来的引用
        final Object mutex;     // Object on which to synchronize，要同步的对象

        SynchronizedCollection(Collection<E> c) {    构造方法初始化，让c变成当前的引用
            if (c==null)
                throw new NullPointerException();
            this.c = c;
            mutex = this;
        }
        SynchronizedCollection(Collection<E> c, Object mutex) {
            this.c = c;
            this.mutex = mutex;
        }
        //使用mutex 实现互斥，然后仍是调用原Collection相应的方法。
        public int size() {
            synchronized (mutex) {return c.size();}
        }
          //略
        public Iterator<E> iterator() {
            return c.iterator(); // Must be manually synched by user!
        }

        public boolean add(E e) {
            synchronized (mutex) {return c.add(e);}
        }
        public boolean remove(Object o) {
            synchronized (mutex) {return c.remove(o);}
        }
    }
----------------------------------------------------------------------------
*
×
* It is imperative that the user manually synchronize on the returned
* collection when iterating over it:
* <
*  Collection c = Collections.synchronizedCollection(myCollection);
*   //上下两个方法都可以实现现成同步
*  synchronized (c) {
*      Iterator i = c.iterator(); // Must be in the synchronized block
*      while (i.hasNext())
*         foo(i.next());
*  }
*
```
我们来看一个代码少的吧，其实实现套路都差不多。需要注意的是`super`,这里直接使用了`SynchronizedCollection`中的mytex实现互斥锁。父类中没有equals和hashCode()方法，所以。

```java

    static class SynchronizedSet<E>
          extends SynchronizedCollection<E>
          implements Set<E> {
        private static final long serialVersionUID = 487447009682186044L;

        SynchronizedSet(Set<E> s) {
            super(s);
        }
        SynchronizedSet(Set<E> s, Object mutex) {
            super(s, mutex);
        }

        public boolean equals(Object o) {
            if (this == o)
                return true;
            synchronized (mutex) {return c.equals(o);}
        }
        public int hashCode() {
            synchronized (mutex) {return c.hashCode();}
        }
    }

```
我们接着来看看Map是如何实现的，其实思想是一样的，内部类+mutex，返回`a synchronized view of the specified map.`

因为Map中
```java

    /**
     * Returns a synchronized (thread-safe) map backed by the specified
     * map.  In order to guarantee serial access, it is critical that
     * <strong>all</strong> access to the backing map is accomplished
     * through the returned map.<p>
     *
     * @param  m the map to be "wrapped" in a synchronized map.
     * @return a synchronized view of the specified map.
     */
    public static <K,V> Map<K,V> synchronizedMap(Map<K,V> m) {
        return new SynchronizedMap<>(m);
    }
-------------------------------------------------------------------------------
    /**
     * @serial include
     */
    private static class SynchronizedMap<K,V>
        implements Map<K,V>, Serializable {

        public V get(Object key) {
            synchronized (mutex) {return m.get(key);}
        }

        public V put(K key, V value) {
            synchronized (mutex) {return m.put(key, value);}
        }
----------------------------注意这里-----------------------------------------------------
static class Entry<K,V> implements Map.Entry<K,V> {...}
private final class KeySet extends AbstractSet<K> {...}
----------------------------------------------------------------------------------
        private transient Set<K> keySet = null;
        private transient Set<Map.Entry<K,V>> entrySet = null;
        private transient Collection<V> values = null;

        public Set<K> keySet() {
            synchronized (mutex) {
                if (keySet==null)
                    keySet = new SynchronizedSet<>(m.keySet(), mutex);
                return keySet;
            }
        }

        public Set<Map.Entry<K,V>> entrySet() {
            synchronized (mutex) {
                if (entrySet==null)     //重新new，如果等于null
                    entrySet = new SynchronizedSet<>(m.entrySet(), mutex);
                return entrySet;
            }
        }
    }
```

中间的内容等看Map的时候在看

### Checked

该系列方法保证增删的数据都是同类型的。返回`a dynamically typesafe view of the specified collection`

```java
/**
  * Returns a dynamically typesafe view of the specified collection.
  * Any attempt to insert an element of the wrong type will result in an
  * immediate {@link ClassCastException}.  Assuming a collection
  * contains no incorrectly typed elements prior to the time a
  * dynamically typesafe view is generated, and that all subsequent
  * access to the collection takes place through the view, it is
  * <i>guaranteed</i> that the collection cannot contain an incorrectly
  * typed element.
  */
 public static <E> Collection<E> checkedCollection(Collection<E> c,
                                                   Class<E> type) {
     return new CheckedCollection<>(c, type);
 }

------------------------------------------------------------------------

 static class CheckedCollection<E> implements Collection<E>, Serializable {

     final Collection<E> c;
     final Class<E> type;

     void typeCheck(Object o) {       //类型检查
         if (o != null && !type.isInstance(o))
             throw new ClassCastException(badElementMsg(o));
     }

     CheckedCollection(Collection<E> c, Class<E> type) {
         if (c==null || type == null)
             throw new NullPointerException();
         this.c = c;
         this.type = type;
     }

     public boolean contains(Object o) { return c.contains(o); }
     public Object[] toArray()         { return c.toArray(); }
     public boolean remove(Object o)   { return c.remove(o); }

     public Iterator<E> iterator() {
         final Iterator<E> it = c.iterator();
         return new Iterator<E>() {
             public boolean hasNext() { return it.hasNext(); }
             public E next()          { return it.next(); }
             public void remove()     {        it.remove(); }};
     }

     public boolean add(E e) {
         typeCheck(e);
         return c.add(e);
     }

      //省略了一些
```
### checkedSet
```java
/**
 * Returns a dynamically typesafe view of the specified set.
 * Any attempt to insert an element of the wrong type will result in
 * an immediate {@link ClassCastException}.  Assuming a set contains
 * no incorrectly typed elements prior to the time a dynamically typesafe
 * view is generated, and that all subsequent access to the set
 * takes place through the view, it is <i>guaranteed</i> that the
 * set cannot contain an incorrectly typed element.
 *
 * <p>A discussion of the use of dynamically typesafe views may be
 * found in the documentation for the {@link #checkedCollection
 * checkedCollection} method.
 *
 * <p>The returned set will be serializable if the specified set is
 * serializable.
 *
 * <p>Since {@code null} is considered to be a value of any reference
 * type, the returned set permits insertion of null elements whenever
 * the backing set does.
 *
 * @param s the set for which a dynamically typesafe view is to be
 *          returned
 * @param type the type of element that {@code s} is permitted to hold
 * @return a dynamically typesafe view of the specified set
 * @since 1.5
 */
public static <E> Set<E> checkedSet(Set<E> s, Class<E> type) {
    return new CheckedSet<>(s, type);
}

/**
 * @serial include
 */
static class CheckedSet<E> extends CheckedCollection<E>
                             implements Set<E>, Serializable
{
    private static final long serialVersionUID = 4694047833775013803L;

    CheckedSet(Set<E> s, Class<E> elementType) { super(s, elementType); }

    public boolean equals(Object o) { return o == this || c.equals(o); }
    public int hashCode()           { return c.hashCode(); }
}

```

### Empty

保证返回一个空的Collection。

```java
/**
 * Returns the empty set (immutable).  This set is serializable.
 * Unlike the like-named field, this method is parameterized.
 *
 * <p>This example illustrates the type-safe way to obtain an empty set:
 * <pre>
 *     Set&lt;String&gt; s = Collections.emptySet();
 * </pre>
 */
public static final <T> Set<T> emptySet() {
    return (Set<T>) EMPTY_SET;
}

/**
 * @serial include
 */
private static class EmptySet<E>
    extends AbstractSet<E>
    implements Serializable
{

    public Iterator<E> iterator() { return emptyIterator(); }

    public int size() {return 0;}                 //为 0
    public boolean isEmpty() {return true;}       //为空吗？是啊

    public boolean contains(Object obj) {return false;}
    public <T> T[] toArray(T[] a) {
        if (a.length > 0)
            a[0] = null;                           //注定为null啊
        return a;
    }
}
```
其他大同小异，不多介绍了
### Singleton

保证生成的Collection只包含一个元素。看看人家底层是怎么实现的

```java
public static <T> List<T> singletonList(T o) {
    return new SingletonList<>(o);
}
-------------------------------------------------------------------------------
private static class SingletonList<E>
    extends AbstractList<E>
    implements RandomAccess, Serializable {


    private final E element;

    SingletonList(E obj)     {element = obj;}

    public Iterator<E> iterator() {
        return singletonIterator(element);
    }

    public int size()    {return 1;}       //直接返回1，

    public boolean contains(Object obj) {return eq(obj, element);}

    public E get(int index) {
        if (index != 0)                 //之间给你抛个异常，不服不服？Size等于
          throw new IndexOutOfBoundsException("Index: "+index+", Size: 1");
        return element;
    }
}
```

说起异常了，我们看看异常体系的结构图.注意设计思想，如果我们要设计可以直接来个BaseException继承·`RuntimeException`。其他类在实现这个`BaseException`。其他的也类似如：BaseDAO、BaseAction。。。

![](https://i.imgur.com/MYDgjGm.jpg)


### frequency

统计某个元素在Collection中出现的频率
```java
/**
 * Returns the number of elements in the specified collection equal to the
 * specified object.
 */
public static int frequency(Collection<?> c, Object o) {
    int result = 0;
    if (o == null) {
        for (Object e : c)
            if (e == null)      //这里判断null出现了几次。
                result++;
    } else {
        for (Object e : c)
            if (o.equals(e))   //用queals()方法
                result++;      //result++
    }
    return result;
}
----------------------------补充----------------------------------------------------
/**
 * Returns true if the specified arguments are equal, or both null.
 */
static boolean eq(Object o1, Object o2) {
    return o1==null ? o2==null : o1.equals(o2);
}
```

### disjoint
如果两个指定的集合没有相同元素则返回true。

```java
/**
     * Returns {@code true} if the two specified collections have no
     * elements in common.
     */
    public static boolean disjoint(Collection<?> c1, Collection<?> c2) {
      // The collection to be used for contains().
      Collection<?> contains = c2;
      // The collection to be iterated.
      Collection<?> iterate = c1;

        if (c1 instanceof Set) {

            iterate = c2;
            contains = c1;
        } else if (!(c2 instanceof Set)) {

            int c1size = c1.size();
            int c2size = c2.size();
            if (c1size == 0 || c2size == 0) {
                // At least one collection is empty. Nothing will match.
                return true;
            }
            if (c1size > c2size) {
                iterate = c2;
                contains = c1;
            }
        }
        for (Object e : iterate) {
            if (contains.contains(e)) {
               // Found a common element. Collections are not disjoint.
                return false;
            }
        }

        // No common elements were found.    //检查了一遍没有相同的，则
        return true;
    }
```
### addAll
添加指定的元素到指定集合中。 `@return <tt>true</tt> if the collection changed as a result of the call` 也可以是数组和`Arrays.asList`效果一样。
```java
/**
 * Adds all of the specified elements to the specified collection.
 * Elements to be added may be specified individually or as an array.
 * The behavior of this convenience method is identical to that of
 * <tt>c.addAll(Arrays.asList(elements))</tt>, but this method is likely
 * to run significantly faster under most implementations.
 */
@SafeVarargs
public static <T> boolean addAll(Collection<? super T> c, T... elements) {
    boolean result = false;
    for (T element : elements)
        result |= c.add(element);      //难道这就是传说中的丨(gun)吗？
    return result;                    //其实是给result添加一个属性值，也就是`c.add(element)`
}
```
###  AsLIFOQueue

返回一个后进先出的双端队列，add映射到push，remove映射到pop，当你需要一个后进先出的队列时这个方法非常有用。

```java
/**
     * Returns a view of a {@link Deque} as a Last-in-first-out (Lifo)
     * {@link Queue}. Method <tt>add</tt> is mapped to <tt>push</tt>,
     * <tt>remove</tt> is mapped to <tt>pop</tt> and so on. This
     * view can be useful when you would like to use a method
     * requiring a <tt>Queue</tt> but you need Lifo ordering.
     */
    public static <T> Queue<T> asLifoQueue(Deque<T> deque) {
        return new AsLIFOQueue<>(deque);
    }
-------------------------------------------------------------------
    /**
     * @serial include
     */
    static class AsLIFOQueue<E> extends AbstractQueue<E>
        implements Queue<E>, Serializable {
        private static final long serialVersionUID = 1802017725587941708L;
        private final Deque<E> q;
        AsLIFOQueue(Deque<E> q)           { this.q = q; }
        public boolean add(E e)           { q.addFirst(e); return true; }
        public boolean offer(E e)         { return q.offerFirst(e); }
        public E poll()                   { return q.pollFirst(); }
        public E remove()                 { return q.removeFirst(); }
        public E peek()                   { return q.peekFirst(); }
        public E element()                { return q.getFirst(); }
        public void clear()               {        q.clear(); }
        public int size()                 { return q.size(); }
        public boolean isEmpty()          { return q.isEmpty(); }
        public boolean contains(Object o) { return q.contains(o); }
        public boolean remove(Object o)   { return q.remove(o); }
        public Iterator<E> iterator()     { return q.iterator(); }
        public Object[] toArray()         { return q.toArray(); }
        public <T> T[] toArray(T[] a)     { return q.toArray(a); }
        public String toString()          { return q.toString(); }
        public boolean containsAll(Collection<?> c) {return q.containsAll(c);}
        public boolean removeAll(Collection<?> c)   {return q.removeAll(c);}
        public boolean retainAll(Collection<?> c)   {return q.retainAll(c);}
        // We use inherited addAll; forwarding addAll would be wrong  转发的时候可能会出错。
    }
```

`Collections`到这里算是结束了，中午时再看来看看Collection中的方法，其具体实现在子子类中。

晚安，早安。gogogo～收获蛮大的。
