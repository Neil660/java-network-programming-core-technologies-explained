package chapter10.src;

import java.lang.reflect.*;

public class ArrayTester2 {
    public static void main(String args[]) {
        int dims[] = new int[]{5, 10, 15};
        Object array = Array.newInstance(Integer.TYPE, dims);
        Object arrayObj = Array.get(array, 3);

        //��ȡ��ǰarrayObj�����Ԫ�ص�����
        Class<?> arrayObjComponentType =
                arrayObj.getClass().getComponentType();

        //��ӡ "class [[I  "������������ά����
        System.out.println(arrayObj.getClass());
        //��ӡ "class [I  "����������һά����
        System.out.println(arrayObjComponentType);

        arrayObj = Array.get(arrayObj, 5);
        Array.setInt(arrayObj, 10, 37);
        int arrayCast[][][] = (int[][][]) array;
        System.out.println(arrayCast[3][5][10]);
    }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
