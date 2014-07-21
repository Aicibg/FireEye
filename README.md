# Android校验库 - Android Validation

简单易用的Android校验库。

这是一个简单Android校验库，按配置来验证用户输入的表单信息。
只需要几行代码，即可验证用户输入，并且将验证错误反馈给用户。
它内置了大量常用的验证类型，足以满足你的功能需求。
它还有一个可扩展的验证选项，你可以通过扩展接口添加你需要的验证方式。

![截图](https://raw.githubusercontent.com/chenyoca/android-validation/develop/documents/screenshot.png)

## Gradle 依赖

Add repository

```groovy

    allprojects {
        repositories {
            // !!!! ADD THIS !!!!
            maven{ url 'http://oss.sonatype.org/content/groups/public/' }
        }
    }

```

Add dependency

```groovy

    dependencies {
        compile 'com.github.chenyoca:android-validation:0.1-SNAPSHOT'
    }

```

Maven

```xml

    <dependency>
        <groupId>com.github.chenyoca</groupId>
        <artifactId>android-validation</artifactId>
        <version>2.4-SNAPSHOT</version>
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

## 如何使用？

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
//      final FormValidator av = new FormValidator(form);

//      指定自定义显示出错消息的方式，
    final FormValidator av = new FormValidator(form, messageDisplay);

    av.add(R.id.form_field_1, Type.Required, Type.MobilePhone);
    av.add(R.id.form_field_2, Type.CreditCard);
    av.add(R.id.form_field_3, Type.Digits, Type.MaxLength.value(20));
    av.add(R.id.form_field_4, Type.Email);

    // 使用`ValuesLoader`使得当校验时，才加载校验参数`values`。
    av.add(R.id.form_field_5, Type.EqualsTo.values(new EditTextValuesLoader(form,R.id.form_field_4)));
    av.add(R.id.form_field_6, Type.Host);
    av.add(R.id.form_field_7, Type.URL);
    av.add(R.id.form_field_8, Type.MaxLength.value(5));
    av.add(R.id.form_field_9, Type.MinLength.value(4));
    av.add(R.id.form_field_10, Type.RangeLength.values(4,8));
    av.add(R.id.form_field_11, Type.NotBlank);
    av.add(R.id.form_field_12, Type.Numeric);
    av.add(R.id.form_field_13, Type.MaxValue.value(100));
    av.add(R.id.form_field_14, Type.MinValue.value(20));
    av.add(R.id.form_field_15, Type.RangeValue.values(18,30));

    // 输出调试信息
    av.debug(true);

    // 应用输入框的输入法布局样式
    av.applyInputType();

    TestResult r = av.test();

    if(r.passed){
        // 校验通过
    }else{
        // 校验失败
    }

```

## 如何扩展？

通过 Config的扩展接口，添加你自定义的校验实现类

```java

    // 添加到已创建的Config中：
    
    conf.add(R.id.username, new AbstractValidator("出错时，此消息被返回并显示到EditText中") {
        @Override
        public boolean test(String inputValue) {
            // 校验通过时返回 true
            return inputValue.equal("AABB");
        }
    });
    

```

## 注意

### 校验顺序

校验顺序按Config添加配置的顺序进行校验。

**如果添加 `Required` 校验类型，则 `Required` 无论在哪个顺序被添加，都会被首先校验。**

#### `Required`校验类型对其它类型的影响：

当EditText为空值时，如果添加`Required`校验规则，则校验失败；如果没有，则校验通过并跳过后面的校验类型。

### 自定义消息

自定义消息中如果需要与 value(...) / values(...) 中的参数匹配，请使用 `{$1}` 和 '{$2}' 做占位符。

e.g:

> Types.MaxLength.values(10,140).message("您最多可以输入{$1}到{$2}个文字！");

当校验失败时，提示的消息内容为：`您最多可以输入10到140个文字！`

## 其它扩展接口

### 将校验条件应用到EditText中

如“最大长度”、“邮件地址”等校验条件，可以将EditText的输入类型自动切换至相应类型。

### 获取EditText的值

```java

    String username = validator.getValue(R.id.form_field_1);

```

