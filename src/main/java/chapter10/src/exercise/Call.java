package chapter10.src.exercise;

import java.io.*;

public class Call implements Serializable {
    private String className;  //��ʾ����
    private String methodName; //��ʾ������
    private Class[] paramTypes; //��ʾ������������
    private Object[] params; //��ʾ��������ֵ
    private Object result;  //��ʾ�����ķ���ֵ���߷����׳����쳣

    public Call() {
    }

    public Call(String className, String methodName, Class[] paramTypes,
                Object[] params) {
        this.className = className;
        this.methodName = methodName;
        this.paramTypes = paramTypes;
        this.params = params;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class[] getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(Class[] paramTypes) {
        this.paramTypes = paramTypes;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String toString() {
        String firstParam = "";
        if (params.length > 0) firstParam = (String) params[0];
        return "className=" + className + " methodName=" + methodName
                + " firstParam=" + params[0];
    }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/