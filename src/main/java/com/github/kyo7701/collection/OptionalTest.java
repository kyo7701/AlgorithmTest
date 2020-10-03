package com.github.kyo7701.collection;

import java.util.Optional;

/**
 * Author:Mr.Cris
 * Date:2020-09-25 10:32
 *
 * @description
 */
public class OptionalTest {

    static class InnerEntity {
        private String name;
        private String value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    static class Entity {
        private InnerEntity innerEntity;

        public InnerEntity getInnerEntity() {
            return innerEntity;
        }

        public void setInnerEntity(InnerEntity innerEntity) {
            this.innerEntity = innerEntity;
        }
    }



    public static void main(String[] args) {
        String name =null;
        String s = Optional.ofNullable(name).orElse("4567");
        Entity entity = null;
        Optional<Entity> optionalEntity = Optional.ofNullable(entity);
        System.out.println(s);
//        System.out.println(optionalEntity.orElseGet(() -> {return entity.getInnerEntity();}));
    }

}
