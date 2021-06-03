package com.prestu.test;

public enum TestEnum {
    TEST_ENUM(1,"ok1"),
    TEST_ENUM2(2,"ok2"),
    TEST_ENUM3(3);
    private int seq;
    private String desc;
    TestEnum(int seq, String desc) {
        this.seq = seq;
        this.desc = desc;
    }

    TestEnum(int seq) {
        this.seq = seq;
    }

    public static void main(String[] args) {

        int seq = TestEnum.TEST_ENUM.seq;
        String desc = TestEnum.TEST_ENUM.desc;
        System.out.println(seq+","+desc);
    }
}
