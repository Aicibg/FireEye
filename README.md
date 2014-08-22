# 轻量级输入校验库 - Fire Eye

轻量级，简单易用的Android校验库。

这是一个简单Android校验库，按配置来验证用户输入的表单信息。
只需要几行代码，即可验证用户输入，并且将验证错误反馈给用户。
它内置了大量常用的验证类型，足以满足你的功能需求。
它还有一个可扩展的验证选项，你可以通过扩展接口添加你需要的验证方式。

![截图](!http://i.imgur.com/nK3CgSh.png)

## Gradle 依赖

Add dependency

```groovy

    dependencies {
        compile 'com.github.yoojia:fire-eye:1.0@aar'
    }

```

Maven

```xml

    <dependency>
        <groupId>com.github.yoojia</groupId>
        <artifactId>fire-eye</artifactId>
        <version>1.0</version>
        <type>aar</type>
        <scope>provided</scope>
    </dependency>

```
## 已内置支持的校验方式

* **Required** 必填选项
* **NotBlank** 非空数据
* **Digits** 仅数字
* **IsDate** 是否为有效日期
* **IsTime** 是否为有效时间
* **IsDateTime** 是否为有效的日期和时间
* **IsFuture** 是否为当前时间之后的日期和时间
* **IsPast** 是否为当前时间之前的日期和时间
* **Email** 电子邮件
* **EqualTo** 与指定值相同
* **Host** 主机地址
* **URL** Http URL
* **IPv4** IPv4地
* **RangeLength** 指定长度范围
* **MinLength** 最小长度
* **MaxLength** 最大长度
* **Numeric** 数值
* **CreditCard** 信用卡号
* **RangeValue** 最值范围
* **MinValue** 最小值
* **MaxValue** 最大值
* **MobilePhone** 中国的手机号码

## How to usage - 如何使用

通过 View ID 来绑定校验配置信息

#### 对表单内各个EditText绑定其校验配置

```java

    // 自定义显示出错消息的方式，默认是在 EditText 右边显示一个浮动提示框。
    MessageDisplay messageDisplay = new MessageDisplay() {
        @Override
        public void dismiss(EditText field) {
            field.setError(null);
        }

        @Override
        public void show(EditText field, String message) {
            field.setError(message);
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    // 绑定表单View
    final LinearLayout form = (LinearLayout) findViewById(R.id.form);

//      默认是在 EditText 右边显示一个浮动提示框。
//      final FireEye eye = new FireEye(form);

//      指定自定义显示出错消息的方式，
    final FireEye eye = new FireEye(form, messageDisplay);

    eye.add(R.id.form_field_1, Type.Required, Type.MobilePhone);
    eye.add(R.id.form_field_2, Type.CreditCard);
    eye.add(R.id.form_field_3, Type.Digits, Type.MaxLength.value(20));
    eye.add(R.id.form_field_4, Type.Email);

    // 使用`ValuesLoader`使得当校验时，才加载校验参数`values`。
    eye.add(R.id.form_field_5, Type.EqualsTo.values(new TextViewValuesLoader(form,R.id.form_field_4)));
    eye.add(R.id.form_field_6, Type.Host);
    eye.add(R.id.form_field_7, Type.URL);
    eye.add(R.id.form_field_8, Type.MaxLength.value(5));
    eye.add(R.id.form_field_9, Type.MinLength.value(4));
    eye.add(R.id.form_field_10, Type.RangeLength.values(4,8));
    eye.add(R.id.form_field_11, Type.NotBlank);
    eye.add(R.id.form_field_12, Type.Numeric);
    eye.add(R.id.form_field_13, Type.MaxValue.value(100));
    eye.add(R.id.form_field_14, Type.MinValue.value(20));
    eye.add(R.id.form_field_15, Type.RangeValue.values(18,30));

    // 添加不在Form中的输入框
    EditText inputNotInForm = (EditText)findViewById(R.id.input_not_in_form);
    eye.add(inputNotInForm, Type.IsTime);

    // 输出调试信息
    eye.debug(true);

    // 应用输入框的输入法布局样式
    eye.applyInputType();

    // 对某些输入框不应用输入法布局样式
    //eye.applyInputType(exclude viewId)

    TestResult r = eye.test();

    if(r.passed){
        // 校验通过
    }else{
        // 校验失败
    }

```

## 如何扩展？

通过 FireEye 的扩展接口，添加你自定义的校验实现类

```java

    // 添加到某个ViewID的输入对象中：
    
    eye.add(R.id.username, new AbstractValidator("出错时，此消息被返回并显示到EditText中") {
        @Override
        public boolean test(String inputValue) {
            // 校验通过时返回 true
            return inputValue.equal("AABB");
        }
    });

    // 添加到某个View的输入对象中：

    eye.add(passwordInput, new AbstractValidator("出错时，此消息被返回并显示到EditText中") {
        @Override
        public boolean test(String inputValue) {
            // 校验通过时返回 true
            return inputValue.equal("CCDD");
        }
    });

```

## 校验参数

可以通过Type.Type的方法对校验类型设置参数，这些参数将在校验时，作为对比数据使用。
但一些格式类的校验（如 `Email`，`CreditCard`等），是不接受参数的。即使设置了也不起作用。
另外，一些校验类型只接受特定数据类型的参数，如 `MaxLength` 等，只接受 long,int 类型参数。

需要特别说明的是时间校验参数：

* `Type.IsTime`, `Type.IsDate`, `Type.IsDateTime` 都接受 `format(String format)` 方法传递的参数，
用于设定时间或者日期和格式，这个不用细说了。

* `Type.IsFuture`, 'Type.IsPast' 同样接受 `format(String format)` 方法传递的参数。这两个校验类型，
如果不指定比较时间，则与当前时间做对比，相差1毫秒或以上即认为校验通过。
通过 `than(String time)` 方法来校验时间基准，这个时间的格式与 `format(String format)` 的时间格式相同。
如 `format("yyyy-MM-dd")` 那么 `than(String time)` 必须为 `than("2014-08-13")`。


## 注意

### 校验顺序

校验顺序按`add(...)`添加配置的顺序进行校验。

**如果添加 `Required` 校验类型，则 `Required` 无论在哪个顺序被添加，都会被首先校验。**

#### `Required`校验类型对其它类型的影响：

当EditText为空值时，如果添加`Required`校验规则，则校验失败；如果没有，则校验通过并跳过后面的校验类型。

### 自定义消息

自定义消息中如果需要与 value(...) / values(...) 中的参数匹配，请使用 `{$1}` 和 `{$2}` 做占位符。

e.g:

> Types.MaxLength.values(10,140).message("您最多可以输入{$1}到{$2}个文字！");

当校验失败时，提示的消息内容为：`您最多可以输入10到140个文字！`

## 其它扩展接口

### 将校验条件应用到EditText中

如“最大长度”、“邮件地址”等校验条件，可以将EditText的输入类型自动切换至相应类型。

### 获取Form中被校验EditText的值

   ```java

       String username = eye.getValue(R.id.form_field_1);

   ```

### 获取Form中没有被校验EditText的值

   ```java

       String username = eye.getExtraValue(R.id.form_field_others);

   ```

### 获取Form中被校验EditText的对象

   ```java

       EditText username = eye.getView(R.id.form_field_1,EditText.class);

   ```

### Contact - 交流方式

* Email: yoojia.chen@gmail.com
* QQ: 228441083
* QQ群：106904788(谨入)
