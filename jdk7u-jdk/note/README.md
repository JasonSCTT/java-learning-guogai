## 容器相关的操作及其源码分析

## 说明
- 1、本文是基于JDK 7 分析的。JDK 8 待我工作了得好好研究下。Lambda、Stream。
- 2、因为个人能力有限，只能以模仿的形式+自己的理解写笔记。如有不对的地方还请谅解。
- 3、记录的比较乱，但是对自己加深印象还是蛮有作用的。
- 4、笔记放[github](https://github.com/guoxiaoxu/java-learning-guogai/tree/master/code/CollectionMap/note)了，有兴趣的可以看看。喜欢的可以点个star。
-

### 1、Array、Arrays

在了解容器之前我们先来看下重点的数据吧，还有Arrays工具类。

首先看一个栗子，利用数组计算大数字。[完整的点这里](https://github.com/guoxiaoxu/java-learning-guogai/blob/1fa934a17730fede69ee149e3c007751eed8542f/code/sort/src/com/guo/demo01/ArraySort.java)，重点思想就是计算数组中的每一个数，关键字是怎么进和留。

```java
/**
 * Created by guo on 2018/2/2.
 * 利用数组计算大数字
 */
public static int[] get(int[] ints, int num) {
    //计算每一位
    for (int i = 0; i < ints.length; i++) {
        ints[i] *= num;

    }
    //进和留
    for (int i = ints.length - 1; i > 0; i--) {

        //把个位数除10，加上前面的数
        ints[i - 1] += ints[i] / 10;
        //把最后的数模10，剩余个位数。1-9
        ints[i] = ints[i] % 10;
    }
    return ints;
}
```
需要注意的还有一个System.arrayscopy()方法，那就是底层数组的拷贝，这个也是关键点。[完整代码](https://github.com/guoxiaoxu/java-learning-guogai/blob/1fa934a17730fede69ee149e3c007751eed8542f/code/sort/src/com/guo/study/ArrayCopy.java)

```java
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
 * dest：目标：把数据拷贝到哪一个数组中
 * srcpos：从原数组中哪一个位置开始拷贝
 * destPos：在目标数组开始存放的位置
 * length：拷贝的个数
 */
static void arrayCopy(int[] src, int srcPos, int[] dest, int destPos, int length) {
    if(srcPos < 0 || destPos < 0 || length < 0) {
        throw  new RuntimeException("出异常了，重新检查");
    }
    for (int index = srcPos; index < srcPos + length; index++) {
        dest[destPos] = src[index];
        destPos++;

    }
}

static void print(int[] arr) {
    String str = "[";
    for (int i = 0; i < arr.length; i++) {
        str += arr[i];
        if (i != arr.length - 1) {   //不是最后一个元素
            str = str + ',';
        }
    }
    str = str + "]";
    System.out.println(str);
}
```
其他内容不是今天的重点，这里只是容器中需要数组的底层Copy。

### Arrays

Arrays类是Java中用于操作数组的类，使用这个工具类可以减少平常很多工作量。 [主题框架参考这里，写的非常不错](https://www.jianshu.com/p/d083332c3c29)，

我们具体现看看它有哪些方法

- 1、sort         排序
- 2、binarySearch 二分查找(折半查找)
- 3、equals       比较
- 4、fill         填充
- 5、asList       转列表 记得一个toArray吗？
- 6、indexOf     str首次出现的位置
- 6、hash         哈希(重点)
- 7、toString     重写Object中方法

首先来看看排序的

```java
/**
 * Sorts the specified array into ascending numerical order.
 * @param a the array to be sorted
 */
public static void sort(int[] a) {
    DualPivotQuicksort.sort(a);
}
```
需要关注的是底层默认是按升序(asc),还有调用这个`DualPivotQuicksort.sort(a)`方法是什么鬼？中文意思为双轴快速排序，它在性能上优于传统的单轴快速排序。 它是不稳定的。

重点在这里`O(n log(n))`,还有这句话` and is typically
  faster than traditional (one-pivot) Quicksort implementations.` 具体看大佬的博文[友情提示](https://www.jianshu.com/p/6d26d525bb96)

```java
/**
 * This class implements the Dual-Pivot Quicksort algorithm by
 * Vladimir Yaroslavskiy, Jon Bentley, and Josh Bloch. The algorithm
 * offers O(n log(n)) performance on many data sets that cause other
 * quicksorts to degrade to quadratic performance, and is typically
 * faster than traditional (one-pivot) Quicksort implementations.
 */
final class DualPivotQuicksort {
}
```

上面的sort传进去的是`int[] a`,接下来看看传`Object`对象的。`All elements in the array must implement the Comparable interface. `
```java
/**
 * Sorts the specified array of objects into ascending order, according
 * to the {@linkplain Comparable natural ordering} of its elements.
 * All elements in the array must implement the {@link Comparable}
 * interface.  Furthermore, all elements in the array must be
 * <i>mutually comparable</i> (that is, {@code e1.compareTo(e2)} must
 * not throw a {@code ClassCastException} for any elements {@code e1}
 * and {@code e2} in the array).
 */
public static void sort(Object[] a) {
    if (LegacyMergeSort.userRequested)
        legacyMergeSort(a);
    else
        ComparableTimSort.sort(a);
}
```

在来看看带泛型参数的，这个重点那，有三个点需要关注，Comparator，`ClassCastException` 和 `TimSort算法` 是从JDK 7 开始默认支持，

```java
/**
 * Sorts the specified array of objects according to the order induced by
 * the specified comparator.  All elements in the array must be
 * mutually comparable by the specified comparator must not throw a  * *ClassCastException  for any elements and  in the array.
 */
public static <T> void sort(T[] a, Comparator<? super T> c) {
    if (LegacyMergeSort.userRequested)
        legacyMergeSort(a, c);
    else
        TimSort.sort(a, c);     //注意这个TimSort
}
```
整体上在看看这几个到底啥意思？

```java
/**
*大概意思就是旧的归并算法使用了系统属性，可能会导致循环依赖，不是能是静态boolean，未来版本将移除
 * Old merge sort implementation can be selected (for
 * compatibility with broken comparators) using a system property.
 * Cannot be a static boolean in the enclosing class due to
 * circular dependencies. To be removed in a future release.
 */
static final class LegacyMergeSort {
     private static final boolean userRequested =
         java.security.AccessController.doPrivileged(
             new sun.security.action.GetBooleanAction(
                 "java.util.Arrays.useLegacyMergeSort")).booleanValue();
 }

//传进Object的排序，
 public static void sort(Object[] a) {
     if (LegacyMergeSort.userRequested)
         legacyMergeSort(a);
     else
         ComparableTimSort.sort(a);
 }

 /** To be removed in a future release. */
 private static void legacyMergeSort(Object[] a) {
     Object[] aux = a.clone();
     mergeSort(aux, a, 0, a.length, 0);
 }
//都要移除啊，看来的JDK8了
 /** To be removed in a future release. */
 private static void legacyMergeSort(Object[] a,
                                     int fromIndex, int toIndex) {
     rangeCheck(a.length, fromIndex, toIndex);
     Object[] aux = copyOfRange(a, fromIndex, toIndex);
     mergeSort(aux, a, fromIndex, toIndex, -fromIndex);
 }

 /**
 * 列表大小低于插入将优先使用归并算法，也要移除啊，
  * Tuning parameter: list size at or below which insertion sort will be
  * used in preference to mergesort.
  * To be removed in a future release.
  */
 private static final int INSERTIONSORT_THRESHOLD = 7;

 /**
  * Src is the source array that starts at index 0
  * Dest is the (possibly larger) array destination with a possible offset
  * low is the index in dest to start sorting
  * high is the end index in dest to end sorting
  * off is the offset to generate corresponding low, high in src
  * To be removed in a future release.
  */
 private static void mergeSort(Object[] src,
                               Object[] dest,
                               int low,
                               int high,
                               int off) {
     int length = high - low;

     // Insertion sort on smallest arrays
     //小数组将使用普通的插入算法
     if (length < INSERTIONSORT_THRESHOLD) {
         for (int i=low; i<high; i++)
             for (int j=i; j>low &&
                      ((Comparable) dest[j-1]).compareTo(dest[j])>0; j--)
                 swap(dest, j, j-1);
         return;
     }
     // Merge sorted halves (now in src) into dest

 }
 ----------------------------------------------------------------------------
 /**
 * Swaps x[a] with x[b]. 这个可以理解，面试手写算法时，可以写这个那。
 */
private static void swap(Object[] x, int a, int b) {
    Object t = x[a];
    x[a] = x[b];
    x[b] = t;
}
```

从上面的逻辑可以看出，它的实现方式分为两种，一种是通过`Arrays`中的归并算法实现的,另外一种采用了`TimSOrt算法`，

这个排序算法是稳定的，JDK8已经删除了，我们来看看8是怎么实现的。
```java
/**
 * Sorts the specified array of objects according to the order induced by
 * the specified comparator.  All elements in the array must be
 * <i>mutually comparable</i> by the specified comparator (that is,
 * {@code c.compare(e1, e2)} must not throw a {@code ClassCastException}
 * for any elements {@code e1} and {@code e2} in the array).
 * @since 1.8
 */
@SuppressWarnings("unchecked")
public static <T> void parallelSort(T[] a, Comparator<? super T> cmp) {
    if (cmp == null)
        cmp = NaturalOrder.INSTANCE;
    int n = a.length, p, g;
    if (n <= MIN_ARRAY_SORT_GRAN ||
        (p = ForkJoinPool.getCommonPoolParallelism()) == 1)
        TimSort.sort(a, 0, n, cmp, null, 0, 0);  //还是这个
    else
        new ArraysParallelSortHelpers.FJObject.Sorter<T>
            (null, a,
             (T[])Array.newInstance(a.getClass().getComponentType(), n),
             0, n, 0, ((g = n / (p << 2)) <= MIN_ARRAY_SORT_GRAN) ?
             MIN_ARRAY_SORT_GRAN : g, cmp).invoke();
}
```
需要注意的是方法名变成了`parallelSort`并行啊，


这个也是重点，除传入`int[]`之外其他都变成了`parallelSort`.慢慢来吧，先JDK7，在JDK 8.还有就是可以传进其他类型，char、long、byte。etc(等)。

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
### 二分查找，

注意接下来，我们只看泛型有关的方法，其他实现大同小异。因为泛型很重要。

```java
/**
 * Searches the specified array for the specified object using the binary
 * search algorithm.  The array must be sorted into ascending order
 * according to the specified comparator (as by the
 * {@link #sort(Object[], Comparator) sort(T[], Comparator)}
 * method) prior to making this call.  If it is
 * not sorted, the results are undefined.
 * If the array contains multiple
 * elements equal to the specified object, there is no guarantee which one
 * will be found.
 */
public static <T> int binarySearch(T[] a, T key, Comparator<? super T> c) {
    return binarySearch0(a, 0, a.length, key, c);
}
/**
 * Searches a range of
 * the specified array for the specified object using the binary
 * search algorithm.
 * The range must be sorted into ascending order
 * according to the specified comparator (as by the
 * {@link #sort(Object[], int, int, Comparator)
 * sort(T[], int, int, Comparator)}
 * method) prior to making this call.
 * If it is not sorted, the results are undefined.
 * If the range contains multiple elements equal to the specified object,
 * there is no guarantee which one will be found.

 */
public static <T> int binarySearch(T[] a, int fromIndex, int toIndex,
                                   T key, Comparator<? super T> c) {
    rangeCheck(a.length, fromIndex, toIndex);
    return binarySearch0(a, fromIndex, toIndex, key, c);
}
```
`rangeCheck`作用就是检查边界，看数据是否越界，会抛出`ArrayIndexOutOfBoundsException`

`binarySearch0`这是什么鬼？点进去看看

要明白首先看参数
```java
//fromIndex就是开始索引(inclusive)，toIndex结束(exclusive)，key就是指定的数。
* @param fromIndex the index of the first element (inclusive) to be
*          searched
* @param toIndex the index of the last element (exclusive) to be searched
* @param key the value to be searched for
* @return index of the search key, if it is contained in the array
*         within the specified range;
 */
```

```java
/**
* 二分查找法(折半查找)：前提是在已经排好序的数组中，通过将待查找的元素
* 与中间索引值对应的元素进行比较，若大于中间索引值对应的元素，去右半边查找，
* 否则，去左边查找。依次类推。直到找到位置；找不到返回一个负数
*
* Like public version, but without range checks.
*这里没有边界检查，这才是二分查找重点。
*/
 private static <T> int binarySearch0(T[] a, int fromIndex, int toIndex,
                                      T key, Comparator<? super T> c) {
     if (c == null) {  //先判断
         return binarySearch0(a, fromIndex, toIndex, key);
     }
     int low = fromIndex;       //开始下标
     int high = toIndex - 1;    //结束下标

     while (low <= high) {
         int mid = (low + high) >>> 1;      //这里用了向右移2位(左边补0)，/2 向左就是 *2.
         T midVal = a[mid];
         int cmp = c.compare(midVal, key);    //比较key是在左边还是右边
         if (cmp < 0)                         //小于0意味着key大，
             low = mid + 1;                   //则去掉左边的值。中间的索引+1就是新的开始下标
         else if (cmp > 0)                    //key比中间的小
             high = mid - 1;                  //则去掉右边的，中间下标-1，就是新的结束下标
         else
             return mid;                      // key found
     }
     return -(low + 1);                       // key not found.这里是-1 -(0 + 1)
 }
```
### equals

接下来在看看比较的，这个是重点。这里只看`Object[]`，其他还有很多，如int、byte、char

重点看这句话：

In other words, the two arrays are equal if
 they contain the same elements in the same order.

 Also, two array references are considered equal if both are null

 以相同的顺序，并且互相包含，则返回true，

 两个数组引用都为null，则返回true。

```java
/**
 * Returns <tt>true</tt> if the two specified arrays of Objects are
 * <i>equal</i> to one another.  The two arrays are considered equal if
 * both arrays contain the same number of elements, and all corresponding
 * pairs of elements in the two arrays are equal.  Two objects <tt>e1</tt>
 * and <tt>e2</tt> are considered <i>equal</i> if <tt>(e1==null ? e2==null
 * : e1.equals(e2))</tt>.  In other words, the two arrays are equal if
 * they contain the same elements in the same order.  Also, two array
 * references are considered equal if both are <tt>null</tt>.<p>
 *
 * @param a one array to be tested for equality
 * @param a2 the other array to be tested for equality
 * @return <tt>true</tt> if the two arrays are equal  //相等返回true
 */
public static boolean equals(Object[] a, Object[] a2) {
    if (a==a2)                       //注意，这里是地址的比较，
        return true;
    if (a==null || a2==null)        //任意一个为null，返回false
        return false;

    int length = a.length;
    if (a2.length != length)        //数组长度比较
        return false;

    for (int i=0; i<length; i++) {
        Object o1 = a[i];
        Object o2 = a2[i];
        if (!(o1==null ? o2==null : o1.equals(o2)))   //这里使用了三目运算符
            return false;     //o1等于null吗?,为真，继o2等于null吗，为真，继续
                              //两个相等吗？为真，然后在取反。
    }

    return true;              //当以上都不成立的时候返回true。
}
----------------------------------------------------------------------------
public static boolean equals(int[] a, int[] a2) {
    if (a==a2)
        return true;
    if (a==null || a2==null)
        return false;

    int length = a.length;
    if (a2.length != length)
        return false;

    for (int i=0; i<length; i++)
        if (a[i] != a2[i])
            return false;

    return true;
}
```

### fill 填充

就是循环进行赋值填充，
```java
/**
 * Assigns the specified int value to each element of the specified array
 * of ints.
 * @param a the array to be filled
 * @param val the value to be stored in all elements of the array
 */
public static void fill(int[] a, int val) {
    for (int i = 0, len = a.length; i < len; i++)
        a[i] = val;
}

-----------------------------------------------------------------------------
/**
 * Assigns the specified Object reference to each element of the specified
 * range of the specified array of Objects.  The range to be filled
 * extends from index <tt>fromIndex</tt>, inclusive, to index
 * <tt>toIndex</tt>, exclusive.  (If <tt>fromIndex==toIndex</tt>, the
 * range to be filled is empty.)
 */
public static void fill(Object[] a, int fromIndex, int toIndex, Object val) {
    rangeCheck(a.length, fromIndex, toIndex);
    for (int i = fromIndex; i < toIndex; i++)
        a[i] = val;
}
```

这里主要想再次看下这个边界检查

主要作用就是检查`a.length`是否在开始下标和结束下标之间。

```java
/**
 * Checks that {@code fromIndex} and {@code toIndex} are in
 * the range and throws an appropriate exception, if they aren't.
 */
private static void rangeCheck(int length, int fromIndex, int toIndex) {
    if (fromIndex > toIndex) {               //开始下标还能比结束大吗？你这不是胡闹吗？
        throw new IllegalArgumentException(    //非法-参数-异常，很常见的。
            "fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")");
    }
    if (fromIndex < 0) {                      //你还没开始呢，能小于0吗？
        throw new ArrayIndexOutOfBoundsException(fromIndex);    //数据下标越界啦，
    }
    if (toIndex > length) {                   //你要查的不再这个范围呢。
        throw new ArrayIndexOutOfBoundsException(toIndex);      //你越界了
    }
}
```

### clone

就像刚开始数组复制的那个一样,

首先这里会抛出大家熟悉的异常`NullPointerException`if original is null

新数组的长度能为负数吗？当然不能啊，所以抛出`NegativeArraySizeException`

这里最为关键的是底层使用了本地方法，实现大概由刚开始那个复制差不多。但，考录的因素太多了，或者是版权，或者根本就不想让我们知道。还有就是这个用底层毕竟效率快啊，直接和系统打交道。

```java
/**
 * Copies the specified array, truncating or padding with zeros (if necessary)
 * so the copy has the specified length.  For all indices that are
 * valid in both the original array and the copy, the two arrays will
 * contain identical values.  For any indices that are valid in the
 * copy but not the original, the copy will contain <tt>(byte)0</tt>.
 * Such indices will exist if and only if the specified length
 * is greater than that of the original array.
 *
 * @param original the array to be copied
 * @param newLength the length of the copy to be returned
 * @return a copy of the original array, truncated or padded with zeros
 *     to obtain the specified length
 * @throws NegativeArraySizeException if <tt>newLength</tt> is negative
 * @throws NullPointerException if <tt>original</tt> is null
 * @since 1.6
 */
public static byte[] copyOf(byte[] original, int newLength) {
    byte[] copy = new byte[newLength];
    System.arraycopy(original, 0, copy, 0,
                     Math.min(original.length, newLength));
    return copy;
}
--------------------------范围复制---------------------------------------------

public static char[] copyOfRange(char[] original, int from, int to) {
    int newLength = to - from;
    if (newLength < 0)
        throw new IllegalArgumentException(from + " > " + to);
    char[] copy = new char[newLength];
    System.arraycopy(original, from, copy, 0,
                     Math.min(original.length - from, newLength));
    return copy;
}
---------------------------底层复制----------------------------------------------
注意，这个类是不能进行实例化的，
public final class System {
/**
 * Copies an array from the specified source array, beginning at the
 * specified position, to the specified position of the destination array.
 * A subsequence of array components are copied from the source
 * array referenced by <code>src</code> to the destination array
 * referenced by <code>dest</code>. T
* @param      src      the source array.
* @param      srcPos   starting position in the source array.
* @param      dest     the destination array.
* @param      destPos  starting position in the destination data.
* @param      length   the number of array elements to be copied.
*/
public static native void arraycopy(Object src,  int  srcPos,
                                   Object dest, int destPos,
                                   int length);
}
```

### asList

需要注意的是这里直接new了一个内部的ArrayList，实现类两个接口。

第一段话说明：返回一个指定数组的固定列表。并不是`java.util.ArrayList`,而且它不支持添加和移除元素，不支持扩容。但支持序列化和随机存储，我们具体来看看

```java
/**
    * Returns a fixed-size list backed by the specified array.  (Changes to
    * the returned list "write through" to the array.)  This method acts
    * as bridge between array-based and collection-based APIs, in
    * combination with {@link Collection#toArray}.  The returned list is
    * serializable and implements {@link RandomAccess}.
    *
    * <p>This method also provides a convenient way to create a fixed-size
    * list initialized to contain several elements:
    * <pre>
    *     List&lt;String&gt; stooges = Arrays.asList("Larry", "Moe", "Curly");
    * </pre>
    *
    * @param a the array by which the list will be backed
    * @return a list view of the specified array
    */
   @SafeVarargs
   public static <T> List<T> asList(T... a) {
       return new ArrayList<>(a);
   }

```
为了方便，把这个方法抽出来，里面的方法还会继续抽。
```java

   /**
    * @serial include
    */
   private static class ArrayList<E> extends AbstractList<E>
       implements RandomAccess, java.io.Serializable
   {
       private static final long serialVersionUID = -2764017481108945198L;   进行反序列化时验证用的
       private final E[] a;

       ArrayList(E[] array) {        //进行初始化，如果等null，则抛出空指针异常
           if (array==null)
               throw new NullPointerException();
           a = array;               //然后再赋值给a
       }

       public int size() {
           return a.length;
       }

       public Object[] toArray() {    //原来你跑在这里了，
           return a.clone();
       }

       public <T> T[] toArray(T[] a) {
           int size = size();
           if (a.length < size)              //这里不懂
               return Arrays.copyOf(this.a, size,
                                    (Class<? extends T[]>) a.getClass());
           System.arraycopy(this.a, 0, a, 0, size);
           if (a.length > size)
               a[size] = null;
           return a;
       }

       public E get(int index) {
           return a[index];                     //直接返回索引位置的元素
       }

       public E set(int index, E element) {
           E oldValue = a[index];               //直接替换旧的元素
           a[index] = element;
           return oldValue;
       }

       public int indexOf(Object o) {          //o是否首次出现的索引位置，
           if (o==null) {
               for (int i=0; i<a.length; i++)
                   if (a[i]==null)            //  原来是循环判断是否为null
                       return i;
           } else {
               for (int i=0; i<a.length; i++)
                   if (o.equals(a[i]))        //接着在对比是否等于a[i]
                       return i;
           }
           return -1;                       //没有则返回-1
       }

       public boolean contains(Object o) {    //判断是否包含，前提是调用`indexOf(o)`不能等-1
           return indexOf(o) != -1;
       }
   }
```
### hash

这个方法很重要，后续出场的几率很大，其实也很简单，

首先来看一下HashCode和equals方法调用的过程：

```java
/**
 * new String("abc")
 * 1.调用对象的hashCode方法，new String("abc").hashCode()    == 96354
 * 2.集合在容器内找，有没有和96354一样的哈希值，
 * new String("abc")
 * 3.调用对象的hashCode方法，new String("abc").hashCode()    == 96354
 * 4.集合在啊容器内，找到了一个对象也是96354
 * 5.集合会让后来的new String("abc")调用对象的equals(已经有的对象)
 * 5.两个对象哈希值一样，equals方法返回true，集合判断元素重复，
 * new String("adc)
 * 集合调用对象的hashCode方法 new String("adc").hashCode()=  96354
 * 集合去容器中找，有没有96354的对象，找到了
 * 集合让后来的对象 new String("adc").equals(已存在的对象)
 * 两个对象的哈希值一样，equals返回false
 * 集合判定对象没有重复，因此采用桶的方式
 */

 HashSet<String> set = new HashSet<>();
set.add(new String("abc"));
set.add(new String("abc"));
set.add(new String("bbc"));
set.add(new String("bbc"));
System.out.println(set);          //[bbc, abc]
```
这里到底是怎么算出96354的呢？不急，先来看看字符编码，因为Java采用Unicode编码，一般两个字节表示一个字符，ASCLL则一个字节表示一个字符。所以'abc'对应的就是(97+98+99) “ABC”则为(65+66+67)

注意在这里31是一个素数，就不除它自己不能被整除的。

注意下面这个是字符串中的hashCode方法，就是重复计算，

```java
public int hashCode() {
    int h = hash;
    if (h == 0 && value.length > 0) {
        char val[] = value;

        for (int i = 0; i < value.length; i++) {
            h = 31 * h + val[i];
        }
        hash = h;
    }
    return h;
}
```
底下下这个是Arrays类里面的。重写都不一样。也可以自己重新，
```java
/**
  * Returns a hash code based on the contents of the specified array.
  * For any two <tt>byte</tt> arrays <tt>a</tt> and <tt>b</tt>
  * such that <tt>Arrays.equals(a, b)</tt>, it is also the case that
  * <tt>Arrays.hashCode(a) == Arrays.hashCode(b)</tt>.
  *
  * <p>The value returned by this method is the same value that would be
  * obtained by invoking the {@link List#hashCode() <tt>hashCode</tt>}
  * method on a {@link List} containing a sequence of {@link Byte}
  * instances representing the elements of <tt>a</tt> in the same order.
  * If <tt>a</tt> is <tt>null</tt>, this method returns 0.
  *
  * @param a the array whose hash value to compute
  * @return a content-based hash code for <tt>a</tt>
  * @since 1.5
  */
 public static int hashCode(byte a[]) {
     if (a == null)
         return 0;

     int result = 1;
     for (byte element : a)
         result = 31 * result + element;

     return result;    //最后返回
 }

```

### toString
需要注意的是底层使用`StringBuilder`以追加的形式打印输出。
```java
/**
 * Returns a string representation of the contents of the specified array.
 * The string representation consists of a list of the array's elements,
 * enclosed in square brackets (<tt>"[]"</tt>).  Adjacent elements
 * are separated by the characters <tt>", "</tt> (a comma followed
 * by a space).  Elements are converted to strings as by
 * <tt>String.valueOf(byte)</tt>.  Returns <tt>"null"</tt> if
 * <tt>a</tt> is <tt>null</tt>.
 *
 * @param a the array whose string representation to return
 * @return a string representation of <tt>a</tt>
 * @since 1.5
 */
public static String toString(byte[] a) {
    if (a == null)
        return "null";
    int iMax = a.length - 1;  //如果是一个空数据(和null有区别)，(0 - 1)
    if (iMax == -1)
        return "[]";          //则直接输出[]

    StringBuilder b = new StringBuilder();     //这里是可变的，并不是重新创建，只是在追加。
    b.append('[');
    for (int i = 0; ; i++) {
        b.append(a[i]);                         //循环追加a[i],当等于-1的时候最后追加']'打印
        if (i == iMax)
            return b.append(']').toString();
        b.append(", ");
    }
}
```

其实还有许多重载方法，这里就不展示了，我们来看下Person类重写euqals、hashCode、toString是长啥样、

```java
public class Person {
    private String name;
    private int age;

    //Setter、Getter、Constructor略

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (age != person.age) return false;
        if (name != null ? !name.equals(person.name) : person.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
```

这个主题就到这里吧，下一个主题正式进入容器的介绍。gogogo















































































































-
