# 建造者模式（Builder）

## 介绍

建造者模式（又叫生成器模式、构建者模式）：

建造者模式是一种创建型设计模式， 使你能够分步骤创建复杂对象。 该模式允许你使用相同的创建代码生成不同类型和形式的对象。

## 适用场景

- 避免重叠构造函数（telescopic constructor）
- 希望创建不同形式的产品或分步骤构造产品
- 类属性有依赖或约束关系时（如单个set无法满足多个值的校验）
- 创建不可变对象（构建前赋值）
- ...

## 实现方式

- 创建一个Builder类（原对象内部类或者独立的类都可以）
- 实现其构造步骤（每个构造器的set）
- 实现build方法（包括校验逻辑与 **创建逻辑** ）
- 实现原对象的构造函数（参数为Builder，即创建方法）

## 优缺点

优点：

- 可以分步创建 或延缓创建 或递归创建
- 生成不同形式的产品时可以复用代码
- 单一职责

缺点：

- 需要新增类，复杂度增加

### 与其他模式的关系

- 工厂模式是用来创建不同但是相关的对象，建造者模式是用来创建一种类型的复杂对象

--- 

## 示例

### 饿汉式

```java
public class ConstructorArg {

    /**
     * 当 isRef 为 true 的时候，arg 表示 String 类型的 refBeanId ，type 不需要设置
     * 当 isRef 为 false 的时候，arg、type 都需要设置。
     */
    private boolean isRef;
    private Class type;
    private Object arg;

    // ...  省略3个get方法   注意：这里没有set方法，只有一个参数为builder的构造函数

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
     * 这里我们将Builder类设计成了ConstructorArg的内部类。 
     * 也可以将Builder类设计成独立的非内部类ConstructorArgBuilder。
     */
    public static class Builder {
        private boolean isRef;
        private Class type;
        private Object arg;

        /**
         * 最后调用的生成方法
         */
        /**
         * 最后调用的生成方法
         */
        public ConstructorArg build() {
            // 这里做校验
            if (isRef && type != null) {
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
```

测试代码

```java
public class BuilderTest {
    @Test
    public void testBuilder1() {

        ConstructorArg byId = new ConstructorArg.Builder().setRef(true).setArg("id").build();
        ConstructorArg byType = new ConstructorArg.Builder().setRef(false).setArg("123").setType(Integer.class).build();

    }

    @Test
    public void testBuilderException() {

        IllegalArgumentException exception1 = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            ConstructorArg byId = new ConstructorArg.Builder().setRef(true).setType(Integer.class).build();
        });
        Assertions.assertEquals("校验异常", exception1.getMessage());

    }
}
```

--- 

## 实例

### JDK

#### 字符串构建类

java.lang.StringBuilder （ 非同步 ）

java.lang.StringBuffer （ 同步 ）

属于独立的 Builder 实现方式

```java
public final class StringBuilder
        extends AbstractStringBuilder
        implements java.io.Serializable, Comparable<StringBuilder>, CharSequence {

    // 构造器的构造函数们

    public StringBuilder() {
        super(16);
    }

    public StringBuilder(int capacity) {
        super(capacity);
    }

    public StringBuilder(String str) {
        super(str.length() + 16);
        append(str);
    }

    public StringBuilder(CharSequence seq) {
        this(seq.length() + 16);
        append(seq);
    }

    // 构造步骤 列举几个方法  其他省略

    public StringBuilder append(Object obj) {
        return append(String.valueOf(obj));
    }

    public StringBuilder append(String str) {
        super.append(str);
        return this;
    }

    public StringBuilder insert(int offset, Object obj) {
        super.insert(offset, obj);
        return this;
    }

    public StringBuilder insert(int offset, String str) {
        super.insert(offset, str);
        return this;
    }

    public StringBuilder reverse() {
        super.reverse();
        return this;
    }

    // 一般我们最终调用的方法
    public String toString() {
        // Create a copy, don't share the array
        return isLatin1() ? StringLatin1.newString(value, 0, count)
                : StringUTF16.newString(value, 0, count);
    }

    private void writeObject(java.io.ObjectOutputStream s) {
        // ...
    }

    private void readObject(java.io.ObjectInputStream s) {
        // ...
    }
}
```

#### 日历类 Calendar

java.util.Calendar

属于内部类的 Builder 实现方式

同时，这里也混合了工厂模式： 根据不同的 type 创建了不同的 Calendar 子类

```java
public abstract class Calendar implements Serializable, Cloneable, Comparable<Calendar> {
    //...
    public static class Builder {
        private static final int NFIELDS = FIELD_COUNT + 1;
        private static final int WEEK_YEAR = FIELD_COUNT;
        private long instant;
        private int[] fields;
        private int nextStamp;
        private int maxFieldIndex;
        private String type;
        private TimeZone zone;
        private boolean lenient = true;
        private Locale locale;
        private int firstDayOfWeek, minimalDaysInFirstWeek;

        public Builder() {
        }

        public Builder setInstant(long instant) {
            if (fields != null) {
                throw new IllegalStateException();
            }
            this.instant = instant;
            nextStamp = COMPUTED;
            return this;
        }

        //...省略n多set()方法,比如
        // public Builder setCalendarType(String type) 
        // public Builder setLocale(Locale locale)
        // public Builder setWeekDefinition(int firstDayOfWeek, int minimalDaysInFirstWeek)

        public Calendar build() {
            if (locale == null) {
                locale = Locale.getDefault();
            }
            if (zone == null) {
                zone = TimeZone.getDefault();
            }
            Calendar cal;
            if (type == null) {
                type = locale.getUnicodeLocaleType("ca");
            }
            if (type == null) {
                if (locale.getCountry() == "TH" && locale.getLanguage() == "th") {
                    type = "buddhist";
                } else {
                    type = "gregory";
                }
            }
            switch (type) {
                case "gregory":
                    cal = new GregorianCalendar(zone, locale, true);
                    break;
                case "iso8601":
                    GregorianCalendar gcal = new GregorianCalendar(zone, locale, true);
                    // make gcal a proleptic Gregorian
                    gcal.setGregorianChange(new Date(Long.MIN_VALUE));
                    // and week definition to be compatible with ISO 8601
                    setWeekDefinition(MONDAY, 4);
                    cal = gcal;
                    break;
                case "buddhist":
                    cal = new BuddhistCalendar(zone, locale);
                    cal.clear();
                    break;
                case "japanese":
                    cal = new JapaneseImperialCalendar(zone, locale, true);
                    break;
                default:
                    throw new IllegalArgumentException("unknown calendar type: " + type);
            }
            cal.setLenient(lenient);
            if (firstDayOfWeek != 0) {
                cal.setFirstDayOfWeek(firstDayOfWeek);
                cal.setMinimalDaysInFirstWeek(minimalDaysInFirstWeek);
            }
            if (isInstantSet()) {
                cal.setTimeInMillis(instant);
                cal.complete();
                return cal;
            }

            if (fields != null) {
                boolean weekDate = isSet(WEEK_YEAR) && fields[WEEK_YEAR] > fields[YEAR];
                if (weekDate && !cal.isWeekDateSupported()) {
                    throw new IllegalArgumentException("week date is unsupported by " + type);
                }
                for (int stamp = MINIMUM_USER_STAMP; stamp < nextStamp; stamp++) {
                    for (int index = 0; index <= maxFieldIndex; index++) {
                        if (fields[index] == stamp) {
                            cal.set(index, fields[NFIELDS + index]);
                            break;
                        }
                    }
                }

                if (weekDate) {
                    int weekOfYear = isSet(WEEK_OF_YEAR) ? fields[NFIELDS + WEEK_OF_YEAR] : 1;
                    int dayOfWeek = isSet(DAY_OF_WEEK) ? fields[NFIELDS + DAY_OF_WEEK] : cal.getFirstDayOfWeek();
                    cal.setWeekDate(fields[NFIELDS + WEEK_YEAR], weekOfYear, dayOfWeek);
                }
                cal.complete();
            }
            return cal;
        }
    }
}
```

### Guava

java.lang.Object com.google.common.cache.**CacheBuilder**

使用 Google Guava 来构建内存缓存:

```java
public class CacheDemo {
    public static void main(String[] args) {
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .initialCapacity(100)
                .maximumSize(1000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build();

        cache.put("key1", "value1");
        String value = cache.getIfPresent("key1");
        System.out.println(value);
    }
}
```

