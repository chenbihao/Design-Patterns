package src.builder;

import java.lang.reflect.Constructor;

/**
 * @author: chenbihao
 * @create: 2021/11/15
 * @Description:
 * @History:
 */
public class ConstructorArg {

    /**
     * 当 isRef 为 true 的时候，arg 表示 String 类型的 refBeanId ，type 不需要设置
     * 当 isRef 为 false 的时候，arg、type 都需要设置。
     */
    private boolean isRef;
    private Class type;
    private Object arg;

    public boolean isRef() {
        return isRef;
    }

    public Class getType() {
        return type;
    }

    public Object getArg() {
        return arg;
    }

    /**
     * 唯一的构造函数（创建方法）
     *
     * @param builder
     */
    private ConstructorArg(Builder builder) {
        this.isRef = builder.isRef;
        this.type = builder.type;
        this.arg = builder.arg;
    }

    /**
     * 生成器
     */
    public static class Builder {
        private boolean isRef;
        private Class type;
        private Object arg;

        /**
         * 最后调用的生成方法
         */
        public ConstructorArg build() {
            // 这里做校验
            if(isRef && type != null) {
                throw new IllegalArgumentException("校验异常");
            }
            // ... 省略一堆校验逻辑

            // 这里创建对象
            return new ConstructorArg(this);
        }

        /**
         * 构造步骤 x1
         */
        public Builder setRef(boolean ref) {
            this.isRef = ref;
            return this;
        }

        /**
         * 构造步骤 x2
         */
        public Builder setType(Class type) {
            this.type = type;
            return this;
        }

        /**
         * 构造步骤 x3
         */
        public Builder setArg(Object arg) {
            this.arg = arg;
            return this;
        }
    }
}
