package cn.sxt.test;

import org.junit.Test;

/**
 * Created by beichunming on 2017/4/23.
 */
public class TestImplList {
    @Test
    public void testCustomArrayList(){
        CustomArrayList arrayList = new CustomArrayList();
        arrayList.add(10);
        arrayList.add(1,20);
        arrayList.add(30);
        arrayList.add(40);
        Integer obj =(Integer)arrayList.find(3);
        arrayList.printArrayList();
        Integer reObj =(Integer) arrayList.remove(1);

        System.out.println();
        System.out.println(obj);
        System.out.println("reObj--"+reObj);
        //arrayList.printArrayList();


    }
}
