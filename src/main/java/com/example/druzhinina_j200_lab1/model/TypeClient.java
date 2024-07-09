package com.example.druzhinina_j200_lab1.model;

public class TypeClient {

    public enum typeEnum {
        yl("Физическое лицо"),
        fl("Юридическое лицо");        ;
        private final String type3;
        public String getType3() {
            return type3;
        }
        typeEnum(String type3) {
            this.type3 = type3;
        }
        @Override
        public String toString() {
            return getType3();
        }
    }
}
