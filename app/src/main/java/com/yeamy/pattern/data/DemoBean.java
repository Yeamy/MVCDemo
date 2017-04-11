package com.yeamy.pattern.data;

/**
 * 只是一个数据结构
 */
public class DemoBean {
    public String msg;
    public String name;
    private int state;

    public DemoBean(String name, int state) {
        this.name = name;
        this.state = state;
        this.msg = isOk() ? "成功" : "失败";
    }

    /**
     * 所有方法只是为了方便读取数据结构的信息
     */
    public boolean isOk() {
        return state > 0;
    }

    public void setState(int state) {
        this.state = state;
    }

}
