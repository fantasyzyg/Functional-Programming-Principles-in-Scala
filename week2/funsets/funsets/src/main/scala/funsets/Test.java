package funsets;

import java.util.List;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        /**
         *   Java 允许数组协变
         */
//        List<String> value1 = new ArrayList<>();
//
//        // List<Object> value2 = value1;    // error 编译错误
//
//        List<? extends String> value2 = value1;
//
//        // value2.add(1);   // error
//
//        // value2.add("111");    // error 不允许写入

//        List<String> list = new ArrayList<>();
//
//        list.add("111");
//
//        List<?> list1 = list;
//
//        list.add(1);

//        List<C> list = new ArrayList<>();
//        List<? extends A> list1 = list;
//
//        A b = list1.get(0);

        List<A> list = new ArrayList<>();
        List<? super B> list1 = list;

        list1.add(new C());
    }
}


class A {}
class B extends A {}
class C extends B {}
