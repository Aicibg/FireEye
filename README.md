# 轻量级输入校验库 - Fire Eye

轻量级，简单易用的Android校验库。

这是一个简单Android校验库，按配置来验证用户输入的表单信息。
只需要几行代码，即可验证用户输入，并且将验证错误反馈给用户。
它内置了大量常用的验证类型，足以满足你的功能需求。
它还有一个可扩展的验证选项，你可以通过扩展接口添加你需要的验证方式。

![截图](http://i.imgur.com/sucjaqE.png)

## Gradle 依赖

Add dependency

    dependencies {
        compile 'com.github.yoojia:fire-eye:2.0@aar'
    }

Maven

    <dependency>
        <groupId>com.github.yoojia</groupId>
        <artifactId>fire-eye</artifactId>
        <version>2.0</version>
        <type>aar</type>
    </dependency>

## 已内置支持的校验方式

* **Required** 必填选项
* **NotBlank** 非空数据
* **Digits** 仅数字
* **Email** 电子邮件
* **EqualTo** 与指定值相同
* **Host** 主机地址
* **URL** Http URL
* **IPv4** IPv4地
* **RangeLength** 指定长度范围
* **MinLength** 最小长度
* **MaxLength** 最大长度
* **Numeric** 数值
* **BankCard** 信用卡号/银行卡号
* **RangeValue** 最值范围
* **MinValue** 最小值
* **MaxValue** 最大值
* **Mobile** 中国的手机号码
* **VehicleNumber** 中国的民用车辆号牌
* **IDCard** 中国的身份证号（15位和18位）
## How to use - 如何使用

通过 View ID 来绑定校验配置信息

#### 对表单内各个EditText绑定其校验配置

```java

    // 自定义显示出错消息的方式，默认是在 EditText 右边显示一个浮动提示框。
    MessageDisplay messageDisplay = new MessageDisplay() {
        @Override
        public void dismiss(TextView field) {
            field.setError(null);
        }

        @Override
        public void show(TextView field, String message) {
            field.setError(message);
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

        View formView = findViewById(R.id.form);
        Form form = new Form(formView);

        fireEye = new FireEye();
        fireEye.add(form.byId(R.id.form_field_1), StaticPattern.Required, StaticPattern.Mobile);
        fireEye.add(form.byId(R.id.form_field_2), StaticPattern.BankCard);

        fireEye.add(form.byId(R.id.form_field_3), StaticPattern.Digits);
        fireEye.add(form.byId(R.id.form_field_3), ValuePattern.MaxLength.setValue(20));

        fireEye.add(form.byId(R.id.form_field_4), StaticPattern.Required, StaticPattern.Email);
        fireEye.add(form.byId(R.id.form_field_5), ValuePattern.Required, ValuePattern.EqualsTo.lazy(new TextViewLoader(form.byId(R.id.form_field_4))));
        fireEye.add(form.byId(R.id.form_field_6), StaticPattern.Host);
        fireEye.add(form.byId(R.id.form_field_7), StaticPattern.URL);
        fireEye.add(form.byId(R.id.form_field_8), ValuePattern.MaxLength.setValue(5));
        fireEye.add(form.byId(R.id.form_field_9), ValuePattern.MinLength.setValue(4));
        fireEye.add(form.byId(R.id.form_field_10), ValuePattern.RangeLength.setFirstValue(4L).setSecondValue(8L));
        fireEye.add(form.byId(R.id.form_field_11), StaticPattern.NotBlank);
        fireEye.add(form.byId(R.id.form_field_12), StaticPattern.Numeric);
        fireEye.add(form.byId(R.id.form_field_13), ValuePattern.MaxValue.setValue(100));
        fireEye.add(form.byId(R.id.form_field_14), ValuePattern.MinValue.setValue(20));
        fireEye.add(form.byId(R.id.form_field_15), ValuePattern.RangeValue.setFirstValue(18L).setSecondValue(30L));

    // 输出调试信息
        FireEyeEvt.isDebug = true;

        TestResult r = eye.test();

        if(r.passed){
            // 校验通过
        }else{
            // 校验失败
        }

```

