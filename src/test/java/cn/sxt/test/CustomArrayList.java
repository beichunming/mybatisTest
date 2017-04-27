package cn.sxt.test;

/**
 * Created by beichunming on 2017/4/23.
 *
 */
public class CustomArrayList {
    private  Object data[];   //用于存储数据
    private  int size = 0;    //列表中所添加的元素个数
    /**
     *  获取列表的大小
     */
    public int getSize(){
        return this.size;
    }
    /**
     *  指定数组的大小
     *  http://moyunyu.iteye.com/blog/1472081
     */
    public CustomArrayList(int capacity){
        //判断参数是否合法
        if (capacity<0){
            System.out.println("数组的长度不能为小于0");
            throw new IllegalArgumentException("列表的容量不能是负数");
        }else {
            //实例化数组
            this.data = new  Object[capacity];
        }
    }

    /**
     * 无参的构造函数，指定数组的初始大小10
     * @param 
     */
    public CustomArrayList(){
        this(10);
    }

    /**
     * 检查给定的索引是否合法
     * @param index
     * @return
     */
    public boolean rangeCheck(int index){
        if (index>=size || index<0){
            throw  new  IndexOutOfBoundsException("你指定的索引越界,列表大小 : " + size
                    + ", 你指定的索引: " + index);
        }
        return true;
    }

    /**
     *  作用1: 扩容, 复制原数组
     *  作用2: 扩容, 复制原数组并在指定索引插入对象,
     *  仅当index==-1, o==null
     */
    public void checkCapacity(int index,Object o){
        if (size>=data.length){
            //实例化一个新数组
            Object newData[] = new Object[size*2];
            if (index==-1&&o==null){
                //将数组data的元素拷贝到数组newData中去
                System.arraycopy(data,0,newData,0,size);
            }else {
                // 将要插入索引位置前面的对象拷贝
                System.arraycopy(data,0,newData,0,index);
                // 插入对象
                newData[index] = o;
                // 将要插入索引位置后面的对象拷贝
                System.arraycopy(data,index,newData,index+1,size-index);
            }
            // 将引用交给data
            data = newData;
            // 让GC处理
            newData = null;
        }
    }
    /**
     * 打印列表中的数据
     */
    public void printArrayList(){
        for (int i =0;i<size;i++){
            if (i==0){
                System.out.print("{"+data[i]);
                if (size==1){
                    System.out.print("}");
                }
            }else if (i>0&&i<size-1){
                System.out.print(","+data[i]);
            }else if (i==size-1){
                System.out.print(","+data[i]+"}");
            }
            
        }
    }
    /**
     *
     * ===============================================================================
     * @param o
     *  在列表尾部插入对象 
     * @return
     * 要插入的对象  插入成功则返回true
     */
    public boolean add(Object o){
        // 检查是否需要扩容
        checkCapacity(-1,null);
        // 添加对象到列表尾端
        data[size]=o;
        // 列表长度加1
        size++;
        // 添加成功, 返回true
        return true;
    }

    /**
     * 在指定索引位置插入对象 
     * @param index 指定的索引位置 
     * @param obj   要插入的对象 
     */
    public void add(int index,Object obj){
        // 尾部直接插入
        if (index==size){
            add(obj);
        }else if(rangeCheck(index)){// 检查索引值的合法性
            // 如果不需要扩容, 则直接进行操作, 否则进行扩容插入
            if (size<data.length){
                // 将索引index-1后的所有对象后移
                System.arraycopy(data,index,data,index+1,size-index);
                // 直接插入对象
                data[index]=obj;
            }else {
                // 扩容, 并插入对象
                checkCapacity(index,obj);
            }
            // 列表长度加一
            size++;
        }
    }

    /**
     *  删除指定位置的对象并将删除的对象返回 
     * @param index  指定的索引位置 
     * @return   索引越界返回null, 否则返回删除的对象
     */
    public  Object remove(int index){
        if (index >= size){
            throw new IndexOutOfBoundsException("你指定的索引越界,列表大小 : " + size
                    + ", 你指定的索引: " + index);
        }
        // 索引检查
        if (rangeCheck(index)){
            // 保存对象
            Object obj = data[index];
            // 将后边的前移 
           System.arraycopy(data,index+1,data,index,size-index-1);
            // 列表长度加一
            size--;
            // 将要删除的对象返回
            return obj;
        }
        return null; 
    }


    /**
     *  删除列表尾部的对象
     * @param o  尾部的对象 
     * @return   删除成功则返回true, 没有指定对象返回false 
     */
    public boolean remove(Object o){
        // 遍历数组, 寻找指定对象
        for (int i = 0;i<size;i++){
            if (o.equals(data[i])){
                remove(i);
                return true;
            }
        }
        // 没有找到返回false
        return false;
    }

    /**
     * 查找指定索引位置上的对象 
     * @param index  指定的索引位置
     * @return  该位置上的对象
     */
    public Object find(int index){
        // 检查索引的合法性
        rangeCheck(index);
        //  // 返回指定索引位置的对象
        return data[index];
    }

    /**
     * 判断列表中是否含有指定对象 
     * @param o   指定的对象 
     * @return  含有指定对象返回true
     */
    public boolean contain(Object o){
        // 循环遍历列表
        for (int i = 0;i<size;i++){
            if (data[i].equals(o)){
                return true;
            }
        }
        return false;
    }

    /**
     * 修改指定位置上的对象 
     * @param index 指定的索引位置 
     * @param o  
     * @return 修改成功返回true
     */
    public Object change(int index, Object o){
        // 检查指定索引位置的合法性
        rangeCheck(index);
        // 储存原对象
        Object old = data[index];
        // 替换
        data[index]=o;
        return old;
    }
}

