/**
*
*
*/
public class MyArrayList /*implements List*/ {     //

		//ÓÃÓÚ±£´æÈÝÆ÷µÄ¶ÔÏó
		private Object[] elementData;

		private int size;

		//½øÐÐ³õÊ¼»¯£¬
		public MyArrayList(int initialCapacity) {
			if (initialCapacity < 0) {
				throw new RuntimeException("");
			}
			this.elementData = new Object[initialCapacity];
		}

		public MyArrayList() {
			this(10);          
		}

		Object elementData(int index) {
			return elementData[index];
		}

		public int size() {
			return size;
		}

		public boolean add(Object o) {
			//ÅÐ¶ÏÊÇ·ñÄÜ×°ÏÂ
			if (size >=  elementData.length ) {
				Object[] newData = new Object[size*2 + 1];
				//System.arraycopy(elementData,0,newData,0,elementData.length);
				for (int i = 0; i < elementData.length; i++) {
					newData[i] = elementData[i];
				}
				elementData =  newData;
			}
			elementData[size++] = o;            
			return true;
		}

		public Object get(int index) {
			rangeCheck(index);
			return elementData[index];
		}

		//ÉèÖÃÖ¸¶¨Î»ÖÃÉÏµÄÔªËØ
		public Object set(int index,Object o) {
			rangeCheck(index);

			Object oldValue = elementData(index);
			elementData[index] = o;
			return oldValue;
		}

		//ÅÐ¶ÏË÷ÒýÊ×´Î³öÏÖµÄÎ»ÖÃ
		public int indexOf(Object o) {
			if (o ==  null) {
				for (int i = 0; i < size; i++) {
					if (elementData[i] == null) {
						return i;
					}
				}
			}
			if (o != null) {
				for (int i = 0; i < size; i++) {
					if (o.equals(elementData[i])) {
						return i;
					}
				}
			}
			 return -1;
		}

		//ÅÐ¶ÏË÷Òý×îºóÒ»´Î³öÏÖµÄÎ»ÖÃ
		public int lastIndex(Object o) {
			if (o == null) {
				for (int i = size - 1; i >= 0 ;i-- ) {
					if (elementData[i] == null) {
						return i;
					}
				}
			}else {
				for (int i = size - 1; i >= 0; i--) {
					if (o.equals(elementData[i])) {
						return i;
					}
				}
			}
			return -1;
		}

		//É¾³ýÖ¸¶¨ÔªËØµÄË÷Òý
		public Object remove(int index) {
		//±ß½ç¼ì²é£¬
		 rangeCheck(index);

        Object oldValue = elementData(index);

        int numMoved = size - index - 1;                //¼ÆËãÒªÒÆ³ýµÄÊý
        if (numMoved > 0)
        	//µ÷ÓÃ±¾µØ·½·¨µÄ¸´ÖÆ·½·¨£¬
            System.arraycopy(elementData, index+1, elementData, index,
                             numMoved);
        elementData[--size] = null; // ÈÃËùÓÐÎªnull£¬²¢ÇÒsize--¡£

        return oldValue;	        //É¾³ýÍê³Éºó»á·µ»Ø¾ÉµÄÔªËØ
		}

		//É¾³ýÄ³¸öÔªËØ£¬ÏÈÅÐ¶ÏÊÇ·ñÊÇnull£¬ÔÙÈ¥µ÷ÓÃequals¶Ô¶Ô
		public boolean remove(Object  o) {
			if (o == null) {
				for (int i = 0;i < size ;i++ ) {
					if (elementData[i] ==  null) {
						fastRemove(i);
						return true;
					}
				}
			}else {
				for (int index = 0; index < size; index++) {
					if (o.equals(elementData[index])) {
						fastRemove(index);
						return true;
					}
				}
			}
			return false;
		}

		private void fastRemove(int index) {
			int numMoved = size - index -1;
			if (numMoved > 0) {
				System.arraycopy(elementData,index + 1,elementData,index,numMoved);
			}
			elementData[--size] = null;    //问题出在这里，先减1在计算。
		}

		public void clear() {
			for (int i = 0; i < size; i++) {
				elementData[i] = null;          //ÈÃÀ¬»ø»ØÆ÷È¥´¦Àí
			}
			size = 0;
		}


		//±ß½ç¼ì²é
		public void rangeCheck(int index) {
			if (index >= size) {
				throw new RuntimeException("IndexOutOfBoundsException" + outOfBoundsMsg(index));
			}
		}

		public String outOfBoundsMsg(int index) {
			return " index: " + index + "size" + size; 
		}


		public static void main(String[] args) {

			MyArrayList list = new MyArrayList(1);

			list.add("1111");
			list.add("2222");
			//System.out.println(list.size());     //2
			//System.out.println(list.get(3));

			MyArrayList list1 = new MyArrayList(4);
			list1.add("aa");
			list1.add("bb");
			list1.add("cc");
			list1.add("dd");
			System.out.println(list1.size());
			System.out.println(list1.indexOf("cc"));   //2
			System.out.println("-----------------------------------------------");
			list1.remove("aa");
			list1.remove(1);

			for (int i = 0; i < list1.size(); i++ ) {
				System.out.println(list1.get(i));
			}

			
		}
}